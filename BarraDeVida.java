import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A classe BarraDeVida é uma representação para o jogador saber a quantidade vida que seu viking possui.
 * A classe lida com a atualização da vida e mudança dos sprites da BarraDeVida.
 * 
 * @author Camila Santos Nascimento, Matheus Vitor da Silva e João Gabriel Tófoli.
 * @version 1.0
 */
public class BarraDeVida extends Actor
{
    private GreenfootImage barraDeVidaDoViking[];
    private int vidaAtual;
    private Viking viking;

    public BarraDeVida(Viking viking){
        barraDeVidaDoViking = new GreenfootImage[6];
        ajustarVetorEImagem();
        this.viking = viking;
    }

    /**
     * O método 'act' é chamado a cada ciclo de execução (frame).
     * Sua função é atualizar nessa classe é fazer a mudança de sprite da barra de vida de acordo com
     * a quantidade de vida atual do viking.
     */
    public void act()
    {
        ajustarVidaDoViking();
        if(vidaAtual == 200){
            setImage(barraDeVidaDoViking[5]);
        }
        if(vidaAtual <= 160 && vidaAtual > 120){
            setImage(barraDeVidaDoViking[4]);
        }
        if(vidaAtual <= 120 && vidaAtual > 80){
            setImage(barraDeVidaDoViking[3]);
        }
        if(vidaAtual <= 80 && vidaAtual > 40){
            setImage(barraDeVidaDoViking[2]);
        }
        if(vidaAtual <= 40 && vidaAtual > 0){
            setImage(barraDeVidaDoViking[1]);
        }
        if(vidaAtual <= 0){
            setImage(barraDeVidaDoViking[0]);
        }
    }
    
    /**
     * Insere todos os png da barra de vida no vetor, e ajusta o tamanho dos mesmos.
     */
    public void ajustarVetorEImagem(){
        for(int i = 0; i<barraDeVidaDoViking.length;i++){
            barraDeVidaDoViking[i] = new GreenfootImage("vida" + " ("+ (i+1) +").png");
            barraDeVidaDoViking[i].scale(520,100);
        } 
    }
    
    /**
     * Obtem do viking sua vida atual para alterar os sprites da barra de vida.
     */
    public void ajustarVidaDoViking(){
        vidaAtual = viking.obterVida();
    }
}