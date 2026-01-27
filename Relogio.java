import greenfoot.*; 

/**
 * A classe Relogio é responsável por gerenciar o cronômetro do jogo.
 * Ela utiliza o tempo do sistema para calcular o tempo decorrido
 * e formatá-lo em minutos e segundos para exibição na interface.
 * * @author Camila Santos Nascimento, Matheus Vitor da Silva e João Gabriel Tófoli.
 * @version 1.0
 */
public class Relogio {
    private long tempoInicial;
    
    public Relogio() {
        zerar();
    }

    /**
     * Reseta o tempo inicial para o momento atual (agora).
     */
    public void zerar() {
        tempoInicial = System.currentTimeMillis();
    }

    /**
     * Retorna o tempo formatado (Ex: "02 minutos e 15 segundos")
     */
    public String getTempo() {
        long tempoAtual = System.currentTimeMillis();
        long tempoDecorrido = tempoAtual - tempoInicial;

        long totalSegundos = tempoDecorrido / 1000;
        long minutos = totalSegundos / 60;
        long segundos = totalSegundos % 60;

        // Usando sua formatação mais bonita com String.format
        return String.format("%02d m %02d s", minutos, segundos);
    }
}