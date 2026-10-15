/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import dao.KandangDAO;
import dao.JenisAyamDAO;
import dao.PakanDAO;
import dao.PemberianPakanDAO;
import model.Kandang;
import model.JenisAyam;
import model.Pakan;
import controller.PemberianPakan;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ASUS TUF
 */
public class PBView {
   private final Scanner input = new Scanner(System.in);

    private final KandangDAO kandangDAO = new KandangDAO();
    private final JenisAyamDAO jenisAyamDAO = new JenisAyamDAO();
    private final PakanDAO pakanDAO = new PakanDAO();
    private final PemberianPakanDAO pemberianDAO =
            new PemberianPakanDAO();

    public void tampilkanMenu() {
        boolean berjalan = true;

        while (berjalan) {
            System.out.println("\n=== SISTEM MANAJEMEN PETERNAKAN AYAM ===");
            System.out.println("1. Kelola Kandang");
            System.out.println("2. Kelola Jenis Ayam");
            System.out.println("3. Kelola Pakan");
            System.out.println("4. Kelola Pemberian Pakan");
            System.out.println("0. Keluar");

            int pilihan = bacaInteger("Pilih menu: ", 0, 4);

            try {
                switch (pilihan) {
                    case 1:
                        menuKandang();
                        break;
                    case 2:
                        menuJenisAyam();
                        break;
                    case 3:
                        menuPakan();
                        break;
                    case 4:
                        menuPemberianPakan();
                        break;
                    case 0:
                        berjalan = false;
                        System.out.println("Program selesai.");
                        break;
                }
            } catch (SQLException e) {
                System.out.println("Terjadi kesalahan database: "
                        + e.getMessage());
            }
        }
    }

    private int menuOperasi(String nama) {
        System.out.println("\n=== KELOLA " + nama.toUpperCase() + " ===");
        System.out.println("1. Tampilkan data");
        System.out.println("2. Tambah data");
        System.out.println("3. Ubah data");
        System.out.println("4. Hapus data");
        System.out.println("0. Kembali");

        return bacaInteger("Pilih operasi: ", 0, 4);
    }

    private void menuKandang() throws SQLException {
        boolean kembali = false;

        while (!kembali) {
            switch (menuOperasi("Kandang")) {
                case 1:
                    tampilKandang();
                    break;
                case 2:
                    tambahKandang();
                    break;
                case 3:
                    ubahKandang();
                    break;
                case 4:
                    hapusKandang();
                    break;
                case 0:
                    kembali = true;
                    break;
            }
        }
    }

    private void menuJenisAyam() throws SQLException {
        boolean kembali = false;

        while (!kembali) {
            switch (menuOperasi("Jenis Ayam")) {
                case 1:
                    tampilJenisAyam();
                    break;
                case 2:
                    tambahJenisAyam();
                    break;
                case 3:
                    ubahJenisAyam();
                    break;
                case 4:
                    hapusJenisAyam();
                    break;
                case 0:
                    kembali = true;
                    break;
            }
        }
    }

    private void menuPakan() throws SQLException {
        boolean kembali = false;

        while (!kembali) {
            switch (menuOperasi("Pakan")) {
                case 1:
                    tampilPakan();
                    break;
                case 2:
                    tambahPakan();
                    break;
                case 3:
                    ubahPakan();
                    break;
                case 4:
                    hapusPakan();
                    break;
                case 0:
                    kembali = true;
                    break;
            }
        }
    }

    private void menuPemberianPakan() throws SQLException {
        boolean kembali = false;

        while (!kembali) {
            switch (menuOperasi("Pemberian Pakan")) {
                case 1:
                    tampilPemberianPakan();
                    break;
                case 2:
                    tambahPemberianPakan();
                    break;
                case 3:
                    ubahPemberianPakan();
                    break;
                case 4:
                    hapusPemberianPakan();
                    break;
                case 0:
                    kembali = true;
                    break;
            }
        }
    }

    private void tampilKandang() throws SQLException {
        List<Kandang> data = kandangDAO.getAll();

        System.out.println("\n--- DATA KANDANG ---");
        System.out.printf("%-5s %-18s %-10s %-12s %-18s %-18s%n",
                "ID", "Nama", "Kapasitas", "Jumlah Ayam",
                "Jenis Ayam", "Pakan Dibutuhkan");

        for (Kandang k : data) {
            System.out.printf("%-5d %-18s %-10d %-12d %-18s %-18d%n",
                    k.getIdKandang(), k.getNamaKandang(),
                    k.getKapasitas(), k.getJumlahAyam(),
                    k.getJenisAyam(), k.getPakanDibutuhkan());
        }

        if (data.isEmpty()) {
            System.out.println("Belum ada data kandang.");
        }
    }

