/**
 * 
 */
package net.wyun.wcrs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.wyun.wcrs.model.PAUser;
import net.wyun.wcrs.model.WCUser;
import net.wyun.wcrs.model.mlm.Node;
import net.wyun.wcrs.model.repo.PAUserRepository;
import net.wyun.wcrs.model.repo.WCUserRepository;

/**
 * @author Xuecheng
 *
 */
@CrossOrigin
@RequestMapping("/secure")
@RestController
public class WCUserController {
	
	private static final Logger logger = LoggerFactory.getLogger(WCUserController.class);
	
	@Autowired
	WCUserRepository userRepo;
	
	@Autowired
	PAUserRepository paUserRepo;
	
	@RequestMapping(value= "/wcuser", method=RequestMethod.POST)
	WCUser saveUser(@RequestBody WCUser user){
		return userRepo.save(user);
	}
	
	@RequestMapping(value= "/wcuser", method=RequestMethod.GET)
	PAUser getUser(HttpServletRequest request){
		logger.info("OAuth wechat --> get oid from session");
		HttpSession session=request.getSession();  
        String oid = (String) session.getAttribute("openId"); 
        logger.info("my openid is {}", oid);
        return this.getUser(oid);
	}
	
	@RequestMapping(value= "/wcuser/{oid}", method=RequestMethod.GET)
	PAUser getUser(@PathVariable("oid") String openId){
		PAUser paU = paUserRepo.findByOpenId(openId);
		return paU;
	}
	
	
	@RequestMapping(value= "/wcuser/phone/{phoneNum}", method=RequestMethod.GET)
	WCUser getUserByPhone(@PathVariable("phoneNum") String phoneNum){
		return userRepo.findByPhone(phoneNum);
	}
	
	@RequestMapping(value= "/wcuser/children/{unionId}", method=RequestMethod.GET)
	Node<WCUser> getChildren(@PathVariable("unionId") String unionId){
		WCUser u = userRepo.findOne(unionId);
		Node<WCUser> nodeU = new Node<WCUser>(u);
		this.addChildren(nodeU);
		
		//second level
		List<Node<WCUser>> children = nodeU.getChildren();
		for(Node<WCUser> child:children){
			this.addChildren(child);
		}
		return nodeU;
	}
	
	private void addChildren(Node<WCUser> nu){
		
		String uid = nu.getData().getUnionId();
		List<WCUser> firstCh = userRepo.findByParent(uid);
		nu.addChild(firstCh);
		
	}
	
}
