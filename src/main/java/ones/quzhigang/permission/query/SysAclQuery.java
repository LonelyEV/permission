package ones.quzhigang.permission.query;

import lombok.Data;

import ones.quzhigang.permission.common.PaginationQuery;
import java.io.Serializable;

@Data
public class SysAclQuery extends PaginationQuery implements Serializable {
	
	 
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