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
import java.util.List;
import java.util.Scanner;

public class PBView {

    private final Scanner input = new Scanner(System.in);
    private final KandangDAO kandangDAO = new KandangDAO();
    private final JenisAyamDAO jenisAyamDAO = new JenisAyamDAO();
    private final PakanDAO pakanDAO = new PakanDAO();
    private final PemberianPakanDAO pemberianDAO =
            new PemberianPakanDAO();

    public void tampilkanMenu() throws SQLException {
        int pilihan;

        do {
            System.out.println("\n====================================");
            System.out.println(" SISTEM MANAJEMEN PETERNAKAN AYAM");
            System.out.println("====================================");
            System.out.println("1. Data Kandang (Read)");
            System.out.println("2. Data Jenis Ayam (Read)");
            System.out.println("3. Kelola Data Pakan (CRUD)");
            System.out.println("4. Kelola Pemberian Pakan (CRUD)");
            System.out.println("0. Keluar");

            pilihan = bacaInt("Pilih menu: ");

            switch (pilihan) {
                case 1 -> tampilkanKandang();
                case 2 -> tampilkanJenisAyam();
                case 3 -> menuPakan();
                case 4 -> menuPemberianPakan();
                case 0 -> System.out.println("Program selesai.");
                default -> System.out.println("Pilihan tidak tersedia.");
            }
        } while (pilihan != 0);
    }

    // KANDANG: READ

    private void tampilkanKandang() {
        try {
            List<Kandang> daftar = kandangDAO.getAll();

            System.out.println("\n--- DATA KANDANG ---");
            if (daftar.isEmpty()) {
                System.out.println("Belum ada data kandang.");
                return;
            }

            for (Kandang k : daftar) {
                System.out.println(
                    "ID: " + k.getIdKandang()
                    + " | Nama: " + k.getNamaKandang()
                    + " | Kapasitas: " + k.getKapasitas()
                    + " | Jumlah Ayam: " + k.getJumlahAyam()
                    + " | Jenis Ayam: " + k.getJenisAyam()
                    + " | Pakan Dibutuhkan: " + k.getPakanDibutuhkan()
                );
            }
        } catch (SQLException e) {
            tampilkanError(e);
        }
    }

    // JENIS AYAM: READ

    private void tampilkanJenisAyam() {
        try {
            List<JenisAyam> daftar = jenisAyamDAO.getAll();

            System.out.println("\n--- DATA JENIS AYAM ---");
            if (daftar.isEmpty()) {
                System.out.println("Belum ada data jenis ayam.");
                return;
            }

            for (JenisAyam j : daftar) {
                System.out.println(
                    "ID: " + j.getIdJenisAyam()
                    + " | Nama: " + j.getNamaJenis()
                    + " | Harga/Kg: Rp" + j.getHargaPerKg()
                    + " | Umur Panen: " + j.getUmurPanen() + " hari"
                );
            }
        } catch (SQLException e) {
            tampilkanError(e);
        }
    }

    // MENU PAKAN

    private void menuPakan() {
        int pilihan;

        do {
            System.out.println("\n--- KELOLA PAKAN ---");
            System.out.println("1. Tambah Pakan");
            System.out.println("2. Tampilkan Pakan");
            System.out.println("3. Ubah Pakan");
            System.out.println("4. Hapus Pakan");
            System.out.println("0. Kembali");

            pilihan = bacaInt("Pilih menu: ");

            switch (pilihan) {
                case 1:
                    tambahPakan();
                    break;
                case 2:
                    tampilkanPakan();
                    break;
                case 3:
                    ubahPakan();
                    break;
                case 4:
                    hapusPakan();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Pilihan tidak tersedia.");
            }
        } while (pilihan != 0);
    }

    private void tambahPakan() {
        try {
            int id = bacaInt("ID pakan: ");
            String nama = bacaString("Nama pakan: ");
            int stok = bacaInt("Stok pakan: ");

            if (id <= 0 || stok < 0) {
                System.out.println("ID harus positif dan stok tidak boleh negatif.");
                return;
            }

            if (pakanDAO.insert(new Pakan(id, nama, stok))) {
                System.out.println("Pakan berhasil ditambahkan.");
            }
        } catch (SQLException e) {
            tampilkanError(e);
        }
    }

    private void tampilkanPakan() {
        try {
            List<Pakan> daftar = pakanDAO.getAll();

            System.out.println("\n--- DATA PAKAN ---");
            if (daftar.isEmpty()) {
                System.out.println("Belum ada data pakan.");
                return;
            }

            for (Pakan p : daftar) {
                System.out.println(
                    "ID: " + p.getIdPakan()
                    + " | Nama: " + p.getNamaPakan()
                    + " | Stok: " + p.getStok()
                );
            }
        } catch (SQLException e) {
            tampilkanError(e);
        }
    }

