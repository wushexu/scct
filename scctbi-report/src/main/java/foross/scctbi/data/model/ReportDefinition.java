package foross.scctbi.data.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.AbstractAuditable;

@Entity
public class ReportDefinition extends AbstractAuditable<User, Long> {

	private static final long serialVersionUID = 177876236L;

	/**
	 * 报表编码
	 */
	@Column(unique = true)
	@NotNull
	@Size(min = 1, message = "can not be empty")
	private String code;

	/**
	 * 报表名称
	 */
	@NotNull
	@Size(min = 1, message = "can not be empty")
	private String name;

	/**
	 * 报表说明
	 */
	private String description;

	/**
	 * 备注
	 */
	private String memo;

	/**
	 * 报表文件内容
	 */
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(length = 5 * 1024 * 1024)
	private byte[] content;

	private int contentSize;

	/**
	 * 数据源类型：jndi、jdbc、kettle、xmla、olap4j、hybrid..., 默认jdbc
	 */
	private String datasourceType;
	
	@OneToOne
	private Category categoryId;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public int getContentSize() {
		return contentSize;
	}

	public void setContentSize(int contentSize) {
		this.contentSize = contentSize;
	}

	public String getDatasourceType() {
		return datasourceType;
	}

	public void setDatasourceType(String datasourceType) {
		this.datasourceType = datasourceType;
	}

	public String toString() {
		return this.name;
	}

	public Category getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Category categoryId) {
		this.categoryId = categoryId;
	}
	
	
}
