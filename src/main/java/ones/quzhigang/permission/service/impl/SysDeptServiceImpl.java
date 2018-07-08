package ones.quzhigang.permission.service.impl;


import java.util.*;

import ch.qos.logback.core.joran.spi.NoAutoStart;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import ones.quzhigang.permission.common.BeanValidator;
import ones.quzhigang.permission.common.RequestHolder;
import ones.quzhigang.permission.exception.ParamException;
import ones.quzhigang.permission.exception.PermissionException;
import ones.quzhigang.permission.mapper.SysUserMapper;
import ones.quzhigang.permission.utils.IpUtil;
import ones.quzhigang.permission.utils.LevelUtil;
import ones.quzhigang.permission.utils.SimpleDataFormatUtil;
import ones.quzhigang.permission.vo.DepartmmentVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ones.quzhigang.permission.mapper.SysDeptMapper;
import ones.quzhigang.permission.model.SysDeptModel;

import ones.quzhigang.permission.query.SysDeptQuery;
import ones.quzhigang.permission.service.SysDeptService;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class SysDeptServiceImpl implements SysDeptService {
    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Autowired
    private SysUserMapper sysUserMapper;


    //根据ID查询指定的数据
    @Override
    public SysDeptModel getById(long id) {
        return sysDeptMapper.getById(id);
    }

    //删除
    @Override
    public void delById(long id) {

        SysDeptModel sysDeptModel =  sysDeptMapper.getById(id);
        Preconditions.checkNotNull(sysDeptModel, "待删除的部门不存在，无法删除");

        if(sysDeptMapper.countByPerentId(sysDeptModel.getId()) > 0){
            throw new PermissionException("当前部门下存在子部门，无法删除");
        }

        if(sysUserMapper.countByDeptId(Integer.valueOf(String.valueOf(sysDeptModel.getId()))) > 0){
            throw new PermissionException("当前部门下已存在用户，无法删除");
        }

        sysDeptMapper.delById(id);
    }

    //新增
    @Override
    public long insert(DepartmmentVo vo) {

        BeanValidator.check(vo);


        if (checkExist(vo.getParentId(), vo.getName(), vo.getId())) {
            throw new ParamException("同一层级存在相同名称的部门名称!");
        }

        SysDeptModel sysDeptModel = SysDeptModel.builder().name(vo.getName())
                .parentId(vo.getParentId()).seq(vo.getSeq()).remark(vo.getRemark()).build();

        sysDeptModel.setLevel(LevelUtil.calculatelevel(getLevel(vo.getParentId()), vo.getParentId()));
        sysDeptModel.setOperator(RequestHolder.getCurrentUser().getUsername());
        sysDeptModel.setOperateIp(IpUtil.getUserIP(RequestHolder.getCurrentRequest()));
        sysDeptModel.setOperateTime(SimpleDataFormatUtil.format(new Date(), SimpleDataFormatUtil.DEFAULT_PATTERN));

        return sysDeptMapper.insert(sysDeptModel);

    }

    private boolean checkExist(Integer parentId, String departmentName, Long id) {

        return sysDeptMapper.countByNameAndParentId(parentId, departmentName, id) > 0;
    }

    private String getLevel(long id) {
        SysDeptModel SysDeptModel = sysDeptMapper.getById(id);

        if (SysDeptModel == null) {
            return null;
        }

        return SysDeptModel.getLevel();

    }


    //修改
    @Override
    public long update(DepartmmentVo vo) {

        BeanValidator.check(vo);

        if (checkExist(vo.getParentId(), vo.getName(), vo.getId())) {
            throw new ParamException("同一层级下存在相同名称的部门");
        }

        SysDeptModel before = sysDeptMapper.getById(vo.getId());
        Preconditions.checkNotNull(before, "待更新的部门不存在！");

        SysDeptModel after = SysDeptModel.builder().id(vo.getId()).name(vo.getName())
                .parentId(vo.getParentId()).seq(vo.getSeq()).remark(vo.getRemark()).build();

        after.setLevel(LevelUtil.calculatelevel(getLevel(vo.getParentId()), vo.getParentId()));
        after.setOperator(RequestHolder.getCurrentUser().getUsername());
        after.setOperateIp(IpUtil.getUserIP(RequestHolder.getCurrentRequest()));
        after.setOperateTime(SimpleDataFormatUtil.format(new Date(), SimpleDataFormatUtil.DEFAULT_PATTERN));

        updateWithChild(before, after);

        return sysDeptMapper.update(after);
    }

    @Transactional
    private void updateWithChild(SysDeptModel before, SysDeptModel after) {

        String newLevelPrefix = after.getLevel();
        String oldLevelPrefix = before.getLevel();

        if (!after.getLevel().equals(before.getLevel())) {
            List<SysDeptModel> deptModelList = sysDeptMapper.getChildDepartmentByLevel(before.getLevel() + "." + before.getId() + "%");
            if (CollectionUtils.isNotEmpty(deptModelList)) {
                for (SysDeptModel sysDeptModel : deptModelList) {
                    String level = sysDeptModel.getLevel();
                    if (level.indexOf((oldLevelPrefix)) == 0) {
                        level = newLevelPrefix + level.substring(oldLevelPrefix.length());
                        sysDeptModel.setLevel(level);
                    }
                }
                // 批量修改level信息
                Map<String, List<SysDeptModel>> updateMap = new HashMap<>();
                updateMap.put("list", deptModelList);

                // 待修改的子部门列表不能不空
                if (MapUtils.isNotEmpty(updateMap)) {
                    sysDeptMapper.batchUpdateLevel(updateMap);
                }
            }
        }

        sysDeptMapper.update(after);
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
