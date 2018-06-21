package ones.quzhigang.permission.provider;

import java.io.Serializable;

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
	
	
	

}
