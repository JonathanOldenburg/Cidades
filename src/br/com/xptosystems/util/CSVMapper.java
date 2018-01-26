package br.com.xptosystems.util;

import java.util.HashMap;
import java.util.Map;

public class CSVMapper {

    private static final String FIELD_SEPARATOR = ",";
    
    private Map<String, Integer> csvMap;
    
    private String[] data;
    
    public CSVMapper(String csvHeader) {
        csvMap = new HashMap<String, Integer>();
        String[] fields = csvHeader.split(FIELD_SEPARATOR);
        for (int i = 0; i < fields.length; i++) {
            csvMap.put(fields[i], i);
        }
    }
    
    public String getString(String field) {
        return data[csvMap.get(field)];
    }
    
    public int getInt(String field) {
        return Integer.valueOf(getString(field));//TODO: validar int
    }
    
    public double getDouble(String field) {
        return Double.valueOf(getString(field));//TODO: validar double
    }
    
    public boolean getBoolean(String field) {
        return Boolean.valueOf(getString(field));
    }

    public void setData(String data) {
        this.data = data.split(FIELD_SEPARATOR);
    }
}