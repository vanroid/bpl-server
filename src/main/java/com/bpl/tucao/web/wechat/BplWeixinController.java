package com.bpl.tucao.web.wechat;

import com.bpl.tucao.entity.BplUser;
import com.bpl.tucao.service.BplUserService;
import com.bpl.tucao.utils.SessionUtils;
import com.bpl.tucao.utils.WeixinUtil;
import com.thinkgem.jeesite.common.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.bpl.tucao.utils.SessionUtils.KEY_3_RD;

/**
 * Created by johnnwang on 2017/8/10.
 */
@Controller
@RequestMapping(value = "/wx")
public class BplWeixinController extends BaseController {
    @Autowired
    private BplUserService bplUserService;

    @RequestMapping(value = "/login")
    public void login(@RequestParam(required = true, value = "code") String wxCode, HttpSession session,
                      HttpServletResponse response) {
        logger.info("login session id ={}", session.getId());
        logger.info("login code:" + wxCode);
        String sessionKey = null;
        Map<String, String> result = new HashMap<String, String>();
        try {
            Map<String, Object> wxSession = WeixinUtil.getWxSession(wxCode);
            if (null == wxSession) {
                result.put("error", "wxSession == null");
                renderString(response, result);
                return;
            }
            if (wxSession.containsKey("errcode")) {
                result.put("error", "errcode!=null");
                renderString(response, result);
                return;
            }
            String wxOpenId = (String) wxSession.get("openid");
            String wxSessionKey = (String) wxSession.get("session_key");
            StringBuffer sb = new StringBuffer();
            sb.append(wxSessionKey).append("#").append(wxOpenId);
            sessionKey = sb.toString();
            String key3rd = UUID.randomUUID().toString();
            session.setAttribute(key3rd, sessionKey);
            result.put("sessionId", session.getId());
            result.put(KEY_3_RD, key3rd);
            renderString(response, result);
        } catch (IOException e) {
            logger.error("failed to get wxSession", e);
            result.put("error", "failed to get wxSession");
            renderString(response, result);
            return;
        }
    }

    @RequestMapping(value = "/user")
    public void getUserInfo(@RequestParam(required = true, defaultValue = "key3rd") String key3rd,
                             String nickName,String avatarUrl,Integer gender,
                            String country,String province,String city,
                            HttpSession session,
                            HttpServletResponse response
                           ) {
        Map<String, String> result = new HashMap<String, String>();
        String openId = SessionUtils.getOpenId(session,key3rd);
        if (openId == null) {
            result.put("error", "sessionKey == null");
            renderString(response, result);
            logger.error("Failed to get sessionKey");
            return;
        }
        BplUser bplUser = new BplUser(  nickName, avatarUrl, gender,
                 country, province, city) ;
        bplUser.setOpenid(openId);
        Date time = new Date();
        bplUser.setCreateTime(time);
        bplUser.setUpdateDate(time);
        logger.info("userInfo:" + bplUser);
        session.setAttribute("userInfo", bplUserService.saveOrget(bplUser));
        result.put("sessionId", session.getId());
        result.put("key3rd", key3rd);
        logger.info("success to get result");
        renderString(response, result);
    }

    @RequestMapping(value = "/test")
    //@ResponseBody
    public void test(HttpServletResponse response) {
        Map<String, String> result = new HashMap<String, String>();
        result.put("sessionId", "success");
        renderString(response, result);
    }

}
