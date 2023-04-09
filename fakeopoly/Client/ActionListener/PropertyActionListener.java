package Client.ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Client.UI.GameView;

public class PropertyActionListener implements ActionListener {
    // Id of the property that is pressed
    private int id;
    private GameView gameView;

    public PropertyActionListener(int id, GameView gameView) {
        this.id = id;
        this.gameView = gameView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameView.createPropertyModalInfo(id);
    }

}
