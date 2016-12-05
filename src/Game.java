import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simon on 2016-11-30.
 */
public class Game extends JPanel {

    private int columnCount = 12;
    private int rowCount = 8;
    private List<Rectangle> cells;

    public Game(){
        cells = new ArrayList<>(columnCount * rowCount);
        repaint();
    }

    @Override
    public void invalidate(){
        cells.clear();
        super.invalidate();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        int width = getWidth();
        int height = getHeight();

        //Set the size of the cell based on window height.
        int cellWidth = (height / rowCount);
        int cellHeight = cellWidth;

        //offset the grid.
        int xOffset = (width - (columnCount*cellWidth))/2;
       // int yOffset = (height - (rowCount*cellHeight))/2;
        //int xOffset = 0;
        int yOffset = 0;

        if (cells.isEmpty()) {
            for (int row = 0; row < rowCount; row++) {
                for (int col = 0; col < columnCount; col++) {
                    Rectangle cell = new Rectangle(
                            xOffset + (col * cellWidth),
                            yOffset + (row * cellHeight),
                            cellWidth,
                            cellHeight);
                    cells.add(cell);
                }
            }
        }
        g2d.setColor(Color.BLUE);
        for (Rectangle cell : cells) {
            g2d.draw(cell);
        }
        g2d.dispose();
    }
}
