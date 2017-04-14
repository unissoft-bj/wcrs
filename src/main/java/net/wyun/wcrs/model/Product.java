/**
 * 
 */
package net.wyun.wcrs.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author michael
 *
 */
@Entity
@Table(name = "product")
public class Product {
	
	
    public Product() { createT = new Date();	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id; 

	public Long getId() {
		return id;
	}

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER, mappedBy = "product")
	List<Affiliate> affiliates;
	
	private String description; //     varchar(30)          NOT NULL DEFAULT '',
	
	@Column(name = "p_type")
    private String pType; //p_type          varchar(30)          NOT NULL DEFAULT '',
    private boolean expired; //         tinyint(1) unsigned  NOT NULL DEFAULT '1',
    
    @Column(name = "create_t")
    private Date createT; //create_t        datetime             DEFAULT NULL	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getpType() {
		return pType;
	}

	public void setpType(String pType) {
		this.pType = pType;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public Date getCreateT() {
		return createT;
	}

	public void setCreateT(Date createT) {
		this.createT = createT;
	}

	public List<Affiliate> getAffiliates() {
		return affiliates;
	}

	public void setAffiliates(List<Affiliate> affiliates) {
		this.affiliates = affiliates;
	}
	

}
