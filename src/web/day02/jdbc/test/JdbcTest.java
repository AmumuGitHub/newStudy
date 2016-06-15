package web.day02.jdbc.test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import org.junit.Test;

import web.day02.jdbc.entity.Emp;
import web.day02.jdbc.util.BeanUtil;
import web.day02.jdbc.util.DbUtil;

public class JdbcTest {
	//@Test
	public void testJb1() {
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
	public void testJb2() {
		Emp emp1=new Emp(1,"苍井空",10000,11);
		Emp emp2=new Emp(2,"松岛枫",10000,22);
		insert(emp1);
		insert(emp2);
	}
	
	//@Test
	public void testJb3(){
		try {
			Connection con=DbUtil.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insert(Object o){
		Connection con = null;
		PreparedStatement ps = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DbUtil.getConnection();
			String sql = BeanUtil.genInsertSql(o.getClass().getName());
			System.out.println("sqlqqqqq=="+sql);
			ps=con.prepareStatement(sql);
			Field[] fields =o.getClass().getDeclaredFields();
			for(int i=0;i<fields.length;i++){
				Field field=fields[i];
				field.setAccessible(true);
				System.out.println("field.getName()"+field.getName());
//				if(!field.getName().equals("id")){
//				  ps.setObject(i+1,field.get(o));
//				}else{
//				  ps.setObject(i+1,UUID.randomUUID());
//				}
			}
			//ps.executeUpdate();// 发送sql并执行
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
	
}
