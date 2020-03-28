package com.a7z.zhihu.controller;


import com.a7z.zhihu.entity.json.ResultUploadImgJson;
import com.a7z.zhihu.entity.json.UserRegisterJson;
import com.a7z.zhihu.entity.vo.RegisterVo;
import com.a7z.zhihu.util.UUIDUtil;
import com.a7z.zhihu.util.UploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import java.io.*;


/**
 * @author lq
 * @create 2020/3/25-18:59
 */
@Controller
@RequestMapping("/common")
public class CommonController {
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
     * 注册
     *
     * @param postVo
     * @return
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public UserRegisterJson register(RegisterVo postVo) {
        UserRegisterJson json = new UserRegisterJson();


        return json;
    }
}


