package com.plplsent.battleshooting.Game.Entity.Bullets;

import com.plplsent.battleshooting.BuildConfig;
import com.plplsent.battleshooting.Utils.DPoint;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static com.plplsent.battleshooting.Game.Entity.Bullets.BulletLifeManager.BULLET_SPEED;
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
            bullets = new Bullets(new BulletLifeManager(500, 500));
        }

        @Test
        public void getBulletsで要素がゼロが得られる() throws Exception {
            assertThat(bullets.getBullets().size(), is(0));
        }

    }

    @RunWith(value = RobolectricGradleTestRunner.class)
    @Config(constants = BuildConfig.class, sdk = 21)
    public static class 一つだけcreateした場合 {

        Bullets bullets;
        DPoint startPos = new DPoint(5, 5);

        @Before
        public void setUp() throws Exception {
            bullets = new Bullets(new BulletLifeManager(500, 500));
            bullets.create(startPos);
        }


        @Test
        public void getBulletsで取得できる() throws Exception {
            assertThat(bullets.getBullets().size(), is(1));
        }

        @Test
        public void getBulletで要素が取得できる() {
            assertThat(bullets.getBullets().iterator().next().getPosition(), is(startPos));
        }

        @Test
        public void updateを一回呼び出すと玉の位置が動く() throws Exception {
            bullets.update();
            assertThat(bullets.getBullets().iterator().next().getPosition(),is(startPos.add(BULLET_SPEED)));
        }

        @Test
        public void updateを2回呼び出すと玉の位置が動く() throws Exception {
            bullets.update();
            bullets.update();
            assertThat(bullets.getBullets().iterator().next().getPosition(),is(startPos.add(BULLET_SPEED).add(BULLET_SPEED)));
        }


    }

}