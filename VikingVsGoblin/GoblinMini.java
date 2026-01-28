import greenfoot.GreenfootImage;

/**
 * Classe GoblinMini (Inimigo).
 * Representa um tipo específico de Goblin menor.
 * * @author (seu nome) 
 * @version (a version number or a date)
 */
public class GoblinMini extends Goblin
{
    private GreenfootImage parada_esquerda[];
    private GreenfootImage ataca_esquerda[];
    private GreenfootImage morreGoblinMini[];

    // Variáveis de controle de animação
    private int indiceAtual;
    private int indiceParaAtaque;
    private int contadorAnimacao;
    private int passosParaAtualizarImagem;
    private int passosDesdeUltimaAtualizacaoImagem;

    /**
     * Construtor da classe GoblinMini.
     * Inicializa os atributos e vetores de imagem.
     */
    public GoblinMini(){
        super(50,1,15,30); 
        inicializarVetores();

        contadorAnimacao = 0;
        passosDesdeUltimaAtualizacaoImagem = 0;
        passosParaAtualizarImagem = 3;
        indiceAtual = 0;
        indiceParaAtaque = 0;
    }

    /**
     * Act - Lógica principal executada a cada frame.
     */
    public void act()
    {
        recarga();
        atualizarImagemParado();
        contadorAnimacao++;
        
        if(vikingEstaPerto()){
            atacarAutomatico();
        }
        
        if(obterVida()<=0){
            Mundo mundo = (Mundo) getWorld(); 
            mundo.incrementarPontos();
            morrer();
        }
    }

    /**
     * Controla a animação de "Idle" (Parado/Andando).
     */
    public void atualizarImagemParado(){
        passosDesdeUltimaAtualizacaoImagem++;

        if (passosDesdeUltimaAtualizacaoImagem >= passosParaAtualizarImagem) {
            passosDesdeUltimaAtualizacaoImagem = 0;
            indiceAtual++;

            if (indiceAtual >= parada_esquerda.length) {
                indiceAtual = 0;
            }

            setImage(parada_esquerda[indiceAtual]);
        }
    }

    /**
     * Ataque automático.
     * Passa 'null' para a direita e o vetor existente para a esquerda.
     */
    public void atacarAutomatico() {
        super.atacarAutomatico();
    }

    /**
     * Animação do ataque.
     * Passa 'null' para a direita e o vetor existente para a esquerda.
     */
    public void animarAtaque(GreenfootImage[] atacarDireita, GreenfootImage[] atacarEsquerda){
        super.animarAtaque(null, ataca_esquerda);
    }

    /**
     * Carrega as imagens.
     * As inicializações dos vetores da DIREITA foram removidas.
     */
    public void inicializarVetores(){
        parada_esquerda = new GreenfootImage[16];
        parada_esquerda = gerarVetorPNG(parada_esquerda, "parada-esquerda", 100, 120);

        ataca_esquerda = new GreenfootImage[10];
        ataca_esquerda = gerarVetorPNG(ataca_esquerda, "ataca-esquerda", 100, 120);

        morreGoblinMini = new GreenfootImage[10];
        morreGoblinMini = gerarVetorPNG(morreGoblinMini, "morre-goblinMini", 100, 120);
    }

}