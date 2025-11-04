package br.futurodev.joinville.m3s01.services;

import br.futurodev.joinville.m3s01.dtos.LoanBookRequestDto;
import br.futurodev.joinville.m3s01.dtos.LoanRequestDto;
import br.futurodev.joinville.m3s01.dtos.LoanResponseDto;
import br.futurodev.joinville.m3s01.entities.*;
import br.futurodev.joinville.m3s01.enums.LoanStatus;
import br.futurodev.joinville.m3s01.errors.exceptions.LoanNotFoundException;
import br.futurodev.joinville.m3s01.mappers.LoanMapper;
import br.futurodev.joinville.m3s01.repositories.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final LoanMapper mapper;
    private final BookService bookService;
    private final LoanRepository repository;
    private final CustomerService customerService;

    @Override
    public LoanResponseDto create(LoanRequestDto dto) {
        Loan entity = mapper.requestToEntity(dto);

        enrichLoan(entity, dto);

        entity = repository.save(entity);
        return mapper.entityToResponse(entity);
    }

    @Override
    public LoanResponseDto findById(Long id) {
        return mapper.entityToResponse(findEntityById(id));
    }

    @Override
    public List<LoanResponseDto> findAll() {
        return repository.findAll()
                .stream().map(mapper::entityToResponse)
                .toList();
    }

    @Override
    public void returnLoan(Long id) {
        Loan entity = findEntityById(id);
        for (LoanBook item : entity.getItems()) {
            item.setReturnedDate(LocalDate.now());
        }
        entity.setStatus(LoanStatus.RETURNED);
        repository.save(entity);
    }

    @Override
    public void returnBook(Long loanId, Long bookId) {
        Loan entity = findEntityById(loanId);

        boolean foundItem = false;
        LoanStatus status = LoanStatus.RETURNED;

        for (LoanBook item : entity.getItems()) {
            Book book = bookService.findEntityById(bookId);
            if (item.getBook().getId().equals(book.getId())) {
                foundItem = true;
                item.setReturnedDate(LocalDate.now());
            }

            if (item.getReturnedDate() == null) {
                status = LoanStatus.PARTIAL;
            }
        }
        if (!foundItem) return;

        entity.setStatus(status);
        repository.save(entity);
    }

    private Loan findEntityById(Long id) {
        return repository.findById(id).orElseThrow(() -> new LoanNotFoundException(id));
    }

    private void enrichLoan(Loan entity, LoanRequestDto dto) {
        Customer customer = null;
        if (dto.customerId() != null) {
            customer = customerService.findEntityById(dto.customerId());
        }
        entity.setCustomer(customer);

        Integer bookQty = 0;
        for (LoanBookRequestDto item : dto.items()) {
            LoanBook itemEntity = mapper.requestToEntity(item);
            itemEntity.setLoan(entity);
            entity.getItems().add(itemEntity);

            Book book = null;
            if (item.bookId() != null) {
                book = bookService.findEntityById(item.bookId());
            }
            itemEntity.setBook(book);

            if (itemEntity.getQuantity() == null || itemEntity.getQuantity() == 0) {
                itemEntity.setQuantity(1);
            }

            bookQty += itemEntity.getQuantity();
        }

        entity.setBookQuantity(bookQty);
    }

}
