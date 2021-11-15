package pl.warehouse.models;

import javax.swing.table.AbstractTableModel;
import java.sql.Date;
import java.util.List;

/**
 * Reprezentuje model tabeli zamowien dla JTable
 */
public class OrderTableModel extends AbstractTableModel {
    /**
     * Wiersze tabeli zamowien
     */
    private List<Order> rows;

    /**
     * Kolumny tabeli zamowien
     */
    private String[] columns = new String[]{"Id", "Nazwa klienta", "Nazwa produktu", "Ilosc",
            "Cena", "Data zamowienia"};

    /**
     * Konstruktor przyjmujacy jako parametr liste zamowien
     */
    public OrderTableModel(List<Order> orders) {
        super();
        this.rows = orders;
    }

    /**
     * Zwraca wartosc ilosc wierszy
     */
    @Override
    public int getRowCount() {
        return rows.size();
    }

    /**
     * Zwraca wartosc dla wybranego indeksu wiersza (rowIndex) oraz kolumny (columnIndex)
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Order order = rows.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return order.Id;
            case 1:
                return order.CustomerName;
            case 2:
                return order.ProductName;
            case 3:
                return order.ProductQuantity;
            case 4:
                return order.ProductPrice;
            case 5:
                return order.OrderDate;
        }
        return null;
    }

    /**
     * Zwraca nazwe kolumny dla konkretnego indeksu kolumny (columnIndex)
     */
    @Override
    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }

    /**
     * Zwraca ilosc kolumn
     */
    @Override
    public int getColumnCount() {
        return columns.length;
    }

    /**
     * Zwraca typ danych dla konkretnego indeksu kolumny (columnIndex)
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                return String.class;
            case 5:
                return Date.class;
        }
        return null;
    }
}
