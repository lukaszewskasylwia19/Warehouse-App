package pl.warehouse.ui;

import pl.warehouse.interfaces.ICustomerService;
import pl.warehouse.interfaces.IOrderService;
import pl.warehouse.interfaces.IProductService;
import pl.warehouse.services.*;

import javax.swing.*;
import java.util.logging.Logger;

/**
 * Reprezentuje glowna klase aplikacji
 */
public class Main {
    /**
     * Glowna metoda aplikacji.
     * Przyjmuje opcjonalny parametr "Database" ktory powoduje
     * ze aplikacja korzysta z bazy danych.
     * Domyslnie aplikacja wykorzystuje pamieci aplikacji.
     */
    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);

        ICustomerService customerService;
        IOrderService orderService;
        IProductService productService;

//        if(args.length > 0 && args[0] == "Database"){
            var logger = getLogger();
            customerService = new CustomerServiceDb(logger);
            orderService = new OrderServiceDb(logger);
            productService = new ProductServiceDb(logger);
//        } else {
//            customerService = new CustomerServiceInMemory();
//            orderService = new OrderServiceInMemory();
//            productService = new ProductServiceInMemory();
//        }

        new AppFrame(customerService, orderService, productService);
    }

    private static Logger getLogger() {
        var logger = Logger.getAnonymousLogger();
        return logger;
    }

//    private static Logger getLogger(){
//        var logger = Logger.getLogger("WarehouseLog");
//        FileHandler fileHandler = null;
//        try {
//            fileHandler = new FileHandler("C:/temp/WarehouseLogFile.log");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        logger.addHandler(fileHandler);
//        return logger;
//    }
}
