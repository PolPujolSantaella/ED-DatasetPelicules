package cat.urv.deim.models;

public class Data {

    private int dia;
    private int mes;
    private int any;

    public Data (int dia, int mes, int any) {
        this.dia = dia;
        this.mes = mes;
        this.any = any;
    }

    //Getters i Setters

    public int getDia() {
        return this.dia;
    }

    public int getMes() {
        return this.mes;
    }

    public int getAny() {
        return this.any;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public void setAny(int any) {
        this.any = any;
    }
    
}
