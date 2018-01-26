package br.com.xptosystems.controller;

import java.util.List;

import br.com.xptosystems.dao.CityDAO;
import br.com.xptosystems.model.City;

public class CityController {

    public boolean insertCities(List<City> cities) throws Exception {
        return new CityDAO().insertCities(cities);
    }

}
