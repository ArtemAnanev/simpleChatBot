package bot;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class SimpleChatBot extends JFrame implements ActionListener {

    final String TITLE_OF_PROGRAM = "Chatter";
    final String BOT_AI = "AI";
    final String ENTER_BTN = "Enter";
    final int START_LOCATION = 200;
    final int WINDOW_HEIGHT = 450;
    final int WINDOW_WIDTH = 350;

    JTextPane dialogue;
    JCheckBox ai;
    JTextField message;
    SimpleBot sbot;
    SimpleAttributeSet botStyle;

    public static void main(String[] args) {
        new SimpleChatBot();
    }

    /*
    Constructor
     */
    SimpleChatBot() {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(START_LOCATION, START_LOCATION, WINDOW_WIDTH, WINDOW_HEIGHT);

        //dialog area
        dialogue = new JTextPane();
        dialogue.setEditable(false);
        dialogue.setContentType("text/html");
        JScrollPane scrollBar = new JScrollPane(dialogue);

        //messages style
        botStyle = new SimpleAttributeSet();
        StyleConstants.setItalic(botStyle, true);
        StyleConstants.setForeground(botStyle, Color.cyan);
        // StyleConstants.setForeground(botStyle, StyleConstants.ALIGN_RIGHT);

        //checkbox,btn,dialog area
        JPanel bp = new JPanel();
        bp.setLayout(new BoxLayout(bp, BoxLayout.X_AXIS));

        JButton enter = new JButton(ENTER_BTN);
        enter.addActionListener(this);

        ai = new JCheckBox(BOT_AI);
        ai.doClick();

        message = new JTextField();
        message.addActionListener(this);

        //add all elements on the window
        bp.add(ai);
        bp.add(message);
        bp.add(enter);
        add(BorderLayout.CENTER, scrollBar);
        add(BorderLayout.SOUTH, bp);
        setVisible(true);
        sbot = new SimpleBot();
    }

    /*
    enterBtn, dialog area listeners
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if (message.getText().trim().length() > 0) {
            try {
                StyledDocument doc = dialogue.getStyledDocument();
                doc.insertString(doc.getLength(), message.getText() + "\n",
                        new SimpleAttributeSet());
                doc.insertString(doc.getLength(),
                        TITLE_OF_PROGRAM.substring(0, 9) +
                                sbot.sayInReturn(message.getText(), ai.isSelected()) + "\n",
                        botStyle);
            } catch (Exception e) {}
            message.setText("");
            message.requestFocusInWindow();
        }
    }
}


