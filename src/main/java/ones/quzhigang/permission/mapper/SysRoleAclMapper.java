package ones.quzhigang.permission.mapper;
import java.util.List;
import java.util.Map;

import ones.quzhigang.permission.model.SysAclModuleModel;
import org.apache.ibatis.annotations.*;

import ones.quzhigang.permission.model.SysRoleAclModel;
import ones.quzhigang.permission.query.SysRoleAclQuery;

@Mapper
public interface  SysRoleAclMapper{
	

																																																																																																																				
	String columns="id,role_id,acl_id,operator,operate_time,operate_ip";
	
	String insert="role_id,acl_id,operator,operate_time,operate_ip";
																																																																																																												
	String property="#{id},#{roleId},#{aclId},#{operator},#{operateTime},#{operateIp}";
	
	String insertProperty="#{roleId},#{aclId},#{operator},#{operateTime},#{operateIp}";
																																																																																																																				
	String update="role_id=#{roleId},acl_id=#{aclId},operator=#{operator},operate_time=#{operateTime},operate_ip=#{operateIp}";
	
	@Select("select "+columns+" from tbl_sys_role_acl where ID=#{id}")
	@ResultMap(value="ones.quzhigang.permission.mapper.SysRoleAclMapper.SysRoleAclModelMap")
	SysRoleAclModel getById(long id);
	
	@Insert("insert into tbl_sys_role_acl ("+insert+") values ("+insertProperty+")")
	@SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
	long insert(SysRoleAclModel sysRoleAcl);

	@Update("update tbl_sys_role_acl set "+update+" where ID=#{id}")
	long update(SysRoleAclModel sysRoleAcl);
	
	@Delete("delete from tbl_sys_role_acl where 1 = 1 and ID=#{id} ")
	void delById(long id);

	@SelectProvider(type=ones.quzhigang.permission.provider.SysRoleAclProvider.class,method="fetchPageAdvance")
	@ResultMap(value="ones.quzhigang.permission.mapper.SysRoleAclMapper.SysRoleAclModelMap")
	List<SysRoleAclModel> fetchPageAdvance(SysRoleAclQuery query);
	
	@SelectProvider(type=ones.quzhigang.permission.provider.SysRoleAclProvider.class,method="fetchPageAdvanceCount")
	int fetchPageAdvanceCount(SysRoleAclQuery query);

	@Select("select "+columns+" from tbl_sys_role_acl where role_id in  (${roleIds})")
	@ResultMap(value="ones.quzhigang.permission.mapper.SysRoleAclMapper.SysRoleAclAclIdModelMap")
	List<Long> getAclIdListByRoleIdList(@Param("roleIds") String roleIds);

	@Delete("delete from tbl_sys_role_acl where 1 = 1 and role_id=#{roleId} ")
	void deleteByRoleId(@Param("roleId") long roleId);

	@UpdateProvider(type=ones.quzhigang.permission.provider.SysRoleAclProvider.class,method="batchInsert")
	void batchInsert(Map<String, List<SysRoleAclModel>> map);


}