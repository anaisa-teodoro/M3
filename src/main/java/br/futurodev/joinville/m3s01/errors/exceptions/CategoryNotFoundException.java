package br.futurodev.joinville.m3s01.errors.exceptions;

public class CategoryNotFoundException extends NotFoundException {
    public CategoryNotFoundException(Long id) {
        super("category", id);
    }
}
