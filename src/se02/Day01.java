package se02;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

/**
 * @author amumu
 * @date 2016下午11:11:26
 */
public class Day01 {
	private int a; // 结果为0

	protected class Inner extends Day01 {
		public Inner() {
			a = 10;
		}
	}

	// @Test
	public void testInner() {
		System.out.print(((Day01) new Day01().new Inner()).a);
	}

	// @Test
	public void testFile1() {
		File file = new File("." + File.separator + "README1.md");
		/**
		 * 查看文件大小 long length()
		 */
		long length = file.length();
		System.out.println("文件大小:" + length + "字节");

		String name = file.getName();
		System.out.println("名字:" + name);

		// 最后修改时间
		// 2011年11月25日, 10:17:02
		long lastModified = file.lastModified();
		Date date = new Date(lastModified);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日, HH:mm:ss");
		System.out.println("最后修改时间:" + sdf.format(date));

		// 获取路径
		String path = file.getPath();
		System.out.println("路径:" + path);

		// 获取绝对路径
		String absPath = file.getAbsolutePath();
		System.out.println("absPath:==" + absPath);

		try {
			// 获取当前操作系统中标准的绝对路径,但要求捕获异常
			String cPath = file.getCanonicalPath();
			System.out.println("cPath:==" + cPath);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 查看当前file对象描述的文件是否真实存在
		System.out.println("文件是否存在:" + file.exists());

		// 查看当前File对象描述的是否为一个文件
		boolean isFile = file.isFile();
		System.out.println("是一个文件:" + isFile);

		// 查看当前File对象描述的是否为一个目录
		boolean isDir = file.isDirectory();
		System.out.println("是一个目录:" + isDir);

		// 查看文件是否可读
		boolean canRead = file.canRead();
		System.out.println("文件可读:" + canRead);

		// 查看文件是否可写
		boolean canWrite = file.canWrite();
		System.out.println("文件可写:" + canWrite);
	}

	// @Test
	public void testFile2() {
		File dir = new File("demo");
		if (!dir.exists()) {
			// 创建该目录
			dir.mkdir();
		}
		/**
		 * 在之前创建的demo目录下创建test.txt文件
		 * 
		 * File(File dir,String fileName)
		 */
		File file = new File(dir, "test.txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		/**
		 * 在之前创建的demo目录下创建demo目录
		 * 
		 * File(File dir,String fileName)
		 */
		File dirs = new File(dir, "demo");
		if (!dirs.exists()) {
			dirs.mkdir();
		}
	}

	// @Test
	public void testFile3() {
		/**
		 * 在当前目录下创建 a/b/c/d目录
		 */

		File dir = new File("a" + File.separator + "b" + File.separator + "c"
				+ File.separator + "d");
		if (!dir.exists()) {
			dir.mkdirs();
		}
		System.out.println("创建完毕");
	}

	// @Test
	public void testFile4() throws IOException {
		/**
		 * 删除当前目录中的文件demo.txt
		 */
		File file = new File("demo.txt");
		// 判断是否在硬盘中存在
		if (file.exists()) {
			// 将文件删除
			file.delete();
		}
		/**
		 * 删除当前目录中的目录demo
		 * 
		 * 删除目录: 要求:删除目录时,必须保证目录是空的
		 */
		File dir = new File("demo");
		System.out.println("dir.exists()" + dir.getCanonicalPath());
		if (dir.exists()) {
			dir.delete();
		}
	}

	//@Test
	public void testFile5() throws IOException {
		/**
		 * 查看,当前根目录下的所有子项
		 */
		File dir = new File("./demo");
		/**
		 * String[] list() 该方法用于获取当前File对象所描述的目录下的所有 子项的名字
		 */
		String[] subs = dir.list();
		System.out.println(subs.length);
		for (String sub : subs) {
			System.out.print(sub+"--");
		}
		System.out.println();
		/**
		 * File[] listFiles() 获取当前目录下的所有子项
		 */
		File[] subFiles = dir.listFiles();

		for (File sub : subFiles) {
			System.out.print(sub.isFile() ? "文件:" : "目录:");
			System.out.print(sub.getName() + " ");
			System.out.println(sub.length());
		}
		
		
	}

	//@Test
	public void testFile6() throws IOException {
		File dir = new File("a");
		deleteFile(dir);
	}
	
	@Test
	public void testFile7(){
		/**
		 * 获取当前目录下,所有名字以"."开头的子项
		 */
		File dir = new File(".");
		String[] files=dir.list();
		for (String file : files) {
			System.out.print(file+"--");
		}
		/**
		 * FileFilter接口
		 * 
		 */
		File[] subs = dir.listFiles(new FileFilter(){
			public boolean accept(File file) {
				System.out.println("检查:"+file.getName());
				String fileName = file.getName();
				return fileName.startsWith(".");
			}
		});
		
		for(File sub : subs){
			System.out.println("子项:"+sub.getName());
		}
	}
	
	/**
	 * 删除给定的File对象所描述的文件或目录
	 * @param file
	 */
	public static boolean deleteFile(File file){
		if(file.isDirectory()){
			//先删除该目录下的所有子项
			for(File sub : file.listFiles()){
				boolean success=deleteFile(sub);
				if(!success){
					return false;
				}
			}
		}
		return file.delete();
	}
}
