package pl.warehouse.services;

import pl.warehouse.interfaces.IProductService;
import pl.warehouse.models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Dostarcza implementacje serwisu IProductService korzystajacego z bazy danych
 */
public class ProductServiceDb extends SQLDatabaseConnection implements IProductService {
    private Logger _logger;

    public ProductServiceDb(Logger logger){
        _logger = logger;
    }

    @Override
    public List<Product> getAll() {
        var productList = new ArrayList<Product>();
        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(super.connectionUrl);
             Statement statement = connection.createStatement();) {

            String selectSql = "SELECT [Id]\n" +
                    "      ,[Name]\n" +
                    "      ,[Quantity]\n" +
                    "      ,[Price]\n" +
                    "      ,[Category]\n" +
                    "      ,[Details]\n" +
                    "  FROM [dbo].[Products]";
            resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
                ResultSet finalResultSet = resultSet;
                productList.add(new Product() {{
                    Id = finalResultSet.getInt(1);
                    Name = finalResultSet.getString(2);
                    Quantity = finalResultSet.getInt(3);
                    Price = finalResultSet.getInt(4);
                    Category = finalResultSet.getString(5);
                    Details = finalResultSet.getString(6);
                }});
            }
        } catch (SQLException e) {
            _logger.log(Level.SEVERE, "ProductServiceDb.getAll()", e);
        }

        return productList;
    }

    @Override
    public void add(Product product) {
        String insertSql = "INSERT INTO [dbo].[Products] (Name, Quantity, Price, Category, Details) VALUES "
                + "('" + product.Name + "', '" + product.Quantity + "', '" + product.Price + "', '"
                + product.Category + "', '" + product.Details + "');";

        try (Connection connection = DriverManager.getConnection(super.connectionUrl);
             PreparedStatement prepsInsertProduct = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);) {
            prepsInsertProduct.execute();
        } catch (SQLException e) {
            _logger.log(Level.SEVERE, "ProductServiceDb.add()", e);
        }
    }

    @Override
    public void update(Product product) {
        String updateSql = "UPDATE [dbo].[Products]" +
                "SET Name = '" + product.Name + "', Quantity = '" + product.Quantity +
                "', Price = '" + product.Price + "', Category = '" + product.Category +
                "', Details = '" + product.Details + "' WHERE id = " + product.Id;

        try (Connection connection = DriverManager.getConnection(super.connectionUrl);
             Statement statement = connection.createStatement();) {
            statement.execute(updateSql);
        } catch (SQLException e) {
            _logger.log(Level.SEVERE, "ProductServiceDb.update()", e);
        }
    }

    @Override
    public void delete(int id) {
        String deleteSql = "DELETE [dbo].[Products] WHERE id = " + id;

        try (Connection connection = DriverManager.getConnection(super.connectionUrl);
             Statement statement = connection.createStatement();) {
            statement.execute(deleteSql);
        } catch (SQLException e) {
            _logger.log(Level.SEVERE, "ProductServiceDb.delete()", e);
        }
    }
}
