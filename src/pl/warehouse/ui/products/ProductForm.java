package pl.warehouse.ui.products;

import pl.warehouse.common.Colors;
import pl.warehouse.models.Product;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Reprezentuje panel formularza produktu (dodawanie/edycja)
 */
public class ProductForm extends JPanel {

    /**
     * Lewy magines
     */
    private int _marginLeft = 100;

    /**
     * Identyfikator produktu
     */
    private Integer _productId = null;

    /**
     * Etykieta wystepujaca w formularzu produktu
     */
    private JLabel _headerLabel, _productNameLabel, _quantityLabel,
            _priceLabel, _categoryLabel, _detailsLabel, _errorMessage;

    /**
     * Pole tekstowe wystepujace w formularzu produktu
     */
    private JTextField _productNameField, _quantityField, _priceField, _categoryField;

    /**
     * Pole tekstowe wystepujace w formularzu produktu
     */
    private JTextArea _detailsField;

    /**
     * Przycisk wystepujacy w formularzu produktu
     */
    public JButton saveButton, cancelButton;

    /**
     * Konstruktor formularza produktu
     */
    public ProductForm() {
        init();
    }

    /**
     * Sluzy do zainicjowania elementow formularza
     */
    protected void init() {
        setLayout(null);

        _headerLabel = new JLabel("Product form");
        _headerLabel.setBounds(200, 10, 300, 30);
        _headerLabel.setFont(new Font("Verdana", Font.PLAIN, 25));
        this.add(_headerLabel);

        _productNameLabel = new JLabel("Name");
        _productNameLabel.setBounds(_marginLeft, 60, 150, 20);
        this.add(_productNameLabel);

        _productNameField = new JTextField("");
        _productNameField.setBounds(_marginLeft + 150, 60, 250, 20);
        this.add(_productNameField);

        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
        DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
        decimalFormat.setGroupingUsed(false);

        _quantityLabel = new JLabel("Quantity");
        _quantityLabel.setBounds(_marginLeft, 90, 150, 20);
        this.add(_quantityLabel);

        _quantityField = new JFormattedTextField(decimalFormat);
        _quantityField.setBounds(_marginLeft + 150, 90, 250, 20);
        this.add(_quantityField);

        _priceLabel = new JLabel("Price");
        _priceLabel.setBounds(_marginLeft, 120, 150, 20);
        this.add(_priceLabel);

        _priceField = new JFormattedTextField(decimalFormat);
        _priceField.setBounds(_marginLeft + 150, 120, 250, 20);
        this.add(_priceField);

        _categoryLabel = new JLabel("Category");
        _categoryLabel.setBounds(_marginLeft, 150, 150, 20);
        this.add(_categoryLabel);

        _categoryField = new JTextField("");
        _categoryField.setBounds(_marginLeft + 150, 150, 250, 20);
        this.add(_categoryField);

        _detailsLabel = new JLabel("Details");
        _detailsLabel.setBounds(_marginLeft, 180, 150, 20);
        this.add(_detailsLabel);

        _detailsField = new JTextArea("");
        _detailsField.setBounds(_marginLeft + 150, 180, 250, 80);
        this.add(_detailsField);

        _errorMessage = new JLabel("Wprowadzono nieprawidłowe dane do fomularza");
        _errorMessage.setVisible(false);
        _errorMessage.setBounds(_marginLeft, 300, 400, 20);
        _errorMessage.setForeground(Colors.danger);
        this.add(_errorMessage);

        var saveIcon = new ImageIcon(this.getClass().getResource(".././img/save_btn.png"));
        saveButton = new JButton(saveIcon);
        saveButton.setBounds(_marginLeft + 80, 340, 92, 32);
        this.add(saveButton);

        var cancelIcon = new ImageIcon(this.getClass().getResource(".././img/cancel_btn.png"));
        cancelButton = new JButton(cancelIcon);
        cancelButton.setBounds(_marginLeft + 180, 340, 114, 32);
        this.add(cancelButton);
    }

    /**
     * Sluzy do pobrania danych produktu wprowadzonych do formularza
     */
    public Product getProductForm() {
        Product product = new Product() {{
            Id = _productId;
            Name = _productNameField.getText();
            Quantity = Integer.parseInt(_quantityField.getText());
            Price = Integer.parseInt(_priceField.getText());
            Category = _categoryField.getText();
            Details = _detailsField.getText();
        }};

        return product;
    }

    /**
     * Sluzy do ustawienia pol formularza na podstawie modelu Product (wykorzystywane do edycji produktu)
     */
    public void setProductForm(Product product) {
        if (product == null) return;

        _productId = product.Id;
        _productNameField.setText(product.Name);
        _quantityField.setText(product.Quantity.toString());
        _priceField.setText(product.Price.toString());
        _categoryField.setText(product.Category);
        _detailsField.setText(product.Details);
    }

    /**
     * Sluzy do wyczyszczenia pol formularza
     */
    public void clearForm() {
        _productId = null;
        _productNameField.setText("");
        _quantityField.setText("");
        _priceField.setText("");
        _categoryField.setText("");
        _detailsField.setText("");

        clearMarkedFields();
    }

    /**
     * Sluzy do sprawdzenia poprawnosci wprowadzonych danych do formularza.
     * Pola z nieprawidlowymi danymi zostaja zaznaczane kolorem czerwonym.
     */
    public boolean hasValidData() {
        var isProductNameFieldValid = validField(_productNameField);
        var isQuantityFieldValid = validField(_quantityField);
        var isPriceFieldValid = validField(_priceField);
        var isCategoryFieldValid = validField(_categoryField);
        var isDetailsFieldValid = validField(_detailsField);

        var isFormValid = isProductNameFieldValid && isQuantityFieldValid && isPriceFieldValid
                && isCategoryFieldValid && isDetailsFieldValid;

        if (isFormValid) {
            _errorMessage.setVisible(false);
        } else {
            _errorMessage.setVisible(true);
        }

        return isFormValid;
    }

    /**
     * Sluzy do sprawdzenia poprawności danych w wybranym polu field).
     * W przypadku nieprawidlowych danych, pole zostaje zaznaczone kolorem czewonym.
     */
    private boolean validField(JTextComponent field) {
        if (field.getText().isEmpty()) {
            field.setBackground(Color.pink);
            return false;
        }

        field.setBackground(Color.white);
        return true;
    }

    /**
     * Sluzy do wyczyszczenia zaznaczonych pol na czerwono
     */
    private void clearMarkedFields() {
        _productNameField.setBackground(Color.white);
        _quantityField.setBackground(Color.white);
        _priceField.setBackground(Color.white);
        _categoryField.setBackground(Color.white);
        _detailsField.setBackground(Color.white);

        _errorMessage.setVisible(false);
    }
}