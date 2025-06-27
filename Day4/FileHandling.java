package Day4;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class FileHandling {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        FileWriter f = new FileWriter("D:\\FileHandlingInJavaEX\\WriteFile.txt", true);
		try {
            System.out.println("Enter the text to write in file: ");
			f.write(sc.next());
			
		}
		catch (Exception e) {
			System.out.println(e);
		}
		finally {
			f.close();
			System.out.println("Successfully write in file ");
		}

        FileReader fr = new FileReader("D:\\FileHandlingInJavaEX\\WriteFile.txt");
		try {
			int i;
			while((i=fr.read())!=-1) {
				System.out.print((char)i);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		finally {
			fr.close();
		}
        sc.close();
    }
}
