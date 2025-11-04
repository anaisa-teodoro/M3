package br.futurodev.joinville.m3s01.errors.exceptions;

public abstract class NotFoundException extends RuntimeException {

    public NotFoundException(Long id) {
        super("Not found with id: " + id);
    }

    protected NotFoundException(String entity, Long id) {
        super("Not found " + entity + " with id: " + id);
    }

}
