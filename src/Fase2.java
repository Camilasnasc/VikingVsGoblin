import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * A classe Fase2 representa o segundo nível (mundo) do jogo.
 * Ela herda funcionalidades de gerenciamento de estado e som da superclasse Mundo.
 * Esta classe configura um cenário diferente com novas posições
 * de plataformas, inimigos e o objetivo (Portal) do jogador.
 * 
 * @author Camila Santos Nascimento, Matheus Vitor da Silva e João Gabriel Tófoli.
 * @version 1.0
 */
public class Fase2 extends Mundo
{
    /**
     * Construtor para objetos da classe Fase2.
     * Configura o mundo com as dimensões padrão, carrega a imagem de fundo específica da Fase 2,
     * inicia a música de fundo e chama o método de preparação dos objetos.
     */
    public Fase2()
    {   
        GreenfootImage telaDeFundo = new GreenfootImage("cenario2.png");
        telaDeFundo.scale(1200,1000);
        setBackground(telaDeFundo); 
        
        tratandoSomFundo();
        prepare();
    }
    
    /**
     * O método 'act' é chamado a cada ciclo de execução.
     * Sua principal função é verificar se o jogo atingiu uma condição de vitória ou derrota,
     * utilizando a lógica herdada da superclasse Mundo.
     */
    public void act(){
        showText("pontos:" + obterPontos(), 50, 100);
        tratandoVitoriaEDerrota();
    }
    
    /**
     * Prepara o mundo para o início da Fase 2.
     * Cria e posiciona as plataformas, inimigos (Goblins),
     * o jogador (Viking), a barra de vida (HUD), armadilhas (Espinho) e o Portal para proxima fase.
     */
    private void prepare()
    {
        Plataforma plataforma  = new Plataforma(500, 50);
        addObject(plataforma, 236, 887);

        Plataforma plataforma3 = new Plataforma(600, 50);
        addObject(plataforma3, 753, 887);

        Plataforma plataforma2 = new Plataforma(500, 50);
        addObject(plataforma2, 1268, 887);

        Plataforma plataforma4 = new Plataforma(100);
        addObject(plataforma4, 243, 708);

        Plataforma plataforma5 = new Plataforma(100);
        addObject(plataforma5, 378, 657);

        Plataforma plataforma6 = new Plataforma(100);
        addObject(plataforma6, 457, 593);

        Plataforma plataforma7 = new Plataforma(100);
        addObject(plataforma7, 578, 541);

        Plataforma plataforma14 = new Plataforma(100);
        addObject(plataforma14, 684, 464);

        Plataforma plataforma10 = new Plataforma(50);
        addObject(plataforma10, 790, 461);

        Plataforma plataforma11 = new Plataforma(50);
        addObject(plataforma11, 959, 460);

        Plataforma plataforma8 = new Plataforma(50);
        addObject(plataforma8, 1049, 462);

        Plataforma plataforma9 = new Plataforma(50);
        addObject(plataforma9, 874, 464);

        Plataforma plataforma13 = new Plataforma(50);
        addObject(plataforma13, 1144, 460);

        // Plataforma grande do portal
        Plataforma plataforma12 = new Plataforma(300);
        addObject(plataforma12, 1355, 459);

        // Portal
        Portal portal = new Portal();
        addObject(portal, 1430, 357);

        // Personagens
        Viking viking = new Viking();
        addObject(viking, 64, 795);

        Goblin goblin  = new Goblin();
        addObject(goblin, 724, 796);

        Goblin goblin2 = new Goblin();
        addObject(goblin2, 1356, 797);

        Goblin goblin3 = new Goblin();
        addObject(goblin3, 1273, 797);
        
        GoblinMini goblinMini1 = new GoblinMini();
        addObject(goblinMini1, 284, 647);
        
        GoblinMini goblinMini2 = new GoblinMini();
        addObject(goblinMini2, 721, 402);
        // Barra de vida
        BarraDeVida barraDeVida = new BarraDeVida(viking);
        addObject(barraDeVida, 213, 36);

        // Espinhos
        Espinho espinho = new Espinho(viking, 365, 55);
        addObject(espinho, 1063, 841);
        
        //Vinhas 
        Vinhas vinhas1 = new Vinhas(viking);
        addObject(vinhas1, 395, 845);
        
        Vinhas vinhas2 = new Vinhas(viking);
        addObject(vinhas2, 570, 845);
        
        Vinhas vinhas3 = new Vinhas(viking);
        addObject(vinhas3, 814, 845);
        
        //Moeda
        Moeda moeda = new Moeda(viking);
        addObject(moeda,960, 398);
    }     
 }