package ones.quzhigang.permission.service.impl;


import java.util.Date;
import java.util.List;

import com.google.common.base.Preconditions;
import com.sun.tools.javac.comp.Todo;
import ones.quzhigang.permission.beans.PageQuery;
import ones.quzhigang.permission.beans.PageResult;
import ones.quzhigang.permission.common.BeanValidator;
import ones.quzhigang.permission.common.RequestHolder;
import ones.quzhigang.permission.exception.ParamException;
import ones.quzhigang.permission.utils.IpUtil;
import ones.quzhigang.permission.utils.MD5Util;
import ones.quzhigang.permission.utils.PasswordUtil;
import ones.quzhigang.permission.utils.SimpleDataFormatUtil;
import ones.quzhigang.permission.vo.UserVo;
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
	public long insert(UserVo userVo){
    	// 参数效验
		BeanValidator.check(userVo);
		// 判断电话号码是否使用
		if(checkTelephoneExist(userVo.getTelephone(), userVo.getId(), true)){
			throw new ParamException("电话号码已被占用");
		}
		// 判断邮箱是否使用
		if(checkEmailExist(userVo.getMail(), userVo.getId(), true)){
			throw new ParamException("邮箱地址已被占用");
		}
		// 构建 SysUser对象
		SysUserModel sysUserModel = SysUserModel.builder().username(userVo.getUsername()).telephone(userVo.getTelephone())
				.mail(userVo.getMail()).deptId(userVo.getDeptId()).status(userVo.getStatus())
				.remark(userVo.getRemark()).build();

		//随机密码并MD5加密
		String password = MD5Util.encrypt(PasswordUtil.randomPassword());

		sysUserModel.setPassword(password);
		sysUserModel.setOperator(RequestHolder.getCurrentUser().getUsername());
		sysUserModel.setOperateIp(IpUtil.getUserIP(RequestHolder.getCurrentRequest()));
		sysUserModel.setOperateTime(SimpleDataFormatUtil.format(new Date(), SimpleDataFormatUtil.DEFAULT_PATTERN));

		// Todo: 发送邮件

		// 插入数据库
		return sysUserMapper.insert(sysUserModel);

	}

	private boolean checkTelephoneExist(String telephone, Long userId, boolean newFlag){

    	if(newFlag){
			return sysUserMapper.countByTelephoneForNew(telephone) > 0;
		}
    	return sysUserMapper.countByTelephoneForUp(telephone, userId) > 0;

	}

	private boolean checkEmailExist(String mail, Long userId, boolean newFlag){

    	if(newFlag){
			return sysUserMapper.countByMailForNew(mail) > 0;
		}

    	return sysUserMapper.countByMailForUp(mail, userId) > 0;
	}
	
	//修改
    @Override
	public long update(UserVo userVo){

		// 参数效验
		BeanValidator.check(userVo);
		// 判断电话号码是否使用
		if(checkTelephoneExist(userVo.getTelephone(), userVo.getId(), false)){
			throw new ParamException("电话号码已被占用");
		}
		// 判断邮箱是否使用
		if(checkEmailExist(userVo.getMail(), userVo.getId(), false)){
			throw new ParamException("邮箱地址已被占用");
		}

		SysUserModel before = sysUserMapper.getById(userVo.getId());
		Preconditions.checkNotNull(before,"待更新的用户不存在");


		// 构建 SysUser对象
		SysUserModel after = SysUserModel.builder().username(userVo.getUsername()).telephone(userVo.getTelephone())
				.mail(userVo.getMail()).deptId(userVo.getDeptId()).status(userVo.getStatus())
				.remark(userVo.getRemark()).build();

		after.setOperator("system");
		after.setOperateIp("");
		after.setOperateTime(SimpleDataFormatUtil.format(new Date(), SimpleDataFormatUtil.DEFAULT_PATTERN));


		return sysUserMapper.update(after);
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

	@Override
	public SysUserModel findByKeyWords(String keyWords) {
		return sysUserMapper.findByKeyWords(keyWords);
	}

	@Override
	public PageResult<SysUserModel> getPageByDeptId(Integer deptId, PageQuery query) {

    	BeanValidator.check(query);

    	int count = countByDeptId(deptId);

    	if(count > 0){
    		List<SysUserModel> SysUserModelList = sysUserMapper.getPageByDeptId(deptId, query);
    		return PageResult.<SysUserModel>builder().total(count).data(SysUserModelList).build();
		}


		return PageResult.<SysUserModel>builder().build();
	}

	@Override
	public int countByDeptId(Integer deptId) {
		return sysUserMapper.countByDeptId(deptId);
	}


}
