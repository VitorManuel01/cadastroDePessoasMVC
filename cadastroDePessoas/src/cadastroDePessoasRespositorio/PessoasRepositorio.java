package cadastroDePessoasRespositorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoasRepositorio {
	private Connection conexao;
	private String url = "URL_VAI_AQUI";
	private String usuarioBD = "USUARIO_VAI_AQUI";
	private String senhaBD = "SENHA_VAI_AQUI";
	
	//Vai ser criado a base de dados Pessoas se não existir
    public PessoasRepositorio() {
        try (Connection conexao = DriverManager.getConnection(url, usuarioBD, senhaBD);
             PreparedStatement ps = conexao.prepareStatement("CREATE DATABASE IF NOT EXISTS Pessoas;");
             PreparedStatement ps2 = conexao.prepareStatement("USE Pessoas;");
             PreparedStatement ps3 = conexao.prepareStatement("CREATE TABLE IF NOT EXISTS pessoa (id INT AUTO_INCREMENT PRIMARY KEY, nome VARCHAR(255), sobrenome VARCHAR(255), idade INT, email VARCHAR(255));")) 
        {

            Class.forName("com.mysql.cj.jdbc.Driver");

            ps.executeUpdate();
            ps2.executeUpdate();
            ps3.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao executar SQL: " + e.getMessage(), e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver JDBC não encontrado: " + e.getMessage(), e);
        }
    }	
    
    //No repositório, as informações que o controlador passou para o objeto pessoa irá ser enviada para o banco de dados
	public void inserirPessoa(Pessoa pessoa){
		String sqlInsert = "INSERT INTO Pessoas.pessoa(nome, sobrenome, idade, email) VALUES(?,?,?,?);";
        try (Connection conexao = DriverManager.getConnection(url, usuarioBD, senhaBD);
        	 PreparedStatement ps = conexao.prepareStatement(sqlInsert)){
        	
			Class.forName("com.mysql.cj.jdbc.Driver");			
			ps.setString(1, pessoa.getNome());
			ps.setString(2, pessoa.getSobrenome());
			ps.setInt(3, pessoa.getIdade());
			ps.setString(4, pessoa.getEmail());
						
			ps.executeUpdate();
        } catch (SQLException e) {
        	throw new RuntimeException("Erro ao executar SQL: " + e.getMessage(), e);
        } catch (ClassNotFoundException e) {
        	throw new RuntimeException("Driver JDBC não encontrado: " + e.getMessage(), e);
		}	
	}
	
	
	//o método listar pessoas irá retornar uma lista de pessoas, do qual suas informações serão resgatadas do banco de dados
	public List<Pessoa> listarPessoas() {
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		String sqlSelect = "select * from pessoas.pessoa;";
		
		
		try (Connection conexao = DriverManager.getConnection(url, usuarioBD, senhaBD);
	        	 PreparedStatement ps = conexao.prepareStatement(sqlSelect)){
			
			Class.forName("com.mysql.cj.jdbc.Driver");			
			ResultSet retorno = ps.executeQuery();			
			while(retorno.next()) {		
				Pessoa pessoa= new Pessoa();
				pessoa.setId(retorno.getInt("id"));
				pessoa.setNome(retorno.getString("nome"));
				pessoa.setSobrenome(retorno.getString("sobrenome"));
				pessoa.setIdade(retorno.getInt("idade"));
				pessoa.setEmail(retorno.getString("email"));
				
				pessoas.add(pessoa);
			}
        } catch (SQLException e) {
        	throw new RuntimeException("Erro ao executar SQL: " + e.getMessage(), e);
        } catch (ClassNotFoundException e) {
        	throw new RuntimeException("Driver JDBC não encontrado: " + e.getMessage(), e);
		}
		return pessoas;
	}
	
	public Pessoa buscarPessoaPorID(int id) {		
		String sqlSelect = "SELECT * FROM Pessoas.pessoa WHERE id = ?";
		Pessoa pessoa= new Pessoa();
		
		try (Connection conexao = DriverManager.getConnection(url, usuarioBD, senhaBD);
	        	 PreparedStatement ps = conexao.prepareStatement(sqlSelect)){
			ps.setInt(1, id);
			Class.forName("com.mysql.cj.jdbc.Driver");			
			ResultSet retorno = ps.executeQuery();			
			while(retorno.next()) {		
				
				pessoa.setId(retorno.getInt("id"));
				pessoa.setNome(retorno.getString("nome"));
				pessoa.setSobrenome(retorno.getString("sobrenome"));
				pessoa.setIdade(retorno.getInt("idade"));
				pessoa.setEmail(retorno.getString("email"));
				
			}
        } catch (SQLException e) {
        	throw new RuntimeException("Erro ao executar SQL: " + e.getMessage(), e);
        } catch (ClassNotFoundException e) {
        	throw new RuntimeException("Driver JDBC não encontrado: " + e.getMessage(), e);
		}
		return pessoa; //o método retorna um objeto pessoa baseado na id passado na classe view
	}
	
	public void removerPessoa(int id) {
			
		String sqlDelete = "DELETE FROM Pessoas.pessoa WHERE id = ?;";
		try (Connection conexao = DriverManager.getConnection(url, usuarioBD, senhaBD);
   	 PreparedStatement ps = conexao.prepareStatement(sqlDelete)){
			
			Class.forName("com.mysql.cj.jdbc.Driver");			
				
			ps.setInt(1, id);
			ps.executeUpdate();	
			
        } catch (SQLException e) {
        	throw new RuntimeException("Erro ao executar SQL: " + e.getMessage(), e);
        } catch (ClassNotFoundException e) {
        	throw new RuntimeException("Driver JDBC não encontrado: " + e.getMessage(), e);	
		} //o método exclui uma pessoa baseado no id passado na classe view
	}
	
	public void atualizarPessoa(int id, String atributo, String novoAtributo) {
	    String sqlUpdate = "UPDATE pessoas.pessoa SET " + atributo + " = ? WHERE id = ?;";
	    try (Connection conexao = DriverManager.getConnection(url, usuarioBD, senhaBD);
	         PreparedStatement ps = conexao.prepareStatement(sqlUpdate)) {

	        Class.forName("com.mysql.cj.jdbc.Driver");

	        if (atributo.equals("idade")) {
	            ps.setInt(1, Integer.parseInt(novoAtributo));
	        } else {
	            ps.setString(1, novoAtributo);
	        }
	        ps.setInt(2, id);

	        ps.executeUpdate();
	    } catch (SQLException e) {
	        throw new RuntimeException("Erro ao executar SQL: " + e.getMessage(), e);
	    } catch (ClassNotFoundException e) {
	        throw new RuntimeException("Driver JDBC não encontrado: " + e.getMessage(), e);
	    }
	} //o método passa os atributos necessários para executar um update

}
