
package com.dinstone.security.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.dinstone.security.AccessControlAuthentication;
import com.dinstone.security.Authentication;
import com.dinstone.security.BusinessException;
import com.dinstone.security.SecurityException;
import com.dinstone.security.service.AccountExceptionType;
import com.dinstone.security.spi.UsernamePasswordAccount;

@Controller
@RequestMapping("/account")
public class AccountController {

    private static final Logger LOG = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccessControlAuthentication accessControlAuthentication;

    // @Autowired
    // private AccountService accountService;

    @RequestMapping(value = "/login")
    public String accountLogin(String username, String password, Map<String, Object> mod, HttpServletRequest request) {
        String msg = "登陆成功";
        try {
            LOG.info("username = " + username);
            LOG.info("password = " + password);
            checkUsernamePassword(username, password);

            UsernamePasswordAccount account = new UsernamePasswordAccount(username, password);
            Authentication authen = accessControlAuthentication.authenticate(account);
            WebUtils.setSessionAttribute(request, Authentication.class.getName(), authen);
        } catch (SecurityException se) {
            msg = "无效的用户名或密码";
            System.out.println(msg);
        }
        mod.put("msg", msg);
        return "main";
    }

    private void checkUsernamePassword(String username, String password) {
        if (StringUtils.isEmpty(username)) {
            throw new BusinessException(AccountExceptionType.USERNAME_NULL);
        }
        if (StringUtils.isEmpty(password)) {
            throw new BusinessException(AccountExceptionType.PASSWORD_NULL);
        }
    }

    @RequestMapping(value = "/info")
    @ResponseBody
    public Object info(String username) {
        List<String> list = new ArrayList<String>();
        list.add("电视");
        list.add("洗衣机");
        list.add("冰箱");
        list.add("电脑");
        list.add("汽车");
        list.add("空调");
        list.add("自行车");
        list.add("饮水机");
        list.add("热水器");
        return list;
    }
}
