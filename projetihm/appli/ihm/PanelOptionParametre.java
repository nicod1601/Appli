package appli.ihm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import appli.Controleur;
import appli.metier.Fond;

public class PanelOptionParametre extends JPanel implements ActionListener, ItemListener
{
    private JTabbedPane tabbedPane;
    private JPanel panelChangerFond;
    private JPanel panelSecurite;
    private JPanel panelOptionFond;

    private JButton btnQuitter;

    /* Partie Compte */
    private JPanel panelCompte;
    private JLabel lblProfil;
    private JTextField txtNom;
    private JTextField txtPrenom;

    private JPanel panelBoutonCompte;
    private JButton btnImporter;


    private JRadioButton[] tabRadio;
    private ButtonGroup groupe;

    /* Partie Fond */
    private Fond fond;
    private JButton[][] tabButtonFond;

    private FrameAppli frame;
    private Controleur ctrl;

    public PanelOptionParametre(FrameAppli frame, Controleur ctrl)
    {
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(30, 30, 30));

        /*--------------------------*/
        /* Création des composants  */
        /*--------------------------*/
        this.frame = frame;
        this.ctrl = ctrl;

        this.tabbedPane = new JTabbedPane();
        this.panelCompte = new JPanel();
        this.panelChangerFond = new JPanel();
        this.panelSecurite = new JPanel();

        this.btnQuitter = new JButton("Quitter");

        /* Panel Compte */
        this.panelCompte.setLayout(new GridLayout(2, 3, 5, 5));
        this.lblProfil = new JLabel(" ");
        this.txtNom = new JTextField(20);
        this.txtPrenom = new JTextField(20);

        this.panelBoutonCompte = new JPanel();
        this.btnImporter = new JButton("Importer");
        this.panelBoutonCompte.add(this.btnImporter);

        this.panelCompte.add(this.lblProfil);
        this.panelCompte.add(this.txtNom);
        this.panelCompte.add(this.panelBoutonCompte);
        this.panelCompte.add(this.txtPrenom);

        /* Panel Fond */
        this.fond = new Fond();
        this.panelChangerFond.setLayout(new GridLayout( this.fond.getLigne(), this.fond.getColonne(),5,5));
        System.out.println(this.fond.getLigne() + " " + this.fond.getColonne());
        this.tabButtonFond = new JButton[this.fond.getLigne()][this.fond.getColonne()];

        for(int cpt=0; cpt<this.fond.getLigne(); cpt++)
        {
            for(int cpt2=0; cpt2<this.fond.getColonne(); cpt2++)
            {
                this.tabButtonFond[cpt][cpt2] = new JButton();
                this.tabButtonFond[cpt][cpt2].setBackground(this.fond.getCouleur(cpt, cpt2));
                this.panelChangerFond.add(this.tabButtonFond[cpt][cpt2]);
            }
        }

        this.panelOptionFond = new JPanel();

        this.tabRadio = new JRadioButton[3];
        this.tabRadio[0] = new JRadioButton("Fond d'écrans"  );
        this.tabRadio[1] = new JRadioButton("Bordure Boutons");
        this.tabRadio[2] = new JRadioButton("Ecriture Boutons");
        
        this.groupe = new ButtonGroup();
        this.groupe.add(this.tabRadio[0]);
        this.groupe.add(this.tabRadio[1]);
        this.groupe.add(this.tabRadio[2]);

        this.panelOptionFond.add(this.tabRadio[0]);
        this.panelOptionFond.add(this.tabRadio[1]);
        this.panelOptionFond.add(this.tabRadio[2]);

        this.tabbedPane.addChangeListener(e -> {
            if (this.tabbedPane.getSelectedComponent() == this.panelChangerFond) {
                this.panelOptionFond.setVisible(true);
            } else {
                this.panelOptionFond.setVisible(false);
            }
        });

        

        /* Ajout des onglets */
        this.tabbedPane.addTab("Compte", this.panelCompte);
        this.tabbedPane.addTab("Changer le fond", this.panelChangerFond);
        this.tabbedPane.addTab("Sécurité", this.panelSecurite);


        /*--------------------------*/
        /* Position des composants  */
        /*--------------------------*/

        /* Position des composants */
        this.add(this.btnQuitter     , BorderLayout.NORTH );
        this.add(this.tabbedPane     , BorderLayout.CENTER);
        this.add(this.panelOptionFond, BorderLayout.SOUTH );

        /*--------------------------*/
        /* Activation des composants*/
        /*--------------------------*/

        // ActionListener
        this.btnQuitter.addActionListener(this);
        
        for(int cpt=0; cpt<this.fond.getLigne(); cpt++)
        {
            for(int cpt2=0; cpt2<this.fond.getColonne(); cpt2++)
            {
                this.tabButtonFond[cpt][cpt2].addActionListener(this);
            }
        }

        this.btnImporter.addActionListener(this);
        this.txtNom.addActionListener(this);
        this.txtPrenom.addActionListener(this);

        //ItemListener
        for(int cpt=0; cpt<this.tabRadio.length; cpt++)
        {
            this.tabRadio[cpt].addItemListener(this);
        }

    }

    public void itemStateChanged(ItemEvent e)
    {
        if(e.getSource() == this.tabRadio[0])
        {
            this.changerFond();
        }

        if(e.getSource() == this.tabRadio[1])
        {    
            this.changerFondBouton();
        }

        if(e.getSource() == this.tabRadio[2])
        {    
            this.changerEcritureBouton();
        }
    }

    public boolean changerFond()
    {
        return this.tabRadio[0].isSelected();
    }

    public boolean changerFondBouton()
    {
        return this.tabRadio[1].isSelected();
    }

    public boolean changerEcritureBouton()
    {
        return this.tabRadio[2].isSelected();
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == this.btnQuitter) 
        {
            this.frame.parametre(false);
        }

        for(int cpt=0; cpt<this.fond.getLigne(); cpt++)
        {
            for(int cpt2=0; cpt2<this.fond.getColonne(); cpt2++)
            {
                if(e.getSource() == this.tabButtonFond[cpt][cpt2])
                {
                    if(this.changerFond())
                    {
                        System.out.println("fond");
                        this.frame.setFond(this.tabButtonFond[cpt][cpt2].getBackground());
                    }
                    
                    if(this.changerFondBouton())
                    {
                        System.out.println("bouton");
                        this.frame.setFondBouton(this.tabButtonFond[cpt][cpt2].getBackground());
                    }

                    if(this.changerEcritureBouton())
                    {
                        System.out.println("ecriture");
                        this.frame.setEcritureBouton(this.tabButtonFond[cpt][cpt2].getBackground());
                    }
                }
            }
        }

        if(e.getSource() == this.btnImporter)
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int returnValue = fileChooser.showOpenDialog(this);
            
            if (returnValue == JFileChooser.APPROVE_OPTION) 
            {
                File file = fileChooser.getSelectedFile();
                try 
                {
                    BufferedImage image = ImageIO.read(file);
                    ImageIcon icon = new ImageIcon(image.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                    this.lblProfil.setIcon(icon);

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Erreur lors du chargement de l'image", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public void setProfile(String nom, String prenom)
    {
        this.txtNom.setText(nom);
        this.txtNom.setEnabled(false);
        this.txtPrenom.setText(prenom);
        this.txtPrenom.setEnabled(false);
    }
}
