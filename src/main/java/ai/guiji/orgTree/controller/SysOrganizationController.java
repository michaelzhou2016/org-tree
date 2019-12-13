package ai.guiji.orgTree.controller;


import ai.guiji.orgTree.dto.OrgTree;
import ai.guiji.orgTree.service.OrgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    @GetMapping("/getAllOrgTree")
    public List<OrgTree> getAllOrgTree() throws Exception {
        List<OrgTree> treeVos = orgService.getAllOrgTree();
        return treeVos;
    }

    /**
     * 获取所有部门的树形列表
     */
    @GetMapping("/getAllOrgTree2")
    public List<OrgTree> getAllOrgTree2() throws Exception {
        List<OrgTree> treeVos = orgService.getAllOrgTree2();
        return treeVos;
    }
}
