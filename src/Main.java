
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 14201805
 */
public class Main {

    private static ArrayList<String> tabelas = new ArrayList<>();
    private static Scanner entrada = new Scanner(System.in);

    /**
     * FIXME: Limpar console
     * 
     */
    public static void main(String[] args) {
        System.out.println(new MusicaDAO().getLista());
        
        tabelas.add("Musicos");
        tabelas.add("Musicas");
        tabelas.add("Bandas");
        tabelas.add("Albuns");
        
        String opcao = "";
        do {

            System.out.println("O que deseja fazer?");
            System.out.println();
            exibirOpcoes();
            opcao = entrada.next();
            try {
                int valor = Integer.parseInt(opcao);
                if (valor > 4 || valor < 0) {
                    System.out.println("Opção Invalida");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("\f");
                System.out.println("Opção invalida");
                continue;
            }
            System.out.println();
            String opcaoSelecionada="";
            switch (opcao) {
                case "1":
                    System.out.println("Qual tabela deseja consultar?");
                    opcaoSelecionada = "Select ";
                    break;
                case "2":
                    System.out.println("Em qual tabela deseja inserir novos dados?");
                    opcaoSelecionada = "Insert Into ";
                    break;
                case "3":
                    System.out.println("Qual tabela deseja atualizar?");
                    opcaoSelecionada = "Update ";
                    break;
                case "4":
                    System.out.println("De qual tabela deseja remover dados?");
                    opcaoSelecionada = "Delete ";
                    break;
            }
            System.out.println("Digite 'r' para retornar ao menu inicial");
            exibirTabelas();
            opcao = entrada.next();
            do{
                System.out.println("Opção errada");
                System.out.println("Tente novamente");
                opcao = entrada.next();
            }while(!tabelas.contains(opcao) || !opcao.equalsIgnoreCase("r"));

            switch(opcaoSelecionada.toUpperCase()){
                case "R": continue;
                case "INSERT":
                    
                case "SELECT":
                    selectOptions(opcao);
                case "UPDATE":
                case "DELETE":
            }
            
            
            
            Connection con = new ConnectionFactory().getConnection();
            
            
        } while (!opcao.equals("0"));
        
    }

    private static void exibirTabelas() {
        System.out.println("Tabelas:");
        for (String tabela : tabelas) {
            System.out.println(tabela);
        }
    }

    private static void exibirOpcoes() {
        System.out.println("1 - Consultar");
        System.out.println("2 - Inserir");
        System.out.println("3 - Atualizar");
        System.out.println("4 - Remover");
        System.out.println("0 - Sair");
    }
    
    

    private static void selectOptions(String opcao) {
        switch(opcao.toUpperCase()){
            case "MUSICAS":
                System.out.println("Lista ?");
                String pesquisa = entrada.nextLine();
                
        }
    }
}
