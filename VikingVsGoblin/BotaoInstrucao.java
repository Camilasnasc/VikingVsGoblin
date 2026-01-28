import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Representa o botão que leva à tela de instruções.
 * Ao ser clicado pelo usuário, este ator altera o mundo atual para a TelaInstrucoes.
 * @author Camila Santos Nascimento, Matheus Vitor da Silva e João Gabriel Tófoli.
 * @version 1.0
 */
public class BotaoInstrucao extends Botao
{
    /**
     * Construtor da classe BotaoInstrucao.
     * Define o texto "Instruções" para ser exibido no botão através
     * do construtor da superclasse Botao.
     */
    public BotaoInstrucao(){
        super("Instruções");
    }
    
    /**
     * Act - Executa as ações do botão a cada ciclo.
     * Verifica se o mouse foi clicado sobre este objeto. Caso positivo,
     * instancia e define o novo mundo como TelaInstrucoes.
     */
    @Override
    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
            Greenfoot.setWorld(new TelaInstrucoes());
        }
        
    }   
}