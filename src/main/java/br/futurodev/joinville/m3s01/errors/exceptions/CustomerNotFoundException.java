package br.futurodev.joinville.m3s01.errors.exceptions;

public class CustomerNotFoundException extends NotFoundException {
    public CustomerNotFoundException(Long id) {
        super("customer", id);
    }
}
