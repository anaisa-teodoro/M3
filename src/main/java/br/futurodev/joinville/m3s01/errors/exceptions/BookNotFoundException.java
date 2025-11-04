package br.futurodev.joinville.m3s01.errors.exceptions;

public class BookNotFoundException extends NotFoundException {
    public BookNotFoundException(Long id) {
        super("book", id);
    }
}
