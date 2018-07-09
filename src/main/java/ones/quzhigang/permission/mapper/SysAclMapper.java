package ones.quzhigang.permission.mapper;
import java.util.List;

import ones.quzhigang.permission.beans.PageQuery;
import org.apache.ibatis.annotations.*;

import ones.quzhigang.permission.model.SysAclModel;
import ones.quzhigang.permission.query.SysAclQuery;

@Mapper
public interface  SysAclMapper{
	

																																																																																																																				
	String columns="id,code,name,acl_module_id,url,type,status,seq,remark,operator,operate_time,operate_ip";
	
	String insert="code,name,acl_module_id,url,type,status,seq,remark,operator,operate_time,operate_ip";
																																																																																																												
	String property="#{id},#{code},#{name},#{aclModuleId},#{url},#{type},#{status},#{seq},#{remark},#{operator},#{operateTime},#{operateIp}";
	
	String insertProperty="#{code},#{name},#{aclModuleId},#{url},#{type},#{status},#{seq},#{remark},#{operator},#{operateTime},#{operateIp}";
																																																																																																																				
	String update="code=#{code},name=#{name},acl_module_id=#{aclModuleId},url=#{url},type=#{type},status=#{status},seq=#{seq},remark=#{remark},operator=#{operator},operate_time=#{operateTime},operate_ip=#{operateIp}";
	
	@Select("select "+columns+" from tbl_sys_acl where ID=#{id}")
	@ResultMap(value="ones.quzhigang.permission.mapper.SysAclMapper.SysAclModelMap")
	SysAclModel getById(long id);
	
	@Insert("insert into tbl_sys_acl ("+insert+") values ("+insertProperty+")")
	@Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
	long insert(SysAclModel sysAcl);

	@Update("update tbl_sys_acl set "+update+" where ID=#{id}")
	long update(SysAclModel sysAcl);
	
	@Delete("delete from tbl_sys_acl where 1 = 1 and ID=#{id} ")
	void delById(long id);

	@SelectProvider(type=ones.quzhigang.permission.provider.SysAclProvider.class,method="fetchPageAdvance")
	@ResultMap(value="ones.quzhigang.permission.mapper.SysAclMapper.SysAclModelMap")
	List<SysAclModel> fetchPageAdvance(SysAclQuery query);

	@SelectProvider(type=ones.quzhigang.permission.provider.SysAclProvider.class,method="fetchPageAdvanceCount")
	int fetchPageAdvanceCount(SysAclQuery query);

	@Select("select count(1) from tbl_sys_acl where acl_module_id = #{aclModuleId} ")
	int countByAclModuleId(@Param("aclModuleId") Integer aclModuleId);

	@Select("select "+columns+" from tbl_sys_acl where 1=1 and  acl_module_id=#{aclModuleId} order by seq,name ASC  limit #{query.offset}," +
			"#{query.pageSize} ")
	@ResultMap(value="ones.quzhigang.permission.mapper.SysAclMapper.SysAclModelMap")
	List<SysAclModel> getPageByaclModuleId(@Param("aclModuleId") Integer aclModuleId, @Param("query") PageQuery query);

	@SelectProvider(type=ones.quzhigang.permission.provider.SysAclProvider.class,method="countByNameAndAclModuleId")
	int countByNameAndAclModuleId(@Param("aclModuleId") Integer aclModuleId, @Param("name") String name, @Param("id") Long id);

	@Select("select "+columns+" from tbl_sys_acl ")
	@ResultMap(value="ones.quzhigang.permission.mapper.SysAclMapper.SysAclModelMap")
	List<SysAclModel> getAll();

	@Select("select "+columns+" from tbl_sys_acl where id in (${idListStr}) ")
	@ResultMap(value="ones.quzhigang.permission.mapper.SysAclMapper.SysAclModelMap")
	List<SysAclModel> getByIdList(@Param("idListStr") String idListStr);

	// url is not null and url <> ''  #{url} REGEXP url  匹配 path请求的URL
	@Select("select "+columns+" from tbl_sys_acl where id url = #{url} ")
	@ResultMap(value="ones.quzhigang.permission.mapper.SysAclMapper.SysAclModelMap")
	List<SysAclModel> getByUrl(@Param("url") String url);


	
	
}