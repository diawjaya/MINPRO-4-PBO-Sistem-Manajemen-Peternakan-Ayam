
package dao;

import controller.PemberianPakan;
import database.Koneksi;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PemberianPakanDAO {

    public List<PemberianPakan> getAll() throws SQLException {
        List<PemberianPakan> daftar = new ArrayList<>();
        String sql = "SELECT * FROM pemberian_pakan";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                daftar.add(new PemberianPakan(
                    rs.getInt("id_pemberian"),
                    rs.getInt("jumlah_pakan"),
                    rs.getString("hari")
                ));
            }
        }

        return daftar;
    }

    public boolean insert(PemberianPakan data) throws SQLException {
        String sql = "INSERT INTO pemberian_pakan "
                   + "(id_pemberian, jumlah_pakan, hari) VALUES (?, ?, ?)";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, data.getIdPemberian());
            ps.setInt(2, data.getJumlahPakan());
            ps.setString(3, data.getHari());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean update(PemberianPakan data) throws SQLException {
        String sql = "UPDATE pemberian_pakan "
                   + "SET jumlah_pakan = ?, hari = ? "
                   + "WHERE id_pemberian = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, data.getJumlahPakan());
            ps.setString(2, data.getHari());
            ps.setInt(3, data.getIdPemberian());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean delete(int idPemberian) throws SQLException {
        String sql = "DELETE FROM pemberian_pakan WHERE id_pemberian = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idPemberian);
            return ps.executeUpdate() > 0;
        }
    }
}
