package ones.quzhigang.permission.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ones.quzhigang.permission.mapper.SysDeptMapper;
import ones.quzhigang.permission.model.SysDeptModel;

import ones.quzhigang.permission.query.SysDeptQuery;
import ones.quzhigang.permission.service.SysDeptService;

    
@Service
public class SysDeptServiceImpl implements SysDeptService{
	@Autowired
	private SysDeptMapper sysDeptMapper;

	
	//根据ID查询指定的数据
    @Override
	public SysDeptModel getById(long id){ 
		return sysDeptMapper.getById(id);
	}
	
	//删除
    @Override
	public void delById(long id){
	    		sysDeptMapper.delById(id);
	}
	
	//新增
    @Override
	public long insert(SysDeptModel sysDept){	
	    		return sysDeptMapper.insert(sysDept);
		
	}
	
	//修改
    @Override
	public long update(SysDeptModel sysDept){
	    		return sysDeptMapper.update(sysDept);
	}
	
	//高级查询 
	@Override
	public List<SysDeptModel> fetchPageAdvance(SysDeptQuery query) {
	    		return sysDeptMapper.fetchPageAdvance(query);
	}
	
	//高级查询总记录数
	@Override
	public int fetchPageAdvanceCount(SysDeptQuery query) {
	    		return sysDeptMapper.fetchPageAdvanceCount(query);
	}

	
	

}
