import java.io.*;
import java.util.Scanner;

class ReadCommandLine {
    public static String separater = "====================================================";
	private static final void myDebug(String str) {
		System.out.println("----------------------->(<**" + str + "**>)");
		System.out.println("\n");
	}

    private static void processCase(String str) {
        myDebug(str);
    }

    private static void processUserInput() {
        Scanner scan = new Scanner(System.in);
        String str = "";
        // int i = scan.nextInt();
        while (true) {
            System.out.print("Enter Input:> ");
            str = scan.next();
            processCase(str);
        }
    }

    public static void main(String[] args) {
        processUserInput();
    }
}
