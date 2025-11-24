import java.util.Scanner;
import java.io.IOException;
import java.io.File;

public class DecodeTape {

    public static void main(String args[]) throws IOException {
        Scanner s = new Scanner(new File("tape.test.in.txt"));
        s.nextLine();
        String line = s.nextLine();
        while (!line.equals("___________")) {
            int[] num = new int[8];
            int c = 1;
            for (int i = 0; i < line.length(); i++) {
                if(line.charAt(i) == ' '){
                    num[num.length-c] = 0;
                    c++;
                } else if (line.charAt(i) == 'o') {
                    num[num.length-c] = 1;
                    c++;
                }
            }
            char acii = 0;
            for (int n = 0; n < num.length; n++) {
                acii += num[n] * (int)Math.pow(2,n);
            }
            System.out.print(acii);
            line = s.nextLine();
        }
        s.close();
    }
}