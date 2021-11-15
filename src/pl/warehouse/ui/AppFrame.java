package pl.warehouse.ui;

import pl.warehouse.interfaces.ICustomerService;
import pl.warehouse.interfaces.IOrderService;
import pl.warehouse.interfaces.IProductService;
import pl.warehouse.ui.customers.CustomersPanel;
import pl.warehouse.ui.orders.OrdersPanel;
import pl.warehouse.ui.products.ProductsPanel;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.logging.Logger;

/**
 * Reprezentuje glowne okno aplikacji
 */
public class AppFrame extends JFrame {
    private ICustomerService _customerService;
    private IOrderService _orderService;
    private IProductService _productService;

    /**
     * Konstruktor okna aplikacji
     */
    public AppFrame(ICustomerService customerService, IOrderService orderService, IProductService productService) {
        _customerService = customerService;
        _orderService = orderService;
        _productService = productService;
        init();
    }

    /**
     * Inicializacja okna aplikacji ustawia m.in. rozmiar okna oraz
     * nazwe aplikacji
     */
    private void init() {
        setLayout(null);

        var backgroundIcon = new ImageIcon(this.getClass().getResource("./img/logo_warehouse.png"));
        var backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setSize(600, 100);
        backgroundLabel.setBounds(0, 0, 600, 100);

        getContentPane().add(backgroundLabel);

        JTabbedPane tabs = new JTabbedPane();
        tabs.setBounds(0, 105, 600, 480);

        var productsIcon = new ImageIcon(this.getClass().getResource("./img/products_btn.png"));
        tabs.addTab(null, productsIcon, new ProductsPanel(_productService), "Products");
        tabs.setMnemonicAt(0, KeyEvent.VK_1);
        getContentPane().add(tabs);

        var customersIcon = new ImageIcon(this.getClass().getResource("./img/customers_btn.png"));
        tabs.addTab(null, customersIcon, new CustomersPanel(_customerService), "Customers");
        tabs.setMnemonicAt(1, KeyEvent.VK_2);
        getContentPane().add(tabs);

        var ordersIcon = new ImageIcon(this.getClass().getResource("./img/orders_btn.png"));
        tabs.addTab(null, ordersIcon, new OrdersPanel(_orderService), "Orders");
        tabs.setMnemonicAt(2, KeyEvent.VK_3);
        getContentPane().add(tabs);

        pack();

        this.setSize(600, 600);
        this.setResizable(false);

        this.setVisible(true);
        this.setTitle("Warehouse App");

        //Ustawia okno na środku
        this.setLocationRelativeTo(null);
        //Kończy program po zamknięciu okna
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
