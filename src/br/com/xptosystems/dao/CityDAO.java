package br.com.xptosystems.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import br.com.xptosystems.dao.db.ConnectionFactory;
import br.com.xptosystems.model.City;

public class CityDAO {

    private static final String TABLE = "cities";
    private static final String FIELDS = "ibge_id,uf,name,capital,lon,lat,no_accents,alternative_names,microregion,mesoregion";
    
    private static final String SQL_INSERT = "INSERT INTO "+TABLE+"("+FIELDS+") values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
    
    public boolean insertCities(List<City> cities) throws Exception {
        PreparedStatement ps = null;
        boolean ret = true;
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
                ret = ps.execute();
                if (++counter == 1000) {
                    counter = 0;
                    conn.commit();
                }
            }
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception(SQL_INSERT, e);
        }
        return ret;
    }

}
