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

import ones.quzhigang.permission.model.SysRoleUserModel;
import ones.quzhigang.permission.query.SysRoleUserQuery;

@Mapper
public interface  SysRoleUserMapper{
	

																																																																																																																				
	public String columns="id,role_id,user_id,operator,operate_time,operate_ip";
	
	public String insert="role_id,user_id,operator,operate_time,operate_ip";
																																																																																																												
	public String property="#{id},#{roleId},#{userId},#{operator},#{operateTime},#{operateIp}";
	
	public String insertProperty="#{roleId},#{userId},#{operator},#{operateTime},#{operateIp}";
																																																																																																																				
	public String update="role_id=#{roleId},user_id=#{userId},operator=#{operator},operate_time=#{operateTime},operate_ip=#{operateIp}";
	
	@Select("select "+columns+" from tbl_sys_role_user where ID=#{id}")
	@ResultMap(value="ones.quzhigang.permission.mapper.SysRoleUserMapper.SysRoleUserModelMap")
	public SysRoleUserModel getById(long id);
	
	@Insert("insert into tbl_sys_role_user ("+insert+") values ("+insertProperty+")")
	@SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
	public long insert(SysRoleUserModel sysRoleUser);

	@Update("update tbl_sys_role_user set "+update+" where ID=#{id}")
	public long update(SysRoleUserModel sysRoleUser); 
	
	@Delete("delete from tbl_sys_role_user where 1 = 1 and ID=#{id} ")
	public void delById(long id);

	@SelectProvider(type=ones.quzhigang.permission.provider.SysRoleUserProvider.class,method="fetchPageAdvance")
	@ResultMap(value="ones.quzhigang.permission.mapper.SysRoleUserMapper.SysRoleUserModelMap")
	public List<SysRoleUserModel> fetchPageAdvance(SysRoleUserQuery query);  
	
	
	@SelectProvider(type=ones.quzhigang.permission.provider.SysRoleUserProvider.class,method="fetchPageAdvanceCount")
	public int fetchPageAdvanceCount(SysRoleUserQuery query);
	
	
	
	
}