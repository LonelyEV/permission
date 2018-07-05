package ones.quzhigang.permission.provider;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import ones.quzhigang.permission.model.SysAclModuleModel;
import ones.quzhigang.permission.model.SysDeptModel;
import ones.quzhigang.permission.query.SysAclModuleQuery;


public class SysAclModuleProvider implements Serializable  {

	
	
	
	public String fetchPageAdvance(SysAclModuleQuery query){  
		StringBuffer sql=new StringBuffer("select * from tbl_sys_acl_module where 1 = 1" );
		   if(query!=null){

		   }
		
		

	
		sql.append(" order by ID desc " );
		
		if(query.getLimit()>0){
			sql.append(" limit "+(query.getPageNumber()-1)*query.getPageSize()+","+query.getPageSize());
		}


		
		
		return sql.toString();
	}
	
	public String fetchPageAdvanceCount(SysAclModuleQuery query){
		StringBuffer sql=new StringBuffer("select count(*) from tbl_sys_acl_module where 1 = 1" );
		   if(query!=null){

		   }
		
		
		return sql.toString();
	}

	public String batchUpdateLevel(Map<String, List<SysAclModuleModel>> map){
		List<SysAclModuleModel> list = map.get("list");

		StringBuffer sql = new StringBuffer();
		sql.append("update tbl_sys_acl_module set level = case id ");
		MessageFormat messageFormat = new MessageFormat("when #'{'list[{0}].id} then #'{'list[{0}].level} ");
		for(int i = 0; i < list.size(); i++) {
			sql.append(messageFormat.format(new Integer[]{i}));
		}
		sql.append("end where id in(");
		messageFormat = new MessageFormat("#'{'list[{0}].id},");
		for(int i = 0; i < list.size(); i++) {
			sql.append(messageFormat.format(new Integer[]{i}));
		}

		sql.setLength(sql.length() - 1);
		return sql.toString() + ")";
	}

	public String countByNameAndParentId(long parentId, String name, Long id){
		StringBuffer sql=new StringBuffer("select count(1) from tbl_sys_acl_module where 1 = 1 " +
				"and name = '"+name+
				"' and parent_id = "+parentId);

		if(id != null){
			sql.append(" and id <> "+id);
		}

		return sql.toString();

	}
	
	
	

}
