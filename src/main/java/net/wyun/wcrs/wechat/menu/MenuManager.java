package net.wyun.wcrs.wechat.menu;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.wyun.wcrs.wechat.CommonUtil;


/**
 * 菜单管理器类
 * 
 * @author qikuo
 * @date 2017-2-28
 */
public class MenuManager {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);
	
	private final static String REDIRECT_URL = "http://www.mianshijilu.com/wechat/oauth";

	/**
	 * 定义菜单结构
	 * 
	 * @return
	 */
	//人才库
	public static Menu getMenu() {
		//按钮1
		ViewButton btn11 = new ViewButton();
		btn11.setName("招聘登记");
		btn11.setType("view");
		btn11.setUrl("https://jinshuju.net/f/tl21JZ");
	
		ViewButton btn12 = new ViewButton();
		btn12.setName("代猎人才");
		btn12.setType("view");
		btn12.setUrl("https://jinshuju.net/f/OAOrtf");
		
		ViewButton btn13 = new ViewButton();
		btn13.setName("简历超市");
		btn13.setType("view");
		btn13.setUrl("http://shop13308654.ddkwxd.com/tag/231285");
		
		//按钮2
		ViewButton btn21 = new ViewButton();
		btn21.setName("求职登记");
		btn21.setType("view");
		btn21.setUrl("https://jinshuju.net/f/j3iabB");
		
		ViewButton btn22 = new ViewButton();
		btn22.setName("代查岗位");
		btn22.setType("view");
		btn22.setUrl("https://jinshuju.net/f/d4unTY");
		
		ViewButton btn23 = new ViewButton();
		btn23.setName("招聘信息");
		btn23.setType("view");
		btn23.setUrl("http://shop13308654.ddkwxd.com/tag/231300");
		
		//String appid = "wx179e17d128a005d0"; //qikuo
		String appId = "wx479cc0c5b93538df";
		//按钮3
		//String reurl= "http://59.110.113.110/wechat/oauth";
		//reurl= "http://zplsyx.iok.la/weixin3/oa.do";
		
		ViewButton btn3 = getTuiGuangJiaMengBtn(REDIRECT_URL, appId);
		
		///weixin3/WebContent/JSP/tuiguang.jsp
		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("招聘入口");
		mainBtn1.setSub_button(new Button[] { btn11, btn12, btn13 });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("求职入口");
		mainBtn2.setSub_button(new Button[] { btn21, btn22, btn23 });

		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2, btn3 });
		
		return menu;
	}
	//企管沙龙
	public static Menu getMenu_qgsl() {
		//按钮1
		ViewButton btn1 = new ViewButton();
		btn1.setName("活动");
		btn1.setType("view");
		btn1.setUrl("https://jinshuju.net/f/m8KFIy");
		//按钮2
		ViewButton btn2 = new ViewButton();
		btn2.setName("咨询");
		btn2.setType("view");
		btn2.setUrl("https://jinshuju.net/f/6rO9Gt");
		
		//按钮3
		String reurl= "http://zplsyx.iok.la/weixin3/oa.do";
		String reurls = CommonUtil.urlEncodeUTF8(reurl);
		ViewButton btn3 = new ViewButton();
		btn3.setName("推广加盟");
		btn3.setType("view");
//		btn3.setUrl("http://zplsyx.iok.la/weixin3/JSP/tuiguang.jsp");
		btn3.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx179e17d128a005d0&redirect_uri="+reurls+"&response_type=code&scope=snsapi_userinfo&state=123#wechat_redire");
		///weixin3/WebContent/JSP/tuiguang.jsp
		
		Menu menu = new Menu();
		menu.setButton(new Button[] { btn1,btn2,btn3 });
		
		return menu;
	}
	
	public static Menu getMenu_qhdrck() {
		//按钮1
		ViewButton btn11 = new ViewButton();
		btn11.setName("招聘登记");
		btn11.setType("view");
		btn11.setUrl("https://jinshuju.net/f/tl21JZ");
	
		ViewButton btn12 = new ViewButton();
		btn12.setName("代猎人才");
		btn12.setType("view");
		btn12.setUrl("https://jinshuju.net/f/OAOrtf");
		
		ViewButton btn13 = new ViewButton();
		btn13.setName("简历超市");
		btn13.setType("view");
		btn13.setUrl("http://shop13308654.ddkwxd.com/tag/231285");
		
		//按钮2
		ViewButton btn21 = new ViewButton();
		btn21.setName("求职登记");
		btn21.setType("view");
		btn21.setUrl("https://jinshuju.net/f/j3iabB");
		
		ViewButton btn22 = new ViewButton();
		btn22.setName("代查岗位");
		btn22.setType("view");
		btn22.setUrl("https://jinshuju.net/f/d4unTY");
		
		ViewButton btn23 = new ViewButton();
		btn23.setName("招聘信息");
		btn23.setType("view");
		btn23.setUrl("http://shop13308654.ddkwxd.com/tag/231300");
		
		//String appid = "wx179e17d128a005d0"; //qikuo
		String appId = "wx67e331a601742a07";
		//按钮3
		//String reurl= "http://59.110.113.110/wechat/oauth";
		//reurl= "http://zplsyx.iok.la/weixin3/oa.do";
		
		ViewButton btn3 = getTuiGuangJiaMengBtn(REDIRECT_URL, appId);
		
		///weixin3/WebContent/JSP/tuiguang.jsp
		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("招聘入口");
		mainBtn1.setSub_button(new Button[] { btn11, btn12, btn13 });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("求职入口");
		mainBtn2.setSub_button(new Button[] { btn21, btn22, btn23 });

		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2, btn3 });
		
		return menu;
	}
	
	public static ViewButton getTuiGuangJiaMengBtn(String redirectUrl, String appId){
		String encodedUrl = CommonUtil.urlEncodeUTF8(redirectUrl);
		//String re_direct = "http://59.110.113.110/wechat/get-weixin-code.html?appid=" + appId + "&scope=snsapi_base&state=123&redirect_uri=" + reurls;
		ViewButton btn = new ViewButton();
		btn.setName("推广加盟");
		btn.setType("view");
		
        //btn3.setUrl("http://zplsyx.iok.la/weixin3/JSP/tuiguang.jsp");
		btn.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appId +
				"&redirect_uri=" + encodedUrl + "&response_type=code&scope=snsapi_userinfo&state=" + 
				appId + "#wechat_redirect");
		
		return btn;
		
	}
	
}
