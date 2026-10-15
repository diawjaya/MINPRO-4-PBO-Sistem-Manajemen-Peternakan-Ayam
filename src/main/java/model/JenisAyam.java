package model;

public class JenisAyam {

    private int idJenisAyam;
    private String namaJenis;
    private double hargaPerKg;
    private int umurPanen;

    public JenisAyam(int idJenisAyam, String namaJenis,
                     double hargaPerKg, int umurPanen) {
        this.idJenisAyam = idJenisAyam;
        this.namaJenis = namaJenis;
        this.hargaPerKg = hargaPerKg;
        this.umurPanen = umurPanen;
    }

    public int getIdJenisAyam() {
        return idJenisAyam;
    }

    public String getNamaJenis() {
        return namaJenis;
    }

    public double getHargaPerKg() {
        return hargaPerKg;
    }

    public int getUmurPanen() {
        return umurPanen;
    }
}
