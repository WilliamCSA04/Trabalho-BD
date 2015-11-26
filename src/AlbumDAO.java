
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class AlbumDAO {
    private final Connection connection;
    
    
    public AlbumDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public LinkedList<String> nome(){
        LinkedList<String> s = new LinkedList<>();
        try {
            String sql = "select titulo from albuns";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                s.add(rs.getString("titulo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MusicaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
    
    public LinkedList<Integer> codigo(){
        LinkedList<Integer> s = new LinkedList<>();
        try {
            String sql = "select cod_albuns from albuns";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                s.add(rs.getInt("cod_albuns"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MusicaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
    
}
