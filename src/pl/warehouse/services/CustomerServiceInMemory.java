package pl.warehouse.services;

import pl.warehouse.interfaces.ICustomerService;
import pl.warehouse.models.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Dostarcza implementacje serwisu ICustomerService korzystajacego z pamieci aplikacji
 */
public class CustomerServiceInMemory implements ICustomerService {
    private List<Customer> _customerList = new ArrayList<Customer>();

    public CustomerServiceInMemory() {
        _customerList.add(new Customer() {{
            Id = 1;
            Name = "Tomasz";
            Address = "Żeromskieog 21";
            Email = "tomasz@wp.pl";
            NIP = "4325423434";
        }});

        _customerList.add(new Customer() {{
            Id = 2;
            Name = "Krzysztof";
            Address = "Akacjowa 23/12";
            Email = "krzysztof@gmail.com";
            NIP = "5275427956";
        }});
    }

    @Override
    public List<Customer> getAll() {
        return _customerList;
    }

    @Override
    public void add(Customer customer) {
        if (customer == null) return;
        customer.Id = getNewId();
        _customerList.add(customer);
    }

    @Override
    public void update(Customer customer) {
        if (customer == null || customer.Id == null) return;

        _customerList.forEach(new Consumer<Customer>() {
            @Override
            public void accept(Customer item) {
                if (item.Id == item.Id) {
                    item.Name = item.Name;
                    item.Address = item.Address;
                    item.Email = item.Email;
                    item.NIP = item.NIP;
                    return;
                }
            }
        });
    }

    @Override
    public void delete(int id) {
        _customerList.removeIf(customer -> customer.Id == id);
    }

    private int getNewId(){
        if(_customerList == null || _customerList.size() == 0) return 1;
        var lastElement = _customerList.get(_customerList.size() - 1);
        return lastElement.Id + 1;
    }
}
