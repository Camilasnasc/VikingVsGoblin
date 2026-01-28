import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
import java.util.List;

/**
 * Classe que representa o inimigo Goblin.
 * Ele se move automaticamente, patrulha as plataformas e persegue o Viking
 * quando está dentro de um certo alcance. Pode atacar o jogador e morrer
 * quando sua vida chega a zero. Também é afetado pela gravidade e colisões com o chão.
 * 
 * @author Camila Santos Nascimento, Matheus Vitor da Silva e João Gabriel Tófoli.
 * @version 1.0
 */
public class Goblin extends Personagem
{
    GreenfootImage andando_esquerda[];
    GreenfootImage andando_direita[];
    GreenfootImage atacando_esquerda[];
    GreenfootImage atacando_direita[];
    GreenfootImage morrendo[];

    // VARIÁVEIS DE GRAVIDADE E MOVIMENTO
    private int velocidadeVertical = 0;
    private final int GRAVIDADE = 1;
    private boolean noChao = false;
    private int velocidadeHorizontal = 1; // Velocidade de andar
    private boolean viradoParaDireita = false;
    private int indiceAtual;
    private Viking vikingAlvo;
    private int cotadorParaAnimacao;
    private int indiceParaAtaque;

    /**
     * Construtor da classe Goblin.
     * Inicializa os atributos do goblin.
     */
    public Goblin(){
        super(100,3,10,25);
        inicializarVetores();
        setImage(andando_esquerda[0]); // Imagem inicial
        cotadorParaAnimacao = 0;
        indiceParaAtaque = 0;
    }

    /**
     * Contrutor da classe Goblin.
     * Criada para ser uma ponte para a superClasse Personagem.
     * @param vida A vida do goblin
     * @param danoMin O dano minimo que o goblin pode causar
     * @param danoMax O dano maximo que o goblin pode causar
     * @param tempoDeRecarga O número de ciclos que deve esperar entre ataques
     */
    public Goblin(int vida, int danoMin, int danoMax, int tempoDeRecarga){
        super(vida, danoMin, danoMax, tempoDeRecarga);
        cotadorParaAnimacao = 0;
        indiceParaAtaque = 0;
    }

    /**
     * Act - do whatever the Goblin wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        aplicarGravidade();
        verificarColisaoPlataforma();
        cotadorParaAnimacao++;
        //if para lidar somenete com a animação
        if(cotadorParaAnimacao == 3){
            animarMovimento();
            moverAutomaticamente();
            atacarAutomatico();
            cotadorParaAnimacao = 0;
        }   
        if(obterVida() <= 0){
            //faz cast(conversão explicita de tipo) --> ((Mundo) getWorld()).
            //getWorl() retorna um *world*, com cast vai /retornar um *TratarMundos*
            Mundo mundo = (Mundo) getWorld(); 
            mundo.incrementarPontos();
            morrer();
        }
    }

    /**
     * Controla o movimento automático do Goblin.Se o Viking estiver
     * perto, ele persegue, caso contrário,anda sozinho e muda de
     * direção nas bordas ou sem plataforma.
     */
    public void moverAutomaticamente()
    {  
        if(vikingEstaPerto()){
            if (vikingAlvo == null) { 
                World mundo = getWorld(); 

                // pega o primeiro Viking encontrado no mundo que o goblin estiver
                List<Viking> vikings = mundo.getObjects(Viking.class);
                if (!vikings.isEmpty()) {
                    vikingAlvo = vikings.get(0);
                }
            }

            if(vikingAlvo != null && vikingAlvo.getWorld() != null){
                if(vikingAlvo.getX() > getX()) {
                    setLocation(getX() + 2, getY());
                    viradoParaDireita = true;
                } else {
                    setLocation(getX() - 2, getY());
                    viradoParaDireita = false;
                }
            }

        }else{
            if (noChao) {
                if (viradoParaDireita) {
                    // Move para direita
                    setLocation(getX() + velocidadeHorizontal, getY());
                    // Verifica se chegou na borda ou precisa virar
                    if (getX() >= getWorld().getWidth() - 50 || precisaVirar()) {
                        viradoParaDireita = false;
                    }
                } else {
                    // Move para esquerda
                    setLocation(getX() - velocidadeHorizontal, getY());
                    // Verifica se chegou na borda ou precisa virar
                    if (getX() <= 50 || precisaVirar()) {
                        viradoParaDireita = true;
                    }
                }
            }
        }
    }

