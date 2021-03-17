import java.io.*;
import java.util.*;

class ReadFile {
    public static String separater = "====================================================";
	private static final void myDebug(String str) {
		System.out.println("----------------------->(<**" + str + "**>)");
		System.out.println("\n");
	}

    private static int caseNum;

    private static void processCase(BufferedReader input, BufferedWriter output) {
        try {
            int numCases = Integer.parseInt(input.readLine());
            List<Pair<String, String>> list = new ArrayList<Pair<String, String>>();
            String[] arr;
            while (numCases != 0) {
                arr = input.readLine().split("\\s+");
                Pair<String, String> p = new Pair<String, String>(arr[0], arr[1]);
                list.add(p);
                numCases--;
            }

            String result = String.valueOf(Solution.processList(list));
            output.write("Case #" + caseNum + ": " + result + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processFile(String filenameWithoutExtension) {
        try {
			BufferedWriter outputBuffer = new BufferedWriter(new FileWriter(filenameWithoutExtension + ".out",false));
            BufferedReader inputBuffer = new BufferedReader(new FileReader(filenameWithoutExtension + ".in"));

            int numCases = Integer.parseInt(inputBuffer.readLine());
            caseNum = 1;
            while (numCases != 0) {
                processCase(inputBuffer, outputBuffer);
                numCases--;
                caseNum++;
            }

            outputBuffer.close();
        } catch (IOException e) {
            myDebug("no file named " + filenameWithoutExtension);
            System.out.println(separater);
            return;
        }
    }

    public static void main(String[] args) {
        String filename = args[0];
        processFile(filename);
    }
}
