package ones.quzhigang.permission.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysUserModel implements Serializable{
	
	 
	 	 	private Long id;
	 	 	private String username;
	 	 	private String telephone;
	 	 	private String mail;
	 	 	private String password;
	 	 	private Integer deptId;
	 	 	private Integer status;
	 	 	private String remark;
	 	 	private String operator;
	 	 	private String operateTime;
	 	 	private String operateIp;
	 

		
}