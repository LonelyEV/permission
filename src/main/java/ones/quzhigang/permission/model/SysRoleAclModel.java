package ones.quzhigang.permission.model;

import lombok.*;
import org.springframework.web.context.annotation.ApplicationScope;

import java.io.Serializable;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SysRoleAclModel implements Serializable{
	
	 
	 	 	private Long id;
	 	 	private Integer roleId;
	 	 	private Integer aclId;
	 	 	private String operator;
	 	 	private String operateTime;
	 	 	private String operateIp;
	 

		
}