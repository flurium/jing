package flurium.jing.controllers;

import flurium.jing.db.models.Category;
import flurium.jing.db.repositories.CategoryRepository;
import flurium.jing.products.FilterService;
import flurium.jing.products.ProductListItem;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.springframework.beans.factory.annotation.Autowired;
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
        Model model
    ) {
        var products = filterService.filter(categories, min, max, text);
        model.addAttribute("products",  ProductListItem.listFromProducts(products));
        return "search";
    }
}
