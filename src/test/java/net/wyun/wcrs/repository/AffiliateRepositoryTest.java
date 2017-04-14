package net.wyun.wcrs.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import net.wyun.wcrs.BaseSpringTestRunner;
import net.wyun.wcrs.model.Affiliate;
import net.wyun.wcrs.model.Product;
import net.wyun.wcrs.model.repo.AffiliateRepository;
import net.wyun.wcrs.model.repo.ProductRepository;

public class AffiliateRepositoryTest extends BaseSpringTestRunner{
	
	@Autowired
	private AffiliateRepository affiliateRepository;
	
	@Autowired
	ProductRepository productRepo;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Affiliate af = new Affiliate();
		af.setAffNodeId("o6_bmasdasdsad6_2sgVt7hMZOPfL");
		af.setDescription("first description for test");
		af.setAffNodeName("test99");
		af.setFirst(6);
		af.setSecond(4);
		af.setThird(2);
		af.setAffNodeP(10);
		
		Product product = new Product();
		product.setDescription("affiliate test");
		product.setExpired(false);
		product.setpType("test");
		
		product = productRepo.save(product);
		Product p = productRepo.findOne(2L);
		af.setProduct(product);
		
		
		
		affiliateRepository.save(af);
	}
	
	@Test
	public void getProduct(){
		Product p = productRepo.findOne(5L);
		List<Affiliate> la = p.getAffiliates();
		for( Affiliate a:la){
			System.out.println("aff node name: " + a.getAffNodeName());
		}
	}

}
