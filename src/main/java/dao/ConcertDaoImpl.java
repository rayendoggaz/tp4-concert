package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import metier.entities.SingletonConnection;
import metier.entities.Concert;

public class ConcertDaoImpl implements IConcertDao { // Update the class name to ConcertDaoImpl

    @Override
    public Concert save(Concert c) {
        Connection conn = SingletonConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO concert(NOM_CONCERT, PRIX) VALUES (?, ?)");
            ps.setString(1, c.getNomConcert());
            ps.setDouble(2, c.getPrix());
            ps.executeUpdate();

            PreparedStatement ps2 = conn.prepareStatement("SELECT MAX(ID_CONCERT) as MAX_ID FROM concert");
            ResultSet rs = ps2.executeQuery();
            if (rs.next()) {
                c.setIdConcert(rs.getInt("MAX_ID"));
            }

            ps.close();
            ps2.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    @Override
    public List<Concert> concertsParMC(String mc) {
        List<Concert> concerts = new ArrayList<>();
        Connection conn = SingletonConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM concert WHERE NOM_CONCERT LIKE ?");
            ps.setString(1, "%" + mc + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Concert c = new Concert();
                c.setIdConcert(rs.getInt("ID_CONCERT"));
                c.setNomConcert(rs.getString("NOM_CONCERT"));
                c.setPrix(rs.getDouble("PRIX"));
                concerts.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return concerts;
    }

    @Override
    public Concert getConcert(Long id) {
        Connection conn = SingletonConnection.getConnection();
        Concert concert = new Concert();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM concert WHERE ID_CONCERT = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                concert.setIdConcert(rs.getInt("ID_CONCERT"));
                concert.setNomConcert(rs.getString("NOM_CONCERT"));
                concert.setPrix(rs.getDouble("PRIX"));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return concert;
    }

    @Override
    public Concert updateConcert(Concert c) {
        Connection conn = SingletonConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE concert SET NOM_CONCERT=?, PRIX=? WHERE ID_CONCERT=?");
            ps.setString(1, c.getNomConcert());
            ps.setDouble(2, c.getPrix());
            ps.setInt(3, c.getIdConcert());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    public void deleteConcert(Long id) {
        Connection conn = SingletonConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM concert WHERE ID_CONCERT = ?");
            ps.setLong(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
