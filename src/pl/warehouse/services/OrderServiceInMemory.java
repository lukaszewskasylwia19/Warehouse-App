package pl.warehouse.services;

import pl.warehouse.interfaces.IOrderService;
import pl.warehouse.interfaces.IProductService;
import pl.warehouse.models.Order;
import pl.warehouse.models.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

/**
 * Dostarcza implementacje serwisu IOrderService korzystajacego z pamieci aplikacji
 */
public class OrderServiceInMemory implements IOrderService {
    private List<Order> _orderList = new ArrayList<Order>();

    public OrderServiceInMemory() {
        _orderList.add(new Order() {{
            Id = 1;
            CustomerName = "Tomasz";
            ProductName = "Sok pomaranczowy";
            ProductQuantity = 10;
            ProductPrice = 2;
            OrderDate = new Date();
        }});

        _orderList.add(new Order() {{
            Id = 1;
            CustomerName = "Krzysztof";
            ProductName = "Sok jablkowy";
            ProductQuantity = 5;
            ProductPrice = 2;
            OrderDate = new Date();
        }});
    }

    @Override
    public List<Order> getAll() {
        return _orderList;
    }
}
