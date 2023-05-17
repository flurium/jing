package flurium.jing.db;

import flurium.jing.db.models.Category;
import flurium.jing.db.models.Product;
import flurium.jing.db.repositories.CategoryRepository;
import flurium.jing.db.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitDatabase {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Bean
    public CommandLineRunner init() {
        return args -> {
            var category1 = new Category("sport");
            var category2 = new Category("electronics");
            var category3 = new Category("food");

            categoryRepository.save(category1);
            categoryRepository.save(category2);
            categoryRepository.save(category3);

            var product1 = new Product("product1", 56.2, "product 1 descrp", category1);
            var product2 = new Product("product2", 534.2, "product 2 descrp", category1);
            var product3 = new Product("product3", 566.2, "product 3 descrp", category3);
            var product4 = new Product("product4", 156.2, "product 4 descrp", category2);

            productRepository.save(product1);
            productRepository.save(product2);
            productRepository.save(product3);
            productRepository.save(product4);
        };
    }
}
