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

/**
 *
 */
public interface ProductRepository extends CrudRepository<Product, Long>{
	Product findProductById(Long id);
}

