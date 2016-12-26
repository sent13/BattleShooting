package com.plplsent.battleshooting.Game.Entity;


import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class TeamGroup<E extends Entity> {

    public enum Team {
        ME,ENEMY
    }
    private Map<Team,List<E>> groups;
    public TeamGroup() {
        groups = new EnumMap<Team,List<E>>(Team.class){{
            put(Team.ME,new LinkedList<E>());
            put(Team.ENEMY,new LinkedList<E>());
        }};
    }
    protected void addElement(Team team,E element){
        groups.get(team).add(element);
    }


    public List<E> getAll(Team team){
        return Collections.unmodifiableList(groups.get(team));
    }

    public void update(){
        for (E element : groups.get(TeamGroup.Team.ME)) {
            element.update();
        }
        for (E element : groups.get(TeamGroup.Team.ENEMY)) {
            element.update();
        }
    }
}
