import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A classe Portal representa o objeto de transição de fase no jogo.
 * Ele permanece inativo até que seja "liberado" (geralmente após derrotar todos os inimigos).
 * O jogador precisa interagir com ele quando liberado para avançar para a próxima fase.
 * 
 *  @author Camila Santos Nascimento, Matheus Vitor da Silva e João Gabriel Tófoli.
 * @version 1.0 
 */
public class Portal extends Actor
{
    private boolean liberado = false;

    /**
     * Construtor da classe Portal.
     * Carrega a imagem do portal e a escala para o tamanho desejado.
     */
    public Portal(){
        GreenfootImage imagem = new GreenfootImage("portal.png");
        imagem.scale(150,170);
        setImage(imagem);

    }

    /**
     * O método 'act' é chamado a cada ciclo de execução (frame).
     * Atualmente, este método não executa nenhuma lógica de movimento ou interação própria.
     */
    public void act() {
        //nada aqui..
    }

    /**
     * Define o estado de liberação do portal.
     * Usado pela lógica do mundo (TratandoMundos) para ativá-lo após a condição de vitória.
     * * @param valor true para liberar o portal, false para mantê-lo bloqueado.
     */
    public void setLiberado(boolean valor) {
        liberado = valor;
    }

    /**
     * Retorna o estado atual de liberação do portal.
     * Usado pelo personagem principal (Viking) para saber se pode avançar de fase.
     * * @return true se o portal estiver liberado, false caso contrário.
     */
    public boolean estaLiberado() {
        return liberado;
    }
 }