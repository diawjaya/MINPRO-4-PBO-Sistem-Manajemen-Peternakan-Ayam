/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.Koneksi;
import model.JenisAyam;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ASUS TUF
 */
public class JenisAyamDAO {
    public List<JenisAyam> getAll() throws SQLException {
        List<JenisAyam> data = new ArrayList<>();
        String sql = "SELECT * FROM jenis_ayam ORDER BY id_jenis_ayam";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                data.add(new JenisAyam(
                    rs.getInt("id_jenis_ayam"),
                    rs.getString("nama_jenis"),
                    rs.getDouble("harga_per_kg"),
                    rs.getInt("umur_panen")
                ));
            }
        }
        return data;
    }

    public boolean insert(JenisAyam j) throws SQLException {
        String sql = "INSERT INTO jenis_ayam VALUES (?, ?, ?, ?)";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, j.getIdJenisAyam());
            ps.setString(2, j.getNamaJenis());
            ps.setDouble(3, j.getHargaPerKg());
            ps.setInt(4, j.getUmurPanen());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean update(JenisAyam j) throws SQLException {
        String sql = "UPDATE jenis_ayam SET nama_jenis=?, "
                + "harga_per_kg=?, umur_panen=? WHERE id_jenis_ayam=?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, j.getNamaJenis());
            ps.setDouble(2, j.getHargaPerKg());
            ps.setInt(3, j.getUmurPanen());
            ps.setInt(4, j.getIdJenisAyam());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM jenis_ayam WHERE id_jenis_ayam=?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
