/**
 * 
 */
package net.wyun.wcrs.model.repo;

import net.wyun.wcrs.model.PAUser;
import net.wyun.wcrs.model.WCUser;

/**
 * @author michael
 *
 */
@Deprecated
public interface PAUserPersistor {
	
	public void persist(PAUser paU, WCUser wcU);
	public void createPAUserWithExistingWCUser(PAUser paU);

}
