/**
 * 
 */
package net.wyun.wcrs.model.repo;

import java.util.List;

/**
 * @author michael
 *
 */

import org.springframework.data.repository.CrudRepository;

import net.wyun.wcrs.model.Product;
import net.wyun.wcrs.model.PublicAccount;

/**
 *
 */
public interface PublicAccountRepository extends CrudRepository<PublicAccount, String>{
	List<PublicAccount> findByAppId(String appId);
}

