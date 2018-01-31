package br.com.xptosystems.model;

public class City {

	private int ibgeId;
	private String uf;
	private String name;
	private String nameWithoutAccents;
	private String alternativeNames;
	private boolean capital;
	private double lon;
	private double lat;
	private String microRegion;
	private String mesoRegion;

	public City(int ibgeId, String uf, String name, String nameWithoutAccents, String alternativeNames, boolean capital,
            double lon, double lat, String microRegion, String mesoRegion) {
        super();
        this.ibgeId = ibgeId;
        this.uf = uf;
        this.name = name;
        this.nameWithoutAccents = nameWithoutAccents;
        this.alternativeNames = alternativeNames;
        this.capital = capital;
        this.lon = lon;
        this.lat = lat;
        this.microRegion = microRegion;
        this.mesoRegion = mesoRegion;
    }
	
	public City() {
    }
	
    public int getIbgeId() {
		return ibgeId;
	}
	public void setIbgeId(int ibgeId) {
		this.ibgeId = ibgeId;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNameWithoutAccents() {
		return nameWithoutAccents;
	}
	public void setNameWithoutAccents(String nameWithoutAccents) {
		this.nameWithoutAccents = nameWithoutAccents;
	}
	public String getAlternativeNames() {
		return alternativeNames;
	}
	public void setAlternativeNames(String alternativeNames) {
		this.alternativeNames = alternativeNames;
	}
	public boolean isCapital() {
		return capital;
	}
	public void setCapital(boolean capital) {
		this.capital = capital;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public String getMicroRegion() {
		return microRegion;
	}
	public void setMicroRegion(String microRegion) {
		this.microRegion = microRegion;
	}
	public String getMesoRegion() {
		return mesoRegion;
	}
	public void setMesoRegion(String mesoRegion) {
		this.mesoRegion = mesoRegion;
	}
}
