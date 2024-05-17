package br.com.alura.DaysOfCode;

import br.com.alura.DaysOfCode.Movie.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {
	@LocalServerPort
	private int port;
	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void shouldReturnTopRatedFilms() {
		ResponseEntity<String> response = testRestTemplate.getForEntity("http://localhost:" + port + "/getTopRated", String.class);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertNotNull(response.getBody());
	}
	@Test
	public void shouldReturnAFilteredList(){
		ResponseEntity<List<Movie>> response = testRestTemplate.exchange("http://localhost:" + port + "/getTopRated&title=father", HttpMethod.GET,null, new ParameterizedTypeReference<List<Movie>>(){});

		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertTrue(response.getBody() instanceof List);
		Assertions.assertFalse(response.getBody().isEmpty());
	}
}
