package net.wyun.wcrs.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * public account user --> unique user binding with a public account
 * WCUser   --> PAUser is 1 --> M relationship
 * @author michael
 *
 */

@Entity
@Table(name = "p_a_user")
public class PAUser {
	public PAUser(){}
	//字段	
	@Id
	@Column(name = "open_id", nullable = false)
	String openId;
	
	@Column(name = "p_a_id", nullable = false)
	String paId; 
	
	@Column(name = "scene_id")
	Integer sceneID; //（1-100000）	
	/*
	@Column(name = "union_id", nullable = false)
	private String unionId;
	*/
	
	@ManyToOne(targetEntity=WCUser.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "union_id", referencedColumnName="union_id", columnDefinition="VARCHAR(36)")
	private WCUser user;
	
	@Column(name = "create_t", nullable = false)
    private Date createt; //        //subscribe_time, 
	
    private Date modify_t; //         datetime 
	//subscribe_time		
	String ticket;
	
	/*
	public String getUnionId() {
		return unionId;
	}
	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}
	*/
		
	public Date getCreatet() {
		return createt;
	}
	public void setCreatet(Date createt) {
		this.createt = createt;
	}
	public Date getModify_t() {
		return modify_t;
	}
	public void setModify_t(Date modify_t) {
		this.modify_t = modify_t;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getPaId() {
		return paId;
	}
	public void setPaId(String paId) {
		this.paId = paId;
	}
	public Integer getSceneID() {
		return sceneID;
	}
	public void setSceneID(Integer sceneID) {
		this.sceneID = sceneID;
	}
	public WCUser getUser() {
		return user;
	}
	public void setUser(WCUser user) {
		this.user = user;
	}
	

}
