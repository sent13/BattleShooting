package com.plplsent.battleshooting.Game.Entity.Bullets;

import com.plplsent.battleshooting.Game.Entity.TeamGroup.TeamGroup;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class BulletGroupTest {
    BulletGroup group;
    @Before
    public void setUp() throws Exception {
        group=new BulletGroup(500,500);
    }

    @Test
    public void getAll＿ENEMYで要素数ゼロのbulletsを取得できる() throws Exception {
        assertTrue(group.get(TeamGroup.Team.ENEMY).getBullets().isEmpty());
    }
    @Test
    public void getAll＿MEで要素数ゼロのbulletsを取得できる() throws Exception {
        assertTrue(group.get(TeamGroup.Team.ME).getBullets().isEmpty());
    }


    @Test
    public void getAll＿MEとgetAll＿ENEMYでそれぞれ違うオブジェクトを取得できる() throws Exception {
        assertThat(group.get(TeamGroup.Team.ME),not(group.get(TeamGroup.Team.ENEMY)));
    }
}