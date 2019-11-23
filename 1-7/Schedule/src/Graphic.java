import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import static java.time.temporal.ChronoUnit.DAYS;

public class Graphic {
    private JPanel rootPanel;

    private JTextField workingLubaDay;
    private JTextField workingIraDay;
    private JButton show;
    private JList result;


    LocalDate workingDayInstanceforLuba;
    LocalDate iraWorkingDay = LocalDate.of(2019, 10,23);



    public Graphic() {
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] date = workingLubaDay.getText().split(" ");
                workingDayInstanceforLuba = LocalDate.of
                        (Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));

                String[] date2 = workingIraDay.getText().split(" ");
                iraWorkingDay = LocalDate.of
                        (Integer.parseInt(date2[0]), Integer.parseInt(date2[1]), Integer.parseInt(date2[2]));
                createSchedule();
            }
        });
    }

    public void createSchedule () {
        ArrayList<LocalDate> restScheduleForLuba = new ArrayList<>();
        final int stage = (int) DAYS.between(workingDayInstanceforLuba, LocalDate.now()) % 4;
        for (int i = stage; i < 20; i++) {
            if (i % 4 != 0) {
                restScheduleForLuba.add(LocalDate.now().plusDays(i - stage));
            }
        }

        ArrayList<LocalDate> scheduleForIra = new ArrayList<>();
        final int stage2 = (int) DAYS.between(iraWorkingDay, LocalDate.now()) % 4;
        for (int i = stage2; i < 20; i++) {
            if (i % 4 == 0 || i % 4 == 1) {
                continue;
            } else {
                scheduleForIra.add(LocalDate.now().plusDays(i - stage2));
            }
        }

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (LocalDate l : restScheduleForLuba) {
            if (scheduleForIra.contains(l)) {
                LocalDate date = LocalDate.parse(l.toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                listModel.addElement(date.format(DateTimeFormatter.ofPattern("EEE,  dd MMMM yyyy")));
            }
        }
        result.setModel(listModel);
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
