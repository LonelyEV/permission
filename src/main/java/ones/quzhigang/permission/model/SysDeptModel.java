package ones.quzhigang.permission.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysDeptModel implements Serializable{
	
	 
	 	 	private Long id;
	 	 	private String name;
	 	 	private Integer parentId;
	 	 	private String level;
	 	 	private Integer seq;
	 	 	private String remark;
	 	 	private String operator;
	 	 	private String operateTime;
	 	 	private String operateIp;
	 

		
}