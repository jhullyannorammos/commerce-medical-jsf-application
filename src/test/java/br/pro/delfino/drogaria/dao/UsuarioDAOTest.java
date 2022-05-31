package br.pro.delfino.drogaria.dao;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.HibernateException;
import org.junit.Ignore;
import org.junit.Test;

import br.com.application.dao.PessoaDAO;
import br.com.application.dao.UsuarioDAO;
import br.com.application.domain.Pessoa;
import br.com.application.domain.Usuario;
import br.com.application.enumeracao.TipoUsuario;

public class UsuarioDAOTest {
	@Test
	// @Ignore
	public void salvar() throws HibernateException, Exception{
		/*
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = new Pessoa();
		pessoa.setTelefone("(61)1234-3456");
		pessoa.setBairro("Asa Norte");
		pessoa.setCelular("(11)4578-6352");
		pessoa.setCep("78.369-451");
		pessoa.setCpf("123.456.789-96");
		pessoa.setComplemento("Prox. ao Brasilian Shopping");
		pessoa.setEmail("adm@adm.com.br");
		pessoa.setNome("Wilson Fisk");
		pessoa.setRg("45.967.369-56");
		pessoa.setCpf("478.236.458-85");
		
		Usuario usuario = new Usuario();
		usuario.setAtivo(true);
		usuario.setPessoa(pessoa);
		usuario.setSenhaSemCriptografia("q1w2e3r4");
		SimpleHash hash = new SimpleHash("md5", usuario.getSenhaSemCriptografia());
		usuario.setSenha(hash.toHex());
		
		usuario.setTipoUsuario(TipoUsuario.ADMINISTRADOR);
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);
		
		System.out.println("Usuário salvo com sucesso.");
	    */
	}
	
	@Test
	//@Ignore
	public void autenticar() throws HibernateException, Exception{
		/*
		String cpf = "999.999.999-99";
		String senha = "12345678";
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.autenticar(cpf, senha);
		
		System.out.println("Usuário autentica: " + usuario);
	    */
	}



}	
