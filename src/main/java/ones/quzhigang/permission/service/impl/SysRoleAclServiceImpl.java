package ones.quzhigang.permission.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ones.quzhigang.permission.mapper.SysRoleAclMapper;
import ones.quzhigang.permission.model.SysRoleAclModel;

import ones.quzhigang.permission.query.SysRoleAclQuery;
import ones.quzhigang.permission.service.SysRoleAclService;

    
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

	
	

}
