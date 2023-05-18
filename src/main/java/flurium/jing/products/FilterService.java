package flurium.jing.products;

import flurium.jing.db.models.Category;
import flurium.jing.db.models.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FilterService {

    @PersistenceContext
    private EntityManager em;

    public FilterResult filter(
        @Nullable List<Long> categories,
        @Nullable Double min,
        @Nullable Double max,
        @Nullable String text,
        Pageable pageable
    ) {
        var builder = em.getCriteriaBuilder();

        /*
            We should get total count of filter products for pagination.
            So we should construct 2 queries.
        */

        var productQuery = builder.createQuery(Product.class);
        var countQuery = builder.createQuery(Long.class);

        var productQueryRoot = productQuery.from(Product.class);
        var countQueryRoot = countQuery.from(Product.class);

        var countQueryConditions = new ArrayList<Predicate>();
        var productQueryConditions = new ArrayList<Predicate>();

        if(categories != null && categories.size() > 0) {
            productQueryConditions.add(productQueryRoot.join("category").get("id").in(categories));
            countQueryConditions.add(countQueryRoot.join("category").get("id").in(categories));
        }

        if(min != null) {
            productQueryConditions.add(builder.greaterThanOrEqualTo(productQueryRoot.get("price"), min));
            countQueryConditions.add(builder.greaterThanOrEqualTo(countQueryRoot.get("price"), min));
        }

        if (max != null) {
            productQueryConditions.add(builder.lessThanOrEqualTo(productQueryRoot.get("price"), max));
            countQueryConditions.add(builder.lessThanOrEqualTo(countQueryRoot.get("price"), max));
        }

        if(text != null && !text.isEmpty()) {
            productQueryConditions.add(builder.or(
                    builder.like(productQueryRoot.get("name"), "%" + text + "%"),
                    builder.like(productQueryRoot.get("description"), "%" + text + "%")
            ));
            countQueryConditions.add(builder.or(
                    builder.like(countQueryRoot.get("name"), "%" + text + "%"),
                    builder.like(countQueryRoot.get("description"), "%" + text + "%")
            ));

            // sort only for product query
            var sortOrder = builder.selectCase()
                    .when(builder.like(productQueryRoot.get("name"), "%" + text + "%"), 0)
                    .otherwise(1);

            productQuery.orderBy(builder.asc(sortOrder), builder.asc(productQueryRoot.get("name")));
        }

        productQuery.where(productQueryConditions.toArray(new Predicate[0]));
        countQuery.where(countQueryConditions.toArray(new Predicate[0]));

        List<Product> products = em.createQuery(productQuery)
                .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        countQuery.select(builder.count(countQueryRoot));
        Long count = em.createQuery(countQuery).getSingleResult();

        return new FilterResult(products, count);
    }



}
