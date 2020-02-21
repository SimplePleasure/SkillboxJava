import javax.swing.*;
import java.awt.*;

public class Handle extends JFrame {
    private JPanel rootPanel;
    private JButton carbohydrates;
    private JButton proteins;
    private JButton lipids;
    private JButton nucleic;
    private JButton atf;
    private JButton h2o;
    private JButton minerals;


    public Handle() {

        setSize(980, 630);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Химия 10-11 класс. Органические / Неорганические в-ва");
        setLocationRelativeTo(null);
//        setResizable(false);


        JPanel container = new JPanel();
        container.setLayout(new FlowLayout());
        container.add(getRootPanel());
        container.add(new Carbohydrates().getRootPanel());
        setContentPane(container);

        carbohydrates.addActionListener(e -> carbohydrates());
        proteins.addActionListener(e -> proteins());
        lipids.addActionListener(e -> lipids());
        nucleic.addActionListener(e -> nucleic());
        atf.addActionListener(e -> atf());
        h2o.addActionListener(e -> h2o());
        minerals.addActionListener(e -> minerals());
    }

    public void carbohydrates() {

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(getRootPanel());
        panel.add(new Carbohydrates().getRootPanel());
        setContentPane(panel);
        getContentPane().revalidate();

    }
    public void proteins () {

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(getRootPanel());
        panel.add(new Proteins().getRootPanel());
        setContentPane(panel);
        getContentPane().revalidate();

    }
    public void lipids() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(getRootPanel());
        panel.add(new Lipids().getRootPanel());
        setContentPane(panel);
        getContentPane().revalidate();


    }
    public void nucleic () {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(getRootPanel());
        panel.add(new Nucleic().getRootPanel());
        setContentPane(panel);
        getContentPane().revalidate();

    }
    public void atf () {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(getRootPanel());
        panel.add(new Atf().getRootPanel());
        setContentPane(panel);
        getContentPane().revalidate();

    }

    public void h2o () {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(getRootPanel());
        panel.add(new H2o().getRootPanel());
        setContentPane(panel);
        getContentPane().revalidate();

    }
    public void minerals() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(getRootPanel());
        panel.add(new Minerals().getRootPanel());
        setContentPane(panel);
        getContentPane().revalidate();

    }


    public JPanel getRootPanel() {
        return rootPanel;
    }

}
