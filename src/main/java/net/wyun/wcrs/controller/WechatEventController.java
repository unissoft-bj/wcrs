/**
 * 
 */
package net.wyun.wcrs.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.wyun.wcrs.model.Affiliate;
import net.wyun.wcrs.model.Product;
import net.wyun.wcrs.model.WechatEvent;
import net.wyun.wcrs.model.repo.AffiliateRepository;
import net.wyun.wcrs.model.repo.ProductRepository;
import net.wyun.wcrs.model.repo.WechatEventRepository;

/**
 * @author michael
 *
 */
@CrossOrigin
@RequestMapping("/secure")
@RestController
public class WechatEventController {
	
private static final Logger logger = LoggerFactory.getLogger(WechatEventController.class);
	
	@Autowired
	WechatEventRepository eventRepo;
	
	@RequestMapping(value= "/event/last/{num}", method=RequestMethod.GET)
	Iterable<WechatEvent> getProducts(@PathVariable("num") Integer num){
		Pageable top = new PageRequest(0, num, Direction.DESC, "id"); 
		List<WechatEvent> evts = eventRepo.findWithPageable(top);
		return evts;
	}
	
	@RequestMapping(value= "/event/{evtId}", method=RequestMethod.GET)
	WechatEvent getProduct(@PathVariable("evtId") Long evtId){
		return eventRepo.findOne(evtId);
	}

}
