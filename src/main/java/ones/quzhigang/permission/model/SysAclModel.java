package ones.quzhigang.permission.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysAclModel implements Serializable{
	
	 
	 	 	private Long id;
	 	 	private String code;
	 	 	private String name;
	 	 	private Integer aclModuleId;
	 	 	private String url;
	 	 	private Integer type;
	 	 	private Integer status;
	 	 	private Integer seq;
	 	 	private String remark;
	 	 	private String operator;
	 	 	private String operateTime;
	 	 	private String operateIp;
	 

		
}