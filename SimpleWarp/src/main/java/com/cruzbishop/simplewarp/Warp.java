package com.cruzbishop.simplewarp;

import com.avaje.ebean.validation.Length;
import com.avaje.ebean.validation.NotEmpty;
import com.avaje.ebean.validation.NotNull;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

@Entity()
@Table(name="sw_warp")
/**
 * A basic class that defines a single warp
 * Used with the database
 * @version 1.0.0
 * @author Cruz Bishop
 */
public class Warp implements Serializable {
    @Id
    /**
     * The ID of this warp.
     */
    private int id;

    @Length(max=30)
    @NotEmpty
    /**
     * The name of this warp
     */
    private String name;

    @NotNull
    /**
     * The X coordinate
     */
    private double x;

    @NotNull
    /**
     * The Y coordinate
     */
    private double y;

    @NotNull
    /**
     * The Z coordinate
     */
    private double z;

    @NotNull
    /**
     * The pitch you warp as
     */
    private float pitch;

    @NotNull
    /**
     * The yaw you warp as
     */
    private float yaw;

    @NotEmpty
    /**
     * The name of the world
     */
    private String worldName;

    /**
     * Sets the ID of this warp
     * @param id The ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets this warp's ID
     * @return The ID
     */
    public int getId() {
        return id;
    }

    /**
     * Gets this warp's name
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets this warp's name
     * @param name The name to use
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the world this warp is in
     * @return The world's name
     */
    public String getWorldName() {
        return worldName;
    }

    /**
     * Sets the world this warp was in
     * @param worldName The world name to use
     */
    public void setWorldName(String worldName) {
        this.worldName = worldName;
    }

    /**
     * Gets the X coordinate
     * @return The X coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * Sets the X coordinate
     * @param x The X coordinate
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Gets the Y coordinate
     * @return The Y coordinate
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the Y coordinate
     * @param y The Y coordinate
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Gets the Z coordinate
     * @return The Z coordinate
     */
    public double getZ() {
        return z;
    }

    /**
     * Sets the Z coordinate
     * @param z The Z coordinate
     */
    public void setZ(double z) {
        this.z = z;
    }

    /**
     * Gets the pitch
     * @return The pitch
     */
    public float getPitch() {
        return pitch;
    }

    /**
     * Sets the pitch
     * @param pitch The pitch
     */
    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    /**
     * Gets the yaw
     * @return The yaw
     */
    public float getYaw() {
        return yaw;
    }

    /**
     * Sets the yaw
     * @param yaw The yaw
     */
    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    /**
     * Sets this warp's location
     * @param location The location
     */
    public void setLocation(Location location) {
        this.worldName = location.getWorld().getName();
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
        this.pitch = location.getPitch();
        this.yaw = location.getYaw();
    }

    /**
     * Gets this warp's location
     * @return The location
     */
    public Location getLocation() {
        World world = Bukkit.getServer().getWorld(worldName);
        return new Location(world, x, y, z, yaw, pitch);
    }
}
