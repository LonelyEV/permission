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
import ones.quzhigang.permission.mapper.SysDeptMapper;
import ones.quzhigang.permission.model.SysDeptModel;
import ones.quzhigang.permission.utils.LevelUtil;
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
    private SysDeptMapper SysDeptMapper;

    public List<DepartmentLevelVo> deptTree(){

        List<SysDeptModel> sysDeptModelList = SysDeptMapper.getAllDepartments();

        List<DepartmentLevelVo> departmentLevelVoList = Lists.newArrayList();

        for(SysDeptModel dept : sysDeptModelList){
            DepartmentLevelVo departmentLevelVo = DepartmentLevelVo.adapt(dept);
            departmentLevelVoList.add(departmentLevelVo);

        }

        return departmentLevelVoList;
    }

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
        Collections.sort(rootList, new Comparator<DepartmentLevelVo>() {
            @Override
            public int compare(DepartmentLevelVo o1, DepartmentLevelVo o2) {
                return o1.getSeq() - o2.getSeq();
            }
        });


        //递归生成树
        transformDeptTree(departmentLevelVoList, LevelUtil.ROOT, levelDepartmentMap);
        return rootList;

    }

    public void transformDeptTree(List<DepartmentLevelVo> departmentLevelVoList,
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


}
