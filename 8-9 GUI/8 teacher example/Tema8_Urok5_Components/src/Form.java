import javax.swing.*;

public class Form extends JPanel{
    private JPanel rootPanel;
    private JPanel labelPanel;
    private JPanel textPanel;

    {
        rootPanel.setLayout(new BoxLayout(rootPanel,BoxLayout.LINE_AXIS));
        labelPanel.add(new JLabel("Фамилия")).setPreferredSize(labelPanel.getPreferredSize());
        labelPanel.add(new JLabel("Имя")).setPreferredSize(labelPanel.getPreferredSize());
        labelPanel.add(new JLabel("Отчество")).setPreferredSize(labelPanel.getPreferredSize());
        textPanel.add(new JTextField()).setPreferredSize(textPanel.getPreferredSize());
        textPanel.add(new JTextField()).setPreferredSize(textPanel.getPreferredSize());
        textPanel.add(new JTextField()).setPreferredSize(textPanel.getPreferredSize());

    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        rootPanel=this;
    }
}