    /**
     * Verifica se o Goblin precisa mudar de direção.
     * @return true se não houver plataforma à frente ou se estiver na borda.
     */
    public boolean precisaVirar()
    {
        // Verifica se há plataforma à frente
        int direcao = viradoParaDireita ? 1 : -1;
        Actor plataformaAhead = getOneObjectAtOffset(direcao * (getImage().getWidth()/2 + 10), 
                getImage().getHeight()/2 + 5, Plataforma.class);

        // Se não há plataforma à frente, precisa virar
        return plataformaAhead == null;
    }

    /**
     * Atualiza o sprite do Goblin para criar a animação de caminhada.
     */
    public void animarMovimento()
    {
        indiceAtual++;

        if (viradoParaDireita) {
            if (indiceAtual >= andando_direita.length) {
                indiceAtual = 0;
            }
            setImage(andando_direita[indiceAtual]);
        } else {
            if (indiceAtual >= andando_esquerda.length) {
                indiceAtual = 0;
            }
            setImage(andando_esquerda[indiceAtual]);
        }
    }

    /**
     * metodo que recebe um vetor do tipo GreenfootImage e uma String ação, retorna um vetor contendo as imagens da ação
     * @param imagens[] o vetor de imagens
     * @param acao a ação que o personagem ta fazendo
     * @param x A largura da imagem
     * @param y A altura da imagem
     * @return um vetor com as imagens ja adicionadas
     */
    public GreenfootImage[] gerarVetorPNG (GreenfootImage[] imagens, String acao, int x, int y){
        for(int i = 0; i<imagens.length;i++){
            imagens[i] = new GreenfootImage(acao + " ("+ (i+1) +").png");
            imagens[i].scale(x,y);
        } 
        return imagens;
    }

    /**
     * Carrega e armazena todas as animações do Goblin nos vetores de imagens.
     */
    public void inicializarVetores(){
        andando_direita = new GreenfootImage[19];
        andando_direita = gerarVetorPNG(andando_direita, "andar-direita-goblin",180,220);

        andando_esquerda = new GreenfootImage[19];
        andando_esquerda = gerarVetorPNG(andando_esquerda, "andar-esquerda-goblin", 180,220); // Corrigido o nome do arquivo

        atacando_direita = new GreenfootImage[9];
        atacando_direita = gerarVetorPNG(atacando_direita,"atacar-direita-goblin",180,220);

        atacando_esquerda = new GreenfootImage[9];
        atacando_esquerda = gerarVetorPNG(atacando_esquerda,"atacar-esquerda-goblin",180,220);

        morrendo = new GreenfootImage[9];
        morrendo = gerarVetorPNG(morrendo, "morrendo-goblin",180,220);
    }

    /**
     * Executa o ataque automático do Goblin se o Viking estiver próximo.
     * Reproduz a animação de ataque e causa dano ao jogador.
     */
    public void atacarAutomatico() {
        //aqui q diminui o range do ataque
        int range = 15;

        Viking viking = (Viking) getOneObjectAtOffset(viradoParaDireita ? range : -range,0,Viking.class);

        // Se não está perto, não ataca
        if (viking == null){
            return;
        }

        animarAtaque(atacando_direita,atacando_esquerda);
        // Causa dano apenas se puder atacar (cooldown)
        if (podeAtacar()) {
            int dano = atacar();
            viking.tomarDano(dano);
        }
    }

    /**
     * Executa o ataque automático sincronizado com a animação.
     */
    public void atacarAutomatico(GreenfootImage[] atacarDireita, GreenfootImage[] atacarEsquerda) {
        int range = 50; 
    
        Viking viking = (Viking) getOneObjectAtOffset(
                viradoParaDireita ? range : -range, 0, Viking.class);
    
        // Se não está perto, não ataca
        if (viking == null){
            return;
        }
    
        // 1 Executa a animação
        animarAtaque(atacarDireita, atacarEsquerda);
        
        // 2 Verifica se a animação chegou no frame de impacto (ex: imagem 5)
        // Isso garante que ele só tente dar dano quando a "espada" encostar no jogador
        if (this.indiceParaAtaque == 5) {
            // 3 Verifica se o tempo de recarga permite o ataque
            if (podeAtacar()) {
                int dano = atacar(); // Reseta o cooldown aqui
                viking.tomarDano(dano);
            }
        }
    }

