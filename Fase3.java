import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A classe Fase3 representa a ultima fase(mundo) do jogo.
 * Ela herda funcionalidades de gerenciamento de estado e som da superclasse Mundo.
 * Esta classe configura um cenário diferente com novas posições
 * de plataformas, inimigos e o objetivo (Portal) do jogador.
 * 
 * @author Camila Santos Nascimento, Matheus Vitor da Silva e João Gabriel Tófoli.
 * @version 1.0
 */
public class Fase3 extends Mundo
{
    /**
     * Constructor for objects of class Fase3.
     * 
     */
    public Fase3()
    {
        GreenfootImage telaDeFundo = new GreenfootImage("telaDeFundoBoss.png");
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
     * Prepara o mundo para o início do programa.
     * Ou seja: criar os objetos iniciais e adicioná-los ao mundo.
     */
    private void prepare()
    {
        Plataforma plataforma = new Plataforma(600, 50);
        addObject(plataforma,128,881);
        plataforma.setLocation(299,886);
        plataforma.setLocation(292,886);
        Plataforma plataforma2 = new Plataforma(500, 50);
        addObject(plataforma2,1054,884);
        plataforma2.setLocation(799,875);
        plataforma2.setLocation(817,886);
        Plataforma plataforma3 = new Plataforma(400, 50);
        addObject(plataforma3,1365,881);
        plataforma3.setLocation(1296,876);
        removeObject(plataforma3);
        //Plataforma plataforma3 = new Plataforma(500, 50);
        addObject(plataforma3,1338,874);
        plataforma3.setLocation(1296,886);
        GoblinBoss goblinBoss = new GoblinBoss();
        addObject(goblinBoss,1341,718);
        Plataforma plataforma4 = new Plataforma(100);
        addObject(plataforma4,349,607);
        Plataforma plataforma5 = new Plataforma(100);
        addObject(plataforma5,383,492);
        Plataforma plataforma6 = new Plataforma(100);
        addObject(plataforma6,514,555);
        Plataforma plataforma7 = new Plataforma(100);
        addObject(plataforma7,245,539);
        Plataforma plataforma8 = new Plataforma(100);
        addObject(plataforma8,531,472);
        Plataforma plataforma9 = new Plataforma(100);
        addObject(plataforma9,114,626);
        Plataforma plataforma10 = new Plataforma(100);
        addObject(plataforma10,246,451);
        Plataforma plataforma11 = new Plataforma(100);
        addObject(plataforma11,761,515);
        plataforma9.setLocation(194,710);
        plataforma9.setLocation(198,683);
        plataforma9.setLocation(210,703);
        plataforma9.setLocation(189,679);
        plataforma4.setLocation(322,629);
        plataforma4.setLocation(320,621);
        plataforma6.setLocation(468,570);
        plataforma11.setLocation(690,526);
        plataforma11.setLocation(754,523);
        plataforma11.setLocation(732,514);
        plataforma8.setLocation(893,585);
        plataforma8.setLocation(875,577);
        plataforma6.setLocation(586,588);
        plataforma6.setLocation(573,580);
        plataforma5.setLocation(676,517);
        plataforma5.setLocation(735,518);
        plataforma5.setLocation(664,514);
        plataforma11.setLocation(749,514);
        plataforma11.setLocation(745,514);
        plataforma11.setLocation(750,515);
        plataforma8.setLocation(847,582);
        plataforma8.setLocation(851,576);
        plataforma6.setLocation(554,576);
        plataforma6.setLocation(582,574);
        plataforma6.setLocation(550,582);
        plataforma6.setLocation(590,566);
        plataforma6.setLocation(564,566);
        plataforma6.setLocation(559,576);
        plataforma4.setLocation(433,656);
        plataforma4.setLocation(433,656);
        plataforma4.setLocation(440,646);
        plataforma9.setLocation(301,711);
        plataforma9.setLocation(294,691);
        plataforma7.setLocation(992,630);
        plataforma7.setLocation(968,648);
        plataforma7.setLocation(947,634);
        plataforma7.setLocation(957,648);
        plataforma10.setLocation(1084,692);
        plataforma10.setLocation(1084,696);
        Viking viking = new Viking();
        addObject(viking,63,780);
        viking.setLocation(46,802);
        viking.setLocation(45,799);
        Coracao coracao = new Coracao(viking);
        addObject(coracao,810,407);
        coracao.setLocation(761,411);
        coracao.setLocation(763,410);
        coracao.setLocation(768,413);
        coracao.setLocation(768,410);
        coracao.setLocation(767,406);
        coracao.setLocation(780,400);
        ElixirDoPoder elixirDoPoder = new ElixirDoPoder(viking);
        addObject(elixirDoPoder,661,404);
        elixirDoPoder.setLocation(670,401);
        elixirDoPoder.setLocation(664,399);
        GoblinResistente goblinResistente = new GoblinResistente();
        addObject(goblinResistente,785,808);
        goblinResistente.setLocation(878,779);
        goblinResistente.setLocation(878,786);
        GoblinResistente goblinResistente2 = new GoblinResistente();
        addObject(goblinResistente2,487,762);
        goblinResistente2.setLocation(503,787);
        goblinBoss.setLocation(1258,741);
        goblinBoss.setLocation(1264,723);
        Portal portal = new Portal();
        addObject(portal,1429,784);
        goblinBoss.setLocation(1452,783);
        goblinBoss.setLocation(1442,783);
        plataforma3.setLocation(1118,746);
        goblinBoss.setLocation(1185,657);
        plataforma3.setLocation(1257,712);
        plataforma3.setLocation(1237,847);
        goblinBoss.setLocation(1242,748);
        goblinBoss.setLocation(1253,770);
        goblinBoss.setLocation(1256,720);
        plataforma3.setLocation(1310,880);
        plataforma3.setLocation(1219,712);
        goblinBoss.setLocation(1218,722);
        plataforma3.setLocation(1495,881);
        plataforma3.setLocation(1236,884);
        plataforma3.setLocation(1269,883);
        plataforma3.setLocation(1250,886);
        Plataforma plataforma12 = new Plataforma(100);
        addObject(plataforma12,1458,885);
        goblinBoss.setLocation(1459,883);
        goblinBoss.setLocation(1466,883);
        goblinBoss.setLocation(1468,883);
        goblinBoss.setLocation(1468,881);
        goblinBoss.setLocation(1464,883);
        goblinBoss.setLocation(1469,777);
        goblinBoss.setLocation(1424,779);
        goblinBoss.setLocation(1424,781);
        goblinBoss.setLocation(1247,757);
        goblinBoss.setLocation(1276,719);
        plataforma9.setLocation(303,685);
        plataforma9.setLocation(303,694);
        goblinBoss.setLocation(1266,747);
        goblinBoss.setLocation(1275,747);
        BarraDeVida barraDeVida = new BarraDeVida(viking);
        addObject(barraDeVida, 213, 36);
        //Vinhas 
        Vinhas vinhas1 = new Vinhas(viking);
        addObject(vinhas1, 369, 845);
        
        Vinhas vinhas2 = new Vinhas(viking);
        addObject(vinhas2, 1033, 845);
        
        Vinhas vinhas3 = new Vinhas(viking);
        addObject(vinhas3, 1114, 655);
        
        //Moeda
        Moeda moeda = new Moeda(viking);
        addObject(moeda,954, 580);
        
        Moeda moeda1 = new Moeda(viking);
        addObject(moeda1,439, 580);
    }
}
