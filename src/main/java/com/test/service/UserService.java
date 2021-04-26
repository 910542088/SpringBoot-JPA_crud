package com.test.service;

import com.test.domain.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    User saveOrUpdate(User user) throws IllegalAccessException;

    List<User> getAll();

    int delete(String[] id);

    Page<User> selectPage(Integer currentPage, Integer pageSize, String name, String value);
}
