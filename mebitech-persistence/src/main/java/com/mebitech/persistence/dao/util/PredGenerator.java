package com.mebitech.persistence.dao.util;

import com.mebitech.core.api.persistence.enums.CriteriaOperator;

import javax.persistence.criteria.*;
import java.util.Date;

/**
 * Created by tayipdemircan on 27.02.2017.
 */
public class PredGenerator {

    private CriteriaOperator operator;

    private String field;

    private Object value;

    private Root from;

    private Join join;

    private CriteriaBuilder builder;

    private String type;

    private Expression expression;

    public PredGenerator() {

    }

    /**
     * @param operator
     * @param field
     * @param value
     * @param from
     * @param join
     * @param builder
     * @param type
     */
    public PredGenerator(CriteriaOperator operator, String field, Object value, Root from, Join join, CriteriaBuilder builder, String type) {
        this.operator = operator;
        this.field = field;
        this.value = value;
        this.from = from;
        this.join = join;
        this.builder = builder;
        this.type = type;
    }

    /**
     * @return expression
     */
    private Expression getExpression() {
        expression = join != null ? join.get(field) : from.get(field);
        return expression;
    }

    /**
     * @return predicate object
     */
    public Predicate getPredicate() {
        Predicate pred = null;
        switch (operator) {
            case EQ://Equal =
                pred = builder.equal(getExpression(), getValue());
                break;
            case NE://Not Equal <>
                pred = builder.notEqual(getExpression(), getValue());
                break;
            case LIKE://Like %%
                pred = builder.like(getExpression(), getStringValue());
                break;
            case IN://in
                pred = getExpression().in(getValue());
                break;
            case NOT_IN://Not in
                //TODO
                break;
            case BT://Between
                //TODO
                break;
            case NULL://isNull
                pred = getExpression().isNull();
                break;
            case NOT_NULL://isNotNull
                pred = getExpression().isNotNull();
                break;
            case GT://greaterThan >
                pred = generateGreaterThanCriteria();
                break;
            case GE://greaterThanOrEqualTo >=
                pred = generateGreaterThanOrEqualToCriteria();
                break;
            case LT://lessThan <
                pred = generateLessThanCriteria();
                break;
            case LE://lessThanOrEqualTo <=
                pred = generateLessThanOrEqualToCriteria();
                break;
            default:
                break;
        }

        return pred;
    }

    public CriteriaBuilder getBuilder() {
        return builder;
    }

    public void setBuilder(CriteriaBuilder builder) {
        this.builder = builder;
    }

    public CriteriaOperator getOperator() {
        return operator;
    }

    public void setOperator(CriteriaOperator operator) {
        this.operator = operator;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Root getFrom() {
        return from;
    }

    public void setFrom(Root from) {
        this.from = from;
    }

    public Join getJoin() {
        return join;
    }

    public void setJoin(Join join) {
        this.join = join;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private Date getDateValue() {
        return (Date) value;
    }

    private Integer getNumericValue() {
        return (Integer) value;
    }

    private String getStringValue() {
        return value.toString();
    }

    /**
     * @return lessThanCriteria predicate object
     */
    private Predicate generateLessThanCriteria() {
        Predicate pred = null;
        if (type.equals("date")) {
            Expression<Date> expressionDate = getExpression();
            pred = getBuilder().lessThan(expressionDate, getDateValue());
        } else if (type.equals("int")) {
            Expression<Integer> expressionInteger = getExpression();
            pred = getBuilder().lessThan(expressionInteger, getNumericValue());
        } else {
            Expression<String> expressionString = getExpression();
            pred = getBuilder().lessThan(expressionString, getStringValue());
        }
        return pred;
    }

    /**
     * @return lessThanOrEqualToCriteria predicate object
     */
    private Predicate generateLessThanOrEqualToCriteria() {
        Predicate pred = null;
        if (type.equals("date")) {
            Expression<Date> expressionDate = getExpression();
            pred = getBuilder().lessThanOrEqualTo(expressionDate, getDateValue());
        } else if (type.equals("int")) {
            Expression<Integer> expressionInteger = getExpression();
            pred = getBuilder().lessThanOrEqualTo(expressionInteger, getNumericValue());
        } else {
            Expression<String> expressionString = getExpression();
            pred = getBuilder().lessThanOrEqualTo(expressionString, getStringValue());
        }
        return pred;
    }

    /**
     * @return greaterThanCriteria predicate object
     */
    private Predicate generateGreaterThanCriteria() {
        Predicate pred = null;
        if (type.equals("date")) {
            Expression<Date> expressionDate = getExpression();
            pred = getBuilder().greaterThan(expressionDate, getDateValue());
        } else if (type.equals("int")) {
            Expression<Integer> expressionInteger = getExpression();
            pred = getBuilder().greaterThan(expressionInteger, getNumericValue());
        } else {
            Expression<String> expressionString = getExpression();
            pred = getBuilder().greaterThan(expressionString, getStringValue());
        }
        return pred;
    }

    /**
     * @return greaterThanOrEqualToCriteria predicate object
     */
    private Predicate generateGreaterThanOrEqualToCriteria() {
        Predicate pred = null;
        if (type.equals("date")) {
            Expression<Date> expressionDate = getExpression();
            pred = getBuilder().greaterThanOrEqualTo(expressionDate, getDateValue());
        } else if (type.equals("int")) {
            Expression<Integer> expressionInteger = getExpression();
            pred = getBuilder().greaterThanOrEqualTo(expressionInteger, getNumericValue());
        } else {
            Expression<String> expressionString = getExpression();
            pred = getBuilder().greaterThanOrEqualTo(expressionString, getStringValue());
        }
        return pred;
    }
}
