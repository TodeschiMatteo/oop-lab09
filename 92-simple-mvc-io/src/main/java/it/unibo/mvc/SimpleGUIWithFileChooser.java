package it.unibo.mvc;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JFileChooser;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private final int sw = (int) screen.getWidth();
    private final int sh = (int) screen.getHeight();

    private static final String TITLE = "My first Java graphical interface";
    private static final int PROPORTION = 5;
    private final JFrame frame = new JFrame(TITLE);

    /**
     * Define the GUI.
     */
    public SimpleGUIWithFileChooser() {
        final Controller controller = new Controller();

        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        final JPanel upperBar = new JPanel();
        upperBar.setLayout(new BorderLayout());
        final JTextField pathBar = new JTextField(controller.getCurrentFilePath());
        final JButton browse = new JButton("Browse...");
        upperBar.add(pathBar, BorderLayout.CENTER);
        upperBar.add(browse, BorderLayout.EAST);
        canvas.add(upperBar, BorderLayout.NORTH);
        final JTextArea textArea = new JTextArea(sw / PROPORTION, sh / PROPORTION);
        canvas.add(textArea, BorderLayout.CENTER);
        final JButton save = new JButton("Save");
        canvas.add(save, BorderLayout.SOUTH);
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final JFileChooser chooser = new JFileChooser(System.getProperty("user.home"));
                final int result = chooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    final File newFile = chooser.getSelectedFile();
                    controller.setCurrentFile(newFile);
                    pathBar.setText(controller.getCurrentFilePath());
                } else if (result != JFileChooser.CANCEL_OPTION) {
                    JOptionPane.showMessageDialog(frame, "Error occured during file selection");
                }
            }
        });
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    controller.saveStringInFile(textArea.getText());
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(frame, e1, "Error", JOptionPane.ERROR_MESSAGE);
                    e1.printStackTrace(); // NOPMD suppressed as it is a false positive
                }
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
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this
         * flag makes the OS window manager take care of the default positioning
         * on screen. Results may vary, but it is generally the best choice.
         */
        frame.setLocationByPlatform(true);
        /*
         * Resize the frame to minimum size
         */
        //frame.pack();
        /*
         * OK, ready to pull the frame onscreen
         */
        frame.setVisible(true);
    }

    /**
     * Launches the application.
     *
     * @param args
     *            ignored
     */
    public static void main(final String... args) {
        new SimpleGUIWithFileChooser().display();
    }

}
