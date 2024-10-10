package dentiste.model;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EtatDent {
    private Dent dent;
    private int etat;

    public static HashMap<String, String[]> toStringArray(List<EtatDent> etatDents) {
        String[] dents = new String[etatDents.size()];
        String[] etat = new String[etatDents.size()];

        for (int i=0; i<etatDents.size(); i++) {

            if (Integer.parseInt(etat[i]) == 10) continue;

            dents[i] = etatDents.get(i).getDent().getCode();
            etat[i] = String.valueOf(etatDents.get(i).getEtat());
        }

        HashMap<String, String[]> result = new HashMap<>();
        result.put("dents", dents);
        result.put("etats", etat);

        return result;
    }

    public double calculCoutReparation(Connection connection) throws SQLException {
        List<PrixPrestation> allAmount = PrixPrestation.findByDent(this.getDent().getCode(), connection);
        double prix = 0;
        int nextLevel = this.getEtat();

        while (nextLevel != 10) {
            prix += PrixPrestation.getPrix(allAmount, nextLevel);
            nextLevel = PrixPrestation.getNextLevel(nextLevel);
        }

        return prix;
    }


    public List<DetailReparation> getDetailReparation(Connection connection) throws SQLException, ClassNotFoundException {
        List<PrixPrestation> allAmount = PrixPrestation.findByDent(this.getDent().getCode(), connection);
        List<DetailReparation> detailReparations = new ArrayList<>();
        int nextLevel = this.getEtat();

        while (nextLevel != 10) {
            detailReparations.add(PrixPrestation.getDetailRepatation(allAmount, this.getDent(), nextLevel));
            nextLevel = PrixPrestation.getNextLevel(nextLevel);
        }

        return detailReparations;
    }

    public List<DetailReparation> getDetailReparation(double reste_montant, Connection connection) throws SQLException, ClassNotFoundException {
        List<PrixPrestation> allAmount = PrixPrestation.findByDent(this.getDent().getCode(), connection);
        List<DetailReparation> detailReparations = new ArrayList<>();
        int nextLevel = this.getEtat();

        double prix_total = 0;
        DetailReparation dr;
        while (nextLevel != 10) {
            dr = PrixPrestation.getDetailRepatation(allAmount, this.getDent(), nextLevel);
            prix_total += dr.getCout();
            nextLevel = PrixPrestation.getNextLevel(nextLevel);

            if (prix_total > reste_montant ) break;
            detailReparations.add(dr);
        }

        return detailReparations;
    }

    public Dent getDent() {
        return dent;
    }
    public void setDent(Dent dent) {
        this.dent = dent;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
}
