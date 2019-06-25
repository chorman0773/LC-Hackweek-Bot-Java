package github.lightnincreations.hackweekbot.properties;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import org.sqlite.JDBC;

import net.dv8tion.jda.api.entities.Guild;

public class ServerProperties  {
	private Guild guild;
	
	private static final Connection conn;
	
	static {
		try {
			conn = JDBC.createConnection("jdbc:sqlite:main.db", new Properties());
			DatabaseMetaData md = conn.getMetaData();
			ResultSet rs = md.getTables(null, null, "Properties", null);
			if(!rs.next()) {
				String CREATE_TABLE_STAT = "CREATE TABLE \"Properties\"(\"Server\" TEXT NOT NULL, \"Property\" TEXT NOT NULL, \"Value\" TEXT NOT NULL);";
				conn.createStatement().execute(CREATE_TABLE_STAT);
				conn.createStatement().execute("INSERT INTO Properties (Server,Property,Value) VALUES (\"default\",\"command.prefix\",\">\");");
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		
	}
	
	private static final String GET_PROPERTY_STAT = "SELECT Value FROM Properties WHERE Server=? AND Property=?";
	private static final String INSERT_PROPERTY_STAT = "INSERT INTO Properties (Server,Property,Value) VALUES (?,?,?)";
	private static final String UPDATE_PROPERTY_STAT = "UPDATE Properties SET Value=? WHERE Server=? AND Property=?";

	
	private Map<String,String> cached;
	
	private PreparedStatement get;
	private PreparedStatement insert;
	private PreparedStatement update;
	public ServerProperties(Guild guild) {
		this.guild = guild;
		try {
			get = conn.prepareStatement(GET_PROPERTY_STAT);
			insert = conn.prepareStatement(INSERT_PROPERTY_STAT);
			update = conn.prepareStatement(UPDATE_PROPERTY_STAT);
			cached = new TreeMap<>();
		}catch(SQLException s) {
			throw new RuntimeException(s);
		}
	}
	
	public void cleanCache() {
		cached.clear();
	}
	
	public String getProperty(String prop) {
		if(cached.containsKey(prop))
			return cached.get(prop);
		try {
			get.setString(1, guild.getId());
			get.setString(2, prop);
			ResultSet s = get.executeQuery();
			if(!s.next()) {
				get.setString(1, "default");
				s = get.executeQuery();
				if(s.next()) {
					String value = s.getString("Value");
					insert.setString(1, guild.getId());
					insert.setString(2, prop);
					insert.setString(3, value);
					insert.executeUpdate();
					cached.put(prop, value);
					return value;
				}
				else
					return null;
			}
			else {
				String value = s.getString("Value");
				cached.put(prop, value);
				return value;
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
	}
	
	public void setProperty(String prop,String value) {
		try {
			if(getProperty(prop)==null) {
				insert.setString(1, guild.getId());
				insert.setString(2, prop);
				insert.setString(3, value);
				insert.execute();
			}else {
				update.setString(1, value);
				update.setString(2, guild.getId());
				update.setString(3, prop);
				update.execute();
			}
			cached.put(prop, value);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
