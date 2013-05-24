package dbcontroller;

import java.sql.*;

public class Launch {

    //public static void main(String[] args) {
    public void launchInit(Connection connection) {

        // Creating a storage object:
        GuestBookController storage = new GuestBookStorage();

        // Launching:
        new Input().readInput(connection, storage);
    }
}
