package web.day02.jdbc.test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import web.day02.jdbc.entity.Emp;
import web.day02.jdbc.util.BeanUtil;
import web.day02.jdbc.util.DbUtil;

public class JdbcTest {
	// @Test
	public void testInser1() {
		Connection con = null;
		Statement stat = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String user = "scott";
			String password = "tiger";
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:HR";
			con = DriverManager.getConnection(url, user, password);
			stat = con.createStatement();
			String sql = "insert into " + "emp(empno,ename,sal,deptno)"
					+ "values (7503,'bob',8000,10)";
			stat.executeUpdate(sql);// 发送sql并执行
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				stat.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	@Test
	public void testInsert2() {
		Emp emp1 = new Emp(1, "苍老师", 10000.00, 11);
		Emp emp2 = new Emp(2, "松老师", 10000.00, 22);
		insert(emp1);
		insert(emp2);
	}

	// @Test
	public void testSelect1() {
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			con = DbUtil.getConnection();
			stat = con.createStatement();
			String sql = "select id,empno,ename,sal,deptno "
					+ "from amumu_emp order by sal";
			rs = stat.executeQuery(sql);// 执行select
			Emp emp;
			while (rs.next()) {
				String id = rs.getString("id");
				int empno = rs.getInt("empno");
				String ename = rs.getString("ename");
				double sal = rs.getDouble("sal");
				int deptno = rs.getInt("deptno");
				emp = new Emp(empno, ename, sal, deptno);
				emp.setId(id);
				System.out.println(emp);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DbUtil.closeConnection(con, stat, rs);
		}
	}

	@Test
	public void testSelect2() {
		try {
			Emp emp = new Emp();
			List list = select(emp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 插入对象
	public void insert(Object o) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DbUtil.getConnection();
			String sql = BeanUtil.genInsertSql(o.getClass().getName());
			ps = con.prepareStatement(sql);
			Field[] fields = o.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				field.setAccessible(true);
				if (!field.getName().equals("id")) {
					ps.setObject(i + 1, field.get(o));
				} else {
					ps.setObject(i + 1, UUID.randomUUID().toString()
							.replaceAll("-", ""));
				}
			}
			ps.executeUpdate();// 发送sql并执行
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				DbUtil.closeConnection(con, ps);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	// 查询对象
	public List select(Object o) {
		List list = new ArrayList();
		Connection con = null;
		Statement ps = null;
		ResultSet rs = null;
		try {
			con = DbUtil.getConnection();
			String beanName=o.getClass().getName();
			String sql = BeanUtil.genSelectAllSql(beanName);
			ps = con.createStatement();
			rs = ps.executeQuery(sql);
			while (rs.next()) {
				List<String> propertys=BeanUtil.getTypeAndPropertyList(beanName);
				for(int i=0;i<propertys.size();i++){
					String[] str=propertys.get(i).split("`");
					if(str[0].equals("String")){
						 BeanUtil.setProperty(o, str[1], rs.getString(str[1]));
					}else if(str[0].equals("int")){
						 BeanUtil.setProperty(o, str[1], rs.getInt(str[1]));
					}else if(str[0].equals("double")){
						 BeanUtil.setProperty(o, str[1], rs.getDouble(str[1]));
					}
				}
				list.add(o);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
