package com.yasi.web;

import com.common.base.BaseController;
import com.common.util.CheckUtil;
import com.yasi.bo.UserBo;
import com.yasi.vo.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
@Controller
@Scope("prototype")
@RequestMapping("UserController/")
public class UserController extends BaseController {
    private static Logger logger = Logger.getLogger(UserController.class);

    /**
     * 注入业务接口层
     **/
    @Autowired
    private UserBo userBo;

    /**
     * 定义参数
     **/
    private User user;

    @ModelAttribute
    public void setParaVal(User user) {
        this.user = user;
    }

    public UserBo getUserBo() {
        return userBo;
    }

    @RequestMapping("login.do")
    public void login() {
        User u = new User();
        u.setUsername(user.getUsername());
        List<User> resultArr = userBo.findUserByPojo(u);
        if (resultArr == null || resultArr.size() == 0) {
            setFailure("账号不存在");
            return;
        }
        CheckUtil.Look(resultArr.get(0));
        if (resultArr.get(0).getPassword().equals(user.getPassword())) {
            setSuccess("登录成功");
            session.setAttribute("user", resultArr.get(0));
            return;
        } else {
            setFailure("密码错误");
            return;
        }
    }

    @RequestMapping("logout.do")
    public void logout() {
        session.removeAttribute("user");
        setSuccess("登出成功");
    }

    @RequestMapping("findSessionByLogin.do")
    public void findSessionByLogin() {
        User u = (User) session.getAttribute("user");
        if (u == null) {
            setFailure("未登录");
            return;
        }
        setAjaxObject(u);
        return;
    }

    @RequestMapping("findUserByPojo.do")
    public void findUserByPojo() {
        List<User> list = userBo.findUserByPojo(user);
        if (list == null || list.size() == 0) {
            setFailure("未发现users");
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            CheckUtil.Look(list.get(i));
        }
        setAjaxObject(list);
        return;
    }
}
