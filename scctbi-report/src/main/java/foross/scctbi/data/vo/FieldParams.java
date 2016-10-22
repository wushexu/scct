package foross.scctbi.data.vo;

import java.util.List;

public class FieldParams{
	private String label;
	private String name;
	private Object defaultValue;
	private String type;
	private boolean isValidate;
	private List<SelectVo> selects;
	private String renderType;
	private boolean isHidden;
	
	public FieldParams() {
		// TODO Auto-generated constructor stub
	}
	
	public FieldParams(String label,String name,Object defaultValue) {
		// TODO Auto-generated constructor stub
		this.label = label;
		this.name = name;
		this.defaultValue = defaultValue;
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(Object defaultValue) {
		this.defaultValue = defaultValue;
	}

	public List<SelectVo> getSelects() {
		return selects;
	}

	public void setSelects(List<SelectVo> selects) {
		this.selects = selects;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isValidate() {
		return isValidate;
	}

	public void setValidate(boolean isValidate) {
		this.isValidate = isValidate;
	}

	public String getRenderType() {
		return renderType;
	}

	public void setRenderType(String renderType) {
		this.renderType = renderType;
	}

	public boolean isHidden() {
		return isHidden;
	}

	public void setHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}
	
	
}
