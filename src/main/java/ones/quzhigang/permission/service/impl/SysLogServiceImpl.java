package ones.quzhigang.permission.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ones.quzhigang.permission.mapper.SysLogMapper;
import ones.quzhigang.permission.model.SysLogModel;

import ones.quzhigang.permission.query.SysLogQuery;
import ones.quzhigang.permission.service.SysLogService;

    
@Service
public class SysLogServiceImpl implements SysLogService{
	@Autowired
	private SysLogMapper sysLogMapper;

	
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
	
	//高级查询 
	@Override
	public List<SysLogModel> fetchPageAdvance(SysLogQuery query) {
	    		return sysLogMapper.fetchPageAdvance(query);
	}
	
	//高级查询总记录数
	@Override
	public int fetchPageAdvanceCount(SysLogQuery query) {
	    		return sysLogMapper.fetchPageAdvanceCount(query);
	}

	
	

}
