package ones.quzhigang.permission.service.impl;


import java.util.Date;
import java.util.List;

import com.google.common.base.Preconditions;
import ones.quzhigang.permission.common.BeanValidator;
import ones.quzhigang.permission.common.RequestHolder;
import ones.quzhigang.permission.exception.PermissionException;
import ones.quzhigang.permission.utils.IpUtil;
import ones.quzhigang.permission.utils.SimpleDataFormatUtil;
import ones.quzhigang.permission.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ones.quzhigang.permission.mapper.SysRoleMapper;
import ones.quzhigang.permission.model.SysRoleModel;

import ones.quzhigang.permission.query.SysRoleQuery;
import ones.quzhigang.permission.service.SysRoleService;


@Service
public class SysRoleServiceImpl implements SysRoleService {
	@Autowired
	private SysRoleMapper sysRoleMapper;


	//根据ID查询指定的数据
	@Override
	public SysRoleModel getById(long id) {
		return sysRoleMapper.getById(id);
	}

	//删除
	@Override
	public void delById(long id) {
		sysRoleMapper.delById(id);
	}

	//新增
	@Override
	public long insert(RoleVo vo) {

		// 效验参数
		BeanValidator.check(vo);
		// 判断是否存在相同名称的角色
		if(checkExist(vo.getName(), vo.getId())){
			throw new PermissionException("角色名称已存在");
		}
		// 组装参数
		SysRoleModel sysRoleModel = SysRoleModel.builder().name(vo.getName()).type(vo.getType()).status(vo.getStatus())
				.remark(vo.getRemark()).build();
		sysRoleModel.setOperator(RequestHolder.getCurrentUser().getUsername());
        sysRoleModel.setOperateIp(IpUtil.getUserIP(RequestHolder.getCurrentRequest()));
        sysRoleModel.setOperateTime(SimpleDataFormatUtil.format(new Date(), SimpleDataFormatUtil.DEFAULT_PATTERN));

		// 持久化对象
        long result = sysRoleMapper.insert(sysRoleModel);
		// 记录操作日志

		return result;
	}

	private boolean checkExist(String name, Long id){
		return sysRoleMapper.countByNameAndId(name, id) > 0;
	}

	//修改
	@Override
	public long update(RoleVo vo) {
        // 效验参数
        BeanValidator.check(vo);
        // 判断是否存在相同名称的角色
        if(checkExist(vo.getName(), vo.getId())){
            throw new PermissionException("角色名称已存在");
        }

        // 判断数据库是否存在对应的记录
        SysRoleModel before = sysRoleMapper.getById(vo.getId());
        Preconditions.checkNotNull(before, "带更新的角色不存在");


        // 组装参数
        SysRoleModel after = SysRoleModel.builder().id(vo.getId()).name(vo.getName()).type(vo.getType()).status(vo.getStatus())
                .remark(vo.getRemark()).build();
        after.setOperator(RequestHolder.getCurrentUser().getUsername());
        after.setOperateIp(IpUtil.getUserIP(RequestHolder.getCurrentRequest()));
        after.setOperateTime(SimpleDataFormatUtil.format(new Date(), SimpleDataFormatUtil.DEFAULT_PATTERN));

        // 持久化对象
        long result = sysRoleMapper.update(after);
        // 记录操作日志

        return result;
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

	@Override
    public List<SysRoleModel> getAll(){
	    return sysRoleMapper.getAll();
    }

	
	

}
