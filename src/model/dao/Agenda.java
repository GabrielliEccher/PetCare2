package model.dao;

public class Agenda {

    private  String datahorainicio;
    private String datahorafinal;


    public Agenda(String datahorainicio, String datahorafinal) {
        this.datahorainicio = datahorainicio;
        this.datahorafinal = datahorafinal;
    }

    public Agenda() {

    }

    public String getDatahorainicio(String datahorafinal) {
        return datahorainicio;
    }

    public void setDatahorainicio(String datahorainicio) {
        this.datahorainicio = datahorainicio;
    }

    public String getDatahorafinal(String datahorainicio) {
        return datahorafinal;
    }

    public void setDatahorafinal(String datahorafinal) {
        this.datahorafinal = datahorafinal;
    }
}



