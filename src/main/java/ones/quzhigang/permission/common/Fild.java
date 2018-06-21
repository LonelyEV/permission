package ones.quzhigang.permission.common;


public   class Fild  {

	private String type;  //字段类型 String
	private String fild;   //字段   userName 
	private String property;  //属性  UserName  
	private String columnName;//原始列名
	
	
	
	public Fild(String type, String fild, String property, String columnName) {
		super();
		this.type = type;
		this.fild = fild;
		this.property = property;
		this.columnName = columnName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFild() {
		return fild;
	}
	public void setFild(String fild) {
		this.fild = fild;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	
	
	

	
	
}