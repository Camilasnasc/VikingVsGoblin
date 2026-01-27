import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Trata as vinhas. Obstaculo para o viking, causa 10 de dano ao ser tocada
 * 
 * @author Camila Santos Nascimento, Matheus Vitor da Silva e João Gabriel Tófoli.
 * @version 1.0
 */
public class Vinhas extends Actor
{
    private GreenfootImage vinhas;
    private Viking viking;
    
    /**
     * Construtor da classe Espinho.
     * Inicializa os atributos da classe.
     * @param viking o vking que receberá hit kill.
     */
    public Vinhas(Viking viking){
        this.viking = viking;
        vinhas = new GreenfootImage("vinhas.png");
        vinhas.scale(33,38);
        setImage(vinhas);
    }

    /**
     * Verifica a todo freme se o viking esta a uma distancia menor ou igual 120 pixels de distancia.
     * Se estiver a distancia for igual ou menor a 120, o viking recebe hit kill.
     */
    public void act()
    {
        if(isTouching(Viking.class) && viking.estaVivo() && vikingEstaPerto(70)){
            viking.danoMenor(); // Aplica o dano
            getWorld().removeObject(this); // Remove a vinha para não dar dano infinito
        }
    }
    
    /**
     * Verifica se o viking esta a uma distancia especifica de pixels dos espinhos
     * @param distancia a quantidade de pixels que vai ser considerada perto do viking
     * @return true se a distancia esta dentro da distancia desejada de pixels e false caso contrário.
     *
     */
    public boolean vikingEstaPerto(int distancia){
        //Math.abs tranforma -30 em 30. numeros negativos em positivos.
        int difX = Math.abs(getX() - viking.getX()); 
        int difY = Math.abs(getY() - viking.getY());

        return (difX + difY) <= distancia;
        //retorna true se a distancia é menor ou igual o numero de celulas de distancia
        //do inimigo
    }
 }
