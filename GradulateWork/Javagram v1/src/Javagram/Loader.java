package Javagram;

import Javagram.AppSettings.Config;
import Javagram.Viev.EnterPhoneNum;
import Javagram.Viev.WindowHandler;

public class Loader {

    public static void main(String[] args) {
        Config.loadProperties();
        WindowHandler.frameInitialization();
        new EnterPhoneNum();
//        new EnterConfirmCode();
//        new FinishRegister();
//        new MainScreen();
    }
}
