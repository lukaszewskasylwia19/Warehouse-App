package pl.warehouse.interfaces;

import pl.warehouse.models.Customer;
import pl.warehouse.models.Product;

import java.util.List;

/**
 * Dostarcza opis czynnosci wykonywanych na Kliencie
 */
public interface ICustomerService {
    /**
     * Zwraca liste wszystkich klientow
     */
    public List<Customer> getAll();

    /**
     * Dodaje klienta do listy
     */
    public void add(Customer customer);

    /**
     * Aktualizuje klienta w liscie
     */
    public void update(Customer customer);

    /**
     * Usuwa klienta z listy
     */
    public void delete(int id);
}
