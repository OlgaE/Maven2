package dbcontroller;

public class Factory {

    public static Commands createCommand(String com){

        if(com.equals("add")){
            return new Add();
        } else if(com.equals("list")){
            return new List();
        } else {
            System.out.println("Command not found.");
            return null;
            //System.exit(0);
        }
    }
}
