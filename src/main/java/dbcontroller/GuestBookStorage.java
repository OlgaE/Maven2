package dbcontroller;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class GuestBookStorage implements GuestBookController {


    @Override
    public void addRecord(String message) {

        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "Olga", "jgd")) {

            String s1 = "INSERT INTO autoInc1 (postDate, message) values (?,?)";
            PreparedStatement statementNew = connection.prepareStatement(s1, Statement.RETURN_GENERATED_KEYS);

            String timeStamp = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
            statementNew.setString(1, timeStamp);
            statementNew.setString(2, message);
            statementNew.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Record> getRecords() {

        List<Record> list = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "Olga", "jgd")) {

            Statement stmt = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            ResultSet res = stmt.executeQuery("SELECT * FROM autoInc1");

            int count = 0;
            while (res.next()) {
                Record recordObject = new Record();

                recordObject.id = res.getInt("id");
                recordObject.postDate = res.getString("postDate");
                recordObject.message = res.getString("message");
                list.add(recordObject);

                System.out.println(list.get(count).id + "\t" + list.get(count).postDate + "\t" + list.get(count).message);
                count++;
            }
            //

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
