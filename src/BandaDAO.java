
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 14201805
 */
public class BandaDAO {
    
    private Connection connection;
    private LinkedList<Integer> codigosBanda = new LinkedList<>();
    private LinkedList<String> nomeBanda = new LinkedList<>();
    public BandaDAO() {
        this.connection = new ConnectionFactory().getConnection();
        try {
            String sql = "select Nome, cod_bandas from bandas";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                codigosBanda.add(rs.getInt("cod_bandas"));
                nomeBanda.add(rs.getString("Nome"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MusicaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public LinkedList<Integer> getCodigosBanda() {
        Collections.sort(codigosBanda);
        return codigosBanda;
    }
    
    
    
    public LinkedList<String> getNomeBanda() {
        return nomeBanda;
    }

    public String getNomeBandas(){
        String s="";
        try {
            
            String sql = "select Nome from bandas";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                s+=rs.getString("Nome")+"\n";
            }
        } catch (SQLException ex) {
            Logger.getLogger(MusicaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
    
    public int getCodBandas(int n){
        int s=0;
        try {
            
            String sql = "select cod_bandas from bandas where nome = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                s=rs.getInt("cod_bandas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MusicaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    public List select() {
        try {
            List<Banda> bandas = new ArrayList<>();
            PreparedStatement stmt = this.connection.prepareStatement("select * from bandas ");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // criando o objeto Banda
                Banda b = new Banda();
                b.setNome(rs.getString("Nome"));
                b.setCod_bandas(rs.getInt("cod_bandas"));
                b.setData_criacao(rs.getTime("data_criacao"));
                b.setLingua(rs.getString("lingua"));
                // adicionando o objeto à lista
                bandas.add(b);
            }
            rs.close();
            stmt.close();
            return bandas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List selectOrdenado(boolean crescente) {
        List<Banda> bandas = new ArrayList<>();
        PreparedStatement stmt;
        try{
        if(crescente){
            stmt = this.connection.prepareStatement("select * from bandas order by nome asc");
        }else stmt = this.connection.prepareStatement("select * from bandas order by nome desc");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
                // criando o objeto Banda
                Banda b = new Banda();
                b.setNome(rs.getString("Nome"));
                b.setCod_bandas(rs.getInt("cod_bandas"));
                b.setData_criacao(rs.getTime("data_criacao"));
                b.setLingua(rs.getString("lingua"));
                // adicionando o objeto à lista
                bandas.add(b);
            }
            rs.close();
            stmt.close();
        }catch(SQLException ex){
            Logger.getLogger(BandaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bandas;
    }

    
    public boolean insert(Banda b) {
        String sql = "insert into musicas "
                + "(Nome, cod_bandas, data_criacao, lingua)"
                + " values (?,?,?,?)";
        try {
            // prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sql);
            // seta os valores
            stmt.setString(1, b.getNome());
            stmt.setInt(2, b.getCod_bandas());
            stmt.setTime(3, b.getData_criacao());
            stmt.setString(4, b.getLingua());
            // executa
            stmt.execute();
            stmt.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
        
    }

    
    public boolean delete(Banda b) {
        try {
            String sql = "delete from bandas where nome = ?";
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setString(1, b.getNome());
            stmt.execute();
            stmt.close();
            return true;
        } catch (SQLException ex) {
            return false;
        }
        
    }
    
    public boolean update(Banda b){
        try {
            String sql = "update bandas set nome= ? where cod_banda = ?";
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setString(1, b.getNome());
            stmt.setInt(2, b.getCod_bandas());
            stmt.execute();
            stmt.close();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    
    
    
    
    
}
