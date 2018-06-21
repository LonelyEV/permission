package ones.quzhigang.permission.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import ones.quzhigang.permission.model.SysRoleModel;
import ones.quzhigang.permission.query.SysRoleQuery;

@Mapper
public interface  SysRoleMapper{
	

																																																																																																																				
	public String columns="id,name,type,status,remark,operator,operate_time,operate_ip";
	
	public String insert="name,type,status,remark,operator,operate_time,operate_ip";
																																																																																																												
	public String property="#{id},#{name},#{type},#{status},#{remark},#{operator},#{operateTime},#{operateIp}";
	
	public String insertProperty="#{name},#{type},#{status},#{remark},#{operator},#{operateTime},#{operateIp}";
																																																																																																																				
	public String update="name=#{name},type=#{type},status=#{status},remark=#{remark},operator=#{operator},operate_time=#{operateTime},operate_ip=#{operateIp}";
	
	@Select("select "+columns+" from tbl_sys_role where ID=#{id}")
	@ResultMap(value="ones.quzhigang.permission.mapper.SysRoleMapper.SysRoleModelMap")
	public SysRoleModel getById(long id);
	
	@Insert("insert into tbl_sys_role ("+insert+") values ("+insertProperty+")")
	@SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
	public long insert(SysRoleModel sysRole);

	@Update("update tbl_sys_role set "+update+" where ID=#{id}")
	public long update(SysRoleModel sysRole); 
	
	@Delete("delete from tbl_sys_role where 1 = 1 and ID=#{id} ")
	public void delById(long id);

	@SelectProvider(type=ones.quzhigang.permission.provider.SysRoleProvider.class,method="fetchPageAdvance")
	@ResultMap(value="ones.quzhigang.permission.mapper.SysRoleMapper.SysRoleModelMap")
	public List<SysRoleModel> fetchPageAdvance(SysRoleQuery query);  
	
	
	@SelectProvider(type=ones.quzhigang.permission.provider.SysRoleProvider.class,method="fetchPageAdvanceCount")
	public int fetchPageAdvanceCount(SysRoleQuery query);
	
	
	
	
}