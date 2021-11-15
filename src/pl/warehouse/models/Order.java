package pl.warehouse.models;

import java.util.Date;

/**
 * Reprezentuje zamowienie znajdujace sie w bazie
 */
public class Order {
    /**
     * Id zamowienia
     */
    public Integer Id;

    /**
     * Nazwa klienta
     */
    public String CustomerName;

    /**
     * Nazwa produktu
     */
    public String ProductName;

    /**
     * Ilosc produktu
     */
    public Integer ProductQuantity;

    /**
     * Cena produktu
     */
    public Integer ProductPrice;

    /**
     * Data zamowienia
     */
    public Date OrderDate;

}
