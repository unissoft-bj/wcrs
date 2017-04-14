/**
 * 
 */
package net.wyun.wcrs.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.wyun.wcrs.model.Affiliate;
import net.wyun.wcrs.model.Product;
import net.wyun.wcrs.model.repo.AffiliateRepository;
import net.wyun.wcrs.model.repo.ProductRepository;

/**
 * @author michael
 *
 */
@CrossOrigin
@RequestMapping("/secure")
@RestController
public class AffiliateController {
	
private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	AffiliateRepository affiliateRepo;
	
	@RequestMapping(value= "/affiliate", method=RequestMethod.POST)
	Affiliate saveProduct(@RequestBody Affiliate p){
		p.setCreateT(new Date());
		return affiliateRepo.save(p);
	}
	
	@RequestMapping(value= "/affiliate", method=RequestMethod.GET)
	Iterable<Affiliate> getProducts(){
		Iterable<Affiliate> itP = affiliateRepo.findAll();
		return itP;
	}

}
