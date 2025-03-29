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
    private int id;


    public static Joueur creerJoueur(String nom, String prenom, int id)
    {
        if(Joueur.existe(nom, prenom)){return null;}

        return new Joueur(nom, prenom, id);
    }

    private Joueur(String nom, String prenom, int id)
    {
        this.nom    = nom;
        this.prenom = prenom;
        this.id     = id;

        this.sauvgarder();
    }

    public String getNom()   {return this.nom;   }
    public String getPrenom(){return this.prenom;}
    public int    getID()    {return this.id;    }

    public void setNom   (String nvNom   ){this.nom    = nvNom   ;}
    public void setPrenom(String nvPrenom){this.prenom = nvPrenom;}


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
            bw.newLine();

            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde : " + e.getMessage());
        }
    }
    public static boolean existe(String nom, String prenom)
    {
        try 
        {
            File fichier = new File("./donnee/joueur.txt");
            if (!fichier.exists()) return false; // Si le fichier n'existe pas, aucun joueur enregistré

            Scanner sc = new Scanner(fichier);
            while (sc.hasNextLine())
            {
                String ligne = sc.nextLine();
                if (ligne.contains("Nom : " + nom) && 
                    sc.hasNextLine() && sc.nextLine().contains("Prénom : " + prenom) /*  && 
                    sc.hasNextLine() && sc.nextLine().contains("ID : " + id)*/
                    )
                {
                    sc.close();
                    return true;
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erreur de lecture du fichier : " + e.getMessage());
        }
        return false;
    }
}
