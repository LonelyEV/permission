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

import ones.quzhigang.permission.model.SysRoleAclModel;
import ones.quzhigang.permission.query.SysRoleAclQuery;

@Mapper
public interface  SysRoleAclMapper{
	

																																																																																																																				
	public String columns="id,role_id,acl_id,operator,operate_time,operate_ip";
	
	public String insert="role_id,acl_id,operator,operate_time,operate_ip";
																																																																																																												
	public String property="#{id},#{roleId},#{aclId},#{operator},#{operateTime},#{operateIp}";
	
	public String insertProperty="#{roleId},#{aclId},#{operator},#{operateTime},#{operateIp}";
																																																																																																																				
	public String update="role_id=#{roleId},acl_id=#{aclId},operator=#{operator},operate_time=#{operateTime},operate_ip=#{operateIp}";
	
	@Select("select "+columns+" from tbl_sys_role_acl where ID=#{id}")
	@ResultMap(value="ones.quzhigang.permission.mapper.SysRoleAclMapper.SysRoleAclModelMap")
	public SysRoleAclModel getById(long id);
	
	@Insert("insert into tbl_sys_role_acl ("+insert+") values ("+insertProperty+")")
	@SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
	public long insert(SysRoleAclModel sysRoleAcl);

	@Update("update tbl_sys_role_acl set "+update+" where ID=#{id}")
	public long update(SysRoleAclModel sysRoleAcl); 
	
	@Delete("delete from tbl_sys_role_acl where 1 = 1 and ID=#{id} ")
	public void delById(long id);

	@SelectProvider(type=ones.quzhigang.permission.provider.SysRoleAclProvider.class,method="fetchPageAdvance")
	@ResultMap(value="ones.quzhigang.permission.mapper.SysRoleAclMapper.SysRoleAclModelMap")
	public List<SysRoleAclModel> fetchPageAdvance(SysRoleAclQuery query);  
	
	
	@SelectProvider(type=ones.quzhigang.permission.provider.SysRoleAclProvider.class,method="fetchPageAdvanceCount")
	public int fetchPageAdvanceCount(SysRoleAclQuery query);
	
	
	
	
}