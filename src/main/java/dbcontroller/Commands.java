package dbcontroller;

public interface Commands {

    public void exec(String line, GuestBookController storage);
}
