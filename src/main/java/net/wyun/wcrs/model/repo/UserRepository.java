/**
 * 
 */
package net.wyun.wcrs.model.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import net.wyun.wcrs.model.User;

/**
 * @author Xuecheng
 *
 */
public interface UserRepository extends CrudRepository<User, String>{
	
	@Modifying
	@Transactional
	@Query("DELETE FROM User a WHERE (a.createt > :cutOff)")
	int removeByCreate_tGreaterThan(@Param("cutOff") Date cutOff);
	
	List<User> deleteByCreatetAfter(@Param("cutOff") Date cutOff);
	
	User findByPhone(String phone);
	
	
}
