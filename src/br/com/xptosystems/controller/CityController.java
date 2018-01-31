package br.com.xptosystems.controller;

import java.util.List;

import br.com.xptosystems.dao.CityDAO;
import br.com.xptosystems.model.City;
import br.com.xptosystems.model.StateInfo;

public class CityController {

    public void insertCities(List<City> cities) throws Exception {
        new CityDAO().insertCities(cities);
    }

    public List<City> getCapitals() throws Exception {
        return new CityDAO().getCapitals();
    }

    public StateInfo getStateWithMoreCities() {
        return new CityDAO().getStateWithMoreCities();
    }

	public StateInfo getStateWithLessCities() {
		return new CityDAO().getStateWithLessCities();
	}
	
	public StateInfo getStateInfo(String uf) {
		return new CityDAO().getStateInfo(uf);
	}
}
