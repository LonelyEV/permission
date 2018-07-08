package ones.quzhigang.permission.mapper;
import java.util.List;

import org.apache.ibatis.annotations.*;

import ones.quzhigang.permission.model.SysRoleModel;
import ones.quzhigang.permission.query.SysRoleQuery;

@Mapper
public interface  SysRoleMapper{
	

																																																																																																																				
	String columns="id,name,type,status,remark,operator,operate_time,operate_ip";
	
	String insert="name,type,status,remark,operator,operate_time,operate_ip";
																																																																																																												
	String property="#{id},#{name},#{type},#{status},#{remark},#{operator},#{operateTime},#{operateIp}";
	
	String insertProperty="#{name},#{type},#{status},#{remark},#{operator},#{operateTime},#{operateIp}";
																																																																																																																				
	String update="name=#{name},type=#{type},status=#{status},remark=#{remark},operator=#{operator},operate_time=#{operateTime},operate_ip=#{operateIp}";
	
	@Select("select "+columns+" from tbl_sys_role where ID=#{id}")
	@ResultMap(value="ones.quzhigang.permission.mapper.SysRoleMapper.SysRoleModelMap")
	SysRoleModel getById(long id);
	
	@Insert("insert into tbl_sys_role ("+insert+") values ("+insertProperty+")")
	@SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
	long insert(SysRoleModel sysRole);

	@Update("update tbl_sys_role set "+update+" where ID=#{id}")
	long update(SysRoleModel sysRole);
	
	@Delete("delete from tbl_sys_role where 1 = 1 and ID=#{id} ")
	void delById(long id);

	@SelectProvider(type=ones.quzhigang.permission.provider.SysRoleProvider.class,method="fetchPageAdvance")
	@ResultMap(value="ones.quzhigang.permission.mapper.SysRoleMapper.SysRoleModelMap")
	List<SysRoleModel> fetchPageAdvance(SysRoleQuery query);
	
	@SelectProvider(type=ones.quzhigang.permission.provider.SysRoleProvider.class,method="fetchPageAdvanceCount")
	int fetchPageAdvanceCount(SysRoleQuery query);

	@Select("select "+columns+" from tbl_sys_role ")
	@ResultMap(value="ones.quzhigang.permission.mapper.SysRoleMapper.SysRoleModelMap")
	List<SysRoleModel> getAll();

	@SelectProvider(type=ones.quzhigang.permission.provider.SysRoleProvider.class,method="countByNameAndId")
	int countByNameAndId(@Param("name") String name, @Param("id") Long id);

	@Select("select "+columns+" from tbl_sys_role where ID in (${ids}) ")
	@ResultMap(value="ones.quzhigang.permission.mapper.SysRoleMapper.SysRoleModelMap")
	List<SysRoleModel> getByIds(@Param("ids") String ids);

	@Select("select role_id from tbl_sys_role_acl where acl_id = ${aclId}")
	@ResultMap(value="ones.quzhigang.permission.mapper.SysRoleMapper.SysRoleIdModelMap")
	List<Long> getRoleIdListByAclId(@Param("aclId") long aclId);
}