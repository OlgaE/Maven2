package MavenProject2;

import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class Database implements GuestBookController {
    public static void main(String[] args) {

        Database ob = new Database();

        // Creating a table or overwriting an old one:
        try {
            Class.forName("org.h2.Driver");
            try (Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "Olga", "jgd")) {

                System.out.println("Connected. " + connection.toString());
                Statement stmt = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);

                stmt.executeUpdate("DROP TABLE IF EXISTS autoInc1");
                stmt.executeUpdate(
                        "CREATE TABLE autoInc1 ("
                                + "id INT NOT NULL AUTO_INCREMENT, "
                                + "postDate VARCHAR(64), "
                                + "message VARCHAR(64), PRIMARY KEY (id))");

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Reading System.in:
        InputStream in = System.in;
        try (Scanner scanner = new Scanner(in)) {
            String nextLine;
            while (scanner.hasNextLine()) {
                nextLine = scanner.nextLine();

                String[] fullData = nextLine.split(" ");

                if (fullData[0].equals("add")&& fullData.length > 1) {
                    ob.addRecord(nextLine.substring(4));

                } else if (nextLine.equals(("list"))) {
                    ob.getRecords();

                } else if (nextLine.equals("exit")) {
                    System.exit(0);

                } else if (fullData[0].equals("add") && fullData.length == 1) {
                    System.out.println("\" add \" command must be followed by a message.");

                } else {
                    System.out.println("Command not found.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addRecord(String message) {
        try {
            Class.forName("org.h2.Driver");
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

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Record> getRecords() {

        List<Record> list = new ArrayList<>();

        try {
            Class.forName("org.h2.Driver");
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

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return list;
    }
}
