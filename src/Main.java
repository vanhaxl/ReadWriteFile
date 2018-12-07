import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    private static final String INPUT_FILE = "input/input1.txt";
    private static final String OUTPUT_FILE = "output/test.output1.txt";

    public static void main(String[] args) {
        long a = System.currentTimeMillis();
        fileStreamUsingBufferedReader(INPUT_FILE);
        long b = System.currentTimeMillis();
        System.out.println("time: " + (b-a));
    }

    private static void fileStreamUsingBufferedReader(String fileName) {
        try {
            BufferedReader br = Files.newBufferedReader(Paths.get(fileName));

            //read and write header
            String header = br.readLine();
            String newHeader = header.substring(0, 52) + "EXP" + header.substring(55) + "\n";
            writeToFile(newHeader);

            // body data
            String line = "";
            String content = "";
            BigInteger count = new BigInteger("1000777716920000000");
            BigInteger increase = new BigInteger("1");
            String footer = "";
            while ((line = br.readLine()) != null) {
                if (line.startsWith("9")) {
                    footer = line;
                    break;
                }
                StringBuffer stringBuffer = new StringBuffer("4");
                stringBuffer.append(line.substring(1, 62));
                count = count.add(BigInteger.ONE);
                stringBuffer.append(count);
                stringBuffer.append(line.substring(81, 149));
                stringBuffer.append("00");
                stringBuffer.append(getSpaces(25));
                stringBuffer.append(line.substring(150, 236));
                stringBuffer.append(getSpaces(15));
                stringBuffer.append("0059");
                stringBuffer.append(line.substring(247, 252));
                stringBuffer.append(getSpaces(175));
                stringBuffer.append(line.substring(455, 487));
                stringBuffer.append(getSpaces(106));
                stringBuffer.append("\n");

                //write
                writeToFile(new String(stringBuffer));
            }

            writeToFile(footer);


            br.close();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    private static String getSpaces(int spaceNumber) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < spaceNumber; i++) {
            stringBuffer.append(" ");
        }
        return new String(stringBuffer);
    }

    private static void writeToFile(String content) {
        try {
            FileWriter fw = new FileWriter(OUTPUT_FILE, true); //the true will append the new data
            fw.write(content);//appends the string to the file
            fw.close();
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }
}
