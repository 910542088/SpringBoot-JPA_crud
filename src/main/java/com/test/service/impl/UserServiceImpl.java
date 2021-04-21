package com.test.service.impl;

import com.test.dao.UserMapper;
import com.test.domain.User;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper mapper;

    @Override
    public User saveOrUpdate(User user) {
        return mapper.save(user);
    }

    @Override
    public List<User> getAll() {
        return mapper.findAll();
    }

    @Override
    public int delete(String id) {
        try {
            mapper.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @Override
    public Page<User> selectPage(Integer page, Integer pageSize, String name, String value) {
        if (value == null || value.length() == 0) {//无搜索查询
            PageRequest pageRequest = PageRequest.of(page - 1, pageSize,Sort.by(Sort.Direction.DESC,"created"));
            return mapper.findAll(pageRequest);
        } else {//有搜索查询，根据name值判断
            User user;
            if ("Column_2".equals(name)) {
                user = new User();
                user.setName(value);
                return getUserList(user, page, pageSize);
            } else if ("Column_8".equals(name)) {
                user = new User();
                user.setPhoneNumber(value);
                return getUserList(user, page, pageSize);
            } else {
                user = new User();
                user.setIdNumber(value);
                return getUserList(user, page, pageSize);
            }
        }
    }

    public Page<User> getUserList(User user, Integer page, Integer pageSize) {
        Example<User> example = Example.of(user);
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC,"created"));
        return mapper.findAll(example, pageRequest);
    }
}
