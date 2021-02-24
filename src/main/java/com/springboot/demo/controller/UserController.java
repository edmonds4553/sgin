package com.springboot.demo.controller;

import com.springboot.demo.base.controller.BaseController;
import com.springboot.demo.base.utils.RedisConstants;
import com.springboot.demo.base.utils.RedisUtil;
import com.springboot.demo.base.utils.StateParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: UserController
 * @Auther: FT
 * @Date: 2021/02-24
 * @Description:
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    RedisUtil redisUtil;

    /**
     * @return
     */
    @RequestMapping(value = "/test")
    @ResponseBody
    public ModelMap test() {
        try {
            redisUtil.set("redisTemplate", "这是一条测试数据", RedisConstants.datebase2);
            String value = redisUtil.get("redisTemplate", RedisConstants.datebase2).toString();
            logger.info("redisValue=" + value);
            logger.info("读取redis成功");
            return getModelMap(StateParameter.SUCCESS, value, "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return getModelMap(StateParameter.FAULT, null, "操作失败");
        }
    }

}
