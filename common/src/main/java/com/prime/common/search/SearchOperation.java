package com.prime.common.search;

import java.util.Collection;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public enum SearchOperation {
    
    GREATER_THAN {
        @Override
        public Predicate buildPredicate(Root<?> root, SearchCriteria criteria, CriteriaBuilder builder) {
            return builder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString());
        }
    },
    LESS_THAN {
        @Override
        public Predicate buildPredicate(Root<?> root, SearchCriteria criteria, CriteriaBuilder builder) {
            return builder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString());
        }
    },
    GREATER_THAN_EQUAL {
        @Override
        public Predicate buildPredicate(Root<?> root, SearchCriteria criteria, CriteriaBuilder builder) {
            return builder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
        }
    },
    LESS_THAN_EQUAL {
        @Override
        public Predicate buildPredicate(Root<?> root, SearchCriteria criteria, CriteriaBuilder builder) {
            return builder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
        }
    },
    NOT_EQUAL {
        @Override
        public Predicate buildPredicate(Root<?> root, SearchCriteria criteria, CriteriaBuilder builder) {
            return builder.notEqual(root.get(criteria.getKey()), criteria.getValue().toString());
        }
    },
    EQUAL {
        @Override
        public Predicate buildPredicate(Root<?> root, SearchCriteria criteria, CriteriaBuilder builder) {
            return builder.equal(root.get(criteria.getKey()), criteria.getValue().toString());
        }
    },
    MATCH {
        @Override
        public Predicate buildPredicate(Root<?> root, SearchCriteria criteria, CriteriaBuilder builder) {
            return builder.like(builder.lower(root.get(criteria.getKey())),
                    PERCENT_SIGN + criteria.getValue().toString().toLowerCase() + PERCENT_SIGN);
        }
    },
    MATCH_START {
        @Override
        public Predicate buildPredicate(Root<?> root, SearchCriteria criteria, CriteriaBuilder builder) {
            return builder.like(builder.lower(root.get(criteria.getKey())),
                    criteria.getValue().toString().toLowerCase() + PERCENT_SIGN);
        }
    },
    MATCH_END {
        @Override
        public Predicate buildPredicate(Root<?> root, SearchCriteria criteria, CriteriaBuilder builder) {
            return builder.like(builder.lower(root.get(criteria.getKey())),
                    PERCENT_SIGN + criteria.getValue().toString().toLowerCase());
        }
    },
    IN {
        @Override
        public Predicate buildPredicate(Root<?> root, SearchCriteria criteria, CriteriaBuilder builder) {
            return builder.in(root.get(criteria.getKey())).value(criteria.getValue());
        }
    },
    NOT_IN {
        @Override
        public Predicate buildPredicate(Root<?> root, SearchCriteria criteria, CriteriaBuilder builder) {
            return builder.not(root.get(criteria.getKey())).in(criteria.getValue());
        }
    },
    ANY_OF {
        @Override
        public Predicate buildPredicate(Root<?> root, SearchCriteria criteria, CriteriaBuilder builder) {
            Object value = criteria.getValue();
            if (!(value instanceof Collection)) {
                throw new IllegalArgumentException("ANY_OF operation is allowed on the collection only");
            }
            Collection<?> elements = (Collection<?>) value;
            Predicate[] predicates = elements.stream()
                    .map(v -> builder.equal(root.get(criteria.getKey()), v))
                    .toArray(Predicate[]::new);
            return builder.or(predicates);
        } 
    },
    IS_MEMBER {
        @Override
        public Predicate buildPredicate(Root<?> root, SearchCriteria criteria, CriteriaBuilder builder) {
            Object value = criteria.getValue();
            if (!(value instanceof Collection)) {
                throw new IllegalArgumentException("IS_MEMBER operation is allowed on the collection only");
            }
            Collection<?> elements = (Collection<?>) value;
            Predicate[] predicates = elements.stream()
                    .map(v -> builder.isMember(v, root.get(criteria.getKey())))
                    .toArray(Predicate[]::new);
            return builder.or(predicates);
        }
    };

    private static final String PERCENT_SIGN = "%";

    public abstract Predicate buildPredicate(Root<?> root, SearchCriteria criteria, CriteriaBuilder builder);
}
