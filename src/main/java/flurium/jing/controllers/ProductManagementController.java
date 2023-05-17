package flurium.jing.controllers;

import flurium.jing.db.repositories.ProductRepository;
import flurium.jing.products.ProductListItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ProductManagementController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/me/products")
    public String list(Model model, Authentication authentication) {

        var products = productRepository.findAll(); //productRepository.findAllByUserId();
        var productList = ProductListItem.listFromProducts(products);

        var product = new ProductListItem(1L, "test", 125, "test description about product");
        productList.add(product);
        model.addAttribute("products", productList);
        return "product_management/list";
    }
}
