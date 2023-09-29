package Estrutura;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;
import java.util.List;

public class Grafo {
    private int nDeVertices; //Números de vértices
    private int[][] matriz; //Matriz do Grafo
    private List<LinkedList<Integer>> lista = new ArrayList<>(); //ArrayList de LinkedList


    public Grafo() {} //Construtor vazio

    public Grafo(int nDeVertices, String s) { //Construtor que recebe o inteiro como o tamanho da matriz e uma string com os dados da matriz
        this.nDeVertices = nDeVertices;
        this.matriz = new int[nDeVertices][nDeVertices];

        for(int i=0; i<nDeVertices; i++){ //Enchendo a arraylist de linkedList
            lista.add(new LinkedList<>());
        }

        String[] aux = s.split("\t"); //Separando em um array de string cada número
        int a = 0;

        for(int i=0; i<nDeVertices; i++){
            for(int j=0; j<nDeVertices; j++){
                try{
                    this.matriz[i][j] = Integer.parseInt(aux[a]); //Passando o dado numérico para a matriz
                } catch(Exception e){
                    System.out.println("O arquivo contém um dado não númerico");
                }
                if(this.matriz[i][j] != 0){
                    this.lista.get(i).add(j+1); //Colocando na lista as conexões
                }

                a++;
            }
        }
    }

    public String BFS(int inicio, int objetivo){ //Função BFS recebe um int inicio que diz onde a busca começa e um int objetivo para saber o caminho entre dois
        boolean[] visitados = new boolean[nDeVertices]; //Lista "isVisited"
        for(int i=0; i<nDeVertices; i++){ //Colocando todos da lista como False
            visitados[i] = false;
        }

        Queue<Integer> fila = new LinkedList<>(); //Criando a fila
        int[] ant = new int[nDeVertices];

        visitados[inicio-1] = true; //Fazendo a primeira operação com o início
        fila.offer(inicio-1);
        ant[inicio-1] = inicio-1;

        System.out.println("\n---Busca em Largura:");
        while(!fila.isEmpty()){ //Inicia while da fila
            int vAtual = fila.poll();
            System.out.print(vAtual+1 + " ");

            for(int vizinho : lista.get(vAtual)){ //Identifica os vizinhos e coloca na fila
                if(!visitados[vizinho-1]){
                    visitados[vizinho-1] = true;
                    fila.offer(vizinho-1);
                    ant[vizinho-1] = vAtual;
                }

            }

            if(fila.isEmpty() &&  visitadosFalse(visitados)){ // caso termine a busca por vizinhos e ainda tiver vértices ainda não visitados
                int i = 0;
                while(visitados[i])
                    i++;
                fila.offer(i);
                visitados[i] = true;
                System.out.print("/ ");
                ant[i] = i; //Definindo o anterior do primeiro como ele mesmo
            }
        }
        
        LinkedList<Integer> aux = new LinkedList<>(); 
        int aux2 = ant[objetivo-1];
        aux.add(objetivo-1);
        while(aux.getLast() != inicio-1){ //Identifica o caminho
            aux.add(aux2);
            aux2 = ant[aux2];
            if(aux.getLast() == aux.get(aux.size()-2)){
                return "Não tem caminho entre "
                        + inicio + "-"
                        + objetivo;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\nCaminho: " + inicio + " -> ");
        for(int i=aux.size()-2; i>=1; i--){ //Passa o caminho pra String
            sb.append((aux.get(i)+1) + " -> ");
        }
        sb.append(objetivo);
        return sb.toString(); //Retorna a String
    }

    public void DFS(int inicio){
        boolean[] visitados = new boolean[nDeVertices]; //Lista "isVisited"
        for(int i=0; i<nDeVertices; i++){ //Colocando todos da lista como False
            visitados[i] = false;
        }

        Stack<Integer> pilha = new Stack<>(); //Craindo a pilha

        visitados[inicio-1] = true; //Fazendo a primeira operação com o início
        pilha.push(inicio-1); //Adicionando-o na pilha

        System.out.println("\n---Busca em Profundidade:");

        while(!pilha.isEmpty()){ //Iniciando o while para escrever todos so vértices
            int vAtual = pilha.pop(); 
            System.out.print(vAtual+1 + " ");

            for (int vizinho : lista.get(vAtual)) { //Buscando os vértices não visitados
                if (!visitados[vizinho - 1]) {
                    visitados[vizinho - 1] = true;
                    pilha.push(vizinho - 1);
                }
            }
            if (pilha.isEmpty() && visitadosFalse(visitados)) { //Caso a pilha esteja vazia mas ainda haja vértices não visitados
                int i = 0;
                while (visitados[i])
                    i++;
                pilha.push(i);
                visitados[i] = true;
                System.out.print("/ ");
            }
        }
    }

    private boolean visitadosFalse(boolean[] visitados){ //Verifica se ainda tem vértices ainda não visitados
        for(boolean x : visitados){
            if(!x)
                return true;
        }
        return false;
    }

    //getters and setters caso necessário.
    public int getNDeVertices() {
        return this.nDeVertices;
    }

    public void setNDeVertices(int nDeVertices) {
        this.nDeVertices = nDeVertices;
    }

    public int[][] getMatriz() {
        return this.matriz;
    }

    public void setMatriz(int[][] matriz) {
        this.matriz = matriz;
    }

    public List<LinkedList<Integer>> getLista() {
        return this.lista;
    }

    public void setLista(List<LinkedList<Integer>> lista) {
        this.lista = lista;
    }
}