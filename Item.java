import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Classe que representa os itens coletaveis do jogo.
 * Lida com a distancia que o viking esta do item e se ele encostou.
 * 
 * @author Camila Santos Nascimento, Matheus Vitor da Silva e João Gabriel Tófoli.
 * @version 1.0
 */
public class Item extends Actor{
    private Viking viking;
    
    /**
     * Construtor da classe Item.
     * @param viking O viking que vai pegar os itens.
     */
    public Item(Viking viking){
        this.viking = viking;
    }
    
    /**
     * Act - do whatever the Item wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
    /**
     * Verifica se o viking encostou no item.
     * @return true se o viking enconstou e false caso contrario
     */
    public boolean encostouNoViking(){
        Actor viking = getOneIntersectingObject(Viking.class);
        if(viking != null && vikingEstaPerto(30)){
            return true;
        }
        return false;
    }
    /**
     * Verifica se o viking esta proximo a certo numero de pixels
     * 
     * @param distanciaMaxima o numero de pixels que o viking precisa
     * estar para retornar true.
     * 
     * @return true se o viking estiver dentro da distancia passada e false
     * caso contrario.
     */
    public boolean vikingEstaPerto(int distanciaMaxima) {
        if(this.viking.getWorld() == null){
            return false;
        }
        int distanciaX = Math.abs(this.viking.getX() - getX());
        int distanciaY = Math.abs(this.viking.getY() - getY());

        // Soma das distâncias em X e Y
        return (distanciaX + distanciaY) <= distanciaMaxima;
    }
    
    /**
     * Retorna o viking;
     */
    public Viking obterViking(){
        return viking;
    }
}
