package com.dto;

public class Exoplanet {
	
	private Integer id;
	private String planet;
	private Double masse;
	private Double radius;
	private Double periode;
	private Double astroEinheit;
	private Double exzentrizitaet;
	private Double bahnneigung;
	private Double winkelabstand;
	private Integer entdeckung;
	private String aktualisierung;
	
	public Exoplanet(){}
	
	public Exoplanet(Integer id, String planet, Double masse, Double radius, Double periode,
			Double astroEinheit, Double exzentrizitaet, Double bahnneigung,
			Double winkelabstand, Integer entdeckung, String aktualisierung){
		this.id = id;
		this.planet = planet;
		this.masse = masse;
		this.radius = radius;
		this.periode = periode;
		this.astroEinheit = astroEinheit;
		this.exzentrizitaet = exzentrizitaet;
		this.bahnneigung = bahnneigung;
		this.winkelabstand = winkelabstand;
		this.entdeckung = entdeckung;
		this.aktualisierung = aktualisierung;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getMasse() {
		return masse;
	}
	public void setMasse(Double masse) {
		this.masse = masse;
	}
	public Double getRadius() {
		return radius;
	}
	public void setRadius(Double radius) {
		this.radius = radius;
	}
	public Double getPeriode() {
		return periode;
	}
	public void setPeriode(Double periode) {
		this.periode = periode;
	}
	public Double getAstroEinheit() {
		return astroEinheit;
	}
	public void setAstroEinheit(Double astroEinheit) {
		this.astroEinheit = astroEinheit;
	}
	public Double getExzentrizitaet() {
		return exzentrizitaet;
	}
	public void setExzentrizitaet(Double exzentrizitaet) {
		this.exzentrizitaet = exzentrizitaet;
	}
	public Double getBahnneigung() {
		return bahnneigung;
	}
	public void setBahnneigung(Double bahnneigung) {
		this.bahnneigung = bahnneigung;
	}
	public Double getWinkelabstand() {
		return winkelabstand;
	}
	public void setWinkelabstand(Double winkelabstand) {
		this.winkelabstand = winkelabstand;
	}
	public Integer getEntdeckung() {
		return entdeckung;
	}
	public void setEntdeckung(Integer entdeckung) {
		this.entdeckung = entdeckung;
	}
	public String getAktualisierung() {
		return aktualisierung;
	}
	public void setAktualisierung(String aktualisierung) {
		this.aktualisierung = aktualisierung;
	}
	public String getPlanet() {
		return planet;
	}
	public void setPlanet(String planet) {
		this.planet = planet;
	}

	@Override
	public String toString() {
		return "Exoplanet [id=" + id + ", planet=" + planet + ", masse=" + masse + ", radius=" + radius + ", periode="
				+ periode + ", astroEinheit=" + astroEinheit + ", exzentrizitaet=" + exzentrizitaet + ", bahnneigung="
				+ bahnneigung + ", winkelabstand=" + winkelabstand + ", entdeckung=" + entdeckung + ", aktualisierung="
				+ aktualisierung + "]";
	}
}
