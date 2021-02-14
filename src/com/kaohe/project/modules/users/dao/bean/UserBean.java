package com.kaohe.project.modules.users.dao.bean;


import com.kaohe.project.modules.users.entity.Department;
import com.kaohe.project.modules.users.entity.Menu;
import com.kaohe.project.modules.users.entity.Role;

import java.util.Date;
import java.util.List;

public class UserBean {
	private Integer id;

	private Integer departmentId;

	private String userName;

	private String fullName;

	private String type;

	private String email;

	private Integer status;

	private Integer createById;

	private Integer updateById;
	private Date createTime;
	private Date updateTime;

	private String remake;

	private Date lastLoginTime;

	private Department department;

	private List<Role> roles; // 拥有的角色列表

	private List<Menu> menus; // 拥有的菜单列表

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public String getRemake() {
		return remake;
	}

	public void setRemake(String remake) {
		this.remake = remake;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	@Override
	public String toString() {
		return "UserBean [id=" + id + ", departmentId=" + departmentId
				+ ", userName=" + userName + ", fullName=" + fullName
				+ ", type=" + type + ", email=" + email + ", status=" + status
				+ ", createById=" + createById + ", updateById=" + updateById
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", remake=" + remake + ", lastLoginTime=" + lastLoginTime
				+ ", department=" + department + ", roles=" + roles
				+ ", menus=" + menus + "]";
	}

}
