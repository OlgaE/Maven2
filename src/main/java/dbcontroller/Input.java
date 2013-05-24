package dbcontroller;

import java.sql.*;
import java.io.InputStream;
import java.util.Scanner;

public class Input {

//    GuestBookController storage;
//    Connection connection;
//
//    public Input(Connection connection, GuestBookController storage){
//        this.connection = connection;
//        this.storage = storage;
//        readInput();
//    }

    public void readInput(Connection connection, GuestBookController storage){

        // Reading System.in:
        InputStream in = System.in;
        try (Scanner scanner = new Scanner(in)) {
            String nextLine;
            while (scanner.hasNextLine()) {
                nextLine = scanner.nextLine();

                String[] fullData = nextLine.split(" ");

                try{Commands command = Factory.createCommand(fullData[0]);
                    command.exec(nextLine, connection, storage);
                } catch(Exception e){
                    if (fullData[0].equals("exit")){
                        System.exit(0);
                    } else{
                        System.out.println("Command not found.");
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
