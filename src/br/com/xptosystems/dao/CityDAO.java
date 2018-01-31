package br.com.xptosystems.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.xptosystems.dao.db.ConnectionFactory;
import br.com.xptosystems.model.City;
import br.com.xptosystems.model.StateInfo;

public class CityDAO {

    private static final String TABLE = "cities";
    private static final String FIELDS = "ibge_id,uf,name,capital,lon,lat,no_accents,alternative_names,microregion,mesoregion";
    
    private static final String SQL_INSERT = "INSERT INTO "+TABLE+"("+FIELDS+") values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
    
    private static final String SELECT_CAPITALS = "SELECT * FROM "+TABLE+" WHERE capital = 1 ORDER BY name";
    
    public void insertCities(List<City> cities) throws Exception {
        PreparedStatement ps = null;
        int counter = 0;
        try (Connection conn = ConnectionFactory.getConnection()) {
            conn.setAutoCommit(false);
            for (City city : cities) {
                ps = conn.prepareStatement(SQL_INSERT);
                ps.setInt(1, city.getIbgeId());
                ps.setString(2, city.getUf());
                ps.setString(3, city.getName());
                ps.setInt(4, city.isCapital() ? 1 : 0);
                ps.setDouble(5, city.getLon());
                ps.setDouble(6, city.getLat());
                ps.setString(7, city.getNameWithoutAccents());
                ps.setString(8, city.getAlternativeNames());
                ps.setString(9, city.getMicroRegion());
                ps.setString(10, city.getMesoRegion());
                ps.execute();
                if (++counter == 1000) {
                    counter = 0;
                    conn.commit();
                }
            }
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println(new Date().toString() + "Sql Exception: "+SQL_INSERT);
            throw new Exception(e);
        }
    }

    public List<City> getCapitals() throws Exception {
        List<City> cities = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection()) {
          PreparedStatement ps = conn.prepareStatement(SELECT_CAPITALS);
          ResultSet resultSet = ps.executeQuery();
          City city;
          while (resultSet.next()) {
              city = new City(
                      resultSet.getInt("ibge_id"),
                      resultSet.getString("uf"),
                      resultSet.getString("name"),
                      resultSet.getString("no_accents"),
                      resultSet.getString("alternative_names"),
                      resultSet.getInt("capital") == 1,
                      resultSet.getDouble("lon"),
                      resultSet.getDouble("lat"),
                      resultSet.getString("microregion"),
                      resultSet.getString("mesoregion"));
              cities.add(city);
          }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    public StateInfo getStateWithMoreCities() {
        StateInfo stateInfo = null;
        try(Connection conn = ConnectionFactory.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "select uf, count(name) city_count from cities\n" +
                    "group by uf\n" +
                    "ORDER BY count(name) desc");
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.first()) {
                stateInfo = new StateInfo(
                        resultSet.getString("uf"),
                        resultSet.getInt("city_count")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stateInfo;
    }

	public StateInfo getStateWithLessCities() {
		StateInfo stateInfo = null;
		try(Connection conn = ConnectionFactory.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(
					"select uf, count(name) city_count from cities\n" +
							"group by uf\n" +
					"ORDER BY count(name)");
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.first()) {
				stateInfo = new StateInfo(
						resultSet.getString("uf"),
						resultSet.getInt("city_count")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stateInfo;
	}
	
	public StateInfo getStateInfo(String uf) {
		StateInfo stateInfo = null;
		try(Connection conn = ConnectionFactory.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(
					"select uf, count(name) city_count from cities\n"
					+ "where uf = ?");
			ps.setString(1, uf);
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.first()) {
				stateInfo = new StateInfo(
						resultSet.getString("uf"),
						resultSet.getInt("city_count"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stateInfo;
	}
}
