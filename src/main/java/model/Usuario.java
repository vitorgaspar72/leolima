/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.InputStream;
import java.sql.Date;
import java.util.Base64;

import javax.websocket.Decoder.BinaryStream;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 *
 * @author Vitor
 */
public class Usuario {
   private int idUsuario;
   private String nome;
   private String login;
   private String senha;
   private int status;
   private Perfil perfil;
   private String endereco;
   private String telefone;
   private Date dataNascimento;
   private String cpf;
   private InputStream foto;
   private String imagem;
   
   
    public Usuario(){
        
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Perfil getPerfil() {
        return perfil;
    }
    
    public void setPerfil(Perfil perfil){
        this.perfil=perfil;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

	public InputStream getFoto() {
		return foto;
	}

	public void setFoto(InputStream inputStream) {
		this.foto =  inputStream;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(InputStream foto) throws IOException {
		
		if(foto!=null) {
			byte[] bytes = foto.readAllBytes();
			String imagem = Base64.getEncoder().encodeToString(bytes);
			this.imagem = imagem;
		}
		
	}
	
	
    
    
    
    

    
   
   
}
