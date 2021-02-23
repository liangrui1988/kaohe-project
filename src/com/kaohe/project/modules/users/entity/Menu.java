package com.kaohe.project.modules.users.entity;

import java.util.Date;
import java.util.List;

public class Menu {
    private String id;

    private String name;

    private String types;

    private Integer sortNo;

    private String parentId;

    private String parentIds;

    private String permission;

    /**
     * {@link} MenuStatus 菜单状态  0=正常   1=权限标识  2=停用
     */
    private Integer status;

    private String icon;

    private String href;

    
    private Date createTime;
    private Date updateTime;

    private Integer createById;

    private Integer updateById;
    
	private List<Menu> menus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types == null ? null : types.trim();
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds == null ? null : parentIds.trim();
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href == null ? null : href.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCreateById() {
        return createById;
    }

    public void setCreateById(Integer createById) {
        this.createById = createById;
    }

    public Integer getUpdateById() {
        return updateById;
    }

    public void setUpdateById(Integer updateById) {
        this.updateById = updateById;
    }

    



	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createById == null) ? 0 : createById.hashCode());
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((href == null) ? 0 : href.hashCode());
		result = prime * result + ((icon == null) ? 0 : icon.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((parentId == null) ? 0 : parentId.hashCode());
		result = prime * result
				+ ((parentIds == null) ? 0 : parentIds.hashCode());
		result = prime * result
				+ ((permission == null) ? 0 : permission.hashCode());
		result = prime * result + ((sortNo == null) ? 0 : sortNo.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((types == null) ? 0 : types.hashCode());
		result = prime * result
				+ ((updateById == null) ? 0 : updateById.hashCode());
		result = prime * result
				+ ((updateTime == null) ? 0 : updateTime.hashCode());
		return result;
	}

	
	@Override
	public String toString() {
		return "Menu [id=" + id + ", name=" + name + ", types=" + types
				+ ", sortNo=" + sortNo + ", parentId=" + parentId
				+ ", parentIds=" + parentIds + ", permission=" + permission
				+ ", status=" + status + ", icon=" + icon + ", href=" + href
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", createById=" + createById + ", updateById=" + updateById
				+ "]";
	}
    
    
}