/**
 * 
 */
package net.wyun.wcrs.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.annotation.PostConstruct;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import net.wyun.wcrs.config.WechatProperties;
import net.wyun.wcrs.model.PublicAccount;
import net.wyun.wcrs.model.repo.PublicAccountRepository;
import net.wyun.wcrs.wechat.CommonUtil;
import net.wyun.wcrs.wechat.menu.MenuManager;
import net.wyun.wcrs.wechat.menu.MenuUtil;
import net.wyun.wcrs.wechat.po.Token;

/**
 * @author michael
 *
 */
@Component
@EnableScheduling
public class TokenServiceImpl implements TokenService {

	private static final Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);
	
	@Autowired
	private WechatProperties wcrsProperties;
	
	ConcurrentMap<String, Token> paTokenMap = new ConcurrentHashMap<String, Token>();
	Iterable<PublicAccount> publicAccounts;

	
	@Autowired
	PublicAccountRepository paRepo;
	
	
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	
	
	@Scheduled(fixedDelayString = "${wechat.token.update.interval}")
	public void updateToken() {
		
		logger.info("### [Token Svc] on " + dateFormat.format(new Date()));
		for(PublicAccount pa:publicAccounts){
			Token token = CommonUtil.getToken(pa.getAppId(), pa.getAppSecret());
			this.paTokenMap.put(pa.getPaId(), token);
		}
		
		
	}
	
	private static long ONE_MINUTE = 60 * 1000;
	
	@PostConstruct
    public void init() {
		logger.info("token service: initialising ...");
		//CommonUtil.APPID = wcrsProperties.getAppId();
		//CommonUtil.APPSECRET = wcrsProperties.getAppSecret();
		publicAccounts = this.paRepo.findAll();
		for(PublicAccount pa:publicAccounts){
			logger.info("public account {} with id/key: {}/{}", pa.getPaId(), pa.getAppId(), pa.getAppSecret());
		}
		this.updateToken();
		
		//create menu
		this.createMenuRecruiting();
		createMenuSalon();
		
		logger.info("token service loaded successfully! ");
	}

	@Override
	public Token getToken(String paId) {
		return paTokenMap.get(paId);
	}
	
	private void createMenuRecruiting() {
		String paId = "gh_d698ab97cae6";
		paId = "gh_d7901caa4f9b";
		Token token = this.getToken(paId);
		logger.info("token: {}", token.getAccessToken());
		// 第三方用户唯一凭证
		//String appId = CommonUtil.APPID;
		// 第三方用户唯一凭证密钥
		//String appSecret = CommonUtil.APPSECRET;
		
		// 调用接口获取凭证
		//Token token = CommonUtil.getToken(appId, appSecret);
		if (null != token) {
			// 创建菜单
			boolean result = MenuUtil.createMenu(MenuManager.getMenu(), token.getAccessToken());
			// 判断菜单创建结果
			if (result)
				//log.info("菜单创建成功！");
				logger.info("Public account {} ===> Recruiting -- 菜单创建成功!", paId);
			else
				//log.info("菜单创建失败！");
				logger.error("Recruiting -- 创建失败!");
		}
		
    }
	
	private void createMenuSalon() {
		String paId = "gh_39bf79a08057";
		Token token = this.getToken(paId);
		// 第三方用户唯一凭证
		//String appId = CommonUtil.APPID;
		// 第三方用户唯一凭证密钥
		//String appSecret = CommonUtil.APPSECRET;
		
		// 调用接口获取凭证
		//Token token = CommonUtil.getToken(appId, appSecret);
		if (null != token) {
			// 创建菜单
			boolean result = MenuUtil.createMenu(MenuManager.getMenu_qgsl(), token.getAccessToken());
			// 判断菜单创建结果
			if (result)
				//log.info("菜单创建成功！");
				logger.info("Public account {} ===> Salon -- 菜单创建成功!", paId);
			else
				//log.info("菜单创建失败！");
				logger.error("Salon -- 创建失败!");
		}
		
    }

	
}