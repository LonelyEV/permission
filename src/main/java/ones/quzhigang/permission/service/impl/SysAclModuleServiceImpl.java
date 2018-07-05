package ones.quzhigang.permission.service.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ones.quzhigang.permission.common.BeanValidator;
import ones.quzhigang.permission.common.RequestHolder;
import ones.quzhigang.permission.exception.PermissionException;
import ones.quzhigang.permission.model.SysDeptModel;
import ones.quzhigang.permission.utils.IpUtil;
import ones.quzhigang.permission.utils.LevelUtil;
import ones.quzhigang.permission.utils.SimpleDataFormatUtil;
import ones.quzhigang.permission.vo.AclModuleVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ones.quzhigang.permission.mapper.SysAclModuleMapper;
import ones.quzhigang.permission.model.SysAclModuleModel;

import ones.quzhigang.permission.query.SysAclModuleQuery;
import ones.quzhigang.permission.service.SysAclModuleService;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SysAclModuleServiceImpl implements SysAclModuleService{
	@Autowired
	private SysAclModuleMapper sysAclModuleMapper;

	
	//根据ID查询指定的数据
    @Override
	public SysAclModuleModel getById(long id){ 
		return sysAclModuleMapper.getById(id);
	}
	
	//删除
    @Override
	public void delById(long id){
	    		sysAclModuleMapper.delById(id);
	}
	
	//新增
    @Override
	public long insert(AclModuleVo vo){


    	// 参数效验
		BeanValidator.check(vo);
		// 判断同意级别下是否有相同的权限模块
		if(checkExist(vo.getParentId(), vo.getName(), vo.getId())){
			throw new PermissionException("同一层级下不能存在相同名称的权限模块");
		}
		// 组装参数
		SysAclModuleModel sysAclModuleModel = SysAclModuleModel.builder().name(vo.getName()).parentId(vo.getParentId())
				.seq(vo.getSeq()).status(vo.getStatus()).remark(vo.getRemark()).build();
		sysAclModuleModel.setLevel(LevelUtil.calculatelevel(getLevel(vo.getParentId()), vo.getParentId()));
		sysAclModuleModel.setOperator(RequestHolder.getCurrentUser().getUsername());
		sysAclModuleModel.setOperateIp(IpUtil.getUserIP(RequestHolder.getCurrentRequest()));
		sysAclModuleModel.setOperateTime(SimpleDataFormatUtil.format(new Date(), SimpleDataFormatUtil.DEFAULT_PATTERN));

		// 持久化数据
		long result = sysAclModuleMapper.insert(sysAclModuleModel);
		// 记录操作日志



		return result;
		
	}

	private boolean checkExist(Integer parentId, String name, Long id){
    	return sysAclModuleMapper.countByNameAndParentId(parentId, name, id) > 0;
	}
	
	//修改
    @Override
	public long update(AclModuleVo vo){

		// 参数效验
		BeanValidator.check(vo);
		// 判断同意级别下是否有相同的权限模块
		if(checkExist(vo.getParentId(), vo.getName(), vo.getId())){
			throw new PermissionException("同意层级下不能存在相同名称的权限模块");
		}

		SysAclModuleModel before = sysAclModuleMapper.getById(vo.getId());

		if(before == null){
			throw new PermissionException("要更新的权限模块不存在");
		}


		// 组装参数
		SysAclModuleModel after = SysAclModuleModel.builder().id(vo.getId()).name(vo.getName()).parentId(vo.getParentId())
				.seq(vo.getSeq()).status(vo.getStatus()).remark(vo.getRemark()).build();
		after.setLevel(LevelUtil.calculatelevel(getLevel(vo.getParentId()),vo.getParentId()));
		after.setOperator(RequestHolder.getCurrentUser().getUsername());
		after.setOperateIp(IpUtil.getUserIP(RequestHolder.getCurrentRequest()));
		after.setOperateTime(SimpleDataFormatUtil.format(new Date(), SimpleDataFormatUtil.DEFAULT_PATTERN));

		// 持久化数据
		updateWithChild(before, after);

		//记录操作日志

		return sysAclModuleMapper.update(after);
	}

	private String getLevel(long id){
		SysAclModuleModel sysAclModuleModel = sysAclModuleMapper.getById(id);

		if(sysAclModuleModel == null){
			return null;
		}

		return sysAclModuleModel.getLevel();

	}

	@Transactional
	private void updateWithChild(SysAclModuleModel before, SysAclModuleModel after){

		String newLevelPrefix = after.getLevel();
		String oldLevelPrefix = before.getLevel();

		if(!after.getLevel().equals(before.getLevel())){
			List<SysAclModuleModel> sysAclModuleModelList = sysAclModuleMapper.getChildAclModuleByLevel(before.getLevel() + "." + before.getId() + "%");
			if(CollectionUtils.isNotEmpty(sysAclModuleModelList)){
				for(SysAclModuleModel sysAclModuleModel : sysAclModuleModelList){
					String level = sysAclModuleModel.getLevel();
					if(level.indexOf((oldLevelPrefix)) == 0){
						level = newLevelPrefix + level.substring(oldLevelPrefix.length());
						sysAclModuleModel.setLevel(level);
					}
				}
				// 批量修改level信息
				Map<String, List<SysAclModuleModel>> updateMap = new HashMap<>();
				updateMap.put("list", sysAclModuleModelList);

				// 待修改的子部门列表不能不空
				if(MapUtils.isNotEmpty(updateMap)){
					sysAclModuleMapper.batchUpdateLevel(updateMap);
				}
			}
		}

		sysAclModuleMapper.update(after);
	}
	
	//高级查询 
	@Override
	public List<SysAclModuleModel> fetchPageAdvance(SysAclModuleQuery query) {
	    		return sysAclModuleMapper.fetchPageAdvance(query);
	}
	
	//高级查询总记录数
	@Override
	public int fetchPageAdvanceCount(SysAclModuleQuery query) {
	    		return sysAclModuleMapper.fetchPageAdvanceCount(query);
	}

	
	

}
