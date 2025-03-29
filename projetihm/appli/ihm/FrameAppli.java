package appli.ihm;

import java.awt.BorderLayout;
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

        this.frameID = frameID;
        /*--------------------------*/
        /* Création des composants  */
        /*--------------------------*/
        this.panelAccueil = new PanelAccueil();
        this.panelParametre = new PanelParametre(this.frameID, this);
        this.panelOptionParametre = new PanelOptionParametre();


        /*---------------------------*/
        /*   Position des composants */
        /*---------------------------*/
        this.add(this.panelAccueil);
        this.add(this.panelParametre, BorderLayout.NORTH);

        this.setVisible(false);
    }

    public void parametre()
    {
        if(this.panelOptionParametre.isVisible() == false)
        {
            this.panelOptionParametre.setVisible(true);
            this.panelAccueil.setVisible(false);
        }
        else
        {
            this.panelOptionParametre.setVisible(false);
            this.panelAccueil.setVisible(true);
        }
    }
}