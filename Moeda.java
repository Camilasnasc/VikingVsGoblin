import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Representa o item "Moeda" dentro do jogo.
 * Este objeto é coletável: ao ser tocado pelo Viking, incrementa a pontuação
 * e desaparece do mundo.
 * * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class Moeda extends Item
{
    private GreenfootImage moeda;

    /**
     * Construtor da classe Moeda.
     * Inicializa o item, carrega a imagem "moeda.png", redimensiona para 
     * o tamanho adequado e define a imagem do ator.
     * * @param viking Referência ao objeto Viking (o jogador) passada para a superclasse Item.
     */
    public Moeda(Viking viking){
        super(viking);
        moeda = new GreenfootImage("moeda.png");
        moeda.scale(30,53);
        setImage(moeda);
    }
    
    /**
     * * Verifica se houve colisão com o Viking. Se sim, adiciona pontos e
     * remove a moeda do jogo.
     */
    public void act()
    {
        if(encostouNoViking()){
            // Acessa o objeto Viking e chama o método para aumentar a pontuação
            obterViking().incrementarPontos();
            World mundo = getWorld();
            if (mundo != null) {
                mundo.removeObject(this); 
            }
        }
    }
}