package net.wyun.wcrs.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.wyun.wcrs.model.PublicAccount;
import net.wyun.wcrs.model.repo.PAUserRepository;
import net.wyun.wcrs.model.repo.PublicAccountRepository;

@Component
public class SceneIdServiceImpl implements SceneIdService {
	
	private static final Logger logger = LoggerFactory.getLogger(SceneIdServiceImpl.class);
	
	@Autowired
	PAUserRepository userRepo;
	
	@Autowired
	PublicAccountRepository paRepo;
	
	
	Map<String, AtomicInteger> sidMap = new HashMap<String, AtomicInteger>();

	/**
	 * need synchronize this call
	 * @return
	 */
	public int nextSceneID(String paId){
		
		return sidMap.get(paId).incrementAndGet();
	}
	
	@PostConstruct
    public void init() {
		logger.info("SceneId service: initialising ...");
		Iterable<PublicAccount> PAs = paRepo.findAll();
		
		for(PublicAccount pa:PAs){
			int currentMax = userRepo.findMaxSceneID(pa.getPaId());
			AtomicInteger s_id = new AtomicInteger(currentMax);
			logger.info("SceneId service, public account {}  current max scene_id is {}", pa.getPaId(),  s_id);
			sidMap.put(pa.getPaId(), s_id);
		}
		
		logger.info("SceneId service: loading done.");
		
	}
}
