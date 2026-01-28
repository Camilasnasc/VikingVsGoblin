import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A classe ElixirDoPoder, que é um item, faz com que o dano minimo e maximo do viking 
 * seja aumentado.
 * 
 * @author Camila Santos Nascimento, Matheus Vitor da Silva e João Gabriel Tófoli.
 * @version 1.0
 */
public class ElixirDoPoder extends Item
{
    private GreenfootImage elixir;
    /**
     * Construtor da classe ElixirDoPoder.
     * @param viking O viking que poderá pegar esse item.
     */
    public ElixirDoPoder(Viking viking){
        super(viking);
        elixir = new GreenfootImage("elixir.png");
        elixir.scale(30,53);
        setImage(elixir);
    }
    /**
     * Act - do whatever the ElixirDoPoder wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(encostouNoViking()){
            obterViking().aumentarPoder();
            World mundo = getWorld();
            if (mundo != null) {
                mundo.removeObject(this);
            }
        }
    }
}
