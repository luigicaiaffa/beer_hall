package org.project.java.beer_hall.specification;

import org.project.java.beer_hall.model.Brewery;
import org.springframework.data.jpa.domain.Specification;

public class BrewerySpecification {
    public static Specification<Brewery> filter(String name, String nation) {
        return (root, query, cb) -> {
            var predicates = cb.conjunction();

            if (name != null && !name.isEmpty()) {
                predicates = cb.and(predicates, cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }
            if (nation != null && !nation.isEmpty()) {
                predicates = cb.and(predicates, cb.like(cb.lower(root.get("nation").get("name")),
                        "%" + nation.toLowerCase() + "%"));
            }

            return predicates;
        };
    }
}
