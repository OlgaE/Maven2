package dbcontroller;

import java.sql.*;

public class List implements Commands {
    @Override
    public void exec(String line, Connection connection, GuestBookController storage) {

        String[] lineArr = line.split(" ");

        if (lineArr[0].equals(("list"))) {
            storage.getRecords(connection);
        } else{
            System.out.println("Command not found.");
        }
    }
}
