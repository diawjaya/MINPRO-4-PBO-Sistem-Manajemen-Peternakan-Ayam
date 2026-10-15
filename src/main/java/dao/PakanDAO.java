package dao;

import database.Koneksi;
import model.Pakan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PakanDAO {

    public List<Pakan> getAll() throws SQLException {
        List<Pakan> daftar = new ArrayList<>();
        String sql = "SELECT * FROM pakan";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                daftar.add(new Pakan(
                    rs.getInt("id_pakan"),
                    rs.getString("nama_pakan"),
                    rs.getInt("stok")
                ));
            }
        }

        return daftar;
    }

    public boolean insert(Pakan pakan) throws SQLException {
        String sql = "INSERT INTO pakan "
                   + "(id_pakan, nama_pakan, stok) VALUES (?, ?, ?)";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, pakan.getIdPakan());
            ps.setString(2, pakan.getNamaPakan());
            ps.setInt(3, pakan.getStok());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean update(Pakan pakan) throws SQLException {
        String sql = "UPDATE pakan SET nama_pakan = ?, stok = ? "
                   + "WHERE id_pakan = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pakan.getNamaPakan());
            ps.setInt(2, pakan.getStok());
            ps.setInt(3, pakan.getIdPakan());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean delete(int idPakan) throws SQLException {
        String sql = "DELETE FROM pakan WHERE id_pakan = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPakan);
            return ps.executeUpdate() > 0;
        }
    }
}