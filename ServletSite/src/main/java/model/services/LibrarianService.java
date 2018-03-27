package model.services;

import java.util.List;

public interface LibrarianService<E, PK> {
    boolean authenticate(String email, String password);
}
