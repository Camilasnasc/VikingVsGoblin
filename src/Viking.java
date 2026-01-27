import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.GreenfootImage;

/**
 * Classe que representa o personagem principal controlado pelo jogador.
 * O Viking pode andar, pular, atacar inimigos e interagir com plataformas.
 * Também é afetado pela gravidade e possui animações de movimento e ataque.
 * 
 * @author Camila Santos Nascimento, Matheus Vitor da Silva e João Gabriel Tófoli.
 * @version 1.0
 */
public class Viking extends Personagem
{
    private GreenfootImage andando_direita[];
    private GreenfootImage andando_esquerda[];
    private GreenfootImage atacando_esquerda[];
    private GreenfootImage atacando_direita[];
    private GreenfootImage morrendo;
    private GreenfootImage parado;

    private final int GRAVIDADE;
    private final int FORCA_PULO; 
    private static int indiceAtual;
    private static int contadorParaAnimacao;
    private int velocidadeVertical;
    private boolean noChao;
    private boolean viradoParaDireita;
    private boolean algumaTeclaPrecionada;
    private boolean vikingEstaVivo;

    private boolean somAtaqueTocando = false;

    public Viking(){
        super(200,10,25,20);
        noChao = false;
        FORCA_PULO = -20;
        GRAVIDADE = 1;
        velocidadeVertical = 0;
        inicializarVetores();
        setImage(parado);
        algumaTeclaPrecionada = false;
        contadorParaAnimacao = 0;
        vikingEstaVivo = true;
    }

    /**
     * Act - do whatever the viking wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        algumaTeclaPrecionada = false;//se o jogador não esta precionando nenhuma tecla
        contadorParaAnimacao++;

        aplicarGravidade();
        verificarSoloOuPlataforma();

        if(vikingEstaVivo){
            if(contadorParaAnimacao == 2){
                processarMovimentoHorizontal();
                contadorParaAnimacao = 0;
            }
        }

        if(obterVida() <= 0){
            iniciarMorte();
        }
        verificarPortal();
    }

    /**
     * processa os comandos do teclado e chama os metodos correspondendes.
     * nota: Caso nenhuma tecla seja precionada, o viking permanece virado para frente e verifica qual foi a ultima direção que
     * ele esteve(direita ou esquerda). Caso a tecla "space" seja precionada, vai executar o ataque e causar dano ao alvo, reduzindo a vida do inimigo
     */
    public void processarMovimentoHorizontal()
    {
        if(Greenfoot.isKeyDown("right")){
            andar("right");
            viradoParaDireita = true;
            algumaTeclaPrecionada = true;
        }
        if(Greenfoot.isKeyDown("left")){
            andar("left");
            viradoParaDireita = false;
            algumaTeclaPrecionada = true;
        }

        //realiza a animação de ataque e causa dano ao goblin.
        if(Greenfoot.isKeyDown("space")){
            algumaTeclaPrecionada = true;

            // Mantém a animação e o dano rodando em cada frame enquanto a tecla estiver pressionada
            movimentoAtacar();

            Goblin goblin = (Goblin) getOneIntersectingObject(Goblin.class);
            if(goblin != null){
                int dano = atacar();
                goblin.tomarDano(dano);
            }

            if (!somAtaqueTocando) {
                Greenfoot.playSound("ataque.wav");
                somAtaqueTocando = true; 
            }
        } else {
            somAtaqueTocando = false;
        }

        // PULO - só pode pular se estiver no chão
        if(Greenfoot.isKeyDown("up") && noChao){
            pular();
            algumaTeclaPrecionada = true; 
            Greenfoot.playSound("pulo.wav");
        }
        //se nenhuma tecla precionada, ele só fica parado.
        if(!algumaTeclaPrecionada){
            setImage(parado);
        }
    }

    /**
     * Metodo que realiza a animação e movimentação "andar". Identidica se é para esquerda ou direita e executa a animação e movimento depenendendo
     * da direção escolhida.
     * @param direcao a tecla que vai indicar se o viking vai para esquerda(left) ou direita(right).
     */
    public void andar(String direcao){
        indiceAtual++;

        if(direcao.equals("right")){
            animarPersonagem(andando_direita);
            setLocation(getX()+4, getY());
        }
        if(direcao.equals("left")){
            animarPersonagem(andando_esquerda);
            setLocation(getX()-4, getY());
        }
    }

    /**
     * Metodo que lida se o viking esteve virado para direita ou esquerda na ultima movimentação.
     * Se esteve virado para direita, a animação de ataque vai ser para a direita também, caso contrário
     * a animação sera para esquerda.
     */
    public void movimentoAtacar(){
        if(viradoParaDireita){
            animarPersonagem(atacando_direita);
        }

        if(!viradoParaDireita){
            animarPersonagem(atacando_esquerda);
        }
    }

