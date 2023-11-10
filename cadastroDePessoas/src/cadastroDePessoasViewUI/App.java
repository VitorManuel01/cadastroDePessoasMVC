package cadastroDePessoasViewUI;

import javax.swing.JOptionPane;

public class App {

	public static void main(String[] args) {
		PessoaViewUI viewUI = new PessoaViewUI();
		
		do {
			String[] opcoes = { "Inserir Pessoa", "Listar Pessoas", "Apagar Pessoa","Atualizar Pessoa", "Finalizar" };
			int escolha = JOptionPane.showOptionDialog(null, "O que deseja fazer?", "Menu", JOptionPane.DEFAULT_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
			if (escolha == 0) {
				viewUI.insercaoDadosPessoa();
			} else if (escolha == 1) {
				viewUI.exibirTodasPessoas();
			} else if (escolha == 2) {
				viewUI.deletarPessoa();
			} else if (escolha == 3) {
				viewUI.atualizarPessoa();
			} else if (escolha == 4) {
				break;
			} 
		}while (true);
	}

}
