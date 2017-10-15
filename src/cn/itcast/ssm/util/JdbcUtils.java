package cn.itcast.ssm.util;

/*
 * db.properties�ļ���src��
driver=com.mysql.jdbc.Driver
url=jdbc\:mysql\://localhost\:3306/page
username=root
password=mysqladmin
*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
public class JdbcUtils{
	private static Properties config = new Properties();	//����ģʽ
	static{	//��̬����飬��ȡ��Դ�ļ�����������
		try{
			config.load(JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties"));	//��̬����飬��ȡ�����ļ�
			
			Class.forName(config.getProperty("driver"));	//��������
			
		}catch(Exception e){
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static Connection getConnection() throws SQLException{	//��������
		return DriverManager.getConnection(config.getProperty("url"),config.getProperty("username"),config.getProperty("password"));
	}
	
	public static void release(Connection conn,Statement st,ResultSet rs){	//�ͷ���Դ
		if(rs != null){
			try{
				rs.close();
			}catch(Exception e){}
		}
		if(st != null){
			try{
				st.close();
			}catch(Exception e){}
		}
		if(conn != null){
			try{
				conn.close();
			}catch(Exception e){}
		}
	}
}
