package control;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.Agendamento;
import model.AgendamentoDAO;
import model.AgendamentoServico;
import model.Usuario;
import model.UsuarioDAO;

/**
 * Servlet implementation class GerenciarRelatorio
 */
@WebServlet("/gerenciarRelatorio")
public class GerenciarRelatorio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String acao= request.getParameter("acao"); 
		
		if(acao.contains("relatorio")) {
        	gerarRelatorio(request,response);
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
    		Document documento = new Document();
    		Usuario usuario = GerenciarLogin.verificarAcesso(request, response);
    		AgendamentoDAO adao= new AgendamentoDAO();
    		UsuarioDAO udao= new UsuarioDAO();
    
    		 SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
    	        Calendar calendar = Calendar.getInstance();

    	        Date dateObj = calendar.getTime();
    	        String formattedDate = dtf.format(dateObj);
    	        
    	        String ipAddress = request.getHeader("X-FORWARDED-FOR");  
    	        if (ipAddress == null) {  
    	        	ipAddress = request.getRemoteAddr();  
    	        }
    		
    		try {
    			//extensao
    			response.setContentType("apllication/pdf");
    			
    			//nome
    			response.addHeader("Content-Disposition","inline; filename="+"relatorioAgendamentos.pdf");
    			
    			//criar o conteudo
    			response.reset();
    			PdfWriter.getInstance(documento,  response.getOutputStream());
    			
    			//abrir o documento
    			documento.open();
    			documento.add(new Paragraph("Lista de Agendamentos: "));
    			documento.add(new Paragraph(" "));
    		
    			
    			//criar tabela
    			PdfPTable tabela= new PdfPTable(10); //numero de colunas
    			
    			// cabeçalho
    			PdfPCell col1 = new PdfPCell(new Paragraph("ID Agendamento"));
    			PdfPCell col2 = new PdfPCell(new Paragraph("Serviço"));
    			PdfPCell col3 = new PdfPCell(new Paragraph("Horário"));
    			PdfPCell col4 = new PdfPCell(new Paragraph("Duração(Minutos)"));
    			PdfPCell col5 = new PdfPCell(new Paragraph("Status"));
    			PdfPCell col6 = new PdfPCell(new Paragraph("Atendente"));
    			PdfPCell col7 = new PdfPCell(new Paragraph("Cliente"));
    			PdfPCell col8 = new PdfPCell(new Paragraph("CPF Cliente"));
    			PdfPCell col9 = new PdfPCell(new Paragraph("Data de Serviço"));
    			PdfPCell col10 = new PdfPCell(new Paragraph("Valor"));
    			
    			
    			tabela.addCell(col1);
    			tabela.addCell(col2);
    			tabela.addCell(col3);
    			tabela.addCell(col4);
    			tabela.addCell(col5);
    			tabela.addCell(col6);
    			tabela.addCell(col7);
    			tabela.addCell(col8);
    			tabela.addCell(col9);
    			tabela.addCell(col10);
    			//popular a tabela
    			ArrayList<AgendamentoServico> lista1 = adao.agendamentosServicos(usuario.getIdUsuario(), usuario.getPerfil().getIdPerfil());
    			for(int i=0;i<lista1.size();i++) {
    					ArrayList<Usuario> atendente = udao.verificarAtendente(lista1.get(i).getIdAtendente());
    					ArrayList<Agendamento> agendamento = adao.carregarCliente(lista1.get(i).getAgendamento().getIdAgendamento());
    					
    					String idAgendamento = String.valueOf(lista1.get(i).getAgendamento().getIdAgendamento());
    					tabela.addCell(idAgendamento);
    	    			tabela.addCell(lista1.get(i).getServico().getNome());
    	    			tabela.addCell(lista1.get(i).getHorario().toString());
    	    			
    	    			String duracao= String.valueOf(lista1.get(i).getServico().getDuracao());
    	    			tabela.addCell(duracao);
    	    			String confirmacao;
    	    			
    	    			int status= lista1.get(i).getStatus();
    	    			if(status==1) {
    	    				confirmacao = "Confirmado";
    	    			}else if(status==2) {
    	    				confirmacao= "Cancelado";
    	    			}else {
    	    				confirmacao= "Em espera";
    	    			}
    	    			
    	    			String valor= String.valueOf(lista1.get(i).getServico().getPreco());
    	    			
    	    			tabela.addCell(confirmacao);
    	    			tabela.addCell(atendente.get(0).getNome());
    	    			tabela.addCell(agendamento.get(0).getUsuario().getNome());
    	    			tabela.addCell(agendamento.get(0).getUsuario().getCpf());
    	    			tabela.addCell(lista1.get(i).getAgendamento().getDataAgendamento().toString());
    	    			tabela.addCell(valor);
    				
    				
    				
    			}
    			documento.add(tabela);
    			documento.add(new Paragraph(" "));
    			documento.add(new Paragraph("Requisitante: "+usuario.getNome()));
    			documento.add(new Paragraph(" "));
    			documento.add(new Paragraph("CPF: "+usuario.getCpf()));
    			documento.add(new Paragraph(" "));
    			documento.add(new Paragraph("Cargo: "+usuario.getPerfil().getNome()));
    			documento.add(new Paragraph(" "));
    			documento.add(new Paragraph("Data de Requisição: "+formattedDate));
    			documento.add(new Paragraph(" "));
    			documento.add(new Paragraph("IP do requisitante: "+ipAddress));
    			documento.add(new Paragraph(" "));
    			documento.close();
    			
    		}catch(Exception ex) {
    			System.out.println("<script type='text/javascript'> "+"alert('"+ex.getStackTrace()+"');"+
                        "location.href='listarAgendamento.jsp';</script>");
    			documento.close();
    		}
    }

}
