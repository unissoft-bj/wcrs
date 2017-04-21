/**
 * 
 */
package net.wyun.wcrs.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author michael
 *
 */
@Entity
@Table(name = "wcuser_product")
public class WCUserProduct {
	
	public WCUserProduct() {	}

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
	
	@Column(name = "union_id")
	String unionId; //union_id        VARCHAR(36)         NOT NULL,
	
	@Column(name = "product_id")
    Long productId; //product_id     int unsigned        NOT NULL,
	
    BigDecimal amount; //         numeric(15,2)       NOT NULL,
    
    @Column(name = "create_t")
    Date createT; //create_t       datetime            DEFAULT NULL,

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getCreateT() {
		return createT;
	}

	public void setCreateT(Date createT) {
		this.createT = createT;
	}
    
}
