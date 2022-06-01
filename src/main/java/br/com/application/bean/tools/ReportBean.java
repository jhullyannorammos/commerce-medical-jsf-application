package br.com.application.bean.tools;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.omnifaces.util.Faces;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;



public class ReportBean implements Serializable {

	JasperPrint jasperPrint;
	
	public void ReportGeneratorToPDF(String nome, HashMap param, List list) throws JRException, IOException {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
		ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
		
		try {
			String path = servletContext.getRealPath("/");
			String logo = path + "resources/imagens/Medtronic.png";
			
			param.put("REPORT_LOCALE", new Locale("PT", "BR"));
			param.put("logo", logo);
			
			JRBeanCollectionDataSource JRBeanCollectionDataSource = new JRBeanCollectionDataSource(list);
			jasperPrint = JasperFillManager.fillReport(path + "relatorios/" + nome + ".jasper", param, JRBeanCollectionDataSource);
		
		}catch(Exception exception) {
			exception.printStackTrace();
		}finally {
			response.setContentType("application/pdf");
			response.addHeader("Content-disposition", "attachment; filename=\"" + nome + ".pdf\"");
			JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
			ServletOutputStream responseStream = response.getOutputStream();
			responseStream.flush();
			responseStream.close();
			FacesContext.getCurrentInstance().renderResponse();
			FacesContext.getCurrentInstance().responseComplete();
		}
		
	}

}
