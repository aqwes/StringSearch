package main;
// Sample code to read a complete text file into a char array.

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ReadFile {
	private char[] a;
	private String source = "src/resources/1.txt";

	public char[] readFile() throws IOException {
		InputStreamReader r = new InputStreamReader(new FileInputStream(source));
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
		a = new char[bytes]; // breaks if file is 2^31 chars or more
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
}
