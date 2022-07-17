package com.project.base.model;
import java.time.LocalDate;

import javax.persistence.*;

@Entity
@Table(name = "cadusuarios")
public class Cadusuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idcadusuario;
	
	@Column(name = "dcr_usuario")
	private String dcr_usuario;
	@Column(name = "dcr_login")
	private String dcr_login;
	@Column(name = "dcr_senha")
	private String dcr_senha;
	@Column(name = "dat_cadastro")
	private LocalDate dat_cadastro;
	@Column(name = "dat_desativacao")
	private LocalDate dat_desativacao;
	
	public Cadusuario() {
	}

	public Cadusuario(String dcr_usuario, String dcr_login, String dcr_senha, LocalDate dat_cadastro) {
		this.dcr_usuario = dcr_usuario;
		this.dcr_login = dcr_login;
		this.dcr_senha = dcr_senha;
		this.dat_cadastro = dat_cadastro;
	}
	

	public long getIdcadusuario() {
		return idcadusuario;
	}

	public void setIdcadusuario(long idcadusuario) {
		this.idcadusuario = idcadusuario;
	}

	public String getDcr_usuario() {
		return dcr_usuario;
	}

	public void setDcr_usuario(String dcr_usuario) {
		this.dcr_usuario = dcr_usuario;
	}

	public String getDcr_login() {
		return dcr_login;
	}

	public void setDcr_login(String dcr_login) {
		this.dcr_login = dcr_login;
	}

	public String getDcr_senha() {
		return dcr_senha;
	}

	public void setDcr_senha(String dcr_senha) {
		this.dcr_senha = dcr_senha;
	}

	public LocalDate getDat_cadastro() {
		return dat_cadastro;
	}

	public void setDat_cadastro(LocalDate dat_cadastro) {
		this.dat_cadastro = dat_cadastro;
	}

	public LocalDate getDat_desativacao() {
		return dat_desativacao;
	}

	public void setDat_desativacao(LocalDate dat_desativacao) {
		this.dat_desativacao = dat_desativacao;
	}
	
	@Override
	public String toString() {
		return "Test [id=" + idcadusuario + ", usuario=" + dcr_usuario + ", login=" + dcr_login + ", cadastro=" + dat_cadastro + "]";
	}
}
