/**
 * 
 */
package net.wyun.wcrs.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.wyun.wcrs.model.Affiliate;
import net.wyun.wcrs.model.Billing;
import net.wyun.wcrs.model.Product;
import net.wyun.wcrs.model.WCUser;
import net.wyun.wcrs.model.WCUserProduct;
import net.wyun.wcrs.model.repo.AffiliateRepository;
import net.wyun.wcrs.model.repo.BillingRepository;
import net.wyun.wcrs.model.repo.ProductRepository;
import net.wyun.wcrs.model.repo.WCUserProductRepository;
import net.wyun.wcrs.model.repo.WCUserRepository;

/**
 * @author michael
 *
 */
@CrossOrigin
@RequestMapping("/secure")
@RestController
public class WCUserProductController {
	
private static final Logger logger = LoggerFactory.getLogger(WCUserProductController.class);
	
	@Autowired
	WCUserProductRepository userProductRepo;
	
	@Autowired
	WCUserRepository userRepo;
	
	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	AffiliateRepository affiliateRepo;
	
	@Autowired
	BillingRepository billingRepo;
	
	@RequestMapping(value= "/userproduct", method=RequestMethod.POST)
	WCUserProduct saveProduct(@RequestBody WCUserProduct up){
		up.setCreateT(new Date());
		WCUserProduct userP = userProductRepo.save(up);
		Map<Integer, String> distMap = distribute(userP);
		
		return userP;
	}
	
	@RequestMapping(value= "/userproduct/uid/{unionid}", method=RequestMethod.GET)
	List<WCUserProduct> retrieveUserProduct(@PathVariable("unionid") String unionid){
		return userProductRepo.findByUnionId(unionid);
	}
	
	@RequestMapping(value= "/userproduct/bonus/{oid}", method=RequestMethod.GET)
	String getUserBonus(@PathVariable("oid") String openId){
		return "$25.88";
	}
	
	private Map<Integer, String> distribute(WCUserProduct up){
		Map<Integer, String> distMap = new HashMap<Integer, String>();
		
		String unionId = up.getUnionId();
		WCUser u = userRepo.findOne(unionId);
		
		Long productId = up.getProductId();
		Product p = productRepo.findOne(productId);
		List<Affiliate> la = affiliateRepo.findByProduct(p);
		
		
		int currentLevel = 1;
		distMap.put(currentLevel, unionId);
		Affiliate root = findRoot(la, unionId);
		if(null != root){
			
		}else{
			
			while(u.getParent() != null){
				// parent
				currentLevel++;
				String p_unionId = u.getParent();
				root = findRoot(la, p_unionId);
				if(null != root){
					logger.info("current level in searching root: {} and root is {}", currentLevel, p_unionId);
					distMap.put(4, p_unionId);
					break;
				}
				
				if(currentLevel <= 3){
					distMap.put(currentLevel, p_unionId);
				}
				u = userRepo.findOne(p_unionId);
			}
		}
			
		process(distMap, root, up);
		
		return distMap;
		
		
	}
	
	Affiliate findRoot(List<Affiliate> la, String unionId){
		for(Affiliate af:la){
			if(af.getAffNodeId().equals(unionId)) return af;
		}
		
		return null;
	}
	
	private void process(Map<Integer, String> distMap, Affiliate root, WCUserProduct up) {
		BigDecimal OneHundred = new BigDecimal(100);
		Set<Integer> keys =distMap.keySet();
		if(keys.size() == 1){  // 1, root takes all
			Integer percent = root.getFirst() + root.getSecond() + root.getThird() + root.getAffNodeP();
			BigDecimal amount = up.getAmount().multiply(new BigDecimal(percent)).divide(OneHundred);
			Billing b = new Billing(root.getAffNodeId(), up.getId(), percent, amount, new Date());
			billingRepo.save(b);
		}
		
		if(keys.size() == 2){  // 1, 4, root takes 2, 3, and 4
			Integer percent = root.getFirst();
			BigDecimal amount = up.getAmount().multiply(new BigDecimal(percent)).divide(OneHundred);
			Billing fB = new Billing(distMap.get(1), up.getId(), percent, amount, new Date());
			billingRepo.save(fB);
			
			percent = root.getSecond() + root.getThird() + root.getAffNodeP();
			amount = up.getAmount().multiply(new BigDecimal(percent)).divide(OneHundred);
			Billing b = new Billing(root.getAffNodeId(), up.getId(), percent, amount, new Date());
			billingRepo.save(b);
		}
		
		if(keys.size() == 3){  // 1, 2, 4, root takes  3, and 4
			Integer percent = root.getFirst();
			BigDecimal amount = up.getAmount().multiply(new BigDecimal(percent)).divide(OneHundred);
			Billing fB = new Billing(distMap.get(1), up.getId(), percent, amount, new Date());
			billingRepo.save(fB);
			
			percent = root.getSecond();
			amount = up.getAmount().multiply(new BigDecimal(percent)).divide(OneHundred);
			Billing sB = new Billing(distMap.get(2), up.getId(), percent, amount, new Date());
			billingRepo.save(sB);
			
			percent = root.getThird() + root.getAffNodeP();
			amount = up.getAmount().multiply(new BigDecimal(percent)).divide(OneHundred);
			Billing b = new Billing(root.getAffNodeId(), up.getId(), percent, amount, new Date());
			billingRepo.save(b);
		}
		
		if(keys.size() == 4){  // 1, 2, 3, 4, root takes 4
			Integer percent = root.getFirst();
			BigDecimal amount = up.getAmount().multiply(new BigDecimal(percent)).divide(OneHundred);
			Billing fB = new Billing(distMap.get(1), up.getId(), percent, amount, new Date());
			billingRepo.save(fB);
			
			percent = root.getSecond();
			amount = up.getAmount().multiply(new BigDecimal(percent)).divide(OneHundred);
			Billing sB = new Billing(distMap.get(2), up.getId(), percent, amount, new Date());
			billingRepo.save(sB);
			
			percent = root.getThird();
			amount = up.getAmount().multiply(new BigDecimal(percent)).divide(OneHundred);
			Billing tB = new Billing(distMap.get(3), up.getId(), percent, amount, new Date());
			billingRepo.save(tB);
			
			percent = root.getAffNodeP();
			amount = up.getAmount().multiply(new BigDecimal(percent)).divide(OneHundred);
			Billing b = new Billing(root.getAffNodeId(), up.getId(), percent, amount, new Date());
			billingRepo.save(b);
		}
		
		
	}

}
