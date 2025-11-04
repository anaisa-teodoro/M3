package br.futurodev.joinville.m3s01.errors.exceptions;

public class AuthorNotFoundException extends NotFoundException {
    public AuthorNotFoundException(Long id) {
        super("author", id);
    }
}
