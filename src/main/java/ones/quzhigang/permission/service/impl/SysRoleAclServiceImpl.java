package ones.quzhigang.permission.service.impl;


import java.util.*;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import ones.quzhigang.permission.common.RequestHolder;
import ones.quzhigang.permission.utils.IpUtil;
import ones.quzhigang.permission.utils.SimpleDataFormatUtil;
import ones.quzhigang.permission.utils.StringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ones.quzhigang.permission.mapper.SysRoleAclMapper;
import ones.quzhigang.permission.model.SysRoleAclModel;

import ones.quzhigang.permission.query.SysRoleAclQuery;
import ones.quzhigang.permission.service.SysRoleAclService;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SysRoleAclServiceImpl implements SysRoleAclService{
	@Autowired
	private SysRoleAclMapper sysRoleAclMapper;

	
	//根据ID查询指定的数据
    @Override
	public SysRoleAclModel getById(long id){ 
		return sysRoleAclMapper.getById(id);
	}
	
	//删除
    @Override
	public void delById(long id){
	    		sysRoleAclMapper.delById(id);
	}
	
	//新增
    @Override
	public long insert(SysRoleAclModel sysRoleAcl){	
	    		return sysRoleAclMapper.insert(sysRoleAcl);
		
	}
	
	//修改
    @Override
	public long update(SysRoleAclModel sysRoleAcl){
	    		return sysRoleAclMapper.update(sysRoleAcl);
	}
	
	//高级查询 
	@Override
	public List<SysRoleAclModel> fetchPageAdvance(SysRoleAclQuery query) {
	    		return sysRoleAclMapper.fetchPageAdvance(query);
	}
	
	//高级查询总记录数
	@Override
	public int fetchPageAdvanceCount(SysRoleAclQuery query) {
	    		return sysRoleAclMapper.fetchPageAdvanceCount(query);
	}



	@Override
	public void changeAcls(Long roleId, String aclsIds) {

		List<Long> ids = StringUtil.splitToListInt(aclsIds);
		List<String> tempIds = ids.stream().map(s -> String.valueOf(s)).collect(Collectors.toList());
		String idsCommaSeparated = String.join(",", tempIds);

    	List<Long> originAclIdList = sysRoleAclMapper.getAclIdListByRoleIdList("'"+roleId+"'");

    	if(CollectionUtils.isNotEmpty(originAclIdList)){

    		if(originAclIdList.size() == ids.size()){

				Set<Long> originAclIdSet = Sets.newHashSet(originAclIdList);
				Set<Long> aclIdSet = Sets.newHashSet(ids);

				originAclIdSet.removeAll(aclIdSet);

				if(CollectionUtils.isEmpty(originAclIdSet)){
					return;
				}
			}
		}
		updateRoleAcls(roleId, ids);
	}

	@SuppressWarnings("AlibabaTransactionMustHaveRollback")
	@Transactional
	public void updateRoleAcls(Long roleId, List<Long> aclsIds){

		sysRoleAclMapper.deleteByRoleId(roleId);

		if(CollectionUtils.isEmpty(aclsIds)){
			return;
		}

		List<SysRoleAclModel> sysRoleAclModelList = Lists.newArrayList();

		for(Long aclId :aclsIds){

			SysRoleAclModel sysRoleAclModel =SysRoleAclModel.builder().roleId(Integer.valueOf(String.valueOf(roleId)))
					.aclId(Integer.valueOf(String.valueOf(aclId)))
					.operator(RequestHolder.getCurrentUser().getUsername())
					.operateIp(IpUtil.getUserIP(RequestHolder.getCurrentRequest()))
					.operateTime(SimpleDataFormatUtil.format(new Date(), SimpleDataFormatUtil.DEFAULT_PATTERN)).build();

			sysRoleAclModelList.add(sysRoleAclModel);
		}

		Map<String, List<SysRoleAclModel>> batchInsertMap = new HashMap<>();
		batchInsertMap.put("list", sysRoleAclModelList);
		if(MapUtils.isNotEmpty(batchInsertMap)){
			sysRoleAclMapper.batchInsert(batchInsertMap);
		}
	}


}
