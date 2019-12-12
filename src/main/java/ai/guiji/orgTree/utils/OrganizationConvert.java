package ai.guiji.orgTree.utils;

import ai.guiji.orgTree.dto.OrgTree;
import ai.guiji.orgTree.entity.SysOrganization;

import java.util.List;
import java.util.stream.Collectors;

public class OrganizationConvert {

    public static OrgTree entityToTreeDto(SysOrganization sysOrganization) {
        OrgTree orgTree = new OrgTree();
        orgTree.setId(sysOrganization.getId());
        orgTree.setOrgName(sysOrganization.getName());
        orgTree.setCode(sysOrganization.getCode());

        return orgTree;
    }

    public static List<OrgTree> listToTreeVoList(List<SysOrganization> list) {
        if (list == null) {
            return null;
        }

        return list.stream().map(sysOrganization -> entityToTreeDto(sysOrganization)).collect(Collectors.toList());
    }
}
