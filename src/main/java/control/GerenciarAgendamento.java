/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.Agendamento;
import model.AgendamentoDAO;
import model.AgendamentoServico;
import model.Servico;
import model.ServicoDAO;
import model.Usuario;
import model.UsuarioDAO;

/**
 *
 * @author Vitor
 */
@WebServlet(name = "GerenciarAgendamento", urlPatterns = {"/gerenciarAgendamento"})
public class GerenciarAgendamento extends HttpServlet {


    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String acao = request.getParameter("acao");
        int idAgendamento = Integer.parseInt(request.getParameter("idAgendamento"));
        int status= Integer.parseInt(request.getParameter("status"));
        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
        AgendamentoDAO adao = new AgendamentoDAO();
        PrintWriter out = response.getWriter();
        String mensagem = "";
        
        UsuarioDAO udao= new UsuarioDAO();
            try {
                Usuario usuario = udao.getCarregarPorId(idUsuario);

                if(acao.contains("confirmar")){
                    adao.confirmar(idAgendamento, status);
                     mensagem="Agendamento Confirmado!";
                     out.println("<script type='text/javascript'> "+"alert('"+mensagem+"');"+
                       "location.href='listarAgendamento.jsp';</script>");
                }
                else if(acao.contains("cancelar")){
                    adao.cancelar(idAgendamento, status);
                     mensagem="Agendamento Cancelado!";
                     if(usuario.getPerfil().getIdPerfil()<4){
                        out.println("<script type='text/javascript'> "+"alert('"+mensagem+"');"+
                       "location.href='listarAgendamento.jsp';</script>");
                     }else{
                          out.println("<script type='text/javascript'> "+"alert('"+mensagem+"');"+
                       "location.href='meusAgendamentos.jsp';</script>");
                     }
                }
                
                
            } catch (SQLException ex) {
                Logger.getLogger(GerenciarAgendamento.class.getName()).log(Level.SEVERE, null, ex);
            }
        
       
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       int idServico = Integer.parseInt(request.getParameter("idServico"));
       int status= Integer.parseInt(request.getParameter("status"));
       int idUsuario= Integer.parseInt(request.getParameter("idUsuario"));
       String idAgendamento = request.getParameter("idAgendamento");
       int idAtendente= Integer.parseInt(request.getParameter("idAtendente"));
       String tempo= request.getParameter("horario");
       Date dataAgendamento = Date.valueOf(request.getParameter("dataAgendamento"));
       String mensagem = "";
       PrintWriter out = response.getWriter();
       
     Usuario user = GerenciarLogin.verificarAcesso(request, response);
       
       Servico servico = new Servico();
       Usuario usuario = new Usuario();
       Agendamento agendamento = new Agendamento();
       AgendamentoServico agendamentoServico = new AgendamentoServico();
       ServicoDAO sdao = new ServicoDAO();
       UsuarioDAO udao= new UsuarioDAO();
       AgendamentoDAO adao= new AgendamentoDAO();
       
       Calendar verificarData = Calendar.getInstance();
       verificarData.getTime();
       verificarData.add(Calendar.DATE, 15);
       
       Calendar verificarDomingo = Calendar.getInstance();
       verificarDomingo.setTime(dataAgendamento);
       int dia = verificarDomingo.get(Calendar.DAY_OF_WEEK);
       
       
       
       
        try {
            servico= sdao.carregarServicoPorId(idServico);
            usuario= udao.getCarregarPorId(idUsuario);
            
            SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
            java.util.Date data2 =  formatador.parse(tempo);
            Time time = new Time(data2.getTime());
           
            Calendar hora = Calendar.getInstance();
             hora.setTime(time);
            
            if(idAgendamento.contentEquals("")){
               
      
            	
            	Calendar horaAtual= Calendar.getInstance();
                
                
                agendamento.setStatus(status);
                agendamento.setUsuario(usuario);
                agendamento.setValor(servico.getPreco());
                agendamento.setDataAgendamento(dataAgendamento);
                agendamentoServico.setIdAtendente(idAtendente);
                agendamentoServico.setStatus(status);
                agendamentoServico.setAgendamento(agendamento);
                agendamentoServico.setHorario(time);
               
              
                
                if(adao.verificarAtendimento(idAtendente, time, dataAgendamento)) {
                	mensagem="O agendamento n�o pode ser feito pois o atendente est� ocupado neste horario!!";
                    out.println("<script type='text/javascript'> "+"alert('"+mensagem+"');"+
                       "location.href='agendamento.jsp';</script>");
                }
                else if(dataAgendamento.compareTo(horaAtual.getTime())<-1 ) {
                	mensagem="O agendamento n�o pode ser feito para dias ou horarios anteriores ao atual!!";
                    out.println("<script type='text/javascript'> "+"alert('"+mensagem+"');"+
                       "location.href='agendamento.jsp';</script>");
                }
                else if(dataAgendamento.compareTo(verificarData.getTime())>=0){
                    mensagem="O data de atendimento n�o pode ser superior a 15 dias!";
                    out.println("<script type='text/javascript'> "+"alert('"+mensagem+"');"+
                       "location.href='agendamento.jsp';</script>");
                }else if(dia == 1 && (hora.get(Calendar.HOUR)>2)){
                    mensagem="N�o atendemos neste hor�rio no Domingo!";
                    out.println("<script type='text/javascript'> "+"alert('"+mensagem+"');"+
                       "location.href='agendamento.jsp';</script>");
                }else if(adao.qtdeAgendamentos(idUsuario)>=4){
                	
                    mensagem="Voc� j� tem 4 atendimentos confirmados ou para serem confirmados, cancele um deles para prosseguir!";
                    out.println("<script type='text/javascript'> "+"alert('"+mensagem+"');"+
                       "location.href='agendamento.jsp';</script>");
                }
                else{

                    adao.gravarAgendamento(agendamento);
                    agendamento = adao.carregarIDAgendamento(status, dataAgendamento, idUsuario);
                    
                    Date dataLimpar = new java.sql.Date(new java.util.Date().getTime());
                    adao.limparTabela(dataLimpar);

                    adao.vincularAgendamentoServico(servico, agendamento, agendamentoServico);
                     mensagem="Agendamento efetuado com sucesso!"; 
                    if(user.getPerfil().getIdPerfil()<4){
                            out.println("<script type='text/javascript'> "+"alert('"+mensagem+"');"+
                           "location.href='listarAgendamento.jsp';</script>");
                         }else{
                              out.println("<script type='text/javascript'> "+"alert('"+mensagem+"');"+
                           "location.href='meusAgendamentos.jsp';</script>");
                         }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(GerenciarAgendamento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(GerenciarAgendamento.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

   

   
    
}
