package ones.quzhigang.permission.query;

import lombok.Data;

import ones.quzhigang.permission.common.PaginationQuery;
import java.io.Serializable;

@Data
public class SysLogQuery extends PaginationQuery implements Serializable {
	
	 
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