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
        this.setSize(500, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        /*--------------------------*/
        /* Création des composants  */
        /*--------------------------*/
        this.panelID = new PanelID(this);

        this.ctrl = ctrl;
        this.frameConnexion = new FrameConnexion(this);
        this.frameAppli = new FrameAppli(this, this.ctrl);


        /*---------------------------*/
        /*   Position des composants */
        /*---------------------------*/
        this.add(this.panelID);


        this.setVisible(true);
    }

    public void setIdentification(int id, String nom, String prenom, String mdp, String pseudo)
    {
        this.ctrl.setIdentification(id, nom, prenom, mdp, pseudo);
    }

    public boolean connecter(String pseudo, String mdp)
    {
        return this.ctrl.connecter(pseudo, mdp);
    }

    public void frameConnexion()
    {
        this.frameConnexion.setVisible(true);
    }

    public void frameAppli()
    {
        this.frameAppli.setVisible(true);
    }

    public void setInformation(String pseudo, String mdp)
    {
        this.frameAppli.setInformation(pseudo, mdp);
    }
}