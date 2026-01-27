import greenfoot.*;

/**
 * Classe base para os botões da interface do jogo.
 * Responsável por gerar a aparência visual (forma arredondada, cores e texto)
 * e servir de molde para botões específicos através de herança.
 * @author Camila Santos Nascimento, Matheus Vitor da Silva e João Gabriel Tófoli.
 * @version 1.0
 */
public class Botao extends Actor
{
    // Variável para armazenar o texto que aparecerá no botão
    private String texto;

    /**
     * Construtor da classe Botao.
     * Recebe o texto desejado e chama o método auxiliar para gerar e definir
     * a imagem do ator.
     * @param texto A String que será escrita no centro do botão.
     */
    public Botao(String texto)
    {
        setImage(criarImagemBotao(texto)); 
    }

    /**
     * Método auxiliar que constrói a parte visual do botão.
     * Cria uma imagem, desenha o fundo arredondado, a borda e centraliza o texto.
     * @param texto O rótulo do botão.
     * @return GreenfootImage A imagem finalizada pronta para uso.
     */
    private GreenfootImage criarImagemBotao(String texto) {
        int largura = 200;
        int altura = 60;

        // Cria uma nova imagem vazia com as dimensões especificadas
        GreenfootImage img = new GreenfootImage(largura, altura);

        // Definindo uma cor personalizada (Verde)
        Color corFundo = new Color(46, 204, 113); 

        // Define a cor e preenche o formato base do botão
        img.setColor(corFundo);
        // fillOval desenha um círculo se for quadrado, ou uma cápsula se for retângulo
        img.fillOval(0, 0, largura - 1, altura - 1);

        // Configura a cor para desenhar a Borda do Botão
        img.setColor(Color.WHITE);
        // drawOval desenha apenas o contorno sobre o preenchimento
        img.drawOval(0, 0, largura - 1, altura - 1); 

        // Define a fonte, estilo (negrito) e tamanho do texto
        Font fonte = new Font("Verdana", true, false, 24); 

        // Criamos uma imagem temporária só com o texto para calcular suas dimensões reais
        GreenfootImage imgTexto = new GreenfootImage(texto, 24, Color.WHITE, new Color(0, 0, 0, 0));
        imgTexto.setFont(fonte);

        // Cálculo matemático das posições X e Y para centralizar o texto na imagem
        int x = (largura - imgTexto.getWidth()) / 2;
        int y = (altura - imgTexto.getHeight()) / 2;

        // Desenhamos a imagem do texto sobre a imagem do botão na posição calculada
        img.drawImage(imgTexto, x, y);

        return img;
    }

    /**
     * Act - Lógica de execução do botão.
     * Detecta o clique do mouse, mas não executa ação.
     * Este método foi deixado intencionalmente vazio para ser sobrescrito (@Override)
     * pelas subclasses (ex: BotaoIniciar, BotaoSair) com suas ações específicas.
     */
    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            // Vazio para que possa ser sobrescrito
        }
    }

}