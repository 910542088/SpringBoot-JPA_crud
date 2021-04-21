package com.test.common.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.test.dao.UserMapper;
import com.test.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class UserListener extends AnalysisEventListener<User> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserListener.class);
    private static final int BATCH_COUNT = 10;
    List<User> list = new ArrayList<>();

    private final UserMapper userMapper;

    public UserListener(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void invoke(User user, AnalysisContext analysisContext) {
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(user));
        list.add(user);
        if (list.size()>=BATCH_COUNT){//批量导入
            saveData();
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();//倒入最后的数据
        LOGGER.info("所有数据解析完成！");
    }

    private void saveData() {
        userMapper.saveAll(list);
    }
}
