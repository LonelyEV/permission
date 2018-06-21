package ones.quzhigang.permission.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysRoleModel implements Serializable{
	
	 
	 	 	private Long id;
	 	 	private String name;
	 	 	private Integer type;
	 	 	private Integer status;
	 	 	private String remark;
	 	 	private String operator;
	 	 	private String operateTime;
	 	 	private String operateIp;
	 

		
}