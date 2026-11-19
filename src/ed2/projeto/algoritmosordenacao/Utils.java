
package ed2.projeto.algoritmosordenacao;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

/**
 * 
 * @author Hilario Tomaz Alves de Oliveira
 *
 */

public class Utils {

  private Utils() {

  }
  
  // Métodos
  
  public static void imprimirVetor(int[] vetor) {
	
	System.out.print( "  Vetor = {" );

    int limite = Math.min(vetor.length, 100);
	  
    for(int i = 0; i < limite; i++) {
    	
      System.out.print( vetor[i] );
      
      if( i < ( limite - 1 ) ) {
    	  
        System.out.print( ", " );
    	  
      }
      
    }
    
    System.out.print( "}" );

    if (vetor.length > limite) {
      System.out.print(" (primeiros 100 de " + vetor.length + " números)");
    }
    
  }

  public static int[] lerArquivo(String caminho) throws IOException {

    return lerConteudo(Files.readString(Path.of(caminho), StandardCharsets.UTF_8));
  }

  public static int[] lerConteudo(String conteudo) {

    if (conteudo.startsWith("\uFEFF")) {
      conteudo = conteudo.substring(1);
    }
    conteudo = conteudo.trim();

    if (conteudo.isEmpty()) {
      throw new IllegalArgumentException("O arquivo está vazio.");
    }

    String[] valores = conteudo.split("[\\s,;]+");
    if (valores.length == 0) {
      throw new IllegalArgumentException("O arquivo deve conter números inteiros.");
    }
    int[] numeros = new int[valores.length];

    for (int i = 0; i < valores.length; i++) {
      try {
        numeros[i] = Integer.parseInt(valores[i]);
      } catch (NumberFormatException erro) {
        throw new IllegalArgumentException("O arquivo deve conter apenas números inteiros.");
      }
    }

    return numeros;
  }

  public static int[] gerarVetor(int tamanhoVetor, int limiteSuperior) {
	
	Random random = new Random();
	
	int[] vetor = new int[tamanhoVetor];
	
    for(int i = 0; i < tamanhoVetor; i++) {
	    	
	  int numero = random.nextInt( limiteSuperior );
	 
	  vetor[i] = numero;
	  
    } // FOR VETOR
	    
    return vetor;
    
  }

}
