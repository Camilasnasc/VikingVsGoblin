import greenfoot.*;
import java.util.Random;

/**
 * A classe Personagem representa um ator básico no mundo do Greenfoot.
 * Ele possui atributos para combate, como vida, dano mínimo e máximo,
 * e um mecanismo de tempo de recarga para limitar a frequência de ataques.
 *
 * @author Camila Santos Nascimento, Matheus Vitor da Silva e João Gabriel Tófoli.
 * @version 1.0 
 */
public class Personagem extends Actor {
    private int vida;
    private int danoMinimo;
    private int danoMaximo;
    private int tempoRecargaMax;
    private int tempoRecarga;
    private int vidaInicial;
    private Random aleatorio = new Random();

    /**
     * Construtor da classe Personagem. Inicializa os atributos do personagem.
     * @param vidaInicial A vida com que o personagem começa.
     * @param danoMinimo O dano mínimo possível.
     * @param danoMaximo O dano máximo possível.
     * @param tempoRecargaMax O número de ciclos que deve esperar entre ataques.
     */
    public Personagem(int vidaInicial, int danoMinimo, int danoMaximo, int tempoRecargaMax) {
        this.vida = vidaInicial;
        this.danoMinimo = danoMinimo;
        this.danoMaximo = danoMaximo;
        this.tempoRecargaMax = tempoRecargaMax;
        this.tempoRecarga = 0;
        this.vidaInicial = vidaInicial;
    }

    /**
     * O método 'act' é chamado a cada ciclo de execução (frame).
     * Sua função é atualizar o estado do personagem, neste caso, gerenciando a recarga.
     */
    public void act() {
        recarga();
    }

    /**
     * Verifica se o personagem está pronto para realizar um ataque.
     * * @return true se o tempoRecarga for menor ou igual a 0, false caso contrário.
     */
    public boolean podeAtacar() {
        return tempoRecarga <= 0;
    }

    /**
     * Decrementa o contador de tempoRecarga, se este for maior que zero.
     * Essencialmente, conta o tempo para que o personagem possa atacar novamente.
     */
    public void recarga(){
        if (tempoRecarga > 0) {
            tempoRecarga--;
        }
    }

    /**
     * Executa a ação de ataque. Calcula um dano aleatório e reinicia o tempo de recarga.
     * * @return O valor do dano causado (ou 0 se o personagem estiver em recarga).
     */
    public int atacar() {
        if (!podeAtacar()){
            return 0; 
        }

        // Calcula o dano aleatório
        int dano = aleatorio.nextInt(danoMaximo - danoMinimo + 1) + danoMinimo;
        tempoRecarga = tempoRecargaMax;

        return dano;
    }

    /**
     * Aplica o dano recebido, subtraindo-o da vida atual do personagem.
     * * @param dano A quantidade de vida a ser subtraída.
     */
    public void tomarDano(int dano) {
        vida -= dano;
    }

    /**
     * Remove o personagem do mundo do Greenfoot. Deve ser chamado quando a vida chega a zero.
     */
    public void morrer() {
        World mundo = getWorld();
        if (mundo != null) {
            mundo.removeObject(this);
        }
    }

    /**
     * Retorna o valor atual da vida do personagem.
     * * @return O valor inteiro da vida atual.
     */
    public int obterVida() {
        return vida;
    }

    /**
     * Altera a vida do personagem para 0.
     */
    public void zerarVidaDoPersonagem(){
        this.vida = 0;
    }

    /**
     * Restaura completamente a vida do personagem.
     */
    public void restaurarVida(){
        this.vida = vidaInicial;
    }

    /**
     * Aumenta o danoMinimo(+30) e danoMaximo(+25) que o personagem causa.
     */
    public void aumentarPoder(){
        danoMinimo = danoMinimo+30;
        danoMaximo = danoMaximo+25;
    }

    /**
     * Chama o método de incrementar pontos que está na classe Mundo.
     */
    public void incrementarPontos() {
        World world = getWorld(); 

        if (world != null && world instanceof Mundo) {
            Mundo meuMundo = (Mundo) world;
            meuMundo.incrementarPontos();
        }
    }

    /**
     * Diminui a vida em -10 do personagem.
     */
    public void danoMenor(){
        this.vida -= 10;
    }
}