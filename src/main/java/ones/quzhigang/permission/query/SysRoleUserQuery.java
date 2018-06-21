package ones.quzhigang.permission.query;

import lombok.Data;

import ones.quzhigang.permission.common.PaginationQuery;
import java.io.Serializable;

@Data
public class SysRoleUserQuery extends PaginationQuery implements Serializable {
	
	 
	 	 	private Long id;
	 	 	private Integer roleId;
	 	 	private Integer userId;
	 	 	private String operator;
	 	 	private String operateTime;
	 	 	private String operateIp;
	 

		
}