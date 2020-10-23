package pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BorrowExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BorrowExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andBorrowIdIsNull() {
            addCriterion("borrow_id is null");
            return (Criteria) this;
        }

        public Criteria andBorrowIdIsNotNull() {
            addCriterion("borrow_id is not null");
            return (Criteria) this;
        }

        public Criteria andBorrowIdEqualTo(Integer value) {
            addCriterion("borrow_id =", value, "borrowId");
            return (Criteria) this;
        }

        public Criteria andBorrowIdNotEqualTo(Integer value) {
            addCriterion("borrow_id <>", value, "borrowId");
            return (Criteria) this;
        }

        public Criteria andBorrowIdGreaterThan(Integer value) {
            addCriterion("borrow_id >", value, "borrowId");
            return (Criteria) this;
        }

        public Criteria andBorrowIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("borrow_id >=", value, "borrowId");
            return (Criteria) this;
        }

        public Criteria andBorrowIdLessThan(Integer value) {
            addCriterion("borrow_id <", value, "borrowId");
            return (Criteria) this;
        }

        public Criteria andBorrowIdLessThanOrEqualTo(Integer value) {
            addCriterion("borrow_id <=", value, "borrowId");
            return (Criteria) this;
        }

        public Criteria andBorrowIdIn(List<Integer> values) {
            addCriterion("borrow_id in", values, "borrowId");
            return (Criteria) this;
        }

        public Criteria andBorrowIdNotIn(List<Integer> values) {
            addCriterion("borrow_id not in", values, "borrowId");
            return (Criteria) this;
        }

        public Criteria andBorrowIdBetween(Integer value1, Integer value2) {
            addCriterion("borrow_id between", value1, value2, "borrowId");
            return (Criteria) this;
        }

        public Criteria andBorrowIdNotBetween(Integer value1, Integer value2) {
            addCriterion("borrow_id not between", value1, value2, "borrowId");
            return (Criteria) this;
        }

        public Criteria andBookIdIsNull() {
            addCriterion("book_id is null");
            return (Criteria) this;
        }

        public Criteria andBookIdIsNotNull() {
            addCriterion("book_id is not null");
            return (Criteria) this;
        }

        public Criteria andBookIdEqualTo(String value) {
            addCriterion("book_id =", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdNotEqualTo(String value) {
            addCriterion("book_id <>", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdGreaterThan(String value) {
            addCriterion("book_id >", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdGreaterThanOrEqualTo(String value) {
            addCriterion("book_id >=", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdLessThan(String value) {
            addCriterion("book_id <", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdLessThanOrEqualTo(String value) {
            addCriterion("book_id <=", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdLike(String value) {
            addCriterion("book_id like", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdNotLike(String value) {
            addCriterion("book_id not like", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdIn(List<String> values) {
            addCriterion("book_id in", values, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdNotIn(List<String> values) {
            addCriterion("book_id not in", values, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdBetween(String value1, String value2) {
            addCriterion("book_id between", value1, value2, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdNotBetween(String value1, String value2) {
            addCriterion("book_id not between", value1, value2, "bookId");
            return (Criteria) this;
        }

        public Criteria andBorrowTimeIsNull() {
            addCriterion("borrow_time is null");
            return (Criteria) this;
        }

        public Criteria andBorrowTimeIsNotNull() {
            addCriterion("borrow_time is not null");
            return (Criteria) this;
        }

        public Criteria andBorrowTimeEqualTo(Date value) {
            addCriterion("borrow_time =", value, "borrowTime");
            return (Criteria) this;
        }

        public Criteria andBorrowTimeNotEqualTo(Date value) {
            addCriterion("borrow_time <>", value, "borrowTime");
            return (Criteria) this;
        }

        public Criteria andBorrowTimeGreaterThan(Date value) {
            addCriterion("borrow_time >", value, "borrowTime");
            return (Criteria) this;
        }

        public Criteria andBorrowTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("borrow_time >=", value, "borrowTime");
            return (Criteria) this;
        }

        public Criteria andBorrowTimeLessThan(Date value) {
            addCriterion("borrow_time <", value, "borrowTime");
            return (Criteria) this;
        }

        public Criteria andBorrowTimeLessThanOrEqualTo(Date value) {
            addCriterion("borrow_time <=", value, "borrowTime");
            return (Criteria) this;
        }

        public Criteria andBorrowTimeIn(List<Date> values) {
            addCriterion("borrow_time in", values, "borrowTime");
            return (Criteria) this;
        }

        public Criteria andBorrowTimeNotIn(List<Date> values) {
            addCriterion("borrow_time not in", values, "borrowTime");
            return (Criteria) this;
        }

        public Criteria andBorrowTimeBetween(Date value1, Date value2) {
            addCriterion("borrow_time between", value1, value2, "borrowTime");
            return (Criteria) this;
        }

        public Criteria andBorrowTimeNotBetween(Date value1, Date value2) {
            addCriterion("borrow_time not between", value1, value2, "borrowTime");
            return (Criteria) this;
        }

        public Criteria andShouldRTimeIsNull() {
            addCriterion("should_r_time is null");
            return (Criteria) this;
        }

        public Criteria andShouldRTimeIsNotNull() {
            addCriterion("should_r_time is not null");
            return (Criteria) this;
        }

        public Criteria andShouldRTimeEqualTo(Date value) {
            addCriterion("should_r_time =", value, "shouldRTime");
            return (Criteria) this;
        }

        public Criteria andShouldRTimeNotEqualTo(Date value) {
            addCriterion("should_r_time <>", value, "shouldRTime");
            return (Criteria) this;
        }

        public Criteria andShouldRTimeGreaterThan(Date value) {
            addCriterion("should_r_time >", value, "shouldRTime");
            return (Criteria) this;
        }

        public Criteria andShouldRTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("should_r_time >=", value, "shouldRTime");
            return (Criteria) this;
        }

        public Criteria andShouldRTimeLessThan(Date value) {
            addCriterion("should_r_time <", value, "shouldRTime");
            return (Criteria) this;
        }

        public Criteria andShouldRTimeLessThanOrEqualTo(Date value) {
            addCriterion("should_r_time <=", value, "shouldRTime");
            return (Criteria) this;
        }

        public Criteria andShouldRTimeIn(List<Date> values) {
            addCriterion("should_r_time in", values, "shouldRTime");
            return (Criteria) this;
        }

        public Criteria andShouldRTimeNotIn(List<Date> values) {
            addCriterion("should_r_time not in", values, "shouldRTime");
            return (Criteria) this;
        }

        public Criteria andShouldRTimeBetween(Date value1, Date value2) {
            addCriterion("should_r_time between", value1, value2, "shouldRTime");
            return (Criteria) this;
        }

        public Criteria andShouldRTimeNotBetween(Date value1, Date value2) {
            addCriterion("should_r_time not between", value1, value2, "shouldRTime");
            return (Criteria) this;
        }

        public Criteria andIsReturnIsNull() {
            addCriterion("is_return is null");
            return (Criteria) this;
        }

        public Criteria andIsReturnIsNotNull() {
            addCriterion("is_return is not null");
            return (Criteria) this;
        }

        public Criteria andIsReturnEqualTo(Boolean value) {
            addCriterion("is_return =", value, "isReturn");
            return (Criteria) this;
        }

        public Criteria andIsReturnNotEqualTo(Boolean value) {
            addCriterion("is_return <>", value, "isReturn");
            return (Criteria) this;
        }

        public Criteria andIsReturnGreaterThan(Boolean value) {
            addCriterion("is_return >", value, "isReturn");
            return (Criteria) this;
        }

        public Criteria andIsReturnGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_return >=", value, "isReturn");
            return (Criteria) this;
        }

        public Criteria andIsReturnLessThan(Boolean value) {
            addCriterion("is_return <", value, "isReturn");
            return (Criteria) this;
        }

        public Criteria andIsReturnLessThanOrEqualTo(Boolean value) {
            addCriterion("is_return <=", value, "isReturn");
            return (Criteria) this;
        }

        public Criteria andIsReturnIn(List<Boolean> values) {
            addCriterion("is_return in", values, "isReturn");
            return (Criteria) this;
        }

        public Criteria andIsReturnNotIn(List<Boolean> values) {
            addCriterion("is_return not in", values, "isReturn");
            return (Criteria) this;
        }

        public Criteria andIsReturnBetween(Boolean value1, Boolean value2) {
            addCriterion("is_return between", value1, value2, "isReturn");
            return (Criteria) this;
        }

        public Criteria andIsReturnNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_return not between", value1, value2, "isReturn");
            return (Criteria) this;
        }

        public Criteria andReturnTimeIsNull() {
            addCriterion("return_time is null");
            return (Criteria) this;
        }

        public Criteria andReturnTimeIsNotNull() {
            addCriterion("return_time is not null");
            return (Criteria) this;
        }

        public Criteria andReturnTimeEqualTo(Date value) {
            addCriterion("return_time =", value, "returnTime");
            return (Criteria) this;
        }

        public Criteria andReturnTimeNotEqualTo(Date value) {
            addCriterion("return_time <>", value, "returnTime");
            return (Criteria) this;
        }

        public Criteria andReturnTimeGreaterThan(Date value) {
            addCriterion("return_time >", value, "returnTime");
            return (Criteria) this;
        }

        public Criteria andReturnTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("return_time >=", value, "returnTime");
            return (Criteria) this;
        }

        public Criteria andReturnTimeLessThan(Date value) {
            addCriterion("return_time <", value, "returnTime");
            return (Criteria) this;
        }

        public Criteria andReturnTimeLessThanOrEqualTo(Date value) {
            addCriterion("return_time <=", value, "returnTime");
            return (Criteria) this;
        }

        public Criteria andReturnTimeIn(List<Date> values) {
            addCriterion("return_time in", values, "returnTime");
            return (Criteria) this;
        }

        public Criteria andReturnTimeNotIn(List<Date> values) {
            addCriterion("return_time not in", values, "returnTime");
            return (Criteria) this;
        }

        public Criteria andReturnTimeBetween(Date value1, Date value2) {
            addCriterion("return_time between", value1, value2, "returnTime");
            return (Criteria) this;
        }

        public Criteria andReturnTimeNotBetween(Date value1, Date value2) {
            addCriterion("return_time not between", value1, value2, "returnTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}