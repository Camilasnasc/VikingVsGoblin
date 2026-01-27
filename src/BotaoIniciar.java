import greenfoot.*;
/**
 * Representa o botão que Inicia a fase1 do jogo.
 * Ao ser clicado pelo usuário, este ator altera o mundo atual para a Fase1.
 * @author Camila Santos Nascimento, Matheus Vitor da Silva e João Gabriel Tófoli.
 * @version 1.0
 */
public class BotaoIniciar extends Botao
{
    /**
     * Constructor for objects of class BotaoIniciar
     */
    public BotaoIniciar()
    {
        super("Iniciar");
    }

    // Sobreescrita do metodo da superclasse
    @Override
    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
            Greenfoot.setWorld(new Fase1());
        }
        
    }
}
