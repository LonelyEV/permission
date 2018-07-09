package ones.quzhigang.permission.mapper;
import java.util.List;

import ones.quzhigang.permission.beans.PageQuery;
import ones.quzhigang.permission.vo.SearchLogQueryVo;
import org.apache.ibatis.annotations.*;

import ones.quzhigang.permission.model.SysLogModel;
import ones.quzhigang.permission.query.SysLogQuery;

@Mapper
public interface  SysLogMapper{
	

																																																																																																																				
	String columns="id,type,target_id,old_value,new_value,operator,operate_time,operate_ip,status";
	
	String insert="type,target_id,old_value,new_value,operator,operate_time,operate_ip,status";
																																																																																																												
	String property="#{id},#{type},#{targetId},#{oldValue},#{newValue},#{operator},#{operateTime},#{operateIp},#{status}";
	
	String insertProperty="#{type},#{targetId},#{oldValue},#{newValue},#{operator},#{operateTime},#{operateIp},#{status}";
																																																																																																																				
	String update="type=#{type},target_id=#{targetId},old_value=#{oldValue},new_value=#{newValue},operator=#{operator},operate_time=#{operateTime},operate_ip=#{operateIp},status=#{status}";
	
	@Select("select "+columns+" from tbl_sys_log where ID=#{id}")
	@ResultMap(value="ones.quzhigang.permission.mapper.SysLogMapper.SysLogModelMap")
	SysLogModel getById(long id);
	
	@Insert("insert into tbl_sys_log ("+insert+") values ("+insertProperty+")")
	@Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
	long insert(SysLogModel sysLog);

	@Update("update tbl_sys_log set "+update+" where ID=#{id}")
	long update(SysLogModel sysLog);
	
	@Delete("delete from tbl_sys_log where 1 = 1 and ID=#{id} ")
	void delById(long id);

	@SelectProvider(type=ones.quzhigang.permission.provider.SysLogProvider.class,method="fetchPageAdvance")
	@ResultMap(value="ones.quzhigang.permission.mapper.SysLogMapper.SysLogModelMap")
	List<SysLogModel> fetchPageAdvance(@Param("query") SearchLogQueryVo query);


	@SelectProvider(type=ones.quzhigang.permission.provider.SysLogProvider.class,method="fetchPageAdvanceCount")
	int fetchPageAdvanceCount(SearchLogQueryVo query);
	
	
	
	
}