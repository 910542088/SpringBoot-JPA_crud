package com.test.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Component
@Entity
@Table(name = "table_1")
public class User implements Serializable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
    @Column( name = "Column_1")
    @ExcelIgnore
    private String id;
    @Column( name = "Column_2")
    @ExcelProperty("姓名")
    private String name;
    @Column( name = "Column_3")
    @ExcelProperty("姓名简拼")
    private String spellName;
    @Column( name = "Column_4")
    @ExcelProperty("性别")
    private String sex;
    @Column( name = "Column_5")
    @ExcelProperty("证件类型")
    private String idType;
    @Column( name = "Column_6")
    @ExcelProperty("证件编号")
    private String idNumber;
    @Column( name = "Column_7")
    @JsonFormat(pattern = "yyyy/MM/hh",timezone = "GMT+8")
    @ExcelProperty("生日")
    @DateTimeFormat("yyyy/MM/dd")
    private Date birthday;
    @Column( name = "Column_8")
    @ExcelProperty("手机号码")
    private String phoneNumber;
    @Column( name = "Column_9")
    @ExcelProperty("电子邮箱")
    private String email;
    @Column( name = "createtime",updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @org.hibernate.annotations.CreationTimestamp
    @ExcelProperty("创建时间")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    @ExcelIgnore
    private Date created;
    @Column( name = "updatetime")
    @Temporal(TemporalType.TIMESTAMP)
    @org.hibernate.annotations.UpdateTimestamp
    @ExcelProperty("修改时间")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    @ExcelIgnore
    private Date update;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", spellName='" + spellName + '\'' +
                ", sex='" + sex + '\'' +
                ", idType='" + idType + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", birthday=" + birthday +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", created=" + created +
                ", update=" + update +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpellName() {
        return spellName;
    }

    public void setSpellName(String spellName) {
        this.spellName = spellName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdate() {
        return update;
    }

    public void setUpdate(Date update) {
        this.update = update;
    }

    public User(String id, String name, String spellName, String sex, String idType, String idNumber, Date birthday, String phoneNumber, String email, Date created, Date update) {
        this.id = id;
        this.name = name;
        this.spellName = spellName;
        this.sex = sex;
        this.idType = idType;
        this.idNumber = idNumber;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.created = created;
        this.update = update;
    }

    public User() {
    }
}

//    drop table if exists Table_1;
//
//        /*==============================================================*/
//        /* Table: Table_1                                               */
//        /*==============================================================*/
//        create table Table_1
//        (
//        Column_1             char(32) not null comment 'Column_1',
//        Column_2             varchar(200) comment '姓名',
//        Column_3             varchar(200) comment '姓名简拼',
//        Column_4             varchar(10) comment '性别',
//        Column_5             varchar(200) comment '身份证证件类型',
//        Column_6             varchar(200) comment '身份证件号码',
//        Column_7             date comment '出生日期',
//        Column_8             varchar(200) comment '手机号码',
//        Column_9             varchar(200) comment '电子邮箱',
//        createtime           datetime,
//        updatetime           datetime,
//        primary key (Column_1)
//        );
//
//        alter table Table_1 comment 'Table_1<人员基本信息表>';