package controller;

public class PemberianPakan {

    private final int idPemberian;
    private int jumlahPakan;
    private String tanggalPemberian;
    private String waktuPemberian;

    public PemberianPakan(int idPemberian, int jumlahPakan,
            String tanggalPemberian, String waktuPemberian) {

        this.idPemberian = idPemberian;
        this.jumlahPakan = jumlahPakan;
        this.tanggalPemberian = tanggalPemberian;
        this.waktuPemberian = waktuPemberian;
    }

    public PemberianPakan(int idPemberian, int jumlahPakan) {

        this.idPemberian = idPemberian;
        this.jumlahPakan = jumlahPakan;
        this.tanggalPemberian = "-";
        this.waktuPemberian = "-";
    }

    public int getIdPemberian() {
        return idPemberian;
    }

    public int getJumlahPakan() {
        return jumlahPakan;
    }

    public String getTanggalPemberian() {
        return tanggalPemberian;
    }

    public String getWaktuPemberian() {
        return waktuPemberian;
    }

    public void setJumlahPakan(int jumlahPakan) {
        this.jumlahPakan = jumlahPakan;
    }

    public void setTanggalPemberian(String tanggalPemberian) {
        this.tanggalPemberian = tanggalPemberian;
    }

    public void setWaktuPemberian(String waktuPemberian) {
        this.waktuPemberian = waktuPemberian;
    }
}