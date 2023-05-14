package com.github.Laefye.Minecraft2D.types;

import java.util.Objects;

public class Location {
    private double x = 0;
    private double y = 0;

    public Location() {

    }

    public Location(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getBlockX() {
        return (int) Math.floor(x);
    }

    public int getBlockY() {
        return (int) Math.floor(y);
    }

    public Location toBlock() {
        return new Location(getBlockX(), getBlockY());
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Location location)) {
            return false;
        }
        return this.getX() == location.getX() && this.getY() == location.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public Location add(double x, double y) {
        return new Location(this.getX() + x, this.getY() + y);
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
