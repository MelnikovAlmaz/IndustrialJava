package ru.stc.services;

public interface LibrarianService<E, PK> {
    boolean authenticate(String email, String password);
}
