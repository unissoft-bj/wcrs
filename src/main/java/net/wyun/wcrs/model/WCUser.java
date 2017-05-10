package net.wyun.wcrs.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * WCUser  --> unique WeChat user, identified by unionId
 * @author michael
 *
 */

@Entity
@Table(name = "w_c_user")
//@JsonIgnoreProperties({"id", "password", "factor", "sent_to_server", "enabled", "macAccounts"})
public class WCUser {
	
	//字段	
	@Id
	@Column(name = "union_id", nullable = false)
	String unionId;
	
	String parent; //parent's scene_id
	
	@Column(name = "nick_name")
	String nickName;
	
	Gender gender; //sex		
	String city;		
	String province;
	String country;
	
	@Column(name = "head_img_url", nullable = false)
	String headimgurl;
	
	@Column(name = "create_t", nullable = false)
    private Date createt; //        //subscribe_time, 
	
    private Date modify_t; //         datetime 
	
	String language;
	WCUserStatus status;
	
	private String phone;
	/*
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, mappedBy="user")
    private Collection<PAUser> paUsers = new ArrayList<PAUser>();
	
	@Transient
	public void addPAUser(PAUser paU){
		paUsers.add(paU);
	}
	*/
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUnionId() {
		return unionId;
	}
	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}
	public WCUserStatus getStatus() {
		return status;
	}
	public void setStatus(WCUserStatus status) {
		this.status = status;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
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

}
