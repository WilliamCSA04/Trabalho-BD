public class Musica{
    private String titulo;
    private int cod_musica;
    private int cod_albuns;
    private String genero;

    public Musica(String titulo, int cod_musica, int cod_albuns, String genero) {
        
        
        this.titulo = titulo;
        this.cod_musica = cod_musica;
        this.cod_albuns = cod_albuns;
        this.genero = genero;
    }

    public Musica() {
        
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getCod_musica() {
        return cod_musica;
    }

    public void setCod_musica(int cod_musica) {
        this.cod_musica = cod_musica;
    }

    public int getCod_albuns() {
        return cod_albuns;
    }

    public void setCod_albuns(int cod_albuns) {
        this.cod_albuns = cod_albuns;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Titulo:" + titulo + " - Genero:" + genero;
    }
    
}

