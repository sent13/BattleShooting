package com.plplsent.battleshooting.Game.Entity.TeamGroup;


import java.util.EnumMap;
import java.util.Map;

/**
 * Teamそれぞれに存在する要素を一つのグループとして扱う
 * @param <E>　要素型
 */
public abstract class TeamGroup<E extends GroupEntry> {

    public enum Team {
        ME,ENEMY
    }
    private Map<Team,E> groups;
    public TeamGroup(final E entryForMe,final E entryForEnemy) {
        groups = new EnumMap<Team, E>(Team.class){{
            put(Team.ME,entryForMe);
            put(Team.ENEMY,entryForEnemy);
        }};
    }

    public E get(Team team){
        return groups.get(team);
    }

    public void update(){
        for (Map.Entry<Team, E> eEntry : groups.entrySet()) {
            eEntry.getValue().update();
        }
    }
}
