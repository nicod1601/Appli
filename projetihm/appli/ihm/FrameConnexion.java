package appli.ihm;

import javax.swing.*;
import java.awt.*;

public class FrameConnexion extends JFrame
{
    private PanelConnexion panelConnexion;

    private FrameID frameID;

    public FrameConnexion(FrameID frameID)
    {
        this.setTitle("Page Connexion");
        this.setSize(450, 150);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.frameID = frameID;

        /*--------------------------*/
        /* Création des composants  */
        /*--------------------------*/
        this.panelConnexion = new PanelConnexion(this);


        /*---------------------------*/
        /*   Position des composants */
        /*---------------------------*/
        this.add(this.panelConnexion);


        this.setVisible(false);
    }

    public boolean  connecter(String pseudo,String mdp)
    {
        return this.frameID.connecter(pseudo, mdp);
    }

    public void creerCompte()
    {
        this.frameID.setVisible(true);
        this.setVisible(false);
    }

    public void frameAppli()
    {
        this.frameID.frameAppli();
    }

    public void setInformation(String pseudo, String mdp)
    {
        this.frameID.setInformation(pseudo, mdp);
    }

    public void setEcritureBouton(Color c)
    {
        this.panelConnexion.setEcritureBouton(c);
    }

    public void setFondBouton(Color c)
    {
       this.panelConnexion.setFondBouton(c);
    }
  
}
