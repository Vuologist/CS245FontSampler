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
//Description: This program allows a user to input characters into a
//             text box and chose an available font to customize the
//             output text.

public class FontSampler {

    JFrame jfrm;
    JList jlst;
    JScrollPane jscrlp;
    JLabel finalSampleText;
    JTextField input;

    public FontSampler(String val) {
        generateFrame();
        getListOfFontsAndAddToList();
        System.out.println(val);

        if(!val.equals("")) {
            input = new JTextField(val);
            finalSampleText = new JLabel(val);
        } else {
            input = new JTextField();
            finalSampleText = new JLabel("Anthony Vu");
        }

        JPanel topArea = new JPanel(new GridLayout(2,1));
        JLabel sampleTextTop = new JLabel("Sample text: ");
        sampleTextTop.setDisplayedMnemonic('S');
        sampleTextTop.setLabelFor(input);
        topArea.add(sampleTextTop);
        topArea.add(input);

        input.addActionListener((ActionEvent e)-> {
                String text = e.getActionCommand();
                finalSampleText.setText(text);
            }
        );

        JPanel middleArea = new JPanel(new GridLayout(2,1));
        JLabel fonts = new JLabel("Fonts");
        fonts.setDisplayedMnemonic('F');
        fonts.setLabelFor(jlst);
        middleArea.add(fonts);
        middleArea.add(jscrlp);

        jlst.addListSelectionListener((ListSelectionEvent le) -> {
            String font = jlst.getSelectedValue().toString();
            finalSampleText.setFont(new Font(font, Font.PLAIN, 24));
            finalSampleText.setText(input.getText());

        });

        JPanel bottomArea = new JPanel(new FlowLayout());
        //this is where anthony should go
        bottomArea.add(finalSampleText);

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
            StringBuilder sb = new StringBuilder();
            if(args.length > 0){
                for(String s : args)
                    sb.append(s);
            }
            new FontSampler(sb.toString());
        });

    }

}
