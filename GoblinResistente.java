import greenfoot.*;  

/**
 * A classe GoblinResistente cria um goblin com mais vida, porem menos dano.
 * GoblinResistente é um dos inimigos que o viking precisa matar para liberar o portal.
 * 
 * @author Camila Santos Nascimento, Matheus Vitor da Silva e João Gabriel Tófoli.
 * @version 1.0
 */
public class GoblinResistente extends Goblin
{
    private static int vida = 200;
    private static final int danoMin = 1;
    private static final int danoMax = 2;
    private static final int tempoDeRecarga = 5;
    private GreenfootImage[] andandoDireita;
    private GreenfootImage[] andandoEsquerda;
    private GreenfootImage[] atacandoDireita;
    private GreenfootImage[] atacandoEsquerda;

    private int contadorParaAnimacao;
    // ALTERAÇÃO: Use um nome único para garantir que não haja confusão.
    private int indiceAtaqueResistente; 
    private int indiceAtual;    

    public GoblinResistente(){
        super(vida, danoMin, danoMax, tempoDeRecarga);
        inicializarVetores();
        setImage(andandoEsquerda[0]);

        this.contadorParaAnimacao = 0;
        // Utilize o novo nome
        this.indiceAtaqueResistente = 0;
        this.indiceAtual = 0;
    }

    public void act()
    {
        recarga();
        aplicarGravidade();
        verificarColisaoPlataforma();
        contadorParaAnimacao++;

        if(this.contadorParaAnimacao == 3){
            animarMovimento();
            moverAutomaticamente();
            // Agora chama nosso próprio método
            atacarAutomaticoResistente();
            this.contadorParaAnimacao = 0;
        }   

        if(obterVida() <= 0){
            Mundo mundo = (Mundo) getWorld(); 
            mundo.incrementarPontos();
            morrer();
        }
    }

    /**
     * Ataca automaticamente quando o viking esta dentro do range(50 pixels).
     */
    public void atacarAutomaticoResistente() {
        int range = 50; 
    
        Viking viking = (Viking) getOneObjectAtOffset(
                obterViradoParaDireita() ? range : -range, 0, Viking.class);
    
        if (viking == null){
            return;
        }
    
        animarAtaque(atacandoDireita, atacandoEsquerda);
    
        if (this.indiceAtaqueResistente == 5) {
            if (podeAtacar()) {
                int dano = atacar();
                viking.tomarDano(dano);
            }
        }
    }
    
    /**
     * Lida com a movimentação do GoblinResistente.
     */
    @Override
    public void animarMovimento()
    {
        this.indiceAtual++;

        if (obterViradoParaDireita()) {
            if (this.indiceAtual >= andandoDireita.length) {
                this.indiceAtual = 0;
            }
            setImage(andandoDireita[this.indiceAtual]);
        } else {
            if (this.indiceAtual >= andandoEsquerda.length) {
                this.indiceAtual = 0;
            }
            setImage(andandoEsquerda[this.indiceAtual]);
        }
    }
    /**
     * Lida com a animação de ataque.
     * @param atacar_direita O vetor de imagens do goblin atacando a direita
     * @param atacar_esquerda O vetor de imagens do goblin atacando a esquerda
     * Sobrescrito para evitar conflito de atributos(classe pai e filho).
     */
    @Override
    public void animarAtaque(GreenfootImage[] atacar_direita, GreenfootImage[] atacando_esquerda){
        this.indiceAtaqueResistente++;

        if (obterViradoParaDireita()) {
            if (this.indiceAtaqueResistente >= atacar_direita.length) {
                this.indiceAtaqueResistente = 0;
            }
            setImage(atacar_direita[this.indiceAtaqueResistente]);
        } else {
            if (this.indiceAtaqueResistente >= atacando_esquerda.length){
                this.indiceAtaqueResistente = 0;
            }
            setImage(atacando_esquerda[this.indiceAtaqueResistente]);
        }
    }
    /**
     * Inicializa os vetores de imagens.
     */
    public void inicializarVetores(){
        andandoDireita = new GreenfootImage[20];
        andandoDireita = gerarVetorPNG(andandoDireita, "goblinMandandoDireita",200,240);

        andandoEsquerda = new GreenfootImage[20];
        andandoEsquerda = gerarVetorPNG(andandoEsquerda, "goblinMAndandoEsquerda", 200,240);

        atacandoDireita = new GreenfootImage[10];
        atacandoDireita = gerarVetorPNG(atacandoDireita,"goblinMAtacandoDireita",200,240);

        atacandoEsquerda = new GreenfootImage[10];
        atacandoEsquerda = gerarVetorPNG(atacandoEsquerda,"goblinMAtacandoEsquerda",200,240);
    }
}