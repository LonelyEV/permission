package ones.quzhigang.permission.service;

import ones.quzhigang.permission.beans.PageQuery;
import ones.quzhigang.permission.beans.PageResult;
import ones.quzhigang.permission.model.*;
import ones.quzhigang.permission.query.SysLogQuery;
import ones.quzhigang.permission.vo.SearchLogQueryVo;

import java.util.List;


public interface SysLogService{
	
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
	 SysLogModel getById(long id);
	
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
	 * @param sysLog
	 * @return
	 * @since JDK 1.8
	 */
	 long insert(SysLogModel sysLog);
	
	/**
	 * 
	 * update:(修改). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author 系统生成 
	 * @param sysLog
	 * @return
	 * @since JDK 1.8
	 */
	 long update(SysLogModel sysLog);

	 void saveDeptLog(SysDeptModel before, SysDeptModel after);

	 void saveUserLog(SysUserModel before, SysUserModel after);

	 void saveAclModuleLog(SysAclModuleModel before, SysAclModuleModel after);

	 void saveAclLog(SysAclModel before, SysAclModel after);

	 void saveRoleLog(SysRoleModel before, SysRoleModel after);

	 void saveRoleAclLog(Long roleId, List<Long> before, List<Long> after);

	 void saveRoleuserLog(Long roleId, List<Long> before, List<Long> after);

	 PageResult<SysLogModel> searchpageList(SearchLogQueryVo queryVo);

	 void recover(Long id);
	
	

}
