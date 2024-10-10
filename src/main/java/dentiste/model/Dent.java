package dentiste.model;

import dentiste.model.persist.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Dent {
    private int id;
    private String code;
    private String nom;

    public static Dent findByCode(Connection connection, String code) throws SQLException, ClassNotFoundException {
        boolean isOpen = false;
        if (connection == null || connection.isClosed()) {
            connection = new DBConnect().getConnection();
            isOpen = true;
        }

        String sql = "SELECT * FROM dent WHERE code = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, code);
        ResultSet resultSet = statement.executeQuery();

        Dent result = null;
        if (resultSet.next()) {
            result = new Dent();
            result.setId(resultSet.getInt(1));
            result.setCode(resultSet.getString(2));
            result.setNom(resultSet.getString(3));
        }

        resultSet.close();
        statement.close();
        if (isOpen) connection.close();

        return result;
    }

    public static List<Dent> findAll(Connection connection) throws SQLException, ClassNotFoundException {
        boolean isOpen = false;
        if (connection == null || connection.isClosed()) {
            connection = new DBConnect().getConnection();
            isOpen = true;
        }

        String sql = "SELECT * FROM dent";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        List<Dent> dents = new ArrayList<>();
        Dent d_temp;
        while (resultSet.next()) {
            d_temp = new Dent();
            d_temp.setId(resultSet.getInt(1));
            d_temp.setCode(resultSet.getString(2));
            d_temp.setNom(resultSet.getString(3));
            dents.add(d_temp);
        }

        resultSet.close();
        statement.close();
        if (isOpen) connection.close();

        return dents;
    }

    public static Dent findById(Connection connection, int id) throws SQLException, ClassNotFoundException {
        boolean isOpen = false;
        if (connection == null || connection.isClosed()) {
            connection = new DBConnect().getConnection();
            isOpen = true;
        }

        String sql = "SELECT * FROM dent WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        Dent result = null;
        if (resultSet.next()) {
            result = new Dent();
            result.setId(resultSet.getInt(1));
            result.setCode(resultSet.getString(2));
            result.setNom(resultSet.getString(3));
        }

        resultSet.close();
        statement.close();
        if (isOpen) connection.close();

        return result;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
