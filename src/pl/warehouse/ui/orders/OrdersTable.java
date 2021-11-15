package pl.warehouse.ui.orders;

import pl.warehouse.interfaces.IOrderService;
import pl.warehouse.models.OrderTableModel;

import javax.swing.*;
import java.awt.*;

/**
 * Reprezentuje panel listy prduktow
 */
public class OrdersTable extends JPanel {
    /**
     * Przycisk wystepujacy w panelu listy zamowien
     */
    public JButton refreshOrdersButton;

    /**
     * Serwis zamowien
     */
    private IOrderService _ordersService;

    /**
     * Etykieta wystepujaca w panelu listy zamowien
     */
    private JLabel _headerLabel;

    /**
     * Tablica wystepujaca w panelu listy zamowien
     */
    private JTable _customersTable;

    /**
     * Okno przewijania wystepujace w panelu listy zamowien
     */
    private JScrollPane _scrollPane;

    /**
     * Konstruktor tablicy klientow
     */
    public OrdersTable(IOrderService ordersService) {
        _ordersService = ordersService;
        init();
    }

    /**
     * Słuzy do zainicjowania panelu listy zamowien.
     */
    protected void init() {
        setLayout(null);

        _headerLabel = new JLabel("Orders");
        _headerLabel.setBounds(220, 10, 200, 30);
        _headerLabel.setFont(new Font("Verdana", Font.PLAIN, 25));
        this.add(_headerLabel);

        var addIcon = new ImageIcon(this.getClass().getResource(".././img/refresh_btn.png"));
        refreshOrdersButton = new JButton(addIcon);
        refreshOrdersButton.setBounds(35, 50, 112, 32);
        this.add(refreshOrdersButton);

        _customersTable = new JTable();
        refreshTable();
        _scrollPane = new JScrollPane(_customersTable);
        _scrollPane.setBounds(35, 100, 500, 200);
        this.add(_scrollPane);
    }

    /**
     * Słuzy do ponownego zaladownia listy zamowien do tablicy.
     */
    public void refreshTable() {
        var orders = _ordersService.getAll();
        _customersTable.setModel(new OrderTableModel(orders));
        _customersTable.setRowHeight(25);
    }
}