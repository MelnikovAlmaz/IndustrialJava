package model.dao.interfaces;

public interface LibrarianDAO {
    boolean authenticate(String email, String password);
}