    /**
     * metodo que recebe um vetor do tipo GreenfootImage e uma String ação, retorna um vetor contendo as imagens da ação
     * @param imagens[] o vetor de imagens
     * @param acao a ação que o personagem ta fazendo
     * @return um vetor com as imagens ja adicionadas
     */
    public GreenfootImage[] gerarVetorPNG (GreenfootImage[] imagens, String acao){
        for(int i = 0; i<imagens.length;i++){
            imagens[i] = new GreenfootImage(acao + " ("+ (i+1) +").png");
            imagens[i].scale(180,220);
        } 
        return imagens;
    }

    /**
     * carrega os sprites nos respctivos vetores
     */
    public void inicializarVetores(){
        parado = new GreenfootImage("parado (1).png");
        parado.scale(180,220);

        andando_direita = new GreenfootImage[11];
        andando_direita = gerarVetorPNG(andando_direita, "andando-direita");

        andando_esquerda = new GreenfootImage[11];
        andando_esquerda = gerarVetorPNG(andando_esquerda, "andando-esquerda");

        atacando_esquerda = new GreenfootImage[9];
        atacando_esquerda = gerarVetorPNG(atacando_esquerda, "atacando-esquerda");

        atacando_direita = new GreenfootImage[9];
        atacando_direita = gerarVetorPNG(atacando_direita, "atacando-direita");

        morrendo = new GreenfootImage("morrendo (1).png");
        morrendo.scale(180,220);
    }

    /**
     * pegar o vetor de imagens e coloca ele sem sequencia.Faz a animação.
     */
    public void animarPersonagem(GreenfootImage vetor[]){
        indiceAtual++;
        if (indiceAtual >= vetor.length) {
            indiceAtual = 0;
        } 
        setImage(vetor[indiceAtual]);
    }

    /**
     * Exibe a imagem do viking morto;
     */
    public void iniciarMorte(){
        vikingEstaVivo = false;
        setImage(morrendo);
    }

    /**
     * Metodo para verificar se o viking esta vivo.
     * @return boolean, true se estiver vivo e false caso contrário.
     */
    public boolean estaVivo(){
        return obterVida() > 0;
    }

    /**
     * Aplica o efeito de gravidade no Viking.Quando ele não está em contato
     * com o solo ou com uma plataforma,sua posição vertical é aumentada
     * gradualmente para simular uma queda. Se o Viking estiver no chão,
     * a velocidade vertical é zerada.
     */
    public void aplicarGravidade() {
        velocidadeVertical += GRAVIDADE;
        setLocation(getX(), getY() + velocidadeVertical);
    }

    /**
     * Faz o Viking pular aplicando uma força vertical para cima.
     * O pulo só ocorre se ele estiver no chão, evitando pular no ar.
     */
    public void pular() {
        if (noChao) {
            velocidadeVertical = FORCA_PULO;// Valor negativo para pular para cima
            noChao = false;
        }
    }

    /**
     * Verifica se o Viking está tocando o solo ou uma plataforma.Caso esteja,
     * a queda é interrompida mantendo a posição estável.Caso contrário, a
     * gravidade é aplicada para que ele continue caindo.
     */
    public void verificarSoloOuPlataforma() {

        Plataforma p = (Plataforma) getOneObjectAtOffset(0,getImage().getHeight()/2,Plataforma.class);

        int metadeAlturaViking = getImage().getHeight() / 2;
        int offsetPes = 45; // ajuste fino do "pé" do sprite

        if (p != null && velocidadeVertical >= 0) {

            int topoPlataforma = p.getY() - p.getImage().getHeight() / 2;
            setLocation(getX(), topoPlataforma - (metadeAlturaViking - offsetPes));

            velocidadeVertical = 0;
            noChao = true;

        } else {
            noChao = false;
        }

        // CHÃO
        int chao = getWorld().getHeight() - (metadeAlturaViking - offsetPes);

        if (getY() > chao) {
            setLocation(getX(), chao);
            velocidadeVertical = 0;
            noChao = true;
        }
    }

    /**
     * Verifica se o personagem Viking está em contato com um objeto Portal.
     * Se houver contato, ele checa se a condição de avanço (não existirem Goblins vivos)
     * é atendida. Se ambas as condições forem verdadeiras, o jogo avança para a próxima fase (Fase2).
     * 
     * Nota: A verificação de proximidade (`Math.abs(getX() - portal.getX()) < 20`) é
     * um ajuste para estabilizar a transição, evitando bugs de teletransporte indesejados.
     */
    private void verificarPortal() {
        Actor portal = getOneIntersectingObject(Portal.class);
        if (portal != null) {
            Mundo mundo = (Mundo) getWorld();

            if (!mundo.existeGoblinVivo()) {
                if (Math.abs(getX() - portal.getX()) < 20) {
                    Greenfoot.playSound("portal.wav");

                    // muda a fase
                    if (mundo instanceof Fase1) {
                        Greenfoot.setWorld(new Fase2());
                    } else if (mundo instanceof Fase2) {
                        Greenfoot.setWorld(new Fase3());
                    } else if (mundo instanceof Fase3) {
                        Greenfoot.setWorld(new TelaFinal());
                    }
                }
            }
        }
    }
}