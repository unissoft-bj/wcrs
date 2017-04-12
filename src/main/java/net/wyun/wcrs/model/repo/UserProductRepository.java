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

import net.wyun.wcrs.model.UserProduct;

/**
 *
 */
public interface UserProductRepository extends CrudRepository<UserProduct, Long>{
	List<UserProduct> findByUnionId(String uniodId);
}

