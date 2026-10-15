package dao;

import database.Koneksi;
import model.JenisAyam;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JenisAyamDAO {

    public List<JenisAyam> getAll() throws SQLException {
        List<JenisAyam> daftar = new ArrayList<>();
        String sql = "SELECT * FROM jenis_ayam";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                daftar.add(new JenisAyam(
                    rs.getInt("id_jenis_ayam"),
                    rs.getString("nama_jenis"),
                    rs.getDouble("harga_per_kg"),
                    rs.getInt("umur_panen")
                ));
            }
        }

        return daftar;
    }
}
