package com.tencent.bugly.crashreport.biz;

import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Build.VERSION;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.common.info.a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.proguard.w;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;

/* compiled from: BUGLY */
public class b {
    public static a a;
    private static boolean b;
    /* access modifiers changed from: private */
    public static int c = 10;
    /* access modifiers changed from: private */
    public static long d = 300000;
    /* access modifiers changed from: private */
    public static long e = 30000;
    /* access modifiers changed from: private */
    public static long f = 0;
    /* access modifiers changed from: private */
    public static int g;
    /* access modifiers changed from: private */
    public static long h;
    /* access modifiers changed from: private */
    public static long i;
    /* access modifiers changed from: private */
    public static long j = 0;
    private static ActivityLifecycleCallbacks k = null;
    /* access modifiers changed from: private */
    public static Class<?> l = null;
    /* access modifiers changed from: private */
    public static boolean m = true;

    static /* synthetic */ String a(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(z.a());
        sb.append("  ");
        sb.append(str);
        sb.append("  ");
        sb.append(str2);
        sb.append("\n");
        return sb.toString();
    }

    static /* synthetic */ int g() {
        int i2 = g;
        g = i2 + 1;
        return i2;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:72:? A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void c(android.content.Context r10, com.tencent.bugly.BuglyStrategy r11) {
        /*
            r0 = 1
            r1 = 0
            if (r11 == 0) goto L_0x000c
            boolean r1 = r11.recordUserInfoOnceADay()
            boolean r0 = r11.isEnableUserInfo()
        L_0x000c:
            if (r1 == 0) goto L_0x006c
            com.tencent.bugly.crashreport.common.info.a r2 = com.tencent.bugly.crashreport.common.info.a.a(r10)
            java.lang.String r0 = r2.d
            com.tencent.bugly.crashreport.biz.a r1 = a
            java.util.List r3 = r1.a(r0)
            if (r3 == 0) goto L_0x0069
            r0 = 0
            r1 = r0
        L_0x001e:
            int r0 = r3.size()
            if (r1 >= r0) goto L_0x0069
            java.lang.Object r0 = r3.get(r1)
            com.tencent.bugly.crashreport.biz.UserInfoBean r0 = (com.tencent.bugly.crashreport.biz.UserInfoBean) r0
            java.lang.String r4 = r0.n
            java.lang.String r5 = r2.k
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x0065
            int r4 = r0.b
            r5 = 1
            if (r4 != r5) goto L_0x0065
            long r4 = com.tencent.bugly.proguard.z.b()
            r6 = 0
            int r6 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r6 <= 0) goto L_0x0069
            long r6 = r0.e
            int r4 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r4 < 0) goto L_0x0065
            long r0 = r0.f
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 > 0) goto L_0x0061
            com.tencent.bugly.crashreport.biz.a r0 = a
            com.tencent.bugly.proguard.w r1 = com.tencent.bugly.proguard.w.a()
            if (r1 == 0) goto L_0x0061
            com.tencent.bugly.crashreport.biz.a$2 r2 = new com.tencent.bugly.crashreport.biz.a$2
            r2.<init>()
            r1.a(r2)
        L_0x0061:
            r0 = 0
        L_0x0062:
            if (r0 != 0) goto L_0x006b
        L_0x0064:
            return
        L_0x0065:
            int r0 = r1 + 1
            r1 = r0
            goto L_0x001e
        L_0x0069:
            r0 = 1
            goto L_0x0062
        L_0x006b:
            r0 = 0
        L_0x006c:
            com.tencent.bugly.crashreport.common.info.a r4 = com.tencent.bugly.crashreport.common.info.a.b()
            if (r4 == 0) goto L_0x00ac
            r1 = 0
            r2 = 0
            java.lang.Thread r3 = java.lang.Thread.currentThread()
            java.lang.StackTraceElement[] r5 = r3.getStackTrace()
            int r6 = r5.length
            r3 = 0
        L_0x007e:
            if (r3 >= r6) goto L_0x00a2
            r7 = r5[r3]
            java.lang.String r8 = r7.getMethodName()
            java.lang.String r9 = "onCreate"
            boolean r8 = r8.equals(r9)
            if (r8 == 0) goto L_0x0092
            java.lang.String r1 = r7.getClassName()
        L_0x0092:
            java.lang.String r7 = r7.getClassName()
            java.lang.String r8 = "android.app.Activity"
            boolean r7 = r7.equals(r8)
            if (r7 == 0) goto L_0x009f
            r2 = 1
        L_0x009f:
            int r3 = r3 + 1
            goto L_0x007e
        L_0x00a2:
            if (r1 == 0) goto L_0x010b
            if (r2 == 0) goto L_0x0108
            r2 = 1
            r4.a(r2)
        L_0x00aa:
            r4.q = r1
        L_0x00ac:
            if (r0 == 0) goto L_0x00d5
            r0 = 0
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 14
            if (r1 < r2) goto L_0x00d5
            android.content.Context r1 = r10.getApplicationContext()
            boolean r1 = r1 instanceof android.app.Application
            if (r1 == 0) goto L_0x00c3
            android.content.Context r0 = r10.getApplicationContext()
            android.app.Application r0 = (android.app.Application) r0
        L_0x00c3:
            if (r0 == 0) goto L_0x00d5
            android.app.Application$ActivityLifecycleCallbacks r1 = k     // Catch:{ Exception -> 0x010e }
            if (r1 != 0) goto L_0x00d0
            com.tencent.bugly.crashreport.biz.b$2 r1 = new com.tencent.bugly.crashreport.biz.b$2     // Catch:{ Exception -> 0x010e }
            r1.<init>()     // Catch:{ Exception -> 0x010e }
            k = r1     // Catch:{ Exception -> 0x010e }
        L_0x00d0:
            android.app.Application$ActivityLifecycleCallbacks r1 = k     // Catch:{ Exception -> 0x010e }
            r0.registerActivityLifecycleCallbacks(r1)     // Catch:{ Exception -> 0x010e }
        L_0x00d5:
            boolean r0 = m
            if (r0 == 0) goto L_0x0064
            long r0 = java.lang.System.currentTimeMillis()
            i = r0
            com.tencent.bugly.crashreport.biz.a r0 = a
            r1 = 1
            r2 = 0
            r4 = 0
            r0.a(r1, r2, r4)
            java.lang.String r0 = "[session] launch app, new start"
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.x.a(r0, r1)
            com.tencent.bugly.crashreport.biz.a r0 = a
            r0.a()
            com.tencent.bugly.crashreport.biz.a r0 = a
            r2 = 21600000(0x1499700, double:1.0671818E-316)
            com.tencent.bugly.proguard.w r1 = com.tencent.bugly.proguard.w.a()
            com.tencent.bugly.crashreport.biz.a$c r4 = new com.tencent.bugly.crashreport.biz.a$c
            r4.<init>(r2)
            r1.a(r4, r2)
            goto L_0x0064
        L_0x0108:
            java.lang.String r1 = "background"
            goto L_0x00aa
        L_0x010b:
            java.lang.String r1 = "unknown"
            goto L_0x00aa
        L_0x010e:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x00d5
            r0.printStackTrace()
            goto L_0x00d5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.biz.b.c(android.content.Context, com.tencent.bugly.BuglyStrategy):void");
    }

    public static void a(final Context context, final BuglyStrategy buglyStrategy) {
        long j2;
        if (!b) {
            m = a.a(context).e;
            a = new a(context, m);
            b = true;
            if (buglyStrategy != null) {
                l = buglyStrategy.getUserInfoActivity();
                j2 = buglyStrategy.getAppReportDelay();
            } else {
                j2 = 0;
            }
            if (j2 <= 0) {
                c(context, buglyStrategy);
            } else {
                w.a().a(new Runnable() {
                    public final void run() {
                        b.c(context, buglyStrategy);
                    }
                }, j2);
            }
        }
    }

    public static void a(long j2) {
        if (j2 < 0) {
            j2 = com.tencent.bugly.crashreport.common.strategy.a.a().c().q;
        }
        f = j2;
    }

    public static void a(StrategyBean strategyBean, boolean z) {
        if (a != null && !z) {
            a aVar = a;
            w a2 = w.a();
            if (a2 != null) {
                a2.a(new Runnable() {
                    public final void run() {
                        try {
                            a.this.c();
                        } catch (Throwable th) {
                            x.a(th);
                        }
                    }
                });
            }
        }
        if (strategyBean != null) {
            if (strategyBean.q > 0) {
                e = strategyBean.q;
            }
            if (strategyBean.w > 0) {
                c = strategyBean.w;
            }
            if (strategyBean.x > 0) {
                d = strategyBean.x;
            }
        }
    }

    public static void a() {
        if (a != null) {
            a.a(2, false, 0);
        }
    }

    public static void a(Context context) {
        if (b && context != null) {
            Application application = null;
            if (VERSION.SDK_INT >= 14) {
                if (context.getApplicationContext() instanceof Application) {
                    application = (Application) context.getApplicationContext();
                }
                if (application != null) {
                    try {
                        if (k != null) {
                            application.unregisterActivityLifecycleCallbacks(k);
                        }
                    } catch (Exception e2) {
                        if (!x.a(e2)) {
                            e2.printStackTrace();
                        }
                    }
                }
            }
            b = false;
        }
    }
}
