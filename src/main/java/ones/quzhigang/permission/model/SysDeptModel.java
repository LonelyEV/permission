package ones.quzhigang.permission.model;

import lombok.*;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
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