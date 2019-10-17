package com.jt.query;
import java.util.ArrayList;
import java.util.List;

public class EquipmentQuery {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer pageNo = 1;

    protected Integer startRow;

    protected Integer pageSize = 10;

    protected String fields;

    public EquipmentQuery() {
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

    public void setPageNo(Integer pageNo) {
        this.pageNo=pageNo;
        this.startRow = (pageNo-1)*this.pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setStartRow(Integer startRow) {
        this.startRow=startRow;
    }

    public Integer getStartRow() {
        return startRow;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize=pageSize;
        this.startRow = (pageNo-1)*this.pageSize;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setFields(String fields) {
        this.fields=fields;
    }

    public String getFields() {
        return fields;
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

        public Criteria andEquipmentIdIsNull() {
            addCriterion("equipment_id is null");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdIsNotNull() {
            addCriterion("equipment_id is not null");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdEqualTo(Long value) {
            addCriterion("equipment_id =", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdNotEqualTo(Long value) {
            addCriterion("equipment_id <>", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdGreaterThan(Long value) {
            addCriterion("equipment_id >", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdGreaterThanOrEqualTo(Long value) {
            addCriterion("equipment_id >=", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdLessThan(Long value) {
            addCriterion("equipment_id <", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdLessThanOrEqualTo(Long value) {
            addCriterion("equipment_id <=", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdIn(List<Long> values) {
            addCriterion("equipment_id in", values, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdNotIn(List<Long> values) {
            addCriterion("equipment_id not in", values, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdBetween(Long value1, Long value2) {
            addCriterion("equipment_id between", value1, value2, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdNotBetween(Long value1, Long value2) {
            addCriterion("equipment_id not between", value1, value2, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andUploadPeriodIsNull() {
            addCriterion("upload_period is null");
            return (Criteria) this;
        }

        public Criteria andUploadPeriodIsNotNull() {
            addCriterion("upload_period is not null");
            return (Criteria) this;
        }

        public Criteria andUploadPeriodEqualTo(Long value) {
            addCriterion("upload_period =", value, "uploadPeriod");
            return (Criteria) this;
        }

        public Criteria andUploadPeriodNotEqualTo(Long value) {
            addCriterion("upload_period <>", value, "uploadPeriod");
            return (Criteria) this;
        }

        public Criteria andUploadPeriodGreaterThan(Long value) {
            addCriterion("upload_period >", value, "uploadPeriod");
            return (Criteria) this;
        }

        public Criteria andUploadPeriodGreaterThanOrEqualTo(Long value) {
            addCriterion("upload_period >=", value, "uploadPeriod");
            return (Criteria) this;
        }

        public Criteria andUploadPeriodLessThan(Long value) {
            addCriterion("upload_period <", value, "uploadPeriod");
            return (Criteria) this;
        }

        public Criteria andUploadPeriodLessThanOrEqualTo(Long value) {
            addCriterion("upload_period <=", value, "uploadPeriod");
            return (Criteria) this;
        }

        public Criteria andUploadPeriodIn(List<Long> values) {
            addCriterion("upload_period in", values, "uploadPeriod");
            return (Criteria) this;
        }

        public Criteria andUploadPeriodNotIn(List<Long> values) {
            addCriterion("upload_period not in", values, "uploadPeriod");
            return (Criteria) this;
        }

        public Criteria andUploadPeriodBetween(Long value1, Long value2) {
            addCriterion("upload_period between", value1, value2, "uploadPeriod");
            return (Criteria) this;
        }

        public Criteria andUploadPeriodNotBetween(Long value1, Long value2) {
            addCriterion("upload_period not between", value1, value2, "uploadPeriod");
            return (Criteria) this;
        }

        public Criteria andInversionSwitchIsNull() {
            addCriterion("inversion_switch is null");
            return (Criteria) this;
        }

        public Criteria andInversionSwitchIsNotNull() {
            addCriterion("inversion_switch is not null");
            return (Criteria) this;
        }

        public Criteria andInversionSwitchEqualTo(Integer value) {
            addCriterion("inversion_switch =", value, "inversionSwitch");
            return (Criteria) this;
        }

        public Criteria andInversionSwitchNotEqualTo(Integer value) {
            addCriterion("inversion_switch <>", value, "inversionSwitch");
            return (Criteria) this;
        }

        public Criteria andInversionSwitchGreaterThan(Integer value) {
            addCriterion("inversion_switch >", value, "inversionSwitch");
            return (Criteria) this;
        }

        public Criteria andInversionSwitchGreaterThanOrEqualTo(Integer value) {
            addCriterion("inversion_switch >=", value, "inversionSwitch");
            return (Criteria) this;
        }

        public Criteria andInversionSwitchLessThan(Integer value) {
            addCriterion("inversion_switch <", value, "inversionSwitch");
            return (Criteria) this;
        }

        public Criteria andInversionSwitchLessThanOrEqualTo(Integer value) {
            addCriterion("inversion_switch <=", value, "inversionSwitch");
            return (Criteria) this;
        }

        public Criteria andInversionSwitchIn(List<Integer> values) {
            addCriterion("inversion_switch in", values, "inversionSwitch");
            return (Criteria) this;
        }

        public Criteria andInversionSwitchNotIn(List<Integer> values) {
            addCriterion("inversion_switch not in", values, "inversionSwitch");
            return (Criteria) this;
        }

        public Criteria andInversionSwitchBetween(Integer value1, Integer value2) {
            addCriterion("inversion_switch between", value1, value2, "inversionSwitch");
            return (Criteria) this;
        }

        public Criteria andInversionSwitchNotBetween(Integer value1, Integer value2) {
            addCriterion("inversion_switch not between", value1, value2, "inversionSwitch");
            return (Criteria) this;
        }

        public Criteria andInversionPeriodIsNull() {
            addCriterion("inversion_period is null");
            return (Criteria) this;
        }

        public Criteria andInversionPeriodIsNotNull() {
            addCriterion("inversion_period is not null");
            return (Criteria) this;
        }

        public Criteria andInversionPeriodEqualTo(Long value) {
            addCriterion("inversion_period =", value, "inversionPeriod");
            return (Criteria) this;
        }

        public Criteria andInversionPeriodNotEqualTo(Long value) {
            addCriterion("inversion_period <>", value, "inversionPeriod");
            return (Criteria) this;
        }

        public Criteria andInversionPeriodGreaterThan(Long value) {
            addCriterion("inversion_period >", value, "inversionPeriod");
            return (Criteria) this;
        }

        public Criteria andInversionPeriodGreaterThanOrEqualTo(Long value) {
            addCriterion("inversion_period >=", value, "inversionPeriod");
            return (Criteria) this;
        }

        public Criteria andInversionPeriodLessThan(Long value) {
            addCriterion("inversion_period <", value, "inversionPeriod");
            return (Criteria) this;
        }

        public Criteria andInversionPeriodLessThanOrEqualTo(Long value) {
            addCriterion("inversion_period <=", value, "inversionPeriod");
            return (Criteria) this;
        }

        public Criteria andInversionPeriodIn(List<Long> values) {
            addCriterion("inversion_period in", values, "inversionPeriod");
            return (Criteria) this;
        }

        public Criteria andInversionPeriodNotIn(List<Long> values) {
            addCriterion("inversion_period not in", values, "inversionPeriod");
            return (Criteria) this;
        }

        public Criteria andInversionPeriodBetween(Long value1, Long value2) {
            addCriterion("inversion_period between", value1, value2, "inversionPeriod");
            return (Criteria) this;
        }

        public Criteria andInversionPeriodNotBetween(Long value1, Long value2) {
            addCriterion("inversion_period not between", value1, value2, "inversionPeriod");
            return (Criteria) this;
        }

        public Criteria andPicDirIsNull() {
            addCriterion("pic_dir is null");
            return (Criteria) this;
        }

        public Criteria andPicDirIsNotNull() {
            addCriterion("pic_dir is not null");
            return (Criteria) this;
        }

        public Criteria andPicDirEqualTo(String value) {
            addCriterion("pic_dir =", value, "picDir");
            return (Criteria) this;
        }

        public Criteria andPicDirNotEqualTo(String value) {
            addCriterion("pic_dir <>", value, "picDir");
            return (Criteria) this;
        }

        public Criteria andPicDirGreaterThan(String value) {
            addCriterion("pic_dir >", value, "picDir");
            return (Criteria) this;
        }

        public Criteria andPicDirGreaterThanOrEqualTo(String value) {
            addCriterion("pic_dir >=", value, "picDir");
            return (Criteria) this;
        }

        public Criteria andPicDirLessThan(String value) {
            addCriterion("pic_dir <", value, "picDir");
            return (Criteria) this;
        }

        public Criteria andPicDirLessThanOrEqualTo(String value) {
            addCriterion("pic_dir <=", value, "picDir");
            return (Criteria) this;
        }

        public Criteria andPicDirLike(String value) {
            addCriterion("pic_dir like", value, "picDir");
            return (Criteria) this;
        }

        public Criteria andPicDirNotLike(String value) {
            addCriterion("pic_dir not like", value, "picDir");
            return (Criteria) this;
        }

        public Criteria andPicDirIn(List<String> values) {
            addCriterion("pic_dir in", values, "picDir");
            return (Criteria) this;
        }

        public Criteria andPicDirNotIn(List<String> values) {
            addCriterion("pic_dir not in", values, "picDir");
            return (Criteria) this;
        }

        public Criteria andPicDirBetween(String value1, String value2) {
            addCriterion("pic_dir between", value1, value2, "picDir");
            return (Criteria) this;
        }

        public Criteria andPicDirNotBetween(String value1, String value2) {
            addCriterion("pic_dir not between", value1, value2, "picDir");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNull() {
            addCriterion("is_delete is null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNotNull() {
            addCriterion("is_delete is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteEqualTo(Integer value) {
            addCriterion("is_delete =", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotEqualTo(Integer value) {
            addCriterion("is_delete <>", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThan(Integer value) {
            addCriterion("is_delete >", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_delete >=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThan(Integer value) {
            addCriterion("is_delete <", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThanOrEqualTo(Integer value) {
            addCriterion("is_delete <=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIn(List<Integer> values) {
            addCriterion("is_delete in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotIn(List<Integer> values) {
            addCriterion("is_delete not in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBetween(Integer value1, Integer value2) {
            addCriterion("is_delete between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotBetween(Integer value1, Integer value2) {
            addCriterion("is_delete not between", value1, value2, "isDelete");
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