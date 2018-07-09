package ones.quzhigang.permission.service.impl;


import java.util.*;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import ones.quzhigang.permission.beans.LogType;
import ones.quzhigang.permission.common.JsonMapper;
import ones.quzhigang.permission.common.RequestHolder;
import ones.quzhigang.permission.mapper.SysLogMapper;
import ones.quzhigang.permission.mapper.SysUserMapper;
import ones.quzhigang.permission.model.SysLogModel;
import ones.quzhigang.permission.model.SysUserModel;
import ones.quzhigang.permission.service.SysLogService;
import ones.quzhigang.permission.utils.IpUtil;
import ones.quzhigang.permission.utils.SimpleDataFormatUtil;
import ones.quzhigang.permission.utils.StringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ones.quzhigang.permission.mapper.SysRoleUserMapper;
import ones.quzhigang.permission.model.SysRoleUserModel;

import ones.quzhigang.permission.query.SysRoleUserQuery;
import ones.quzhigang.permission.service.SysRoleUserService;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SysRoleUserServiceImpl implements SysRoleUserService {
    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysLogMapper sysLogMapper;

    //根据ID查询指定的数据
    @Override
    public SysRoleUserModel getById(long id) {
        return sysRoleUserMapper.getById(id);
    }

    //删除
    @Override
    public void delById(long id) {
        sysRoleUserMapper.delById(id);
    }

    //新增
    @Override
    public long insert(SysRoleUserModel sysRoleUser) {
        return sysRoleUserMapper.insert(sysRoleUser);
    }

    //修改
    @Override
    public long update(SysRoleUserModel sysRoleUser) {
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

    @Override
    public List<SysUserModel> getListByRoleId(Long roleId) {

        List<Long> userIdList = sysRoleUserMapper.getUserIdByRoleId(roleId);

        if(CollectionUtils.isEmpty(userIdList)){
            return Lists.newArrayList();
        }

        // List<Long>  ==>>  List<String>
        List<String> listIds = userIdList.stream().map(s -> String.valueOf(s)).collect(Collectors.toList());
        // List<String> ==>> "1,2,3,4..."
        String ids = String.join(",", listIds);

        List<SysUserModel> userList = sysUserMapper.getByIds(ids);

        if(CollectionUtils.isEmpty(userList)){
            return Lists.newArrayList();
        }

        return userList;
    }

    @Override
    public void changeRoleUsers(Long roleId, String userIds) {

        List<Long> ids = StringUtil.splitToListInt(userIds);
        List<String> tempIds = ids.stream().map(s -> String.valueOf(s)).collect(Collectors.toList());

        List<Long> userIdList = sysRoleUserMapper.getUserIdByRoleId(roleId);

        if(CollectionUtils.isNotEmpty(userIdList)){

            if(userIdList.size() == ids.size()){

                Set<Long> originUserIdSet = Sets.newHashSet(userIdList);
                Set<Long> userIdSet = Sets.newHashSet(ids);

                originUserIdSet.removeAll(userIdSet);

                if(CollectionUtils.isEmpty(originUserIdSet)){
                    return;
                }
            }
        }

        updateRoleUsers(roleId, ids);
        saveRoleuserLog(roleId, userIdList, ids);
    }

    @SuppressWarnings("AlibabaTransactionMustHaveRollback")
    @Transactional
    private void updateRoleUsers(Long roleId, List<Long> userIdList){

        sysRoleUserMapper.deleteByRoleId(roleId);

        if(CollectionUtils.isEmpty(userIdList)){
            return;
        }

        List<SysRoleUserModel> sysRoleUserModelList = Lists.newArrayList();

        for(Long userId :userIdList){

            SysRoleUserModel sysRoleAclModel =SysRoleUserModel.builder().roleId(Integer.valueOf(String.valueOf(roleId)))
                    .userId(Integer.valueOf(String.valueOf(userId)))
                    .operator(RequestHolder.getCurrentUser().getUsername())
                    .operateIp(IpUtil.getUserIP(RequestHolder.getCurrentRequest()))
                    .operateTime(SimpleDataFormatUtil.format(new Date(), SimpleDataFormatUtil.DEFAULT_PATTERN)).build();

            sysRoleUserModelList.add(sysRoleAclModel);
        }

        Map<String, List<SysRoleUserModel>> batchInsertMap = new HashMap<>();
        batchInsertMap.put("list", sysRoleUserModelList);
        if(MapUtils.isNotEmpty(batchInsertMap)){
            sysRoleUserMapper.batchInsert(batchInsertMap);
        }
    }

    private void saveRoleuserLog(Long roleId, List<Long> before, List<Long> after) {

        Integer targetId = Integer.valueOf(String.valueOf(roleId));
        String oldValue = before == null ? "" : JsonMapper.obj2String(before);
        String newValue = after == null ? "" : JsonMapper.obj2String(after);

        SysLogModel sysLogModel = SysLogModel.builder().type(LogType.TYPE_ROLE_USER)
                .targetId(targetId).oldValue(oldValue).newValue(newValue)
                .operator(RequestHolder.getCurrentUser().getUsername())
                .operateIp(IpUtil.getUserIP(RequestHolder.getCurrentRequest()))
                .operateTime(SimpleDataFormatUtil.format(new Date(), SimpleDataFormatUtil.DEFAULT_PATTERN)).status(1)
                .build();

        sysLogMapper.insert(sysLogModel);
    }


}
