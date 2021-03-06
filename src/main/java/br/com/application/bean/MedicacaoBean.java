package br.com.application.bean;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.hibernate.HibernateException;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.com.application.dao.FabricanteDAO;
import br.com.application.dao.MedicacaoDAO;
import br.com.application.domain.Fabricante;
import br.com.application.domain.Medicacao;
import br.com.application.util.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class MedicacaoBean implements Serializable {
	
	MedicacaoDAO produtoDAO;
	private Medicacao produto;
	private List<Medicacao> produtos;
	private List<Fabricante> fabricantes;

	private StreamedContent foto;
	
	@PostConstruct public void listar() throws Exception {
		try {
			produtoDAO = new MedicacaoDAO();
			produtos = produtoDAO.listar("descricao");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os produtos");
			erro.printStackTrace();
		}
	}

	public void novo() throws Exception {
		try {
			produto = new Medicacao();

			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.listar("descricao");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar um novo produto");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) throws Exception {
		try {
			produto = (Medicacao) evento.getComponent().getAttributes().get("produtoSelecionado");
			produto.setCaminho("D:/images/devs/" + produto.getCodigo() + ".png");

			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar um produto");
			erro.printStackTrace();
		}
	}

	public void salvar() throws Exception {
		try {
			if (produto.getCaminho() == null) {
				Messages.addGlobalError("O campo foto ?? obrigat??rio");
				return;
			}

			produtoDAO = new MedicacaoDAO();
			Medicacao produtoRetorno = produtoDAO.merge(produto);

			Path origem = Paths.get(produto.getCaminho());
			Path destino = Paths.get("D:/images/devs/" + produtoRetorno.getCodigo() + ".png");
			Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
			
		} catch (RuntimeException | IOException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar o produto");
			erro.printStackTrace();
		} finally {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.listar();
			
			produtoDAO = new MedicacaoDAO();
			produtos = produtoDAO.listar();

			Messages.addGlobalInfo("Produto salvo com sucesso");
		}
	}

	public void excluir(ActionEvent evento) throws Exception {
		produto = (Medicacao) evento.getComponent().getAttributes().get("produtoSelecionado");
		try {
			produtoDAO = new MedicacaoDAO();
			produtoDAO.excluir(produto);

			Path arquivo = Paths.get("D:/images/dev/" + produto.getCodigo() + ".png");
			Files.deleteIfExists(arquivo);

		} catch (RuntimeException | IOException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o produto");
			erro.printStackTrace();
		} finally {
			produtos = produtoDAO.listar();
			Messages.addGlobalInfo("Produto removido com sucesso");
		}
	}

	
	public void upload(FileUploadEvent evento) {
		try {
			UploadedFile arquivoUpload = evento.getFile();
			Path arquivoTemp = Files.createTempFile(null, null);
			
			Files.copy(arquivoUpload.getInputstream(), 
					arquivoTemp, 
					StandardCopyOption.REPLACE_EXISTING);
			
			produto.setCaminho(arquivoTemp.toString());

		} catch (IOException erro) {
			Messages.addGlobalInfo("Ocorreu um erro ao tentar realizar o upload de arquivo");
			erro.printStackTrace();
		} finally {
			Messages.addGlobalInfo("Upload realizado com sucesso");
		}
	}

	public void imprimir() throws HibernateException, Exception {
		try {
			DataTable tabela = (DataTable) Faces.getViewRoot().findComponent("formListagem:tabela");
			Map<String, Object> filtros = tabela.getFilters();

			String proDescricao = (String) filtros.get("descricao");
			String fabDescricao = (String) filtros.get("fabricante.descricao");
			String caminho = Faces.getRealPath("/reports/produtos.jasper");
			String caminhoBanner = Faces.getRealPath("/resources/images/banner.jpg");

			Map<String, Object> parametros = new HashMap<>();
			parametros.put("CAMINHO_BANNER", caminhoBanner);
			
			if (proDescricao == null) {
				parametros.put("PRODUTO_DESCRICAO", "%%");
			} else {
				parametros.put("PRODUTO_DESCRICAO", "%" + proDescricao + "%");
			}
			
			if (fabDescricao == null) {
				parametros.put("FABRICANTE_DESCRICAO", "%%");
			} else {
				parametros.put("FABRICANTE_DESCRICAO", "%" + fabDescricao + "%");
			}

			Connection conexao = HibernateUtil.getConnection();
			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
			JasperPrintManager.printReport(relatorio, true);
			
		} catch (JRException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar o relat??rio");
			erro.printStackTrace();
		}
	}

	public void download(ActionEvent evento) {
		produto = (Medicacao) evento.getComponent().getAttributes().get("produtoSelecionado");
		try {
			InputStream stream = new FileInputStream("C:/Desenvolvimento/Uploads/" + produto.getCodigo() + ".png");
			foto = new DefaultStreamedContent(stream, "image/png", produto.getCodigo() + ".png");
		} catch (FileNotFoundException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar efetuar o download da foto");
			erro.printStackTrace();
		}
	}
	
	public Medicacao getProduto() {
		return produto;
	}

	public void setProduto(Medicacao produto) {
		this.produto = produto;
	}

	public List<Medicacao> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Medicacao> produtos) {
		this.produtos = produtos;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

	public StreamedContent getFoto() {
		return foto;
	}

	public void setFoto(StreamedContent foto) {
		this.foto = foto;
	}

}
