package br.com.ita.bdic3.util;

public class Haversine {

	private final static Integer R = 6371; // raio da terra em km
	
	public static Double distanciaEntreCoordenadasEmKm(
			Double lat1, Double long1, 
			Double lat2, Double long2) {
		

		Double latDistance = toRad(lat2 - lat1);
		Double lonDistance = toRad(long2 - long1);

		Double a = 
				Math.sin(latDistance / 2) * 
				Math.sin(latDistance / 2) + 
				Math.cos(toRad(lat1)) * 
				Math.cos(toRad(lat2)) * 
				Math.sin(lonDistance / 2) * 
				Math.sin(lonDistance / 2);

		Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return R * c;
	}

	private static Double toRad(Double value) {
		return value * Math.PI / 180;
	}

}