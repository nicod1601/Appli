package appli.metier;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Joueur 
{
    private String nom;
    private String prenom;
    private String pseudo;
    private int id;
    private String mdp;


    public static Joueur creerJoueur(String nom, String prenom, int id, String mdp, String pseudo)
    {
        if(Joueur.existe(pseudo, mdp)){return null;}

        return new Joueur(nom, prenom, id, mdp, pseudo);
    }

    private Joueur(String nom, String prenom, int id, String mdp, String pseudo)
    {
        this.nom    = nom;
        this.prenom = prenom;
        this.id     = id;
        this.mdp    = mdp;
        this.pseudo = pseudo;

        this.sauvgarder();
    }

    public String getNom()   {return this.nom;   }
    public String getPrenom(){return this.prenom;}
    public int    getID()    {return this.id;    }
    public String getMDP()   {return this.mdp;   }
    public String getPseudo(){return this.pseudo;}
    

    public void setNom   (String nvNom   ){this.nom    = nvNom   ;}
    public void setPrenom(String nvPrenom){this.prenom = nvPrenom;}
    public void setMDP   (String nvMDP   ){this.mdp    = nvMDP   ;}
    public void setPseudo(String nvPseudo){this.pseudo = nvPseudo;}


    public void sauvgarder()
    {
        try 
        {
            File dossier = new File("./donnee");
            if (!dossier.exists()) 
            {
                dossier.mkdirs(); // Création du dossier s'il n'existe pas
            }

            FileWriter fw = new FileWriter("./donnee/joueur.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("Nom : " + this.nom);
            bw.newLine();
            bw.write("Prénom : " + this.prenom);
            bw.newLine();
            bw.write("ID : " + this.id);
            bw.newLine();
            bw.write("MDP : " + this.mdp);
            bw.newLine();
            bw.write("Pseudo : " + this.pseudo);
            bw.newLine();
            bw.newLine();

            bw.close();
            fw.close();
        }
        catch (IOException e)
        {
            System.out.println("Erreur lors de la sauvegarde : " + e.getMessage());
        }
    }
    public static boolean existe(String pseudo, String mdp) {
        try {
            File fichier = new File("./donnee/joueur.txt");
            if (!fichier.exists()) return false;
    
            Scanner sc = new Scanner(fichier);
            
            while (sc.hasNextLine())
            {
                String nom     = sc.nextLine();
                String prenom  = sc.nextLine();
                String id      = sc.nextLine();
                String mdpLigne = sc.nextLine();
                String pseudoLigne = sc.nextLine();
    
                // On s'assure que le format est correct avant de comparer
                if (mdpLigne.startsWith("MDP : ") && pseudoLigne.startsWith("Pseudo : "))
                {
                    String mdpFichier = mdpLigne.substring(6);
                    String pseudoFichier = pseudoLigne.substring(9);
    
                    if (mdpFichier.equals(mdp) && pseudoFichier.equals(pseudo))
                    {
                        sc.close();
                        return true;
                    }
                }
    
                // Vérification qu'on ne saute pas une ligne vide entre les joueurs
                if (sc.hasNextLine())
                {
                    sc.nextLine();
                }
            }
            
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erreur de lecture du fichier : " + e.getMessage());
        }
        return false;
    }
    
}
