package com.tencent.bugly.proguard;

import android.content.Context;
import android.text.TextUtils;

import com.tencent.bugly.BuglyDatabase;
import com.tencent.bugly.crashreport.biz.UserInfoBean;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/* compiled from: BUGLY */
public class a {
    private static Proxy e;
    protected HashMap<String, HashMap<String, byte[]>> a = new HashMap<>();
    protected String b;
    i c;
    private HashMap<String, Object> d;

    public static aj a(int i) {
        if (i == 1) {
            return new ai();
        }
        if (i == 3) {
            return new ah();
        }
        return null;
    }

    a() {
        new HashMap();
        this.d = new HashMap<>();
        this.b = "GBK";
        this.c = new i();
    }

    public static void a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            e = null;
        } else {
            e = new Proxy(Type.HTTP, new InetSocketAddress(str, i));
        }
    }

    public static void a(InetAddress inetAddress, int i) {
        if (inetAddress == null) {
            e = null;
        } else {
            e = new Proxy(Type.HTTP, new InetSocketAddress(inetAddress, i));
        }
    }

    public void a(String str) {
        this.b = str;
    }

    public static at a(UserInfoBean userInfoBean) {
        if (userInfoBean == null) {
            return null;
        }
        at atVar = new at();
        atVar.a = userInfoBean.e;
        atVar.e = userInfoBean.j;
        atVar.d = userInfoBean.c;
        atVar.c = userInfoBean.d;
        atVar.g = com.tencent.bugly.crashreport.common.info.a.b().i();
        atVar.h = userInfoBean.o == 1;
        switch (userInfoBean.b) {
            case com.tencent.bugly.BuglyStrategy.a.CRASHTYPE_JAVA_CATCH /*1*/:
                atVar.b = 1;
                break;
            case com.tencent.bugly.BuglyStrategy.a.CRASHTYPE_NATIVE /*2*/:
                atVar.b = 4;
                break;
            case com.tencent.bugly.BuglyStrategy.a.CRASHTYPE_U3D /*3*/:
                atVar.b = 2;
                break;
            case com.tencent.bugly.BuglyStrategy.a.CRASHTYPE_ANR /*4*/:
                atVar.b = 3;
                break;
            default:
                if (userInfoBean.b >= 10 && userInfoBean.b < 20) {
                    atVar.b = (byte) userInfoBean.b;
                    break;
                } else {
                    x.e("unknown uinfo type %d ", Integer.valueOf(userInfoBean.b));
                    return null;
                }
                break;
        }
        atVar.f = new HashMap();
        if (userInfoBean.p >= 0) {
            atVar.f.put("C01", userInfoBean.p);
        }
        if (userInfoBean.q >= 0) {
            atVar.f.put("C02", userInfoBean.q);
        }
        if (userInfoBean.r != null && userInfoBean.r.size() > 0) {
            for (Entry entry : userInfoBean.r.entrySet()) {
                atVar.f.put("C03_" + ((String) entry.getKey()), entry.getValue());
            }
        }
        if (userInfoBean.s != null && userInfoBean.s.size() > 0) {
            for (Entry entry2 : userInfoBean.s.entrySet()) {
                atVar.f.put("C04_" + ((String) entry2.getKey()), entry2.getValue());
            }
        }
        atVar.f.put("A36", (!userInfoBean.l));
        atVar.f.put("F02", userInfoBean.g);
        atVar.f.put("F03", userInfoBean.h);
        atVar.f.put("F04", userInfoBean.j);
        atVar.f.put("F05", userInfoBean.i);
        atVar.f.put("F06", userInfoBean.m);
        atVar.f.put("F10", userInfoBean.k);
        x.c("summary type %d vm:%d", Byte.valueOf(atVar.b), Integer.valueOf(atVar.f.size()));
        return atVar;
    }

    public static Proxy b() {
        return e;
    }

    public static String a(ArrayList<String> arrayList) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < arrayList.size(); i++) {
            String str = (String) arrayList.get(i);
            if (str.equals("java.lang.Integer") || str.equals("int")) {
                str = "int32";
            } else if (str.equals("java.lang.Boolean") || str.equals("boolean")) {
                str = "bool";
            } else if (str.equals("java.lang.Byte") || str.equals("byte")) {
                str = "char";
            } else if (str.equals("java.lang.Double") || str.equals("double")) {
                str = "double";
            } else if (str.equals("java.lang.Float") || str.equals("float")) {
                str = "float";
            } else if (str.equals("java.lang.Long") || str.equals("long")) {
                str = "int64";
            } else if (str.equals("java.lang.Short") || str.equals("short")) {
                str = "short";
            } else if (str.equals("java.lang.Character")) {
                throw new IllegalArgumentException("can not support java.lang.Character");
            } else if (str.equals("java.lang.String")) {
                str = "string";
            } else if (str.equals("java.util.List")) {
                str = "list";
            } else if (str.equals("java.util.Map")) {
                str = "map";
            }
            arrayList.set(i, str);
        }
        Collections.reverse(arrayList);
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            String str2 = (String) arrayList.get(i2);
            if (str2.equals("list")) {
                arrayList.set(i2 - 1, "<" + ((String) arrayList.get(i2 - 1)));
                arrayList.set(0, ((String) arrayList.get(0)) + ">");
            } else if (str2.equals("map")) {
                arrayList.set(i2 - 1, "<" + ((String) arrayList.get(i2 - 1)) + ",");
                arrayList.set(0, ((String) arrayList.get(0)) + ">");
            } else if (str2.equals("Array")) {
                arrayList.set(i2 - 1, "<" + ((String) arrayList.get(i2 - 1)));
                arrayList.set(0, ((String) arrayList.get(0)) + ">");
            }
        }
        Collections.reverse(arrayList);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            stringBuffer.append((String) it.next());
        }
        return stringBuffer.toString();
    }

    public <T> void a(String str, T t) {
        if (str == null) {
            throw new IllegalArgumentException("put key can not is null");
        } else if (t == null) {
            throw new IllegalArgumentException("put value can not is null");
        } else if (t instanceof Set) {
            throw new IllegalArgumentException("can not support Set");
        } else {
            j jVar = new j();
            jVar.a(this.b);
            jVar.a((Object) t, 0);
            byte[] a2 = l.a(jVar.a());
            HashMap hashMap = new HashMap(1);
            ArrayList arrayList = new ArrayList(1);
            a(arrayList, (Object) t);
            hashMap.put(a(arrayList), a2);
            this.d.remove(str);
            this.a.put(str, hashMap);
        }
    }

    public static au a(List<UserInfoBean> list, int i) {
        if (list == null || list.size() == 0) {
            return null;
        }
        com.tencent.bugly.crashreport.common.info.a b2 = com.tencent.bugly.crashreport.common.info.a.b();
        if (b2 == null) {
            return null;
        }
        b2.t();
        au auVar = new au();
        auVar.b = b2.d;
        auVar.c = b2.h();
        ArrayList<at> arrayList = new ArrayList<>();
        for (UserInfoBean a2 : list) {
            at a3 = a(a2);
            if (a3 != null) {
                arrayList.add(a3);
            }
        }
        auVar.d = arrayList;
        auVar.e = new HashMap();
        auVar.e.put("A7", b2.g);
        auVar.e.put("A6", b2.s());
        auVar.e.put("A5", b2.r());
        auVar.e.put("A2", b2.p());
        auVar.e.put("A1", b2.p());
        auVar.e.put("A24", b2.i);
        auVar.e.put("A17", b2.q());
        auVar.e.put("A15", b2.w());
        auVar.e.put("A13", b2.x());
        auVar.e.put("F08", b2.w);
        auVar.e.put("F09", b2.x);
        Map G = b2.G();
        if (G != null && G.size() > 0) {
            for (Entry entry : G.entrySet()) {
                auVar.e.put("C04_" + ((String) entry.getKey()), entry.getValue());
            }
        }
        switch (i) {
            case com.tencent.bugly.BuglyStrategy.a.CRASHTYPE_JAVA_CATCH /*1*/:
                auVar.a = 1;
                break;
            case com.tencent.bugly.BuglyStrategy.a.CRASHTYPE_NATIVE /*2*/:
                auVar.a = 2;
                break;
            default:
                x.e("unknown up type %d ", Integer.valueOf(i));
                return null;
        }
        return auVar;
    }

    public static <T extends k> T a(byte[] bArr, Class<T> cls) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        try {
            T t = (k) cls.newInstance();
            i iVar = new i(bArr);
            iVar.a("utf-8");
            t.a(iVar);
            return t;
        } catch (Throwable th) {
            if (!x.b(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static ap a(Context context, int i, byte[] bArr) {
        com.tencent.bugly.crashreport.common.info.a b2 = com.tencent.bugly.crashreport.common.info.a.b();
        StrategyBean c2 = com.tencent.bugly.crashreport.common.strategy.a.a().c();
        if (b2 == null || c2 == null) {
            x.e("Can not create request pkg for parameters is invalid.", new Object[0]);
            return null;
        }
        try {
            ap apVar = new ap();
            synchronized (b2) {
                apVar.a = 1;
                apVar.b = b2.f();
                apVar.c = b2.c;
                apVar.d = b2.k;
                apVar.e = b2.m;
                apVar.f = b2.f;
                apVar.g = i;
                apVar.h = bArr == null ? "".getBytes() : bArr;
                apVar.i = b2.h;
                apVar.j = b2.i;
                apVar.k = new HashMap();
                apVar.l = b2.e();
                apVar.m = c2.p;
                apVar.o = b2.h();
                apVar.p = b.c(context);
                apVar.q = System.currentTimeMillis();
                apVar.r = b2.k();
                apVar.s = b2.j();
                apVar.t = b2.m();
                apVar.u = b2.l();
                apVar.v = b2.n();
                apVar.w = apVar.p;
                b2.getClass();
                apVar.n = "com.tencent.bugly";
                apVar.k.put("A26", b2.y());
                apVar.k.put("A60", b2.z());
                apVar.k.put("A61", b2.A());
                apVar.k.put("A62", b2.R());
                apVar.k.put("A63", b2.S());
                apVar.k.put("F11", b2.B);
                apVar.k.put("F12", b2.A);
                apVar.k.put("G1", b2.u());
                apVar.k.put("A64", b2.T());
                if (b2.D) {
                    apVar.k.put("G2", b2.L());
                    apVar.k.put("G3", b2.M());
                    apVar.k.put("G4", b2.N());
                    apVar.k.put("G5", b2.O());
                    apVar.k.put("G6", b2.P());
                    apVar.k.put("G7", Long.toString(b2.Q()));
                }
                apVar.k.put("D3", b2.l);
                if (com.tencent.bugly.b.b != null) {
                    for (BuglyDatabase aVar : com.tencent.bugly.b.b) {
                        if (!(aVar.versionKey == null || aVar.version == null)) {
                            apVar.k.put(aVar.versionKey, aVar.version);
                        }
                    }
                }
                apVar.k.put("G15", z.b("G15", ""));
                apVar.k.put("D4", z.b("D4", "0"));
            }
            u a2 = u.a();
            if (!(a2 == null || a2.a || bArr == null)) {
                apVar.h = z.a(apVar.h, 2, 1, c2.u);
                if (apVar.h == null) {
                    x.e("reqPkg sbuffer error!", new Object[0]);
                    return null;
                }
            }
            Map F = b2.F();
            if (F != null) {
                for (Entry entry : F.entrySet()) {
                    apVar.k.put(entry.getKey(), entry.getValue());
                }
            }
            return apVar;
        } catch (Throwable th) {
            if (!x.b(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    private void a(ArrayList<String> arrayList, Object obj) {
        if (obj.getClass().isArray()) {
            if (!obj.getClass().getComponentType().toString().equals("byte")) {
                throw new IllegalArgumentException("only byte[] is supported");
            } else if (Array.getLength(obj) > 0) {
                arrayList.add("java.util.List");
                a(arrayList, Array.get(obj, 0));
            } else {
                arrayList.add("Array");
                arrayList.add("?");
            }
        } else if (obj instanceof Array) {
            throw new IllegalArgumentException("can not support Array, please use List");
        } else if (obj instanceof List) {
            arrayList.add("java.util.List");
            List list = (List) obj;
            if (list.size() > 0) {
                a(arrayList, list.get(0));
            } else {
                arrayList.add("?");
            }
        } else if (obj instanceof Map) {
            arrayList.add("java.util.Map");
            Map map = (Map) obj;
            if (map.size() > 0) {
                Object next = map.keySet().iterator().next();
                Object obj2 = map.get(next);
                arrayList.add(next.getClass().getName());
                a(arrayList, obj2);
                return;
            }
            arrayList.add("?");
            arrayList.add("?");
        } else {
            arrayList.add(obj.getClass().getName());
        }
    }

    public byte[] a() {
        j jVar = new j(0);
        jVar.a(this.b);
        jVar.a((Map<K, V>) this.a, 0);
        return l.a(jVar.a());
    }

    public void a(byte[] bArr) {
        this.c.a(bArr);
        this.c.a(this.b);
        HashMap hashMap = new HashMap(1);
        HashMap hashMap2 = new HashMap(1);
        hashMap2.put("", new byte[0]);
        hashMap.put("", hashMap2);
        this.a = this.c.a((Map<K, V>) hashMap, 0, false);
    }

    public static byte[] a(Object obj) {
        try {
            d dVar = new d();
            dVar.c();
            dVar.a("utf-8");
            dVar.b(1);
            dVar.b("RqdServer");
            dVar.c("sync");
            dVar.a("detail", obj);
            return dVar.a();
        } catch (Throwable th) {
            if (!x.b(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static aq a(byte[] bArr, boolean z) {
        aq aqVar;
        if (bArr != null) {
            try {
                d dVar = new d();
                dVar.c();
                dVar.a("utf-8");
                dVar.a(bArr);
                Object b2 = dVar.b("detail", new aq());
                if (aq.class.isInstance(b2)) {
                    aqVar = (aq) aq.class.cast(b2);
                } else {
                    aqVar = null;
                }
                if (z || aqVar == null || aqVar.c == null || aqVar.c.length <= 0) {
                    return aqVar;
                }
                x.c("resp buf %d", Integer.valueOf(aqVar.c.length));
                aqVar.c = z.b(aqVar.c, 2, 1, StrategyBean.d);
                if (aqVar.c != null) {
                    return aqVar;
                }
                x.e("resp sbuffer error!", new Object[0]);
                return null;
            } catch (Throwable th) {
                if (!x.b(th)) {
                    th.printStackTrace();
                }
            }
        }
        return null;
    }

    public static byte[] a(k kVar) {
        try {
            j jVar = new j();
            jVar.a("utf-8");
            kVar.a(jVar);
            return jVar.b();
        } catch (Throwable th) {
            if (!x.b(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }
}
