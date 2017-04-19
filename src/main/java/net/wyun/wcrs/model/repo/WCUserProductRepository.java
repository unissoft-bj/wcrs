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

import net.wyun.wcrs.model.WCUserProduct;

/**
 *
 */
public interface WCUserProductRepository extends CrudRepository<WCUserProduct, Long>{
	List<WCUserProduct> findByUnionId(String uniodId);
}

