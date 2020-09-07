package com.woniuxy.cq.ssmboot39.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ThymeleafDataController {

    @RequestMapping("data")
    public String go(ModelMap modelMap, HttpSession session){
        modelMap.addAttribute("username","张三");
        modelMap.addAttribute("dishes", Arrays.asList("盘龙茄子","红烧猪耳朵","肝腰合炒"));

        modelMap.addAttribute("age",12);
        modelMap.addAttribute("dishClass","dish");
        modelMap.addAttribute("usernameClass","username");
        modelMap.addAttribute("hongshaorou","https://www.lostlaowai.com/wp-content/uploads/2015/03/hongshao_rou_by_vinwim_web.jpg");
        Map<String,Object> map = new HashMap<>();
        map.put("age",20);
        map.put("job","test");
        map.put("firtDateTime",new Date());
        session.setAttribute("fanrongdeduixiang",map);
        return "thymeleafDemo";
    }

}
