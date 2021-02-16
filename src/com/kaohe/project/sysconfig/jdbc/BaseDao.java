package com.kaohe.project.sysconfig.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;
/**
 * jdbc基类
 * 
 * @author liangrui
 * @date 2021-02-15
 */
public class BaseDao {

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	// 与数据库建立连接
	public boolean getConnection() {
		String driver = ConfigManager.getInstance().getPropertis("driver");
		String url = ConfigManager.getInstance().getPropertis("url");
		String user = ConfigManager.getInstance().getPropertis("user");
		String password = ConfigManager.getInstance().getPropertis("password");
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	// 所有查询方法的模板
	public ResultSet select(String sql, Object[] obj) {
		getConnection();
		try { // 使用Preparedstatement对象预编译sql
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				ps.setObject(i + 1, obj[i]);
			}
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	// 所有查询方法的模板
	public int getCount(String sql, Object[] obj) {
		getConnection();
		int result = 0;
		try { // 使用Preparedstatement对象预编译sql
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				ps.setObject(i + 1, obj[i]);
			}
			rs = ps.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} 
		return result;
	}

	// 所有增删改方法的模板
	public int update(String sql, Object[] obj) {
		getConnection();
		try {
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				ps.setObject(i + 1, obj[i]);
			}
			int lines = ps.executeUpdate();
			return lines;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}

	}

	/**
	 * 获取主健id
	 * 
	 * @param sql
	 * @param obj
	 * @return
	 */
	public int insertGetId(String sql, Object[] obj) {
		getConnection();
		int id = 0;
		try {
			ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			for (int i = 0; i < obj.length; i++) {
				ps.setObject(i + 1, obj[i]);
			}
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
			return id;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}

	}

	// 释放资源
	public boolean close() {
		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		try {
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public static void main(String[] args) throws SQLException {
		BaseDao b = new BaseDao();
		Object[] obj = { 1, 2 };
		ResultSet result = b.select("", obj);
		String name = "";
		while (result.next()) {
			name = result.getString("name");
		}

	}

}