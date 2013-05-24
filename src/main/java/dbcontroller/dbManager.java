package dbcontroller;

import java.sql.*;

public class DbManager {

    public static void main(String[] args){
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


            new Launch().launchInit(connection);


            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
