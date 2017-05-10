/**
 * 
 */
package net.wyun.wcrs.model.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import net.wyun.wcrs.model.WCUser;

/**
 * @author Xuecheng
 *
 */
public interface WCUserRepository extends JpaRepository<WCUser, String>{
	
	@Modifying
	@Transactional
	@Query("DELETE FROM WCUser a WHERE (a.createt > :cutOff)")
	int removeByCreate_tGreaterThan(@Param("cutOff") Date cutOff);
	
	List<WCUser> deleteByCreatetAfter(@Param("cutOff") Date cutOff);
	
	WCUser findByPhone(String phone);
	
	List<WCUser> findByParent(String parentId);
	
	
}
