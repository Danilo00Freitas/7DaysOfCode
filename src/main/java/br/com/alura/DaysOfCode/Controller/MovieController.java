package br.com.alura.DaysOfCode.Controller;

/*import br.com.alura.DaysOfCode.ApplicationConfigs.RestTemplateConfig;*/
import br.com.alura.DaysOfCode.HtmlGenerator.HtmlGenerator;
import br.com.alura.DaysOfCode.Movie.Movie;
import br.com.alura.DaysOfCode.Movie.MovieDados;
import br.com.alura.DaysOfCode.TmdbApiClient.TmdbApiClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
public class MovieController {
    @Autowired
    private TmdbApiClient tmdbApiClient;
    @GetMapping
    @RequestMapping("/getTopRated")
    public List<Movie> topRated(@RequestParam(name = "title", required = false) String title){
        var response = tmdbApiClient.makeRequest(title);
        response.forEach(movie -> {
            System.out.println(movie.getTitulo());
        });

        try{
            PrintWriter printWriter = new PrintWriter("src/main/resources/content.html");
            new HtmlGenerator(printWriter).generateHtml(response);
            printWriter.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return response;
    }

    @GetMapping
    @RequestMapping("/getTopRated/html")
    public void html(){

    }



}
