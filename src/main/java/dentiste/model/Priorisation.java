package dentiste.model;

import dentiste.model.persist.DBConnect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Priorisation {
    private int id;
    private String nom;

    public static List<Priorisation> findAll (Connection connection) throws SQLException, ClassNotFoundException {
        boolean isOpen = false;
        if (connection == null || connection.isClosed()) {
            connection = new DBConnect().getConnection();
            isOpen = true;
        }

        String sql="SELECT * FROM priorisation";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        List<Priorisation> priorisations = new ArrayList<>();
        Priorisation p_temp;
        while (resultSet.next()) {
            p_temp = new Priorisation();
            p_temp.setId(resultSet.getInt(1));
            p_temp.setNom(resultSet.getString(2));

            priorisations.add(p_temp);
        }
        resultSet.close();
        statement.close();
        if (isOpen) connection.close();

        return priorisations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
