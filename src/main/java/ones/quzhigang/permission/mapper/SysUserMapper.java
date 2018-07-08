package ones.quzhigang.permission.mapper;
import java.util.List;

import ones.quzhigang.permission.beans.PageQuery;
import org.apache.ibatis.annotations.*;

import ones.quzhigang.permission.model.SysUserModel;
import ones.quzhigang.permission.query.SysUserQuery;

@Mapper
public interface  SysUserMapper{
	

																																																																																																																				
	String columns="id,username,telephone,mail,password,dept_id,status,remark,operator,operate_time,operate_ip";
	
	String insert="username,telephone,mail,password,dept_id,status,remark,operator,operate_time,operate_ip";
																																																																																																												
	String property="#{id},#{username},#{telephone},#{mail},#{password},#{deptId},#{status},#{remark},#{operator},#{operateTime},#{operateIp}";
	
	String insertProperty="#{username},#{telephone},#{mail},#{password},#{deptId},#{status},#{remark},#{operator},#{operateTime},#{operateIp}";
																																																																																																																				
	String update="username=#{username},telephone=#{telephone},mail=#{mail},password=#{password},dept_id=#{deptId},status=#{status},remark=#{remark},operator=#{operator},operate_time=#{operateTime},operate_ip=#{operateIp}";

	String getUser_By_RoleId_columns="t1.id,t1.username,t1.telephone,t1.mail,t1.password,t1.dept_id,t1.status,t1.remark,t1.operator,t1.operate_time,t1.operate_ip";

	String getUser_By_RoleId_property="#{id},#{username},#{telephone},#{mail},#{password},#{deptId},#{status},#{remark},#{operator},#{operateTime},#{operateIp}";

	@Select("select "+columns+" from tbl_sys_user where ID=#{id}")
	@ResultMap(value="ones.quzhigang.permission.mapper.SysUserMapper.SysUserModelMap")
	SysUserModel getById(long id);

	@Select("select "+columns+" from tbl_sys_user where 1=1 and  telephone=#{keyWords} or mail = #{keyWords} ")
	@ResultMap(value="ones.quzhigang.permission.mapper.SysUserMapper.SysUserModelMap")
	SysUserModel findByKeyWords(@Param("keyWords") String keyWords);
	
	@Insert("insert into tbl_sys_user ("+insert+") values ("+insertProperty+")")
	@SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
	long insert(SysUserModel sysUser);

	@Update("update tbl_sys_user set "+update+" where ID=#{id}")
	long update(SysUserModel sysUser);
	
	@Delete("delete from tbl_sys_user where 1 = 1 and ID=#{id} ")
	void delById(long id);

	@SelectProvider(type=ones.quzhigang.permission.provider.SysUserProvider.class,method="fetchPageAdvance")
	@ResultMap(value="ones.quzhigang.permission.mapper.SysUserMapper.SysUserModelMap")
	List<SysUserModel> fetchPageAdvance(SysUserQuery query);
	
	
	@SelectProvider(type=ones.quzhigang.permission.provider.SysUserProvider.class,method="fetchPageAdvanceCount")
	int fetchPageAdvanceCount(SysUserQuery query);

	@Select("select count(1) from tbl_sys_user where telephone = #{telephone} and id <> #{userId} ")
	int countByTelephoneForUp(@Param("telephone") String telephone, @Param("userId") Long userId);

	@Select("select count(1) from tbl_sys_user where  mail = #{mail} and id <> #{userId}")
	int countByMailForUp(@Param("mail") String mail, @Param("userId") Long userId);

	@Select("select count(1) from tbl_sys_user where telephone = #{telephone} ")
	int countByTelephoneForNew(@Param("telephone") String telephone);

	@Select("select count(1) from tbl_sys_user where  mail = #{mail} ")
	int countByMailForNew(@Param("mail") String mail);

	@Select("select "+columns+" from tbl_sys_user where 1=1 and  dept_id=#{deptId} order by username ASC limit #{query.offset}," +
			"#{query.pageSize} ")
	@ResultMap(value="ones.quzhigang.permission.mapper.SysUserMapper.SysUserModelMap")
	List<SysUserModel> getPageByDeptId(@Param("deptId") Integer deptId,@Param("query") PageQuery query);

	@Select("select count(1) from tbl_sys_user where dept_id = #{deptId} ")
	int countByDeptId(@Param("deptId") Integer deptId);

	@Select("select "+columns+" from tbl_sys_user where 1=1 and  id in (${ids})  ")
	@ResultMap(value="ones.quzhigang.permission.mapper.SysUserMapper.SysUserModelMap")
	List<SysUserModel> getByIds(@Param("ids") String ids);

	@Select("select "+columns+" from tbl_sys_user  ")
	@ResultMap(value="ones.quzhigang.permission.mapper.SysUserMapper.SysUserModelMap")
	List<SysUserModel> getAllUser();

	@Select("select "+getUser_By_RoleId_columns+" from tbl_sys_user t1 " +
			"where t1.id in ( select t2.user_id from tbl_sys_role_user t2 where t2.role_id in(${roleIds})) ")
	@ResultMap(value="ones.quzhigang.permission.mapper.SysUserMapper.SysUserModelMap")
	List<SysUserModel> getUserByRoleIds(@Param("roleIds") String roleIds);


}