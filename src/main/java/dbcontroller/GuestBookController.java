package dbcontroller;

import java.sql.*;
import java.util.List;

public interface GuestBookController {

    void addRecord(String message, Connection connection);
    public List<Record> getRecords(Connection connection);
}
