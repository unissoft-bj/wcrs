/**
 * 
 */
package net.wyun.wcrs.model.repo;


import org.springframework.data.repository.CrudRepository;

import net.wyun.wcrs.model.WechatEvent;

/**
 * @author Xuecheng
 *
 */
public interface WechatEventRepository extends CrudRepository<WechatEvent, Long>{
	
}
