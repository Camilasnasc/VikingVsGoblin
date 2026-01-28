import greenfoot.*;

/**
 * Representa a tela de encerramento.
 * * @author Camila Santos Nascimento, Matheus Vitor da Silva e João Gabriel Tófoli.
 * @version 1.1
 */
public class TelaFinal extends Mundo
{
    private Botao botaoReiniciar;

    public TelaFinal()
    {
        super();

        GreenfootImage telaDeFundo = new GreenfootImage("telaFinal.png");
        telaDeFundo.scale(1500, 900);
        setBackground(telaDeFundo);
        
        botaoReiniciar = new BotaoReiniciar();
        addObject(botaoReiniciar, 750, 450);

        showText( "" + obterPontos(), 880, 760);

        String tempoFinal = Mundo.relogio.getTempo(); 
        showText(tempoFinal, 975, 825);
    }
}