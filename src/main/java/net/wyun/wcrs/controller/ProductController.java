/**
 * 
 */
package net.wyun.wcrs.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.wyun.wcrs.model.Product;
import net.wyun.wcrs.model.ProductRepository;

/**
 * @author michael
 *
 */
@CrossOrigin
@RequestMapping("/secure")
@RestController
public class ProductController {
	
private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	ProductRepository productRepo;
	
	@RequestMapping(value= "/product", method=RequestMethod.POST)
	Product saveProduct(@RequestBody Product p){
		p.setCreateT(new Date());
		return productRepo.save(p);
	}

}
