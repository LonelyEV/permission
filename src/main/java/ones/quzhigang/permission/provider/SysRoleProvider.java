package ones.quzhigang.permission.provider;

import java.io.Serializable;

import ones.quzhigang.permission.query.SysRoleQuery;


public class SysRoleProvider implements Serializable  {

	
	
	
	public String fetchPageAdvance(SysRoleQuery query){  
		StringBuffer sql=new StringBuffer("select * from tbl_sys_role where 1 = 1" );
		   if(query!=null){

		   }
		
		

	
		sql.append(" order by ID desc " );
		
		if(query.getLimit()>0){
			sql.append(" limit "+(query.getPageNumber()-1)*query.getPageSize()+","+query.getPageSize());
		}


		
		
		return sql.toString();
	}
	
	public String fetchPageAdvanceCount(SysRoleQuery query){
		StringBuffer sql=new StringBuffer("select count(*) from tbl_sys_role where 1 = 1" );
		   if(query!=null){

		   }
		
		
		return sql.toString();
	}

	public String countByNameAndId(String name, Long id){
		StringBuffer sql=new StringBuffer("select count(1) from tbl_sys_role where 1 = 1 " +
				"and name = ");
		sql.append("'").append(name).append("'");

		if(id != null){
			sql.append(" and id <> ");
			sql.append(id);
		}

		return sql.toString();

	}
	
	
	

}
