/**
 * 
 */
package net.wyun.wcrs.model.repo;

/**
 * @author michael
 *
 */

import org.springframework.data.repository.CrudRepository;

import net.wyun.wcrs.model.Billing;

/**
 *
 */
public interface BillingRepository extends CrudRepository<Billing, Long>{
}

