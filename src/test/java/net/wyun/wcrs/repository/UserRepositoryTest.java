package net.wyun.wcrs.repository;


import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import net.wyun.wcrs.BaseSpringTestRunner;
import net.wyun.wcrs.model.UserStatus;
import net.wyun.wcrs.model.repo.UserRepository;
import net.wyun.wcrs.model.Gender;
import net.wyun.wcrs.model.User;


public class UserRepositoryTest extends BaseSpringTestRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	static String testPhone = "18699918882";
	
	@Test
	public void saveUser(){
		//String openId = "ff8081814da00e2b014da00f32260002";
		String unionId = "o6_bmasdasdsad6_2sgVt7hMZOPfL";
		User o = createUser(unionId, testPhone);
		userRepository.save(o);
		
		unionId = "88_bmasdasdsad6_2sgVt7hMZOPfL";
		o = createUser(unionId, "18666668888");
		o.setParent("o6_bmasdasdsad6_2sgVt7hMZOPfL");
		userRepository.save(o);
		
		findByPhone();
	}
	
	@Test
	public void findAll() {
		//assertThat(userRepository.findAll()).isNotEmpty();
		Iterable<User> oclgs = userRepository.findAll();
		for(User o:oclgs){
			System.out.println(o.toString());
		}
	}
	
	public void findByPhone() {
		//assertThat(userRepository.findAll()).isNotEmpty();
		User u = userRepository.findByPhone(testPhone);
		System.out.println(u.getUnionId());
		assertEquals(u.getUnionId(), "o6_bmasdasdsad6_2sgVt7hMZOPfL");
			
	}
	
	public User createUser(String unionId, String phone){
		User o = new User();
		
		o.setNickName("test");
		o.setGender(Gender.MALE);
		o.setLanguage("en");
		o.setCity("北京");
		o.setProvince("上海");
		o.setCountry("China");
		o.setHeadimgurl("/head/image/test");
		o.setCreatet(new Date());
		o.setStatus(UserStatus.REGISTERED);
		o.setUnionId(unionId);
		o.setPhone(phone);
		return o;
		
	}
	
}
