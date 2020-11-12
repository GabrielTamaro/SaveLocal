package br.usjt.ucsist.savelocationusjtql.model;


import java.util.Date;

public class Local{

    private String dadosLongitude;
    private String dadosLatitude;
    private String cep;
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private Date dataCadastro;


    public String getDadosLongitude() {
        return dadosLongitude;
    }

    public void setDadosLongitude(String dadosLongitude) {
        this.dadosLongitude = dadosLongitude;
    }

    public String getDadosLatitude() {
        return dadosLatitude;
    }

    public void setDadosLatitude(String dadosLatitude) {
        this.dadosLatitude = dadosLatitude;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}