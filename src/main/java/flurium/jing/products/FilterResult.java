package flurium.jing.products;

import flurium.jing.db.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FilterResult {
    private List<Product> products;
    private long totalCount;
}
