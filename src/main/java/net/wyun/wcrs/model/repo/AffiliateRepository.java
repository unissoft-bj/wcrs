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

import net.wyun.wcrs.model.Affiliate;
import net.wyun.wcrs.model.Product;

/**
 *
 */
public interface AffiliateRepository extends CrudRepository<Affiliate, Long>{
	List<Affiliate> findByProduct(Product p);
}

