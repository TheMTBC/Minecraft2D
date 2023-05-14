package com.github.Laefye.Minecraft2D.game;

import com.github.Laefye.Minecraft2D.frames.ViewEngine;
import com.github.Laefye.Minecraft2D.types.Location;

public class Camera {
    private Location location;
    private ViewEngine viewEngine;

    public Camera(Location location, ViewEngine viewEngine) {
        this.location = location;
        this.viewEngine = viewEngine;
    }

    public Location getLocation() {
        return location;
    }

    public Location getOffset() {
        return location.add(0, -255);
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
