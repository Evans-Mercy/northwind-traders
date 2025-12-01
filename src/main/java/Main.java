import java.sql.Connection;
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("What do you want to do?");
        System.out.println("1) Display all products");
        System.out.println("2) Display all customers");
        System.out.println("0) Exit");
        System.out.println("Select an option: ");

        int choice = scanner.nextInt();

        String url = "jdbc:mysql://localhost:3306/northwind";
        String user = "root";
        String password = "yearup";


        try (Connection connection = DriverManager.getConnection(url, user, password)) {

            if (choice == 1) {
                Statement statement = connection.createStatement();

                String query = """
                        SELECT ProductID, ProductName, UnitPrice, UnitsInStock
                        FROM products""";

                ResultSet results = statement.executeQuery(query);

                while (results.next()) {
                    String productID = results.getString("ProductID");
                    String productName = results.getString("ProductName");
                    String unitPrice = results.getString("UnitPrice");
                    String unitsInStock = results.getString("UnitsInStock");
                    System.out.println("Product Id: " + productID);
                    System.out.println("Product Name: " + productName);
                    System.out.println("Product Unit Price: " + unitPrice);
                    System.out.println("Product Units in Stock: " + unitsInStock);
                }
            } else if (choice == 2) {
                Statement statement = connection.createStatement();

                String query = """
                        SELECT ContactName, CompanyName, City, Country, Phone
                        FROM customers ORDER BY Country""";

                ResultSet results = statement.executeQuery(query);

                while (results.next()) {
                    String contactName = results.getString("ContactName");
                    String companyName = results.getString("CompanyName");
                    String city = results.getString("City");
                    String country = results.getString("Country");
                    String phone = results.getString("Phone");
                    System.out.println("Contact: " + contactName);
                    System.out.println("Company: " + companyName);
                    System.out.println("City: " + city);
                    System.out.println("Country: " + country);
                    System.out.println("Country: " + phone);
                }
            } else if (choice == 0) {
                System.out.println("Goodbye!");
            }

            else {
                System.out.println("Invalid option.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }
}

