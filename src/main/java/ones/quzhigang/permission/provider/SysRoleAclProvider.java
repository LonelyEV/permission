package ones.quzhigang.permission.provider;

import java.io.Serializable;

import ones.quzhigang.permission.query.SysRoleAclQuery;


public class SysRoleAclProvider implements Serializable  {

	
	
	
	public String fetchPageAdvance(SysRoleAclQuery query){  
		StringBuffer sql=new StringBuffer("select * from tbl_sys_role_acl where 1 = 1" );
		   if(query!=null){

		   }
		
		

	
		sql.append(" order by ID desc " );
		
		if(query.getLimit()>0){
			sql.append(" limit "+(query.getPageNumber()-1)*query.getPageSize()+","+query.getPageSize());
		}


		
		
		return sql.toString();
	}
	
	public String fetchPageAdvanceCount(SysRoleAclQuery query){
		StringBuffer sql=new StringBuffer("select count(*) from tbl_sys_role_acl where 1 = 1" );
		   if(query!=null){

		   }
		
		
		return sql.toString();
	}
	
	
	

}
