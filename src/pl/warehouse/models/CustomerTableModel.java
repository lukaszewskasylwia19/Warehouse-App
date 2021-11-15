package pl.warehouse.models;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Reprezentuje model tabeli klientow dla JTable
 */
public class CustomerTableModel extends AbstractTableModel {
    /**
     * Wiersze tabeli klientow
     */
    private List<Customer> rows;

    /**
     * Kolumny tabeli klientow
     */
    private String[] columns =  new String[]{"Id", "Nazwa klienta", "Adres", "Email",
            "NIP", "Usuń", "Edytuj" };

    /**
     * Konstruktor przyjmujacy jako parametr liste klientow
     */
    public CustomerTableModel(List<Customer> customers)  {
        super();
        this.rows = customers;
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
        Customer customer = rows.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return customer.Id;
            case 1:
                return customer.Name;
            case 2:
                return customer.Address;
            case 3:
                return customer.Email;
            case 4:
                return customer.NIP;
            case 5:
            case 6:
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
            case 1:
            case 2:
            case 3:
            case 4:
                return String.class;
            case 5:
            case 6:
                return JButton.class;
        }
        return null;
    }

    /**
     * Zwraca wartosc true dla kolumny "Delete" oraz "Edit"
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return (columnIndex == 5 || columnIndex == 6);
    }
}
