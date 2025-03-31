package appli.metier;

public class Compte
{
    private int id;
    private String nom;
    private String prenom;
    private String mdp;

    public Compte(int id, String nom, String prenom, String mdp)
    {
        this.id     = id;
        this.nom    = nom;
        this.prenom = prenom;
        this.mdp    = mdp;
    }
}
