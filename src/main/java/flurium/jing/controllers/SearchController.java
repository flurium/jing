package flurium.jing.controllers;

import flurium.jing.db.models.Product;
import flurium.jing.db.repositories.CategoryRepository;
import flurium.jing.products.FilterService;
import flurium.jing.products.ProductListItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private FilterService filterService;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/")
    public String search(
        @RequestParam(required = false) List<Long> categories,
        @RequestParam(required = false)  Double min,
        @RequestParam(required = false)  Double max,
        @RequestParam(required = false)  String text,
        @PageableDefault(size = 1) Pageable pageable,
        Model model
    ) {
        var products = filterService.filter(categories, min, max, text, pageable);
        //Page<Product> productsPage =  new PageImpl<>(products.getProducts(), pageable, products.getTotalCount());
        model.addAttribute("products",  ProductListItem.listFromProducts(products.getProducts()));
        model.addAttribute("totalCount", products.getTotalCount());
        return "products/search";
    }
}
