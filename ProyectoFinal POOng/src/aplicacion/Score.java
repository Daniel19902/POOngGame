package aplicacion;

public class Score {

    private  String name;
    private int y;
    private int score;
    private int fortaleza = 175;
    private int distance = 0;
    private int lostFortaleza = 0;

    public Score(int y, String name){
        score = 0;
        this.y = y;
        this.name = name;
    }

    public void setScore(int score){
        this.score += score;
    }

    public void setFortaleza(int fortaleza){
        this.fortaleza += fortaleza;
    }

    public String getName() {
        return name;
    }

    public int getY() {
        return y;
    }

    public void plusDistance(){
        distance++;
        if(distance == 1000){
            fortaleza -= 10;
            lostFortaleza += 10;
            distance = 0;
        }
    }
    public int getScore(){
        return score;
    }

    public int getFortaleza() {
        return fortaleza;
    }

    public int getLostFortaleza() {
        return lostFortaleza;
    }
}
