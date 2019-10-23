package ustc.sse.meitu.pojo;

import java.util.ArrayList;
import java.util.List;

public class UserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserExample() {
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

        public Criteria andUidIsNull() {
            addCriterion("uid is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("uid is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(Integer value) {
            addCriterion("uid =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(Integer value) {
            addCriterion("uid <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(Integer value) {
            addCriterion("uid >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("uid >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(Integer value) {
            addCriterion("uid <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(Integer value) {
            addCriterion("uid <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<Integer> values) {
            addCriterion("uid in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<Integer> values) {
            addCriterion("uid not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(Integer value1, Integer value2) {
            addCriterion("uid between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(Integer value1, Integer value2) {
            addCriterion("uid not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUavatorIsNull() {
            addCriterion("uavator is null");
            return (Criteria) this;
        }

        public Criteria andUavatorIsNotNull() {
            addCriterion("uavator is not null");
            return (Criteria) this;
        }

        public Criteria andUavatorEqualTo(String value) {
            addCriterion("uavator =", value, "uavator");
            return (Criteria) this;
        }

        public Criteria andUavatorNotEqualTo(String value) {
            addCriterion("uavator <>", value, "uavator");
            return (Criteria) this;
        }

        public Criteria andUavatorGreaterThan(String value) {
            addCriterion("uavator >", value, "uavator");
            return (Criteria) this;
        }

        public Criteria andUavatorGreaterThanOrEqualTo(String value) {
            addCriterion("uavator >=", value, "uavator");
            return (Criteria) this;
        }

        public Criteria andUavatorLessThan(String value) {
            addCriterion("uavator <", value, "uavator");
            return (Criteria) this;
        }

        public Criteria andUavatorLessThanOrEqualTo(String value) {
            addCriterion("uavator <=", value, "uavator");
            return (Criteria) this;
        }

        public Criteria andUavatorLike(String value) {
            addCriterion("uavator like", value, "uavator");
            return (Criteria) this;
        }

        public Criteria andUavatorNotLike(String value) {
            addCriterion("uavator not like", value, "uavator");
            return (Criteria) this;
        }

        public Criteria andUavatorIn(List<String> values) {
            addCriterion("uavator in", values, "uavator");
            return (Criteria) this;
        }

        public Criteria andUavatorNotIn(List<String> values) {
            addCriterion("uavator not in", values, "uavator");
            return (Criteria) this;
        }

        public Criteria andUavatorBetween(String value1, String value2) {
            addCriterion("uavator between", value1, value2, "uavator");
            return (Criteria) this;
        }

        public Criteria andUavatorNotBetween(String value1, String value2) {
            addCriterion("uavator not between", value1, value2, "uavator");
            return (Criteria) this;
        }

        public Criteria andUnameIsNull() {
            addCriterion("uname is null");
            return (Criteria) this;
        }

        public Criteria andUnameIsNotNull() {
            addCriterion("uname is not null");
            return (Criteria) this;
        }

        public Criteria andUnameEqualTo(String value) {
            addCriterion("uname =", value, "uname");
            return (Criteria) this;
        }

        public Criteria andUnameNotEqualTo(String value) {
            addCriterion("uname <>", value, "uname");
            return (Criteria) this;
        }

        public Criteria andUnameGreaterThan(String value) {
            addCriterion("uname >", value, "uname");
            return (Criteria) this;
        }

        public Criteria andUnameGreaterThanOrEqualTo(String value) {
            addCriterion("uname >=", value, "uname");
            return (Criteria) this;
        }

        public Criteria andUnameLessThan(String value) {
            addCriterion("uname <", value, "uname");
            return (Criteria) this;
        }

        public Criteria andUnameLessThanOrEqualTo(String value) {
            addCriterion("uname <=", value, "uname");
            return (Criteria) this;
        }

        public Criteria andUnameLike(String value) {
            addCriterion("uname like", value, "uname");
            return (Criteria) this;
        }

        public Criteria andUnameNotLike(String value) {
            addCriterion("uname not like", value, "uname");
            return (Criteria) this;
        }

        public Criteria andUnameIn(List<String> values) {
            addCriterion("uname in", values, "uname");
            return (Criteria) this;
        }

        public Criteria andUnameNotIn(List<String> values) {
            addCriterion("uname not in", values, "uname");
            return (Criteria) this;
        }

        public Criteria andUnameBetween(String value1, String value2) {
            addCriterion("uname between", value1, value2, "uname");
            return (Criteria) this;
        }

        public Criteria andUnameNotBetween(String value1, String value2) {
            addCriterion("uname not between", value1, value2, "uname");
            return (Criteria) this;
        }

        public Criteria andUintroIsNull() {
            addCriterion("uintro is null");
            return (Criteria) this;
        }

        public Criteria andUintroIsNotNull() {
            addCriterion("uintro is not null");
            return (Criteria) this;
        }

        public Criteria andUintroEqualTo(String value) {
            addCriterion("uintro =", value, "uintro");
            return (Criteria) this;
        }

        public Criteria andUintroNotEqualTo(String value) {
            addCriterion("uintro <>", value, "uintro");
            return (Criteria) this;
        }

        public Criteria andUintroGreaterThan(String value) {
            addCriterion("uintro >", value, "uintro");
            return (Criteria) this;
        }

        public Criteria andUintroGreaterThanOrEqualTo(String value) {
            addCriterion("uintro >=", value, "uintro");
            return (Criteria) this;
        }

        public Criteria andUintroLessThan(String value) {
            addCriterion("uintro <", value, "uintro");
            return (Criteria) this;
        }

        public Criteria andUintroLessThanOrEqualTo(String value) {
            addCriterion("uintro <=", value, "uintro");
            return (Criteria) this;
        }

        public Criteria andUintroLike(String value) {
            addCriterion("uintro like", value, "uintro");
            return (Criteria) this;
        }

        public Criteria andUintroNotLike(String value) {
            addCriterion("uintro not like", value, "uintro");
            return (Criteria) this;
        }

        public Criteria andUintroIn(List<String> values) {
            addCriterion("uintro in", values, "uintro");
            return (Criteria) this;
        }

        public Criteria andUintroNotIn(List<String> values) {
            addCriterion("uintro not in", values, "uintro");
            return (Criteria) this;
        }

        public Criteria andUintroBetween(String value1, String value2) {
            addCriterion("uintro between", value1, value2, "uintro");
            return (Criteria) this;
        }

        public Criteria andUintroNotBetween(String value1, String value2) {
            addCriterion("uintro not between", value1, value2, "uintro");
            return (Criteria) this;
        }

        public Criteria andUlocationIsNull() {
            addCriterion("ulocation is null");
            return (Criteria) this;
        }

        public Criteria andUlocationIsNotNull() {
            addCriterion("ulocation is not null");
            return (Criteria) this;
        }

        public Criteria andUlocationEqualTo(String value) {
            addCriterion("ulocation =", value, "ulocation");
            return (Criteria) this;
        }

        public Criteria andUlocationNotEqualTo(String value) {
            addCriterion("ulocation <>", value, "ulocation");
            return (Criteria) this;
        }

        public Criteria andUlocationGreaterThan(String value) {
            addCriterion("ulocation >", value, "ulocation");
            return (Criteria) this;
        }

        public Criteria andUlocationGreaterThanOrEqualTo(String value) {
            addCriterion("ulocation >=", value, "ulocation");
            return (Criteria) this;
        }

        public Criteria andUlocationLessThan(String value) {
            addCriterion("ulocation <", value, "ulocation");
            return (Criteria) this;
        }

        public Criteria andUlocationLessThanOrEqualTo(String value) {
            addCriterion("ulocation <=", value, "ulocation");
            return (Criteria) this;
        }

        public Criteria andUlocationLike(String value) {
            addCriterion("ulocation like", value, "ulocation");
            return (Criteria) this;
        }

        public Criteria andUlocationNotLike(String value) {
            addCriterion("ulocation not like", value, "ulocation");
            return (Criteria) this;
        }

        public Criteria andUlocationIn(List<String> values) {
            addCriterion("ulocation in", values, "ulocation");
            return (Criteria) this;
        }

        public Criteria andUlocationNotIn(List<String> values) {
            addCriterion("ulocation not in", values, "ulocation");
            return (Criteria) this;
        }

        public Criteria andUlocationBetween(String value1, String value2) {
            addCriterion("ulocation between", value1, value2, "ulocation");
            return (Criteria) this;
        }

        public Criteria andUlocationNotBetween(String value1, String value2) {
            addCriterion("ulocation not between", value1, value2, "ulocation");
            return (Criteria) this;
        }

        public Criteria andUusernameIsNull() {
            addCriterion("uusername is null");
            return (Criteria) this;
        }

        public Criteria andUusernameIsNotNull() {
            addCriterion("uusername is not null");
            return (Criteria) this;
        }

        public Criteria andUusernameEqualTo(String value) {
            addCriterion("uusername =", value, "uusername");
            return (Criteria) this;
        }

        public Criteria andUusernameNotEqualTo(String value) {
            addCriterion("uusername <>", value, "uusername");
            return (Criteria) this;
        }

        public Criteria andUusernameGreaterThan(String value) {
            addCriterion("uusername >", value, "uusername");
            return (Criteria) this;
        }

        public Criteria andUusernameGreaterThanOrEqualTo(String value) {
            addCriterion("uusername >=", value, "uusername");
            return (Criteria) this;
        }

        public Criteria andUusernameLessThan(String value) {
            addCriterion("uusername <", value, "uusername");
            return (Criteria) this;
        }

        public Criteria andUusernameLessThanOrEqualTo(String value) {
            addCriterion("uusername <=", value, "uusername");
            return (Criteria) this;
        }

        public Criteria andUusernameLike(String value) {
            addCriterion("uusername like", value, "uusername");
            return (Criteria) this;
        }

        public Criteria andUusernameNotLike(String value) {
            addCriterion("uusername not like", value, "uusername");
            return (Criteria) this;
        }

        public Criteria andUusernameIn(List<String> values) {
            addCriterion("uusername in", values, "uusername");
            return (Criteria) this;
        }

        public Criteria andUusernameNotIn(List<String> values) {
            addCriterion("uusername not in", values, "uusername");
            return (Criteria) this;
        }

        public Criteria andUusernameBetween(String value1, String value2) {
            addCriterion("uusername between", value1, value2, "uusername");
            return (Criteria) this;
        }

        public Criteria andUusernameNotBetween(String value1, String value2) {
            addCriterion("uusername not between", value1, value2, "uusername");
            return (Criteria) this;
        }

        public Criteria andUpasswordIsNull() {
            addCriterion("upassword is null");
            return (Criteria) this;
        }

        public Criteria andUpasswordIsNotNull() {
            addCriterion("upassword is not null");
            return (Criteria) this;
        }

        public Criteria andUpasswordEqualTo(String value) {
            addCriterion("upassword =", value, "upassword");
            return (Criteria) this;
        }

        public Criteria andUpasswordNotEqualTo(String value) {
            addCriterion("upassword <>", value, "upassword");
            return (Criteria) this;
        }

        public Criteria andUpasswordGreaterThan(String value) {
            addCriterion("upassword >", value, "upassword");
            return (Criteria) this;
        }

        public Criteria andUpasswordGreaterThanOrEqualTo(String value) {
            addCriterion("upassword >=", value, "upassword");
            return (Criteria) this;
        }

        public Criteria andUpasswordLessThan(String value) {
            addCriterion("upassword <", value, "upassword");
            return (Criteria) this;
        }

        public Criteria andUpasswordLessThanOrEqualTo(String value) {
            addCriterion("upassword <=", value, "upassword");
            return (Criteria) this;
        }

        public Criteria andUpasswordLike(String value) {
            addCriterion("upassword like", value, "upassword");
            return (Criteria) this;
        }

        public Criteria andUpasswordNotLike(String value) {
            addCriterion("upassword not like", value, "upassword");
            return (Criteria) this;
        }

        public Criteria andUpasswordIn(List<String> values) {
            addCriterion("upassword in", values, "upassword");
            return (Criteria) this;
        }

        public Criteria andUpasswordNotIn(List<String> values) {
            addCriterion("upassword not in", values, "upassword");
            return (Criteria) this;
        }

        public Criteria andUpasswordBetween(String value1, String value2) {
            addCriterion("upassword between", value1, value2, "upassword");
            return (Criteria) this;
        }

        public Criteria andUpasswordNotBetween(String value1, String value2) {
            addCriterion("upassword not between", value1, value2, "upassword");
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