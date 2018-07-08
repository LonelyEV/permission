package ones.quzhigang.permission.provider;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import ones.quzhigang.permission.model.SysRoleUserModel;
import ones.quzhigang.permission.query.SysRoleUserQuery;


public class SysRoleUserProvider implements Serializable  {

	
	
	
	public String fetchPageAdvance(SysRoleUserQuery query){  
		StringBuffer sql=new StringBuffer("select * from tbl_sys_role_user where 1 = 1" );
		   if(query!=null){

		   }
		
		

	
		sql.append(" order by ID desc " );
		
		if(query.getLimit()>0){
			sql.append(" limit "+(query.getPageNumber()-1)*query.getPageSize()+","+query.getPageSize());
		}


		
		
		return sql.toString();
	}
	
	public String fetchPageAdvanceCount(SysRoleUserQuery query){
		StringBuffer sql=new StringBuffer("select count(*) from tbl_sys_role_user where 1 = 1" );
		   if(query!=null){

		   }
		
		
		return sql.toString();
	}

	public String batchInsert(Map<String, List<SysRoleUserModel>> map) {
		List<SysRoleUserModel> list = map.get("list");
		if(list==null||list.isEmpty()){
			throw new RuntimeException("SysRoleUserProvider.batchInsert 批量保存角色权限参数不能为空！");
		}

		StringBuffer sql = new StringBuffer("insert into tbl_sys_role_user(role_id,user_id,operator,operate_time,operate_ip) values ");
		MessageFormat messageFormat = new MessageFormat("(#'{'list[{0}].roleId},#'{'list[{0}].userId},#'{'list[{0}].operator},#'{'list[{0}].operateTime},#'{'list[{0}].operateIp})");
		for (int i = 0; i < list.size(); i++) {
			sql.append(messageFormat.format(new Integer[]{i}));
			sql.append(",");
		}
		sql.setLength(sql.length() - 1);
		return sql.toString();
	}
	
	
	

}
