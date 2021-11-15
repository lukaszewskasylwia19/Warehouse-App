package pl.warehouse.interfaces;

import pl.warehouse.models.Product;

import java.util.List;

/**
 * Dostarcza opis czynnosci wykonywanych na Produkcie
 */
public interface IProductService {
    /**
     * Zwraca liste wszystkich produktow
     */
    public List<Product> getAll();

    /**
     * Dodaje produkt do listy
     */
    public void add(Product product);

    /**
     * Aktualizuje produkt w liscie
     */
    public void update(Product product);

    /**
     * Usuwa produkt z listy
     */
    public void delete(int id);
}
