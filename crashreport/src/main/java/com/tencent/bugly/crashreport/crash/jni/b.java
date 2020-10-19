package com.tencent.bugly.crashreport.crash.jni;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.bugly.crashreport.common.info.a;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/* compiled from: BUGLY */
public final class b {
    private static List<File> a = new ArrayList();

    private static Map<String, Integer> d(String str) {
        String[] split;
        if (str == null) {
            return null;
        }
        try {
            HashMap hashMap = new HashMap();
            for (String str2 : str.split(",")) {
                String[] split2 = str2.split(":");
                if (split2.length != 2) {
                    x.e("error format at %s", str2);
                    return null;
                }
                hashMap.put(split2[0], Integer.valueOf(Integer.parseInt(split2[1])));
            }
            return hashMap;
        } catch (Exception e) {
            x.e("error format intStateStr %s", str);
            e.printStackTrace();
            return null;
        }
    }

    protected static String a(String str) {
        if (str == null) {
            return "";
        }
        String[] split = str.split("\n");
        if (split == null || split.length == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for (String str2 : split) {
            if (!str2.contains("java.lang.Thread.getStackTrace(")) {
                sb.append(str2).append("\n");
            }
        }
        return sb.toString();
    }

    private static CrashDetailBean a(Context context, Map<String, String> map, NativeExceptionHandler nativeExceptionHandler) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        if (map == null) {
            return null;
        }
        if (a.a(context) == null) {
            x.e("abnormal com info not created", new Object[0]);
            return null;
        }
        String str8 = (String) map.get("intStateStr");
        if (str8 == null || str8.trim().length() <= 0) {
            x.e("no intStateStr", new Object[0]);
            return null;
        }
        Map d = d(str8);
        if (d == null) {
            x.e("parse intSateMap fail", Integer.valueOf(map.size()));
            return null;
        }
        try {
            ((Integer) d.get("sino")).intValue();
            ((Integer) d.get("sud")).intValue();
            String str9 = (String) map.get("soVersion");
            if (TextUtils.isEmpty(str9)) {
                x.e("error format at version", new Object[0]);
                return null;
            }
            String str10 = (String) map.get("errorAddr");
            String str11 = str10 == null ? "unknown" : str10;
            String str12 = (String) map.get("codeMsg");
            if (str12 == null) {
                str = "unknown";
            } else {
                str = str12;
            }
            String str13 = (String) map.get("tombPath");
            if (str13 == null) {
                str2 = "unknown";
            } else {
                str2 = str13;
            }
            String str14 = (String) map.get("signalName");
            if (str14 == null) {
                str3 = "unknown";
            } else {
                str3 = str14;
            }
            map.get("errnoMsg");
            String str15 = (String) map.get("stack");
            if (str15 == null) {
                str4 = "unknown";
            } else {
                str4 = str15;
            }
            String str16 = (String) map.get("jstack");
            if (str16 != null) {
                str4 = str4 + "java:\n" + str16;
            }
            Integer num = (Integer) d.get("sico");
            if (num != null && num.intValue() > 0) {
                str3 = str3 + "(" + str + ")";
                str = "KERNEL";
            }
            String str17 = (String) map.get("nativeLog");
            byte[] bArr = null;
            if (str17 != null && !str17.isEmpty()) {
                bArr = z.a((File) null, str17, "BuglyNativeLog.txt");
            }
            String str18 = (String) map.get("sendingProcess");
            if (str18 == null) {
                str5 = "unknown";
            } else {
                str5 = str18;
            }
            Integer num2 = (Integer) d.get("spd");
            if (num2 != null) {
                str5 = str5 + "(" + num2 + ")";
            }
            String str19 = (String) map.get("threadName");
            if (str19 == null) {
                str6 = "unknown";
            } else {
                str6 = str19;
            }
            Integer num3 = (Integer) d.get("et");
            if (num3 != null) {
                str6 = str6 + "(" + num3 + ")";
            }
            String str20 = (String) map.get("processName");
            if (str20 == null) {
                str7 = "unknown";
            } else {
                str7 = str20;
            }
            Integer num4 = (Integer) d.get("ep");
            if (num4 != null) {
                str7 = str7 + "(" + num4 + ")";
            }
            HashMap hashMap = null;
            String str21 = (String) map.get("key-value");
            if (str21 != null) {
                hashMap = new HashMap();
                for (String split : str21.split("\n")) {
                    String[] split2 = split.split("=");
                    if (split2.length == 2) {
                        hashMap.put(split2[0], split2[1]);
                    }
                }
            }
            CrashDetailBean packageCrashDatas = nativeExceptionHandler.packageCrashDatas(str7, str6, (((long) ((Integer) d.get("etms")).intValue()) / 1000) + (((long) ((Integer) d.get("ets")).intValue()) * 1000), str3, str11, a(str4), str, str5, str2, (String) map.get("sysLogPath"), (String) map.get("jniLogPath"), str9, bArr, hashMap, false, false);
            if (packageCrashDatas != null) {
                String str22 = (String) map.get("userId");
                if (str22 != null) {
                    x.c("[Native record info] userId: %s", str22);
                    packageCrashDatas.m = str22;
                }
                String str23 = (String) map.get("sysLog");
                if (str23 != null) {
                    packageCrashDatas.w = str23;
                }
                String str24 = (String) map.get("appVersion");
                if (str24 != null) {
                    x.c("[Native record info] appVersion: %s", str24);
                    packageCrashDatas.f = str24;
                }
                String str25 = (String) map.get("isAppForeground");
                if (str25 != null) {
                    x.c("[Native record info] isAppForeground: %s", str25);
                    packageCrashDatas.N = str25.equalsIgnoreCase("true");
                }
                String str26 = (String) map.get("launchTime");
                if (str26 != null) {
                    x.c("[Native record info] launchTime: %s", str26);
                    packageCrashDatas.M = Long.parseLong(str26);
                }
                packageCrashDatas.z = null;
                packageCrashDatas.k = true;
            }
            return packageCrashDatas;
        } catch (NumberFormatException e) {
            if (!x.a(e)) {
                e.printStackTrace();
            }
        } catch (Throwable th) {
            x.e("error format", new Object[0]);
            th.printStackTrace();
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x003a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String a(java.io.BufferedInputStream r5) throws java.io.IOException {
        /*
            r0 = 0
            if (r5 != 0) goto L_0x0004
        L_0x0003:
            return r0
        L_0x0004:
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch:{ Throwable -> 0x0041, all -> 0x0036 }
            r1 = 1024(0x400, float:1.435E-42)
            r2.<init>(r1)     // Catch:{ Throwable -> 0x0041, all -> 0x0036 }
        L_0x000b:
            int r1 = r5.read()     // Catch:{ Throwable -> 0x0028 }
            r3 = -1
            if (r1 == r3) goto L_0x0032
            if (r1 != 0) goto L_0x0024
            java.lang.String r1 = new java.lang.String     // Catch:{ Throwable -> 0x0028 }
            byte[] r3 = r2.toByteArray()     // Catch:{ Throwable -> 0x0028 }
            java.lang.String r4 = "UTf-8"
            r1.<init>(r3, r4)     // Catch:{ Throwable -> 0x0028 }
            r2.close()
            r0 = r1
            goto L_0x0003
        L_0x0024:
            r2.write(r1)     // Catch:{ Throwable -> 0x0028 }
            goto L_0x000b
        L_0x0028:
            r1 = move-exception
        L_0x0029:
            com.tencent.bugly.proguard.x.a(r1)     // Catch:{ all -> 0x003e }
            if (r2 == 0) goto L_0x0003
            r2.close()
            goto L_0x0003
        L_0x0032:
            r2.close()
            goto L_0x0003
        L_0x0036:
            r1 = move-exception
            r2 = r0
        L_0x0038:
            if (r2 == 0) goto L_0x003d
            r2.close()
        L_0x003d:
            throw r1
        L_0x003e:
            r0 = move-exception
            r1 = r0
            goto L_0x0038
        L_0x0041:
            r1 = move-exception
            r2 = r0
            goto L_0x0029
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.jni.b.a(java.io.BufferedInputStream):java.lang.String");
    }

    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r0v1, types: [com.tencent.bugly.crashreport.crash.CrashDetailBean] */
    /* JADX WARNING: type inference failed for: r2v5, types: [java.io.BufferedInputStream] */
    /* JADX WARNING: type inference failed for: r2v6 */
    /* JADX WARNING: type inference failed for: r2v7, types: [java.io.BufferedInputStream] */
    /* JADX WARNING: type inference failed for: r2v8 */
    /* JADX WARNING: type inference failed for: r2v9, types: [java.io.BufferedInputStream] */
    /* JADX WARNING: type inference failed for: r1v11 */
    /* JADX WARNING: type inference failed for: r1v12, types: [java.lang.Object] */
    /* JADX WARNING: type inference failed for: r3v4, types: [java.lang.Object, java.lang.String] */
    /* JADX WARNING: type inference failed for: r0v6, types: [com.tencent.bugly.crashreport.crash.CrashDetailBean] */
    /* JADX WARNING: type inference failed for: r4v4, types: [java.lang.Object[]] */
    /* JADX WARNING: type inference failed for: r1v15 */
    /* JADX WARNING: type inference failed for: r1v16 */
    /* JADX WARNING: type inference failed for: r1v17 */
    /* JADX WARNING: type inference failed for: r0v7 */
    /* JADX WARNING: type inference failed for: r2v10 */
    /* JADX WARNING: type inference failed for: r2v11 */
    /* JADX WARNING: type inference failed for: r0v8 */
    /* JADX WARNING: type inference failed for: r0v9 */
    /* JADX WARNING: type inference failed for: r1v18 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v15
      assigns: []
      uses: []
      mth insns count: 73
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1510)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1510)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1510)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x009d A[SYNTHETIC, Splitter:B:53:0x009d] */
    /* JADX WARNING: Unknown variable types count: 9 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.tencent.bugly.crashreport.crash.CrashDetailBean a(android.content.Context r6, java.lang.String r7, com.tencent.bugly.crashreport.crash.jni.NativeExceptionHandler r8) {
        /*
            r2 = 0
            r0 = 0
            if (r6 == 0) goto L_0x0008
            if (r7 == 0) goto L_0x0008
            if (r8 != 0) goto L_0x0010
        L_0x0008:
            java.lang.String r1 = "get eup record file args error"
            java.lang.Object[] r2 = new java.lang.Object[r2]
            com.tencent.bugly.proguard.x.e(r1, r2)
        L_0x000f:
            return r0
        L_0x0010:
            java.io.File r1 = new java.io.File
            java.lang.String r2 = "rqd_record.eup"
            r1.<init>(r7, r2)
            boolean r2 = r1.exists()
            if (r2 == 0) goto L_0x000f
            boolean r2 = r1.canRead()
            if (r2 == 0) goto L_0x000f
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x0087, all -> 0x0099 }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0087, all -> 0x0099 }
            r3.<init>(r1)     // Catch:{ IOException -> 0x0087, all -> 0x0099 }
            r2.<init>(r3)     // Catch:{ IOException -> 0x0087, all -> 0x0099 }
            java.lang.String r1 = a(r2)     // Catch:{ IOException -> 0x00a9 }
            if (r1 == 0) goto L_0x003b
            java.lang.String r3 = "NATIVE_RQD_REPORT"
            boolean r3 = r1.equals(r3)     // Catch:{ IOException -> 0x00a9 }
            if (r3 != 0) goto L_0x004f
        L_0x003b:
            java.lang.String r3 = "record read fail! %s"
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ IOException -> 0x00a9 }
            r5 = 0
            r4[r5] = r1     // Catch:{ IOException -> 0x00a9 }
            com.tencent.bugly.proguard.x.e(r3, r4)     // Catch:{ IOException -> 0x00a9 }
            r2.close()     // Catch:{ IOException -> 0x004a }
            goto L_0x000f
        L_0x004a:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x000f
        L_0x004f:
            java.util.HashMap r4 = new java.util.HashMap     // Catch:{ IOException -> 0x00a9 }
            r4.<init>()     // Catch:{ IOException -> 0x00a9 }
            r1 = r0
        L_0x0055:
            java.lang.String r3 = a(r2)     // Catch:{ IOException -> 0x00a9 }
            if (r3 == 0) goto L_0x0064
            if (r1 != 0) goto L_0x005f
            r1 = r3
            goto L_0x0055
        L_0x005f:
            r4.put(r1, r3)     // Catch:{ IOException -> 0x00a9 }
            r1 = r0
            goto L_0x0055
        L_0x0064:
            if (r1 == 0) goto L_0x007a
            java.lang.String r3 = "record not pair! drop! %s"
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ IOException -> 0x00a9 }
            r5 = 0
            r4[r5] = r1     // Catch:{ IOException -> 0x00a9 }
            com.tencent.bugly.proguard.x.e(r3, r4)     // Catch:{ IOException -> 0x00a9 }
            r2.close()     // Catch:{ IOException -> 0x0075 }
            goto L_0x000f
        L_0x0075:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x000f
        L_0x007a:
            com.tencent.bugly.crashreport.crash.CrashDetailBean r0 = a(r6, r4, r8)     // Catch:{ IOException -> 0x00a9 }
            r2.close()     // Catch:{ IOException -> 0x0082 }
            goto L_0x000f
        L_0x0082:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x000f
        L_0x0087:
            r1 = move-exception
            r2 = r0
        L_0x0089:
            r1.printStackTrace()     // Catch:{ all -> 0x00a6 }
            if (r2 == 0) goto L_0x000f
            r2.close()     // Catch:{ IOException -> 0x0093 }
            goto L_0x000f
        L_0x0093:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x000f
        L_0x0099:
            r1 = move-exception
            r2 = r0
        L_0x009b:
            if (r2 == 0) goto L_0x00a0
            r2.close()     // Catch:{ IOException -> 0x00a1 }
        L_0x00a0:
            throw r1
        L_0x00a1:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x00a0
        L_0x00a6:
            r0 = move-exception
            r1 = r0
            goto L_0x009b
        L_0x00a9:
            r1 = move-exception
            goto L_0x0089
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.jni.b.a(android.content.Context, java.lang.String, com.tencent.bugly.crashreport.crash.jni.NativeExceptionHandler):com.tencent.bugly.crashreport.crash.CrashDetailBean");
    }

    private static String b(String str, String str2) {
        String str3 = null;
        String a2 = z.a(str, "reg_record.txt");
        if (a2 != null) {
            try {
                StringBuilder sb = new StringBuilder();
                a2 = a2.readLine();
                if (a2 != null && a2.startsWith(str2)) {
                    String str4 = "                ";
                    int i = 18;
                    int i2 = 0;
                    int i3 = 0;
                    while (true) {
                        String readLine = a2.readLine();
                        if (readLine == null) {
                            break;
                        }
                        if (i2 % 4 == 0) {
                            if (i2 > 0) {
                                sb.append("\n");
                            }
                            sb.append("  ");
                        } else {
                            if (readLine.length() > 16) {
                                i = 28;
                            }
                            sb.append(str4.substring(0, i - i3));
                        }
                        int length = readLine.length();
                        sb.append(readLine);
                        i2++;
                        i3 = length;
                    }
                    sb.append("\n");
                    str3 = sb.toString();
                    if (a2 != null) {
                        try {
                            a2.close();
                        } catch (Exception e) {
                            x.a(e);
                        }
                    }
                } else if (a2 != null) {
                    try {
                        a2.close();
                    } catch (Exception e2) {
                        x.a(e2);
                    }
                }
            } catch (Throwable th) {
                x.a(th);
                if (a2 != null) {
                    try {
                        a2.close();
                    } catch (Exception e3) {
                        x.a(e3);
                    }
                }
            } finally {
                if (a2 != null) {
                    try {
                        a2.close();
                    } catch (Exception e4) {
                        x.a(e4);
                    }
                }
            }
        }
        return str3;
    }

    private static String c(String str, String str2) {
        String str3 = null;
        String a2 = z.a(str, "map_record.txt");
        if (a2 != null) {
            try {
                StringBuilder sb = new StringBuilder();
                a2 = a2.readLine();
                if (a2 != null && a2.startsWith(str2)) {
                    while (true) {
                        String readLine = a2.readLine();
                        if (readLine == null) {
                            break;
                        }
                        sb.append("  ");
                        sb.append(readLine);
                        sb.append("\n");
                    }
                    str3 = sb.toString();
                    if (a2 != null) {
                        try {
                            a2.close();
                        } catch (Exception e) {
                            x.a(e);
                        }
                    }
                } else if (a2 != null) {
                    try {
                        a2.close();
                    } catch (Exception e2) {
                        x.a(e2);
                    }
                }
            } catch (Throwable th) {
                x.a(th);
                if (a2 != null) {
                    try {
                        a2.close();
                    } catch (Exception e3) {
                        x.a(e3);
                    }
                }
            } finally {
                if (a2 != null) {
                    try {
                        a2.close();
                    } catch (Exception e4) {
                        x.a(e4);
                    }
                }
            }
        }
        return str3;
    }

    public static String a(String str, String str2) {
        if (str == null || str2 == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        String b = b(str, str2);
        if (b != null && !b.isEmpty()) {
            sb.append("Register infos:\n");
            sb.append(b);
        }
        String c = c(str, str2);
        if (c != null && !c.isEmpty()) {
            if (sb.length() > 0) {
                sb.append("\n");
            }
            sb.append("System SO infos:\n");
            sb.append(c);
        }
        return sb.toString();
    }

    public static String b(String str) {
        if (str == null) {
            return null;
        }
        File file = new File(str, "backup_record.txt");
        if (file.exists()) {
            return file.getAbsolutePath();
        }
        return null;
    }

    public static void c(String str) {
        if (str != null) {
            try {
                File file = new File(str);
                if (file.canRead() && file.isDirectory()) {
                    File[] listFiles = file.listFiles();
                    if (listFiles != null) {
                        for (File file2 : listFiles) {
                            if (file2.canRead() && file2.canWrite() && file2.length() == 0) {
                                file2.delete();
                                x.c("Delete empty record file %s", file2.getAbsoluteFile());
                            }
                        }
                    }
                }
            } catch (Throwable th) {
                x.a(th);
            }
        }
    }

    public static void a(boolean z, String str) {
        if (str != null) {
            a.add(new File(str, "rqd_record.eup"));
            a.add(new File(str, "reg_record.txt"));
            a.add(new File(str, "map_record.txt"));
            a.add(new File(str, "backup_record.txt"));
            if (z) {
                c(str);
            }
        }
        if (a != null && a.size() > 0) {
            for (File file : a) {
                if (file.exists() && file.canWrite()) {
                    file.delete();
                    x.c("Delete record file %s", file.getAbsoluteFile());
                }
            }
        }
    }

    public static String a(String str, int i, String str2, boolean z) {
        BufferedReader bufferedReader;
        if (str == null || i <= 0) {
            return null;
        }
        File file = new File(str);
        if (!file.exists() || !file.canRead()) {
            return null;
        }
        x.a("Read system log from native record file(length: %s bytes): %s", Long.valueOf(file.length()), file.getAbsolutePath());
        a.add(file);
        x.c("Add this record file to list for cleaning lastly.", new Object[0]);
        if (str2 == null) {
            return z.a(new File(str), i, z);
        }
        StringBuilder sb = new StringBuilder();
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    if (Pattern.compile(str2 + "[ ]*:").matcher(readLine).find()) {
                        sb.append(readLine).append("\n");
                    }
                    if (i > 0 && sb.length() > i) {
                        if (z) {
                            sb.delete(i, sb.length());
                            break;
                        }
                        sb.delete(0, sb.length() - i);
                    }
                } catch (Throwable th) {
                    th = th;
                }
            }
            String sb2 = sb.toString();
            try {
                bufferedReader.close();
                return sb2;
            } catch (Exception e) {
                x.a(e);
                return sb2;
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedReader = null;
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e2) {
                    x.a(e2);
                }
            }
            throw th;
        }
    }
}
