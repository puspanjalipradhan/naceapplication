package com.nace.data;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="nace_entity")
public class NaceEntity implements Serializable {

    private static final long serialVersionUID = -2731425678149216053L;
    @Id
    @GeneratedValue
    private long id;

    @Column
    private int orderId;

    @Column
    private int level;

    @Column
    private String code;

    @Column
    private String parent;

    @Column
    private String description;

    @Column
    private String thisItemIncludes;

    @Column
    private String thisItemAlsoIncludes;

    @Column
    private String rulings;

    @Column
    private String thisItemExcludes;

    @Column
    private String refIscRef4;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThisItemIncludes() {
        return thisItemIncludes;
    }

    public void setThisItemIncludes(String thisItemIncludes) {
        this.thisItemIncludes = thisItemIncludes;
    }

    public String getThisItemAlsoIncludes() {
        return thisItemAlsoIncludes;
    }

    public void setThisItemAlsoIncludes(String thisItemAlsoIncludes) {
        this.thisItemAlsoIncludes = thisItemAlsoIncludes;
    }

    public String getRulings() {
        return rulings;
    }

    public void setRulings(String rulings) {
        this.rulings = rulings;
    }

    public String getThisItemExcludes() {
        return thisItemExcludes;
    }

    public void setThisItemExcludes(String thisItemExcludes) {
        this.thisItemExcludes = thisItemExcludes;
    }

    public String getRefIscRef4() {
        return refIscRef4;
    }

    public void setRefIscRef4(String refIscRef4) {
        this.refIscRef4 = refIscRef4;
    }


}
