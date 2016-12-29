# BattleShootig
Google play surviceを利用したマルチプレイシューティングゲーム

Could not find class 'com.google.firebase.FirebaseOptions', referenced from method com.google.firebase.FirebaseApp.<init>
12-29 14:15:24.210 7338-7338/com.plplsent.battleshooting E/dalvikvm: Could not find class 'com.google.firebase.FirebaseApp$zzb', referenced from method com.google.firebase.FirebaseApp.zzaR
12-29 14:15:24.225 7338-7338/com.plplsent.battleshooting E/dalvikvm: Could not find class 'com.google.firebase.FirebaseApiNotAvailableException', referenced from method com.google.firebase.FirebaseApp.getToken
12-29 14:15:24.235 7338-7338/com.plplsent.battleshooting E/dalvikvm: Could not find class 'com.google.firebase.FirebaseApp$zza', referenced from method com.google.firebase.FirebaseApp.zza
12-29 14:15:24.245 7338-7338/com.plplsent.battleshooting E/AndroidRuntime: FATAL EXCEPTION: main

java.lang.NoClassDefFoundError: com.google.firebase.FirebaseOptions
                                                                               at com.google.firebase.FirebaseApp.initializeApp(Unknown Source)
                                                                               at com.google.firebase.provider.FirebaseInitProvider.onCreate(Unknown Source)
                                                                               at android.content.ContentProvider.attachInfo(ContentProvider.java:967)
                                                                               at com.google.firebase.provider.FirebaseInitProvider.attachInfo(Unknown Source)
                                                                               at android.app.ActivityThread.installProvider(ActivityThread.java:4253)
                                                                               at android.app.ActivityThread.installContentProviders(ActivityThread.java:4008)
                                                                               at android.app.ActivityThread.handleBindApplication(ActivityThread.java:3958)
                                                                               at android.app.ActivityThread.access$1300(ActivityThread.java:128)
                                                                               at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1197)
                                                                               at android.os.Handler.dispatchMessage(Handler.java:99)
                                                                               at android.os.Looper.loop(Looper.java:137)
                                                                               at android.app.ActivityThread.main(ActivityThread.java:4516)
                                                                               at java.lang.reflect.Method.invokeNative(Native Method)
                                                                               at java.lang.reflect.Method.invoke(Method.java:511)
                                                                               at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:991)
                                                                               at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:758)
                                                                               at dalvik.system.NativeStart.main(Native Method)
