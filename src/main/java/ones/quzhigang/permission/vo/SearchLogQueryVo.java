package ones.quzhigang.permission.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ones.quzhigang.permission.beans.PageQuery;

@Getter
@Setter
@ToString
public class SearchLogQueryVo extends PageQuery {

    private Integer type; // LogType

    private String beforeSeg;

    private String afterSeg;

    private String operator;

    private String fromTime;//yyyy-MM-dd HH:mm:ss

    private String toTime;
}
