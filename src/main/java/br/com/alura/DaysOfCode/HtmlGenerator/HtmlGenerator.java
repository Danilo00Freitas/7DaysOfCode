package br.com.alura.DaysOfCode.HtmlGenerator;

import br.com.alura.DaysOfCode.Movie.Movie;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.List;

public class HtmlGenerator {
    private PrintWriter writer;
    public HtmlGenerator(PrintWriter receivedWriter){
        this.writer = receivedWriter;
    }

    public void generateHtml(List<Movie> movieList){
        writeHeader();
        writeBodyStart();
        writeMovieCard(movieList);
        writeBodyEnd();
    }

    private void writeHeader() {
        writer.println("""
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Filmes</title>
                    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
                </head>
                <body>
                """);
    }

    private void writeBodyStart() {
        writer.println("<div class=\"container-fluid\">");
        writer.println("<div class=\"row border-dark\">");
    }

    private void writeBodyEnd() {
        writer.println("</div>");
        writer.println("</div>");
    }

    private void writeMovieCard(List<Movie> movies){
        writer.println("<div class=\"col-md-6\">");
        for (Movie movie : movies){
            writer.println("""
                    <div class=\\"card text-white bg-dark mb-3\\" style=\\"max-width: 18rem;\\">
                                              	<h4 class=\\"card-header\\">%s</h4>
                                              	<div class=\\"card-body\\">
                                              		<img class=\\"card-img\\" src=\\"%s\\" alt=\\"%s\\">
                                              		<p class=\\"card-text mt-2\\">Nota: %s - Ano: %s</p>
                                              	</div>
                                              </div>
                    """.formatted(movie.getTitulo(),movie.getImgUrl(),movie.getTitulo(),movie.getNota(),movie.getAno()));
        }
        writer.println("</div>");
    }
}
