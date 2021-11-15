package pl.warehouse.models;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Reprezentuje model tabeli produktow dla JTable
 */
public class ProductTableModel extends AbstractTableModel {
    /**
     * Wiersze tabeli produktow
     */
    private List<Product> rows;

    /**
     * Kolumny tabeli produktow
     */
    private String[] columns =  new String[]{"Id", "Nazwa produktu", "Ilosc", "Cena",
            "Kategoria", "Szczegóły produktu", "Usuń", "Edytuj" };

    /**
     * Konstruktor przyjmujacy jako parametr liste produktow
     */
    public ProductTableModel(List<Product> products)  {
        super();
        this.rows = products;
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
        Product product = rows.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return product.Id;
            case 1:
                return product.Name;
            case 2:
                return product.Quantity;
            case 3:
                return product.Price;
            case 4:
                return product.Category;
            case 5:
                return product.Details;
            case 6:
            case 7:
                return "";
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
            case 2:
            case 3:
            case 1:
            case 4:
            case 5:
                return String.class;
            case 6:
            case 7:
                return JButton.class;
        }
        return null;
    }

    /**
     * Zwraca wartosc true dla kolumny "Delete" oraz "Edit"
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return (columnIndex == 6 || columnIndex == 7);
    }
}
