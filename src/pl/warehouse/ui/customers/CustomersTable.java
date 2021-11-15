package pl.warehouse.ui.customers;

import pl.warehouse.common.Colors;
import pl.warehouse.interfaces.ICustomerService;
import pl.warehouse.models.CustomerTableModel;
import pl.warehouse.ui.ButtonEditor;
import pl.warehouse.ui.ButtonRenderer;

import javax.swing.*;
import java.awt.*;

/**
 * Reprezentuje panel listy prduktow
 */
public class CustomersTable extends JPanel {
    /**
     * Przycisk wystepujacy w panelu listy klientow
     */
    public JButton addCustomerButton;
    /**
     * Przycisk wystepujacy w tablicy panelu listy klientow
     */
    public ButtonEditor removeButtonEditor, editButtonEditor;
    /**
     * Serwis klientow
     */
    private final ICustomerService _customerService;
    /**
     * Etykieta wystepujaca w panelu listy klientow
     */
    private JLabel _headerLabel;

    /**
     * Tablica wystepujaca w panelu listy klientow
     */
    private JTable _customersTable;

    /**
     * Okno przewijania wystepujace w panelu listy klientow
     */
    private JScrollPane _scrollPane;

    /**
     * Konstruktor tablicy klientow
     */
    public CustomersTable(ICustomerService customerService) {
        _customerService = customerService;
        init();
    }

    /**
     * Słuzy do zainicjowania panelu listy klientow.
     */
    protected void init() {
        setLayout(null);

        _headerLabel = new JLabel("Customers");
        _headerLabel.setBounds(220, 10, 200, 30);
        _headerLabel.setFont(new Font("Verdana", Font.PLAIN, 25));
        this.add(_headerLabel);

        var addIcon = new ImageIcon(this.getClass().getResource(".././img/add_btn.png"));
        addCustomerButton = new JButton(addIcon);
        addCustomerButton.setBounds(35, 50, 91, 32);
        this.add(addCustomerButton);

        removeButtonEditor = new ButtonEditor();
        editButtonEditor = new ButtonEditor();

        _customersTable = new JTable();
        refreshTable();
        _scrollPane = new JScrollPane(_customersTable);
        _scrollPane.setBounds(35, 100, 500, 200);
        this.add(_scrollPane);
    }

    /**
     * Słuzy do ponownego zaladownia listy klientow do tablicy.
     */
    public void refreshTable() {
        var customers = _customerService.getAll();
        _customersTable.setModel(new CustomerTableModel(customers));
        _customersTable.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer("Delete", Colors.danger));
        _customersTable.getColumnModel().getColumn(5).setCellEditor(removeButtonEditor);
        _customersTable.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer("Edit", Colors.success));
        _customersTable.getColumnModel().getColumn(6).setCellEditor(editButtonEditor);
        _customersTable.setRowHeight(25);
    }
}