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

import net.wyun.wcrs.model.PAUser;
import net.wyun.wcrs.model.User;

/**
 * @author Xuecheng
 *
 */
public interface PAUserRepository extends CrudRepository<PAUser, String>{
	
	PAUser findByOpenId(String openId);
	
	@Query("select coalesce(max(o.sceneID), '1') from PAUser o WHERE (o.paId = :paId)")
	int findMaxSceneID(@Param("paId") String paId);
	
	PAUser findBySceneID(Integer sceneID);
	
}
