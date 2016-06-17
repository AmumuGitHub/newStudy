package web.jdbc.test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.UUID;

import org.junit.Test;

import web.jdbc.util.DbUtil;

public class JdbcTest2 {

	//@Test
	//事物控制
	public void testTran(){
		boolean ok = change("苍老师","松老师",10000);
		if(ok){
			System.out.println("转账成功");
		}else{
			System.out.println("转账失败");
		}
	}
	
	//@Test
	//用事务控制同一批操作的完整性
	public void TestBatchTran(){
		Connection con = null;
		PreparedStatement pst = null;
		try{
			con = DbUtil.getConnection();
			String sql = "insert into amumu_emp (id,empno,ename,sal,deptno)" +
					" values(?,?,?,?,?)";
			con.setAutoCommit(false);//关闭自动提交
			pst = con.prepareStatement(sql);
			pst.setString(1,UUID.randomUUID().toString().replace("-",""));
			pst.setInt(2,3);
			pst.setString(3,"波老师");
			pst.setDouble(4,3003.00);
			pst.setInt(5,33);
			pst.addBatch();//加入一组参数
			pst.setString(1,UUID.randomUUID().toString().replace("-",""));
			pst.setInt(2,4);
			pst.setString(3,"苍老师_2");
			pst.setDouble(4,30000.00);
			pst.setInt(5,33);
			pst.addBatch();//加入另一组参数,有错误违反主键约束
			pst.setString(1,UUID.randomUUID().toString().replace("-",""));
			pst.setInt(2,5);
			pst.setString(3,"佐老师");
			pst.setDouble(4,3005.00);
			pst.setInt(5,33);
			pst.addBatch();//加入另一组参数
			pst.executeBatch();//执行批操作
			con.commit();//手动提交
		}catch(Exception ex){
			ex.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}finally{
			DbUtil.closeConnection(con, pst);
		}
	}
	
	@Test
	public void TestMetaData(){
		String sql = "select * from amumu_emp";
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try{
			con = DbUtil.getConnection();
			//获取数据库的结构信息
			DatabaseMetaData dbMeta = con.getMetaData();
			System.out.println(dbMeta.getDatabaseProductName());
			System.out.println(dbMeta.getDatabaseProductVersion());
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			//获取结果集结构信息
			ResultSetMetaData meta = rs.getMetaData();
			int size = meta.getColumnCount();
			System.out.println("DEPT表一共有"+size+"列");
			for(int i=1;i<=size;i++){
				String columnName = meta.getColumnName(i);
				String type=meta.getColumnTypeName(i);
				System.out.println(columnName+type);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			DbUtil.closeConnection(con, pst, rs);
		}
	}
	
	/**
	 * @param fromId 转出人员
	 * @param to 转入人员
	 * @param money 金额
	 * @return
	 */
	public static boolean change(String from,String to,double money){
		Connection con = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		try{
			con = DbUtil.getConnection();
			con.setAutoCommit(false);
			String sq1 = "update amumu_emp " +
					"set sal=sal-? where ename=?";
			ps1 = con.prepareStatement(sq1);
			ps1.setDouble(1, money);
			ps1.setString(2, from);
			int size1 = ps1.executeUpdate();
			String sq2 = "update amumu_emp " +
			"set sal=sal+? where ename=?";
			ps2 = con.prepareStatement(sq2);
			ps2.setDouble(1, money);
			ps2.setString(2, to);
			int size2 = ps2.executeUpdate();
			if(size1==1 && size2==1){
				con.commit();
				return true;
			}else{
				con.rollback();
				return false;
			}
			
		}catch(Exception e1){
			e1.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			return false;
		}finally{
			DbUtil.closeConnection(con,ps1);
			DbUtil.closeConnection(con,ps2);
		}

	}
}
