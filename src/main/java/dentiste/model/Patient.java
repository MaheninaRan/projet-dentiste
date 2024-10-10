package dentiste.model;

import dentiste.model.persist.DBConnect;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Patient {
    private int id;
    private String nom;
    private Date date;

    public List<List<DetailReparation>> traiter(double montant, int priorisation, Connection connection) throws SQLException, ClassNotFoundException {
        boolean isOpen = false;
        List<List<DetailReparation>> result = new ArrayList<>();
        if (connection == null || connection.isClosed()) {
            connection = new DBConnect().getConnection();
            isOpen = true;
        }

        List<EtatDent> etatDents = this.getEtatDent(priorisation, connection);
        double cout_reparation, reste_temp;
        for (EtatDent ed : etatDents) {
            cout_reparation = ed.calculCoutReparation(connection);
            reste_temp = montant - cout_reparation;


            if (reste_temp < 0) {
//                int etatDent = ed.getEtat();
//                ed.setEtat(etatDent+1);
                result.add(ed.getDetailReparation(montant, connection));
                break;
            }
            montant = reste_temp;
            result.add(ed.getDetailReparation(connection));
            ed.setEtat(10);
        }

        if (isOpen) connection.close();

        return result;
    }

    public List<EtatDent> getEtatDent(int priorisation, Connection connection) throws SQLException, ClassNotFoundException {
        boolean isOpen = false;
        if (connection == null || connection.isClosed()) {
            connection = new DBConnect().getConnection();
            isOpen = true;
        }

        String sql = "SELECT codedent, etat FROM v_patientEtatDentOrderByPriorite WHERE idpatient=? AND idpriorisation=? AND date = (SELECT MAX(date) FROM v_patientEtatDentOrderByPriorite WHERE idpatient=?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, this.getId());
        preparedStatement.setInt(2, priorisation);
        preparedStatement.setInt(3, this.getId());

        ResultSet resultSet = preparedStatement.executeQuery();

        List<EtatDent> etatDents = new ArrayList<>();
        EtatDent etatDent_temp;

        while (resultSet.next()) {
            etatDent_temp = new EtatDent();
            etatDent_temp.setDent(Dent.findByCode(connection, resultSet.getString(1)));
            etatDent_temp.setEtat(resultSet.getInt(2));

            etatDents.add(etatDent_temp);
        }

        resultSet.close();
        preparedStatement.close();
        if (isOpen) connection.close();

        return etatDents;
    }

    public void insererEtatDent(Date date,List<Integer> dents, List<Integer> etat, Connection connection) throws SQLException, ClassNotFoundException {
        boolean isOpen = false;
        if (connection == null || connection.isClosed()) {
            connection = new DBConnect().getConnection();
            connection.setAutoCommit(false);
            isOpen = true;
        }

        String sql = "INSERT INTO patientEtatDent(date, idpatient, codedent, etat) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, date);
            preparedStatement.setInt(2, this.getId());
            for (int i = 0; i < dents.size(); i++) {
                preparedStatement.setString(3, Dent.findById(connection, dents.get(i)).getCode());
                preparedStatement.setInt(4, etat.get(i));

                preparedStatement.executeUpdate();
            }
        }catch (SQLException e) {
            connection.rollback();
        }
        assert preparedStatement != null;
        preparedStatement.close();
        if (isOpen) {
            connection.close();
            connection.commit();
        }
    }


    public void insererEtatDent(Date date, String[] dents, String[] etat, Connection connection) throws SQLException, ClassNotFoundException {
        boolean isOpen = false;
        if (connection == null || connection.isClosed()) {
            connection = new DBConnect().getConnection();
            connection.setAutoCommit(false);
            isOpen = true;
        }

        String sql = "INSERT INTO patientEtatDent(date, idpatient, codedent, etat) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, date);
            preparedStatement.setInt(2, this.getId());
            for (int i = 0; i < dents.length; i++) {
                preparedStatement.setString(3, dents[i]);
                preparedStatement.setInt(4, Integer.parseInt(etat[i]));

                preparedStatement.executeUpdate();
            }
        }catch (SQLException e) {
            connection.rollback();
        }
        assert preparedStatement != null;
        preparedStatement.close();
        if (isOpen) {
            connection.close();
            connection.commit();
        }
    }



    public static Patient findById(Connection connection, String id) throws SQLException, ClassNotFoundException {
        int id_nbr = Integer.parseInt(id);
        return findById(connection, id_nbr);
    }

    public static Patient findById(Connection connection, int id) throws SQLException, ClassNotFoundException {
        boolean isOpen = false;
        if ((connection == null) || connection.isClosed()) {
            connection = new DBConnect().getConnection();
            isOpen = true;
        }
        String sql = "SELECT * FROM patient WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        Patient patient = null;
        if (resultSet.next()) {
            patient = new Patient();
            patient.setId(resultSet.getInt(1));
            patient.setNom(resultSet.getString(2));
            patient.setDate(resultSet.getDate(3));

        }

        resultSet.close();
        statement.close();

        if (isOpen) connection.close();

        return patient;
    }

    public static List<Patient> findAll(Connection connection) throws SQLException, ClassNotFoundException {
        boolean isOpen = false;
        List<Patient> patients = new ArrayList<>();
        if ((connection == null) || connection.isClosed()) {
            connection = new DBConnect().getConnection();
            isOpen = true;
        }
        String sql = "SELECT * FROM patient";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        Patient p_temp;
        while (resultSet.next()) {
            p_temp = new Patient();
            p_temp.setId(resultSet.getInt(1));
            p_temp.setNom(resultSet.getString(2));
            p_temp.setDate(resultSet.getDate(3));

            patients.add(p_temp);
        }

        resultSet.close();
        statement.close();

        if (isOpen) connection.close();

        return patients;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
