import javax.swing.*;
import java.awt.event.ActionListener;

public class SettingsScreen {
    private JPanel rootPanel;
    private JButton changeButton;
    private JRadioButton radioButton;
    private JRadioButton deleteSelectedRadioButton;
    ButtonGroup bg;


    SettingsScreen() {
        bg = new ButtonGroup();
        bg.add(radioButton);
        bg.add(deleteSelectedRadioButton);
    }

    public boolean clearList() {
        return radioButton.isSelected();
    }

    public boolean delSelected() {
        return deleteSelectedRadioButton.isSelected();
    }

    public void clearSelections() {
        bg.clearSelection();
    }

    public void setChangeButton(ActionListener listener) {
        changeButton.addActionListener(listener);
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
