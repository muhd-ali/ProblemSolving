import java.io.*;

class ReadFile {
    public static String separater = "====================================================";
	private static final void myDebug(String str) {
		System.out.println("----------------------->(<**" + str + "**>)");
		System.out.println("\n");
	}

    private static int numCases;
    private static int caseNum;

    private static void processCase(BufferedReader input, BufferedWriter output) {
        try {
            String result = input.readLine();
            output.write("Case #" + caseNum + ": " + result + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processFile(String filenameWithoutExtension) {
        try {
			BufferedWriter outputBuffer = new BufferedWriter(new FileWriter(filenameWithoutExtension + ".out",false));
            BufferedReader inputBuffer = new BufferedReader(new FileReader(filenameWithoutExtension + ".in"));

            numCases = Integer.parseInt(inputBuffer.readLine());
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
