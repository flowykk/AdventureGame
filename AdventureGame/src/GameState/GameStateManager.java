package GameState;

import java.util.ArrayList;

public class GameStateManager {

    private ArrayList<GameState> gameStates;
    private int currentState;

    public static final int MENUSTATE = 0;
    public static final int LEVEL1STATE = 1;
    public static final int LEVEL2STATE = 2;
    public static final int LEVEL3STATE = 3;
    public static final int LEVEL4STATE = 4;
    public static final int LEVEL5STATE = 5;
    public static final int LEVEL6STATE = 6;
    public static final int LEVEL7STATE = 7;
    public static final int LEVEL8STATE = 8;
    public static final int LEVEL9STATE = 9;
    public static final int RESTARTSTATE = 10;
    public static final int HELPSTATE = 11;


    public GameStateManager() {

        gameStates = new ArrayList<GameState>();

        currentState = MENUSTATE;
        gameStates.add(new MenuState(this));
        gameStates.add(new Level1State(this));
        gameStates.add(new Level2State(this));
        gameStates.add(new Level3State(this));
        gameStates.add(new Level4State(this));
        gameStates.add(new Level5State(this));
        gameStates.add(new Level6State(this));
        gameStates.add(new Level7State(this));
        gameStates.add(new Level8State(this));
        gameStates.add(new Level9State(this));
        gameStates.add(new RestartState(this));
        gameStates.add(new HelpState(this));

    }

    public void setState(int state) {
        currentState = state;
        gameStates.get(currentState).init();
    }

    public void update() {
        gameStates.get(currentState).update();
    }

    public void draw(java.awt.Graphics2D g) {
        gameStates.get(currentState).draw(g);
    }

    public void keyPressed(int k) {
        gameStates.get(currentState).keyPressed(k);
    }

    public void keyReleased(int k) {
        gameStates.get(currentState).keyReleased(k);
    }

}
