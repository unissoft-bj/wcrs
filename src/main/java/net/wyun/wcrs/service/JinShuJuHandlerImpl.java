/**
 * 
 */
package net.wyun.wcrs.service;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.wyun.wcrs.controller.WechatController;
import net.wyun.wcrs.model.PAUser;
import net.wyun.wcrs.model.WCUser;
import net.wyun.wcrs.model.WCUserStatus;
import net.wyun.wcrs.model.repo.PAUserRepository;
import net.wyun.wcrs.model.repo.WCUserRepository;
import net.wyun.wcrs.wechat.AdvancedUtil;

/**
 * @author michael
 *
 */
@Component
public class JinShuJuHandlerImpl implements JinShuJuHandler{

	private static final Logger logger = LoggerFactory.getLogger(JinShuJuHandlerImpl.class);
	
	@Autowired
	WCUserRepository userRepo;
	
	@Autowired
	PAUserRepository paUserRepo;
	
	@Autowired
	SceneIdService sceneIdService;
	
	@Autowired
	TokenService tokenService;
	
	private ExecutorService executor = Executors.newFixedThreadPool(2);
	
		
	public class Handler implements Runnable{

	    protected String oid = null;
	    protected String phone = null;

	    public Handler(String oid, String phone) {
	        this.oid = oid;
	        this.phone = phone;
	    }

	    public void run() {
	        try {
	        	PAUser pau = paUserRepo.findByOpenId(oid);
	    		if(null == pau){
	    			throw new RuntimeException("user not exist for oid: " + oid);
	    		}
	    		String paId = pau.getPaId();
	    		if( pau.getSceneID() == 0){
	    			
	    			int s_id = sceneIdService.nextSceneID(paId);
	    			pau.setSceneID(s_id);
	    			
	    			//get QR code
	    			String accessToken = tokenService.getToken(paId).getAccessToken();
	    			String ticket = AdvancedUtil.createPermanentQRCode(accessToken, s_id);
	    			
	    			pau.setTicket(ticket);
	    		}
	    		
	    		WCUser u = pau.getUser();
	    		u.setPhone(phone);
	    		u.setStatus(WCUserStatus.REGISTERED);
	    		u.setModify_t(new Date());
	    		pau.setModify_t(new Date());
	    		
	    		updateUser(u, pau);
	    		//userRepo.save(pau);
	        	
	        } catch (Exception e) {
	            logger.error("", e);
	        }
	    }
	}

	@Override
	public void handle(String openId, String phone) {
		logger.info("process: oid -- {}, phone -- {}", openId, phone);
		executor.execute(new Handler(openId, phone));
	}
	
	@Transactional
	private void updateUser(WCUser u, PAUser pau){
		userRepo.save(u);
		paUserRepo.save(pau);
	}

}
