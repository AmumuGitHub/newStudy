package se02.dms.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 工具类
 * @author Administrator
 *
 */
public class Utils {
	/**
	 * 将给定的int值写入给定的文件中
	 * @param file
	 * @param num
	 */
	public static void saveInt(File file,int num){
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(
									new FileOutputStream(file)
							 );
			writer.println(num);
			
		} catch (Exception e) {
				throw new RuntimeException(e);
		} finally{
			if(writer != null){
				writer.close();
			}
		}
	}
	
	/**
	 * 读取给定的文件,将第一行的字符串转换为数字返回 
	 * @param file
	 * @return
	 */
	public static int readInt(File file){
		BufferedReader reader = null;
		try {
			reader 
					= new BufferedReader(
							new InputStreamReader(
									new FileInputStream(file)
							)
					  );
			
			int num = Integer.parseInt(reader.readLine());
			return num;			
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally{
			if(reader!=null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 将给定数组从给定位置开始,连续读取给定字节,并转换为
	 * 相应的字符串
	 * 在unix中的日志文件里,字符串的字符集使用iso8859-1
	 * @param array
	 * @param offset
	 * @param length
	 * @return
	 */
	public static String toString(
									byte[] array,int offset,int length){
		try {
			return new String(array,offset,length,"iso8859-1").trim();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	/**
	 * 将给定数组中给定位置开始连续读取4个字节
	 * 并转换为int值返回
	 * @param array
	 * @param offset
	 * @return
	 */
	public static int toInt(byte[] array,int offset){
		/**
		 * ByteArrayInputStream
		 * 低级流,数据的来源明确,数据从给定的字节数组中读取		 * 
		 * byte b = array[0];		 * 
		 * byte b = bis.read();
		 */
		DataInputStream dis = null;
		try{
			ByteArrayInputStream bis
					= new ByteArrayInputStream(array);
			dis = new DataInputStream(bis);
			/**
			 * 从流的当前位置开始忽略给定字节
			 */
			dis.skipBytes(offset);
			/**
			 * 忽略后,当前流要读取的位置就是这个int值的位置了
			 */
			return dis.readInt();			
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			if(dis != null){
				try {
					dis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static short toShort(byte[] array,int offset){
		DataInputStream dis = null;
		try{
			ByteArrayInputStream bis
					= new ByteArrayInputStream(array);
			dis = new DataInputStream(bis);
			dis.skipBytes(offset);
			return dis.readShort();			
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			if(dis != null){
				try {
					dis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	/**
	 * 将给定集合中的元素遍历出来,并调用每个元素的toString
	 * 方法,将返回的字符串以行为单位写入到给定的文件中
	 * @param file
	 * @param list
	 */
	public static void saveList(File file,List list){
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(file);
			//不关心集合中的元素类型
			for(Object obj : list){
				//只要将每个元素的toString方法的返回值写入文件即可
				writer.println(obj);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally{
			if(writer != null){
				writer.close();
			}
		}
	}
	
	
}





