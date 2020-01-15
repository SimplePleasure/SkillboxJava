package UI.Form;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Formatter;

import File.*;

public class MainForm extends JFrame {
    private JPanel rootPanel;
    private JButton btnSelectFile;
    private JLabel lblFilePath;
    private JTextArea txtFileView;
    private JButton ForwardToBeginFile;
    private JButton btnForwardToEndFile;
    private JButton btnPrev_100;
    private JButton btnForward_100;
    private JButton btnPrev_10;
    private JButton btnForward_10;
    private JScrollBar scrBarTextView;
    private JLabel lblFileInfo;
    private JTextPane lblDescTextPane;

    private ReaderLargeFiles reader;

    private boolean isScrollBarMustChange = true;
    private int scrollBarLastValue = 0;

    public MainForm() {
        btnSelectFile.addActionListener(e -> {

            File inputFile = FileChooser.OpenFile();

            if (inputFile != null) {
                lblFilePath.setText(inputFile.getPath());
                try {
                    reader = new ReaderLargeFiles(inputFile);
                    setScrollMaxValue();
                    reader.movePointer(0);
                    setTextAreaReadLines(0);

                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }


        });

        //go forward
        ForwardToBeginFile.addActionListener(e -> {
            reader.movePointer(0);
            setTextAreaReadLines(0);
        });
        btnForward_100.addActionListener(e -> setTextAreaReadLines(100));
        btnForward_10.addActionListener(e -> setTextAreaReadLines(10));

        //go back
        btnForwardToEndFile.addActionListener(e -> {
            reader.movePointer(scrBarTextView.getMaximum());
            setTextAreaReadLines(0);
        });
        btnPrev_100.addActionListener(e -> setTextAreaReadLines(-100));
        btnPrev_10.addActionListener(e -> setTextAreaReadLines(-10));

        //обработка скроллинга мыши
        txtFileView.addMouseWheelListener(e -> {
            int linesToScroll = e.getWheelRotation();
            setTextAreaReadLines(linesToScroll);
        });

        //обработка изменения scrollbar
        scrBarTextView.addAdjustmentListener(e -> {
            int diffValue = e.getValue() - scrollBarLastValue;
            if (isScrollBarMustChange) {
                if (diffValue <= 0) {
                    reader.movePointer((long) e.getValue() - 1);
                    setTextAreaReadLines(0);
                } else {
                    reader.movePointer((long) e.getValue());
                    setTextAreaReadLines(1);
                }
                isScrollBarMustChange = false;
                scrBarTextView.setValue(reader.getPointer());
            }
            isScrollBarMustChange = true;
            scrollBarLastValue = scrBarTextView.getValue();
        });

    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public JButton getBtnSelectFile() {
        return btnSelectFile;
    }

    private void setScrollMaxValue() {
        long fileLength = reader.getFileLength();
        //hide scrollBar if file length very large
        if (fileLength > Integer.MAX_VALUE) {
            scrBarTextView.setPreferredSize(new Dimension(0, 0));
        }
        scrBarTextView.setMaximum((int) fileLength);


    }

    private void setStringToTextArea(String str) {
        txtFileView.setText(str);
    }

    private void setTextAreaReadLines(int lines) {
        reader.readFileLines(lines);
        isScrollBarMustChange = false;
        scrBarTextView.setValue(reader.getPointer());
        setStringToTextArea(reader.getResult());
        Formatter f = new Formatter();


        float percent = ((float)reader.getPointer() / (float)reader.getFileLength()) * 100;
        f.format("%3.1f", percent);
        lblFileInfo.setText(reader.getPointer() + "/" + reader.getFileLength() +
                " символов (" + f + "%)");
    }


}
