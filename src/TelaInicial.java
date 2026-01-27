import greenfoot.*;

/**
 * Representa a tela de menu principal do jogo.
 * Esta classe configura o cenário de fundo e posiciona os botões de interação
 * (Iniciar e Instrução) para que o jogador possa navegar pelo jogo.
 * @author Camila Santos Nascimento, Matheus Vitor da Silva e João Gabriel Tófoli.
 * @version 1.0
 */
public class TelaInicial extends Mundo
{
    private Botao botaoIniciar;
    private Botao botaoInstrucao;

    /**
     * Construtor da classe TelaInicial.
     * Inicializa o mundo, carrega e ajusta a imagem de fundo e adiciona
     * os botões de menu nas posições especificadas.
     */
    public TelaInicial()
    {
        super(); 
        
        GreenfootImage telaDeFundo = new GreenfootImage("telaInicial.png");
        
        telaDeFundo.scale(1500, 900);
        
        setBackground(telaDeFundo);
        
        Botao botaoIniciar = new BotaoIniciar();
        addObject(botaoIniciar, 750, 450);
        
        Botao botaoIntrucao = new BotaoInstrucao();
        addObject(botaoIntrucao, 750, 550);
    }
}