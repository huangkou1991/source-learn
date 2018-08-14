package com.learn.dozer;

public class DozerSource2 {
    private int id;
    private int points;
    public DozerSource2() {
    }

    public DozerSource2(int id, int points) {
        super();
        this.id = id;
        this.points = points;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Dest2 [id=" + id + ", points=" + points + "]";
    }

}
