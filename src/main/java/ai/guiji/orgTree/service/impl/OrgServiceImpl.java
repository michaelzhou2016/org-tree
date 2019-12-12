package ai.guiji.orgTree.service.impl;

import ai.guiji.orgTree.dto.OrgTree;
import ai.guiji.orgTree.dto.SubOrg;
import ai.guiji.orgTree.entity.SysOrganization;
import ai.guiji.orgTree.entity.SysOrganizationExample;
import ai.guiji.orgTree.mapper.SysOrganizationMapper;
import ai.guiji.orgTree.service.OrgService;
import ai.guiji.orgTree.utils.OrganizationConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
