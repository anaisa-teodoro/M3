package br.futurodev.joinville.m3s01.errors.exceptions;

public class LoanNotFoundException extends NotFoundException {
    public LoanNotFoundException(Long id) {
        super("loan", id);
    }
}
