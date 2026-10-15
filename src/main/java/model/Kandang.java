
package model;

public class Kandang {
    private int idKandang;
    private String namaKandang;
    private int kapasitas;
    private int jumlahAyam;
    private String jenisAyam;
    private int pakanDibutuhkan;

    public Kandang(int idKandang, String namaKandang,
            int kapasitas, int jumlahAyam,
            String jenisAyam, int pakanDibutuhkan) {
        this.idKandang = idKandang;
        this.namaKandang = namaKandang;
        this.kapasitas = kapasitas;
        this.jumlahAyam = jumlahAyam;
        this.jenisAyam = jenisAyam;
        this.pakanDibutuhkan = pakanDibutuhkan;
    }

    public int getIdKandang() {
        return idKandang;
    }

    public String getNamaKandang() {
        return namaKandang;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public int getJumlahAyam() {
        return jumlahAyam;
    }

    public String getJenisAyam() {
        return jenisAyam;
    }

    public int getPakanDibutuhkan() {
        return pakanDibutuhkan;
    }

    public void setNamaKandang(String namaKandang) {
        this.namaKandang = namaKandang;
    }

    public void setKapasitas(int kapasitas) {
        this.kapasitas = kapasitas;
    }

    public void setJumlahAyam(int jumlahAyam) {
        this.jumlahAyam = jumlahAyam;
    }

    public void setJenisAyam(String jenisAyam) {
        this.jenisAyam = jenisAyam;
    }

    public void setPakanDibutuhkan(int pakanDibutuhkan) {
        this.pakanDibutuhkan = pakanDibutuhkan;
    }
}
