package ones.quzhigang.permission.service.impl;


import java.util.Date;
import java.util.List;

import com.google.common.base.Preconditions;
import ones.quzhigang.permission.beans.PageQuery;
import ones.quzhigang.permission.beans.PageResult;
import ones.quzhigang.permission.common.BeanValidator;
import ones.quzhigang.permission.common.RequestHolder;
import ones.quzhigang.permission.exception.PermissionException;
import ones.quzhigang.permission.model.SysUserModel;
import ones.quzhigang.permission.utils.IpUtil;
import ones.quzhigang.permission.utils.SimpleDataFormatUtil;
import ones.quzhigang.permission.vo.AclVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ones.quzhigang.permission.mapper.SysAclMapper;
import ones.quzhigang.permission.model.SysAclModel;

import ones.quzhigang.permission.query.SysAclQuery;
import ones.quzhigang.permission.service.SysAclService;

    
@Service
public class SysAclServiceImpl implements SysAclService{
	@Autowired
	private SysAclMapper sysAclMapper;

	
	//根据ID查询指定的数据
    @Override
	public SysAclModel getById(long id){ 
		return sysAclMapper.getById(id);
	}
	
	//删除
    @Override
	public void delById(long id){
	    		sysAclMapper.delById(id);
	}
	
	//新增
    @Override
	public long insert(AclVo vo){

    	// 参数效验
		BeanValidator.check(vo);
		// 判断在同一权限模块下是否存在相同名称的权限点
		if(checkExist(vo.getAclModuleId(), vo.getName(), vo.getId())){
			throw new PermissionException("同一权限模块下是否存在相同名称的权限点");
		}
		// 组装参数
		SysAclModel sysAclModel = SysAclModel.builder().code(generateCode()).name(vo.getName())
				.aclModuleId(vo.getAclModuleId()).url(vo.getUrl()).type(vo.getType())
				.status(vo.getStatus()).seq(vo.getSeq()).remark(vo.getRemark()).build();
		sysAclModel.setOperator(RequestHolder.getCurrentUser().getUsername());
		sysAclModel.setOperateIp(IpUtil.getUserIP(RequestHolder.getCurrentRequest()));
		sysAclModel.setOperateTime(SimpleDataFormatUtil.format(new Date(), SimpleDataFormatUtil.DEFAULT_PATTERN));

		// 持久化到数据库
		long result = sysAclMapper.insert(sysAclModel);
		// 保存操作日志


		return result;
		
	}
	
	//修改
    @Override
	public long update(AclVo vo){

		// 参数效验
		BeanValidator.check(vo);
		// 判断在同一权限模块下是否存在相同名称的权限点
		if(checkExist(vo.getAclModuleId(), vo.getName(), vo.getId())){
			throw new PermissionException("同一权限模块下是否存在相同名称的权限点");
		}

		SysAclModel before = sysAclMapper.getById(vo.getId());

		Preconditions.checkNotNull(before, "带更新的部门不存在");

		// 组装参数
		SysAclModel after = SysAclModel.builder().id(vo.getId()).code(before.getCode()).name(vo.getName())
				.aclModuleId(vo.getAclModuleId()).url(vo.getUrl()).type(vo.getType())
				.status(vo.getStatus()).seq(vo.getSeq()).remark(vo.getRemark()).build();
		after.setOperator(RequestHolder.getCurrentUser().getUsername());
		after.setOperateIp(IpUtil.getUserIP(RequestHolder.getCurrentRequest()));
		after.setOperateTime(SimpleDataFormatUtil.format(new Date(), SimpleDataFormatUtil.DEFAULT_PATTERN));

		// 持久化到数据库
		long result = sysAclMapper.update(after);
		// 保存操作日志

		return result;
	}

	private boolean checkExist(Integer aclModuleId, String name,Long id){
    	return sysAclMapper.countByNameAndAclModuleId(aclModuleId, name, id) > 0;
	}


	private String generateCode(){
		return SimpleDataFormatUtil.format(new Date(), SimpleDataFormatUtil.YYYYMMDDHHMMSS) +"_"+ (int)Math.random() * 100;
	}
	
	//高级查询 
	@Override
	public List<SysAclModel> fetchPageAdvance(SysAclQuery query) {
	    		return sysAclMapper.fetchPageAdvance(query);
	}
	
	//高级查询总记录数
	@Override
	public int fetchPageAdvanceCount(SysAclQuery query) {
	    		return sysAclMapper.fetchPageAdvanceCount(query);
	}

	@Override
	public PageResult<SysAclModel> getPageByAclModuleId(Integer aclModuleId, PageQuery query) {

		BeanValidator.check(query);

		int count = countByAclModuleId(aclModuleId);

		if(count > 0){
			List<SysAclModel> sysAclModelList = sysAclMapper.getPageByaclModuleId(aclModuleId, query);
			return PageResult.<SysAclModel>builder().total(count).data(sysAclModelList).build();
		}

		return PageResult.<SysAclModel>builder().build();
	}

	@Override
	public int countByAclModuleId(Integer aclModuleId) {
		return sysAclMapper.countByAclModuleId(aclModuleId);
	}


}
