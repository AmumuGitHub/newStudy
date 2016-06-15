package web.day02.jdbc.util;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DbUtil {
	private static String url;
	private static String user;
	private static String password;
	
	static{//下面逻辑只执行一次就可以
		try{
			//读取config包下的db.properties文件
			Properties props = new Properties();
			InputStream inStream = DbUtil.class.getClassLoader().getResourceAsStream("web/day02/jdbc/config/db.properties");
			props.load(inStream);
			url = props.getProperty("url");
			user = props.getProperty("user");
			password = props.getProperty("password");
			String driver = props.getProperty("driver");
			//注册驱动,获取connection对象
			Class.forName(driver);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	/**
	 * 返回一个connection连接对象
	 * @return
	 * @throws SQLException
	 */
  public static Connection getConnection()throws SQLException{
  	try{
			Connection con = DriverManager.getConnection(url,user,password);
			return con;
  	}catch(Exception ex){
  		throw new SQLException(ex);
  	}
  }
  
  /**
   * 关闭connection连接
   * @param con
   */
  public static void closeConnection(Connection con){
  	if(con != null){
  		try{
  			con.close();
  		}catch(Exception ex){}
  	}
  }
  
  public static void closeConnection(Connection con,Statement stat){
  	//释放stat
  	if(stat != null){
  		try{
  			stat.close();
  		}catch(Exception ex){}
  	}
  	//释放con
  	closeConnection(con);
  }
  
  public static void closeConnection(Connection con,Statement stat,ResultSet rs){
  	//释放rs
  	if(rs != null){
  		try{
  			rs.close();
  		}catch(Exception ex){}
  	}
  	//释放stat,con
  	closeConnection(con,stat);
  }
}
