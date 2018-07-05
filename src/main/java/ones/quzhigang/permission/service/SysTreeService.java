/**
 * Copyright (C), 2018, 上海米袋融资有限公司
 * ProjectName: permission
 * FileName: SysTreeService
 * Author:   屈志刚
 * Date:     2018/6/22 0022 11:06
 * Description: 系统树形菜单
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package ones.quzhigang.permission.service;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import ones.quzhigang.permission.mapper.SysAclModuleMapper;
import ones.quzhigang.permission.mapper.SysDeptMapper;
import ones.quzhigang.permission.model.SysAclModuleModel;
import ones.quzhigang.permission.model.SysDeptModel;
import ones.quzhigang.permission.utils.LevelUtil;
import ones.quzhigang.permission.vo.AclModuleLevelVo;
import ones.quzhigang.permission.vo.DepartmentLevelVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class SysTreeService {

    @Autowired
    private SysDeptMapper sysDeptMapper;


    @Autowired
    private SysAclModuleMapper sysAclModuleMapper;

    /**
     * 功能描述: <br>
     * 〈生成权限模块树〉
     *
     * @param
     * @return: java.util.List<ones.quzhigang.permission.vo.AclModuleLevelVo>
     * @@throws:
     * @Author: 屈志刚
     * @Date: 2018/7/5 0005 13:41
     */
    public List<AclModuleLevelVo> aclModuleTree(){

        List<SysAclModuleModel> sysAclModuleModelList = sysAclModuleMapper.getAllAclModules();

        List<AclModuleLevelVo> aclModuleLevelVoList = Lists.newArrayList();

        for(SysAclModuleModel sysAclModuleModel : sysAclModuleModelList){
            AclModuleLevelVo aclModuleLevelVo = AclModuleLevelVo.acl(sysAclModuleModel);
            aclModuleLevelVoList.add(aclModuleLevelVo);

        }

        return sysAclModuleModelList2Tree(aclModuleLevelVoList);

    }

    /**
     * 功能描述: <br>
     * 〈sysAclModuleModelList 转树结构〉
     *
     * @param aclModuleLevelVoList
     * @return: java.util.List<ones.quzhigang.permission.vo.AclModuleLevelVo>
     * @@throws:
     * @Version: 1.0.0
     * @Author: 屈志刚
     * @Date: 2018/7/5 0005 13:42
     */
    private List<AclModuleLevelVo> sysAclModuleModelList2Tree(List<AclModuleLevelVo> aclModuleLevelVoList){

        if(CollectionUtils.isEmpty(aclModuleLevelVoList)){
            return Lists.newArrayList();
        }
        // 按照  level --> [dept1,dept2.....]
        Multimap<String, AclModuleLevelVo> levelSysAclModuleMap = ArrayListMultimap.create();

        List<AclModuleLevelVo> rootList = Lists.newArrayList();

        for(AclModuleLevelVo vo : aclModuleLevelVoList){
            levelSysAclModuleMap.put(vo.getLevel(), vo);

            if(LevelUtil.ROOT.equals(vo.getLevel())){
                rootList.add(vo);
            }
        }
        //按照 seq 从大到小排列
        Collections.sort(rootList, aclModuleSeqComparator);
        //递归生成树
        transformAclModuleTree(aclModuleLevelVoList, LevelUtil.ROOT, levelSysAclModuleMap);
        return rootList;
    }

    /**
     * 功能描述: <br>
     * 〈递归生成权限模块树〉
     *
     * @param aclModuleLevelVoList
     * @param level
     * @param multimap
     * @return: void
     * @@throws:
     * @Version: 1.0.0
     * @Author: 屈志刚
     * @Date: 2018/7/5 0005 13:43
     */
    private void transformAclModuleTree(List<AclModuleLevelVo> aclModuleLevelVoList,
                                   String level, Multimap<String, AclModuleLevelVo> multimap){

        for(int i=0; i<aclModuleLevelVoList.size(); i++){
            // 遍历该层的每一个元素
            AclModuleLevelVo aclModuleLevelVo = aclModuleLevelVoList.get(i);
            // 处理当前层级的数据
            String nextLevel = LevelUtil.calculatelevel(level, aclModuleLevelVo.getId());
            // 处理下一层
            List<AclModuleLevelVo> temp = (List<AclModuleLevelVo>) multimap.get(nextLevel);

            if(CollectionUtils.isNotEmpty(temp)){
                // 排序
                Collections.sort(temp, new Comparator<AclModuleLevelVo>() {
                    @Override
                    public int compare(AclModuleLevelVo o1, AclModuleLevelVo o2) {
                        return o1.getSeq() - o2.getSeq();
                    }
                });
                // 设置下一层部门
                aclModuleLevelVo.setAclModuleList(temp);
                // 进入到下一层处理
                transformAclModuleTree(temp, nextLevel, multimap);
            }
        }
    }




    /**
     * 功能描述: <br>
     * 〈生成部门树〉
     *
     * @param
     * @return: java.util.List<ones.quzhigang.permission.vo.DepartmentLevelVo>
     * @@throws:
     * @Version: 1.0.0
     * @Author: 屈志刚
     * @Date: 2018/7/5 0005 13:43
     */
    public List<DepartmentLevelVo> deptTree(){

        List<SysDeptModel> sysDeptModelList = sysDeptMapper.getAllDepartments();

        List<DepartmentLevelVo> departmentLevelVoList = Lists.newArrayList();

        for(SysDeptModel dept : sysDeptModelList){
            DepartmentLevelVo departmentLevelVo = DepartmentLevelVo.adapt(dept);
            departmentLevelVoList.add(departmentLevelVo);
        }

        return departmentList2Tree(departmentLevelVoList);
    }

    /**
     * 功能描述: <br>
     * 〈departmentLevelVoList 转 树结构〉
     *
     * @param departmentLevelVoList
     * @return: java.util.List<ones.quzhigang.permission.vo.DepartmentLevelVo>
     * @@throws:
     * @Version: 1.0.0
     * @Author: 屈志刚
     * @Date: 2018/7/5 0005 13:44
     */
    public List<DepartmentLevelVo> departmentList2Tree(List<DepartmentLevelVo> departmentLevelVoList){

        if(CollectionUtils.isEmpty(departmentLevelVoList)){
            return Lists.newArrayList();
        }

        // 按照  level --> [dept1,dept2.....]
        Multimap<String, DepartmentLevelVo> levelDepartmentMap = ArrayListMultimap.create();

        List<DepartmentLevelVo> rootList = Lists.newArrayList();

        for(DepartmentLevelVo vo : departmentLevelVoList){
            levelDepartmentMap.put(vo.getLevel(), vo);

            if(LevelUtil.ROOT.equals(vo.getLevel())){
                rootList.add(vo);
            }
        }

        //按照 seq 从大到小排列
        Collections.sort(rootList, deptSeqComparator);
        //递归生成树
        transformDeptTree(departmentLevelVoList, LevelUtil.ROOT, levelDepartmentMap);
        return rootList;
    }

    /**
     * 功能描述: <br>
     * 〈递归生成部门树〉
     *
     * @param departmentLevelVoList
     * @param level
     * @param multimap
     * @return: void
     * @@throws:
     * @Version: 1.0.0
     * @Author: 屈志刚
     * @Date: 2018/7/5 0005 13:45
     */
    private void transformDeptTree(List<DepartmentLevelVo> departmentLevelVoList,
                                  String level, Multimap<String, DepartmentLevelVo> multimap){

        for(int i=0; i<departmentLevelVoList.size(); i++){
            // 遍历该层的每一个元素
            DepartmentLevelVo departmentLevelVo = departmentLevelVoList.get(i);
            // 处理当前层级的数据
            String nextLevel = LevelUtil.calculatelevel(level, departmentLevelVo.getId());
            // 处理下一层
            List<DepartmentLevelVo> temp = (List<DepartmentLevelVo>) multimap.get(nextLevel);

            if(CollectionUtils.isNotEmpty(temp)){
                // 排序
                Collections.sort(temp, new Comparator<DepartmentLevelVo>() {
                    @Override
                    public int compare(DepartmentLevelVo o1, DepartmentLevelVo o2) {
                        return o1.getSeq() - o2.getSeq();
                    }
                });
                // 设置下一层部门
                departmentLevelVo.setDepartmentLevelList(temp);
                // 进入到下一层处理
                transformDeptTree(temp, nextLevel, multimap);
            }
        }
    }

    /**
     * 功能描述: <br>
     * 〈部门数据比较器〉
     *
     * @param null
     * @return:
     * @@throws:
     * @Version: 1.0.0
     * @Author: 屈志刚
     * @Date: 2018/7/5 0005 13:45
     */
    private Comparator<DepartmentLevelVo> deptSeqComparator = new Comparator<DepartmentLevelVo>() {
        @Override
        public int compare(DepartmentLevelVo o1, DepartmentLevelVo o2) {
            return o1.getSeq() - o2.getSeq();
        }
    };

    /**
     * 功能描述: <br>
     * 〈权限模块比较器〉
     *
     * @param null
     * @return:
     * @@throws:
     * @Version: 1.0.0
     * @Author: 屈志刚
     * @Date: 2018/7/5 0005 13:46
     */
    private Comparator<AclModuleLevelVo> aclModuleSeqComparator = new Comparator<AclModuleLevelVo>() {

        @Override
        public int compare(AclModuleLevelVo o1, AclModuleLevelVo o2) {
            return o1.getSeq() - o2.getSeq();
        }
    };
}
