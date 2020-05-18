package Javagram.AppSettings;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {

    public static String phoneMask;
    public static String productionHostAddress;
    public static String appId;
    public static String appHash;

    public static BufferedImage background;
    public static BufferedImage buttonBackground;
    public static BufferedImage buttonSend;
    public static BufferedImage back;
    public static BufferedImage close;
    public static BufferedImage edit;
    public static BufferedImage hide;
    public static BufferedImage lock;
    public static BufferedImage phone;
    public static BufferedImage plus;
    public static BufferedImage search;
    public static BufferedImage settings;
    public static BufferedImage trash;
    public static BufferedImage logoMicro;
    public static BufferedImage logoMini;
    public static BufferedImage logo;
    public static BufferedImage maskBlueMini;
    public static BufferedImage maskDarkGrayBig;
    public static BufferedImage maskGrayOnline;
    public static BufferedImage maskGray;
    public static BufferedImage maskWhiteMini;
    public static BufferedImage maskWhiteOnline;
    public static BufferedImage maskWhite;
    public static BufferedImage messageInBottom;
    public static BufferedImage messageInLeft;
    public static BufferedImage messageInTop;
    public static BufferedImage messageOutBottom;
    public static BufferedImage messageOutRight;
    public static BufferedImage messageOutTop;
    public static BufferedImage standartPhoto;


    public static void loadProperties() {
        try {

            background = ImageIO.read(new File("./res/GUI Components/background.png"));
            buttonBackground = ImageIO.read(new File("./res/GUI Components/button-background.png"));
            buttonSend = ImageIO.read(new File("./res/GUI Components/button-send.png"));
            back = ImageIO.read(new File("./res/GUI Components/icon-back.png"));
            close = ImageIO.read(new File("./res/GUI Components/icon-close.png"));
            edit = ImageIO.read(new File("./res/GUI Components/icon-edit.png"));
            hide = ImageIO.read(new File("./res/GUI Components/icon-hide.png"));
            lock = ImageIO.read(new File("./res/GUI Components/icon-lock.png"));
            phone = ImageIO.read(new File("./res/GUI Components/icon-phone.png"));
            plus = ImageIO.read(new File("./res/GUI Components/icon-plus.png"));
            search = ImageIO.read(new File("./res/GUI Components/icon-search.png"));
            settings = ImageIO.read(new File("./res/GUI Components/icon-settings.png"));
            trash = ImageIO.read(new File("./res/GUI Components/icon-trash.png"));
            logoMicro = ImageIO.read(new File("./res/GUI Components/logo-micro.png"));
            logoMini = ImageIO.read(new File("./res/GUI Components/logo-mini.png"));
            logo = ImageIO.read(new File("./res/GUI Components/logo.png"));
            maskBlueMini = ImageIO.read(new File("./res/GUI Components/mask-blue-mini.png"));
            maskDarkGrayBig = ImageIO.read(new File("./res/GUI Components/mask-dark-gray-big.png"));
            maskGrayOnline = ImageIO.read(new File("./res/GUI Components/mask-gray-online.png"));
            maskGray = ImageIO.read(new File("./res/GUI Components/mask-gray.png"));
            maskWhiteMini = ImageIO.read(new File("./res/GUI Components/mask-white-mini.png"));
            maskWhiteOnline = ImageIO.read(new File("./res/GUI Components/mask-white-online.png"));
            maskWhite = ImageIO.read(new File("./res/GUI Components/mask-white.png"));
            messageInBottom = ImageIO.read(new File("./res/GUI Components/message-in-bottom.png"));
            messageInLeft = ImageIO.read(new File("./res/GUI Components/message-in-left.png"));
            messageInTop = ImageIO.read(new File("./res/GUI Components/message-in-top.png"));
            messageOutBottom = ImageIO.read(new File("./res/GUI Components/message-out-bottom.png"));
            messageOutRight = ImageIO.read(new File("./res/GUI Components/message-out-right.png"));
            messageOutTop = ImageIO.read(new File("./res/GUI Components/message-out-top.png"));
            standartPhoto = ImageIO.read(new File("./res/GUI Components/standartPhoto.png"));





            Properties props = new Properties();
            props.load(new FileInputStream(new File("./src/Javagram/AppSettings/app.properties")));
            phoneMask = props.getProperty("PHONE_MASK");
            productionHostAddress = props.getProperty("PRODUCTION_HOST_ADDRESS");
            appId = props.getProperty("APP_ID");
            appHash = props.getProperty("APP_HASH");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
