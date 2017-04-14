/**
 * 
 */
package net.wyun.wcrs.model.repo;


import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import net.wyun.wcrs.model.WechatEvent;

/**
 * @author Xuecheng
 *
 */
public interface WechatEventRepository extends CrudRepository<WechatEvent, Long>{

	@Query(value="select evt from WechatEvent evt")
	public List<WechatEvent> findWithPageable(Pageable pageable);
	
}