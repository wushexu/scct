package foross.scctbi.data.vo;

import java.util.List;

import foross.scctbi.data.model.ReportDefinition;

public class MenuVo {
	private Long id;
	private String name;
	private String description;
	private List<ReportDefinition> list;
	
	public MenuVo() {
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

	public List<ReportDefinition> getList() {
		return list;
	}

	public void setList(List<ReportDefinition> list) {
		this.list = list;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
