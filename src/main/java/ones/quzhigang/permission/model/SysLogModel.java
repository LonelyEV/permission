package ones.quzhigang.permission.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysLogModel implements Serializable{
	
	 
	 	 	private Long id;
	 	 	private Integer type;
	 	 	private Integer targetId;
	 	 	private String oldValue;
	 	 	private String newValue;
	 	 	private String operator;
	 	 	private String operateTime;
	 	 	private String operateIp;
	 	 	private Integer status;
	 

		
}