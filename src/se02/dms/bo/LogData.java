package se02.dms.bo;
/**
 * LogData类用于描述WTMPX日志文件中的数据
 * LogData中声明的属性用于对应日志文件中每条数据的相关
 * 信息
 * 这样该类的每一个实例都可以保存日志文件中的一条数据了
 * @author Administrator
 *
 */
public class LogData {
	//一条日志的字节量
	public static final int LOG_LENGTH = 372;
	/**
	 * 属性定义
	 */
	//用户名
	private String user;
	
	/**
	 * 用户名在日志文件中的位置
	 */
	public static final int USER_OFFSET = 0;
	public static final int USER_LENGTH = 32;
	
	
	//用户的进程id
	private int pid;
	/**
	 * 进程ID在日志文件中的位置
	 */
	public static final int PID_OFFSET = 68;
	
	
	/**
	 * 日志的类型
	 * 用于查看是登录还是登出
	 */
	private short type;
	//日志类型在日志文件中的位置
	public static final int TYPE_OFFSET = 72;
	//日志类型的值  登录
	public static final short USER_PROCESS = 7;
	//日志类型的值  登出
	public static final short DEAD_PROCESS = 8;
	
	/**
	 * 登录时间
	 * 从1970年元旦起到当前登录这一刻的秒
	 * 以秒为单位,不是毫秒
	 */
	private int time;
	
	//登录时间在日志文件中的位置
	public static final int TIME_OFFSET = 80;
	
	
	/**
	 * 登录用户的主机名,通常是IP
	 */
	private String host;
	
	public static final int HOST_OFFSET = 114;
	public static final int HOST_LENGTH = 257;
	
	public LogData(String user, int pid, short type, int time, String host) {
		super();
		this.user = user;
		this.pid = pid;
		this.type = type;
		this.time = time;
		this.host = host;
	}
	
	/**
	 * 传入一个字符串,字符串格式应该与当前类的toString()
	 * 方法的返回值格式相同
	 * @param log
	 */
	public LogData(String log){
		//按照","拆分字符串
		String[] data = log.split(",");
		this.user = data[0];
		this.pid  = Integer.parseInt(data[1]);
		this.type = Short.parseShort(data[2]);
		this.time = Integer.parseInt(data[3]);
		this.host = data[4];
	}
	
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
	
	/**
	 * 字符串格式
	 * user,pid,type,time,host
	 */
	public String toString(){
		String str = user + "," +
								 pid  + "," +
								 type + "," +
								 time + "," +
								 host;
		return str;
	}
	
	
	
}











