package pl.warehouse.ui.products;

import pl.warehouse.common.Colors;
import pl.warehouse.interfaces.IProductService;
import pl.warehouse.models.ProductTableModel;
import pl.warehouse.ui.ButtonEditor;
import pl.warehouse.ui.ButtonRenderer;

import javax.swing.*;
import java.awt.*;

/**
 * Reprezentuje panel listy prduktow
 */
public class ProductsTable extends JPanel {
    /**
     * Serwis produktow
     */
    private IProductService _productService;

    /**
     * Przycisk wystepujacy w panelu listy prduktow
     */
    public JButton addProductButton;

    /**
     * Przycisk wystepujacy w tablicy panelu listy prduktow
     */
    public ButtonEditor removeButtonEditor, editButtonEditor;

    /**
     * Etykieta wystepujaca w panelu listy prduktow
     */
    private JLabel _headerLabel;

    /**
     * Tablica wystepujaca w panelu listy prduktow
     */
    private JTable _productsTable;

    /**
     * Okno przewijania wystepujace w panelu listy prduktow
     */
    private JScrollPane _scrollPane;

    /**
     * Konstruktor tablicy produktu
     */
    public ProductsTable(IProductService productService) {
        _productService = productService;
        init();
    }

    /**
     * Słuzy do zainicjowania panelu listy prduktow.
     */
    protected void init(){
        setLayout(null);

        _headerLabel = new JLabel("Products");
        _headerLabel.setBounds(220, 10, 200, 30);
        _headerLabel.setFont(new Font("Verdana", Font.PLAIN, 25));
        this.add(_headerLabel);

        var addIcon = new ImageIcon(this.getClass().getResource(".././img/add_btn.png"));
        addProductButton = new JButton(addIcon);
        addProductButton.setBounds(35, 50, 91, 32);
        this.add(addProductButton);

        removeButtonEditor = new ButtonEditor();
        editButtonEditor = new ButtonEditor();

        _productsTable = new JTable();
        refreshTable();
        _scrollPane = new JScrollPane(_productsTable);
        _scrollPane.setBounds(35, 100, 500, 200);
        this.add(_scrollPane);
    }

    /**
     * Słuzy do ponownego zaladownia listy produktow do tablicy.
     */
    public void refreshTable() {
        var products = _productService.getAll();
        _productsTable.setModel(new ProductTableModel(products));
        _productsTable.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer("Delete", Colors.danger));
        _productsTable.getColumnModel().getColumn(6).setCellEditor(removeButtonEditor);
        _productsTable.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer("Edit", Colors.success));
        _productsTable.getColumnModel().getColumn(7).setCellEditor(editButtonEditor);
        _productsTable.setRowHeight(25);
    }
}