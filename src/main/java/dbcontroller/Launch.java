package dbcontroller;

public class Launch {

    public static void main(String[] args) {

        // Creating a database:
        DbManager database = new DbManager();
        database.dbInit();

        // Creating a storage object:
        GuestBookController storage = new GuestBookStorage();

        // Launching:
        new Input(storage);
    }
}
