import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * A classe TratandoMundos serve como uma classe base para todos os mundos do jogo.
 * Ela implementa lógica de som de fundo estática, gerenciamento de estado (vitória/derrota)
 * e métodos utilitários para buscar o jogador e verificar a presença de inimigos.
 *
 * @author Camila Santos Nascimento, Matheus Vitor da Silva e João Gabriel Tófoli.
 * @version 1.0
 */

public class Mundo extends World
{
    private static GreenfootSound musicaFundo = new GreenfootSound("som_fundo.wav");
    private boolean vitoriaTocada = false;
    private boolean derrotaTocada = false;
    private static int pontos = 0;
    protected static Relogio relogio = new Relogio();
    /**
     * Construtor da classe TratandoMundos.
     * Inicializa o mundo com o tamanho padrão (1500x900 pixels).
     */
    public Mundo ()
    {   
        super(1500, 900, 1);
    }

    /**
     * Chamado automaticamente quando o botão "Executar" é pressionado no Greenfoot.
     * Inicia a reprodução da música de fundo em loop, se ainda não estiver tocando.
     */
    public void started() {
        if (!musicaFundo.isPlaying()) {
            musicaFundo.setVolume(20);
            musicaFundo.playLoop();
        }
    }

    /**
     * Chamado automaticamente quando o botão "Parar" é pressionado.
     * Pausa a música de fundo.
     */
    public void stopped() {

        if (musicaFundo.isPlaying()) {
            musicaFundo.pause();
        }
    }

    /**
     * Ajusta o volume da música de fundo.
     */
    public void tratandoSomFundo() {
        musicaFundo.setVolume(30);
    }

    /**
     * Para a música de fundo completamente. Usado ao final do jogo na derrota.
     */
    public void pararMusica() {
        musicaFundo.stop();
    }

    /**
     * Procura e retorna a primeira instância do Viking no mundo.
     * Método utilitário para verificar o estado do jogador.
     * * @return O objeto Viking encontrado, ou null se não houver um.
     */
    public Viking buscarViking(){
        List<Viking> viking = getObjects(Viking.class);
        if(viking.size() > 0){
            return viking.get(0);
        }
        return null;
    }

    /**
     * Verifica se ainda há algum Goblin vivo no mundo.
     * Serve para checar a condição de vitória e liberar o Portal.
     * * @return true se pelo menos um Goblin com vida > 0 for encontrado, false caso contrário.
     */
    public boolean existeGoblinVivo() {
        List<Goblin> goblins = getObjects(Goblin.class);

        for (Goblin gob : goblins) {
            if (gob.obterVida() > 0) { 
                return true;
            }
        }
        return false;
    }

    /**
     * Gerencia a lógica de fim de jogo, verificando a condição de vitória (Goblins mortos)
     * e a condição de derrota (Viking morto).
     */
    public void tratandoVitoriaEDerrota() {
        Viking viking = buscarViking();

        // Vitória
        if (!existeGoblinVivo() && !vitoriaTocada) {
            showText("VITÓRIA! O portal foi liberado!", 750, 450);

            Greenfoot.playSound("vitoria.wav");
            vitoriaTocada = true;

            // libera o portal
            List<Portal> portais = getObjects(Portal.class);
            if (!portais.isEmpty()) {
                Portal portal = portais.get(0);
                portal.setLiberado(true);
            }
        }

        // Derrota
        if ((viking == null || viking.obterVida() <= 0) && !derrotaTocada) {
            showText("DERROTA! Você foi derrotado!", 750, 450);

            pararMusica();
            Greenfoot.playSound("derrota.wav");
            derrotaTocada = true;

            if (viking != null) {
                viking.iniciarMorte();
            }

            Greenfoot.delay(60);

            // Reinicia o jogo do zero
            Greenfoot.setWorld(new Fase1());
            zerarPontos();
        }
    }

    /**
     * Incrementa 20 pontos para o jogador.
     */
    public void incrementarPontos(){
        pontos += 20;
    }

    /**
     * Retorna o numero de pontos
     * @return pontos o numero de pontos atual do jogador.
     */
    public int obterPontos(){
        return pontos;
    }

    /**
     * Metodo que zera os ponto. Para que não fique com pontos de partidas anteriores.
     */
    public void zerarPontos(){
        pontos = 0;
    }
}