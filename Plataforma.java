import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A classe que representa as plataformas.
 * Gera as plataformas que servem como chão e obstaculo para o viking
 * 
 * @author Camila Santos Nascimento, Matheus Vitor da Silva e João Gabriel Tófoli.
 * @version 1.0.
 */
public class Plataforma extends Actor
{
    GreenfootImage plataforma;
    
    /**
     * Construtor da classe Plataforma.
     * Inicializa os atributos da classe e define a escala da plataforma.
     * @param x a altura da plataforma.
     * @param y a largura da plataforma.
     */
    public Plataforma(int x, int y){
        plataforma = new GreenfootImage("plataforma.png");
        plataforma.scale(x,y);
        setImage(plataforma);
    }
    
    /**
     * Construtor da classe Plataforma.
     * Inicializa os atributos da classe e define a altura da plataforma.
     * @param x a altura da plataforma.
     */
    public Plataforma(int x){
        plataforma = new GreenfootImage("plataforma.png");
        plataforma.scale(x,50);
        setImage(plataforma);
    }
    /**
     * Act - do whatever the Plataforma wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
    }
 }
