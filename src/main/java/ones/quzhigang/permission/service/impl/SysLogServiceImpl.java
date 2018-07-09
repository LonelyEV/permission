package ones.quzhigang.permission.service.impl;


import java.util.Date;
import java.util.List;

import com.google.common.base.Preconditions;
import ones.quzhigang.permission.beans.LogType;
import ones.quzhigang.permission.beans.PageResult;
import ones.quzhigang.permission.common.BeanValidator;
import ones.quzhigang.permission.common.JsonMapper;
import ones.quzhigang.permission.common.RequestHolder;
import ones.quzhigang.permission.exception.PermissionException;
import ones.quzhigang.permission.mapper.*;
import ones.quzhigang.permission.model.*;
import ones.quzhigang.permission.service.SysRoleAclService;
import ones.quzhigang.permission.service.SysRoleUserService;
import ones.quzhigang.permission.utils.IpUtil;
import ones.quzhigang.permission.utils.SimpleDataFormatUtil;
import ones.quzhigang.permission.vo.SearchLogQueryVo;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ones.quzhigang.permission.service.SysLogService;

    
@Service
public class SysLogServiceImpl implements SysLogService{
	@Autowired
	private SysLogMapper sysLogMapper;

	@Autowired
	private SysDeptMapper sysDeptMapper;

	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Autowired
	private SysAclMapper sysAclMapper;

	@Autowired
	private SysAclModuleMapper sysAclModuleMapper;

	@Autowired
	private SysRoleAclService sysRoleAclService;

	@Autowired
	private SysRoleUserService sysRoleUserService;


	//根据ID查询指定的数据
    @Override
	public SysLogModel getById(long id){ 
		return sysLogMapper.getById(id);
	}

	//删除
    @Override
	public void delById(long id){
	    		sysLogMapper.delById(id);
	}

	//新增
    @Override
	public long insert(SysLogModel sysLog){	
	    		return sysLogMapper.insert(sysLog);
		
	}

	//修改
    @Override
	public long update(SysLogModel sysLog){
	    		return sysLogMapper.update(sysLog);
	}


	@Override
	public void saveDeptLog(SysDeptModel before, SysDeptModel after) {

		Integer targetId = Integer.valueOf(String.valueOf(after == null ? before.getId() : after.getId()));
		String oldValue = before == null ? "" : JsonMapper.obj2String(before);
		String newValue = after == null ? "" : JsonMapper.obj2String(after);

		SysLogModel sysLogModel = SysLogModel.builder().type(LogType.TYPE_DEPT)
				.targetId(targetId).oldValue(oldValue).newValue(newValue)
				.operator(RequestHolder.getCurrentUser().getUsername())
				.operateIp(IpUtil.getUserIP(RequestHolder.getCurrentRequest()))
				.operateTime(SimpleDataFormatUtil.format(new Date(), SimpleDataFormatUtil.DEFAULT_PATTERN)).status(1)
				.build();

		sysLogMapper.insert(sysLogModel);
	}

	@Override
	public void saveUserLog(SysUserModel before, SysUserModel after) {

		Integer targetId = Integer.valueOf(String.valueOf(after == null ? before.getId() : after.getId()));
		String oldValue = before == null ? "" : JsonMapper.obj2String(before);
		String newValue = after == null ? "" : JsonMapper.obj2String(after);

		SysLogModel sysLogModel = SysLogModel.builder().type(LogType.TYPE_USER)
				.targetId(targetId).oldValue(oldValue).newValue(newValue)
				.operator(RequestHolder.getCurrentUser().getUsername())
				.operateIp(IpUtil.getUserIP(RequestHolder.getCurrentRequest()))
				.operateTime(SimpleDataFormatUtil.format(new Date(), SimpleDataFormatUtil.DEFAULT_PATTERN)).status(1)
				.build();

		sysLogMapper.insert(sysLogModel);

	}

	@Override
	public void saveAclModuleLog(SysAclModuleModel before, SysAclModuleModel after) {

		Integer targetId = Integer.valueOf(String.valueOf(after == null ? before.getId() : after.getId()));
		String oldValue = before == null ? "" : JsonMapper.obj2String(before);
		String newValue = after == null ? "" : JsonMapper.obj2String(after);

		SysLogModel sysLogModel = SysLogModel.builder().type(LogType.TYPE_ACL_MODULE)
				.targetId(targetId).oldValue(oldValue).newValue(newValue)
				.operator(RequestHolder.getCurrentUser().getUsername())
				.operateIp(IpUtil.getUserIP(RequestHolder.getCurrentRequest()))
				.operateTime(SimpleDataFormatUtil.format(new Date(), SimpleDataFormatUtil.DEFAULT_PATTERN)).status(1)
				.build();

		sysLogMapper.insert(sysLogModel);
	}

