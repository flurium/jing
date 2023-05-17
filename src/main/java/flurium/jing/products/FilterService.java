package flurium.jing.products;

import flurium.jing.db.models.Category;
import flurium.jing.db.models.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FilterService {

    @PersistenceContext
    private EntityManager em;

    public List<Product> filter(
        @Nullable List<Long> categories,
        @Nullable Double min,
        @Nullable Double max,
        @Nullable String text
    ) {
        var builder = em.getCriteriaBuilder();
        var query = builder.createQuery(Product.class);
        var product = query.from(Product.class);
        Join<Product, Category> categoryJoin = product.join("category");

        var conditions = new ArrayList<Predicate>();
        if(categories != null && categories.size() > 0) {
            //conditions.add(categoryJoin.get("id").in(categories));
            conditions.add(categoryJoin.get("id").in(categories));
        }

        if(min != null) {
            conditions.add(builder.greaterThanOrEqualTo(product.get("price"), min));
        }

        if (max != null) {
            conditions.add(builder.lessThanOrEqualTo(product.get("price"), max));
        }

        if(text != null && !text.isEmpty()) {
            conditions.add(builder.like(product.get("name"), "%" + text + "%"));
        }

        query.where(conditions.toArray(new Predicate[0]));

        return em.createQuery(query).getResultList();
    }
}
