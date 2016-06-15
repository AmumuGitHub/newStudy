package se02;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.junit.Test;

/**
 * @author amumu
 * @date 2016上午01:12:05
 */
public class Day02 {
	// @Test
	public void testRandomAccessFile() {
		/**
		 * 向当前项目根目录下的demo.dat文件中写入数据
		 * 
		 * RandomAccessFile(File f,String m) 参数1:用于读写的文件 参数2:操作模式 "rw" 读写操作 "r"
		 * 只读操作
		 */
		File file = new File("demo.txt");
		RandomAccessFile raf = null;
		try {
			// FileNotFoundException
			raf = new RandomAccessFile(file, "rw");
			/**
			 * long getFilePointer() RandomAccessFile是基于游标进行读写操作的 该方法用于获取游标当前位置
			 */
			System.out.print("raf.getFilePointer()" + raf.getFilePointer()
					+ "--");// 0

			/**
			 * void write(int d) 写一个字节,写int的低八位 vvvvvvvv 00000000 00000000
			 * 00000000 00000000
			 */
			raf.write('A');
			System.out.print("raf.getFilePointer()" + raf.getFilePointer()
					+ "--");// 0
			raf.write('B');

			/**
			 * 向文件中写入一个int的最大值 max 01111111 11111111 11111111 11111111
			 * 
			 * write vvvvvvvv 01111111 11111111 11111111 11111111
			 */
			int max = Integer.MAX_VALUE;

			raf.write(max >>> 24);
			raf.write(max >>> 16);
			raf.write(max >>> 8);
			raf.write(max);
			System.out.println("raf.getFilePointer()" + raf.getFilePointer()
					+ "--");// 0
			/**
			 * 连续写4个字节,将一个int值写出 等同上面4句
			 */
			raf.writeInt(Integer.MAX_VALUE);
			System.out.println("Integer.MAX_VALUE=="
					+ Integer.toBinaryString(Integer.MAX_VALUE));
			System.out.print("raf.getFilePointer()" + raf.getFilePointer()
					+ "--");// 0
			// 连续写8个字节,将一个long值写出
			raf.writeLong(1234l);

			/**
			 * 将给定字符串按照UTF-8编码转换为字节后写出
			 */
			String str = "你好我好大家好";
			raf.writeUTF(str);
			System.out.print("raf.getFilePointer()" + raf.getFilePointer()
					+ "--");// 0

			/**
			 * 
			 * 使用RandomAccessFile读取数据
			 * 
			 * int read() 读取一个字节,以int值形式返回.但只有低八位有内容 当返回值为-1时,说明文件读取到末尾了 EOF end
			 * of file
			 */
			System.out.println("开始读取数据了!");
			/**
			 * 现将游标移动会文件的开始 seek(long pos) 移动游标到指定的位置 0表示第一个字节,即:文件开头
			 */
			raf.seek(0);
			System.out.println(raf.getFilePointer());// 0
			char a = (char) raf.read();
			System.out.println(raf.getFilePointer());// 1
			char b = (char) raf.read();

			System.out.print("a==" + a + ",");
			System.out.print("b==" + b + ",");

			/**
			 * 读int值 01111111 11111111 11111111 11111111 data 00000000 00000000
			 * 00000000 01111111 data<<24 00000000 00000000 00000000 00000000
			 * num
			 * 
			 * 
			 * 01111111 11111111 11111111 11111111 data 00000000 00000000
			 * 01111111 11111111 data<<16 00000000 00000000 00000000 01111111
			 * num
			 * 
			 * 01111111 11111111 11111111 11111111 data 00000000 01111111
			 * 11111111 11111111 data<<8 00000000 00000000 01111111 11111111 num
			 */
			int num = 0;// 还原的数字
			// 读第一个字节(int的最高8位)
			int data = raf.read();
			num = num | data << 24;

			data = raf.read();
			num = num | data << 16;

			data = raf.read();
			num = num | data << 8;

			data = raf.read();
			num = num | data;

			System.out.println(num);

			// 连续读取4个字节并还原成int值,等同上面好几步
			int imax = raf.readInt();
			System.out.println(imax);

			long lMax = raf.readLong();
			System.out.println(lMax);

			String utf = raf.readUTF();
			System.out.println(utf);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (raf != null) {
				try {
					raf.close();
				} catch (IOException e) {
				}
			}
		}
	}

	// @Test
	public void testRandomAccessFile2() throws Exception {
		File file = new File("test.txt");
		RandomAccessFile raf = new RandomAccessFile(file, "rw");

		/**
		 * 以GBK编码写字符串
		 */
		String str = "大家好才是真的好!";
		byte[] data = str.getBytes("GBK");
		/**
		 * void write(byte[] arr) 一次性将字节数组中的所有字节都写出
		 */
		// 先写一个int值,将字符串所占用的字节量记录下来
		raf.writeInt(data.length);
		raf.write(data);

		str = "这是第二句话!!!!!!!!!!!!!!!!!";
		data = str.getBytes("GBK");
		raf.writeInt(data.length);
		raf.write(data);

		/**
		 * 读取
		 */
		raf.seek(0);

		// 读取第一个字符串
		// 先读取一个int值,以便得知该字符串长度
		int len = raf.readInt();
		/**
		 * int read(byte[] array) 一次性试图读取给定字节数组长度的字节量,并存入该数组 返回值为实际读取的字节量
		 */
		byte[] array = new byte[len];
		raf.read(array);

		String str1 = new String(array, "GBK");
		System.out.println(str1);

		len = raf.readInt();
		array = new byte[len];
		raf.read(array);
		String str2 = new String(array, "GBK");
		System.out.println(str2);
	}

