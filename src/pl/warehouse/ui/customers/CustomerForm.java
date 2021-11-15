package pl.warehouse.ui.customers;

import pl.warehouse.common.Colors;
import pl.warehouse.models.Customer;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;

/**
 * Reprezentuje panel formularza klienta (dodawanie/edycja)
 */
public class CustomerForm extends JPanel {

    /**
     * Lewy magines
     */
    private int _marginLeft = 100;

    /**
     * Identyfikator klienta
     */
    private Integer _customerId = null;

    /**
     * Etykieta wystepujaca w formularzu klienta
     */
    private JLabel _headerLabel, _customerNameLabel, _addressLabel,
            _emailLabel, _nipLabel, _errorMessage;

    /**
     * Pole tekstowe wystepujace w formularzu klienta
     */
    private JTextField _customerNameField, _addressField, _emailField, _nipField;

    /**
     * Przycisk wystepujacy w formularzu klienta
     */
    public JButton saveButton, cancelButton;

    /**
     * Konstruktor formularza klienta
     */
    public CustomerForm() {
        init();
    }

    /**
     * Sluzy do zainicjowania elementow formularza
     */
    protected void init() {
        setLayout(null);

        _headerLabel = new JLabel("Customer form");
        _headerLabel.setBounds(200, 10, 300, 30);
        _headerLabel.setFont(new Font("Verdana", Font.PLAIN, 25));
        this.add(_headerLabel);

        _customerNameLabel = new JLabel("Name");
        _customerNameLabel.setBounds(_marginLeft, 60, 150, 20);
        this.add(_customerNameLabel);

        _customerNameField = new JTextField("");
        _customerNameField.setBounds(_marginLeft + 150, 60, 250, 20);
        this.add(_customerNameField);

        _addressLabel = new JLabel("Address");
        _addressLabel.setBounds(_marginLeft, 90, 150, 20);
        this.add(_addressLabel);

        _addressField = new JTextField("");
        _addressField.setBounds(_marginLeft + 150, 90, 250, 20);
        this.add(_addressField);

        _emailLabel = new JLabel("Email");
        _emailLabel.setBounds(_marginLeft, 120, 150, 20);
        this.add(_emailLabel);

        _emailField = new JTextField("");
        _emailField.setBounds(_marginLeft + 150, 120, 250, 20);
        this.add(_emailField);

        _nipLabel = new JLabel("NIP");
        _nipLabel.setBounds(_marginLeft, 150, 150, 20);
        this.add(_nipLabel);

        _nipField = new JTextField("");
        _nipField.setBounds(_marginLeft + 150, 150, 250, 20);
        this.add(_nipField);

        _errorMessage = new JLabel("Wprowadzono nieprawidłowe dane do fomularza");
        _errorMessage.setVisible(false);
        _errorMessage.setBounds(_marginLeft, 270, 400, 20);
        _errorMessage.setForeground(Colors.danger);
        this.add(_errorMessage);

        var saveIcon = new ImageIcon(this.getClass().getResource(".././img/save_btn.png"));
        saveButton = new JButton(saveIcon);
        saveButton.setBounds(_marginLeft + 80, 310, 92, 32);
        this.add(saveButton);

        var cancelIcon = new ImageIcon(this.getClass().getResource(".././img/cancel_btn.png"));
        cancelButton = new JButton(cancelIcon);
        cancelButton.setBounds(_marginLeft + 180, 310, 114, 32);
        this.add(cancelButton);
    }

    /**
     * Sluzy do pobrania danych klienta wprowadzonych do formularza
     */
    public Customer getCustomerForm() {
        Customer customer = new Customer() {{
            Id = _customerId;
            Name = _customerNameField.getText();
            Address = _addressField.getText();
            Email = _emailField.getText();
            NIP = _nipField.getText();
        }};

        return customer;
    }

    /**
     * Sluzy do ustawienia pol formularza na podstawie modelu Customer (wykorzystywane do edycji klientow)
     */
    public void setCustomerForm(Customer customer) {
        if (customer == null) return;

        _customerId = customer.Id;
        _customerNameField.setText(customer.Name);
        _addressField.setText(customer.Address);
        _emailField.setText(customer.Email);
        _nipField.setText(customer.NIP);
    }

    /**
     * Sluzy do wyczyszczenia pol formularza
     */
    public void clearForm() {
        _customerId = null;
        _customerNameField.setText("");
        _addressField.setText("");
        _emailField.setText("");
        _nipField.setText("");

        clearMarkedFields();
    }

    /**
     * Sluzy do sprawdzenia poprawnosci wprowadzonych danych do formularza.
     * Pola z nieprawidlowymi danymi zostaja zaznaczane kolorem czerwonym.
     */
    public boolean hasValidData() {
        var isCustomerNameFieldValid = validField(_customerNameField);
        var isAddressFieldValid = validField(_addressField);
        var isEmailFieldValid = validField(_emailField);
        var isNIPFieldValid = validField(_nipField);

        var isFormValid = isCustomerNameFieldValid && isAddressFieldValid && isEmailFieldValid
                && isNIPFieldValid;

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
        _customerNameField.setBackground(Color.white);
        _addressField.setBackground(Color.white);
        _emailField.setBackground(Color.white);
        _nipField.setBackground(Color.white);

        _errorMessage.setVisible(false);
    }
}