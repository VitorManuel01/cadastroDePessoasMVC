package cadastroDePessoasControlador;

import java.util.List;

import cadastroDePessoasRespositorio.Pessoa;
import cadastroDePessoasRespositorio.PessoasRepositorio;

public class PessoaControlador {
	private PessoasRepositorio repositorio = new PessoasRepositorio();
	
	
	//O controlador vai pegar essas informações e irá transformar em atributos da classe pessoa que por sua vez irá ser enviado para o repositório como objeto pessoa
	public void cadastrarPessoa(String nome, String sobrenome, int idade, String email) {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(nome);
		pessoa.setSobrenome(sobrenome);
		pessoa.setIdade(idade);
		pessoa.setEmail(email);
		repositorio.inserirPessoa(pessoa);
		
	}
	
	public List<Pessoa> buscarPessoas() {
		List<Pessoa> pessoas = repositorio.listarPessoas();
		return pessoas;
	} //o controlador vai novamente fazer o retorno dessa lista de pessoas repositório
	
	public Pessoa buscaPorID(int id) {
		Pessoa pessoa = repositorio.buscarPessoaPorID(id);
		return pessoa;
	} //o controlador passa retorna objeto pessoa do repositório
	
	public void deletarPorID(int id) {
		repositorio.removerPessoa(id); //o controlador passa id para repositório para ser feita a exclusão no banco de dados
	}
	public void atualizacaoPessoa(int id, String atributo, String novoAtributo) {
		repositorio.atualizarPessoa(id, atributo, novoAtributo);
	}// o controlador passa as informações que vão ser pegas na classe view para o repositório
}
