package appli;

import appli.ihm.FrameID;
import appli.metier.Joueur;

public class Controleur
{
    private Joueur metier;
    private FrameID ihm;

    public Controleur()
    {
        this.metier = null;
        this.ihm    = new FrameID(this);
    }

    public void setIdentification(int id, String nom, String prenom, String mdp, String pseudo)
    {
        this.metier = Joueur.creerJoueur(nom, prenom, id, mdp, pseudo);
    }

    public boolean connecter(String pseudo, String mdp)
    {
        return this.metier.existe(pseudo, mdp);
    }

    public static void main(String[] args)
    {
        Controleur ctrl = new Controleur();
    }


}