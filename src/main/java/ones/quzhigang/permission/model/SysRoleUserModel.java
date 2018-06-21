package ones.quzhigang.permission.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysRoleUserModel implements Serializable{
	
	 
	 	 	private Long id;
	 	 	private Integer roleId;
	 	 	private Integer userId;
	 	 	private String operator;
	 	 	private String operateTime;
	 	 	private String operateIp;
	 

		
}