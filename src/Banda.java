
import java.sql.Time;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 14201805
 */
public class Banda {
    
    String nome;
    int cod_bandas;
    Time data_criacao;
    String lingua;

    public Banda() {
    }

    public Banda(String nome, int cod_bandas, Time data_criacao, String lingua) {
        this.nome = nome;
        this.cod_bandas = cod_bandas;
        this.data_criacao = data_criacao;
        this.lingua = lingua;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCod_bandas() {
        return cod_bandas;
    }

    

    public void setCod_bandas(int cod_bandas) {
        this.cod_bandas = cod_bandas;
    }

    public Time getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(Time data_criacao) {
        this.data_criacao = data_criacao;
    }

    public String getLingua() {
        return lingua;
    }

    public void setLingua(String lingua) {
        this.lingua = lingua;
    }
    
    @Override
    public String toString() {
        return "Banda{" + "nome=" + nome + ", cod_bandas=" + cod_bandas + ", data_criacao=" + data_criacao + ", lingua=" + lingua + '}';
    }
    
}