	// 使用RandomAccessFile复制文件
	// @Test
	public void testRandomAccessFile3() {
		try {
			// 1 创建File对象描述待复制的文件
			File src = new File("demo.txt");

			// 2 创建File对象描述复制后的文件
			File des = new File("demo_copy1.txt");

			// 3 创建RandomAccessFile用来读取待复制的文件
			RandomAccessFile srcRaf = new RandomAccessFile(src, "r");

			// 4 创建RandomAccessFile用来向复制后的文件写数据
			RandomAccessFile desRaf = new RandomAccessFile(des, "rw");

			// 5 获取开始复制工作时的时间
			long start = System.currentTimeMillis();

			// 6 创建一个int值,用于保存每次读取的字节
			int d = -1;

			// 7 开始复制
			while ((d = srcRaf.read()) != -1) {
				desRaf.write(d);
			}

			// 8 获取复制完毕后的工作时间
			long end = System.currentTimeMillis();

			System.out.println("复制完毕!耗时:" + (end - start) + "毫秒");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 一次读写一组数据 用于减少读写次数,从而提高读写效率
	 */
	@Test
	public void testRandomAccessFile4() {
		try {
			RandomAccessFile srcRaf = new RandomAccessFile(
					new File("demo.txt"), "r");
			RandomAccessFile desRaf = new RandomAccessFile(new File(
					"demo_copy2.txt"), "rw");
			byte[] buf = new byte[1024 * 10];
			int len = -1;
			while ((len = srcRaf.read(buf)) > 0) {
				desRaf.write(buf, 0, len);
			}
			long end = System.currentTimeMillis();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 使用文件输出流向文件中写数据
	 */
	// @Test
	public void testFileOutputStream1() {
		try {
			FileOutputStream fos = new FileOutputStream(new File("fos.dat"));
			fos.write('A');
			fos.write('B');
			int max = Integer.MAX_VALUE;
			/**
			 * 将基本类型数据转换成对应的字节序列 称之为:基本类型序列化
			 * 
			 * 将数据写入硬盘做长久保存的过程 称之为:持久化
			 */
			fos.write(max >> 24);
			fos.write(max >> 16);
			fos.write(max >> 8);
			fos.write(max);
			String wStr = "随便来一句";
			byte[] data = wStr.getBytes("UTF-8");
			int wLen = data.length;
			fos.write(wLen >> 24);
			fos.write(wLen >> 16);
			fos.write(wLen >> 8);
			fos.write(wLen);
			fos.write(data);
			fos.close();

			// ----------------------------------------------------------------
			FileInputStream fis = new FileInputStream(new File("fos.dat"));
			char a = (char) fis.read();
			System.out.println(a);
			char b = (char) fis.read();
			System.out.println(b);
			int num = 0;
			int rData = fis.read();
			num = num | rData << 24;

			rData = fis.read();
			num = num | rData << 16;

			rData = fis.read();
			num = num | rData << 8;

			rData = fis.read();
			num = num | rData;
			System.out.println(num);

			int len = 0;
			len = len | fis.read() << 24;
			len = len | fis.read() << 16;
			len = len | fis.read() << 8;
			len = len | fis.read();
			System.out.println(len);
			byte[] array = new byte[len];

			fis.read(array);
			String str = new String(array, "UTF-8");
			System.out.println(str);

			fis.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * 使用文件输出流向文件中写数据
	 */
	@Test
	public void testFileOutputStream2() {
		try {
			System.out.println("---------------------------------");
			FileOutputStream fos = new FileOutputStream("append.txt", true);
			String str = "你";
			byte[] data = str.getBytes("UTF-8");
			fos.write(data);
			fos.close();
			FileInputStream fis1 = new FileInputStream("demo.txt");
			FileOutputStream fos1 = new FileOutputStream("demo3.txt");
			byte[] buf = new byte[1024 * 10];
			int len = -1;
			while ((len = fis1.read(buf)) != -1) {
				fos1.write(buf, 0, len);
			}
			fis1.close();
			fos1.close();

			FileOutputStream fos2 = new FileOutputStream("fos2.dat");
			BufferedOutputStream bos2 = new BufferedOutputStream(fos2);
			System.out.println(fos2);

			bos2.write("你好".getBytes());
			/**
			 * 具有缓冲功能的输出流, 若我们想写数据要求及时性时,我们应调用 flush(),该方法会强制做一次写操作,将当前缓冲区
			 * 中的数据写出.
			 */
			bos2.flush();
			/**
			 * close()方法,会在关闭流前自动调用一次flush()
			 */
			bos2.close();

			FileInputStream fis2 = new FileInputStream("fos2.dat");
			
			BufferedInputStream bis2= new BufferedInputStream(fis2);
			
			DataInputStream dis2= new DataInputStream(bis2);
			
			int i = dis2.readInt();
			//long l = dis2.readLong();
			//String str2 = dis2.readUTF();
			
			System.out.println(i);
			
			dis2.close();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

	}
}
