/**
 * 
 */
package net.wyun.wcrs.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.wyun.wcrs.model.Product;
import net.wyun.wcrs.model.ProductRepository;
import net.wyun.wcrs.model.UserProduct;
import net.wyun.wcrs.model.UserProductRepository;

/**
 * @author michael
 *
 */
@CrossOrigin
@RequestMapping("/secure")
@RestController
public class UserProductController {
	
private static final Logger logger = LoggerFactory.getLogger(UserProductController.class);
	
	@Autowired
	UserProductRepository userProductRepo;
	
	@RequestMapping(value= "/userproduct", method=RequestMethod.POST)
	UserProduct saveProduct(@RequestBody UserProduct up){
		up.setCreateT(new Date());
		return userProductRepo.save(up);
	}
	
	@RequestMapping(value= "/userproduct/uid/{unionid}", method=RequestMethod.GET)
	List<UserProduct> retrieveUserProduct(@PathVariable("unionid") String unionid){
		return userProductRepo.findByUnionId(unionid);
	}

}
