/**
 * 
 */
package net.wyun.wcrs.controller.wechat;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import net.wyun.wcrs.jsj.JSJFormInfo;
import net.wyun.wcrs.model.Gender;
import net.wyun.wcrs.model.PAUser;
import net.wyun.wcrs.model.User;
import net.wyun.wcrs.model.UserStatus;
import net.wyun.wcrs.model.WechatEvent;
import net.wyun.wcrs.model.repo.PAUserRepository;
import net.wyun.wcrs.model.repo.UserRepository;
import net.wyun.wcrs.model.repo.WechatEventRepository;
import net.wyun.wcrs.service.TokenService;
import net.wyun.wcrs.wechat.AdvancedUtil;
import net.wyun.wcrs.wechat.CommonUtil;
import net.wyun.wcrs.wechat.MessageUtil;
import net.wyun.wcrs.wechat.SignUtil;
import net.wyun.wcrs.wechat.message.event.QRCodeEvent;
import net.wyun.wcrs.wechat.message.req.ReqTextMessage;
import net.wyun.wcrs.wechat.message.resp.RespTextMessage;
import net.wyun.wcrs.wechat.po.SNSUserInfo;
import net.wyun.wcrs.wechat.po.WeixinOauth2Token;
import net.wyun.wcrs.wechat.po.WeixinUserInfo;

/**
 * @author Xuecheng
 *
 */
@RequestMapping("/wechat")
@RestController
public class OAuthController {
	
	private static final Logger logger = LoggerFactory.getLogger(OAuthController.class);
	
	@Autowired
	PAUserRepository paUserRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	WechatEventRepository weRepo;
	
	@Autowired
	TokenService tokenService;
	
	@RequestMapping(value= "/oauth", method=RequestMethod.GET)
	String auth(/*@RequestBody String data, */ HttpServletRequest request){
		logger.info("OAuth wechat");
		
		String code = request.getParameter("code");
        String state = request.getParameter("state");
        logger.info("code {} and state {}", code, state);
        if (!"authdeny".equals(code)) {
            // access_token
            WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken("wx479cc0c5b93538df", "9cef9bc43e6ab4a2feedbfd3bf5b1dff", code);
            String accessToken = weixinOauth2Token.getAccessToken();
            String openId = weixinOauth2Token.getOpenId();
            SNSUserInfo snsUserInfo = AdvancedUtil.getSNSUserInfo(accessToken, openId);
            
            logger.info("openId {}", openId);
            request.setAttribute("openId", openId);
            request.setAttribute("snsUserInfo", snsUserInfo);
            request.setAttribute("state", state);
        }
		
		return "tuiguang";
	}
	
	
	
	@RequestMapping(value= "/wechat", method=RequestMethod.GET)
	void handShake(HttpServletRequest request, HttpServletResponse response) throws IOException{
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");
		
		logger.debug("signature, timestamp, echostr: " + signature + ", " + timestamp + ", " + echostr);

		PrintWriter out = response.getWriter();
		// 请求校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}else{
			logger.info("signature check fails!" + request.getRemoteHost());
		}
		out.close();
		out = null;

	}

}
