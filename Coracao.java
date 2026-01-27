import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Classe que representa o coração.
 * O item coração restaura completamente a vida atual do viking.
 * 
 * @author Camila Santos Nascimento, Matheus Vitor da Silva e João Gabriel Tófoli.
 * @version 1.0
 */
public class Coracao extends Item
{
    private GreenfootImage coracao;
    /**
     * Construtor da classe Coração.
     * @param viking O viking que poderá pegar esse item.
     */
    public Coracao(Viking viking){
        super(viking);
        coracao = new GreenfootImage("coracao.png");
        coracao.scale(210,80);
        setImage(coracao);
    }

    /**
     * Act - do whatever the Coracao wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(encostouNoViking()){
            obterViking().restaurarVida();
            World mundo = getWorld();
            if (mundo != null) {
                mundo.removeObject(this);
            }
        }
    }
}
