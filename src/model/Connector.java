package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase Conector realiza y contiene la conexion con la BBDD
 * @author Enaut Agirre
 *
 */
public class Connector {
	
	protected Connection conn;

	public Connector() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				this.conn = DriverManager.getConnection("jdbc:mysql://" + Config.HOST + "/" + Config.BBDD + "?zeroDateTimeBehavior=convertToNull", Config.USERNAME, Config.PASSWORD );
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		
	}

}