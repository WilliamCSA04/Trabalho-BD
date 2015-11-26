
import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
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
public class Main {

    private static Scanner entrada = new Scanner(System.in);

    /**
     * FIXME: Limpar console
     *
     */
    public static void main(String[] args) {

        String opcao = "";
        do {

            System.out.println("O que deseja fazer?");
            System.out.println();
            exibirOpcoes();
            opcao = entrada.nextLine();
            try {
                int valor = Integer.parseInt(opcao);
                if (valor > 7 || valor < 0) {
                    System.out.println("Opção Invalida");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("\f");
                System.out.println("Opção invalida");
                continue;
            }
            System.out.println();
            String opcaoSelecionada = "";
            switch (opcao) {
                case "1":
                    List<Musica> m = new MusicaDAO().select();
                    for (Musica m1 : m) {
                        System.out.println(m1);
                    }
                    
                    continue;
                case "2":
                    AlbumDAO adao = new AlbumDAO();
                    System.out.println("Nome da musica: ");
                    String nome = entrada.nextLine();
                    System.out.println("A qual album ela pertence?");
                    System.out.println(adao.nome());
                    String albumNome = entrada.nextLine();
                    int codigoAlbum = 0;
                    do{
                        for(int i=0; i<adao.nome().size();i++){
                        String a = adao.nome().get(i);
                        if(a.equalsIgnoreCase(albumNome)){
                            codigoAlbum=adao.codigo().get(i);
                        }
                    }
                        if(codigoAlbum==0){
                            System.out.println("Album inexistente, tente de novo: ");
                            albumNome = entrada.nextLine();
                        }
                    }while(codigoAlbum==0);
                    System.out.println("Diga o genero da musica:");
                    String genero = entrada.nextLine();
                    int cod_musica = new MusicaDAO().getLista().getLast()+1;
                    new MusicaDAO().insert(new Musica(nome, cod_musica, codigoAlbum, genero));
                    continue;
                    
                case "3":
                    System.out.println("Lista de musicas:");
                    String s = new MusicaDAO().selectTitulo();
                    System.out.println(s);
                    System.out.println("Digite o codigo de musica que deseja atualizar");
                    System.out.print("Digite: ");
                    String cod = entrada.nextLine();
                    while(!s.toLowerCase().contains(cod.toLowerCase())){
                        System.out.println("Codigo errado, digite de novo:");
                        cod = entrada.nextLine();
                    }
                    System.out.println("Diga o novo titulo dessa musica:");
                    nome = entrada.nextLine();
                    new MusicaDAO().update(nome, Integer.parseInt(cod));
                    continue;
                case "4":
                    System.out.println("Lista de musicas:");
                    s = new MusicaDAO().selectTitulo();
                    System.out.println(s);
                    System.out.println("Digite o codigo de musica que deseja remover");
                    System.out.println("Digite: ");
                    cod = entrada.nextLine();
                    while(!s.toLowerCase().contains(cod.toLowerCase())){
                        System.out.println("Codigo errado, digite de novo:");
                        cod = entrada.nextLine();
                    }
                    new MusicaDAO().delete(cod);
                    continue;
                case "5":
                    System.out.println("Digite 'crescente' para que seja ordenado de maneira crescente, se desejar decrescente digite 'decrescente':");
                    String ordem = entrada.nextLine();
                    boolean ordenacao = !ordem.equalsIgnoreCase("decrescente");
                    List<Banda> b = new BandaDAO().selectOrdenado(ordenacao);
                    for (Banda b1 : b) {
                        System.out.println(b1);
                    }
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    continue;
                case "6":
                    System.out.println("Digite o nome de uma banda para ver suas musicas: ");
                    System.out.println(new BandaDAO().select());
                    nome = entrada.nextLine();
                    System.out.println(new MusicaDAO().selectMusicasPorBanda(nome));
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    continue;
                case "7":
                    System.out.println("Digite o nome da musica: ");
                    String titulo = entrada.nextLine();
                    System.out.println(new MusicaDAO().selectNomeMusica(titulo));
                     {
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    continue;

            }
            

        } while (!opcao.equals("0"));

    }

    private static void exibirTabelas() {
        System.out.println("Tabelas:");
        System.out.println("Musicas");
        System.out.println("Bandas");
    }

    private static void exibirOpcoes() {
        System.out.println("1 - Consultar");
        System.out.println("2 - Inserir");
        System.out.println("3 - Atualizar");
        System.out.println("4 - Remover");
        System.out.println("5 - Busca por bandas");
        System.out.println("6 - Musica por banda");
        System.out.println("7 - Busca por nome de musica");
        System.out.println("0 - Sair");
    }

}
