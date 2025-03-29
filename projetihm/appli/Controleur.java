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

    public void setIdentification(int id, String nom, String prenom)
    {
        this.metier = Joueur.creerJoueur(nom, prenom, id);
    }

    public boolean connecter(String nom, String prenom)
    {
        return this.metier.existe(nom, prenom);
    }

    public static void main(String[] args)
    {
        Controleur ctrl = new Controleur();
    }


}