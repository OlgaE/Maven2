package dbcontroller;

import java.sql.*;

public class Add implements Commands {

    @Override
    public void exec(String line, Connection connection, GuestBookController storage) {

        String[] lineArr = line.split(" ");
        if (lineArr.length > 1) {

            storage.addRecord(line.substring(4), connection);
            System.out.println(line);

        } else {
            System.out.println("Command not found");
        }
    }
}
