package com.a7z.zhihu.controller;

import com.a7z.zhihu.entity.json.EmailRegisterJson;
import com.a7z.zhihu.entity.json.ResultUploadImgJson;
import com.a7z.zhihu.entity.json.UserRegisterJson;
import com.a7z.zhihu.entity.vo.LoginVo;
import com.a7z.zhihu.entity.vo.RegisterVo;
import com.a7z.zhihu.service.UserService;
import com.a7z.zhihu.util.MailUtil;
import com.a7z.zhihu.util.UUIDUtil;
import com.a7z.zhihu.util.UploadUtil;
import com.a7z.zhihu.util.VerificationCode.GifCaptcha;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

/**
 * @author lq
 * @create 2020/3/25-18:59
 */
@Controller
public class CommonController {
    @Autowired
    UserService userService;


    /**
     * 上传图片
     *
     * @return
     */
    @RequestMapping(value = "/image", method = RequestMethod.POST)
    @ResponseBody
    public ResultUploadImgJson uploadImg(MultipartFile file, String src) {
        ResultUploadImgJson json = new ResultUploadImgJson();
        String name = file.getOriginalFilename();
        if (file.isEmpty()) {
            json.setCode(ResultUploadImgJson.WRONG);
            json.setExplain("上传文件为空");
            return json;
        }
        String format = name.substring(name.lastIndexOf(".")).toLowerCase();
        String fileName = UUIDUtil.UU32();
        String real = UploadUtil.getUploadFilePath();
        String srcName = fileName + format;
        String filePath = "/upload/" + src + "/";


        File dest = new File(real + filePath + fileName + format);
        try {
            file.transferTo(dest);//将改文件上传至服务器的dest文件中
            json.setCode(ResultUploadImgJson.SUCCESS);
            json.setExplain("上传成功");
            json.setImageName(srcName);
            return json;
        } catch (IOException e) {
            json.setCode(ResultUploadImgJson.WRONG);
            json.setExplain("服务器出错");
            return json;
        }
    }

    /**
     * 动态图验证码
     *
     * @param response
     * @param session
     */
    @ApiOperation("图片验证码")
    @GetMapping("/captcha")
    public void captcha(HttpServletResponse response, HttpSession session) {
        GifCaptcha gifCaptcha = new GifCaptcha(220, 40);
        String s = new String(gifCaptcha.getText());
        try {
            gifCaptcha.out(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        SecurityUtils.getSubject().getSession().setAttribute("code", s);
    }

    /**
     * 注册
     *
     * @param post
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public UserRegisterJson register(RegisterVo post) {
        UserRegisterJson json = new UserRegisterJson();
        if (userService.queryEmail(post.getEmail()) != null) {
            json.setCode(json.ERROR);
            json.setMsg("该邮箱已被注册");
            return json;
        }

        //加密
        SimpleHash md5 = new SimpleHash("MD5", post.getPassword(), post.getEmail(), 3);
        post.setPassword(md5.toString());
        userService.insertOneUser(post);


        json.setCode(json.SUCCESS);
        json.setMsg("注册成功");
        return json;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public EmailRegisterJson login(LoginVo post) {
        EmailRegisterJson json = new EmailRegisterJson();
        Subject subject = SecurityUtils.getSubject();
        String code = subject.getSession().getAttribute("code").toString();
        if (!code.equalsIgnoreCase(post.getCode())) {
            json.setMsg("验证码不正确");
            json.setCode(json.ERROR);
            return json;
        }

        SimpleHash md5 = new SimpleHash("MD5", post.getPassword(), post.getEmail(), 3);
        UsernamePasswordToken token = new UsernamePasswordToken(post.getEmail(), md5.toString());
        try {
            subject.login(token);
            json.setCode(json.SUCCESS);
            json.setCode(200);
            return json;
        } catch (UnknownAccountException e) {//用户名不存在
            json.setMsg("用户名不存在");
            json.setCode(json.ERROR);
            return json;
        } catch (IncorrectCredentialsException e) {//密码错误
            json.setMsg("密码错误");
            json.setCode(403);
            return json;
        }

    }


    /**
     * 获取注册验证码
     *
     * @param email
     * @return
     */
    @RequestMapping(value = "/emailRegister", method = RequestMethod.POST)
    @ResponseBody
    public EmailRegisterJson getEmailRegister(String email) {
        //测试邮箱        2235648251@qq.com
        EmailRegisterJson json = new EmailRegisterJson();
        if (email.equals("")) {
            json.setCode(json.ERROR);
            json.setMsg("邮箱不能空");
            return json;
        }
        String s = UUIDUtil.UU32();
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("emailRegisterCode", s);
        try {
            MailUtil.sendMail("仿知乎网站注册", s, email);
            json.setCode(json.SUCCESS);
            json.setMsg("邮件已发送,请前往邮箱获取验证码");
//            System.out.println(session.getAttribute("emailRegisterCode").toString());
        } catch (Exception e) {
            e.printStackTrace();
            json.setCode(json.ERROR);
            json.setMsg("邮件发送失败，请您检查邮箱账户");
        }


        return json;
    }


}


