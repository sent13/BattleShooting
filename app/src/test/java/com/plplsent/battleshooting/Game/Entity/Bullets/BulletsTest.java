package com.plplsent.battleshooting.Game.Entity.Bullets;

import com.plplsent.battleshooting.BuildConfig;
import com.plplsent.battleshooting.Game.DPoint;
import com.plplsent.battleshooting.Game.Entity.TeamGroup;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
@RunWith(Enclosed.class)
public class BulletsTest {
    @RunWith(RobolectricGradleTestRunner.class)
    @Config(constants = BuildConfig.class, sdk = 21)
    public static class 初期状態 {

        Bullets bullets;
        @Before
        public void setUp() throws Exception {
            bullets = new Bullets();
        }

        @Test
        public void getBulletsTEAMMEで要素がゼロが得られる() throws Exception {
            assertThat(bullets.getBullets(TeamGroup.Team.ME).size(), is(0));
        }

        @Test
        public void getBulletsTEAMENEMYで要素がゼロが得られる() throws Exception {
            assertThat(bullets.getBullets(TeamGroup.Team.ENEMY).size(), is(0));
        }



    }
    @RunWith(Enclosed.class)
    public static class 一つだけcreateした場合 {


        @RunWith(value = RobolectricGradleTestRunner.class)
        @Config(constants = BuildConfig.class, sdk = 21)
        public static class TEAMMEにcreateした場合 {
            Bullets bullets;
            TeamGroup.Team TEAM = TeamGroup.Team.ME;
            DPoint startPos = new DPoint(5, 5);

            @Before
            public void setUp() throws Exception {
                bullets = new Bullets();
                bullets.create(TEAM, startPos);
            }


            @Test
            public void getBulletsで取得できる() throws Exception {
                assertThat(bullets.getBullets(TEAM).get(0).getPosition(), is(startPos));
            }

            @Test
            public void getBullets_ENEMYで要素数ゼロのリストを取得できる(){
                assertThat(bullets.getBullets(TeamGroup.Team.ENEMY).size(),is(0));
            }
        }

        @RunWith(value = RobolectricGradleTestRunner.class)
        @Config(constants = BuildConfig.class, sdk = 21)
        public static class TEAMENEMYにcreateした場合 {
            Bullets bullets;
            TeamGroup.Team TEAM = TeamGroup.Team.ENEMY;
            DPoint startPos = new DPoint(5, 5);

            @Before
            public void setUp() throws Exception {
                bullets = new Bullets();
                bullets.create(TEAM, startPos);
            }


            @Test
            public void getBulletsで取得できる() throws Exception {
                assertThat(bullets.getBullets(TEAM).get(0).getPosition(), is(startPos));
            }

            @Test
            public void getBullets_MEで要素数ゼロのリストを取得できる(){
                assertThat(bullets.getBullets(TeamGroup.Team.ME).size(),is(0));
            }
        }


    }

    @RunWith(Enclosed.class)
    public static class 二つだけcreateした場合 {


        @RunWith(value = RobolectricGradleTestRunner.class)
        @Config(constants = BuildConfig.class, sdk = 21)
        public static class TEAMMEにcreateした場合 {
            Bullets bullets;
            TeamGroup.Team TEAM = TeamGroup.Team.ME;
            DPoint startPos1 = new DPoint(5, 5);
            DPoint startPos2 = new DPoint(10, 10);

            @Before
            public void setUp() throws Exception {
                bullets = new Bullets();
                bullets.create(TEAM, startPos1);
                bullets.create(TEAM, startPos2);
            }


            @Test
            public void getBullets_1で取得できる() throws Exception {
                assertThat(bullets.getBullets(TEAM).get(1).getPosition(), is(startPos2));
            }

            @Test
            public void getBullets_ENEMYで要素数ゼロのリストを取得できる(){
                assertThat(bullets.getBullets(TeamGroup.Team.ENEMY).size(),is(0));
            }
        }

        @RunWith(value = RobolectricGradleTestRunner.class)
        @Config(constants = BuildConfig.class, sdk = 21)
        public static class TEAENEMYにcreateした場合 {
            Bullets bullets;
            TeamGroup.Team TEAM = TeamGroup.Team.ENEMY;
            DPoint startPos1 = new DPoint(5, 5);
            DPoint startPos2 = new DPoint(10, 10);

            @Before
            public void setUp() throws Exception {
                bullets = new Bullets();
                bullets.create(TEAM, startPos1);
                bullets.create(TEAM, startPos2);
            }


            @Test
            public void getBullets_1で取得できる() throws Exception {
                assertThat(bullets.getBullets(TEAM).get(1).getPosition(), is(startPos2));
            }

            @Test
            public void getBullets_ENEMYで要素数ゼロのリストを取得できる(){
                assertThat(bullets.getBullets(TeamGroup.Team.ME).size(),is(0));
            }
        }

        @RunWith(value = RobolectricGradleTestRunner.class)
        @Config(constants = BuildConfig.class, sdk = 21)
        public static class 両方にCreateした場合 {
            Bullets bullets;
            TeamGroup.Team TEAM1 = TeamGroup.Team.ENEMY;
            TeamGroup.Team TEAM2 = TeamGroup.Team.ME;

            DPoint startPos1 = new DPoint(5, 5);
            DPoint startPos2= new DPoint(5, 5);

            @Before
            public void setUp() throws Exception {
                bullets = new Bullets();
                bullets.create(TEAM1, startPos1);
                bullets.create(TEAM2, startPos2);
            }


            @Test
            public void getBullets_TEAM1_0で取得できる() throws Exception {
                assertThat(bullets.getBullets(TEAM1).get(0).getPosition(), is(startPos1));
            }

            @Test
            public void getBullets_TEAM2_0で取得できる() throws Exception {
                assertThat(bullets.getBullets(TEAM2).get(0).getPosition(), is(startPos2));
            }

        }
    }
}