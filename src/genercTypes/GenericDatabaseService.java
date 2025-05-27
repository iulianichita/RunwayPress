package genercTypes;

import java.sql.SQLException;

public interface GenericDatabaseService<T> {
    void create(T t) throws SQLException;
    void read() throws SQLException;
    String getById(int id) throws SQLException;
    void update(T t) throws SQLException;
    void delete(T t) throws SQLException;

}

