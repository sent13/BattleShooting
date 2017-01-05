package com.plplsent.battleshooting.Game.Entity.Players;

import com.plplsent.battleshooting.Game.Entity.TeamGroup.TeamGroup;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class PlayerGroupTest {

        PlayerGroup group;
        @Before
        public void setUp() throws Exception {
            group=new PlayerGroup();
        }



        @Test
        public void getAll＿MEとgetAll＿ENEMYでそれぞれ違うオブジェクトを取得できる() throws Exception {
            assertThat(group.get(TeamGroup.Team.ME),not(group.get(TeamGroup.Team.ENEMY)));
        }
    }
