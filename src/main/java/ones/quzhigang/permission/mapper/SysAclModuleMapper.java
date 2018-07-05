package ones.quzhigang.permission.mapper;
import java.util.List;
import java.util.Map;

import ones.quzhigang.permission.model.SysDeptModel;
import org.apache.ibatis.annotations.*;

import ones.quzhigang.permission.model.SysAclModuleModel;
import ones.quzhigang.permission.query.SysAclModuleQuery;

@Mapper
public interface  SysAclModuleMapper{
	

																																																																																																																				
	public String columns="id,name,parent_id,level,seq,status,remark,operator,operate_time,operate_ip";
	
	public String insert="name,parent_id,level,seq,status,remark,operator,operate_time,operate_ip";
																																																																																																												
	public String property="#{id},#{name},#{parentId},#{level},#{seq},#{status},#{remark},#{operator},#{operateTime},#{operateIp}";
	
	public String insertProperty="#{name},#{parentId},#{level},#{seq},#{status},#{remark},#{operator},#{operateTime},#{operateIp}";
																																																																																																																				
	public String update="name=#{name},parent_id=#{parentId},level=#{level},seq=#{seq},status=#{status},remark=#{remark},operator=#{operator},operate_time=#{operateTime},operate_ip=#{operateIp}";
	
	@Select("select "+columns+" from tbl_sys_acl_module where ID=#{id}")
	@ResultMap(value="ones.quzhigang.permission.mapper.SysAclModuleMapper.SysAclModuleModelMap")
	public SysAclModuleModel getById(long id);
	
	@Insert("insert into tbl_sys_acl_module ("+insert+") values ("+insertProperty+")")
	@SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
	public long insert(SysAclModuleModel sysAclModule);

	@Update("update tbl_sys_acl_module set "+update+" where ID=#{id}")
	public long update(SysAclModuleModel sysAclModule); 
	
	@Delete("delete from tbl_sys_acl_module where 1 = 1 and ID=#{id} ")
	public void delById(long id);

	@SelectProvider(type=ones.quzhigang.permission.provider.SysAclModuleProvider.class,method="fetchPageAdvance")
	@ResultMap(value="ones.quzhigang.permission.mapper.SysAclModuleMapper.SysAclModuleModelMap")
	public List<SysAclModuleModel> fetchPageAdvance(SysAclModuleQuery query);  
	
	
	@SelectProvider(type=ones.quzhigang.permission.provider.SysAclModuleProvider.class,method="fetchPageAdvanceCount")
	public int fetchPageAdvanceCount(SysAclModuleQuery query);

	@Select("select "+columns+" from tbl_sys_acl_module")
	@ResultMap(value="ones.quzhigang.permission.mapper.SysAclModuleMapper.SysAclModuleModelMap")
	List<SysAclModuleModel> getAllAclModules();

	@Select("select "+columns+" from tbl_sys_acl_module where level like #{level} || '.%'")
	@ResultMap(value="ones.quzhigang.permission.mapper.SysAclModuleMapper.SysAclModuleModelMap")
	List<SysAclModuleModel> getChildAclModuleByLevel(@Param("level") String level);


	@UpdateProvider(type=ones.quzhigang.permission.provider.SysAclModuleProvider.class,method="batchUpdateLevel")
	void batchUpdateLevel(Map<String, List<SysAclModuleModel>> map);

	@SelectProvider(type=ones.quzhigang.permission.provider.SysAclModuleProvider.class,method="countByNameAndParentId")
	int countByNameAndParentId(@Param("parentId") Integer parentId, @Param("name") String name, @Param("id") Long id);
	
	
	
	
}