package ai.guiji.orgTree.service.impl;

import ai.guiji.orgTree.dto.OrgTree;
import ai.guiji.orgTree.dto.SubOrg;
import ai.guiji.orgTree.entity.SysOrganization;
import ai.guiji.orgTree.entity.SysOrganizationExample;
import ai.guiji.orgTree.mapper.SysOrganizationMapper;
import ai.guiji.orgTree.service.OrgService;
import ai.guiji.orgTree.utils.OrganizationConvert;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrgServiceImpl implements OrgService {
    @Autowired
    private SysOrganizationMapper sysOrganizationMapper;

    @Override
    public List<OrgTree> getAllOrgTree() {

        List<SysOrganization> orgList = sysOrganizationMapper.selectByExample(new SysOrganizationExample());
        List<OrgTree> list = OrganizationConvert.listToTreeVoList(orgList);

        List<OrgTree> treeVos = new ArrayList<>();
        for (OrgTree treeVo : list) {
            if (treeVo.getCode().equals("1")) {
                treeVos.add(findChildren(treeVo, list));
            }
        }

        return treeVos;
    }

    /**
     * 获取上一级组织
     * 系统的上一级就是自己
     */
    public String getParentOrgCode(String orgCode) {
        if ("1".equals(orgCode)) {
            return orgCode;
        }

        String tripEnd = orgCode.substring(0, orgCode.lastIndexOf("."));
        String parentOrgCode = tripEnd.substring(0, tripEnd.lastIndexOf(".") + 1);
        return "1.".equals(parentOrgCode) ? "1" : parentOrgCode;
    }

    @Override
    public List<OrgTree> getAllOrgTree2() {
        List<SysOrganization> orgList = sysOrganizationMapper.selectByExample(new SysOrganizationExample());
        List<OrgTree> list = OrganizationConvert.listToTreeVoList(orgList);

        if (CollectionUtils.isNotEmpty(list)) {
            Map<String, OrgTree> map = list.stream().collect(Collectors.toMap(OrgTree::getCode, Function.identity()));
            list.forEach(orgTree -> {
                String code = orgTree.getCode();
                if (code.equals("1")) {
                    return;
                }

                String parentCode = getParentOrgCode(code);
                if (map.containsKey(parentCode)) {
                    OrgTree parentOrg = map.get(parentCode);
                    parentOrg.addChild(orgTree);
                    parentOrg.addSubOrg(new SubOrg(orgTree.getId(), orgTree.getOrgName(), orgTree.getCode()));
                    if (Objects.nonNull(orgTree.getSubOrgList())) {
                        parentOrg.addSubOrgList(orgTree.getSubOrgList());
                    }
                    map.remove(code);
                    map.put(parentCode, parentOrg);
                }
            });

            return map.values().stream().sorted(Comparator.comparing(OrgTree::getLevel)).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * 递归获取树形结果列表
     *
     * @param tree
     * @param list
     * @return
     */
    public OrgTree findChildren(OrgTree tree, List<OrgTree> list) {
        for (OrgTree vo : list) {
            if (vo.getCode().startsWith(tree.getCode())) {
                if (tree.getSubOrgList() == null) {
                    tree.setSubOrgList(new ArrayList<>());
                }
                tree.getSubOrgList().add(new SubOrg(vo.getId(), vo.getOrgName(), vo.getCode()));

                if (isChild(tree.getCode(), vo.getCode())) {
                    if (tree.getChildren() == null) {
                        tree.setChildren(new ArrayList<>());
                    }
                    tree.getChildren().add(findChildren(vo, list));
                }
            }
        }

        return tree;
    }

    public boolean isChild(String pcode, String childCode) {

        if (childCode.startsWith(pcode)) {
            String endCode = childCode.substring(pcode.length());
            if (endCode.length() > 0) {
                int count = 0;
                char[] charArr = endCode.toCharArray();
                for (int i = 0; i < charArr.length; i++) {
                    if (charArr[i] == '.') {
                        count++;
                    }
                }
                if (!pcode.equals("1") && count == 1) {
                    return true;
                } else if (pcode.equals("1") && count == 2) {
                    return true;
                }
            }
        }

        return false;

    }

    /*
        Map<String, OrgRoleInfo> map = new HashMap<>();
		orgInfos.stream().forEach(orgInfo -> map.put(orgInfo.getOrgCode(), orgInfo));
		Comparator<OrgRoleInfo> comparator1 = (o1, o2) -> o2.getOrgCode().split("\\.").length - o1.getOrgCode().split("\\.").length;
		Collections.sort(orgInfos, comparator1);
		for(OrgRoleInfo orgInfo : orgInfos)
		{
			String code = orgInfo.getOrgCode();
			if(code.equals("1")){continue;}
			String parentCode = getParentOrgCode(code);
			if(map.containsKey(parentCode))
			{
				OrgRoleInfo parentOrgInfo = map.get(parentCode);
				parentOrgInfo.addChild(orgInfo);
				map.remove(code);
				map.put(parentCode, parentOrgInfo);
			}
		}
		Comparator<OrgRoleInfo> comparator2 = (o1, o2) -> o1.getOrgCode().split("\\.").length - o2.getOrgCode().split("\\.").length;
		map.values().stream().forEach(orgInfo -> resultList.add(orgInfo));
		Collections.sort(resultList, comparator2);
     */
}
