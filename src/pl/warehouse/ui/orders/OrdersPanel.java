package pl.warehouse.ui.orders;

import pl.warehouse.interfaces.IOrderService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Reprezentuje panel zamowien
 */
public class OrdersPanel extends JPanel {
    /**
     * Serwis zamowien
     */
    private final IOrderService _orderService;

    /**
     * Panel tablicy zamowien
     */
    private OrdersTable _ordersTable;

    /**
     * Słuzy do przechwytywania zdarzen wystepujacych w panelu tj:
     * refreshOrdersButton - zdarzenie wywolywane w przypadku klikniecia przycisku "Refresh orders",
     */
    ActionListener clickHandler = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object actionSource = e.getSource();
            if (actionSource == _ordersTable.refreshOrdersButton) {
                _ordersTable.refreshTable();
            }
        }
    };

    /**
     * Konstruktor panelu zamowien
     */
    public OrdersPanel(IOrderService orderService) {
        _orderService = orderService;
        init();
    }

    /**
     * Słuzy do zainicjowania panelu zamowien.
     */
    protected void init() {
        setLayout(null);

        _ordersTable = new OrdersTable(_orderService);
        _ordersTable.setBounds(10, 10, 570, 400);

        this.setBackground(Color.white);

        _ordersTable.refreshOrdersButton.addActionListener(clickHandler);

        this.add(_ordersTable);
    }
}
