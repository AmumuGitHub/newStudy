package se02.dms.bo;
/**
 * LogRec的每一个实例用于保存一组配对的日志信息
 * @author Administrator
 *
 */
public class LogRec {
	//登录信息
	private LogData login;
	//登出信息
	private LogData logout;
	
	public LogRec(LogData login, LogData logout) {
		/**
		 * 既然LogRec是用来描述一组配对成功的日志信息
		 * 那么传入的登录登出信息就要做必要的验证工作
		 */
		if(login.getType() != LogData.USER_PROCESS){
			throw new RuntimeException("不是登录日志!");
		}
		if(logout.getType() != LogData.DEAD_PROCESS){
			throw new RuntimeException("不是登出日志!");
		}
		if(!login.getUser().equals(logout.getUser())){
			throw new RuntimeException("登录登出必须是同一个用户!");
		}
		if(login.getPid() != logout.getPid()){
			throw new RuntimeException("登录登出必须是同一个进程!");
		}
		if(!login.getHost().equals(logout.getHost())){
			throw new RuntimeException("登录登出必须是同一个主机!");
		}
		
		
		this.login = login;
		this.logout = logout;
	}
	
	/**
	 * 根据给定的字符串解析并创建一个LogRec实例
	 * 这个字符串要求必须与toString()方法的返回值格式相同
	 * @param logRec
	 */
	public LogRec(String logRec){
		String[] data = logRec.split("\\|");
		
		this.login = new LogData(data[0]);
		this.logout = new LogData(data[1]);
	}
	
	
	/**
	 * 用于将一组配对信息组成一个字符串
	 * 格式:
	 * login | logout登录登出中间用"|"分开,如下
	 * 
	 * user,pid,type,time,host|user,pid,type,time,host
	 */
	public String toString(){
		return login.toString() + "|" + logout.toString();
	}
	
}




