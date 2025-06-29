package org.project.java.beer_hall.specification;

import org.project.java.beer_hall.model.Beer;
import org.springframework.data.jpa.domain.Specification;

public class BeerSpecification {
    public static Specification<Beer> filter(String name, String nation, String style, String brewery) {
        return (root, query, cb) -> {
            var predicates = cb.conjunction();

            if (name != null && !name.isEmpty()) {
                predicates = cb.and(predicates, cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }
            if (nation != null && !nation.isEmpty()) {
                predicates = cb.and(predicates, cb.like(cb.lower(root.get("brewery").get("nation").get("name")),
                        "%" + nation.toLowerCase() + "%"));
            }
            if (style != null && !style.isEmpty()) {
                predicates = cb.and(predicates,
                        cb.like(cb.lower(root.get("style").get("name")), "%" + style.toLowerCase() + "%"));
            }
            if (brewery != null && !brewery.isEmpty()) {
                predicates = cb.and(predicates,
                        cb.like(cb.lower(root.get("brewery").get("name")), "%" + brewery.toLowerCase() + "%"));
            }

            return predicates;
        };
    }

}
