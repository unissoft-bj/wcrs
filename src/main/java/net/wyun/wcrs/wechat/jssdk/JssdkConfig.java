/**
 * 
 */
package net.wyun.wcrs.wechat.jssdk;

import java.security.MessageDigest;
import java.util.Formatter;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.wyun.wcrs.model.PublicAccount;
import net.wyun.wcrs.model.repo.PublicAccountRepository;
import net.wyun.wcrs.service.TokenService;

/**
 * @author michael
 * 
 *         /** ClassName: JSSDK_Config
 * @Description: 用户微信前端页面的jssdk配置使用
 * @author dapengniao
 * @date 2016年3月19日 下午3:53:23
 *
 */
@Component
public class JssdkConfig {
	
	@Autowired
	TokenService tokenService;
	
	@Autowired
	PublicAccountRepository paRepo;

	/**
	 * @Description: 前端jssdk页面配置需要用到的配置参数
	 * @param @return
	 *            hashmap {appid,timestamp,nonceStr,signature}
	 * @param @throws
	 *            Exception
	 *  http://blog.csdn.net/gif_t_t/article/details/50774523          
	 * @author dapengniao
	 * @date 2016年3月19日 下午3:53:23
	 */
	public HashMap<String, String> jsSDK_Sign(String url, String paId) throws Exception {
		String nonce_str = create_nonce_str();
		JSTicket ticket = tokenService.getTicket(paId);
		
		String timestamp = createTimestamp();
		String jsapi_ticket = ticket.getTicket();
		// 注意这里参数名必须全部小写，且必须有序
		String string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url="
				+ url;
		MessageDigest crypt = MessageDigest.getInstance("SHA-1");
		crypt.reset();
		crypt.update(string1.getBytes("UTF-8"));
		String signature = byteToHex(crypt.digest());
		HashMap<String, String> jssdk = new HashMap<String, String>();
		
		PublicAccount pa = paRepo.findOne(paId);
		String appId = pa.getAppId();
		jssdk.put("appId", appId);
		jssdk.put("url", url);
		jssdk.put("jsapi_ticket", jsapi_ticket);
		jssdk.put("timestamp", timestamp);
		jssdk.put("nonceStr", nonce_str);
		jssdk.put("signature", signature);
		return jssdk;

	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}
	
	private static String createTimestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

	private static String create_nonce_str() {
		return UUID.randomUUID().toString();
	}

}