/*
 * Créditos dos Assets:
 * Os gráficos utilizados neste jogo (background e personagens) foram baixados gratuitamente no site CraftPix.
 * Assets disponibilizados para uso livre conforme a licença do autor.
 * Site: https://craftpix.net
 */

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Fase1 é o mundo principal da Fase 1 do jogo.
 * Ele herda funcionalidades de gerenciamento de estado e som da superclasse TratandoMundos.
 * Esta classe é responsável por configurar o cenário, adicionar os objetos iniciais
 * (plataformas, inimigos, jogador, etc.) e iniciar o jogo.
 * 
 * * @author Camila Santos Nascimento, Matheus Vitor da Silva e João Gabriel Tófoli.
 * @version 1.0
 */
public class Fase1 extends Mundo
{ 
    private static int contadorParaInstrucoes;
    private Botao botao;
    /**
     * Construtor para objetos da classe Fase1.
     * Define o mundo inicial (TelaInicial), configura a imagem de fundo,
     * inicia a música de fundo e chama o método de preparação dos objetos.
     */
    public Fase1()
    {   
        GreenfootImage telaDeFundo = new GreenfootImage("cenario.png");
        telaDeFundo.scale(1200,1000);
        setBackground(telaDeFundo);

        tratandoSomFundo();

        // Chama o método para posicionar os atores no mundo
        prepare();
        contadorParaInstrucoes = 0;
        zerarPontos();
        Mundo.relogio.zerar();
    }

    /**
     * O método 'act' é chamado a cada ciclo de execução.
     * Gerencia a lógica de vitória ou derrota do jogo, chamando
     * um método de verificação de estado herdado de TratandoMundos.
     */
    public void act(){
        showText("pontos:" + obterPontos(), 50, 100);
        tratandoVitoriaEDerrota();
    }

    /**
     * Prepara o mundo para o início do programa (Fase 1).
     * Cria e posiciona todos os objetos iniciais (plataformas, inimigos, jogador, portal e espinhos) no mundo.
     */
    private void prepare()
    {

        // Plataformas iniciais
        Plataforma plataforma = new Plataforma(500, 50);
        addObject(plataforma, 227, 881);

        Plataforma plataforma2 = new Plataforma(500, 50);
        addObject(plataforma2, 703, 881);

        Plataforma plataforma3 = new Plataforma(500, 50);
        addObject(plataforma3, 1163, 881);

        Plataforma plataforma4 = new Plataforma(100, 50);
        addObject(plataforma4, 1449, 881);

        // Portal
        Portal portal = new Portal();
        addObject(portal, 1431, 277);

        // Inimigos
        Goblin goblin = new Goblin();
        addObject(goblin, 840, 787);

        Goblin goblin2 = new Goblin();
        addObject(goblin2, 968, 786);

        // Jogador
        Viking viking = new Viking();
        addObject(viking, 81, 791);

        // Barra de vida
        BarraDeVida barraDeVida = new BarraDeVida(viking);
        addObject(barraDeVida, 223, 32);

        // Espinho
        Espinho espinho = new Espinho(viking, 300, 50);
        addObject(espinho, 558, 839);

        // Plataformas adicionais
        Plataforma plataforma5 = new Plataforma(300, 50);
        addObject(plataforma5, 567, 695);

        Plataforma plataforma6 = new Plataforma(300);
        addObject(plataforma6, 1455, 369);

        Plataforma plataforma7 = new Plataforma(100);
        addObject(plataforma7, 1335, 710);

        Plataforma plataforma8 = new Plataforma(100);
        addObject(plataforma8, 1211, 637);

        Plataforma plataforma9 = new Plataforma(100);
        addObject(plataforma9, 1069, 597);

        Plataforma plataforma10 = new Plataforma(100);
        addObject(plataforma10, 1071, 448);

        Plataforma plataforma11 = new Plataforma(100);
        addObject(plataforma11, 1211, 400);

        //moeda
        Moeda moeda1 = new Moeda(viking);
        addObject(moeda1, 1200, 800); 
        Moeda moeda2 = new Moeda(viking);
        addObject(moeda2, 600, 800);
    }
}