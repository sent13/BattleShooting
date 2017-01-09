package com.plplsent.battleshooting;

import android.util.Log;

public class StopWatch {

    private static long start_          = 0;

    /**
     * 処理時間の計測を開始します。
     */
    public static void start() {
        start_ = System.nanoTime();
    }

    /**
     * 計測開始からの経過ナノ秒を取得します。
     *
     * @return    経過時間
     */
    public static long nanoTime() {
        return System.nanoTime() - start_;
    }

    /**
     * 計測開始からの経過マイクロ秒を取得します。
     *
     * @return    経過時間
     */
    public static long microTime() {
        return (System.nanoTime() - start_) / 1000;
    }

    /**
     * 計測開始からの経過ミリ秒を取得します。
     *
     * @return    経過時間
     */
    public static long milliTime() {
        return (System.nanoTime() - start_) / 1000000;
    }

    /**
     * 計測開始からの経過秒を取得します。
     *
     * @return    経過時間
     */
    public static long secondTime() {
        return (System.nanoTime() - start_) / 1000000000;
    }

    /**
     * 計測開始からの経過ナノ秒を標準出力に出力します。
     */
    public static void printNanoTime() {
        Log.i("Stopwatch",nanoTime()+"");
    }

    /**
     * 指定したメッセージとともに計測開始からの経過ナノ秒を標準出力に出力します。
     *
     * @param msg    メッセージ
     */
    public static void printNanoTime(String msg) {
        Log.i("Stopwatch",msg + nanoTime());
    }

    /**
     * 計測開始からの経過マイクロ秒を標準出力に出力します。
     */
    public static void printMicroTime() {
        Log.i("Stopwatch",microTime()+"");
    }

    /**
     * 指定したメッセージとともに計測開始からの経過マイクロ秒を標準出力に出力します。
     *
     * @param msg    メッセージ
     */
    public static void printMicroTime(String msg) {
        Log.i("Stopwatch",msg + microTime());
    }

    /**
     * 計測開始からの経過ミリ秒を標準出力に出力します。
     */
    public static void printMilliTime() {
        Log.i("Stopwatch",milliTime()+"");
    }

    /**
     * 指定したメッセージとともに計測開始からの経過ミリ秒を標準出力に出力します。
     *
     * @param msg    メッセージ
     */
    public static void printMilliTime(String msg) {
        Log.i("Stopwatch",msg + milliTime());
    }

    /**
     * 計測開始からの経過秒を標準出力に出力します。
     */
    public static void printSecondTime() {
        Log.i("Stopwatch",secondTime()+"");
    }

    /**
     * 指定したメッセージとともに計測開始からの経過秒を標準出力に出力します。
     *
     * @param msg    メッセージ
     */
    public static void printSecondTime(String msg) {
        Log.i("Stopwatch",msg + secondTime());
    }
}