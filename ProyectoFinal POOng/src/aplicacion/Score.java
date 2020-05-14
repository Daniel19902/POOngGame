package aplicacion;

public class Score {

    private int score;
    private int xPos ;
    private int yPos ;
    private int fortaleza;

    public Score(int xPos, int yPos){
        score = 0;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void setScore(int score){
        System.out.println(this.score);
        this.score += score;
    }


    public int getScore(){
        return score;
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }
}
