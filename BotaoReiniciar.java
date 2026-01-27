import greenfoot.*;

/**
 * Representa o botão de reinício do jogo.
 * Este botão é utilizado na tela final para permitir que o jogador 
 * comece uma nova partida, retornando à Fase 1.
 * * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class BotaoReiniciar extends Botao
{
    /**
     * Construtor da classe BotaoReiniciar.
     * Inicializa o botão definindo o texto "Reiniciar" através da chamada
     * ao construtor da superclasse.
     */
    public BotaoReiniciar()
    {
        super("Reiniciar");
    }

    // Sobreescrita do metodo da superclasse
    /**
     * Act - Executa a lógica do botão a cada frame.
     * Verifica se o botão foi clicado pelo usuário e, em caso afirmativo,
     * define o mundo ativo como uma nova instância da Fase1.
     */
    @Override
    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
            Greenfoot.setWorld(new Fase1());
        }
    }
}