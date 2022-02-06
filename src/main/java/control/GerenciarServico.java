/*
  * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Servico;
import model.ServicoDAO;
import model.Usuario;

/**
 *
 * @author Vitor
 */
@WebServlet(name = "GerenciarServico", urlPatterns = {"/gerenciarServico"})
public class GerenciarServico extends HttpServlet {

   

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("acao");
        int idServico= Integer.parseInt(request.getParameter("idServico"));
        ServicoDAO sdao= new ServicoDAO();
        Servico servico= new Servico();
        PrintWriter out = response.getWriter();
        String mensagem="";
        Usuario usuario= GerenciarLogin.verificarAcesso(request, response);
        
        if(usuario==null){
                     out.println("<script type='text/javascript'> "+
                     "location.href='login.jsp';</script>"); 
        }else{
            if(usuario.getPerfil().getIdPerfil()>2){



                   out.println("<script type='text/javascript'> "+"alert('Usu�rio n�o autorizado!');"+
                   "location.href='index.jsp';</script>");
            }else{
                if(acao.contentEquals("alterar")){
                    try {
                        servico= sdao.carregarServicoPorId(idServico);
                        request.setAttribute("servico", servico);
                        RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/alterarServico.jsp");
                        dispatcher.forward(request, response);
                    } catch (SQLException ex) {
                        Logger.getLogger(GerenciarServico.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                if(acao.contentEquals("deletar")){
                    try {
                        if(sdao.deletarServico(idServico)){
                            mensagem="Servi�o deletado com sucesso";
                                out.println(
                            "<script type='text/javascript'>"
                            + "alert('" + mensagem + "');"
                            + "location.href='listarServico.jsp';"
                            + "</script>");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(GerenciarServico.class.getName()).log(Level.SEVERE, null, ex);
                    }


                }
            }    
        }    
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String money = request.getParameter("preco");
        String descricao = request.getParameter("descricao");
        int duracao = Integer.parseInt(request.getParameter("duracao"));
        String idServico= request.getParameter("idServico");
        
        NumberFormat nf = new DecimalFormat("##,###.##");
        
        PrintWriter out= response.getWriter();
        
        try {
            Double preco= nf.parse(money.toString()).doubleValue();
             Servico servico = new Servico();
            ServicoDAO sdao= new ServicoDAO();
            String mensagem="";
        
        if(idServico.contentEquals("")){
            
           
            servico.setDescricao(descricao);
            servico.setDuracao(duracao);
            servico.setNome(nome);
            servico.setPreco(preco);
            
            try {
                sdao.gravarServico(servico);
                mensagem="Servi�o cadastrado com sucesso!";
                       out.println("<script type='text/javascript'> "+"alert('"+mensagem+"');"+
                        "location.href='listarServico.jsp';</script>");
            } catch (SQLException ex) {
                Logger.getLogger(GerenciarServico.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            servico.setIdServico(Integer.parseInt(idServico));
            servico.setDescricao(descricao);
            servico.setDuracao(duracao);
            servico.setNome(nome);
            servico.setPreco(preco);
           
            try {
                sdao.gravarServico(servico);
                 mensagem="Servi�o alterado com sucesso!";
                       out.println("<script type='text/javascript'> "+"alert('"+mensagem+"');"+
                        "location.href='listarServico.jsp';</script>");
            } catch (SQLException ex) {
                Logger.getLogger(GerenciarServico.class.getName()).log(Level.SEVERE, null, ex);
            }
               
        }
        } catch (ParseException ex) {
            Logger.getLogger(GerenciarServico.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
   
        
       
    }
}