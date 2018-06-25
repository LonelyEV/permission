package ones.quzhigang.permission.query;

import lombok.*;

import ones.quzhigang.permission.common.PaginationQuery;
import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class SysDeptQuery extends PaginationQuery implements Serializable {
	
	 
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