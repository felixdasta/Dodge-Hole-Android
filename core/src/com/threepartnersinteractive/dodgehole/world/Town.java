package com.threepartnersinteractive.dodgehole.world;

import com.threepartnersinteractive.dodgehole.entities.EntityBase;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class Town {

    private String name;
    private int hole_chances; //probability of holes to spawn in the current town
    private final static int DEFAULT_HOLE_CHANCES = 3;
    private List<Town> neighbors;

    //dynamic entities are those entities that move, such as trucks
    private Queue<EntityBase> dynamic_entities_hazards;
    private Queue<Double> dynamic_entities_timing_frequency;

    //dynamic entities are those entities that don't move, such as tries
    private Queue<EntityBase> static_entities_hazards;
    private Queue<Double> static_entities_timing_frequency;

    private Random random;

    public Town(String name){
        this(name, DEFAULT_HOLE_CHANCES);
    }

    public Town(String name, int hole_chances){
        this.name = name;
        this.hole_chances = hole_chances;
        this.dynamic_entities_hazards = new LinkedList<>();
        this.dynamic_entities_timing_frequency = new LinkedList<>();
        this.static_entities_hazards = new LinkedList<>();
        this.static_entities_timing_frequency = new LinkedList<>();
        this.neighbors = new ArrayList<>();
        this.random = new Random();
    }

    public String getName() {
        return name;
    }

    public int getHoleChances() {
        return hole_chances;
    }


    public void addDynamicHazard(EntityBase town_hazard, int lower_sec, int higher_sec) {
        double delta_time;

        if((higher_sec - lower_sec) > 0) delta_time = (higher_sec - lower_sec) * random.nextDouble();
        else delta_time = 0;

        this.dynamic_entities_hazards.add(town_hazard);
        this.dynamic_entities_timing_frequency.add(lower_sec + delta_time);
    }

    /* Spawns the next dynamic hazard in the list */
    public EntityBase spawnDynamicHazard(){
        this.dynamic_entities_timing_frequency.poll();
        return this.dynamic_entities_hazards.poll();
    }

    /* Returns the timing frequency of the next dynamic hazard that will spawn */
    public Double getDynamicHazardTiming(){
        return dynamic_entities_timing_frequency.peek();
    }

    public void addStaticHazard(EntityBase town_hazard, double lower_sec, double higher_sec) {
        double delta_time;

        if((higher_sec - lower_sec) > 0) delta_time = (higher_sec - lower_sec) * random.nextDouble();
        else delta_time = 0;

        this.static_entities_hazards.add(town_hazard);
        this.static_entities_timing_frequency.add(lower_sec + delta_time);
    }

    /*  Retrieves and removes the next static hazard in the list */
    public EntityBase removeStaticHazard(){
        this.static_entities_timing_frequency.poll();
        return this.static_entities_hazards.poll();
    }

    /* Returns the timing frequency of the next static hazard that will spawn */
    public Double getStaticHazardTiming(){
        return static_entities_timing_frequency.peek();
    }

    /* Returns the next static hazard that is about to spawn */
    public EntityBase getStaticHazard(){
        return static_entities_hazards.peek();
    }

    /* Returns the town neighbors */
    public List<Town> getNeighbors() { return neighbors; }

    @Override
    public int hashCode(){
        return name.hashCode();
    }
}
