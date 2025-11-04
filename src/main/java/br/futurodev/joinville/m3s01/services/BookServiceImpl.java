package br.futurodev.joinville.m3s01.services;

import br.futurodev.joinville.m3s01.dtos.BookRequestDto;
import br.futurodev.joinville.m3s01.dtos.BookResponseDto;
import br.futurodev.joinville.m3s01.entities.Author;
import br.futurodev.joinville.m3s01.entities.Book;
import br.futurodev.joinville.m3s01.entities.Category;
import br.futurodev.joinville.m3s01.errors.exceptions.BookNotFoundException;
import br.futurodev.joinville.m3s01.mappers.BookMapper;
import br.futurodev.joinville.m3s01.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookMapper mapper;
    private final BookRepository repository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    @Override
    public BookResponseDto create(BookRequestDto dto) {
        Book entity = mapper.requestCreateToEntity(dto);

        enrichBook(entity, dto);

        entity = repository.save(entity);
        return mapper.entityToResponse(entity);
    }

    @Override
    public BookResponseDto findById(Long id) {
        return mapper.entityToResponse(findEntityById(id));
    }

    @Override
    public List<BookResponseDto> findAll() {
        return repository.findAll()
                .stream().map(mapper::entityToResponse)
                .toList();
    }

    @Override
    public BookResponseDto update(Long id, BookRequestDto dto) {
        Book entity = findEntityById(id);
        mapper.requestUpdateToEntity(entity, dto);

        enrichBook(entity, dto);

        entity = repository.save(entity);
        return mapper.entityToResponse(entity);
    }

    @Override
    public void delete(Long id) {
        Book entity = findEntityById(id);
        repository.delete(entity);
    }

    @Override
    public Book findEntityById(Long id) {
        return repository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    private void enrichBook(Book entity, BookRequestDto dto) {
        Author author = null;
        Category category = null;
        if (dto.authorId() != null) {
            author = authorService.findEntityById(dto.authorId());
        }
        if (dto.categoryId() != null) {
            category = categoryService.findEntityById(dto.categoryId());
        }
        entity.setAuthor(author);
        entity.setCategory(category);
    }

}
