/**
 * Copyright (C), 2018, 上海米袋融资有限公司
 * ProjectName: permission
 * FileName: SysCoreService
 * Author:   屈志刚
 * Date:     2018/7/6 0006 11:14
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package ones.quzhigang.permission.service;

import com.google.common.collect.Lists;
import ones.quzhigang.permission.beans.CacheKeyConstants;
import ones.quzhigang.permission.common.JsonMapper;
import ones.quzhigang.permission.common.RequestHolder;
import ones.quzhigang.permission.mapper.SysAclMapper;
import ones.quzhigang.permission.mapper.SysRoleAclMapper;
import ones.quzhigang.permission.mapper.SysRoleUserMapper;
import ones.quzhigang.permission.model.SysAclModel;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SysCoreService {

    @Autowired
    private SysAclMapper sysAclMapper;

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    @Autowired
    private SysRoleAclMapper sysRoleAclMapper;

    @Autowired
    private SysCacheService sysCacheService;

    public List<SysAclModel> getCurrentUserAclList(){
        long userId = RequestHolder.getCurrentUser().getId();
        return getuserAclList(userId);
    }

    public List<SysAclModel> getRoleAclList(long roleId){



        List<Long> aclIdList =  sysRoleAclMapper.getAclIdListByRoleIdList("'"+roleId+"'");

        if(CollectionUtils.isEmpty(aclIdList)){
            return Lists.newArrayList();
        }

        // List<Long>  ==>>  List<String>
        List<String> listIds = aclIdList.stream().map(s -> String.valueOf(s)).collect(Collectors.toList());
        // List<String> ==>> "1,2,3,4..."
        String citiesCommaSeparated = String.join(",", listIds);
        return sysAclMapper.getByIdList(citiesCommaSeparated);
    }

    public List<SysAclModel> getuserAclList(long userId){

        if(isSuperAdmin()){
            return sysAclMapper.getAll();
        }

        List<Long> userRoleIdList = sysRoleUserMapper.getRoleIdListByuser(userId);

        if(CollectionUtils.isEmpty(userRoleIdList)){
            return Lists.newArrayList();
        }
        List<String> ids = userRoleIdList.stream().map(s -> String.valueOf(s)).collect(Collectors.toList());
        String idsCommaSeparated = String.join(",", ids);
        List<Long> userAclIdList = sysRoleAclMapper.getAclIdListByRoleIdList(idsCommaSeparated);

        if(CollectionUtils.isEmpty(userAclIdList)){
            return Lists.newArrayList();
        }

        List<String> listIds = userAclIdList.stream().map(s -> String.valueOf(s)).collect(Collectors.toList());
        String citiesCommaSeparated = String.join(",", (CharSequence) listIds);
        return sysAclMapper.getByIdList(citiesCommaSeparated);

    }

    private boolean isSuperAdmin(){
        return true;
    }

    public boolean hasUrlAcl(String url){

        if(isSuperAdmin()){
            return true;
        }

        List<SysAclModel> sysAclModelList = sysAclMapper.getByUrl(url);

        if(CollectionUtils.isEmpty(sysAclModelList)){
            return true;
        }

        List<SysAclModel> currentUserAclList = getCurrentUserAclFormCache();
        Set<Long> currentUserAclSet = currentUserAclList.stream().map(sysAclModel -> sysAclModel.getId()).collect(Collectors.toSet());

        boolean haoValidAcl = false;

        for (SysAclModel sysAclModel : sysAclModelList){
            if(sysAclModel == null || sysAclModel.getStatus() != 1){
                continue;
            }
            haoValidAcl = true;
            if(currentUserAclSet.contains(sysAclModel.getId())){
                return true;
            }
        }

        if(!haoValidAcl){
            return true;
        }
        return false;
    }

    public List<SysAclModel> getCurrentUserAclFormCache(){

        Long userId = RequestHolder.getCurrentUser().getId();

        String cacheValue = sysCacheService.getFromCache(CacheKeyConstants.USER_ACLS, String.valueOf(userId));

        if(StringUtils.isBlank(cacheValue)){
            List<SysAclModel> sysAclModelList = getCurrentUserAclList();
            if(CollectionUtils.isNotEmpty(sysAclModelList)){
                sysCacheService.saveCache(JsonMapper.obj2String(sysAclModelList), 600, CacheKeyConstants.USER_ACLS, String.valueOf(userId));
            }
            return sysAclModelList;
        }
        return JsonMapper.string2Obj(cacheValue, new TypeReference<List<SysAclModel>>() {
        });
    }
}
