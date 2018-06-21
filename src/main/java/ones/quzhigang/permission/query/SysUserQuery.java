package ones.quzhigang.permission.query;

import lombok.Data;

import ones.quzhigang.permission.common.PaginationQuery;
import java.io.Serializable;

@Data
public class SysUserQuery extends PaginationQuery implements Serializable {
	
	 
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