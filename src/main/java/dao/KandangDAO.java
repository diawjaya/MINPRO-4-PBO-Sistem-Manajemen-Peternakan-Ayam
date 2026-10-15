package dao;

import database.Koneksi;
import model.Kandang;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KandangDAO {

    public List<Kandang> getAll() throws SQLException {
        List<Kandang> daftar = new ArrayList<>();
        String sql = "SELECT * FROM kandang";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                daftar.add(new Kandang(
                    rs.getInt("id_kandang"),
                    rs.getString("nama_kandang"),
                    rs.getInt("kapasitas"),
                    rs.getInt("jumlah_ayam"),
                    rs.getString("jenis_ayam"),
                    rs.getInt("pakan_dibutuhkan")
                ));
            }
        }

        return daftar;
    }
}