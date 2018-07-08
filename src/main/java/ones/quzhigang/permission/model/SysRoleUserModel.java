package ones.quzhigang.permission.model;

import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SysRoleUserModel implements Serializable{
	
	 
	 	 	private Long id;
	 	 	private Integer roleId;
	 	 	private Integer userId;
	 	 	private String operator;
	 	 	private String operateTime;
	 	 	private String operateIp;
	 

		
}