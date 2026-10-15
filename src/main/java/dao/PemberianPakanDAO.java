/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.Koneksi;
import controller.PemberianPakan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ASUS TUF
 */
public class PemberianPakanDAO {
    public List<PemberianPakan> getAll() throws SQLException {
        List<PemberianPakan> data = new ArrayList<>();
        String sql = "SELECT * FROM pemberian_pakan "
                + "ORDER BY id_pemberian";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                data.add(new PemberianPakan(
                    rs.getInt("id_pemberian"),
                    rs.getInt("jumlah_pakan"),
                    rs.getDate("tanggal_pemberian").toString(),
                    rs.getTime("waktu_pemberian").toString()
                ));
            }
        }
        return data;
    }

    public boolean insert(PemberianPakan p) throws SQLException {
        String sql = "INSERT INTO pemberian_pakan VALUES (?, ?, ?, ?)";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, p.getIdPemberian());
            ps.setInt(2, p.getJumlahPakan());
            ps.setDate(3, Date.valueOf(p.getTanggalPemberian()));
            ps.setTime(4, Time.valueOf(p.getWaktuPemberian()));
            return ps.executeUpdate() > 0;
        }
    }

    public boolean update(PemberianPakan p) throws SQLException {
        String sql = "UPDATE pemberian_pakan SET jumlah_pakan=?, "
                + "tanggal_pemberian=?, waktu_pemberian=? "
                + "WHERE id_pemberian=?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, p.getJumlahPakan());
            ps.setDate(2, Date.valueOf(p.getTanggalPemberian()));
            ps.setTime(3, Time.valueOf(p.getWaktuPemberian()));
            ps.setInt(4, p.getIdPemberian());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM pemberian_pakan WHERE id_pemberian=?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
