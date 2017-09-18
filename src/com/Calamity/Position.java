package com.Calamity;

public class Position {
    private int x;
    private int y;
    private double angle_radian;

    public Position(int x, int y, double angle_rad)
    {
        this.x = x;
        this.y = y;
        this.angle_radian = angle_rad;
    }

    public void move(int dx, int dy)
    {
        x += dx;
        y += dy;
    }

    public Position offset(Position other)
    {
        return new Position(x-other.x, y-other.y, 0);
    }

    public int getX() {return x;}
    public int getY() {return y;}

    public int distance_squared(Position other)
    {
        int dx = this.x-other.x;
        int dy = this.y-other.y;
        return dx*dx + dy*dy;
    }

    /**
     * Untested-- probably a trig error here. Should return the viewing angle to other.
     * @param other
     * @return
     */
    public double direction_radian(Position other)
    {
        int offsetX = this.x-other.x;
        int offsetY = this.y - other.y;

        return Math.atan((offsetY/offsetX));
    }

}
