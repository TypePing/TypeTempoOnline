package typestore.typetempoonline.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StorageDataBase {

	
	
	private MySqlSqlLiteDataBase mysqlsqlitedatabase;

	public StorageDataBase(MySqlSqlLiteDataBase Mysqlsqlitedatabase) {
		mysqlsqlitedatabase = Mysqlsqlitedatabase;
		createTables();
	}

	private void createTables() {
		
			try (PreparedStatement ps = connection().prepareStatement(
					"CREATE TABLE IF NOT EXISTS `typetempoonline` (`player` TEXT, `coletadas` TEXT, `tempo` INTEGER, `tag` TEXT)")) {
				ps.executeUpdate();
				mysqlsqlitedatabase.disconnect();
			} catch (SQLException e) {
				e.printStackTrace();
				mysqlsqlitedatabase.disconnect();
			}

	}

	public void setPlayer(String player, String coletadas, int tempo, String tag) {
			try (PreparedStatement ps = connection()
					.prepareStatement("INSERT INTO `typetempoonline`(`player`, `coletadas`, `tempo`, `tag`) VALUES (?,?,?,?)")) {
				ps.setString(1, player);
				ps.setString(2, coletadas);
				ps.setInt(3, tempo);
				ps.setString(4, tag);
				ps.executeUpdate();
				mysqlsqlitedatabase.disconnect();
			} catch (SQLException e) {
				e.printStackTrace();
				mysqlsqlitedatabase.disconnect();
			}

	}
	public void settempo(String player, int tempo) {
	
			try (PreparedStatement ps = connection()
					.prepareStatement("UPDATE `typetempoonline` SET `tempo` = ? WHERE `player` = ?")) {
				ps.setInt(1, tempo);
				ps.setString(2, player);
				ps.executeUpdate();
				mysqlsqlitedatabase.disconnect();
			} catch (SQLException e) {
				e.printStackTrace();
				mysqlsqlitedatabase.disconnect();
			}

	}

	public int gettempo(String player) {
		try (PreparedStatement ps = connection().prepareStatement("SELECT * FROM `typetempoonline` WHERE `player` = ?")) {
			ps.setString(1, player);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int v = rs.getInt("tempo");
				mysqlsqlitedatabase.disconnect();
				return v;
			}
			mysqlsqlitedatabase.disconnect();
			return 0;
		} catch (SQLException e) {
			mysqlsqlitedatabase.disconnect();
			return 0;

		}

	}
	public void settag(String player, String tag) {
	
			try (PreparedStatement ps = connection()
					.prepareStatement("UPDATE `typetempoonline` SET `tag` = ? WHERE `player` = ?")) {
				ps.setString(1, tag);
				ps.setString(2, player);
				ps.executeUpdate();
				mysqlsqlitedatabase.disconnect();
			} catch (SQLException e) {
				e.printStackTrace();
				mysqlsqlitedatabase.disconnect();
			}

	}

	public String gettag(String player) {
		try (PreparedStatement ps = connection().prepareStatement("SELECT * FROM `typetempoonline` WHERE `player` = ?")) {
			ps.setString(1, player);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String v = rs.getString("tag");
				mysqlsqlitedatabase.disconnect();
				return v;
			}
			mysqlsqlitedatabase.disconnect();
			return "";
		} catch (SQLException e) {
			mysqlsqlitedatabase.disconnect();
			return "";

		}

	}

	public void setcoletadas(String player, String coletadas) {
			try (PreparedStatement ps = connection()
					.prepareStatement("UPDATE `typetempoonline` SET `coletadas` = ? WHERE `player` = ?")) {
				ps.setString(1, coletadas);
				ps.setString(2, player);
				ps.executeUpdate();
				mysqlsqlitedatabase.disconnect();
			} catch (SQLException e) {
				e.printStackTrace();
				mysqlsqlitedatabase.disconnect();
			}

	}

	public String getcoletadas(String player) {
		try (PreparedStatement ps = connection().prepareStatement("SELECT * FROM `typetempoonline` WHERE `player` = ?")) {
			ps.setString(1, player);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String v = rs.getString("coletadas");
				mysqlsqlitedatabase.disconnect();
				return v;
			}
			mysqlsqlitedatabase.disconnect();
			return "";
		} catch (SQLException e) {
			mysqlsqlitedatabase.disconnect();
			return "";

		}

	}

	public boolean verificarPlayer(String player) {

		try (PreparedStatement ps = connection().prepareStatement("SELECT * FROM `typetempoonline` WHERE `player` = ?")) {
			ps.setString(1, player);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				mysqlsqlitedatabase.disconnect();
				return true;
			}
			mysqlsqlitedatabase.disconnect();
			return false;
		} catch (SQLException e) {
			mysqlsqlitedatabase.disconnect();
			return false;
		}
	}

	public List<String> getTopTempo() {
		List<String> users = new ArrayList<>();
		try (PreparedStatement ps = connection().prepareStatement("SELECT * FROM `typetempoonline` ORDER BY `tempo` DESC")) {
			ResultSet rs = ps.executeQuery();
			while (rs.next())
				users.add(rs.getString("player"));
		} catch (SQLException e) {
			mysqlsqlitedatabase.disconnect();
			e.printStackTrace();
		}
		mysqlsqlitedatabase.disconnect();
		return users;
	}
	private Connection connection() {
		return mysqlsqlitedatabase.getConnection();
	}

}
