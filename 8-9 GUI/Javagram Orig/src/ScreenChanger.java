import javax.swing.*;
import java.awt.*;

public class ScreenChanger extends JFrame
{
    {
        CheckIn checkin = new CheckIn();
        setMinimumSize(new Dimension(400, 600));
        setContentPane(checkin.getRootPanel());
    }
}
