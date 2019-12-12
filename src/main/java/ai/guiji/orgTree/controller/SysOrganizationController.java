package ai.guiji.orgTree.controller;


import ai.guiji.orgTree.dto.OrgTree;
import ai.guiji.orgTree.service.OrgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/sysOrganization")
public class SysOrganizationController {

    @Autowired
    private OrgService orgService;

    /**
     * 获取所有部门的树形列表
     */
    @PostMapping("/getAllOrgTree")
    public List<OrgTree> getAllDepartmentTree() throws Exception {
        List<OrgTree> treeVos = orgService.getAllOrgTree();
        return treeVos;
    }
}
