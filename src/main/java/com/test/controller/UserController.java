package com.test.controller;

import com.alibaba.excel.EasyExcel;
import com.test.common.easyexcel.UserListener;
import com.test.common.util.ExcelUtil;
import com.test.dao.UserMapper;
import com.test.domain.User;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;

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

    //根据ID删除用户
    @PostMapping("/delete")
    @ResponseBody
    public int delete(String id) {
        return userService.delete(id);
    }

    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public User saveOrUpdate(User user) {
        return userService.saveOrUpdate(user);
    }

    @GetMapping("/down")
    public void downExcelFile(HttpServletResponse response) throws IOException {
        List<User> userList = userService.getAll();
        try {
            String fileName = "用户数据";
            EasyExcel.write(ExcelUtil.getOutputStream(fileName,response), User.class).sheet("模板").doWrite(userList);
        } catch (Exception e) {
            e.printStackTrace();
            response.reset();
            response.setCharacterEncoding("utf-8");
            response.getWriter().print("下载失败，请稍后再试！");
        }
    }

    @PostMapping("/update")
    @ResponseBody
    public String update(@RequestParam("file") MultipartFile file) throws Exception {
        EasyExcel.read(file.getInputStream(), User.class, new UserListener(userMapper)).sheet().doRead();
        return "上传成功";
    }

}
