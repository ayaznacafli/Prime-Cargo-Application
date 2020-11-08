package com.prime.common.search;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class SearchSpecification<T> implements Specification<T> {

    private static final long serialVersionUID = 3522416053866116034L;

    private final List<SearchCriteria> criteriaList;

    public SearchSpecification(List<SearchCriteria> criteriaList) {
        this.criteriaList = new ArrayList<>(criteriaList);
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        Predicate[] predicates = criteriaList.stream()
                .map(criteria -> criteria.getOperation().buildPredicate(root, criteria, builder))
                .toArray(Predicate[]::new);
        return builder.and(predicates);
    }
}
