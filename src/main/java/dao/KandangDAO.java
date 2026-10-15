/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.Koneksi;
import model.Kandang;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ASUS TUF
 */
public class KandangDAO {
   public List<Kandang> getAll() throws SQLException {
        List<Kandang> data = new ArrayList<>();
        String sql = "SELECT * FROM kandang ORDER BY id_kandang";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                data.add(new Kandang(
                    rs.getInt("id_kandang"),
                    rs.getString("nama_kandang"),
                    rs.getInt("kapasitas"),
                    rs.getInt("jumlah_ayam"),
                    rs.getString("jenis_ayam"),
                    rs.getInt("pakan_dibutuhkan")
                ));
            }
        }
        return data;
    }

    public boolean insert(Kandang k) throws SQLException {
        String sql = "INSERT INTO kandang VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, k.getIdKandang());
            ps.setString(2, k.getNamaKandang());
            ps.setInt(3, k.getKapasitas());
            ps.setInt(4, k.getJumlahAyam());
            ps.setString(5, k.getJenisAyam());
            ps.setInt(6, k.getPakanDibutuhkan());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean update(Kandang k) throws SQLException {
        String sql = "UPDATE kandang SET nama_kandang=?, kapasitas=?, "
                + "jumlah_ayam=?, jenis_ayam=?, pakan_dibutuhkan=? "
                + "WHERE id_kandang=?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, k.getNamaKandang());
            ps.setInt(2, k.getKapasitas());
            ps.setInt(3, k.getJumlahAyam());
            ps.setString(4, k.getJenisAyam());
            ps.setInt(5, k.getPakanDibutuhkan());
            ps.setInt(6, k.getIdKandang());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM kandang WHERE id_kandang=?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    } 
}
