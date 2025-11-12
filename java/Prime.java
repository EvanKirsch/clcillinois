import java.util.Scanner;

public class Prime {

    public static void main (String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a Number to see if it is prime:");
        String input = scanner.nextLine();
        boolean isPrime = isPrime(Integer.valueOf(input));
        System.out.println("Is " + input + "  Prime? " + isPrime);
        scanner.close();
    }

    public static boolean isPrime(int number){
		if (number <= 1) {
			return false;
        }
		int c = 2;
		int mod;
		while (c < number) {
			mod = number % c;
			if (mod == 0) { 
			   return false;
            } else {
                c++;
            }
		}
		return true;
	}

}