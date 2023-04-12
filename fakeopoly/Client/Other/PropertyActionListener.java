package Client.Other;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Client.UI.GameView;

public class PropertyActionListener implements MouseListener {
    // Id of the property that is pressed
    private int id;
    private GameView gameView;

    public PropertyActionListener(int id, GameView gameView) {
        this.id = id;
        this.gameView = gameView;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        gameView.createPropertyModalInfo(id);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
