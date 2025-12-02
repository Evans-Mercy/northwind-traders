import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("What do you want to do?");
        System.out.println("1) Display all products");
        System.out.println("2) Display all customers");
        System.out.println("3) Display all categories");
        System.out.println("0) Exit");
        System.out.println("Select an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        String url = "jdbc:mysql://localhost:3306/northwind";
        String user = "root";
        String password = "yearup";


        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setUrl("jdbc:mysql://localhost:3306/northwind");
        dataSource.setUsername("root");
        dataSource.setPassword("yearup");
        try (
                //Connection connection = DriverManager.getConnection(url, user, password)) {
                Connection connection = dataSource.getConnection()) {

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
            }
            else if (choice == 3){
                Statement statement = connection.createStatement();
                String query = "SELECT categoryID, categoryName FROM categories ORDER BY categoryID";

                ResultSet results = statement.executeQuery(query);

                while (results.next()){
                    String categoryID = results.getString("categoryID");
                    String categoryName = results.getString("CategoryName");

                    System.out.println("Category ID: " + categoryID);
                    System.out.println("Category Name: " + categoryName);
                }

                System.out.println("Select Category ID: ");
                String input = scanner.nextLine();

                query = "SELECT ProductID, ProductName, UnitPrice, UnitsInStock FROM products WHERE categoryID = ?";

                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, input);
                ResultSet filteredList = preparedStatement.executeQuery();


                while (filteredList.next()) {
                    String productID = filteredList.getString("ProductID");
                    String productName = filteredList.getString("ProductName");
                    String unitPrice = filteredList.getString("UnitPrice");
                    String unitsInStock = filteredList.getString("UnitsInStock");
                    System.out.println("Product Id: " + productID);
                    System.out.println("Product Name: " + productName);
                    System.out.println("Product Unit Price: " + unitPrice);
                    System.out.println("Product Units in Stock: " + unitsInStock);
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

