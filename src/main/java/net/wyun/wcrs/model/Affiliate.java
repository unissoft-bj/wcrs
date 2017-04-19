/**
 * 
 */
package net.wyun.wcrs.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author michael
 *
 */
@Entity
@Table(name = "affiliate")
public class Affiliate {
	
	public Affiliate() {	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id; 

	public Long getId() {
		return id;
	}
	
	@ManyToOne
	Product product;

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "create_t")
    private Date createT; //create_t        datetime             DEFAULT NULL	
	
	private Integer first; //           smallint           NOT NULL DEFAULT 0,
    private Integer second; //          smallint           NOT NULL DEFAULT 0,
    private Integer third; //           smallint           NOT NULL DEFAULT 0,
    
    @Column(name = "aff_node_p")
    private Integer affNodeP;           //aff_node_p      smallint           NOT NULL DEFAULT 10,
    @Column(name = "aff_node_id")
    private String affNodeId;           //union id in WCUser
    @Column(name = "aff_node_name")
    private String affNodeName; //aff_node_name   varchar(32)        DEFAULT NULL,

    private String description; //     varchar(100)       NOT NULL DEFAULT '',

	public Date getCreateT() {
		return createT;
	}

	public void setCreateT(Date createT) {
		this.createT = createT;
	}

	public Integer getFirst() {
		return first;
	}

	public void setFirst(Integer first) {
		this.first = first;
	}

	public Integer getSecond() {
		return second;
	}

	public void setSecond(Integer second) {
		this.second = second;
	}

	public Integer getThird() {
		return third;
	}

	public void setThird(Integer third) {
		this.third = third;
	}

	public Integer getAffNodeP() {
		return affNodeP;
	}

	public void setAffNodeP(Integer affNodeP) {
		this.affNodeP = affNodeP;
	}

	public String getAffNodeId() {
		return affNodeId;
	}

	public void setAffNodeId(String affNodeId) {
		this.affNodeId = affNodeId;
	}

	public String getAffNodeName() {
		return affNodeName;
	}

	public void setAffNodeName(String affNodeName) {
		this.affNodeName = affNodeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
