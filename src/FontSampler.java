import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.GraphicsEnvironment;

//
//Name:       Vu, Anthony
//Homework:   2
//Course:     CS-245-01-w18
//
//Description:
//

public class FontSampler {

    JFrame jfrm;
    JList jlst;
    JLabel jlab;
    JScrollPane jscrlp;

    public FontSampler() {
        generateFrame();
        getListOfFontsAndAddToList();


        JPanel topArea = new JPanel(new GridLayout(2,1));
        JLabel sampleTextTop = new JLabel("Sample text: ");
        JTextField input = new JTextField();
        topArea.add(sampleTextTop);
        topArea.add(input);

        JPanel middleArea = new JPanel(new GridLayout(2,1));
        JLabel fonts = new JLabel("Fonts");
        middleArea.add(fonts);
        middleArea.add(jscrlp);

        JPanel bottomArea = new JPanel(new FlowLayout());
        JLabel finalSampleText = new JLabel("Anthony Vu");


        jfrm.add(topArea);
        jfrm.add(middleArea);
        jfrm.add(bottomArea);


    }

    public void getListOfFontsAndAddToList(){
        String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        DefaultListModel lm = new DefaultListModel();

        for(int i = 0; i < fonts.length; i++)
            lm.addElement(fonts[i]);

        jlst = new JList(lm);
        jscrlp = new JScrollPane(jlst);
        jscrlp.setPreferredSize(new Dimension(200,100));

    }

    public void generateFrame() {
        jfrm = new JFrame("Font Sampler");
        jfrm.setLayout(new GridLayout(3,1));
        jfrm.setSize(300,500);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.setVisible(true);
        jfrm.setLocationRelativeTo(null);
    }

    public static void main(String args[]){
        SwingUtilities.invokeLater(() -> {
            new FontSampler();
        });

    }

}
