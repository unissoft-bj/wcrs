/**
 * 
 */
package net.wyun.wcrs.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * wechat public account
 * @author michael
 *
 */
@Entity
@Table(name = "public_account")
public class PublicAccount {
	
	public PublicAccount() {	}
	
	@Id
	@Column(name = "p_a_id", nullable = false)
	String paId;
	
	@Column(name = "app_id")
	String appId;
	
	@Column(name = "app_secret")
	String appSecret;
	
	String description;
	
	@Column(name = "create_t")
    private Date createT;
	
	
	public String getPaId() {
		return paId;
	}
	public void setPaId(String paId) {
		this.paId = paId;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppSecret() {
		return appSecret;
	}
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	
	public Date getCreateT() {
		return createT;
	}
	public void setCreateT(Date createT) {
		this.createT = createT;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
