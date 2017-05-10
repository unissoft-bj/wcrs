/**
 * 
 */
package net.wyun.wcrs.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.wyun.wcrs.model.PAUser;

/**
 * @author Xuecheng
 *
 */
public interface PAUserRepository extends JpaRepository<PAUser, String>{
	
	PAUser findByOpenId(String openId);
	
	@Query("select coalesce(max(o.sceneID), '1') from PAUser o WHERE (o.paId = :paId)")
	int findMaxSceneID(@Param("paId") String paId);
	
	PAUser findBySceneID(Integer sceneID);
	
}
