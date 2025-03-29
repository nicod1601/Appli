package appli.ihm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PanelOptionParametre extends JPanel implements ActionListener 
{
    private JTabbedPane tabbedPane;
    private JPanel panelCompte;
    private JPanel panelChangerFond;
    private JPanel panelSecurite;

    private JButton btnQuitter;

    /* Partie Compte */
    private JLabel lblProfil;
    private JTextField txtNom;
    private JTextField txtPrenom;

    private FrameAppli frame;

    public PanelOptionParametre(FrameAppli frame)
    {
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(30, 30, 30));

        /*--------------------------*/
        /* Création des composants  */
        /*--------------------------*/
        this.frame = frame;

        this.tabbedPane = new JTabbedPane();
        this.panelCompte = new JPanel();
        this.panelChangerFond = new JPanel();
        this.panelSecurite = new JPanel();

        this.btnQuitter = new JButton("Quitter");

        /* Panel Compte */
        this.panelCompte.setLayout(new GridLayout(3, 1, 5, 5)); // Ajustement des espacements
        this.lblProfil = new JLabel("Profil");
        this.txtNom = new JTextField(20);
        this.txtPrenom = new JTextField(20);

        this.panelCompte.add(this.lblProfil);
        this.panelCompte.add(this.txtNom);
        this.panelCompte.add(this.txtPrenom);

        /* Ajout des onglets */
        this.tabbedPane.addTab("Compte", this.panelCompte);
        this.tabbedPane.addTab("Changer le fond", this.panelChangerFond);
        this.tabbedPane.addTab("Sécurité", this.panelSecurite);

        /* Position des composants */
        this.add(this.btnQuitter, BorderLayout.NORTH);
        this.add(this.tabbedPane, BorderLayout.CENTER);

        /* Action des composants */
        this.btnQuitter.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == this.btnQuitter) 
        {
            this.frame.parametre(false);
        }
    }
}