	@Override
	public void saveAclLog(SysAclModel before, SysAclModel after) {

		Integer targetId = Integer.valueOf(String.valueOf(after == null ? before.getId() : after.getId()));
		String oldValue = before == null ? "" : JsonMapper.obj2String(before);
		String newValue = after == null ? "" : JsonMapper.obj2String(after);

		SysLogModel sysLogModel = SysLogModel.builder().type(LogType.TYPE_ACL)
				.targetId(targetId).oldValue(oldValue).newValue(newValue)
				.operator(RequestHolder.getCurrentUser().getUsername())
				.operateIp(IpUtil.getUserIP(RequestHolder.getCurrentRequest()))
				.operateTime(SimpleDataFormatUtil.format(new Date(), SimpleDataFormatUtil.DEFAULT_PATTERN)).status(1)
				.build();

		sysLogMapper.insert(sysLogModel);

	}

	@Override
	public void saveRoleLog(SysRoleModel before, SysRoleModel after) {

		Integer targetId = Integer.valueOf(String.valueOf(after == null ? before.getId() : after.getId()));
		String oldValue = before == null ? "" : JsonMapper.obj2String(before);
		String newValue = after == null ? "" : JsonMapper.obj2String(after);

		SysLogModel sysLogModel = SysLogModel.builder().type(LogType.TYPE_ROLE)
				.targetId(targetId).oldValue(oldValue).newValue(newValue)
				.operator(RequestHolder.getCurrentUser().getUsername())
				.operateIp(IpUtil.getUserIP(RequestHolder.getCurrentRequest()))
				.operateTime(SimpleDataFormatUtil.format(new Date(), SimpleDataFormatUtil.DEFAULT_PATTERN)).status(1)
				.build();

		sysLogMapper.insert(sysLogModel);

	}

	@Override
	public void saveRoleAclLog(Long roleId, List<Long> before, List<Long> after) {

		Integer targetId = Integer.valueOf(String.valueOf(roleId));
		String oldValue = before == null ? "" : JsonMapper.obj2String(before);
		String newValue = after == null ? "" : JsonMapper.obj2String(after);

		SysLogModel sysLogModel = SysLogModel.builder().type(LogType.TYPE_ROLE_ACL)
				.targetId(targetId).oldValue(oldValue).newValue(newValue)
				.operator(RequestHolder.getCurrentUser().getUsername())
				.operateIp(IpUtil.getUserIP(RequestHolder.getCurrentRequest()))
				.operateTime(SimpleDataFormatUtil.format(new Date(), SimpleDataFormatUtil.DEFAULT_PATTERN)).status(1)
				.build();

		sysLogMapper.insert(sysLogModel);
	}

	@Override
	public void saveRoleuserLog(Long roleId, List<Long> before, List<Long> after) {

		Integer targetId = Integer.valueOf(String.valueOf(roleId));
		String oldValue = before == null ? "" : JsonMapper.obj2String(before);
		String newValue = after == null ? "" : JsonMapper.obj2String(after);

		SysLogModel sysLogModel = SysLogModel.builder().type(LogType.TYPE_ROLE_USER)
				.targetId(targetId).oldValue(oldValue).newValue(newValue)
				.operator(RequestHolder.getCurrentUser().getUsername())
				.operateIp(IpUtil.getUserIP(RequestHolder.getCurrentRequest()))
				.operateTime(SimpleDataFormatUtil.format(new Date(), SimpleDataFormatUtil.DEFAULT_PATTERN)).status(1)
				.build();

		sysLogMapper.insert(sysLogModel);
	}

	@Override
	public PageResult<SysLogModel> searchpageList(SearchLogQueryVo query) {

		BeanValidator.check(query);

		int resultCount = sysLogMapper.fetchPageAdvanceCount(query);

		PageResult<SysLogModel> pageResult = null;

		if (resultCount > 0) {
			pageResult = PageResult.<SysLogModel>builder()
					.total(resultCount)
					.data(sysLogMapper.fetchPageAdvance(query))
					.build();

			return pageResult;
		}

		pageResult = PageResult.<SysLogModel>builder()
				.build();

		return pageResult;
	}

