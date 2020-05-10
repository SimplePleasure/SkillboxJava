package Javagram.Viev;

import Javagram.TgAPI.TelegramAPI;
import Javagram.interfaces.MainInterface;
import org.javagram.response.object.User;
import org.javagram.response.object.UserContact;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class MainScreen implements MainInterface.IMainScreen {

    private JPanel rootPanel;
    private JList list1;

    public MainScreen() {
        WindowHandler.setContainer(this);
        DefaultListModel<String> listModel = new DefaultListModel<>();
        ArrayList<String> list = new ArrayList<>();


        try {
        TelegramAPI.getContacts().stream().map(User::getFirstName).forEach(list::add);
        listModel.addAll(list);
        list1.setModel(listModel);

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    @Override
    public JPanel getRootPanel() {
        return rootPanel;
    }
}
