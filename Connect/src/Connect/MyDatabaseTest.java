package Connect;

import java.sql.*;

public class MyDatabaseTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "Murali#13";

        try {
            // 1. Connect to the MySQL Server (without specifying a DB initially to avoid 'Unknown DB' error)
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", user, password);
            System.out.println("Connected to the MySQL Server!");

            Statement st = connection.createStatement();

            // 2. Create the Database if it doesn't exist
//            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS Connect");
            st.execute("USE Connect");
            System.out.println("Database 'Connect' is ready!");

            // 3. Define and Create the Table
            String createTableSQL = "CREATE TABLE IF NOT EXISTS members ("
                                  + "id INT AUTO_INCREMENT PRIMARY KEY, "
                                  + "name VARCHAR(100), "
                                  + "email VARCHAR(100))";

            st.executeUpdate(createTableSQL);
            System.out.println("Table 'members' is ready in database 'Connect'!");
            String InsertTableSQL = "INSERT INTO MEMBERS VALUES(13,'MURALI','MURALIPAILA@GMAIL.COM')";
            System.out.println("Data inserted Successfully!");
          
//            st.executeUpdate(InsertTableSQL);
            String UpdateTableSQL = "UPDATE MEMBERS  SET EMAIL = 'muralipaila@gmail.com' where id =13 ";
            st.executeUpdate(UpdateTableSQL);
            
            
            

            // 4. Always close the statement and connection
            st.close();
            connection.close();
            
        } catch (SQLException e) {
            System.out.println("Database error occurred!");
            e.printStackTrace();
        }
    }
}