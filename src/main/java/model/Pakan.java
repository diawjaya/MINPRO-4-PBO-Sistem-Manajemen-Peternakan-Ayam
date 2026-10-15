
package model;

public class Pakan {
    private int idPakan;
    private String namaPakan;
    private int stok;

    public Pakan(int idPakan, String namaPakan, int stok) {
        this.idPakan = idPakan;
        this.namaPakan = namaPakan;
        this.stok = stok;
    }

    public int getIdPakan() {
        return idPakan;
    }

    public String getNamaPakan() {
        return namaPakan;
    }

    public int getStok() {
        return stok;
    }

    public void setNamaPakan(String namaPakan) {
        this.namaPakan = namaPakan;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }
}
