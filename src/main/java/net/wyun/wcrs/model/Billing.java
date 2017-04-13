/**
 * 
 */
package net.wyun.wcrs.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
@Table(name = "billing")
public class Billing {
	/**
	 * 
	 * @param unionId, w_c_user id
	 * @param upId, user_product id
	 * @param percent, percent for bonus
	 * @param amount, award amount
	 * @param createT
	 */
	public Billing(String unionId, Long upId, Integer percent, BigDecimal amount, Date createT) {
		this.unionId = unionId;
		this.upId = upId;
		this.percent = percent;
		this.amount = amount;
		this.createT = createT;
	}

	public Billing() { createT = new Date();	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id; 

	public Long getId() {
		return id;
	}
	
	@Column(name = "union_id", nullable = false)
	private String unionId;
	
	@Column(name = "u_p_id", nullable = false)
	private Long upId;
	
	private Integer percent;
	
	public Integer getPercent() {
		return percent;
	}

	public void setPercent(Integer percent) {
		this.percent = percent;
	}

	BigDecimal amount; //         numeric(15,2)       NOT NULL,
	
	@Column(name = "create_t")
    private Date createT; //create_t        datetime             DEFAULT NULL	

	public Date getCreateT() {
		return createT;
	}

	public void setCreateT(Date createT) {
		this.createT = createT;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public Long getUpId() {
		return upId;
	}

	public void setUpId(Long upId) {
		this.upId = upId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
