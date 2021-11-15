package pl.warehouse.services;

/**
 * Dostarcza danych dotyczacych bazy danych
 */
public class SQLDatabaseConnection {
    /**
     * Ciag polaczenia do bazy danych
     */
    protected String connectionUrl = "jdbc:sqlserver://LAPTOP-LRGC5F0O;"
            + "database=Warehouse App;"
            + "user=admin;"
            + "password=Password1;"
            + "trustServerCertificate=false;"
            + "loginTimeout=10;";
}
