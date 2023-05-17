package flurium.jing.productManagement;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductListItem {

    private Long id;
    private String name;
    private double price;
    private String description;
}
