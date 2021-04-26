package com.test.common.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.test.common.util.UserUtil;
import com.test.dao.UserMapper;
import com.test.domain.User;
import com.test.domain.UserExcelDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class UserListener extends AnalysisEventListener<UserExcelDto> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserListener.class);

    List<User> list = new ArrayList<>();
    public StringBuilder builder = new StringBuilder();
    private int count = 1;
    private boolean isUpdatable = true;
    private final UserMapper userMapper;

    public UserListener(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    private void saveData() {
        userMapper.saveAll(list);
    }

    @Override
    public void invoke(UserExcelDto dto, AnalysisContext analysisContext) {
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(dto));
        String msg = "";
        count++;
        try {
            UserExcelDto trimObject = (UserExcelDto) UserUtil.trimObject(dto);
            boolean idTypeExist = UserUtil.checkIdtype(trimObject);
            msg = EasyExcelValidateHelper.validateEntity(dto);
            if (StringUtils.isEmpty(msg) && idTypeExist && isUpdatable) {
                //复制对象,导入数据库
                User swapUser = UserUtil.swapUser(dto);
                list.add(swapUser);
            } else if (!idTypeExist) {
                builder.append("第" + count + "行->" + msg + "身份证证件类型不支持！");
                isUpdatable = false;
                list.clear();
            } else if (!StringUtils.isEmpty(msg)){
                builder.append("第" + count + "行->" + msg);
                isUpdatable = false;
                list.clear();
            }
        } catch (NoSuchFieldException | IllegalAccessException | ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        LOGGER.info("所有数据解析完成！");
        if (isUpdatable) {
            saveData();//导入最后的数据
            LOGGER.info("所有数据已上传！");
        } else {
            LOGGER.error("数据有误，已经取消上传！");
        }
    }


}
