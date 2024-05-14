package br.com.alura.DaysOfCode.Controller;

/*import br.com.alura.DaysOfCode.ApplicationConfigs.RestTemplateConfig;*/
import br.com.alura.DaysOfCode.Movie.Movie;
import br.com.alura.DaysOfCode.Movie.MovieDados;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
public class MovieController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    @GetMapping
    @RequestMapping("/getTopRated")
    public ResponseEntity<String> topRated(){
        String apiUrl = "https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=vote_average.desc&without_genres=99,10755&vote_count.gte=200";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3ODcyYzZlODYyMGVmOTMwZjZkOTE4Zjc3MWU5MWIwYyIsInN1YiI6IjY2M2M5MDk0OWQ3ZTBjZjhjNjBlNjE5MSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.MeIy8TOBsKLpgTrzuUEha6VZW89wbrVcpD8HeJs4O0s");
        httpHeaders.set("accept", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        var response = this.restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);
        String responseBody = response.getBody();

            Collection<Movie> movieList = new ArrayList<>();

        try {
            Map<String, Object> jsonMap = objectMapper.readValue(responseBody, Map.class);
            List<Map<String, Object>> results = (List<Map<String, Object>>) jsonMap.get("results");
            for (Map<String, Object> result : results) {
                String titulo = (String) result.get("title");
                String imgUrl = (String) result.get("poster_path");
                Double nota = (Double) result.get("vote_average");
                String ano = ((String) result.get("release_date")).substring(0, 4);

                MovieDados movieDados = new MovieDados(titulo, imgUrl, nota, Integer.parseInt(ano));
                movieList.add(new Movie(movieDados));
            }
            } catch (Exception e){
                System.out.println("Erro na criação da lista de DadosMovie: \n" + e.getMessage());
            }

        return response;
    }

}
