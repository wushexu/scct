package foross.scctbi.data.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Category extends AbstractPersistable<Long> {

	private static final long serialVersionUID = -1553671092370407899L;
	
	/**
	 * 类别名称
	 */
	@Column(unique = true)
	@NotNull
	@Size(min = 1, message = "can not be empty")
	private String name;

	/**
	 * 类表描述
	 */
	@NotNull
	@Size(min = 1, message = "can not be empty")
	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	
	public Category(Long id) {
		// TODO Auto-generated constructor stub
		this.setId(id);
	}
	
	public Category() {
		// TODO Auto-generated constructor stub
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
