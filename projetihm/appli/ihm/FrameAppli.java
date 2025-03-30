package appli.ihm;

import java.awt.*;
import javax.swing.*;

public class FrameAppli extends JFrame
{
    private PanelAccueil    panelAccueil;
    private PanelParametre panelParametre;
    //private PanelMenu panelMenu;
    private PanelOptionParametre panelOptionParametre;

    private FrameID frameID;

    public FrameAppli(FrameID frameID)
    {
        this.setTitle("Appli");
        this.setSize(500, 500);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.frameID = frameID;
        /*--------------------------*/
        /* Création des composants  */
        /*--------------------------*/
        this.panelAccueil = new PanelAccueil();
        this.panelParametre = new PanelParametre(this.frameID, this);
        this.panelOptionParametre = new PanelOptionParametre(this);


        /*---------------------------*/
        /*   Position des composants */
        /*---------------------------*/
        this.add(this.panelAccueil        , BorderLayout.CENTER);
        this.add(this.panelParametre      , BorderLayout.NORTH );


        this.setVisible(false);
    }

    public void parametre(boolean t)
    {
        // Si le panel d'options est visible, on le remplace par le panel d'accueil
        if (t) {
            this.remove(this.panelAccueil);
            this.add(this.panelOptionParametre, BorderLayout.CENTER);
        } else {
            this.remove(this.panelOptionParametre);
            this.add(this.panelAccueil, BorderLayout.CENTER);
        }

        // Mettre à jour l'interface pour appliquer le changement
        this.revalidate();
        this.repaint();
    }

    public void setFond(Color c)
    {
        this.panelAccueil.setBackground(c);
    }

    public void setFondBouton(Color c)
    {
       this.panelParametre.setFondBouton(c);
    }

    public void setEcritureBouton(Color c)
    {
        this.panelParametre.setEcritureBouton(c);
    }
}