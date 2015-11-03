/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rdias
 */
import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class DBApplication { //DELETE ME,PLEASE

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        Connection con = new ConnectionFactory().getConnection();
        /*String sql = "insert into pessoas"
                + " (cpf, nome,endereco,sexo,datanasc)"
                + " values ('12345678', 'Rodrigo Rivera', 'Rua A, 10', 'M', '22/05/1988')";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.execute();
        System.out.println("Gravado!");*/
        PreparedStatement stmt = con.prepareStatement("select * from pessoas");
// executa um select
        ResultSet rs = stmt.executeQuery();
// itera no ResultSet
        while (rs.next()) {
            String cpf = rs.getString("cpf");
            String nome = rs.getString("nome");
            String endereco = rs.getString("endereco");
            String sexo = rs.getString("Sexo");
            String datanasc = rs.getString("datanasc");
            System.out.println(cpf + " :: " + nome + " :: " + datanasc + " :: " + endereco);
        }
// Fecha todos os objetos
        rs.close();
        stmt.close();
        con.close();

    }

}
