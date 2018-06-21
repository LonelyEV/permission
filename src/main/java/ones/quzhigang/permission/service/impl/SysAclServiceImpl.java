package ones.quzhigang.permission.service.impl;


import java.util.List;

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
	public long insert(SysAclModel sysAcl){	
	    		return sysAclMapper.insert(sysAcl);
		
	}
	
	//修改
    @Override
	public long update(SysAclModel sysAcl){
	    		return sysAclMapper.update(sysAcl);
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

	
	

}
