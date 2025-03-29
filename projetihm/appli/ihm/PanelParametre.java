package appli.ihm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PanelParametre extends JPanel implements ActionListener, MouseListener
{
    private JButton btnParametre;
    private JButton btnQuitter;

    private FrameID frameID;
    private FrameAppli frameAppli;

    public PanelParametre(FrameID frameID, FrameAppli frameAppli)
    {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBackground(new Color(30, 30, 30));

        this.frameID = frameID;
        this.frameAppli = frameAppli;

        /*--------------------------*/
        /* Création des composants  */
        /*--------------------------*/
        this.btnParametre = new JButton("Paramétrage");
        this.btnQuitter   = new JButton("Quitter");

        this.styleButton(btnParametre);
        this.styleButton(btnQuitter);

        /*---------------------------*/
        /*   Position des composants */
        /*---------------------------*/
        this.add(this.btnParametre);
        this.add(this.btnQuitter);

        /*-------------------------- */
        /* Activation des composants */
        /*-------------------------- */
        this.btnParametre.addActionListener(this);
        this.btnQuitter.addActionListener(this);

        this.btnParametre.addMouseListener(this);
        this.btnQuitter.addMouseListener(this);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == this.btnParametre)
        {
            System.out.println("Paramétrage appuyé");
            this.frameAppli.parametre();
        }
        else if(e.getSource() == this.btnQuitter)
        {
            this.frameID.setVisible(true);
            this.frameAppli.setVisible(false);

        }
    }

    public void mouseClicked(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}


    public void mouseEntered(MouseEvent e)
    {
        if(e.getComponent() == this.btnParametre || e.getComponent() == this.btnQuitter)
        {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    }

    public void mouseExited(MouseEvent e)
    {
        if(e.getComponent() == this.btnParametre || e.getComponent() == this.btnQuitter)
        {
            this.setCursor(Cursor.getDefaultCursor());
        }
    }


    private void styleButton(JButton button)
    {
        button.setBackground(new Color(70, 70, 70));
        button.setForeground(Color.YELLOW);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
        button.setFocusPainted(false);
    }
}
