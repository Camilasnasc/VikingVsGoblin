import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A classe que representa os espinhos.
 * Ele verifica se o viking esta perto, se estiver, mata o viking.
 * 
 * @author Camila Santos Nascimento, Matheus Vitor da Silva e João Gabriel Tófoli.
 * @version 1.0.
 */
public class Espinho extends Actor
{
    private GreenfootImage espinho;
    private Viking viking;
    
    /**
     * Construtor da classe Espinho.
     * Inicializa os atributos da classe.
     * @param viking o vking que receberá hit kill.
     */
    public Espinho(Viking viking){
        this.viking = viking;
        espinho = new GreenfootImage("espinho.png");
        espinho.scale(100,80);
        setImage(espinho);
    }
    /**
     * Construtor da classe Espinho.
     * Inicializa os atributos da classe e define a escala dos espinhos.
     * @param viking o vking que receberá hit kill.
     * @param x a altura dos espinhos.
     * @param y a largura dos espinhos.
     */
    public Espinho(Viking viking, int x, int y){
        this.viking = viking;
        espinho = new GreenfootImage("espinho.png");
        espinho.scale(x,y);
        setImage(espinho);
    }

    /**
     * Verifica a todo freme se o viking esta a uma distancia menor ou igual 120 pixels de distancia.
     * Se estiver a distancia for igual ou menor a 120, o viking recebe hit kill.
     */
    public void act()
    {
        if(vikingEstaPerto(120) && viking.estaVivo()){
            hitKillViking();
            return;
        }
    }
    
    /**
     * Altera a vida atual do viking para 0. Hit kill.
     */
    public void hitKillViking(){
        viking.zerarVidaDoPersonagem();
    }
    
    /**
     * Verifica se o viking esta a uma distancia especifica de pixels dos espinhos
     * @param distancia a quantidade de pixels que vai ser considerada perto do viking
     * @return true se a distancia esta dentro da distancia desejada de pixels e false caso contrário.
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