    private void tambahKandang() throws SQLException {
        int id = bacaInteger("ID kandang: ", 1, Integer.MAX_VALUE);
        String nama = bacaTeks("Nama kandang: ");
        int kapasitas = bacaInteger("Kapasitas: ", 1, Integer.MAX_VALUE);
        int jumlah = bacaInteger("Jumlah ayam: ", 0, kapasitas);
        String jenis = bacaTeks("Jenis ayam: ");
        int pakan = bacaInteger("Pakan dibutuhkan: ", 0, Integer.MAX_VALUE);

        Kandang k = new Kandang(id, nama, kapasitas, jumlah, jenis, pakan);
        hasil(kandangDAO.insert(k), "Data kandang berhasil ditambahkan.");
    }

    private void ubahKandang() throws SQLException {
        int id = bacaInteger("ID kandang yang diubah: ", 1, Integer.MAX_VALUE);
        String nama = bacaTeks("Nama kandang baru: ");
        int kapasitas = bacaInteger("Kapasitas baru: ", 1, Integer.MAX_VALUE);
        int jumlah = bacaInteger("Jumlah ayam baru: ", 0, kapasitas);
        String jenis = bacaTeks("Jenis ayam: ");
        int pakan = bacaInteger("Pakan dibutuhkan: ", 0, Integer.MAX_VALUE);

        Kandang k = new Kandang(id, nama, kapasitas, jumlah, jenis, pakan);
        hasil(kandangDAO.update(k), "Data kandang berhasil diubah.");
    }

    private void hapusKandang() throws SQLException {
        int id = bacaInteger("ID kandang yang dihapus: ", 1, Integer.MAX_VALUE);

        if (konfirmasiHapus()) {
            hasil(kandangDAO.delete(id), "Data kandang berhasil dihapus.");
        }
    }

    private void tampilJenisAyam() throws SQLException {
        List<JenisAyam> data = jenisAyamDAO.getAll();

        System.out.println("\n--- DATA JENIS AYAM ---");
        System.out.printf("%-5s %-22s %-18s %-12s%n",
                "ID", "Nama Jenis", "Harga per Kg", "Umur Panen");

        for (JenisAyam j : data) {
            System.out.printf("%-5d %-22s %-18.2f %-12d%n",
                    j.getIdJenisAyam(), j.getNamaJenis(),
                    j.getHargaPerKg(), j.getUmurPanen());
        }

        if (data.isEmpty()) {
            System.out.println("Belum ada data jenis ayam.");
        }
    }

    private void tambahJenisAyam() throws SQLException {
        int id = bacaInteger("ID jenis ayam: ", 1, Integer.MAX_VALUE);
        String nama = bacaTeks("Nama jenis: ");
        double harga = bacaDouble("Harga per kg: ", 0);
        int umur = bacaInteger("Umur panen (hari): ", 1, Integer.MAX_VALUE);

        JenisAyam j = new JenisAyam(id, nama, harga, umur);
        hasil(jenisAyamDAO.insert(j),
                "Data jenis ayam berhasil ditambahkan.");
    }

    private void ubahJenisAyam() throws SQLException {
        int id = bacaInteger("ID jenis ayam yang diubah: ", 1, Integer.MAX_VALUE);
        String nama = bacaTeks("Nama jenis baru: ");
        double harga = bacaDouble("Harga per kg: ", 0);
        int umur = bacaInteger("Umur panen (hari): ", 1, Integer.MAX_VALUE);

        JenisAyam j = new JenisAyam(id, nama, harga, umur);
        hasil(jenisAyamDAO.update(j),
                "Data jenis ayam berhasil diubah.");
    }

    private void hapusJenisAyam() throws SQLException {
        int id = bacaInteger("ID jenis ayam yang dihapus: ", 1, Integer.MAX_VALUE);

        if (konfirmasiHapus()) {
            hasil(jenisAyamDAO.delete(id),
                    "Data jenis ayam berhasil dihapus.");
        }
    }

    private void tampilPakan() throws SQLException {
        List<Pakan> data = pakanDAO.getAll();

        System.out.println("\n--- DATA PAKAN ---");
        System.out.printf("%-5s %-25s %-12s%n",
                "ID", "Nama Pakan", "Stok");

        for (Pakan p : data) {
            System.out.printf("%-5d %-25s %-12d%n",
                    p.getIdPakan(), p.getNamaPakan(), p.getStok());
        }

        if (data.isEmpty()) {
            System.out.println("Belum ada data pakan.");
        }
    }

    private void tambahPakan() throws SQLException {
        int id = bacaInteger("ID pakan: ", 1, Integer.MAX_VALUE);
        String nama = bacaTeks("Nama pakan: ");
        int stok = bacaInteger("Stok pakan: ", 0, Integer.MAX_VALUE);

        Pakan p = new Pakan(id, nama, stok);
        hasil(pakanDAO.insert(p), "Data pakan berhasil ditambahkan.");
    }

    private void ubahPakan() throws SQLException {
        int id = bacaInteger("ID pakan yang diubah: ", 1, Integer.MAX_VALUE);
        String nama = bacaTeks("Nama pakan baru: ");
        int stok = bacaInteger("Stok baru: ", 0, Integer.MAX_VALUE);

        Pakan p = new Pakan(id, nama, stok);
        hasil(pakanDAO.update(p), "Data pakan berhasil diubah.");
    }

