/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.Koneksi;
import model.Pakan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ASUS TUF
 */
public class PakanDAO {
    public List<Pakan> getAll() throws SQLException {
        List<Pakan> data = new ArrayList<>();
        String sql = "SELECT * FROM pakan ORDER BY id_pakan";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                data.add(new Pakan(
                    rs.getInt("id_pakan"),
                    rs.getString("nama_pakan"),
                    rs.getInt("stok")
                ));
            }
        }
        return data;
    }

    public boolean insert(Pakan p) throws SQLException {
        String sql = "INSERT INTO pakan VALUES (?, ?, ?)";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, p.getIdPakan());
            ps.setString(2, p.getNamaPakan());
            ps.setInt(3, p.getStok());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean update(Pakan p) throws SQLException {
        String sql = "UPDATE pakan SET nama_pakan=?, stok=? "
                + "WHERE id_pakan=?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getNamaPakan());
            ps.setInt(2, p.getStok());
            ps.setInt(3, p.getIdPakan());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM pakan WHERE id_pakan=?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
