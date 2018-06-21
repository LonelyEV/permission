package ones.quzhigang.permission.provider;

import java.io.Serializable;

import ones.quzhigang.permission.query.SysDeptQuery;


public class SysDeptProvider implements Serializable  {

	
	
	
	public String fetchPageAdvance(SysDeptQuery query){  
		StringBuffer sql=new StringBuffer("select * from tbl_sys_dept where 1 = 1" );
		   if(query!=null){

		   }
		
		

	
		sql.append(" order by ID desc " );
		
		if(query.getLimit()>0){
			sql.append(" limit "+(query.getPageNumber()-1)*query.getPageSize()+","+query.getPageSize());
		}


		
		
		return sql.toString();
	}
	
	public String fetchPageAdvanceCount(SysDeptQuery query){
		StringBuffer sql=new StringBuffer("select count(*) from tbl_sys_dept where 1 = 1" );
		   if(query!=null){

		   }
		
		
		return sql.toString();
	}
	
	
	

}
