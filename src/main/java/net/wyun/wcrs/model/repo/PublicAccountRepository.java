/**
 * 
 */
package net.wyun.wcrs.model.repo;

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
public interface PublicAccountRepository extends CrudRepository<PublicAccount, String>{ }

