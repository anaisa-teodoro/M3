package br.futurodev.joinville.m3s01.errors.exceptions;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(Long id) {
        super("user", id);
    }
}
