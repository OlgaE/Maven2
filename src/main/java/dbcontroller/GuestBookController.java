package dbcontroller;

import java.util.List;

public interface GuestBookController {

    void addRecord(String message);
    public List<Record> getRecords();
}
