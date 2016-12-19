package Game;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Alexander Nyström(dv15anm) on 18/12/2016.
 */
public class HighScoreWindow {

    private JFrame frame;
    private ArrayList<Player> highScores;

    public HighScoreWindow(ArrayList<Player> highScores) {
        this.highScores = sortHighScores(highScores);
        frame = new JFrame("High scores");
        frame.add(new JScrollPane( new JPanel().add(createHighScoreList())));
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(350,760);
        frame.setVisible(true);
    }

    private ArrayList<Player> sortHighScores(ArrayList<Player> highScores) {
        ArrayList<Player> sorted = new ArrayList<Player>();
        while (highScores.size() > 0){
            Player best = null;
            long highest = 0;
            for (Player player: highScores) {
                if (player.getResults().getCreditsLeft() > highest) {
                    highest = player.getResults().getCreditsLeft();
                    best = player;
                }
            }
            sorted.add(best);
            highScores.remove(best);

        }
        return sorted;
    }

    private JLabel createHighScoreList() {
        String outPut = "";
        for (Player player: highScores){
            Results rs = player.getResults();
            outPut = outPut.concat("Name: "+player.getName()+"<br>Time: "+
                    rs.getStringTime()+"<br> Credits: "+rs.getCreditsLeft()+
                    "<br>-------------<br>");
        }
        JLabel list = new JLabel("<html><div " +
                "style=\"margin:25px\"><h1 style=\"text-align:center;\">" +
                "HIGH SCORES</h1>" + "<p style=\"font-size:18\">"+outPut+
                " </p></div></html>");
        return list;
    }


}
