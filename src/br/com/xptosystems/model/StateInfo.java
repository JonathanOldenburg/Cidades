package br.com.xptosystems.model;

public class StateInfo {
    private String uf;
    private int cityCount;

    public StateInfo(String uf, int cityCount) {
        this.uf = uf;
        this.cityCount = cityCount;
    }

    public String getUf() {
        return uf;

    }

    public void setUf(String uf) {
        this.uf = uf;
    }
    
    public int getCityCount() {
    	return cityCount;
    }
    
    public void setCityCount(int cityCount) {
    	this.cityCount = cityCount;
    }
}
