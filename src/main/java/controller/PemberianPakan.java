
package controller;

public class PemberianPakan {
    private int idPemberian;
    private int jumlahPakan;
    private String hari;

    public PemberianPakan(int idPemberian, int jumlahPakan, String hari) {
        this.idPemberian = idPemberian;
        this.jumlahPakan = jumlahPakan;
        this.hari = hari;
    }

    public int getIdPemberian() {
        return idPemberian;
    }

    public int getJumlahPakan() {
        return jumlahPakan;
    }

    public String getHari() {
        return hari;
    }
}
