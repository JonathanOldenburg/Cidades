package br.com.xptosystems.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import br.com.xptosystems.controller.CityController;
import br.com.xptosystems.model.City;
import br.com.xptosystems.util.CSVMapper;

public class CityService {

    private static final String IBGE_ID = "ibge_id";
    private static final String UF = "uf";
    private static final String NAME = "name";
    private static final String CAPITAL = "capital";
    private static final String LON = "lon";
    private static final String LAT = "lat";
    private static final String NO_ACCENTS = "no_accents";
    private static final String ALTERNATIVE_NAMES = "alternative_names";
    private static final String MICRO_REGION = "microregion";
    private static final String MESO_REGION = "mesoregion";
    
    public Response importCsvFile(InputStream file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file))) {
            CityController cityController = new CityController();
            List<City> cities = new ArrayList<>();
            String line = reader.readLine();
            CSVMapper csvMapper = new CSVMapper(line);
            
            City city;
            while ((line = reader.readLine()) != null) {
                city = new City();
                csvMapper.setData(line);
                city.setAlternativeNames(csvMapper.getString(ALTERNATIVE_NAMES));
                city.setCapital(csvMapper.getBoolean(CAPITAL));
                city.setIbgeId(csvMapper.getInt(IBGE_ID));
                city.setLat(csvMapper.getDouble(LAT));
                city.setLon(csvMapper.getDouble(LON));
                city.setMesoRegion(csvMapper.getString(MESO_REGION));
                city.setMicroRegion(csvMapper.getString(MICRO_REGION));
                city.setName(csvMapper.getString(NAME));
                city.setNameWithoutAccents(csvMapper.getString(NO_ACCENTS));
                city.setUf(csvMapper.getString(UF));
                cities.add(city);
            }
            cityController.insertCities(cities);
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }

        return Response.status(200).build();
    }
}
