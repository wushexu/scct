package foross.scctbi.data.vo;

public class SelectVo {
	private Object key ;
	private Object text;
	public SelectVo() {
		// TODO Auto-generated constructor stub
	}
	public SelectVo(Object key,Object text) {
		// TODO Auto-generated constructor stub
		this.key = key;
		this.text = text;
	}
	public Object getKey() {
		return key;
	}
	public void setKey(Object key) {
		this.key = key;
	}
	public Object getText() {
		return text;
	}
	public void setText(Object text) {
		this.text = text;
	}
	
	
}
