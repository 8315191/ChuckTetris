package controle;

import modelo.Jogador;
import modelo.Cenario;
import visao.JanelaPrincipal;
import javax.swing.*;

public class ControleTetris
{
	private JanelaPrincipal janela;

	private SecondsController secondsController;

	
	private Jogador jogador;
	public Cenario cenario;
	private int nivel;
	
	private boolean temProximaPeca;
	private boolean caiPeca = false;	
	private int velocidade;	
	private boolean gameOver;


	public ControleTetris(){				
		cenario = new Cenario();

		janela = new JanelaPrincipal(this);
		secondsController = new SecondsController(janela);
		
		temProximaPeca = true;
		gameOver = true;

		while(true){
			try{ Thread.sleep((long)velocidade); } catch(Exception e){}
			gameLoop();			
		}
		
	}
	
	public void novoJogo() {
		jogador = new Jogador(JOptionPane.showInputDialog(null, "Qual sua gra�a?", "Chuck Tetris", JOptionPane.PLAIN_MESSAGE));
		cenario = new Cenario();
		temProximaPeca = true;
		this.secondsController.setTempo(0);
		janela.mostraNome(jogador.getNome());
		cenario.setPecaAtual();
		this.setNivel(1);
		gameOver = false;
	}
	
	public void setNivel(int n) {
		nivel = n;
		velocidade = 1000 / (nivel*3);
	}	
		
	
	public void caiPeca() {
		cenario.caiPeca();
		caiPeca = true;
	}
		
	private void gameLoop() {
		if(gameOver) { return; }		

		/*
		 * @todo Adicionar controle pelo teclado aqui.
		 * Para mover uma pe�a para esquerda basta chamar
		 * cenario.paraEsquerda()
		 * ou
		 * cenario.paraDireita()
		 * ou
		 * cenario.caiPeca()
		 *
		 * a �nica dificuldade � saber a tecla que o usu�rio digitou
		 */

		//@todo No futuro quero adicionar controle pelo Joystick utilizando a lib JInput


		//Se tem pe�a pra descer, calcula
		if(cenario.descePeca() || caiPeca){
			caiPeca = false;
			this.computaPontos(cenario.verificaLinhaCompleta());
			temProximaPeca = true;
			cenario.setPecaAtual();
		}

		if(cenario.perdeu()){
			gameOver = true;
			Records r = new Records();
			r.salvarJogador(jogador);
			JOptionPane.showMessageDialog(null,"Game Over");
		}

		if(temProximaPeca)	{
			this.adicionaPecaAoCenario();
		}

		janela.repintar();
		janela.mostraScore(jogador.getPontuacao());
		
	}

	private void adicionaPecaAoCenario(){
		cenario.setPermissaoDesce(true);
		cenario.adicionaPeca(cenario.getPecaAtual());
		temProximaPeca = false;
	}
	
	public void computaPontos(int quant) {
		jogador.setPontuacao(jogador.getPontuacao()+quant);
	}

	// M�todos de Acesso

	public int getNivel(){return nivel;}	
	public Jogador getJogador() {return jogador;}
}