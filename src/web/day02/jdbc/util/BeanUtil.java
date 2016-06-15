package web.day02.jdbc.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class BeanUtil {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(getTableName("web.day02.jdbc.entity.Emp"));
		System.out.println(getTypeAndPropertyList("web.day02.jdbc.entity.Emp"));
		System.out.println(getPropertyList("web.day02.jdbc.entity.Emp"));
		System.out.println(genCreateTableSql("web.day02.jdbc.entity.Emp"));
		System.out.println(genSelectAllSql("web.day02.jdbc.entity.Emp"));
		System.out.println(genInsertSql("web.day02.jdbc.entity.Emp"));
	}

	public static String getTableName(String bean) {
		try {
			String tableName = bean.substring(bean.lastIndexOf(".") + 1).toLowerCase();
			return tableName;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static List<String> getTypeAndPropertyList(String bean) {
		try {
			Class clz = Class.forName(bean);
			Field[] strs = clz.getDeclaredFields();
			List<String> propertyList = new ArrayList<String>();
			for (int i = 0; i < strs.length; i++) {
				String protype = strs[i].getType().toString();
				propertyList.add(protype
						.substring(protype.lastIndexOf(".") + 1)
						+ "`" + strs[i].getName());
			}
			return propertyList;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getPropertyList(String bean) {
		try {
			List<String> typeAndPropertyList = getTypeAndPropertyList(bean);
			StringBuffer sb = new StringBuffer();
			for (String str : typeAndPropertyList) {
				String property = str.split("`")[1];
				sb.append(property+", ");
			}
            sb.deleteCharAt(sb.toString().lastIndexOf(","));  
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 生成建表語句
	 * 
	 * @param bean
	 * @return
	 */
	public static String genCreateTableSql(String bean) {
		List<String> typeAndPropertyList = getTypeAndPropertyList(bean);
		StringBuffer sb = new StringBuffer("create table amumu_" + getTableName(bean)
				+ "(\n");
		for (String str : typeAndPropertyList) {
			String[] typeAndProperty = str.split("`");
			if (typeAndProperty[1].equals("id")) {
				sb.append("   id varchar2(32) primary key,\n");
			} else {
				if (typeAndProperty[0].equals("int")) {
					sb.append("   " + typeAndProperty[1]
							+ " number(10) default 0,\n");
				} else if (typeAndProperty[0].equals("String")) {
					sb.append("   " + typeAndProperty[1]
							+ " varchar2(200) default '' ,\n");
				} else if (typeAndProperty[0].equals("double")) {
					sb.append("   " + typeAndProperty[1]
							+ " number(10,2) default 0.0 ,\n");
				} else if (typeAndProperty[0].equals("Date")) {
					sb.append("   " + typeAndProperty[1] + " datetime ,\n");
				}
			}
		}
		sb.append(")");
		sb.deleteCharAt(sb.lastIndexOf(","));
		return sb.toString();
	}

	/**
	 * 生成查询语句
	 * 
	 * @param bean
	 * @return
	 */
	public static String genSelectAllSql(String bean) {
		String propertyList = getPropertyList(bean);
		return "select \n " + propertyList + " \n from \n amumu_"
				+ getTableName(bean) + "";
	}

	/**
	 * 生成插入语句
	 * 
	 * @param bean
	 * @return
	 */
	public static String genInsertSql(String bean) {
		String propertyList = getPropertyList(bean);
		int count=propertyList.split(",").length;
		String wenhao = "";
		for (int i = 0; i < count; i++) {
			if (i == count- 1) {
				wenhao = wenhao + "?";
			} else {
				wenhao = wenhao + "?,";
			}
		}
		return "insert into amumu_" + getTableName(bean) + "(" + propertyList
				+ ") values(" + wenhao + ")";
	}
}
