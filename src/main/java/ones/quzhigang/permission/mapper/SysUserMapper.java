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

import ones.quzhigang.permission.model.SysUserModel;
import ones.quzhigang.permission.query.SysUserQuery;

@Mapper
public interface  SysUserMapper{
	

																																																																																																																				
	public String columns="id,username,telephone,mail,password,dept_id,status,remark,operator,operate_time,operate_ip";
	
	public String insert="username,telephone,mail,password,dept_id,status,remark,operator,operate_time,operate_ip";
																																																																																																												
	public String property="#{id},#{username},#{telephone},#{mail},#{password},#{deptId},#{status},#{remark},#{operator},#{operateTime},#{operateIp}";
	
	public String insertProperty="#{username},#{telephone},#{mail},#{password},#{deptId},#{status},#{remark},#{operator},#{operateTime},#{operateIp}";
																																																																																																																				
	public String update="username=#{username},telephone=#{telephone},mail=#{mail},password=#{password},dept_id=#{deptId},status=#{status},remark=#{remark},operator=#{operator},operate_time=#{operateTime},operate_ip=#{operateIp}";
	
	@Select("select "+columns+" from tbl_sys_user where ID=#{id}")
	@ResultMap(value="ones.quzhigang.permission.mapper.SysUserMapper.SysUserModelMap")
	public SysUserModel getById(long id);
	
	@Insert("insert into tbl_sys_user ("+insert+") values ("+insertProperty+")")
	@SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
	public long insert(SysUserModel sysUser);

	@Update("update tbl_sys_user set "+update+" where ID=#{id}")
	public long update(SysUserModel sysUser); 
	
	@Delete("delete from tbl_sys_user where 1 = 1 and ID=#{id} ")
	public void delById(long id);

	@SelectProvider(type=ones.quzhigang.permission.provider.SysUserProvider.class,method="fetchPageAdvance")
	@ResultMap(value="ones.quzhigang.permission.mapper.SysUserMapper.SysUserModelMap")
	public List<SysUserModel> fetchPageAdvance(SysUserQuery query);  
	
	
	@SelectProvider(type=ones.quzhigang.permission.provider.SysUserProvider.class,method="fetchPageAdvanceCount")
	public int fetchPageAdvanceCount(SysUserQuery query);
	
	
	
	
}