    private void hapusPakan() throws SQLException {
        int id = bacaInteger("ID pakan yang dihapus: ", 1, Integer.MAX_VALUE);

        if (konfirmasiHapus()) {
            hasil(pakanDAO.delete(id), "Data pakan berhasil dihapus.");
        }
    }

    private void tampilPemberianPakan() throws SQLException {
        List<PemberianPakan> data = pemberianDAO.getAll();

        System.out.println("\n--- DATA PEMBERIAN PAKAN ---");
        System.out.printf("%-5s %-15s %-18s %-15s%n",
                "ID", "Jumlah Pakan", "Tanggal", "Waktu");

        for (PemberianPakan p : data) {
            System.out.printf("%-5d %-15d %-18s %-15s%n",
                    p.getIdPemberian(), p.getJumlahPakan(),
                    p.getTanggalPemberian(), p.getWaktuPemberian());
        }

        if (data.isEmpty()) {
            System.out.println("Belum ada data pemberian pakan.");
        }
    }

    private void tambahPemberianPakan() throws SQLException {
        int id = bacaInteger("ID pemberian: ", 1, Integer.MAX_VALUE);
        int jumlah = bacaInteger("Jumlah pakan: ", 1, Integer.MAX_VALUE);
        String tanggal = bacaTanggal();
        String waktu = bacaWaktu();

        PemberianPakan p =
                new PemberianPakan(id, jumlah, tanggal, waktu);

        hasil(pemberianDAO.insert(p),
                "Data pemberian pakan berhasil ditambahkan.");
    }

    private void ubahPemberianPakan() throws SQLException {
        int id = bacaInteger("ID pemberian yang diubah: ", 1, Integer.MAX_VALUE);
        int jumlah = bacaInteger("Jumlah pakan baru: ", 1, Integer.MAX_VALUE);
        String tanggal = bacaTanggal();
        String waktu = bacaWaktu();

        PemberianPakan p =
                new PemberianPakan(id, jumlah, tanggal, waktu);

        hasil(pemberianDAO.update(p),
                "Data pemberian pakan berhasil diubah.");
    }

    private void hapusPemberianPakan() throws SQLException {
        int id = bacaInteger("ID pemberian yang dihapus: ", 1, Integer.MAX_VALUE);

        if (konfirmasiHapus()) {
            hasil(pemberianDAO.delete(id),
                    "Data pemberian pakan berhasil dihapus.");
        }
    }

    private int bacaInteger(String pesan, int min, int max) {
        while (true) {
            System.out.print(pesan);
            String teks = input.nextLine().trim();

            try {
                int angka = Integer.parseInt(teks);

                if (angka >= min && angka <= max) {
                    return angka;
                }

                System.out.println("Masukkan angka antara "
                        + min + " dan " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa bilangan bulat.");
            }
        }
    }

    private double bacaDouble(String pesan, double min) {
        while (true) {
            System.out.print(pesan);
            String teks = input.nextLine().trim().replace(',', '.');

            try {
                double angka = Double.parseDouble(teks);

                if (Double.isFinite(angka) && angka >= min) {
                    return angka;
                }

                System.out.println("Nilai harus minimal " + min + ".");
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka.");
            }
        }
    }

    private String bacaTeks(String pesan) {
        while (true) {
            System.out.print(pesan);
            String teks = input.nextLine().trim();

            if (!teks.isEmpty()) {
                return teks;
            }

            System.out.println("Input tidak boleh kosong.");
        }
    }

    private String bacaTanggal() {
        while (true) {
            System.out.print("Tanggal (YYYY-MM-DD): ");
            String tanggal = input.nextLine().trim();

            try {
                return LocalDate.parse(tanggal).toString();
            } catch (Exception e) {
                System.out.println(
                        "Tanggal tidak valid. Contoh: 2026-10-15");
            }
        }
    }

    private String bacaWaktu() {
        while (true) {
            System.out.print("Waktu (HH:MM atau HH:MM:SS): ");
            String waktu = input.nextLine().trim();

            try {
                LocalTime jam = waktu.length() == 5
                        ? LocalTime.parse(waktu)
                        : LocalTime.parse(waktu);

                return jam.toString().length() == 5
                        ? jam.toString() + ":00"
                        : jam.toString();
            } catch (Exception e) {
                System.out.println(
                        "Waktu tidak valid. Contoh: 08:30 atau 08:30:00");
            }
        }
    }

    private boolean konfirmasiHapus() {
        while (true) {
            System.out.print("Yakin ingin menghapus? (y/n): ");
            String jawaban = input.nextLine().trim();

            if (jawaban.equalsIgnoreCase("y")) {
                return true;
            }

            if (jawaban.equalsIgnoreCase("n")) {
                System.out.println("Penghapusan dibatalkan.");
                return false;
            }

            System.out.println("Masukkan y atau n.");
        }
    }

    private void hasil(boolean berhasil, String pesan) {
        if (berhasil) {
            System.out.println(pesan);
        } else {
            System.out.println(
                    "Operasi tidak berhasil. Periksa ID atau data yang dimasukkan.");
        }
    } 
}
