package flurium.jing.products;

import flurium.jing.db.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class ProductListItem {

    private Long id;
    private String name;
    private double price;
    private String description;

    public static List<ProductListItem> listFromProducts(List<Product> products) {
        return products.stream()
            .map(p -> new ProductListItem(
                p.getId(), p.getName(), p.getPrice(),
                p.getDescription().substring(0, Math.min(100, p.getDescription().length()))
            ))
            .collect(Collectors.toList());
    }
}
