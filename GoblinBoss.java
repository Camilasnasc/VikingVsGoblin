import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Metodo que lida com a movimentação e ataque do GoblinBoss.
 * 
 * @author Camila Santos Nascimento, Matheus Vitor da Silva e João Gabriel Tófoli.
 * @version 1.0
 */
public class GoblinBoss extends Goblin
{
    private static int vida = 1000;
    private static final int danoMin = 25;
    private static final int danoMax = 75;
    private static final int tempoDeRecarga = 5;
    private int contadorAnimacao;
    private GreenfootImage parado;
    private static boolean naoChegouNaBorda;
    private static int temporizadorDoAtaque;
    private static boolean estaAtacando;
    private static int delayParaAnimacaoDoAtaque;
    private int indiceAtaqueBoss;

    public GoblinBoss(){
        super(vida, danoMin, danoMax, tempoDeRecarga);
        this.contadorAnimacao = 0;
        naoChegouNaBorda = true;
        inicializarVetoresDeImagem();
        temporizadorDoAtaque = 0;
        delayParaAnimacaoDoAtaque = 0;
        estaAtacando = false;
        this.indiceAtaqueBoss = 0;
    }

    /**
     * Act - do whatever the GoblinBoss wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        recarga();
        aplicarGravidade();
        verificarColisaoPlataforma();
        this.contadorAnimacao++;
        if(contadorAnimacao == 5){
            if(!estaAtacando){
                animarMovimento();
                moverAutomaticamente();
                temporizadorDoAtaque++;
            }

            if(temporizadorDoAtaque == 70){
                estaAtacando = true;
                animarAtaque(atacando_direita, atacando_esquerda);
                parado();
            }
            contadorAnimacao = 0;
        }   
        
        if(obterVida() <= 0){
            morrer();
        }
    }
    
    /**
     * Inicializa os vetores de imagens.
     */
    public void inicializarVetoresDeImagem(){
        andando_direita = new GreenfootImage[20];
        andando_direita = gerarVetorPNG(andando_direita, "andandoDireitaBoss",220,240);

        andando_esquerda = new GreenfootImage[20];
        andando_esquerda = gerarVetorPNG(andando_esquerda, "andandoEsquerdaBoss", 220,240);

        atacando_direita = new GreenfootImage[10];
        atacando_direita = gerarVetorPNG(atacando_direita,"direitaAtacandoBoss",220,240);

        atacando_esquerda = new GreenfootImage[10];
        atacando_esquerda = gerarVetorPNG(atacando_esquerda,"esquerdaAtacandoBoss",220,240);

        morrendo = new GreenfootImage[10];
        morrendo = gerarVetorPNG(morrendo, "morrendoBoss",220,240);

        parado = new GreenfootImage("paradoBoss.png");
        parado.scale(260,280);
    }
    /**
     * Move automaticamente, quando chega a borda ele vira para a outra direção.
     */
    @Override
    public void moverAutomaticamente(){
        if(naoChegouNaBorda){
            setLocation(getX()-4, getY());
            falsificarViradoParaDireita();
            if(precisaVirar()){
                naoChegouNaBorda = false;
            }
        }
        if(!naoChegouNaBorda){
            setLocation(getX()+4, getY());
            validarViradoParaDireita();
            if(precisaVirar()){
                naoChegouNaBorda = true;
            }
        }
    }
    
    /**
     * Metodo que controla as variaveis de controle e chama a animação com o dano,
     */
    public void parado(){
        delayParaAnimacaoDoAtaque++;
        atacarAutomatico(atacando_direita, atacando_esquerda);
        if(delayParaAnimacaoDoAtaque == 50){
            estaAtacando = false;
            delayParaAnimacaoDoAtaque = 0;
            temporizadorDoAtaque = 0;
        }
    }
    
    /**
     * Metodo que lida com o dano causado ao viking e roda a animação de ataque.
     * Foi sobrescrito pq o range do goblin normal era curto, esse boss é maior, ent
     * precisa de um range maior.
     * @param atacar_direita O vetor de imagens do goblin atacando a direita
     * @param atacar_esquerda O vetor de imagens do goblin atacando a esquerda
     */
    @Override
    public void atacarAutomatico(GreenfootImage[] atacarDireita, GreenfootImage[] atacarEsquerda) {
        int range = 100; 
    
        Viking viking = (Viking) getOneObjectAtOffset(
                obterViradoParaDireita() ? range : -range, 0, Viking.class);
    
        if (viking == null){
            return;
        }
    
        animarAtaqueBoss(atacarDireita, atacarEsquerda);
    
        if (this.indiceAtaqueBoss == 5) {
            if (podeAtacar()) {
                int dano = atacar();
                viking.tomarDano(dano);
            }
        }
    }

    /**
     * Lida com a animação de ataque do boss.
     * @param atacar_direita O vetor de imagens do goblin atacando a direita
     * @param atacar_esquerda O vetor de imagens do goblin atacando a esquerda
     */
    public void animarAtaqueBoss(GreenfootImage[] atacar_direita, GreenfootImage[] atacando_esquerda){
        this.indiceAtaqueBoss++;

        if (obterViradoParaDireita()) {
            if (this.indiceAtaqueBoss >= atacar_direita.length) {
                this.indiceAtaqueBoss = 0;
            }
            setImage(atacar_direita[this.indiceAtaqueBoss]);
        } else {
            if (this.indiceAtaqueBoss >= atacando_esquerda.length){
                this.indiceAtaqueBoss = 0;
            }
            setImage(atacando_esquerda[this.indiceAtaqueBoss]);
        }
    }
}