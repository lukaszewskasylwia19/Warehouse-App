package pl.warehouse.interfaces;

import pl.warehouse.models.Order;

import java.util.List;

/**
 * Dostarcza opis czynnosci wykonywanych na Zamowieniu
 */
public interface IOrderService {
    /**
     * Zwraca liste wszystkich zamowien
     */
    public List<Order> getAll();
}
