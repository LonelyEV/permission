package ones.quzhigang.permission.service;

import ones.quzhigang.permission.model.SysAclModel;
import ones.quzhigang.permission.query.SysAclQuery;

import java.util.List;


public interface SysAclService{
	
    /**
	 * 
	 * getById:(根据ID查询指定的数据). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author 系统生成  
	 * @param id
	 * @return
	 * @since JDK 1.8
	 */
	 SysAclModel getById(long id);
	
	/**
	 * 
	 * del:(根据ID删除). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author 系统生成
	 * @param id
	 * @since JDK 1.8
	 */
	 void delById(long id);
	
	/**
	 * 
	 * insert:(新增并返回ID). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author 系统生成   
	 * @param sysAcl
	 * @return
	 * @since JDK 1.8
	 */
	 long insert(SysAclModel sysAcl);
	
	/**
	 * 
	 * update:(修改). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author 系统生成 
	 * @param sysAcl
	 * @return
	 * @since JDK 1.8
	 */
	 long update(SysAclModel sysAcl);
	
	/**
	 * 
	 * fetchPageAdvance:(高级查询带分页). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author 系统生成   
	 * @param query
	 * @return
	 * @since JDK 1.8
	 */
	 List<SysAclModel> fetchPageAdvance(SysAclQuery query);
	
		/**
	 * 
	 * fetchPageAdvanceCount:(高级查询总记录数). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author 系统生成
	 * @param query
	 * @return
	 * @since JDK 1.8
	 */
	 int fetchPageAdvanceCount(SysAclQuery query);
	
	

}
