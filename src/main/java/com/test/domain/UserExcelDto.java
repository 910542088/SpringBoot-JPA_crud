package com.test.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Component
public class UserExcelDto implements Serializable {

    @ExcelProperty("姓名")
    @Pattern(regexp = "^[\\u4e00-\\u9fa5]+$",message = "姓名只能为中文并且没有特殊字符!")
    private String name;
    @ExcelProperty("姓名全拼")
    @NotBlank(message = "姓名全拼不能为空")
    @Pattern(regexp = "^[a-z]+$",message = "姓名全拼只能为小写字母且没有特殊字符!")
    private String spellName;

    @ExcelProperty("性别")
    @NotBlank(message = "性别不能为空！")
    @Pattern(regexp = "^[男|女]$",message = "性别信息有误，请按正确格式输入！")
    private String sex;

    @ExcelProperty("身份证证件类型")
    @NotBlank(message = "身份证证件类型不能为空！")
    private String idType;

    @ExcelProperty("身份证件号码")
    @Pattern(regexp = "^\\d{15}(\\d{2}[A-Za-z0-9])?$",message = "身份证号码不正确!")
    private String idNumber;

    @ExcelProperty(value = "出生日期")
    @Pattern(regexp = "^(?:(?!0000)[0-9]{4}([-/.]?)(?:(?:0?[1-9]|1[0-2])\\1(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])\\1(?:29|30)|(?:0?[13578]|1[02])\\1(?:31))|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)([-/.]?)0?2\\2(?:29))$",message = "出生日期格式不正确!")
    private String birthday;

    @ExcelProperty("手机号码")
    @Pattern(regexp = "^(13[0-\u00AD9]|14[5|7]|15[0-9]|18[0-9])\\d{8}$", message = "手机号信息有误，请输入正确的手机号！")
    private String phoneNumber;

    @ExcelProperty("电子邮箱")
    @Pattern(regexp = "[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+",message = "请输入正确的邮箱地址！")
    private String email;

    public UserExcelDto(@Pattern(regexp = "^[\\u4e00-\\u9fa5]+$", message = "姓名只能为中文并且没有特殊字符!") String name, @Pattern(regexp = "^[a-z]+$", message = "姓名全拼只能为小写字母且没有特殊字符!") String spellName, @NotBlank(message = "性别不能为空！") @Pattern(regexp = "^[男|女]$", message = "性别信息有误，请按正确格式输入！") String sex, @NotBlank(message = "身份证证件类型不能为空！") String idType, @Pattern(regexp = "^\\d{15}(\\d{2}[A-Za-z0-9])?$", message = "身份证号码不正确!") String idNumber, String birthday, @Pattern(regexp = "^(13[0-\u00AD9]|14[5|7]|15[0-9]|18[0-9])\\d{8}$", message = "手机号信息有误，请输入正确的手机号！") String phoneNumber, @Pattern(regexp = "[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+", message = "请输入正确的邮箱地址！") String email) {
        this.name = name;
        this.spellName = spellName;
        this.sex = sex;
        this.idType = idType;
        this.idNumber = idNumber;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public UserExcelDto() {
    }

    @Override
    public String toString() {
        return "UserExcelDto{" +
                "name='" + name + '\'' +
                ", spellName='" + spellName + '\'' +
                ", sex='" + sex + '\'' +
                ", idType='" + idType + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", birthday='" + birthday + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
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
}
