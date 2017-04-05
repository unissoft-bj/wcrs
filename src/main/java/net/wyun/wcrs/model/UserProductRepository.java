/**
 * 
 */
package net.wyun.wcrs.model;

import java.util.List;

/**
 * @author michael
 *
 */

import org.springframework.data.repository.CrudRepository;

/**
 *
 */
public interface UserProductRepository extends CrudRepository<UserProduct, Long>{
	List<UserProduct> findByUnionId(String uniodId);
}

