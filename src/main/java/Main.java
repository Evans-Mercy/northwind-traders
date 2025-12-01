import java.sql.Connection;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException{
        Connection connection;
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", "root", "yearup");

        Statement statement = connection.createStatement();

        String query = """
                SELECT ProductID, ProductName, UnitPrice, UnitsInStock
                FROM products""";

        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
            String product = results.getString("ProductID");
            System.out.println(product);
        }
        connection.close();

    }
}
