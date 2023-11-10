package cadastroDePessoasViewUI;

import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import cadastroDePessoasControlador.PessoaControlador;
import cadastroDePessoasRespositorio.Pessoa;

public class PessoaViewUI {
	private PessoaControlador controlador = new PessoaControlador();
	
	
	//O método insercaoDadosPessoa() irá passar todas as informações necessárias para o método cadastrarPessoa() do controlador
    public void insercaoDadosPessoa() {
    	String nomePessoa = JOptionPane.showInputDialog(null, "Digite o nome: ", "Nome", JOptionPane.INFORMATION_MESSAGE);
        String sobrenomePessoa =JOptionPane.showInputDialog(null, "Digite o sobrenome: ", "Sobrenome", JOptionPane.INFORMATION_MESSAGE);
        String idadePessoa = JOptionPane.showInputDialog(null, "Digite a idade: ", "Idade", JOptionPane.INFORMATION_MESSAGE);    
        String emailPessoa = JOptionPane.showInputDialog(null, "Digite o email: ", "Email", JOptionPane.INFORMATION_MESSAGE);
        
        PessoaControlador PC = new PessoaControlador();
        PC.cadastrarPessoa(nomePessoa, sobrenomePessoa, Integer.parseInt(idadePessoa), emailPessoa);
    }
  
    public void exibirTodasPessoas() {
    	
    	List<Pessoa> pessoas = controlador.buscarPessoas(); 	
    	Iterator<Pessoa> iterator = pessoas.iterator();
    	String impressao = "";    	
    	while(iterator.hasNext()) {
    		Pessoa pessoa = iterator.next();
    		impressao += "ID: " + pessoa.getId() + 
    				"\nNome: " + pessoa.getNome() + 
    				"\nSobrenome: " + pessoa.getSobrenome() + 
    				"\nIdade: " + pessoa.getIdade() + 
    				"\nEmail: " + pessoa.getEmail() + 
    				"\n=========================\n";							
    	}
    	JOptionPane.showMessageDialog(null, impressao);
    	pessoas.clear();
    } //O método exibir, vai ficar responsável pela impressão na tela para o usuário dessa lista, através de um iterator(pois não se sabe o tamanho da lista)
    
    public void deletarPessoa() { //o método deletar pessoas vai chamar o método buscar por id para validar para usuário se a pessoa que ele quer apagar é esta mesma
    	String idPessoa = JOptionPane.showInputDialog(null, "Digite o Id: ", "ID", JOptionPane.INFORMATION_MESSAGE);
    	Pessoa pessoa = controlador.buscaPorID(Integer.parseInt(idPessoa));
    	
    	if (pessoa.getId() != 0) {
			String impressao = "ID: " + pessoa.getId() + "\nNome: " + pessoa.getNome() + "\nSobrenome: "
					+ pessoa.getSobrenome();
			String[] opcoes = { "Sim", "Não" };
			int escolha = JOptionPane.showOptionDialog(null, impressao, "Tem certeza que deseja deletar esta pessoa?",
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
			if (escolha == 0) {
				controlador.deletarPorID(Integer.parseInt(idPessoa));
			} else if (escolha == 1) {
				JOptionPane.showMessageDialog(null, "OK! A Pessoa não será exluída!");
			} 
		}else {
			JOptionPane.showMessageDialog(null, "Pessoa não encontrada!");
		}//então se o id digitado existir, ele vai mandar essa informação para o controlador
    }
    
    public void atualizarPessoa() { //Baseado na escolha do usuário de qual atributo ele quer mudar, as informações vão ser passadas de acordo para o controlador
    	String idPessoa = JOptionPane.showInputDialog(null, "Digite o Id: ", "ID", JOptionPane.INFORMATION_MESSAGE);
    	Pessoa pessoa = controlador.buscaPorID(Integer.parseInt(idPessoa));
    	
    	if (pessoa.getId() != 0) {
			String impressao = "ID: " + pessoa.getId() + "\nNome: " + pessoa.getNome() + "\nSobrenome: "
					+ pessoa.getSobrenome();
			String[] opcoes = { "Sim", "Não" };
			int escolha = JOptionPane.showOptionDialog(null, impressao, "Tem certeza que deseja deletar esta pessoa?",
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
			if (escolha == 0) {
				if (escolha == 0) {
					String atributos[] = { "Nome", "Sobrenome", "Idade", "Email" };
					int escolhaAtributos = JOptionPane.showOptionDialog(null, impressao,
							"Qual atributo deseja atualizar? ", JOptionPane.DEFAULT_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, atributos, atributos[0]);

					if (escolhaAtributos == 0) {
						String atr = "nome";

						String novoNome = JOptionPane.showInputDialog(null, "Digite o novo nome para atualizar: ",
								"Atualização", JOptionPane.INFORMATION_MESSAGE);

						controlador.atualizacaoPessoa(Integer.parseInt(idPessoa), atr, novoNome);

						JOptionPane.showMessageDialog(null, "Nome atualizado com sucesso!");

					} else if (escolhaAtributos == 1) {
						String atr = "sobrenome";

						String novoSobrenome = JOptionPane.showInputDialog(null,
								"Digite o novo sobrenome para atualizar: ", "Atualização",
								JOptionPane.INFORMATION_MESSAGE);

						controlador.atualizacaoPessoa(Integer.parseInt(idPessoa), atr, novoSobrenome);

						JOptionPane.showMessageDialog(null, "Sobrenome atualizado com sucesso!");

					} else if (escolhaAtributos == 2) {
						String atr = "idade";

						String novaIdade = JOptionPane.showInputDialog(null, "Digite a nova idade para atualizar: ",
								"Atualização", JOptionPane.INFORMATION_MESSAGE);

						controlador.atualizacaoPessoa(Integer.parseInt(idPessoa), atr, novaIdade);

						JOptionPane.showMessageDialog(null, "Idade atualizada com sucesso!");

					} else if (escolhaAtributos == 3) {
						String atr = "email";

						String novoEmail = JOptionPane.showInputDialog(null, "Digite o novo email para atualizar: ",
								"Atualização", JOptionPane.INFORMATION_MESSAGE);

						controlador.atualizacaoPessoa(Integer.parseInt(idPessoa), atr, novoEmail);

						JOptionPane.showMessageDialog(null, "Email atualizado com sucesso!");

					}
				}
			} else if (escolha == 1) {
				JOptionPane.showMessageDialog(null, "OK! A Pessoa não será exluída!");
			} 
		}else {
			JOptionPane.showMessageDialog(null, "Pessoa não encontrada!");
		}

    }
}
