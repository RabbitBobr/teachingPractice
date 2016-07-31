package bobrov.projectBook.exceptions;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Created by Rabbik on 31.07.2016.
 */
public class NotDateException extends Exception {
    public void messageForm(String mess) {
        JOptionPane.showMessageDialog(new JFrame(),
                mess,
                "Ошибка!",
                JOptionPane.WARNING_MESSAGE);
    }
}
