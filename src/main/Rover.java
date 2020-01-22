package main;

public class Rover {

    private String direction;
    //position for rove
    private int y;
    private int x;
    //size of map
    private int sizeX = 0;
    private int sizeY = 0;
    private int[][] map;
    //roadblock state
    boolean roadblock = false;

    public Rover(String direction, int y, int x, int[][] map) {
        this.direction = direction;
        this.y = y;
        this.x = x;
        this.map = map;
    }

    public void execute(char aux)
    {
        int position =0;
        aux = Character.toUpperCase(aux);
        if (aux == 'L' || aux == 'R')
        {
            turn(aux);
            return;
        }
        else if (aux == 'B')
        {
            position = -1;
        }
        else if(aux == 'F')
        {
            position = 1;
        }
        //If is facing to S or W, the step is in reverse
        if ("SW".contains(getDirection()))
        {
            position = position * (-1);
        }

        if ("NS".contains(getDirection())){
            if (!isRoadBlock(getX(),(getY()+position),map))
            {
                y += position;
                y = isOutOfMap(y,map.length);
            }

        }
        if ("EW".contains(getDirection())){
            if (!isRoadBlock((getX()+position),getY(),map))
            {
                x += position;
                x = isOutOfMap(x,map[0].length);
            }

        }

    }

    /*
    Method for turn the rove
     */
    public void turn(char rightOrLeft) {
        rightOrLeft = Character.toUpperCase(rightOrLeft);
        if (rightOrLeft == 'R')
        {
            if ("N".equals(direction)){
                setDirection("E");
                return;
            }
            if ("E".equals(direction)){
                setDirection("S");
                    return;
            }
            if ("S".equals(direction)){
                setDirection("W");
                return;
            }
            if ("W".equals(direction)){
                setDirection("N");
                return;
            }
        }
        else if(rightOrLeft=='L')
        {
            if ("N".equals(direction)){
                setDirection("W");
                return;
            }
            if ("W".equals(direction)){
                setDirection("S");
                return;
            }
            if ("S".equals(direction)){
                setDirection("E");
                return;
            }
            if ("E".equals(direction)){
                setDirection("N");
                return;
            }
        }
    }

    /*
    Method to check if the position is out of map
     */
    public int isOutOfMap(int position,int max){
        if (position <0)
            return max;
        if (position >max)
            return 0;
        return position;

    }

    /*
    Check if exist a roadblock
     */
    public boolean isRoadBlock(int rX, int rY, int[][] map){
        for (int i=0;i<map.length;i++){
            for (int e=0;e<map[i].length;e++){
                if (rY== i && rX == e && map[i][e]==1){
                    roadblock=true;
                    return true;
                }
            }
        }
        return false;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction)
    {
     this.direction = direction;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public String getFinalPosition(){
        return getY() +":"+ getX() + ":"+getDirection();
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public boolean getRoadblock(){
        return roadblock;
    }

}
