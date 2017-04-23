package net.wyun.wcrs.repository;


import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import net.wyun.wcrs.BaseSpringTestRunner;
import net.wyun.wcrs.model.WCUserStatus;
import net.wyun.wcrs.model.repo.PAUserPersistor;
import net.wyun.wcrs.model.repo.PAUserRepository;
import net.wyun.wcrs.model.repo.PublicAccountRepository;
import net.wyun.wcrs.model.repo.WCUserRepository;
import net.wyun.wcrs.model.Gender;
import net.wyun.wcrs.model.PAUser;
import net.wyun.wcrs.model.PublicAccount;
import net.wyun.wcrs.model.WCUser;


public class PAUserRepositoryTest extends BaseSpringTestRunner {
	
	
	
	@Autowired
	private PAUserRepository paUserRepository;
	
	@Autowired
	private PAUserPersistor paUserPersistor;
	
	
	@Autowired
	private WCUserRepository wcUserRepository;
	
	@Autowired
	PublicAccountRepository paRepo;
	
	
	static String testPhone = "1869991888";
	
	WCUser wcUser;
	
	@Before
	public void setUp() throws Exception {
		wcUser = saveOrUpdateWCUser();
	}

	@After
	public void tearDown() throws Exception {
		PAUser paU = paUserRepository.findByOpenId(openId);
		if(null != paU){
			//paUserRepository.delete(paU);
		}
	}
	
	/**
	 * this test's precondition
	 * One WCUser is already created for PAUser user1
	 * We need create another PAUser user2 share the same WCUser with User1.
	 */
	
	@Test
	public void saveNewPAUser(){
		
		PAUser paU = this.createPAUser();
		wcUser = wcUserRepository.findOne(unionId);
		paU.setUser(wcUser);
		//paUserPersistor.persist(paU, wcUser);
		//paUserPersistor.createPAUserWithExistingWCUser(paU);
		
		paU = paUserRepository.save(paU);
		//paU.setUser(wcUser);
		//paU = paUserRepository.save(paU);
		//wcUser.addPAUser(paU);
		
		//wcUserRepository.save(wcUser);
		
	}
	
	String unionId = "4fa1be55-1311-4782-b235-1b012a0de9b2";
	public WCUser saveOrUpdateWCUser(){
		//String openId = "ff8081814da00e2b014da00f32260002";
		
		WCUser o = createWCUser(unionId, testPhone);
		return wcUserRepository.saveAndFlush(o);
	}
	
	private static final String openId = "4fa1be55-1311-4782-b235-1pautest";
	private PAUser createPAUser(){
		PAUser paU = new PAUser();
		Iterable<PublicAccount>  pas = paRepo.findAll();
		List<PublicAccount> lpa = new ArrayList<PublicAccount>();
		for(PublicAccount pa:pas){
			lpa.add(pa);
		}
		assertTrue(!lpa.isEmpty());
		
		
		paU.setOpenId(openId);
		paU.setPaId(lpa.get(0).getPaId());
		paU.setSceneID(0);
		paU.setCreatet(new Date());
		
		return paU;
	}
	
	
	
	public WCUser createWCUser(String unionId, String phone){
		WCUser o = new WCUser();
		
		o.setNickName("test");
		o.setGender(Gender.MALE);
		o.setLanguage("en");
		o.setCity("北京");
		o.setProvince("上海");
		o.setCountry("China");
		o.setHeadimgurl("/head/image/test");
		o.setCreatet(new Date());
		o.setStatus(WCUserStatus.REGISTERED);
		o.setUnionId(unionId);
		o.setPhone(phone);
		return o;
		
	}
	
}
