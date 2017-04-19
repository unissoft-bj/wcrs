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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.wyun.wcrs.model.Product;
import net.wyun.wcrs.model.repo.ProductRepository;

/**
 * @author michael
 *
 */
@CrossOrigin
@RequestMapping("/secure")
@RestController
public class ProductController {
	
private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	ProductRepository productRepo;
	
	@RequestMapping(value= "/product", method=RequestMethod.POST)
	Product saveProduct(@RequestBody Product p){
		p.setCreateT(new Date());
		return productRepo.save(p);
	}
	
	@RequestMapping(value= "/product", method=RequestMethod.GET)
	List<Product> getProducts(){
		Iterable<Product> itP = productRepo.findAll();
		List<Product> target = new ArrayList<Product>();
		//itP.forEach(target::add);
		for(Product p:itP){
			target.add(p);
		}
		return target;
	}
	
	@RequestMapping(value= "/product/{pid}", method=RequestMethod.GET)
	Product getProduct(@PathVariable("pid") Long pid){
		
		return productRepo.findOne(pid);
	}

}
