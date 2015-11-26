
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

public class MusicaDAO {

    // a conexão com o banco de dados
    private Connection connection;
    private LinkedList<Integer> codigoMusicas=new LinkedList<>();
    public MusicaDAO() {
        try {
            this.connection = new ConnectionFactory().getConnection();
            String sql = "select cod_musica from musicas";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                codigoMusicas.add(rs.getInt("cod_musica"));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(MusicaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String selectTitulo(){
        String s="";
        try {
            this.connection = new ConnectionFactory().getConnection();
            String sql = "select cod_musica, titulo from musicas";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                s+=rs.getInt("cod_musica") + " " + rs.getString("titulo")+"\n";
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(MusicaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
    
    public LinkedList<Integer> getLista(){
        Collections.sort(codigoMusicas);
        return codigoMusicas;
    }

  
    
    
    public void insert(Musica musica) {
        String sql = "insert into musicas "
                + "(titulo, cod_musica, cod_albuns, genero)"
                + " values (?,?,?,?)";
        try {
            // prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sql);
            // seta os valores
            stmt.setString(1, musica.getTitulo());
            stmt.setInt(2, musica.getCod_musica());
            stmt.setInt(3, musica.getCod_albuns());
            stmt.setString(4, musica.getGenero());
            // executa
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public int selectCodAlbum(int a){
        int r=0;
        try {
            String sql = "select cod_albuns from musicas where cod_banda=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                r=rs.getInt("cod_albuns");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MusicaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    
//    public String select(String coluna){
//        if(coluna.equalsIgnoreCase("titulo") || coluna.equalsIgnoreCase("cod_musica") || coluna.equalsIgnoreCase("cod_albuns") || coluna.equalsIgnoreCase("genero")){
//            try {
//                String sql = "select " + coluna + " from musicas";
//                PreparedStatement stmt = connection.prepareStatement(sql);
//                ResultSet rs = stmt.executeQuery();
//                String s="";
//                while(rs.next()){
//                    s+= rs.getString(coluna) + "\n";
//                }
//                stmt.close();
//                return s;
//            } catch (SQLException ex) {
//                Logger.getLogger(MusicaDAO.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return "Coluna invalida";
//            
//    }
    
    public boolean update(String titulo, int codigo){
        try {
            String sql = "update musicas set titulo= ? where cod_musica = ?";
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setString(1, titulo);
            stmt.setInt(2, codigo);
            stmt.execute();
            stmt.close();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public boolean delete(String cod_musica) {
        try {
            String sql = "delete from musicas where cod_musica = ?";
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setString(1, cod_musica);
            stmt.execute();
            stmt.close();
            return true;
        } catch (SQLException ex) {
            return false;
        }
        
    }
    
    public Musica selectNomeMusica(String musica){
        try {
            Musica m = new Musica();    
            String sql = "select * from musicas where titulo= ? ";
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, musica);
                ResultSet rs = stmt.executeQuery();
                
                while(rs.next()){
                    m.setTitulo(rs.getString("titulo"));
                    m.setCod_musica(rs.getInt("cod_musica"));
                    m.setCod_albuns(rs.getInt("cod_albuns"));
                    m.setGenero(rs.getString("genero"));
                    
                }
                return m;
            } catch (SQLException ex) {
                Logger.getLogger(MusicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        return new Musica();
    }
    
   
    
    public List<Musica> selectMusicasPorBanda(String banda){
        try {
                
            String sql = "select * from musicas  m inner join albuns a on m.cod_albuns = a.cod_albuns inner join bandas b on a.cod_bandas = b.cod_bandas where UPPER(b.nome)= ? ";
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, banda.toUpperCase());
                ResultSet rs = stmt.executeQuery();
                List<Musica> musicas = new ArrayList<>();
                while(rs.next()){
                    Musica m = new Musica();
                    m.setTitulo(rs.getString("titulo"));
                    m.setCod_musica(rs.getInt("cod_musica"));
                    m.setCod_albuns(rs.getInt("cod_albuns"));
                    m.setGenero(rs.getString("genero"));
                    musicas.add(m);
                }               
                return musicas;
            } catch (SQLException ex) {
                Logger.getLogger(MusicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        return null;
    }

    public List<Musica> select() {
        try {
            List<Musica> musicas = new ArrayList<>();
            PreparedStatement stmt = this.connection.prepareStatement("select * from musicas ");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // criando o objeto Musica
                Musica musica = new Musica();
                musica.setTitulo(rs.getString("titulo"));
                musica.setCod_musica(rs.getInt("cod_musica"));
                musica.setCod_albuns(rs.getInt("cod_albuns"));
                musica.setGenero(rs.getString("genero"));
                // adicionando o objeto à lista
                musicas.add(musica);
            }
            rs.close();
            stmt.close();
            return musicas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
