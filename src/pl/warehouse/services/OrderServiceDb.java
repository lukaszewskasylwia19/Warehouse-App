package pl.warehouse.services;

import pl.warehouse.interfaces.IOrderService;
import pl.warehouse.models.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Dostarcza implementacje serwisu IOrderService korzystajacego z bazy danych
 */
public class OrderServiceDb extends SQLDatabaseConnection implements IOrderService {
    private final Logger _logger;

    public OrderServiceDb(Logger logger) {
        _logger = logger;
    }

    @Override
    public List<Order> getAll() {
        var orderList = new ArrayList<Order>();
        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(super.connectionUrl);
             Statement statement = connection.createStatement()) {

            String selectSql = "SELECT  o.[Id]\n" +
                    "      ,c.[Name] AS 'CustomerName'\n" +
                    "      ,p.[Name] AS 'ProductName'\n" +
                    "      ,p.[Quantity] AS 'ProductQuantity'\n" +
                    "      ,p.[Price] AS 'ProductPrice'\n" +
                    "      ,[Date] AS 'OrderDate'\n" +
                    "  FROM [dbo].[Orders] o\n" +
                    "  INNER JOIN [dbo].[Customers] c ON o.CustomerId = c.id \n" +
                    "  INNER JOIN [dbo].[Products] p ON o.ProductId = p.Id";
            resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
                ResultSet finalResultSet = resultSet;
                orderList.add(new Order() {{
                    Id = finalResultSet.getInt(1);
                    CustomerName = finalResultSet.getString(2);
                    ProductName = finalResultSet.getString(3);
                    ProductQuantity = finalResultSet.getInt(4);
                    ProductPrice = finalResultSet.getInt(5);
                    OrderDate = finalResultSet.getDate(6);
                }});
            }
        } catch (SQLException e) {
            _logger.log(Level.SEVERE, "OrderServiceDb.getAll()", e);
        }

        return orderList;
    }
}
