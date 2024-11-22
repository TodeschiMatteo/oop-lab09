package it.unibo.mvc;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private static final String TITLE = "My App";
    private static final int PROPORTION = 5;
    private final JFrame frame = new JFrame(TITLE);

    /**
     * Define the GUI.
     */
    public SimpleGUI() {
        final Controller controller = new SimpleController();
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        final JTextField field = new JTextField(null);
        final JTextArea area = new JTextArea();
        final JPanel buttonsBar = new JPanel();
        canvas.setLayout(new BorderLayout());
        final JButton print = new JButton("Print");
        buttonsBar.add(print, BorderLayout.WEST);
        final JButton history = new JButton("Show history");
        buttonsBar.add(history, BorderLayout.EAST);
        canvas.add(field, BorderLayout.NORTH);
        canvas.add(area, BorderLayout.CENTER);
        canvas.add(buttonsBar, BorderLayout.SOUTH);
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final String s = field.getText();
                controller.setNextString(s);
                controller.printString();
                field.setText(null);
            }
        });

        history.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final StringBuilder text = new StringBuilder();
                final String lineSeparator = System.lineSeparator();
                for (final String s : controller.getHistory()) {
                    text.append(s).append(lineSeparator);
                }
                area.setText(text.toString());
            }
        });
    }

    /**
     * Show the GUI.
     */
    private void display() {
        /*
         * Make the frame one fifth the resolution of the screen. This very
         * method is enough for a single screen setup. In case of multiple
         * monitors, the primary is selected. In order to deal coherently with
         * multimonitor setups, other facilities exist (see the Java
         * documentation about this issue). It is MUCH better than manually
         * specify the size of a window in pixel: it takes into account the
         * current resolution.
         */
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this
         * flag makes the OS window manager take care of the default positioning
         * on screen. Results may vary, but it is generally the best choice.
         */
        frame.setLocationByPlatform(true);
        /*
         * OK, ready to pull the frame onscreen
         */
        frame.setVisible(true);
    }

    /**
     * Launch the GUI.
     * @param args
     */
    public static void main(final String... args) {
        new SimpleGUI().display();
    }

}
