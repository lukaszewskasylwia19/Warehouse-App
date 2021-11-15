package pl.warehouse.ui.customers;

import pl.warehouse.interfaces.ICustomerService;
import pl.warehouse.models.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Reprezentuje panel klienta (tabla z klientami / formularz klienta)
 */
public class CustomersPanel extends JPanel {
    /**
     * Serwis klientow
     */
    private final ICustomerService _customerService;

    /**
     * Panel tablicy klientow
     */
    private CustomersTable _customersTable;

    /**
     * Panel formularza klienta
     */
    private CustomerForm _customerForm;
    /**
     * Słuzy do przechwytywania zdarzen wystepujacych w panelu tj:
     * addCustomerButton - zdarzenie wywolywane w przypadku klikniecia przycisku "Add customer",
     * saveButton - zdarzenie wywolywane w przypadku klikniecia przycisku "Save" w formularzu klienta,
     * cancelButton - zdarzenie wywolywane w przypadku klikniecia przycisku "Cancel" w formularzu klienta,
     * removeButtonEditor - zdarzenie wywolywane w przypadku klikniecia przycisku "Delete" w wybranym wierszu tabeli,
     * editButtonEditor - zdarzenie wywolywane w przypadku klikniecia przycisku "Edit" w wybranym wierszu tabeli.
     */
    ActionListener clickHandler = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object actionSource = e.getSource();
            if (actionSource == _customersTable.addCustomerButton) {
                _customersTable.setVisible(false);
                _customerForm.setVisible(true);
            } else if (actionSource == _customerForm.saveButton) {
                if (_customerForm.hasValidData()) {
                    var customer = _customerForm.getCustomerForm();

                    if (customer.Id == null) {
                        _customerService.add(customer);
                    } else {
                        _customerService.update(customer);
                    }

                    _customersTable.refreshTable();
                    _customerForm.clearForm();
                    _customerForm.setVisible(false);
                    _customersTable.setVisible(true);
                }
            } else if (actionSource == _customerForm.cancelButton) {
                _customerForm.clearForm();
                _customerForm.setVisible(false);
                _customersTable.setVisible(true);
            } else if (actionSource == _customersTable.removeButtonEditor.button) {
                var customers = _customerService.getAll();
                var customer = customers.get(_customersTable.removeButtonEditor.selectedRow);
                _customerService.delete(customer.Id);
                _customersTable.refreshTable();
            } else if (actionSource == _customersTable.editButtonEditor.button) {
                var customers = _customerService.getAll();
                var customer = customers.get(_customersTable.editButtonEditor.selectedRow);
                _customerForm.setCustomerForm(customer);
                _customerForm.setVisible(true);
                _customersTable.setVisible(false);
            }
        }
    };

    /**
     * Konstruktor panelu klienta
     */
    public CustomersPanel(ICustomerService customerService) {
        _customerService = customerService;
        init();
    }

    /**
     * Słuzy do zainicjowania panelu klientow.
     * Domyslnie poczatkowo wyswitlona zostaje tabela z klientami.
     */
    protected void init() {
        setLayout(null);

        _customersTable = new CustomersTable(_customerService);
        _customersTable.setBounds(10, 10, 570, 400);

        _customerForm = new CustomerForm();
        _customerForm.setBounds(10, 10, 570, 400);
        _customerForm.setVisible(false);

        this.setBackground(Color.white);

        _customersTable.addCustomerButton.addActionListener(clickHandler);
        _customersTable.removeButtonEditor.button.addActionListener(clickHandler);
        _customersTable.editButtonEditor.button.addActionListener(clickHandler);
        _customerForm.saveButton.addActionListener(clickHandler);
        _customerForm.cancelButton.addActionListener(clickHandler);

        this.add(_customersTable);
        this.add(_customerForm);
    }
}
