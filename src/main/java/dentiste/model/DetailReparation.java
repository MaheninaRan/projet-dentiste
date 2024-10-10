package dentiste.model;

public class DetailReparation {
    private Dent dent;
    private int etatInital;
    private String description;
    private double cout;

    public DetailReparation(Dent dent, String description, double cout) {
        this.dent = dent;
        this.etatInital = etatInital;
        this.description = description;
        this.cout = cout;
    }

    public DetailReparation() {}

    public Dent getDent() {
        return dent;
    }

    public void setDent(Dent dent) {
        this.dent = dent;
    }

    public int getEtatInital() {
        return etatInital;
    }

    public void setEtatInital(int etatInital) {
        this.etatInital = etatInital;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCout() {
        return cout;
    }

    public void setCout(double cout) {
        this.cout = cout;
    }
}
