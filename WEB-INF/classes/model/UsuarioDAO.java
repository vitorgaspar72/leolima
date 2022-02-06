
package model;

import factory.ConexaoFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class UsuarioDAO {
    
    PerfilDAO pdao= new PerfilDAO();
  
  
    public ArrayList<Usuario> getLista(int idPerfil) throws SQLException{
        ArrayList<Usuario> usuarios = new ArrayList<>();
        Connection conexao = ConexaoFactory.conectar();
        String sql = "";
        PreparedStatement query;
        ResultSet lista;
        
        switch(idPerfil){
            case 1:
                sql="SELECT p.idPerfil,p.nome,u.idUsuario,u.nome,u.login,u.senha,u.status,u.cpf,u.endereco,u.telefone,u.dataNascimento,u.idPerfil FROM perfil p INNER JOIN usuario u ON p.idPerfil=u.idPerfil";

                query= conexao.prepareStatement(sql);
                lista= query.executeQuery();


                while(lista.next()){

                    PerfilDAO pdao= new PerfilDAO();
                    Usuario usuario = new Usuario();
                    usuario.setIdUsuario(lista.getInt("u.idUsuario"));
                    usuario.setNome(lista.getString("u.nome"));
                    usuario.setLogin(lista.getString("u.login"));
                    usuario.setSenha(lista.getString("u.senha"));
                    usuario.setStatus(lista.getInt("u.status"));
                    usuario.setCpf(lista.getString("u.cpf"));
                    usuario.setEndereco(lista.getString("u.endereco"));
                    usuario.setTelefone(lista.getString("u.telefone"));
                    usuario.setDataNascimento(lista.getDate("u.dataNascimento"));
                    usuario.setPerfil(pdao.getCarregarPorId(lista.getInt("u.idPerfil")));
                    usuarios.add(usuario);
                } 
                break;
                
            case 2:
                sql="SELECT p.idPerfil,p.nome,u.idUsuario,u.nome,u.login,u.senha,u.status,u.cpf,u.endereco,u.telefone,u.dataNascimento,u.idPerfil FROM perfil p INNER JOIN usuario u ON p.idPerfil=u.idPerfil WHERE p.idPerfil BETWEEN 2 AND 4 ";

                query= conexao.prepareStatement(sql);
                lista= query.executeQuery();


                while(lista.next()){

                    PerfilDAO pdao= new PerfilDAO();
                    Usuario usuario = new Usuario();
                    usuario.setIdUsuario(lista.getInt("u.idUsuario"));
                    usuario.setNome(lista.getString("u.nome"));
                    usuario.setLogin(lista.getString("u.login"));
                    usuario.setSenha(lista.getString("u.senha"));
                    usuario.setStatus(lista.getInt("u.status"));
                    usuario.setCpf(lista.getString("u.cpf"));
                    usuario.setEndereco(lista.getString("u.endereco"));
                    usuario.setTelefone(lista.getString("u.telefone"));
                    usuario.setDataNascimento(lista.getDate("u.dataNascimento"));
                    usuario.setPerfil(pdao.getCarregarPorId(lista.getInt("u.idPerfil")));
                    usuarios.add(usuario);
                } 
                break;
            case 3:
                sql="SELECT p.idPerfil,p.nome,u.idUsuario,u.nome,u.login,u.senha,u.status,u.cpf,u.endereco,u.telefone,u.dataNascimento,u.idPerfil FROM perfil p INNER JOIN usuario u ON p.idPerfil=u.idPerfil WHERE p.idPerfil BETWEEN 3 AND 4 ";

                query= conexao.prepareStatement(sql);
                lista= query.executeQuery();


                while(lista.next()){

                    PerfilDAO pdao= new PerfilDAO();
                    Usuario usuario = new Usuario();
                    usuario.setIdUsuario(lista.getInt("u.idUsuario"));
                    usuario.setNome(lista.getString("u.nome"));
                    usuario.setLogin(lista.getString("u.login"));
                    usuario.setSenha(lista.getString("u.senha"));
                    usuario.setStatus(lista.getInt("u.status"));
                    usuario.setCpf(lista.getString("u.cpf"));
                    usuario.setEndereco(lista.getString("u.endereco"));
                    usuario.setTelefone(lista.getString("u.telefone"));
                    usuario.setDataNascimento(lista.getDate("u.dataNascimento"));
                    usuario.setPerfil(pdao.getCarregarPorId(lista.getInt("u.idPerfil")));
                    usuarios.add(usuario);
                } 
                break;
                
        } 
        ConexaoFactory.close(conexao);
        return usuarios;
    }
    
    public boolean gravar(Usuario usuario) throws SQLException{
        Connection conexao = ConexaoFactory.conectar();
         
        
        if(usuario.getIdUsuario()==0){
           
            
            if(usuario.getFoto()!=null) {
            	 String sql="INSERT INTO usuario(nome, login, senha, status, idPerfil,cpf,endereco,telefone,dataNascimento,imagem) VALUES (?,?,?,?,?,?,?,?,?,?)";
                 PreparedStatement ps=conexao.prepareStatement(sql);
                 ps.setString(1, usuario.getNome());
                 ps.setString(2, usuario.getLogin());
                 ps.setString(3,usuario.getSenha());
                 ps.setInt(4, usuario.getStatus());
                 ps.setInt(5, usuario.getPerfil().getIdPerfil());
                 ps.setString(6, usuario.getCpf());
                 ps.setString(7, usuario.getEndereco());
                 ps.setString(8, usuario.getTelefone());
                 ps.setDate(9, usuario.getDataNascimento());	
            	 ps.setBinaryStream(10, usuario.getFoto());
       
            	 ps.execute();
           }else {
        	   String sql="INSERT INTO usuario(nome, login, senha, status, idPerfil,cpf,endereco,telefone,dataNascimento) VALUES (?,?,?,?,?,?,?,?,?)";
               PreparedStatement ps=conexao.prepareStatement(sql);
               ps.setString(1, usuario.getNome());
               ps.setString(2, usuario.getLogin());
               ps.setString(3,usuario.getSenha());
               ps.setInt(4, usuario.getStatus());
               ps.setInt(5, usuario.getPerfil().getIdPerfil());
               ps.setString(6, usuario.getCpf());
               ps.setString(7, usuario.getEndereco());
               ps.setString(8, usuario.getTelefone());
               ps.setDate(9, usuario.getDataNascimento());
               
               
               ps.execute();
           }
            
            
        }if(usuario.getIdUsuario()>0){
           
            if(usuario.getFoto()!=null) {
	            	String sql="UPDATE usuario SET nome = ?, senha = ?, " +
	                        "status = ?, idPerfil = ?, cpf=? ,endereco = ? ,telefone = ?,dataNascimento = ?, imagem= ? WHERE idUsuario = ?";
	               PreparedStatement ps=conexao.prepareStatement(sql);
	           
	               ps.setString(1, usuario.getNome());
	               ps.setString(2, usuario.getSenha());   
	               ps.setInt(3, usuario.getStatus());
	               ps.setInt(4, usuario.getPerfil().getIdPerfil());
	               ps.setString(5,  usuario.getCpf());
	               ps.setString(6, usuario.getEndereco());
	               ps.setString(7, usuario.getTelefone());
	               ps.setDate(8, usuario.getDataNascimento());
	               ps.setInt(10, usuario.getIdUsuario());
	               ps.setBinaryStream(9, usuario.getFoto());
	               
	               ps.execute();	
            	
          
            }else {
	            	String sql="UPDATE usuario SET nome = ?, senha = ?, " +
	                        "status = ?, idPerfil = ?, cpf=? ,endereco = ? ,telefone = ?,dataNascimento = ? WHERE idUsuario = ?";
	               PreparedStatement ps=conexao.prepareStatement(sql);
	           
	               ps.setString(1, usuario.getNome());
	               ps.setString(2, usuario.getSenha());   
	               ps.setInt(3, usuario.getStatus());
	               ps.setInt(4, usuario.getPerfil().getIdPerfil());
	               ps.setString(5,  usuario.getCpf());
	               ps.setString(6, usuario.getEndereco());
	               ps.setString(7, usuario.getTelefone());
	               ps.setDate(8, usuario.getDataNascimento());
	               ps.setInt(9, usuario.getIdUsuario());
	               
	               
	               ps.execute();
            }
            
          
            
        }
        return true;
    }
    
    public boolean deletar(int idUsuario) throws SQLException{
        Connection conexao = ConexaoFactory.conectar();
        String sql="DELETE FROM usuario WHERE idUsuario=?";
        PreparedStatement ps=conexao.prepareStatement(sql);
        ps.setInt(1, idUsuario);
        ps.execute();
        ConexaoFactory.close(conexao);
        return true;
    }
    
    public Usuario getCarregarPorId(int idUsuario) throws SQLException{
        Usuario usuario = new Usuario();
        Perfil perfil= new Perfil();
        Connection conexao= ConexaoFactory.conectar();
        String sql="SELECT p.idPerfil,p.nome,u.idUsuario,u.nome,u.login,u.senha,u.status,u.cpf,u.endereco,u.telefone,u.dataNascimento,u.idPerfil FROM perfil p INNER JOIN usuario u ON p.idPerfil=u.idPerfil WHERE u.idUsuario=?";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, idUsuario);
        ResultSet lista= ps.executeQuery();
        
        while(lista.next()){
            usuario.setIdUsuario(lista.getInt("u.idUsuario"));
            usuario.setNome(lista.getString("u.nome"));
            usuario.setLogin(lista.getString("u.login"));
            usuario.setSenha(lista.getString("u.senha"));
            usuario.setStatus(lista.getInt("u.status"));
            usuario.setCpf(lista.getString("u.cpf"));
            usuario.setDataNascimento(lista.getDate("u.dataNascimento"));
            usuario.setEndereco(lista.getString("u.endereco"));
            usuario.setTelefone(lista.getString("u.telefone"));
            perfil.setIdPerfil(lista.getInt("p.idPerfil"));
            perfil.setNome(lista.getString("p.nome"));
            usuario.setPerfil(perfil);
     
            
        }
        
        ConexaoFactory.close(conexao);
        return usuario;
    }
    
    public Usuario getCarregarUsuario(String login,String senha) throws SQLException, IOException{
       Usuario usuario = new Usuario();
 
        String sql="SELECT p.idPerfil,p.nome,u.idUsuario,u.nome,u.login,u.senha,u.status,u.cpf,u.endereco,u.telefone,u.dataNascimento,u.idPerfil,u.imagem FROM perfil p INNER JOIN usuario u ON p.idPerfil=u.idPerfil WHERE u.login=? AND u.senha=?";
       
       Connection conexao= ConexaoFactory.conectar();
       PreparedStatement ps= conexao.prepareStatement(sql);
       ps.setString(1, login);
       ps.setString(2, senha);
       ResultSet rs= ps.executeQuery();
       
       if(rs.next()){
           usuario.setIdUsuario(rs.getInt("u.idUsuario"));
           usuario.setNome(rs.getString("u.nome"));
           usuario.setLogin(rs.getString("u.login"));
            usuario.setSenha(rs.getString("u.senha"));
            usuario.setStatus(rs.getInt("u.status"));
            usuario.setCpf(rs.getString("u.cpf"));
            usuario.setDataNascimento(rs.getDate("u.dataNascimento"));
            usuario.setEndereco(rs.getString("u.endereco"));
            usuario.setTelefone(rs.getString("u.telefone"));
            usuario.setFoto(rs.getBinaryStream("u.imagem"));
            usuario.setPerfil(pdao.getCarregarPorId(rs.getInt("p.idPerfil")));
            if(rs.getBinaryStream("u.imagem")!=null) {
            	usuario.setImagem(rs.getBinaryStream("u.imagem"));
            }
            
       }
       conexao.close();
       return usuario;
    }
    
    public boolean getVerificarUsuario(String login, String cpf) throws SQLException{
        

        String sql= "SELECT idUsuario, login FROM usuario WHERE login=? ";
        Connection conexao= ConexaoFactory.conectar();
        PreparedStatement enviar = conexao.prepareStatement(sql);
        enviar.setString(1, login);
        ResultSet lista= enviar.executeQuery();
        
        
        String sql2="SELECT idUsuario, login FROM usuario WHERE cpf=?";
        PreparedStatement enviar2= conexao.prepareStatement(sql2);
        enviar2.setString(1, cpf);
        ResultSet lista2= enviar2.executeQuery();
        
        if(lista.next() || lista2.next()){
            ConexaoFactory.close(conexao);
            return true;
        }else{
            ConexaoFactory.close(conexao);
            return false;
            
        }
        
       
        
        
    }
    
    public ArrayList<Usuario> atendentes() throws SQLException{
        ArrayList<Usuario> atendentes = new ArrayList<>();
        String sql= "SELECT idUsuario, nome FROM usuario WHERE idPerfil BETWEEN 2 AND 3  ";
        Connection conexao= ConexaoFactory.conectar();
        PreparedStatement ps= conexao.prepareStatement(sql);
        ResultSet lista = ps.executeQuery();
        while(lista.next()){
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(lista.getInt("idUsuario"));
            usuario.setNome(lista.getString("nome"));
            atendentes.add(usuario);
        }
        
        
        
        conexao.close();
        return atendentes;
    }
    
    public ArrayList<Usuario> verificarAtendente(int id) throws SQLException{
        Connection conexao = ConexaoFactory.conectar();
        String sql = "SELECT idUsuario,nome,cpf FROM usuario WHERE idUsuario = ?";
        ArrayList<Usuario> lista = new ArrayList<>();
        PreparedStatement puxar = conexao.prepareStatement(sql);
        puxar.setInt(1, id);
        
        Usuario user = new Usuario();
        ResultSet usuario= puxar.executeQuery();
        
        if(usuario.next()){
            user.setIdUsuario(usuario.getInt("idUsuario"));
            user.setNome(usuario.getString("nome"));
            user.setCpf(usuario.getString("cpf"));
            lista.add(user);
        }
        conexao.close();
        return lista;
        
    }
    
    public ArrayList<Usuario> clientes() throws SQLException{
        ArrayList<Usuario> clientes = new ArrayList<>();
        String sql= "SELECT idUsuario, nome FROM usuario WHERE idPerfil =4  ";
        Connection conexao= ConexaoFactory.conectar();
        PreparedStatement ps= conexao.prepareStatement(sql);
        ResultSet lista = ps.executeQuery();
        while(lista.next()){
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(lista.getInt("idUsuario"));
            usuario.setNome(lista.getString("nome"));
            clientes.add(usuario);
        }
        
        
        
        conexao.close();
        return clientes;
    }
    
    public Usuario recuperarSenha(String cpf, String login, Date dataNascimento) throws SQLException{
        Usuario usuario = new Usuario();
        Connection conexao = ConexaoFactory.conectar();
        String sql="SELECT idUsuario,nome,cpf,login,dataNascimento FROM usuario WHERE cpf=? AND login=? AND dataNascimento =?";
        PreparedStatement puxar = conexao.prepareStatement(sql);
        puxar.setString(1, cpf);
        puxar.setString(2, login);
        puxar.setDate(3, dataNascimento);
        
        ResultSet lista = puxar.executeQuery();
        if(lista.next()){
            usuario.setIdUsuario(lista.getInt("idUsuario"));
            usuario.setCpf(lista.getString("cpf"));
            usuario.setNome(lista.getString("nome"));
            usuario.setLogin(lista.getString("login"));
            usuario.setDataNascimento(lista.getDate("dataNascimento"));
        }
        
        
        conexao.close();
        return usuario;
    }
    
    public boolean modificarSenha(int idUsuario, String senha) throws SQLException{
        String sql="UPDATE usuario SET senha = ? WHERE idUsuario = ?"; 
        Connection conexao = ConexaoFactory.conectar();
        PreparedStatement modificar = conexao.prepareStatement(sql);
        modificar.setString(1, senha);
        modificar.setInt(2,idUsuario);
        modificar.execute();
        
        conexao.close();
        
       return true;
    }
}
