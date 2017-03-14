/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasoperativos4;

/**
 *
 * @author Dell
 */
public class BCP {

    private String id;
    private String estado;
    private String op;
    private String res;
    private String tiempoLlegada;
    private String tiempoFinalizacion;
    private String tiempoRetorno;
    private String tiempoEspera;
    private String tiempoServicio;
    private String tiempoRestante;
    private String tiempoRespues;

    public BCP(String id, String estado, String op, String res, String tiempoLlegada, String tiempoFinalizacion, String tiempoRetorno, String tiempoEspera, String tiempoServicio, String tiempoRestante, String tiempoRespues) {
        this.id = id;
        this.estado = estado;
        this.op = op;
        this.res = res;
        this.tiempoLlegada = tiempoLlegada;
        this.tiempoFinalizacion = tiempoFinalizacion;
        this.tiempoRetorno = tiempoRetorno;
        this.tiempoEspera = tiempoEspera;
        this.tiempoServicio = tiempoServicio;
        this.tiempoRestante = tiempoRestante;
        this.tiempoRespues = tiempoRespues;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public String getTiempoLlegada() {
        return tiempoLlegada;
    }

    public void setTiempoLlegada(String tiempoLlegada) {
        this.tiempoLlegada = tiempoLlegada;
    }

    public String getTiempoFinalizacion() {
        return tiempoFinalizacion;
    }

    public void setTiempoFinalizacion(String tiempoFinalizacion) {
        this.tiempoFinalizacion = tiempoFinalizacion;
    }

    public String getTiempoRetorno() {
        return tiempoRetorno;
    }

    public void setTiempoRetorno(String tiempoRetorno) {
        this.tiempoRetorno = tiempoRetorno;
    }

    public String getTiempoEspera() {
        return tiempoEspera;
    }

    public void setTiempoEspera(String tiempoEspera) {
        this.tiempoEspera = tiempoEspera;
    }

    public String getTiempoServicio() {
        return tiempoServicio;
    }

    public void setTiempoServicio(String tiempoServicio) {
        this.tiempoServicio = tiempoServicio;
    }

    public String getTiempoRestante() {
        return tiempoRestante;
    }

    public void setTiempoRestante(String tiempoRestante) {
        this.tiempoRestante = tiempoRestante;
    }

    public String getTiempoRespues() {
        return tiempoRespues;
    }

    public void setTiempoRespues(String tiempoRespues) {
        this.tiempoRespues = tiempoRespues;
    }
    
    
    

}
