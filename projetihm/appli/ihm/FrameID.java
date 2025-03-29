package appli.ihm;
import appli.Controleur;
import javax.swing.*;

public class FrameID extends JFrame
{
    private PanelID panelID;

    private Controleur ctrl;
    private FrameConnexion frameConnexion;
    private FrameAppli frameAppli;

    public FrameID(Controleur ctrl)
    {
        this.setTitle("Page Identification");
        this.setSize(450, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*--------------------------*/
        /* Création des composants  */
        /*--------------------------*/
        this.panelID = new PanelID(this);

        this.ctrl = ctrl;
        this.frameConnexion = new FrameConnexion(this);
        this.frameAppli = new FrameAppli(this);


        /*---------------------------*/
        /*   Position des composants */
        /*---------------------------*/
        this.add(this.panelID);


        this.setVisible(true);
    }

    public void setIdentification(int id, String nom, String prenom)
    {
        this.ctrl.setIdentification(id, nom, prenom);
    }

    public boolean connecter(String nom, String prenom)
    {
        return this.ctrl.connecter(nom, prenom);
    }

    public void frameConnexion()
    {
        this.frameConnexion.setVisible(true);
    }

    public void frameAppli()
    {
        this.frameAppli.setVisible(true);
    }
}