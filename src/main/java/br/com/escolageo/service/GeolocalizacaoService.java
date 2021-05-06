package br.com.escolageo.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.Geometry;
import com.google.maps.model.LatLng;

import br.com.escolageo.model.Contato;

@Service
public class GeolocalizacaoService {
	
	public List<Double> obterLatELongPor(Contato contato) throws Exception {
		GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyA03zKo_Ey2LXS6y9SS12t9Fq4ZIA29lOk");
		GeocodingApiRequest request = GeocodingApi.newRequest(context).address(contato.getEndereco());
		
		GeocodingResult[] results = request.await();
		GeocodingResult resultado = results[0];
		
		Geometry geometry = resultado.geometry;
		LatLng location = geometry.location;
		
		return Arrays.asList(location.lat, location.lng);
	}
}
