package main;
// Sample code to read a complete text file into a char array.

import java.io.*;
import java.util.*;

public class ReadFile {
    public static char[] readFile(String fnam) throws IOException {
        InputStreamReader r = new InputStreamReader(new FileInputStream(fnam));
        ArrayList<char[]> blocks = new ArrayList<char[]>();
        int bytes = 0;
        char[] buf = new char[8192];
        int i = 0;
        while (true) {
            int bytesRead = r.read(buf, i, buf.length-i);
            if (bytesRead < 0) { break; } // end of file
            i += bytesRead;
            bytes += bytesRead;
            if (bytes < 0) {
                throw new ArrayIndexOutOfBoundsException("File too big");
            }
            if (i == buf.length) {
                blocks.add(buf);
                buf = new char[buf.length];
                i = 0;
            }
        }
        char[] a = new char[bytes]; // breaks if file is 2^31 chars or more
        int k = 0;
        for (char[] b : blocks) {
            for (int j = 0; j < b.length; j++) {
                a[k++] = b[j];
            }
        }
        for (int j = 0; j < i; j++) {
            a[k++] = buf[j];
        }
        return a;
    }

    // Test.
    public static void main(String[] args) throws IOException {
        char[] f = readFile(args[0]);
        for (int i = 0; i < f.length; i++) {
            if (i == 40) {
                System.out.println("… and then "+(f.length-i)+" more characters");
                break;
            }
            System.out.println(i+": "+f[i]+" ("+(int) f[i]+")");
        }
    }
}
