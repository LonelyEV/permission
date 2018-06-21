package ones.quzhigang.permission;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import ones.quzhigang.permission.common.Fild;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;



public class TestVelocity {

	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/permission?useUnicode=true&characterEncoding=utf-8";
	private static final String user = "root";
	private static final String password = "root";

	private static String talbeName="tbl_sys_user";


	
	private static String  classNameLowCase="" ;
	
	private static String classNameUpCase="";
	
	private static String  rootPath = "F:\\study_dir\\study_git\\permission\\"; // 物理目录
	
	private static 	List<Fild> filds ;
	
	private static VelocityEngine ve;

	private  TestVelocity(){

	}
	

	private static void init() throws Exception{
		initVelocity();
		initTable();
	};
	
	/**
	 * 初始化velocity相关配置
	 * @throws Exception 
	 */
	private static void   initVelocity() throws Exception{
		ve = new VelocityEngine();
		ve.setProperty("input.encoding", "UTF-8");
		ve.setProperty("output.encoding", "UTF-8");
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class",
		ClasspathResourceLoader.class.getName());
		ve.init();
		
		
		
	}

	private static Connection getConection() throws ClassNotFoundException,
			SQLException {

		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, user, password);
		if (!conn.isClosed()){
			System.out.println("Succeeded connecting to the Database!");
		}
		else {
			System.err.println("connect filed");
			return null;
		}
		return conn;
	}
	
	private static void initTable() throws ClassNotFoundException, SQLException{
		String[] temp = talbeName.substring(4).toLowerCase().split("_");
		for (String str : temp) {
			if (str != null && str.trim().length() > 0) {
				// 首字母大写
				classNameUpCase += str.replaceFirst(str.charAt(0)+ "",
						(char) ((str.charAt(0) - 32))+"");
			}
		}
		// userAccount UserAccount
		classNameLowCase = classNameUpCase.replaceFirst(classNameUpCase.charAt(0)+"",
				(char) ((classNameUpCase.charAt(0) + 32))+"");
		
		filds=getFileds(talbeName);
	}

	public static void main(String[] args) throws ResourceNotFoundException,
			ParseErrorException, Exception {
		init();
		// 生成model
		createModel();
		// 生成mapper
		createMapper();
		// 生成provider
		createProvider();
		// 生成query
		createQuery();
		// 生成server
		 createService();
		// 生成serverImpl
		 createServiceImpl();
		// 生成mybatis_xxxxx.xml
		 createMybatisXml();

	}

	private static void merge(Template template, VelocityContext ctx,
			String path) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(path);
			template.setEncoding("UTF-8");
			template.merge(ctx, writer);
			writer.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		} catch (ParseErrorException e) {
			e.printStackTrace();
		} catch (MethodInvocationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			writer.close();
		}
	}

	/**
	 * 获取所有字段
	 * 
	 * @param tableName
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private static List<Fild> getFileds(String tableName)
			throws ClassNotFoundException, SQLException {
		List<Fild> list = new ArrayList<Fild>();
		Connection conn = getConection();

		Statement statement = conn.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from "
				+ tableName);
		// 获取列名
		ResultSetMetaData metaData = resultSet.getMetaData();
		for (int i = 0; i < metaData.getColumnCount(); i++) {
			// resultSet数据下标从1开始
			String columnName = metaData.getColumnName(i + 1);
			int type = metaData.getColumnType(i + 1);
			String[] temp = columnName.toLowerCase().split("_");
			String fild = "";
			String property = "";
			for (String str : temp) {
				if (str != null && str.trim().length() > 0) {
					// 首字母大写
					property += str.replaceFirst(str.charAt(0) + "",
							(char) ((str.charAt(0) - 32)) + "");
				}

			}
			//首字母大写
		//	property=columnName.replaceFirst(columnName.charAt(0)+"",(char) ((columnName.charAt(0) - 32)) + "");
			// 首字母小写
			
			fild = property.replace(property.charAt(0),
					(char) ((property.charAt(0) + 32)));
			switch (type) {
			case Types.BIT:
			case Types.BIGINT:
			case Types.INTEGER:
				
				if(i == 0){
					list.add(new Fild("Long", fild, property, columnName));
					break;
				}
				list.add(new Fild("Integer", fild, property, columnName));
				
				break;
			case Types.FLOAT:
				list.add(new Fild("Float", fild, property, columnName));
				break;
			case Types.DOUBLE:
				list.add(new Fild("Double", fild, property, columnName));
				break;
			case -1:
			case Types.CHAR:
			case Types.VARCHAR:
				list.add(new Fild("String", fild, property, columnName));
				break;
			case Types.DATE:
			case Types.TIMESTAMP:
			case Types.TIME:
				list.add(new Fild("String", fild, property, columnName));
				break;
			default:
				break;
			}

			System.out.print(columnName + "\t");
		}
		System.out.println();
		statement.close();
		conn.close();
		return list;
	}

	/**
	 * 获取表所有的列名
	 * 
	 * @param table
	 * @return ID,USER_NAME,CONTACT_USER_NAME
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String getColumns(String table)
			throws ClassNotFoundException, SQLException {
		StringBuffer str = new StringBuffer();
		Connection conn = getConection();
		// 获取所有表名
		Statement statement = conn.createStatement();

		ResultSet resultSet = statement.executeQuery("select * from " + table);
		// 获取列名
		ResultSetMetaData metaData = resultSet.getMetaData();
		for (int i = 0; i < metaData.getColumnCount(); i++) {
			// resultSet数据下标从1开始
			String columnName = metaData.getColumnName(i + 1);
			str.append(columnName + ",");
		}
		String strTem = str.substring(0, str.length() - 1).toString();
		statement.close();
		conn.close();
		return strTem;
	}

	/**
	 * 获取表所有的列名 ${userName}
	 * 
	 * @param list
	 * @return ${userName}
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String getProperty(List<Fild> list) {
		StringBuffer str = new StringBuffer();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				str.append("#{" + list.get(i).getFild() + "},");
			}
		}

		String strTem = str.substring(0, str.length() - 1);
		
		return strTem;
	}

	/**
	 * 获取表所有的列名 ${userName}
	 * 
	 * @param columns , filds
	 * @return ${userName}
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String getUpdate(String columns, List<Fild> filds) {
		StringBuffer str = new StringBuffer();
		if (!StringUtils.isBlank(columns) && !CollectionUtils.isEmpty(filds)) {
			String[] columnArray = columns.split(",");

			//去掉 id  和 create_time  update_time
			for (int i = 1; i < columnArray.length; i++) {
						str.append(columnArray[i] + "=#{"
								+ filds.get(i).getFild() + "},");
			}

		}

		String strTem = str.substring(0, str.length() - 1);
		strTem = strTem.replace("create_time=#{createTime},", "");

		return strTem;
	}

	public static String convertDatabaseCharsetType(String in, String type) {
		String dbUser;
		if (in != null) {
			if (type.equals("oracle")) {
				dbUser = in.toUpperCase();
			} else if (type.equals("postgresql")) {
				dbUser = "public";
			} else if (type.equals("mysql")) {
				dbUser = null;
			} else if (type.equals("mssqlserver")) {
				dbUser = null;
			} else if (type.equals("db2")) {
				dbUser = in.toUpperCase();
			} else {
				dbUser = in;
			}
		} else {
			dbUser = "public";
		}
		return dbUser;
	}

	private static void getTables(Connection conn) throws SQLException {
		DatabaseMetaData dbMetData = conn.getMetaData();
		// mysql convertDatabaseCharsetType null
		ResultSet rs = dbMetData.getTables(null,
				convertDatabaseCharsetType("root", "mysql"), null,
				new String[] { "TABLE", "VIEW" });

		while (rs.next()) {
			if (rs.getString(4) != null
					&& (rs.getString(4).equalsIgnoreCase("TABLE") || rs
							.getString(4).equalsIgnoreCase("VIEW"))) {
				String tableName = rs.getString(3).toLowerCase();
				System.out.print(tableName + "\t");
				// 根据表名提前表里面信息：
				ResultSet colRet = dbMetData.getColumns(null, "%", tableName,
						"%");
				while (colRet.next()) {
					String columnName = colRet.getString("COLUMN_NAME");
					String columnType = colRet.getString("TYPE_NAME");
					int datasize = colRet.getInt("COLUMN_SIZE");
					int digits = colRet.getInt("DECIMAL_DIGITS");
					int nullable = colRet.getInt("NULLABLE");
					// System.out.println(columnName + " " + columnType + " "+
					// datasize + " " + digits + " " + nullable);
				}

			}
		}
		System.out.println();

		// resultSet数据下标从1开始 ResultSet tableRet =
		// conn.getMetaData().getTables(null, null, "%", new String[] { "TABLE"
		// });
		// while (tableRet.next()) {
		// System.out.print(tableRet.getString(3) + "\t");
		// }
		// System.out.println();

	}
	
	private static void createModel() throws ResourceNotFoundException, ParseErrorException, Exception{

		Template actionTpt = ve.getTemplate("template/model.vm");
		VelocityContext ctx = new VelocityContext();
		ctx.put("classNameLowCase", classNameLowCase);
		ctx.put("classNameUpCase", classNameUpCase);
		ctx.put("filds", filds);

		// 生成文件 模板 内容容器 新文件位置
		merge(actionTpt, ctx, rootPath + "src/main/java/ones/quzhigang/permission/model/"
				+ classNameUpCase + "Model.java");
		System.out.println("success...model");
	};
	
	private static void createMapper() throws ResourceNotFoundException, ParseErrorException, Exception{

		
		Template mapperTmp = ve.getTemplate("template/mapper.vm");
		VelocityContext mapperCtx = new VelocityContext();
		String columns = getColumns(talbeName);
		String property = getProperty(filds);
		String update = getUpdate(columns, filds);
		mapperCtx.put("classNameUpCase", classNameUpCase);
		mapperCtx.put("classNameLowCase", classNameLowCase);
		mapperCtx.put("talbeName", talbeName);
		mapperCtx.put("columns", columns);
		
		
		
		String insertStr = columns.replace(",update_time", "");
		if("id,".equals(columns.substring(0, 3))){
		    insertStr = columns.substring(3).replace(",update_time", "");
		}
		
		mapperCtx.put("insert", insertStr); // 去掉ID,
		
		String insertProStr = property.replace(",#{updateTime}", "");
		
		if("#{id},".equals(property.substring(0,6))){
			insertProStr = property.substring(6).replace(",#{updateTime}", "");
		}
		
		mapperCtx.put("insertProperty", insertProStr); // 去掉#{id},

		mapperCtx.put("property", property);
		mapperCtx.put("update", update);
		merge(mapperTmp, mapperCtx, rootPath
				+ "src/main/java/ones/quzhigang/permission/mapper/" + classNameUpCase
				+ "Mapper.java");

		System.out.println("success...mapper");
	}
	
	private static void createProvider() throws ResourceNotFoundException, ParseErrorException, Exception{

		
		Template providerTmp = ve.getTemplate("template/provider.vm");
		VelocityContext providerCtx = new VelocityContext();
		String columns = getColumns(talbeName);
		providerCtx.put("classNameUpCase", classNameUpCase);
		providerCtx.put("classNameLowCase", classNameLowCase);
		providerCtx.put("talbeName", talbeName);
		providerCtx.put("filds", filds);
		providerCtx.put("columns", columns);
		merge(providerTmp, providerCtx, rootPath
				+ "src/main/java/ones/quzhigang/permission/provider/" + classNameUpCase
				+ "Provider.java");

		System.out.println("success...provider");
	}
	
	private static void createQuery() throws ResourceNotFoundException, ParseErrorException, Exception{

		Template queryTmp = ve.getTemplate("template/query.vm");
		VelocityContext queryCtx = new VelocityContext();
		queryCtx.put("classNameUpCase", classNameUpCase);
		queryCtx.put("classNameLowCase", classNameLowCase);
		queryCtx.put("filds", filds);
		merge(queryTmp, queryCtx, rootPath
				+ "src/main/java/ones/quzhigang/permission/query/" + classNameUpCase
				+ "Query.java");

		System.out.println("success...query");
	}
	
	private static void createService() throws ResourceNotFoundException, ParseErrorException, Exception{
		
		
		Template serviceTmp = ve.getTemplate("template/service.vm");
		VelocityContext serviceCtx = new VelocityContext();
		serviceCtx.put("classNameUpCase", classNameUpCase);
		serviceCtx.put("classNameLowCase", classNameLowCase);

		merge(serviceTmp, serviceCtx, rootPath
				+ "src/main/java/ones/quzhigang/permission/service/" + classNameUpCase
				+ "Service.java");

		System.out.println("success...server");
	}
	
	private static void createServiceImpl() throws ResourceNotFoundException, ParseErrorException, Exception{

		Template serviceImplTmp = ve.getTemplate("template/serviceImpl.vm");
		VelocityContext serviceImplCtx = new VelocityContext();
		serviceImplCtx.put("classNameUpCase", classNameUpCase);
		serviceImplCtx.put("classNameLowCase", classNameLowCase);
		merge(serviceImplTmp, serviceImplCtx, rootPath
				+ "src/main/java/ones/quzhigang/permission/service/impl/" + classNameUpCase
				+ "ServiceImpl.java");

		System.out.println("success...serviceImpl");
	};
	
	private static void createMybatisXml() throws ResourceNotFoundException, ParseErrorException, Exception{
		
		Template mybatis_xxxxxTmp = ve.getTemplate("template/mybatis_xml.vm");
		VelocityContext mybatis_xxxxxCtx = new VelocityContext();
		mybatis_xxxxxCtx.put("classNameUpCase", classNameUpCase);
		mybatis_xxxxxCtx.put("classNameLowCase", classNameLowCase);
		mybatis_xxxxxCtx.put("filds", filds);
		merge(mybatis_xxxxxTmp, mybatis_xxxxxCtx, rootPath
				+ "src/main/resources/mapper/" + "MYBATIS_"
				+ classNameUpCase + "XmlMapper.xml");

		System.out.println("success...mybatis_xxxxx.xml");
	}

}
