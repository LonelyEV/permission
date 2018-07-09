package ones.quzhigang.permission.model;

import lombok.*;

import java.io.Serializable;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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