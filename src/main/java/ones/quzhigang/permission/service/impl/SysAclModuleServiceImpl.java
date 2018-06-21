package ones.quzhigang.permission.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ones.quzhigang.permission.mapper.SysAclModuleMapper;
import ones.quzhigang.permission.model.SysAclModuleModel;

import ones.quzhigang.permission.query.SysAclModuleQuery;
import ones.quzhigang.permission.service.SysAclModuleService;

    
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
	public long insert(SysAclModuleModel sysAclModule){	
	    		return sysAclModuleMapper.insert(sysAclModule);
		
	}
	
	//修改
    @Override
	public long update(SysAclModuleModel sysAclModule){
	    		return sysAclModuleMapper.update(sysAclModule);
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
