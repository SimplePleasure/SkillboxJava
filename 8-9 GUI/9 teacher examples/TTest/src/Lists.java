

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class Lists extends JFrame {
    private JList<String> list;
    private JComboBox<String> combo;
    private JButton b1,b2;
    private BHandler bh;//��������� ������
    private DefaultListModel listModel;

    public Lists(DefaultListModel initData) {
        super("������������� ������� �������");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.listModel = initData;
        Container c = getContentPane();
        c.setLayout(new FlowLayout(FlowLayout.CENTER,20,40));
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(2,2));
        this.createList();
        p.add(new JScrollPane(list));

        combo = new JComboBox<String>();
        JPanel p2 = new JPanel();
        p2.add(combo);
        p.add(p2);

        bh = new BHandler();
        JPanel p3 = new JPanel();
        b1 = new JButton(">>");
        b1.addActionListener(bh);
        b2 = new JButton("<<");
        b2.addActionListener(bh);
        p3.add(b1);
        JPanel p4 = new JPanel();
        p4.add(b2);
        p4.add(p3);
        p.add(p3);
        p.add(p4);
        c.add(p);
        setSize(400,400);
        this.setVisible(true);

    }

    private void moveFromList() {
        if(list.isSelectionEmpty()) {
            JOptionPane.showMessageDialog(null, "������ ����������");
            return;
        }
        //�������� ������� ��������� ��������� ������
        int i1[] = list.getSelectedIndices();
        List<String> sl = list.getSelectedValuesList();
        for(String s: sl) {
            combo.addItem(s);
        }
        sl.clear();
        combo.setSelectedIndex(combo.getItemCount()-1);//�������� ��������� �������

        //������� �������� �� ������ JList
        for(int i=i1.length;i>0;i--) {
            this.listModel.remove(i1[i-1]);
        }

    }

    private void moveFromComboBox() {
        switch(combo.getSelectedIndex()) {
            case -1:
                JOptionPane.showMessageDialog(null, "������ ����������");
                return;
        }
        this.listModel.addElement(combo.getSelectedItem());
        combo.removeItemAt(combo.getSelectedIndex());


    }

    class BHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==b1) {
                moveFromList();
            }
            if(e.getSource()==b2) {
                moveFromComboBox();
            }

        }
    }

    private void createList() {
        list = new JList<String>(this.listModel);//������� ������ �� ������� ����� ��������� ��� ������
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);//������������� ����� ����������
        list.setVisibleRowCount(4);
        list.setFixedCellWidth(100);
        list.setVisible(true);
    }


}
