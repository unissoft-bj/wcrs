package net.wyun.wcrs.wechat.menu;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import net.wyun.wcrs.wechat.CommonUtil;
import net.wyun.wcrs.wechat.po.Token;

public class MenuManagerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateMenu() {
			// 第三方用户唯一凭证
			String appId = CommonUtil.APPID;
			// 第三方用户唯一凭证密钥
			String appSecret = CommonUtil.APPSECRET;
			
			// 调用接口获取凭证
			Token token = CommonUtil.getToken(appId, appSecret);
			if (null != token) {
				// 创建菜单
				boolean result = MenuUtil.createMenu(MenuManager.getMenu(), token.getAccessToken());
				// 判断菜单创建结果
				if (result)
					//log.info("菜单创建成功！");
					System.out.println("菜单创建成功1");
				else
					//log.info("菜单创建失败！");
					System.out.println("创建失败1");
			}
			
	}

}
