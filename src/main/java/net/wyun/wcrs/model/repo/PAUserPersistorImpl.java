/**
 * 
 */
package net.wyun.wcrs.model.repo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import net.wyun.wcrs.model.PAUser;
import net.wyun.wcrs.model.WCUser;

/**
 * @author michael
 *
 */
@Deprecated
@Component
public class PAUserPersistorImpl implements PAUserPersistor {

	
	@PersistenceContext
    private EntityManager em;
	
	@Autowired
	PAUserRepository paUserRepo;
	
	@Autowired
	WCUserRepository userRepo;

	
	@Override
	@Transactional
	public void persist(PAUser paU, WCUser wcU) {

		//WCUser w = em.find(WCUser.class, wcU.getUnionId());
		paU.setUser(wcU);
		em.merge(paU);
		
	}


	@Override
	@Transactional
	public void createPAUserWithExistingWCUser(PAUser paU) {
		//WCUser w = paU.getUser();
		//paU.setUser(null);
		//paU = this.paUserRepo.saveAndFlush(paU);
		//update paU with
		//paU.setUser(w);
		paUserRepo.saveAndFlush(paU);
		
	}
	
	
	
	

}
