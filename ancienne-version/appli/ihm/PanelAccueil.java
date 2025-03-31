package appli.ihm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class PanelAccueil extends JPanel implements ActionListener, MouseListener
{
    private JButton[] tabButton;
    private String[] tabImages;
    private String[] tabNom;

    public PanelAccueil() {
        this.setLayout(new GridLayout(3, 3, 2, 2));
        this.setBackground(new Color(30, 30, 30));

        /*--------------------------*/
        /* Création des composants  */
        /*--------------------------*/
        this.tabImages = new String[]{"./img/competence.png", "./img/stratageme.png", "./img/logoPasse.png", "./img/stratageme.png"};
        this.tabNom = new String[]{"Compétence", "Stratagème", "Logo", "Stratagème"};

        this.tabButton = new JButton[this.tabImages.length];
        for (int cpt = 0; cpt < this.tabButton.length; cpt++)
        {
            ImageIcon icon = new ImageIcon(getClass().getResource(this.tabImages[cpt]));
            Image img = icon.getImage();
            Image newimg = img.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newimg);

            this.tabButton[cpt] = new JButton(this.tabNom[cpt], icon);
            this.tabButton[cpt].setContentAreaFilled(false);
            this.tabButton[cpt].setBorderPainted(false);
            this.tabButton[cpt].setOpaque(false);
            this.tabButton[cpt].setVerticalTextPosition(SwingConstants.BOTTOM);
            this.tabButton[cpt].setHorizontalTextPosition(SwingConstants.CENTER);
            this.tabButton[cpt].setForeground(Color.WHITE);
            this.tabButton[cpt].setFont(new Font("Arial", Font.BOLD, 12));
            this.tabButton[cpt].setBorder(new LineBorder(Color.YELLOW, 2));
        }

        /*---------------------------*/
        /*   Position des composants */
        /*---------------------------*/
        for (JButton button : this.tabButton)
        {
            this.add(button);
        }

        /*--------------------------- */
        /*   Action des composants    */
        /*--------------------------- */
        for (JButton button : this.tabButton) 
        {
            button.addActionListener(this);
            button.addMouseListener(this);
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        for (int cpt = 0; cpt < this.tabButton.length; cpt++)
        {
            if (e.getSource() == this.tabButton[cpt])
            {
                System.out.println("Bouton " + tabNom[cpt] + " appuyé");
            }
        }
    }

    public void mouseClicked(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e)
    {
        for (int cpt = 0; cpt < this.tabButton.length; cpt++)
        {
            if (e.getSource() == this.tabButton[cpt])
            {
                this.tabButton[cpt].setBorderPainted(true);
            }
        }
    }

    public void mouseExited(MouseEvent e)
    {
        for (int cpt = 0; cpt < this.tabButton.length; cpt++) 
        {
            if (e.getSource() == this.tabButton[cpt]) 
            {
                this.tabButton[cpt].setBorderPainted(false);
            }
        }
    }
}
