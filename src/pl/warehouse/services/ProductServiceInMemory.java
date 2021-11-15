package pl.warehouse.services;

import pl.warehouse.interfaces.IProductService;
import pl.warehouse.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Dostarcza implementacje serwisu IProductService korzystajacego z pamieci aplikacji
 */
public class ProductServiceInMemory implements IProductService {
    private List<Product> _productList = new ArrayList<Product>();

    public ProductServiceInMemory() {
        _productList.add(new Product() {{
            Id = 1;
            Name = "Sok pomaranczowy";
            Quantity = 10;
            Price = 2;
            Category = "Sok";
            Details = "2l";
        }});

        _productList.add(new Product() {{
            Id = 2;
            Name = "Sok jablkowy";
            Quantity = 5;
            Price = 2;
            Category = "Sok";
            Details = "1l";
        }});
    }

    @Override
    public List<Product> getAll() {
        return _productList;
    }

    @Override
    public void add(Product product) {
        if (product == null) return;
        product.Id = getNewId();
        _productList.add(product);
    }

    @Override
    public void update(Product product) {
        if (product == null || product.Id == null) return;

        _productList.forEach(new Consumer<Product>() {
            @Override
            public void accept(Product item) {
                if (item.Id == product.Id) {
                    item.Name = product.Name;
                    item.Quantity = product.Quantity;
                    item.Price = product.Price;
                    item.Category = product.Category;
                    item.Details = product.Details;
                    return;
                }
            }
        });
    }

    @Override
    public void delete(int id) {
        _productList.removeIf(product -> product.Id == id);
    }

    private int getNewId(){
        if(_productList == null || _productList.size() == 0) return 1;
        var lastElement = _productList.get(_productList.size() - 1);
        return lastElement.Id + 1;
    }
}
