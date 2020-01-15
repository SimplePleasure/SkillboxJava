package UI.Form;

import javax.swing.*;
import java.awt.*;


public class UiHandler {
    private final int FRAME_WIDTH = 900; //длина X окна
    private final int FRAME_HEIGHT = 600; //длина Y окна
    private JFrame frame;
    private MainForm mainForm;

    public UiHandler() {

        frame = new JFrame("Large File Reader");
    }

    public void InitUI() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        //UI Manager tuner
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        //Run UI in Thread


        SwingUtilities.invokeLater(() -> {
            mainForm = new MainForm();
            frame.setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
            frame.setResizable(false);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setContentPane(mainForm.getRootPanel());
            frame.setLocationRelativeTo(null); //выравниваем окно по центру экрана
            frame.setVisible(true);

            mainForm.getBtnSelectFile().requestFocus();
        });
    }


}
