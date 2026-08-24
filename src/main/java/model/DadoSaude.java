package model;

import java.time.LocalDateTime;

public class DadoSaude {
    private Integer id;
    private Integer freqCardia;
    private double oxigenacao;
    private Integer passos;
    private LocalDateTime dataHora;
    private Dispositivo dispositivo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFreqCardia() {
        return freqCardia;
    }

    public void setFreqCardia(Integer freqCardia) {
        this.freqCardia = freqCardia;
    }

    public double getOxigenacao() {
        return oxigenacao;
    }

    public void setOxigenacao(double oxigenacao) {
        this.oxigenacao = oxigenacao;
    }

    public Integer getPassos() {
        return passos;
    }

    public void setPassos(Integer passos) {
        this.passos = passos;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Dispositivo getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;
    }
}
