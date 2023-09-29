import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import Estrutura.Grafo;

public class App {
    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        int n = 0;

        try (BufferedReader leitor = new BufferedReader(new FileReader("pcv4.txt"))) { //Lendo o arquivo e passando para um BufferedReader
            String linha;

            n = Integer.parseInt(leitor.readLine()); //Lendo o tamanho da matriz
            while ((linha = leitor.readLine()) != null) { //Lendo o cada linha do BufferedRead e tranformando em String
                sb.append(linha);
            }

        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao ler o arquivo: " + e.getMessage());
        }

        Grafo gp = new Grafo(n, sb.toString()); //Criando o grafo, o próprio grafo interpreta a string e transforma-a na matriz

        int[][] matriz = gp.getMatriz();
        for(int i=0; i<gp.getNDeVertices(); i++){ //Escrevendo a matriz
            for(int j=0; j<gp.getNDeVertices(); j++){
                System.out.printf("%-5d", matriz[i][j]);
            }

            System.out.println();
        }

        System.out.println();
        int cont = 0;
        while(cont < gp.getLista().size()){ //Escrevendo a lista de conexão dos vértices
            System.out.print("O vertíce " + (cont+1) + " está conectado com: ");
            int aux = 0;

            if(gp.getLista().get(cont).isEmpty()){ //Caso o vértice não esteja conectado com nenhum outro
                System.out.print("Não está conectado com nenhum outro");
            } else {
                while(aux < gp.getLista().get(cont).size() ){
                    System.out.print(gp.getLista().get(cont).get(aux) + " ");
                aux++;
                }
            }

            System.out.println();
            cont++;
        }

        System.out.println("\n" + gp.BFS(1, 4)); //Chamando o BFS e dando como entrada o inicio e o objetivo
        gp.DFS(1); //Chamando a DFS
    }
}