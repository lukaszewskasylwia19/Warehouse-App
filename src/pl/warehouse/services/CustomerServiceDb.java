package pl.warehouse.services;

import pl.warehouse.interfaces.ICustomerService;
import pl.warehouse.models.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Dostarcza implementacje serwisu ICustomerService korzystajacego z bazy danych
 */
public class CustomerServiceDb extends SQLDatabaseConnection implements ICustomerService {
    private final Logger _logger;

    public CustomerServiceDb(Logger logger) {
        _logger = logger;
    }

    @Override
    public List<Customer> getAll() {
        var customerList = new ArrayList<Customer>();
        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(super.connectionUrl);
             Statement statement = connection.createStatement()) {

            String selectSql = "SELECT [Id]\n" +
                    "      ,[Name]\n" +
                    "      ,[Address]\n" +
                    "      ,[Email]\n" +
                    "      ,[NIP]\n" +
                    "  FROM [dbo].[Customers]";
            resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
                ResultSet finalResultSet = resultSet;
                customerList.add(new Customer() {{
                    Id = finalResultSet.getInt(1);
                    Name = finalResultSet.getString(2);
                    Address = finalResultSet.getString(3);
                    Email = finalResultSet.getString(4);
                    NIP = finalResultSet.getString(5);
                }});
            }
        } catch (SQLException e) {
            _logger.log(Level.SEVERE, "CustomerServiceDb.getAll()", e);
        }

        return customerList;
    }

    @Override
    public void add(Customer customer) {
        String insertSql = "INSERT INTO [dbo].[Customers] (Name, Address, Email, NIP) VALUES "
                + "('" + customer.Name + "', '" + customer.Address + "', '" + customer.Email + "', '"
                + customer.NIP + "');";

        try (Connection connection = DriverManager.getConnection(super.connectionUrl);
             PreparedStatement prepsInsertCustomer = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
            prepsInsertCustomer.execute();
        } catch (SQLException e) {
            _logger.log(Level.SEVERE, "CustomerServiceDb.add()", e);
        }
    }

    @Override
    public void update(Customer customer) {
        String updateSql = "UPDATE [dbo].[Customers]" +
                "SET Name = '" + customer.Name + "', Address = '" + customer.Address +
                "', Email = '" + customer.Email + "', NIP = '" + customer.NIP + "' WHERE id = " + customer.Id;

        try (Connection connection = DriverManager.getConnection(super.connectionUrl);
             Statement statement = connection.createStatement()) {
            statement.execute(updateSql);
        } catch (SQLException e) {
            _logger.log(Level.SEVERE, "CustomerServiceDb.update()", e);
        }
    }

    @Override
    public void delete(int id) {
        String deleteSql = "DELETE [dbo].[Customers] WHERE id = " + id;

        try (Connection connection = DriverManager.getConnection(super.connectionUrl);
             Statement statement = connection.createStatement()) {
            statement.execute(deleteSql);
        } catch (SQLException e) {
            _logger.log(Level.SEVERE, "CustomerServiceDb.delete()", e);
        }
    }
}