	@Override
	public void recover(Long id) {

    	SysLogModel sysLogModel = sysLogMapper.getById(id);
		Preconditions.checkNotNull(sysLogModel, "待还原的记录不存在");

		long roleId = Long.valueOf(String.valueOf(sysLogModel.getTargetId()));

		switch (sysLogModel.getType()){
			case LogType.TYPE_DEPT:

				SysDeptModel beforeDept = sysDeptMapper.getById(sysLogModel.getTargetId());

				Preconditions.checkNotNull(beforeDept, "待更新的部门不存在");

				if(StringUtils.isBlank(sysLogModel.getNewValue()) || StringUtils.isBlank(sysLogModel.getOldValue())){
					throw new PermissionException("新增和删除操作不做还原");
				}

				SysDeptModel afterDept = JsonMapper.string2Obj(sysLogModel.getOldValue(), new TypeReference<SysDeptModel>() {
				});

				afterDept.setOperator(RequestHolder.getCurrentUser().getUsername());
				afterDept.setOperateIp(IpUtil.getUserIP(RequestHolder.getCurrentRequest()));
				afterDept.setOperateTime(SimpleDataFormatUtil.format(new Date(), SimpleDataFormatUtil.DEFAULT_PATTERN));

				sysDeptMapper.update(afterDept);
				saveDeptLog(beforeDept, afterDept);

				break;
			case LogType.TYPE_USER:

				SysUserModel beforeUser = sysUserMapper.getById(sysLogModel.getTargetId());

				Preconditions.checkNotNull(beforeUser, "待更新的用户不存在");

				if(StringUtils.isBlank(sysLogModel.getNewValue()) || StringUtils.isBlank(sysLogModel.getOldValue())){
					throw new PermissionException("新增和删除操作不做还原");
				}

				SysUserModel afterUser = JsonMapper.string2Obj(sysLogModel.getOldValue(), new TypeReference<SysUserModel>() {
				});

				afterUser.setOperator(RequestHolder.getCurrentUser().getUsername());
				afterUser.setOperateIp(IpUtil.getUserIP(RequestHolder.getCurrentRequest()));
				afterUser.setOperateTime(SimpleDataFormatUtil.format(new Date(), SimpleDataFormatUtil.DEFAULT_PATTERN));

				sysUserMapper.update(afterUser);
				saveUserLog(beforeUser, afterUser);

				break;
			case LogType.TYPE_ACL_MODULE:

				SysAclModuleModel beforeAclModule = sysAclModuleMapper.getById(sysLogModel.getTargetId());

				Preconditions.checkNotNull(beforeAclModule, "待更新的权限模块不存在");

				if(StringUtils.isBlank(sysLogModel.getNewValue()) || StringUtils.isBlank(sysLogModel.getOldValue())){
					throw new PermissionException("新增和删除操作不做还原");
				}

				SysAclModuleModel afterAclModule = JsonMapper.string2Obj(sysLogModel.getOldValue(), new TypeReference<SysAclModuleModel>() {
				});

				afterAclModule.setOperator(RequestHolder.getCurrentUser().getUsername());
				afterAclModule.setOperateIp(IpUtil.getUserIP(RequestHolder.getCurrentRequest()));
				afterAclModule.setOperateTime(SimpleDataFormatUtil.format(new Date(), SimpleDataFormatUtil.DEFAULT_PATTERN));

				sysAclModuleMapper.update(afterAclModule);
				saveAclModuleLog(beforeAclModule, afterAclModule);

				break;
			case LogType.TYPE_ACL:

				SysAclModel beforeAcl = sysAclMapper.getById(sysLogModel.getTargetId());

				Preconditions.checkNotNull(beforeAcl, "待更新的权限点不存在");

				if(StringUtils.isBlank(sysLogModel.getNewValue()) || StringUtils.isBlank(sysLogModel.getOldValue())){
					throw new PermissionException("新增和删除操作不做还原");
				}

				SysAclModel afterAcl = JsonMapper.string2Obj(sysLogModel.getOldValue(), new TypeReference<SysAclModel>() {
				});

				afterAcl.setOperator(RequestHolder.getCurrentUser().getUsername());
				afterAcl.setOperateIp(IpUtil.getUserIP(RequestHolder.getCurrentRequest()));
				afterAcl.setOperateTime(SimpleDataFormatUtil.format(new Date(), SimpleDataFormatUtil.DEFAULT_PATTERN));

				sysAclMapper.update(afterAcl);
				saveAclLog(beforeAcl, afterAcl);
				break;
			case LogType.TYPE_ROLE:

				SysRoleModel beforeRole = sysRoleMapper.getById(sysLogModel.getTargetId());

				Preconditions.checkNotNull(beforeRole, "待更新的角色不存在");

				if(StringUtils.isBlank(sysLogModel.getNewValue()) || StringUtils.isBlank(sysLogModel.getOldValue())){
					throw new PermissionException("新增和删除操作不做还原");
				}

				SysRoleModel afterRole = JsonMapper.string2Obj(sysLogModel.getOldValue(), new TypeReference<SysRoleModel>() {
				});

				afterRole.setOperator(RequestHolder.getCurrentUser().getUsername());
				afterRole.setOperateIp(IpUtil.getUserIP(RequestHolder.getCurrentRequest()));
				afterRole.setOperateTime(SimpleDataFormatUtil.format(new Date(), SimpleDataFormatUtil.DEFAULT_PATTERN));

				sysRoleMapper.update(afterRole);
				saveRoleLog(beforeRole, afterRole);

				break;
			case LogType.TYPE_ROLE_ACL:

				SysRoleModel sysRoleModel = sysRoleMapper.getById(roleId);
				Preconditions.checkNotNull(sysRoleModel, "待更新的角色不存在");

				sysRoleAclService.changeAcls(roleId, JsonMapper.string2Obj(sysLogModel.getOldValue(), new TypeReference<String>() {
				}));
				break;
			case LogType.TYPE_ROLE_USER:

				SysRoleUserModel sysRoleUserModel = sysRoleUserService.getById(roleId);
				Preconditions.checkNotNull(sysRoleUserModel, "待更新的角色不存在");

				sysRoleUserService.changeRoleUsers(roleId, JsonMapper.string2Obj(sysLogModel.getOldValue(), new TypeReference<String>() {
				}));
				break;
			default:;

		}



	}
}
