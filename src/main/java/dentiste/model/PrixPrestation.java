package dentiste.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrixPrestation {
    private int min;
    private int max;
    private double price;
    private String prestation;

    public static double getPrix(List<PrixPrestation> allAmount, int niveau) {
        double prix = 0;
        for (PrixPrestation p : allAmount) {
            if (niveau ==0) {
                return 10;

            } if(niveau>=1){
                return Math.min(niveau+1,10);
            }
        }

        return prix;
    }

    public static DetailReparation getDetailRepatation(List<PrixPrestation> allAmount, Dent d, int niveau) {
        for (PrixPrestation p : allAmount) {
            if (niveau >= p.getMin() && niveau < p.getMax()) {
                return new DetailReparation(d, p.getPrestation(), p.getPrice());
            } else if (niveau == 0 && p.getMin() == 0) {
                return new DetailReparation(d, p.getPrestation(), p.getPrice());
            }
        }

        return null;
    }

    public static int getNextLevel(int niveau) {
        if (niveau == 0) return 10;
        if (niveau >= 1 && niveau < 4) return 5;
        if (niveau >= 4 && niveau < 7) return 8;
        if (niveau >= 8 && niveau < 8) return 10;

        return 10;
    }

    public static List<PrixPrestation> findByDent(String codeDent, Connection connection) throws SQLException {
        String sql = "SELECT * FROM v_prixprestation WHERE codedent=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, codeDent);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<PrixPrestation> prixPrestations = new ArrayList<>();
        PrixPrestation pp;

        while (resultSet.next()) {
            pp = new PrixPrestation();
            pp.setMin(resultSet.getInt("min"));
            pp.setMax(resultSet.getInt("max"));
            pp.setPrice(resultSet.getDouble("prix"));
            pp.setPrestation(resultSet.getString("nom"));

            prixPrestations.add(pp);
        }

        resultSet.close();
        preparedStatement.close();

        return prixPrestations;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPrestation() {
        return prestation;
    }

    public void setPrestation(String prestation) {
        this.prestation = prestation;
    }
}
