import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;

public class PTextField extends JTextField {

    public PTextField(final String proptText) {
        super(proptText);
        setForeground(Color.gray);
        addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    setText(proptText);
                    setForeground(Color.gray);
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
                if (getText().equals(proptText)) {
                    setText("");
                    setForeground(Color.BLACK);
                }
            }
        });
    }
}