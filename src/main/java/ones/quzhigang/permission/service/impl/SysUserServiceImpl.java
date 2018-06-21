package ones.quzhigang.permission.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ones.quzhigang.permission.mapper.SysUserMapper;
import ones.quzhigang.permission.model.SysUserModel;

import ones.quzhigang.permission.query.SysUserQuery;
import ones.quzhigang.permission.service.SysUserService;

    
@Service
public class SysUserServiceImpl implements SysUserService{
	@Autowired
	private SysUserMapper sysUserMapper;

	
	//根据ID查询指定的数据
    @Override
	public SysUserModel getById(long id){ 
		return sysUserMapper.getById(id);
	}
	
	//删除
    @Override
	public void delById(long id){
	    		sysUserMapper.delById(id);
	}
	
	//新增
    @Override
	public long insert(SysUserModel sysUser){	
	    		return sysUserMapper.insert(sysUser);
		
	}
	
	//修改
    @Override
	public long update(SysUserModel sysUser){
	    		return sysUserMapper.update(sysUser);
	}
	
	//高级查询 
	@Override
	public List<SysUserModel> fetchPageAdvance(SysUserQuery query) {
	    		return sysUserMapper.fetchPageAdvance(query);
	}
	
	//高级查询总记录数
	@Override
	public int fetchPageAdvanceCount(SysUserQuery query) {
	    		return sysUserMapper.fetchPageAdvanceCount(query);
	}

	
	

}
