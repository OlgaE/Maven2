package dbcontroller;

public class List implements Commands {
    @Override
    public void exec(String line, GuestBookController storage) {

        String[] lineArr = line.split(" ");

        if (lineArr[0].equals(("list"))) {
            storage.getRecords();
        } else{
            System.out.println("Command not found.");
        }
    }
}
