package net.vtorganisation.ranking.utils;

public class NumberFormatted {

    private static final String a = "K";
    private static final String b = "M";
    private static final String c = "B";
    private static final String d = "T";
    private static final String e = "Q";

    public static String formatted(long n) {
        if (n >= 1.0E15) {
                String s = String.format("%.2f", n / 1.0E15);
                final String substring = s.substring(s.indexOf(".") + 1);
                if (substring.endsWith("0")) {
                    if (substring.startsWith("0")) {
                        s = s.substring(0, s.length() - 3);
                    }
                    else {
                        s = s.substring(0, s.length() - 1);
                    }
                }
                return s + e;
        }
        else if (n >= 1.0E12) {
                String s2 = String.format("%.2f", n / 1.0E12);
                final String substring2 = s2.substring(s2.indexOf(".") + 1);
                if (substring2.endsWith("0")) {
                    if (substring2.startsWith("0")) {
                        s2 = s2.substring(0, s2.length() - 3);
                    }
                    else {
                        s2 = s2.substring(0, s2.length() - 1);
                    }
                }
                return s2 + d;
        }
        else if (n >= 1.0E9) {
                String s3 = String.format("%.2f", n / 1.0E9);
                final String substring3 = s3.substring(s3.indexOf(".") + 1);
                if (substring3.endsWith("0")) {
                    if (substring3.startsWith("0")) {
                        s3 = s3.substring(0, s3.length() - 3);
                    }
                    else {
                        s3 = s3.substring(0, s3.length() - 1);
                    }
                }
                return s3 + c;
        }
        else if (n >= 1000000.0) {
                String s4 = String.format("%.2f", n / 1000000.0);
                final String substring4 = s4.substring(s4.indexOf(".") + 1);
                if (substring4.endsWith("0")) {
                    if (substring4.startsWith("0")) {
                        s4 = s4.substring(0, s4.length() - 3);
                    }
                    else {
                        s4 = s4.substring(0, s4.length() - 1);
                    }
                }
                return s4 + b;
        }
        else {
            if (n >= 1000.0) {
                String s5 = String.format("%.2f", n / 1000.0);
                final String substring5 = s5.substring(s5.indexOf(".") + 1);
                if (substring5.endsWith("0")) {
                    if (substring5.startsWith("0")) {
                        s5 = s5.substring(0, s5.length() - 3);
                    }
                    else {
                        s5 = s5.substring(0, s5.length() - 1);
                    }
                }
                return s5 + a;
            }
            return String.valueOf(n);
        }
    }
}