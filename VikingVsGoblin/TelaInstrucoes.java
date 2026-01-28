import greenfoot.*;

/**
 * Representa a tela de instruções do jogo.
 * Esta classe é responsável por exibir as regras e comandos para o jogador,
 * permitindo o retorno ao menu principal através do botão de voltar.
 * @author Camila Santos Nascimento, Matheus Vitor da Silva e João Gabriel Tófoli.
 * @version 1.0
 */
public class TelaInstrucoes extends World
{
    public TelaInstrucoes()
    {    
        super(1500, 900, 1);

        GreenfootImage telaDeInstrucao = new GreenfootImage("telaInstrucao.png");
        telaDeInstrucao.scale(1500, 900);
        setBackground(telaDeInstrucao);

        addObject(new BotaoVoltar(), 120, 820);
    }
}