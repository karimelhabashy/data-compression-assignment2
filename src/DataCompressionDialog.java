import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DataCompressionDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonCompress;
    private JButton buttonDecompress;
    private JTextField textField2;
    private JTextField textField1;

    DataCompressionDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonCompress);
        this.setLocation(300, 200);
        this.getContentPane().setPreferredSize(new Dimension(500, 350));


        buttonCompress.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                compress();
            }
        });

        buttonDecompress.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                decompress();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                decompress();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                decompress();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void compress() {
        HashMap<String, Integer> mydictionary = new HashMap<>();

        for (int i = 0; i < 127 ; i++) {
            mydictionary.put("" + (char) i,i);
        }

        File inputfile = new File(textField1.getText());
        String input="";
        try {
            Scanner reader = new Scanner(inputfile);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                input += line;
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Input File Not Found!");
        }

        Integer lastindex =  128;
        String output="<";
        String tempstring = "";

        for (int i=0; i<input.length();i++)
        {
            String added_string = tempstring + input.charAt(i);
            if (mydictionary.containsKey(added_string))
                tempstring = added_string;
            else {
                output+=mydictionary.get(tempstring);
                output+="><";
                mydictionary.put(added_string,lastindex++);
                tempstring = "" + input.charAt(i);
            }
        }
        if (!tempstring.equals("")) {
            output += mydictionary.get(tempstring);
        }
        output+='>';

        File file2 = new File("C:\\Users\\karum\\OneDrive\\Desktop\\Year 3\\Data Compression\\Assignment 2\\LZW\\myoutputfile.txt");
        try{
            FileWriter writer = new FileWriter(file2);
            writer.write(output);
            writer.close();
        }
        catch (IOException E)
        {

        }
        dispose();
    }

    private void decompress() {
        System.out.println("de-compressed!");
        dispose();
    }

    public static void main(String[] args) {
        DataCompressionDialog dialog = new DataCompressionDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
