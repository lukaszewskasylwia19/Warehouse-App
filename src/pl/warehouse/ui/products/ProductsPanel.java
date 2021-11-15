package pl.warehouse.ui.products;

import pl.warehouse.interfaces.IProductService;
import pl.warehouse.models.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Reprezentuje panel produktu (tabla z produktami / formularz produktu)
 */
public class ProductsPanel extends JPanel {
    /**
     * Serwis produktow
     */
    private IProductService _productService;

    /**
     * Panel tablicy produktow
     */
    private ProductsTable _productsTable;

    /**
     * Panel formularza produktu
     */
    private ProductForm _productForm;

    /**
     * Konstruktor panelu produktu
     */
    public ProductsPanel(IProductService productService) {
        _productService = productService;
        init();
    }

    /**
     * Słuzy do zainicjowania panelu produktow.
     * Domyslnie poczatkowo wyswitlona zostaje tabela z produktami.
     */
    protected void init() {
        setLayout(null);

        _productsTable = new ProductsTable(_productService);
        _productsTable.setBounds(10, 10, 570, 400);

        _productForm = new ProductForm();
        _productForm.setBounds(10, 10, 570, 400);
        _productForm.setVisible(false);

        this.setBackground(Color.white);

        _productsTable.addProductButton.addActionListener(clickHandler);
        _productsTable.removeButtonEditor.button.addActionListener(clickHandler);
        _productsTable.editButtonEditor.button.addActionListener(clickHandler);
        _productForm.saveButton.addActionListener(clickHandler);
        _productForm.cancelButton.addActionListener(clickHandler);

        this.add(_productsTable);
        this.add(_productForm);
    }

    /**
     * Słuzy do przechwytywania zdarzen wystepujacych w panelu tj:
     * addProductButton - zdarzenie wywolywane w przypadku klikniecia przycisku "Add product",
     * saveButton - zdarzenie wywolywane w przypadku klikniecia przycisku "Save" w formularzu produktu,
     * cancelButton - zdarzenie wywolywane w przypadku klikniecia przycisku "Cancel" w formularzu produktu,
     * removeButtonEditor - zdarzenie wywolywane w przypadku klikniecia przycisku "Delete" w wybranym wierszu tabeli,
     * editButtonEditor - zdarzenie wywolywane w przypadku klikniecia przycisku "Edit" w wybranym wierszu tabeli.
     */
    ActionListener clickHandler = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object actionSource = e.getSource();
            if (actionSource == _productsTable.addProductButton) {
                _productsTable.setVisible(false);
                _productForm.setVisible(true);
            } else if (actionSource == _productForm.saveButton) {
                if (_productForm.hasValidData()) {
                    var product = _productForm.getProductForm();

                    if(product.Id == null){
                        _productService.add(product);
                    }
                    else{
                        _productService.update(product);
                    }

                    _productsTable.refreshTable();
                    _productForm.clearForm();
                    _productForm.setVisible(false);
                    _productsTable.setVisible(true);
                }
            } else if (actionSource == _productForm.cancelButton) {
                _productForm.clearForm();
                _productForm.setVisible(false);
                _productsTable.setVisible(true);
            } else if (actionSource == _productsTable.removeButtonEditor.button) {
                var products= _productService.getAll();
                var product = products.get(_productsTable.removeButtonEditor.selectedRow);
                _productService.delete(product.Id);
                _productsTable.refreshTable();
            } else if (actionSource == _productsTable.editButtonEditor.button) {
                var products= _productService.getAll();
                var product = products.get(_productsTable.editButtonEditor.selectedRow);
                _productForm.setProductForm((Product)product);
                _productForm.setVisible(true);
                _productsTable.setVisible(false);
            }
        }
    };
}
