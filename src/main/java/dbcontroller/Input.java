package dbcontroller;

import java.io.InputStream;
import java.util.Scanner;

public class Input {

    GuestBookController storage;

    public Input(GuestBookController storage){
        this.storage = storage;
        readInput();
    }

    public void readInput(){

        // Reading System.in:
        InputStream in = System.in;
        try (Scanner scanner = new Scanner(in)) {
            String nextLine;
            while (scanner.hasNextLine()) {
                nextLine = scanner.nextLine();

                String[] fullData = nextLine.split(" ");

                try{Commands command = Factory.createCommand(fullData[0]);
                    command.exec(nextLine, storage);
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
