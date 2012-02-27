package visao;

import controle.ControleTetris;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class PainelEsquerdo extends JPanel
{
	ControleTetris controle;
	public PainelEsquerdo(ControleTetris controle)
	{
		super();
		this.controle = controle;		
		setSize(300, 150);
		setVisible(true);
	}

	@Override
	public void paint(Graphics g)
	{
		super.paint(g);

		int[][] grade = new int[20][10];
		grade = controle.cenario.getCenario();

		for(int i=0; i<grade.length; i++) {
			for(int j=0; j<grade[0].length; j++)
			{
				//@todo ROTEIRO: Pinte todas as pe�as com um verde brilhante para que o jogo se pare�a com os terminais antigos de v�deo-game
				if(grade[i][j]==0)
					g.setColor(Color.black);
				else if(grade[i][j]==1||grade[i][j]==5)
					g.setColor(Color.red);
				else if(grade[i][j]==2||grade[i][j]==6)
					g.setColor(Color.blue);
				else if(grade[i][j]==3||grade[i][j]==7)
					g.setColor(Color.green);
				else if(grade[i][j]==4||grade[i][j]==8)
					g.setColor(Color.yellow);

				//@todo DESAFIO: Encontre uma ou v�rias imagens interessantes e ao inv�s de quadrados coloridos, exiba imagens.
				/* para exibir images utilize as linhas abaixo
					try{ //o bloco try garante que, se a imagem n�o existir, um erro ser� lan�ado
						URL url = this.getClass().getResource("/resources/img/nomeimg.jpg"); //Carrega caminho da imagem
						Image image = ImageIO.read(url); //Carrega imagem
						graphic.drawImage(image, x, y, null); //Desenha imagem
					catch(IOException e){
						e.printStackTrace();
					}
				 */
				g.fillRect(15*j+1, 15*i+1, 15, 15);
			}
		}
	}
}