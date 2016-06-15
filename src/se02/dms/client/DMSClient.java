package se02.dms.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import se02.dms.bo.LogData;
import se02.dms.bo.LogRec;
import se02.dms.utils.Utils;


/**
 * DMS客户端
 * @author Administrator
 *
 */
public class DMSClient {
	/**
	 * 单例模式
	 */
	private static DMSClient instance = new DMSClient();
	private DMSClient(){
		initConfig();//构造方法中做初始化操作.
	}
	public static DMSClient getInstance(){
		return instance;
	}
	
	/**
	 * 属性定义
	 */
	/**
	 * unix日志文件wtmpx
	 */
	private File logFile;
	
	/**
	 * 需要解析的临时文件.
	 * 保存从wtmpx文件中每次读取的10条数据
	 */
	private File tempLogFile;
	
	/**
	 * 每次读取wtmpx文件后的位置.
	 * 以便下次继续读取
	 */
	private File lastPositionFile;
	
	/**
	 * 解析为文本格式的日志文件
	 */
	private File textLogFile;
	
	/**
	 * 记录匹配成对的日志信息的文件
	 */
	private File logRecFile;
	
	/**
	 * 记录没有匹配成对的日志信息的文件
	 */
	private File loginLogFile;
	
	/**
	 * 每次从wtmpx文件中读取的记录数量
	 */
	private int batch;
	
	
	/**
	 * 方法定义
	 */
	/**
	 * 初始化方法
	 */
	public void initConfig(){
		try {
			//一次读取10条数据
			batch = 10;
			logFile = new File("wtmpx");
			tempLogFile = new File("temp.log");
			
			lastPositionFile = new File("last-position.txt");		
			
			textLogFile = new File("log.txt");
			
			logRecFile = new File("logrec.txt");
			
			loginLogFile = new File("login.txt");
			
		} catch (Exception e) {
				e.printStackTrace();
				//初始化失败就抛出异常
				throw new RuntimeException(e);
		}
	}
	
	/**
	 * 判断wtmpx文件中是否还有新的数据可读
	 * @return
	 */
	private boolean hasLogs(){
		/**
		 * 判断两点:
		 * 1:首先判断wtmpx文件是否存在,不存在抛出异常
		 * 2:读取last-position文件查看上次读取到的字节位置
		 *   并与wtmpx文件的总字节量比较,若总字节量大于上次
		 *   读取的,则说明还有数据可读
		 */
		if(!logFile.exists()){
			throw new RuntimeException("日志文件wtmpx不存在");
		}
		
		int lastposition = 0;
		if(lastPositionFile.exists()){
			//读取该文件,将上次读取的位置赋值给lastposition			
			lastposition = Utils.readInt(lastPositionFile);
		}
		
		return logFile.length()>lastposition;
		
	}
	
