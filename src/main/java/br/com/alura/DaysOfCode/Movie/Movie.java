package br.com.alura.DaysOfCode.Movie;

public class Movie {
    private String titulo;
    private String imgUrl;
    private double nota;
    private int ano;

    public Movie(MovieDados movieDados) {
        this.titulo = movieDados.titulo();
        this.imgUrl = movieDados.imgUrl();
        this.nota = movieDados.nota();
        this.ano = movieDados.ano();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public double getNota() {
        return nota;
    }

    public int getAno() {
        return ano;
    }
}
