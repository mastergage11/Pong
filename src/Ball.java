import java.awt.*;

public class Ball {

    private int x, y;
    private final int diameter = 50;
    private final int SPEED = 6;

    double dx = SPEED, dy = SPEED;
    double MAXANGLE = 5*Math.PI/12; //70 degree
    Board board;

    public Ball(Board board){
        x = 0;
        y = 0;

        //'this' keyword references the object that is executing or calling the this reference
        //the Ball class's board is equal to the perimeter board
        this.board = board;
    }

    public void move(){

        //LEFT AND RIGHT
        if(x <= 0){
            board.setcScore(board.getcScore() + 1);
            dx*=-1;
        }
        if(x + diameter >= board.getWidth()){
            board.setpScore(board.getpScore() + 1);
            dx*=-1;
        }
        if(y <= 0 || y + diameter >= board.getHeight()){
            dy*=-1;
        }

        x+=dx;
        y+=dy;
    }

    public void setPosition(int x, int y){

        this.x = x - diameter/2;
        this.y = y - diameter/2;

    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, diameter, diameter);
    }



    public void checkCollisions(Paddle other){
        if(getBounds().intersects(other.getBounds())){
            if(getBounds().intersects(other.getBounds())){
                double paddleY = other.getBounds().getY();
                double paddleC = other.getBounds().getHeight()/2;
                double bally = y;

                double relativeIntersect = (paddleY + paddleC - bally);
                double normalIntersect = relativeIntersect/paddleC;
                double bounceAngle = MAXANGLE + normalIntersect;

                if(y+(diameter/2)< paddleY){
                    bally = y + diameter;
                }
                else if(y + diameter/2 > paddleY + other.getBounds().getHeight()){
                    bally = y;
                }
                else{
                    bally = y+ diameter;

                    if(x < board.getWidth()/2){
                        dx*=-1;
                    }
                    if(x > (board.getWidth()/2)){
                        dx*=-1;
                    }
                }
            }
        }
    }

    public void paint(Graphics g){
        g.fillOval(x, y, diameter, diameter);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDiam(){
        return diameter;
    }
}
