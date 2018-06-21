package ones.quzhigang.permission.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ones.quzhigang.permission.mapper.SysRoleUserMapper;
import ones.quzhigang.permission.model.SysRoleUserModel;

import ones.quzhigang.permission.query.SysRoleUserQuery;
import ones.quzhigang.permission.service.SysRoleUserService;

    
@Service
public class SysRoleUserServiceImpl implements SysRoleUserService{
	@Autowired
	private SysRoleUserMapper sysRoleUserMapper;

	
	//根据ID查询指定的数据
    @Override
	public SysRoleUserModel getById(long id){ 
		return sysRoleUserMapper.getById(id);
	}
	
	//删除
    @Override
	public void delById(long id){
	    		sysRoleUserMapper.delById(id);
	}
	
	//新增
    @Override
	public long insert(SysRoleUserModel sysRoleUser){	
	    		return sysRoleUserMapper.insert(sysRoleUser);
		
	}
	
	//修改
    @Override
	public long update(SysRoleUserModel sysRoleUser){
	    		return sysRoleUserMapper.update(sysRoleUser);
	}
	
	//高级查询 
	@Override
	public List<SysRoleUserModel> fetchPageAdvance(SysRoleUserQuery query) {
	    		return sysRoleUserMapper.fetchPageAdvance(query);
	}
	
	//高级查询总记录数
	@Override
	public int fetchPageAdvanceCount(SysRoleUserQuery query) {
	    		return sysRoleUserMapper.fetchPageAdvanceCount(query);
	}

	
	

}