	/**
	 * 从wtmpx文件中读取10条数据到临时文件中
	 * @return true读取成功
	 */
	public boolean readNextLogs(){
		if(tempLogFile.exists()){
			/**
			 * 若临时文件已经存在,则说明已经从wtmpx文件中
			 * 读取了10条.不用再读了.应该去解析生成能看懂的
			 * 文件了.
			 */
			return true;
		}
		/**
		 * 查看是否还需要从wtmpx文件中读取数据
		 */
		if(!hasLogs()){
			//没有新数据可读了.
			return false;
		}
		RandomAccessFile in = null;
		FileOutputStream out = null;
		try {
			//默认从头开始读取
			long lastPosition = 0;
			
			/**
			 * 判断记录上次读取的位置的文件是否存在
			 * 若存在,就读取,并将上次读取的位置赋值
			 * 给lastPosition
			 */
			if(lastPositionFile.exists()){
				//读取
				lastPosition = Utils.readInt(lastPositionFile);
			}
			
			
			//读取wtmpx文件
			in = new RandomAccessFile(logFile,"r");
			
			//创建用于向临时文件中写数据的输出流
			out = new FileOutputStream(tempLogFile);
			
			//创建一个可以一次性保存一条数据的byte数组
			byte[] log = new byte[LogData.LOG_LENGTH];
			
			//记录读取的次数
			int num = 0;
			
			//滑动游标到上次读取的位置,继续读取10条
			in.seek(lastPosition);
			
			while( in.read(log)!= -1){
				out.write(log);
				num ++;
				if(num == batch){
					break;
				}
			}
			
			//将游标记录,以便下次读取
			//先获取当前游标位置
			lastPosition = in.getFilePointer();
			//将游标位置保存到文件中
			Utils.saveInt(lastPositionFile,(int)lastPosition);			
			
			System.out.println("复制了"+num+"条数据到临时文件");
			return true;
		} catch (Exception e) {
			
		} finally{
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		
		
		return false;
	}
	
	
	/**
	 * 从临时文件temp.log中解析每一条数据
	 * 并保存到文本文件中
	 * @return
	 */
	public boolean parseLogs(){
		/**
		 * 判断log.txt文件是否已经存在了
		 * 存在说明解析过了,不需要再次解析
		 */
		if(textLogFile.exists()){
			System.out.println("文件已存在:"+textLogFile);
			return true;
		}
		
		/**
		 * 判断temp.log是否不存在
		 * 若不存在,我们无法完成解析工作
		 */
		if(!tempLogFile.exists()){
			System.out.println("缺少临时文件:"+tempLogFile);
			return false;
		}
		try {
			InputStream in 
							= new FileInputStream(tempLogFile);
			PrintWriter out
							= new PrintWriter(textLogFile);
			/**
			 * 每次从临时文件中读取一条数据 372字节
			 */
			byte[] log = new byte[LogData.LOG_LENGTH];
			
			while(in.read(log) != -1){
				/**
				 * 读取372字节中的每个部分
				 * 解析出日志中的相关信息
				 * 并保存到一个LogData实例中
				 */
				LogData logData = parseLog(log);
				out.println(logData);
			}
			
			in.close();
			out.close();			
			/**
			 * 当将临时文件中的所有数据全部转换完毕后
			 * 将临时文件删除.
			 */
			tempLogFile.delete();
			return true;
		} catch (Exception e) {
			/**
			 * 若解析过程中出错,那么textLogFile文件就无效了
			 * 所以应当删除,以便下次重新解析
			 */
			if(textLogFile.exists()){
				textLogFile.delete();
			}
		}	
		return false;
	}
	
	/**
	 * 解析给定的字节数组,将其转换为一个LogData对象
	 * @param log
	 * @return
	 */
	public LogData parseLog(byte[] log){
		String user = Utils.toString(
											log, 
											LogData.USER_OFFSET,
											LogData.USER_LENGTH);
		
		int pid = Utils.toInt(
											log, 
											LogData.PID_OFFSET);
		
		short type = Utils.toShort(
											log, 
											LogData.TYPE_OFFSET);
		
		int time = Utils.toInt(
											log,
											LogData.TIME_OFFSET);
		
		String host = Utils.toString(
											log, 
											LogData.HOST_OFFSET,
											LogData.HOST_LENGTH);
		
		//将读取的数据存入一个LogData实例并返回
		return new LogData(user,pid,type,time,host);
	}
	
	
	/**
	 * 读取log.txt文件,并匹配一组登录登出数据
	 * 在匹配过程中,会遗留一些当次没有匹配上的数据
	 * 那么我们应当将这些数据保留,以便下次匹配
	 * @return
	 */
	public boolean matchLogs(){
		/**
		 * 如果配对文件已经存在说明已经匹配过了
		 * 无需再次匹配
		 */
		if(logRecFile.exists()){
			System.out.println("文件已经存在:"+logRecFile);
			return true;
		}
		/**
		 * 若保存解析后的日志文件不存在
		 * 无法配对
		 */
		if(!textLogFile.exists()){
			System.out.println("没有文件:"+textLogFile);
			return false;
		}
		
		/**
		 * 读取log.txt文件,将解析的数据读出,并存入集合,等待
		 * 配对
		 */
		List<LogData> list = loadLogData(textLogFile);
		
		/**
		 * 这里还要查看是否上次配对完毕后,有剩余没有配对
		 * 成功的日志,这些日志也要纳入这次配对中
		 */
		if(loginLogFile.exists()){
			list.addAll(loadLogData(loginLogFile));
		}
		
		/**
		 * 创建一个集合,用于保存所有配对成功的日志
		 */
		List<LogRec> matched = new ArrayList<LogRec>();
		/**
		 * 创建另一个集合,用于保存所有没有配对成功的日志
		 */
		List<LogData> loginList = new ArrayList<LogData>();
		
		/**
		 * 遍历所有的日志,开始配对
		 */
		for(LogData logData : list){
			//若是登入信息
			if(logData.getType() == LogData.USER_PROCESS){
				//匹配登出信息
				LogRec logRec = matchLogout(logData,list);
				/**
				 * 返回null意味着没有找到对应的登出信息
				 */
				if(logRec == null){
					/**
					 *  将当前日志加入未配对成功的集合中
					 */
					loginList.add(logData);
				
				}else{ 
					//若配对成功
					matched.add(logRec);
				}
			}
		}
		/**
		 * for循环结束后,所有配对工作完成
		 * 将配对成功的信息存入logrec.txt
		 * 将没有配对成功的信息存入login.txt
		 */
		try {
			/**
			 * 1:我们将matched集合中的元素写入logrec.txt
			 */
			Utils.saveList(logRecFile, matched);
			
			/**
			 * 2:我们将loginList集合中的元素写入login.txt
			 */
			Utils.saveList(loginLogFile, loginList);
			
			/**
			 * 将log.txt文件删除
			 */
			textLogFile.delete();
			//匹配完毕
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			/**
			 * 将匹配成队的文件删除,因为配对过程中出错
			 * 所以内容不完整
			 */
			if(logRecFile.exists()){
				logRecFile.delete();
			}
			return false;
		}
		
		
		
		
		
	}
	/**
	 * 根据给定的登入信息,从给定的集合中查找对应的登出信息
	 * 并将匹配成功的一对数据存入一个LogRec实例中返回
	 * 若根据给定的登入信息没有找到对应的登出信息,则返回
	 * null
	 * @param login
	 * @param list
	 * @return
	 */
	private LogRec matchLogout(
									LogData login,List<LogData> list){
		LogRec logRec = null;
		for(LogData log : list){
			logRec = match(login,log);
			//若返回值不是null,则说明匹配成功
			if(logRec != null){
				return logRec;
			}
		}
		/**
		 * for循环结束,说明没有找到匹配的登出信息
		 */
		return null;
	}
	/**
	 * 将一组登录登出信息进行配对,成功返回LogRec
	 * 不成功返回null
	 * @param login
	 * @param logout
	 * @return
	 */
	private LogRec match(LogData login,LogData logout){
//		try {
//			//不应当依赖异常做分支
//			LogRec logRec = new LogRec(login,logout);
//			return logRec;
//		} catch (Exception e) {
//			return null;
//		}
		
		if(login.getType() != LogData.USER_PROCESS){
			return null;
		}
		if(logout.getType() != LogData.DEAD_PROCESS){
			return null;
		}
		if(!login.getUser().equals(logout.getUser())){
			return null;
		}
		if(login.getPid() != logout.getPid()){
			return null;
		}
		if(!login.getHost().equals(logout.getHost())){
			return null;
		}
		//检查通过就创建配对信息并返回
		return new LogRec(login,logout);
	}
	
	
	/**
	 * 读取给定的文件,将每一条日志读取并转化为一个LogData
	 * 实例,最终将这些实例存入集合返回
	 * @param file
	 * @return
	 */
	private List<LogData> loadLogData(File file){
		BufferedReader in = null;
		try {
			ArrayList<LogData> list 
								= new ArrayList<LogData>();
			//创建用于读取文件的缓冲字符输入流
			in = new BufferedReader(
						 new InputStreamReader(
							  new FileInputStream(file)
						 )
					 );
			
			String log = null;
			while( (log = in.readLine()) != null ){
				/**
				 * 将每一行日志解析并存入集合
				 */
				list.add(new LogData(log));
			}
			
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally{
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 将配对的数据发送至服务端
	 * @return
	 */
	public boolean sendLogs(){
		if(!logRecFile.exists()){
			System.out.println("没有可发送的数据了");
			return true;
		}
		/**
		 * 将配对的数据读取出来并发送至服务端
		 */
		Socket socket = null;
		try {
			/**
			 * 先将配对数据读取出来,存入一个集合
			 */
			List<LogRec> data = loadLogRec(logRecFile);
			
			//尝试连接服务器
			socket = new Socket("localhost",8088);
			
			//创建用于向服务端发送数据的输出流
			PrintWriter writer 
						= new PrintWriter(
								socket.getOutputStream()
								,
								true
						  );
			
			//将每条配对的数据发送给服务端
			for(LogRec logRec : data){
				writer.println(logRec);
			}
			
			/**
			 * 在获取输入流,用于读取服务器发送回来的结果
			 * 从而得知是否发送成功了
			 */
			BufferedReader reader
					= new BufferedReader(
						   new InputStreamReader(
						  		socket.getInputStream()
						   )	
					  );
			//读取服务器发送回来的响应结果
			String response = reader.readLine();
			//若结果为OK,则说明服务器已经成功处理我们发送的数据
			if("OK".equals(response)){
				//将配对文件删除
				logRecFile.delete();
				return true;
			}else{
				return false;
			}	
		} catch (Exception e) {
			System.out.println("发送失败了!");
			return false;
		} finally{
			if(socket != null){
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	/**
	 * 从给定的文件中读取所有配对
	 * @param file
	 * @return
	 */
	private List<LogRec> loadLogRec(File file){		
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(
								 new FileReader(file)
			         );
		  ArrayList<LogRec> list = new ArrayList<LogRec>();
			String logRec = null;
			while((logRec = reader.readLine()) != null){
				list.add(new LogRec(logRec));
			}
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally{
			if(reader != null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	/**
	 * 开启客户端程序的方法
	 */
	public void process(){
		/**
		 * 数据采集工作的几个环节
		 */
		
		/**
		 * 1:从wtmpx文件中读取10条记录,并保存到临时文件中
		 *   每一条数据占372字节 
		 *   每次读取10条数据后,都应记录当前读取的字节位置
		 *   以便下次读取时知道从哪里开始进行.
		 */
		readNextLogs();
		
		
		/**
		 * 2:读取临时文件,每次读取372字节(一条2进制数据).并
		 *   解析为一条我们可以看懂的数据.最终存入到一个
		 *   文本文件中保存
		 */
		parseLogs();
		
		
		/**
		 * 3:读取解析出来的文本文件,将其中的数据进行匹配,按照
		 *   一组登录登出为一对的方式.并将每一组匹配成功的数据
		 *   存入一个文本文件中
		 *   将没有匹配成对的数据存入另一个文本文件中,等待下次
		 *   匹配
		 */
		matchLogs();
		
		
		/**
		 * 4:读取匹配成对的文本文件,将每一组成对数据发送给服务
		 *   器
		 */
		
		
	}
	
	public static void main(String[] args) {
		DMSClient dmsClient = DMSClient.getInstance();
		//启动客户端
		dmsClient.process();
	}
	
	
}





