package Javagram.Viev;

import Javagram.interfaces.MainInterface;

import javax.swing.*;
import java.awt.*;

public class DialogCellRendener implements ListCellRenderer<MainInterface.IDialogs> {


    @Override
    public Component getListCellRendererComponent(JList<? extends MainInterface.IDialogs> jList, MainInterface.IDialogs iDialogs, int i, boolean b, boolean b1) {

        if (b) {
            iDialogs.setUISelection();
        } else {
            iDialogs.removeUISelection();
        }
        return iDialogs.getRootPanel();
    }
}
