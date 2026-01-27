import greenfoot.*;
/**
 * Representa o botão que leva à tela de instruções.
 * Ao ser clicado pelo usuário, este ator altera o mundo atual para a TelaInstrucoes.
 * @author Camila Santos Nascimento, Matheus Vitor da Silva e João Gabriel Tófoli.
 * @version 1.0
 */
public class BotaoVoltar extends Botao
{
    public BotaoVoltar() {
        super("Voltar");
    }

    @Override
    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
            Greenfoot.setWorld(new TelaInicial());
        }
    }
}