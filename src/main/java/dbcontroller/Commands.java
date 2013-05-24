package dbcontroller;

import java.sql.*;

public interface Commands {

    public void exec(String line, Connection connection, GuestBookController storage);
}
