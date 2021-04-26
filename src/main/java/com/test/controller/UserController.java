package com.test.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.test.common.easyexcel.UserListener;
import com.test.common.easyexcel.WriteHandler;
import com.test.common.util.ExcelUtil;
import com.test.common.util.UserUtil;
import com.test.dao.UserMapper;
import com.test.domain.User;
import com.test.domain.UserExcelDto;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;

    UserListener listener;

    @RequestMapping(value = {"/index", "/"})
    public String index() {
        return "index";
    }

    //Excel下载使用
    @PostMapping("/showAll")
    @ResponseBody
    public List<User> showAll() {
        return userService.getAll();
    }

    //首页刷新数据&条件查询
    @PostMapping("/selectPage")
    @ResponseBody
    public Map<String, Object> selectPage(@RequestParam("page") Integer currentPage,
                                          @RequestParam("rows") Integer pageSize,
                                          @RequestParam("name") @Nullable String name,
                                          @RequestParam("value") @Nullable String value) {
        Page<User> page = userService.selectPage(currentPage, pageSize, name, value);
        Map<String, Object> jsonMap = new HashMap<>();
        //前端使用datagrid时默认需要的数据 --> total(总数)  &  rows(每页的数据)
        jsonMap.put("total", page.getTotalElements());
        jsonMap.put("rows", page.getContent());
        return jsonMap;
    }

    //根据ID删除用户,前端发送的ajax注意标明 --> traditional：true
    @PostMapping("/delete")
    @ResponseBody
    public int delete(@RequestParam("ids") String[] ids) {
        return userService.delete(ids);
    }

    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public String saveOrUpdate(@Validated UserExcelDto dto, @Nullable String id) throws IllegalAccessException, ParseException {
        UserExcelDto trimObject = (UserExcelDto) UserUtil.trimObject(dto);//去除文本内空格
        boolean b = UserUtil.checkIdtype(trimObject);//验证身份证证件类型
        if (b) {
            User user = UserUtil.swapUser(trimObject, id);
            userService.saveOrUpdate(user);
        } else {
            return "身份证证件类型不存在!";
        }
        return "";
    }

    @GetMapping("/down")
    public void downExcelFile(HttpServletResponse response) throws IOException {
        List<User> userList = userService.getAll();
        String fileName = "用户数据";
        EasyExcel.write(ExcelUtil.getOutputStream(fileName, response), User.class).sheet("用户数据").doWrite(userList);
    }


    @GetMapping("/downTemplate")
    public void downExcelTemplate(HttpServletResponse response) throws IOException {
        String fileName = "模板";
        EasyExcel.write(ExcelUtil.getOutputStream(fileName, response), User.class).inMemory(Boolean.TRUE).registerWriteHandler(new WriteHandler())
                .sheet("模板").doWrite(null);
    }

    @PostMapping("/upload")
    @ResponseBody
    public String upload(MultipartFile file) {
        String name = file.getOriginalFilename();
        int lastIndexOf = name.lastIndexOf(".");
        if ("xlsx".equals(name.substring(lastIndexOf + 1)) || "xls".equals(name.substring(lastIndexOf + 1))) {
            try {
                listener = new UserListener(userMapper);
                EasyExcel.read(file.getInputStream(), UserExcelDto.class,listener).sheet().doRead();
                boolean isEmptyMessage = StringUtils.isEmpty(String.valueOf(listener.builder));
                return isEmptyMessage ? "上传成功~" : String.valueOf(listener.builder);
            } catch (IOException e) {
                e.printStackTrace();
                return "上传失败";
            }
        }
        return "数据格式不支持！";
    }

    @PostMapping("/getIdType")
    @ResponseBody
    public Object getIdType() {
        String jsonStr = "[{\"value\":\"居民身份证\"},{ \"value\":\"士官证\"},{ \"value\":\"学生证\"}," +
                "{ \"value\":\"驾驶证\"},{ \"value\":\"护照\"},{\"value\":\"港澳通行证\"}]";
        return JSON.parse(jsonStr);
    }
}
