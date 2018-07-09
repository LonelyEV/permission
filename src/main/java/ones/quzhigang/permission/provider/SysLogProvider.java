package ones.quzhigang.permission.provider;

import java.io.Serializable;

import ones.quzhigang.permission.beans.PageQuery;
import ones.quzhigang.permission.query.SysLogQuery;
import ones.quzhigang.permission.vo.SearchLogQueryVo;
import org.apache.commons.lang.StringUtils;


public class SysLogProvider implements Serializable {


    public String fetchPageAdvance(SearchLogQueryVo query) {
        StringBuffer sql = new StringBuffer("select * from tbl_sys_log where 1 = 1");

        queryCondition(query, sql);

        sql.append(" order by operate_time desc ");
        sql.append(" limit #{query.offset},#{query.pageSize} ");


        return sql.toString();
    }

    public String fetchPageAdvanceCount(SearchLogQueryVo query) {
        StringBuffer sql = new StringBuffer("select count(*) from tbl_sys_log where 1 = 1 ");
        queryCondition(query, sql);

        return sql.toString();
    }

    public String queryCondition(SearchLogQueryVo query, StringBuffer sql){
        if (query != null) {

            if(query.getType() != null){
                sql.append(" and type = "+query.getType());
            }
            if(StringUtils.isNotBlank(query.getBeforeSeg())){
                sql.append(" and old_value like '%"+query.getBeforeSeg()+"%'");
            }
            if(StringUtils.isNotBlank(query.getAfterSeg())){
                sql.append(" and new_value like '%"+query.getAfterSeg()+"%'");
            }
            if(StringUtils.isNotBlank(query.getOperator())){
                sql.append(" and operator = '"+query.getOperator()+"'");
            }
            if(StringUtils.isNotBlank(query.getFromTime()) && StringUtils.isNotBlank(query.getToTime())){
                sql.append(" and operate_time between '"+query.getFromTime()+"' and '"+query.getToTime()+"'");
            }else{
                if(StringUtils.isNotBlank(query.getFromTime())){
                    sql.append(" and operate_time >= '"+query.getFromTime()+"'");
                }
                if(StringUtils.isNotBlank(query.getToTime())){
                    sql.append(" and operate_time <= '"+query.getToTime()+"'");
                }
            }

        }

        return sql.toString();
    }


}
