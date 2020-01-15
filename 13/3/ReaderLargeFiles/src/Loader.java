import UI.Form.UiHandler;

import javax.swing.*;

public class Loader {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UiHandler ui = new UiHandler();
        ui.InitUI();
    }
}
