package Model;

/**
 * Created by Tarek on 04/12/2016.
 */

public class Tache {
    private String tache;
    private String temps;

    public Tache(String tache) {
        this.tache = tache;
    }

    public Tache(String tache, String temps) {
        this.tache = tache;
        this.temps = temps;
    }

    public String getTache() {
        return tache;
    }

    public void setTache(String tache) {
        this.tache = tache;
    }

    public String getTemps() {
        return temps;
    }

    public void setTemps(String temps) {
        this.temps = temps;
    }
}
