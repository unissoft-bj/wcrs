/**
 * 
 */
package net.wyun.wcrs.controller;

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
import net.wyun.wcrs.model.User;
import net.wyun.wcrs.model.repo.PAUserRepository;
import net.wyun.wcrs.model.repo.UserRepository;

/**
 * @author Xuecheng
 *
 */
@CrossOrigin
@RequestMapping("/secure")
@RestController
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	PAUserRepository paUserRepo;
	
	@RequestMapping(value= "/user", method=RequestMethod.POST)
	User saveUser(@RequestBody User user){
		
		return userRepo.save(user);
	}
	
	@RequestMapping(value= "/user/{oid}", method=RequestMethod.GET)
	PAUser getUser(@PathVariable("oid") String openId){
		PAUser paU = paUserRepo.findByOpenId(openId);
		return paU;
	}
	
	
	@RequestMapping(value= "/user/phone/{phoneNum}", method=RequestMethod.GET)
	User getUserByPhone(@PathVariable("phoneNum") String phoneNum){
		return userRepo.findByPhone(phoneNum);
	}
	
}
