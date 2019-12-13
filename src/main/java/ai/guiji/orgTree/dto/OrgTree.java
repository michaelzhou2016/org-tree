package ai.guiji.orgTree.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrgTree {

    private Integer id;

    private String orgName;

    private String code;

    private Integer level;

    List<OrgTree> children;

    List<SubOrg> subOrgList;

    public void addChild(OrgTree child) {
        if (children == null) {
            children = new ArrayList<OrgTree>();
        }

        children.add(child);
    }

    public void addSubOrg(SubOrg subOrg) {
        if (subOrgList == null) {
            subOrgList = new ArrayList<SubOrg>();
        }

        subOrgList.add(subOrg);
    }

    public void addSubOrgList(List<SubOrg> list) {
        if (subOrgList == null) {
            subOrgList = new ArrayList<SubOrg>();
        }

        subOrgList.addAll(list);
    }
}
