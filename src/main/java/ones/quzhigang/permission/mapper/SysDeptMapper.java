package ones.quzhigang.permission.mapper;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;

import ones.quzhigang.permission.model.SysDeptModel;
import ones.quzhigang.permission.query.SysDeptQuery;

@Mapper
public interface  SysDeptMapper{
	

																																																																																																																				
	String columns="id,name,parent_id,level,seq,remark,operator,operate_time,operate_ip";
	
	String insert="name,parent_id,level,seq,remark,operator,operate_time,operate_ip";
																																																																																																												
	String property="#{id},#{name},#{parentId},#{level},#{seq},#{remark},#{operator},#{operateTime},#{operateIp}";
	
	String insertProperty="#{name},#{parentId},#{level},#{seq},#{remark},#{operator},#{operateTime},#{operateIp}";
																																																																																																																				
	String update="name=#{name},parent_id=#{parentId},level=#{level},seq=#{seq},remark=#{remark},operator=#{operator},operate_time=#{operateTime},operate_ip=#{operateIp}";
	
	@Select("select "+columns+" from tbl_sys_dept where ID=#{id}")
	@ResultMap(value="ones.quzhigang.permission.mapper.SysDeptMapper.SysDeptModelMap")
	SysDeptModel getById(long id);
	
	@Insert("insert into tbl_sys_dept ("+insert+") values ("+insertProperty+")")
	@SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
	long insert(SysDeptModel sysDept);

	@Update("update tbl_sys_dept set "+update+" where ID=#{id}")
	long update(SysDeptModel sysDept);
	
	@Delete("delete from tbl_sys_dept where 1 = 1 and ID=#{id} ")
	void delById(long id);

	@SelectProvider(type=ones.quzhigang.permission.provider.SysDeptProvider.class,method="fetchPageAdvance")
	@ResultMap(value="ones.quzhigang.permission.mapper.SysDeptMapper.SysDeptModelMap")
	List<SysDeptModel> fetchPageAdvance(SysDeptQuery query);
	
	
	@SelectProvider(type=ones.quzhigang.permission.provider.SysDeptProvider.class,method="fetchPageAdvanceCount")
	int fetchPageAdvanceCount(SysDeptQuery query);

	@Select("select "+columns+" from tbl_sys_dept")
	@ResultMap(value="ones.quzhigang.permission.mapper.SysDeptMapper.SysDeptModelMap")
	List<SysDeptModel> getAllDepartments();

	@Select("select "+columns+" from tbl_sys_dept where level like #{level} || '.%'")
	@ResultMap(value="ones.quzhigang.permission.mapper.SysDeptMapper.SysDeptModelMap")
	List<SysDeptModel> getChildDepartmentByLevel(@Param("level") String level);


	@UpdateProvider(type=ones.quzhigang.permission.provider.SysDeptProvider.class,method="batchUpdateLevel")
	void batchUpdateLevel(Map<String, List<SysDeptModel>> map);

	@SelectProvider(type=ones.quzhigang.permission.provider.SysDeptProvider.class,method="countByNameAndParentId")
	int countByNameAndParentId(@Param("parentId") long parentId, @Param("name") String name, @Param("id") Long id);
	
	
}