    /**
     * Aplica o efeito da gravidade no Goblin.
     * Aumenta a velocidade vertical e move o personagem para baixo até tocar o chão.
     */
    public void aplicarGravidade()
    {
        // Aplica gravidade (aumenta velocidade vertical)
        velocidadeVertical += GRAVIDADE;

        // Move verticalmente
        setLocation(getX(), getY() + velocidadeVertical);

        // Verifica se chegou no "chão" (fundo da tela)
        if (getY() >= getWorld().getHeight() - 50) {
            setLocation(getX(), getWorld().getHeight() - 50);
            velocidadeVertical = 0;
            noChao = true;
        }
    } 

    /**
     * Verifica se o Goblin está sobre uma plataforma ou solo.Caso esteja, sua
     * posição vertical é estabilizada.Caso não esteja, aplica gravidade
     * para simular queda.Isso evita que o Goblin fique suspenso no ar.
     */
    public void verificarColisaoPlataforma()
    {
        noChao = false;

        // Verifica colisão com plataformas usando método mais direto
        List<Plataforma> plataformas = getNeighbours(50, true, Plataforma.class);

        for (Plataforma plataforma : plataformas) {
            // Verifica se está acima da plataforma e caindo
            if (getY() < plataforma.getY() && velocidadeVertical > 0) {
                // Calcula distância vertical
                int distanciaY = (plataforma.getY() - plataforma.getImage().getHeight()/2) - 
                    (getY() + getImage().getHeight()/2);

                // Se está bem em cima (dentro de 10 pixels)
                if (distanciaY >= -10 && distanciaY <= 10) {
                    // Alinha horizontalmente com a plataforma
                    boolean alinhadoX = Math.abs(getX() - plataforma.getX()) < plataforma.getImage().getWidth()/2;

                    if (alinhadoX) {
                        // Posiciona em cima da plataforma
                        setLocation(getX(), plataforma.getY() - plataforma.getImage().getHeight()/2 - getImage().getHeight()/2);
                        velocidadeVertical = 0;
                        noChao = true;
                        return;
                    }
                }
            }
        }

        // Verifica chão do mundo
        if (getY() >= getWorld().getHeight() - getImage().getHeight()/2) {
            setLocation(getX(), getWorld().getHeight() - getImage().getHeight()/2);
            velocidadeVertical = 0;
            noChao = true;
        }
    } 

    /**
     * Verifica se o Viking (jogador) está a uma distância próxima do Goblin,
     * ativando o comportamento de perseguição/alvo.
     * * @return true se o Viking estiver dentro do range de proximidade, false caso contrário.
     */
    public boolean vikingEstaPerto() {
        World mundo = getWorld();
        if (mundo == null) {
            return false; 
        }

        java.util.List<Viking> vikings = mundo.getObjects(Viking.class);
        if (vikings.isEmpty()){
            return false;  
        }

        // Usa o primeiro Viking encontrado
        Viking v = vikings.get(0);
        //Math.abs transforma num negativos em positivos
        int distancia = Math.abs(v.getX() - getX());

        return distancia <= 120; // O raio de proximidade (range)
    }
    /**
     * Retorna o boolean viradoParaDireita
     * @return viradoParaDireta 
     */
    public boolean estaViradoParaDireita() {
        return viradoParaDireita;
    }   
    /**
     * valida o boolean viradoParaDireita
     */
    public void validarViradoParaDireita(){
        viradoParaDireita = true;
    }
    /**
     * Falsifica o boolean viradoParaDireita
     */
    public void falsificarViradoParaDireita(){
        viradoParaDireita = false;
    }

    /**
     * Lida com a animação de ataque.
     * @param atacar_direita O vetor de imagens do goblin atacando a direita
     * @param atacar_esquerda O vetor de imagens do goblin atacando a esquerda
     */
    public void animarAtaque(GreenfootImage[] atacar_direita, GreenfootImage[] atacando_esquerda){
        indiceParaAtaque++;
        if (viradoParaDireita) {
            if (indiceParaAtaque >= atacar_direita.length) {
                indiceParaAtaque = 0;
            }
            setImage(atacar_direita[indiceParaAtaque]);
        } else {
            if (indiceParaAtaque >= atacando_esquerda.length){
                indiceParaAtaque = 0;
            }
            setImage(atacando_esquerda[indiceParaAtaque]);
        }
    }
    
    public boolean obterViradoParaDireita(){
        return viradoParaDireita;
    }
}
