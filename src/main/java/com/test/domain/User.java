package com.test.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
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
    @Pattern(regexp = "^[\\u4e00-\\u9fa5]+$",message = "姓名只能为中文并且没有特殊字符!")
    private String name;
    @Column( name = "Column_3")
    @ExcelProperty("姓名全拼")
    @Pattern(regexp = "^[a-z]+$",message = "姓名全拼只能为小写字母且没有特殊字符!")
    private String spellName;
    @Column( name = "Column_4")
    @ExcelProperty("性别")
    @NotBlank(message = "性别不能为空！")
    @Pattern(regexp = "^[男|女]$",message = "性别信息有误，请按正确格式输入！")
    private String sex;
    @Column( name = "Column_5")
    @ExcelProperty("身份证证件类型")
    @NotBlank(message = "身份证证件类型不能为空！")
    private String idType;
    @Column( name = "Column_6")
    @ExcelProperty("身份证件号码")
    @Pattern(regexp = "^\\d{15}(\\d{2}[A-Za-z0-9])?$",message = "身份证号码不正确!")
    private String idNumber;
    @Column( name = "Column_7")
    @ExcelProperty(value = "出生日期")
    @DateTimeFormat(value = "yyyy-MM-dd")//前端数据格式转换
    @JsonFormat(pattern = "yyyy/MM/dd",timezone = "GMT+8")//后台发送数据格式转换
    private Date birthday;
    @Column( name = "Column_8")
    @ExcelProperty("手机号码")
    @Pattern(regexp = "^(13[0-\u00AD9]|14[5|7]|15[0-9]|18[0-9])\\d{8}$", message = "手机号信息有误，请输入正确的手机号！")
    private String phoneNumber;
    @Column( name = "Column_9")
    @ExcelProperty("电子邮箱")
    @Pattern(regexp = "[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+",message = "请输入正确的邮箱地址！")
    private String email;
    @Column( name = "createtime",updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @org.hibernate.annotations.CreationTimestamp
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    @ExcelIgnore
    private Date created;
    @Column( name = "updatetime")
    @Temporal(TemporalType.TIMESTAMP)
    @org.hibernate.annotations.UpdateTimestamp
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
//        Column_3             varchar(200) comment '姓名全拼',
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