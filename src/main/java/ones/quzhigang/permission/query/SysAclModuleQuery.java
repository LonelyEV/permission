package ones.quzhigang.permission.query;

import lombok.Data;

import ones.quzhigang.permission.common.PaginationQuery;
import java.io.Serializable;

@Data
public class SysAclModuleQuery extends PaginationQuery implements Serializable {
	
	 
	 	 	private Long id;
	 	 	private String name;
	 	 	private Integer parentId;
	 	 	private String level;
	 	 	private Integer seq;
	 	 	private Integer status;
	 	 	private String remark;
	 	 	private String operator;
	 	 	private String operateTime;
	 	 	private String operateIp;
	 

		
}