package appli.ihm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PanelAccueil extends JPanel implements ActionListener
{
    private JButton[] tabButton;
    private String[] tabImages;
    private String[] tabNom;

    public PanelAccueil() {
        this.setLayout(new GridLayout(3, 3,2,2));
        this.setBackground(new Color(30, 30, 30));

        /*--------------------------*/
        /* Création des composants  */
        /*--------------------------*/
        this.tabImages = new String[]{"./img/competence.png", "./img/stratageme.png", "./img/logoPasse.png", "./img/stratageme.png"};
        this.tabNom    = new String[]{"Competence", "Stratageme"};

        this.tabButton = new JButton[this.tabImages.length];
        for (int cpt = 0; cpt < this.tabButton.length; cpt++) 
        {
            ImageIcon icon = new ImageIcon(getClass().getResource(this.tabImages[cpt]));
            Image img = icon.getImage();
            Image newimg = img.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newimg);
            this.tabButton[cpt] = new JButton(icon);
            this.tabButton[cpt].setContentAreaFilled(false);
            this.tabButton[cpt].setBorderPainted(false);
            this.tabButton[cpt].setOpaque(false);
        }
        

        /*---------------------------*/
        /*   Position des composants */
        /*---------------------------*/
        for (int cpt = 0; cpt < this.tabButton.length; cpt++)
        {
            this.add(this.tabButton[cpt]);
        }

        /*--------------------------- */
        /*   Action des composants    */
        /*--------------------------- */
        for (int cpt = 0; cpt < this.tabButton.length; cpt++)
        {
            this.tabButton[cpt].addActionListener(this);
        }
    }

    public void actionPerformed(ActionEvent e) 
    {
        for(int cpt = 0; cpt < this.tabButton.length; cpt++)
        {
            if(e.getSource() == this.tabButton[cpt])
            {
                System.out.println("Bouton " + cpt + " appuyé");
            }
        }
    }
}
