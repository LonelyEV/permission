package ones.quzhigang.permission.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ones.quzhigang.permission.mapper.SysRoleMapper;
import ones.quzhigang.permission.model.SysRoleModel;

import ones.quzhigang.permission.query.SysRoleQuery;
import ones.quzhigang.permission.service.SysRoleService;

    
@Service
public class SysRoleServiceImpl implements SysRoleService{
	@Autowired
	private SysRoleMapper sysRoleMapper;

	
	//根据ID查询指定的数据
    @Override
	public SysRoleModel getById(long id){ 
		return sysRoleMapper.getById(id);
	}
	
	//删除
    @Override
	public void delById(long id){
	    		sysRoleMapper.delById(id);
	}
	
	//新增
    @Override
	public long insert(SysRoleModel sysRole){	
	    		return sysRoleMapper.insert(sysRole);
	}
	
	//修改
    @Override
	public long update(SysRoleModel sysRole){
	    		return sysRoleMapper.update(sysRole);
	}
	
	//高级查询 
	@Override
	public List<SysRoleModel> fetchPageAdvance(SysRoleQuery query) {
	    		return sysRoleMapper.fetchPageAdvance(query);
	}
	
	//高级查询总记录数
	@Override
	public int fetchPageAdvanceCount(SysRoleQuery query) {
	    		return sysRoleMapper.fetchPageAdvanceCount(query);
	}

	
	

}
