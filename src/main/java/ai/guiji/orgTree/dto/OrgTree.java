package ai.guiji.orgTree.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrgTree {

    private Integer id;

    private String orgName;

    private String code;

    List<OrgTree> children;
}