    private void ubahPakan() {
        try {
            int id = bacaInt("ID pakan yang diubah: ");
            String nama = bacaString("Nama pakan baru: ");
            int stok = bacaInt("Stok baru: ");

            if (id <= 0 || stok < 0) {
                System.out.println("ID harus positif dan stok tidak boleh negatif.");
                return;
            }

            if (pakanDAO.update(new Pakan(id, nama, stok))) {
                System.out.println("Pakan berhasil diubah.");
            } else {
                System.out.println("ID pakan tidak ditemukan.");
            }
        } catch (SQLException e) {
            tampilkanError(e);
        }
    }

    private void hapusPakan() {
        try {
            int id = bacaInt("ID pakan yang dihapus: ");

            if (id <= 0) {
                System.out.println("ID harus positif.");
                return;
            }

            if (pakanDAO.delete(id)) {
                System.out.println("Pakan berhasil dihapus.");
            } else {
                System.out.println("ID pakan tidak ditemukan.");
            }
        } catch (SQLException e) {
            tampilkanError(e);
        }
    }

    // MENU PEMBERIAN PAKAN

    private void menuPemberianPakan() throws SQLException {
        int pilihan;

        do {
            System.out.println("\n--- KELOLA PEMBERIAN PAKAN ---");
            System.out.println("1. Tambah Pemberian Pakan");
            System.out.println("2. Tampilkan Pemberian Pakan");
            System.out.println("3. Ubah Pemberian Pakan");
            System.out.println("4. Hapus Pemberian Pakan");
            System.out.println("0. Kembali");

            pilihan = bacaInt("Pilih menu: ");

            switch (pilihan) {
                case 1 -> tambahPemberian();
                case 2 -> tampilkanPemberian();
                case 3 -> ubahPemberian();
                case 4 -> hapusPemberian();
                case 0 -> {
                }
                default -> System.out.println("Pilihan tidak tersedia.");
            }
        } while (pilihan != 0);
    }

    private void tambahPemberian() throws SQLException {
        int id = bacaInt("ID pemberian: ");
        int jumlah = bacaInt("Jumlah pakan: ");
        String hari = bacaHari();
        if (id <= 0 || jumlah <= 0) {
            System.out.println("ID dan jumlah harus lebih dari 0.");
            return;
        }
        if (pemberianDAO.insert(
                new PemberianPakan(id, jumlah, hari))) {
            System.out.println("Pemberian pakan berhasil ditambahkan.");
        }
    }

    private void tampilkanPemberian() throws SQLException {
        List<PemberianPakan> daftar = pemberianDAO.getAll();
        System.out.println("\n--- DATA PEMBERIAN PAKAN ---");
        if (daftar.isEmpty()) {
            System.out.println("Belum ada data pemberian pakan.");
            return;
        }
        for (PemberianPakan p : daftar) {
            System.out.println(
                    "ID: " + p.getIdPemberian()
                            + " | Jumlah Pakan: " + p.getJumlahPakan()
                            + " | Hari: " + p.getHari()
            );
        }
    }

    private void ubahPemberian() throws SQLException {
        int id = bacaInt("ID pemberian yang diubah: ");
        int jumlah = bacaInt("Jumlah pakan baru: ");
        String hari = bacaHari();
        if (id <= 0 || jumlah <= 0) {
            System.out.println("ID dan jumlah harus lebih dari 0.");
            return;
        }
        if (pemberianDAO.update(
                new PemberianPakan(id, jumlah, hari))) {
            System.out.println("Pemberian pakan berhasil diubah.");
        } else {
            System.out.println("ID pemberian tidak ditemukan.");
        }
    }

    private void hapusPemberian() {
        try {
            int id = bacaInt("ID pemberian yang dihapus: ");

            if (id <= 0) {
                System.out.println("ID harus positif.");
                return;
            }

            if (pemberianDAO.delete(id)) {
                System.out.println("Pemberian pakan berhasil dihapus.");
            } else {
                System.out.println("ID pemberian tidak ditemukan.");
            }
        } catch (SQLException e) {
            tampilkanError(e);
        }
    }

    // INPUT

    private int bacaInt(String pesan) {
        while (true) {
            System.out.print(pesan);

            try {
                return Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Masukkan bilangan bulat yang valid.");
            }
        }
    }

    private String bacaString(String pesan) {
        while (true) {
            System.out.print(pesan);
            String nilai = input.nextLine().trim();

            if (!nilai.isEmpty()) {
                return nilai;
            }

            System.out.println("Input tidak boleh kosong.");
        }
    }

    private String bacaHari() {
        String[] daftarHari = {
            "Senin", "Selasa", "Rabu", "Kamis",
            "Jumat", "Sabtu", "Minggu"
        };

        while (true) {
            String hari = bacaString("Hari pemberian pakan: ");

            for (String pilihan : daftarHari) {
                if (pilihan.equalsIgnoreCase(hari)) {
                    return pilihan;
                }
            }

            System.out.println("Nama hari tidak valid.");
        }
    }

    private void tampilkanError(SQLException e) {
        System.out.println("Kesalahan database: " + e.getMessage());
    }
}