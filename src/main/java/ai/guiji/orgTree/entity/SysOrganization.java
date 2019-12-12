package ai.guiji.orgTree.entity;

import java.util.Date;

/**
 *
 * sys_organization
 * @author zhouliliang
 * @date   2019/12/12
 */
public class SysOrganization {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 组织名称
     */
    private String name;

    /**
     * 组织编码
     */
    private String code;

    /**
     * 组织类型1代理商2企业
     */
    private Integer type;

    /**
     * 机器人数量
     */
    private Integer robot;

    /**
     * 机器人有效起始时间
     */
    private String startDate;

    /**
     * 机器人有效截止时间
     */
    private String endDate;

    /**
     * 线路数量
     */
    private Integer line;

    /**
     * 删除标识0正常1删除
     */
    private Integer delFlag;

    /**
     * 0未开户1已开户
     */
    private Integer open;

    /**
     * 
     */
    private Long createId;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Long updateId;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 子编码
     */
    private String subCode;

    /**
     * 话术数量
     */
    private Integer botstence;

    /**
     * 可用状态：0-不可用；1-可用
     */
    private Integer usable;

    /**
     * 授权起始时间
     */
    private String effectiveDate;

    /**
     * 授权失效时间
     */
    private String invalidDate;

    /**
     * 商务id
     */
    private Integer bussinessId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getRobot() {
        return robot;
    }

    public void setRobot(Integer robot) {
        this.robot = robot;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getOpen() {
        return open;
    }

    public void setOpen(Integer open) {
        this.open = open;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public Integer getBotstence() {
        return botstence;
    }

    public void setBotstence(Integer botstence) {
        this.botstence = botstence;
    }

    public Integer getUsable() {
        return usable;
    }

    public void setUsable(Integer usable) {
        this.usable = usable;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getInvalidDate() {
        return invalidDate;
    }

    public void setInvalidDate(String invalidDate) {
        this.invalidDate = invalidDate;
    }

    public Integer getBussinessId() {
        return bussinessId;
    }

    public void setBussinessId(Integer bussinessId) {
        this.bussinessId = bussinessId;
    }
}