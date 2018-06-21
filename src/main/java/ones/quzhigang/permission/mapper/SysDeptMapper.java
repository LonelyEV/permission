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

import ones.quzhigang.permission.model.SysDeptModel;
import ones.quzhigang.permission.query.SysDeptQuery;

@Mapper
public interface  SysDeptMapper{
	

																																																																																																																				
	public String columns="id,name,parent_id,level,seq,remark,operator,operate_time,operate_ip";
	
	public String insert="name,parent_id,level,seq,remark,operator,operate_time,operate_ip";
																																																																																																												
	public String property="#{id},#{name},#{parentId},#{level},#{seq},#{remark},#{operator},#{operateTime},#{operateIp}";
	
	public String insertProperty="#{name},#{parentId},#{level},#{seq},#{remark},#{operator},#{operateTime},#{operateIp}";
																																																																																																																				
	public String update="name=#{name},parent_id=#{parentId},level=#{level},seq=#{seq},remark=#{remark},operator=#{operator},operate_time=#{operateTime},operate_ip=#{operateIp}";
	
	@Select("select "+columns+" from tbl_sys_dept where ID=#{id}")
	@ResultMap(value="ones.quzhigang.permission.mapper.SysDeptMapper.SysDeptModelMap")
	public SysDeptModel getById(long id);
	
	@Insert("insert into tbl_sys_dept ("+insert+") values ("+insertProperty+")")
	@SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
	public long insert(SysDeptModel sysDept);

	@Update("update tbl_sys_dept set "+update+" where ID=#{id}")
	public long update(SysDeptModel sysDept); 
	
	@Delete("delete from tbl_sys_dept where 1 = 1 and ID=#{id} ")
	public void delById(long id);

	@SelectProvider(type=ones.quzhigang.permission.provider.SysDeptProvider.class,method="fetchPageAdvance")
	@ResultMap(value="ones.quzhigang.permission.mapper.SysDeptMapper.SysDeptModelMap")
	public List<SysDeptModel> fetchPageAdvance(SysDeptQuery query);  
	
	
	@SelectProvider(type=ones.quzhigang.permission.provider.SysDeptProvider.class,method="fetchPageAdvanceCount")
	public int fetchPageAdvanceCount(SysDeptQuery query);
	
	
	
	
}