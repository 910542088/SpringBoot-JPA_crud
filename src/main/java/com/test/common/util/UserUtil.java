package com.test.common.util;

import com.test.domain.User;
import com.test.domain.UserExcelDto;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserUtil {

    private final static List<String> idTypeList = Arrays.asList("居民身份证","士官证","学生证","驾驶证","护照","港澳通行证");

    public static Object trimObject(Object o) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Field[] declaredFields = o.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            String type = field.getType().getCanonicalName();
            if (StringUtils.equals("java.lang.String", type)) {
                field.setAccessible(true);
                Object object = field.get(o);
                if (object != null) {
                    String trim = object.toString().replace(" ", "");
                    String s = trim.replace("-", "");
                    map.put(field.getName(), s);
                }
            }
        }
        for (Field field : declaredFields) {
            if (map.get(field.getName()) != null) {
                String s = (String) map.get(field.getName());
                field.setAccessible(true);
                field.set(o, s);
            }
        }
        return o;
    }

    public static boolean checkIdtype(UserExcelDto user){
        String idType = user.getIdType();
        return idTypeList.contains(idType);
    }

    public static User swapUser(UserExcelDto trimObject,String id) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        String birthday = trimObject.getBirthday();
        String[] split = birthday.split("/");
        if (split[1].length()==1){
            split[1] = "0"+split[0];
        }
        if (split[2].length()==1){
            split[2] = "0"+split[2];
        }
        birthday = split[0] + "/" + split[1] + "/" + split[2];

        User user = new User();
        user.setId(id);
        user.setName(trimObject.getName());
        user.setSpellName(trimObject.getSpellName());
        user.setIdType(trimObject.getIdType());
        user.setIdNumber(trimObject.getIdNumber());
        user.setSex(trimObject.getSex());
        user.setBirthday(format.parse(birthday));
        user.setPhoneNumber(trimObject.getPhoneNumber());
        user.setEmail(trimObject.getEmail());
        return user;
    }

    public static User swapUser(UserExcelDto trimObject) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        String birthday = trimObject.getBirthday();
        String[] split = birthday.split("/");
        if (split[1].length()==1){
            split[1] = "0"+split[0];
        }
        if (split[2].length()==1){
            split[2] = "0"+split[2];
        }
        birthday = split[0] + "/" + split[1] + "/" + split[2];

        User user = new User();
        user.setName(trimObject.getName());
        user.setSpellName(trimObject.getSpellName());
        user.setIdType(trimObject.getIdType());
        user.setIdNumber(trimObject.getIdNumber());
        user.setSex(trimObject.getSex());
        user.setBirthday(format.parse(birthday));
        user.setPhoneNumber(trimObject.getPhoneNumber());
        user.setEmail(trimObject.getEmail());
        return user;
    }
}
