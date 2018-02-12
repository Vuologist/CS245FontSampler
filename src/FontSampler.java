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
            finalSampleText = new JLabel(val, JLabel.CENTER);
        } else {
            input = new JTextField();
            finalSampleText = new JLabel("Anthony Vu", JLabel.CENTER);
        }

        JPanel topArea = new JPanel();
        topArea.setLayout(new BoxLayout(topArea, BoxLayout.Y_AXIS));
        JLabel sampleTextTop = new JLabel("Sample text: ");
        sampleTextTop.setDisplayedMnemonic('S');
        sampleTextTop.setLabelFor(input);
        topArea.add(sampleTextTop);
        topArea.add(input);
        sampleTextTop.setAlignmentX(Component.LEFT_ALIGNMENT);

        input.addActionListener((ActionEvent e)-> {
                String text = e.getActionCommand();
                finalSampleText.setText(text);
            }
        );

        JPanel middleArea = new JPanel();
        middleArea.setLayout(new BoxLayout(middleArea, BoxLayout.Y_AXIS));
        JLabel fonts = new JLabel("Fonts: ");
        fonts.setDisplayedMnemonic('F');
        fonts.setLabelFor(jlst);
        middleArea.add(fonts);
        middleArea.add(jscrlp);
        fonts.setAlignmentX(Component.LEFT_ALIGNMENT);
        //fonts.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,20,0,0));

        jlst.addListSelectionListener((ListSelectionEvent le) -> {
            String font = jlst.getSelectedValue().toString();
            finalSampleText.setFont(new Font(font, Font.PLAIN, 24));
            finalSampleText.setText(input.getText());

        });

        JPanel bottomArea = new JPanel(new FlowLayout());
        //this is where anthony should go
        bottomArea.add(finalSampleText);
        bottomArea.setBackground(Color.lightGray);

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
        jscrlp.setPreferredSize(new Dimension(300,400));

    }

    public void generateFrame() {
        jfrm = new JFrame("Font Sampler");
        BoxLayout boxLayout = new BoxLayout(jfrm.getContentPane(), BoxLayout.Y_AXIS);
        jfrm.setLayout(boxLayout);
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
