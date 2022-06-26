package br.com.application.bean;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.hibernate.HibernateException;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.application.dao.UsuarioDAO;
import br.com.application.domain.Pessoa;
import br.com.application.domain.Usuario;
import br.com.application.enumeracao.TipoUsuario;

@ManagedBean
@SessionScoped
public class AutenticacaoBean {
	
	private Usuario usuario;
	private Usuario usuarioLogado;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
	
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	@PostConstruct
	public void iniciar() {
		usuario = new Usuario();
		usuario.setPessoa(new Pessoa());
	}

	public void autenticar2() throws HibernateException, Exception {
		try {
			Faces.redirect("./pages/principal.xhtml");
		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError(erro.getMessage());
		}
	}
	
	public void autentica() throws HibernateException, Exception {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioLogado = usuarioDAO.autenticar(usuario.getPessoa().getCpf(), usuario.getSenha());
			
			if(usuarioLogado == null){
				Messages.addGlobalError("CPF e/ou senha incorretos");
				return;
			}
			
			Faces.redirect("./pages/principal.xhtml");
		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError(erro.getMessage());
		}
	}
	
	public void autenticar() throws HibernateException, Exception {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioLogado = usuarioDAO.getUsuario(usuario.getLogon(), usuario.getSenha());
			
			if(usuarioLogado == null){
				Messages.addGlobalError("Username e/ou senha incorretos");
				return;
			}

		} catch (Exception erro) {
			erro.printStackTrace();
			Messages.addGlobalError(erro.getMessage());
		} finally {
			Faces.redirect("./pages/vendas.xhtml");
		}
	}
	
	public boolean temPermissoes(List<String> permissoes){	
		return true;
	}
	
	/*
	public boolean temPermissoes(List<String> permissoes){	
		for(String permissao : permissoes){
			if(usuarioLogado.getTipoUsuario() == permissao.charAt(0)){
				return true;
			}
		}
		return false;
	}
	
	public boolean temPermissoes(List<String> permissoes){	
		for(String permissao : permissoes){
			if(usuarioLogado.getTipoUsuario() == permissao.charAt(0)){
				return true;
			}
		}
		return false;
	}
	*/
}
