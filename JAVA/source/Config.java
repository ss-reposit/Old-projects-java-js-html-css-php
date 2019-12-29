import java.io.*;
import java.util.*;

public class Config {
	private int panelX;
	private int panelY;
	private String panelColor = new String();

	private String nextLine = new String();
	private String codeLine = new String();

	private int inputConfirmed = 0;
	private int inputStage = 1;
	private boolean readCode = false;

	private final String INPUT_FILE_NAME = "C:\\path\\GAMEConfig\config_simulation.txt";
	File input;
	Scanner in;

	private final String CODE_FILE_NAME = "C:\\path\\GAMEConfig\\codeFile.txt";
	FileWriter fw;
	BufferedWriter bw;

	public Config() {
		input = new File(INPUT_FILE_NAME);
		try {
			in = new Scanner(input);
			initBufferedWriter();
			readConfig();
		} catch (IOException e) {
			System.out.println("ERROR: " + e);
			in.close();
			System.out.println("Terminating.");
			System.exit(0);
		}
	}

	private void initBufferedWriter() {

		try {
			fw = new FileWriter(CODE_FILE_NAME, true);//appends
			bw = new BufferedWriter(fw);
		} catch (IOException e) {
			System.out.println("ERROR: " + e);
			System.out.println("Terminating.");
			System.exit(0);
		}
	}

	private void readConfig() {
		while (in.hasNextLine()) {
			try {
				if (readCode == false) { // locks after readCode = true
					nextLine = in.nextLine();
					if (nextLine.charAt(0) == '#') {
						continue;
					}
					if (nextLine.equals("*#")) {
						readCode = true;
						try{
							new PrintWriter(CODE_FILE_NAME).close();
							//clears previous code in file before inputing new code
						}
						catch (IOException e) {
							System.out.println("ERROR: " + e);
							System.out.println("Terminating.");
							System.exit(0);
						}
						System.out.println("Start code token recieved.");
						continue;
					}
				}
			} catch (java.lang.StringIndexOutOfBoundsException e) {
				System.out.println("Config format success, reading data.");
			}
			if (panelX == 0 || panelY == 0 && inputStage == 1) { // if (not been read)
				try {
					panelX = in.nextInt();
					panelY = in.nextInt();
				} catch (java.util.InputMismatchException e) {
					System.out.println("ERROR: " + e);
					System.out.println("Required input specified in config.");
					System.out.println("Terminating.");
					System.exit(0);
				} catch (java.util.NoSuchElementException e) {
					System.out.println("ERROR: " + e);
					System.out.println("Config format error, please check readme.txt");
					System.out.println("Terminating.");
					System.exit(0);
				}
				if (panelX <= 0 || panelY <= 0) { // to make sure can check if read
					System.out.println("ERROR: Cannot set diemensions <= 0.");
					System.out.println("Terminating.");
					System.exit(0);
				}
				in.nextLine(); // clear buffer after in.nextInt() input
				inputConfirmed++;
			}

			if (panelColor.equals("") && inputStage == 2) { // not been read
				try {
					panelColor = in.nextLine();
				} catch (java.util.NoSuchElementException e) {
					System.out.println("ERROR: " + e);
					System.out.println("Config format error, please check readme.txt");
					System.out.println("Terminating.");
					System.exit(0);
				}

				if (panelColor.equals("")) { // redundent case? caught by nosuchelelment exception
					System.out.println("ERROR: COLOR cannot be null (\"\")");
					System.out.println("Terminating.");
					System.exit(0);
				}
				if (panelColor.equals("BLACK") || panelColor.equals("WHITE") || panelColor.equals("LIGHT_GRAY")) {
				} else {
					System.out.println("ERROR: COLOR can ONLY equal: BLACK, WHITE, LIGHT_GRAY.");
					System.out.println("Terminating." + panelColor);
					System.exit(0);
				}
				inputConfirmed++;
			}

			if (readCode) {
				try {
					codeLine = in.nextLine();
				} catch (java.util.NoSuchElementException e) {
					System.out.println("ERROR: " + e);
					System.out.println("Config format error, please check readme.txt");
					System.out.println("Terminating.");
					System.exit(0);
				}
				if (codeLine.equals("*#")) {
					System.out.println("End code token recieved.");
					readCode = false;
				} else {
					writeCodeFile(codeLine);
				}
			}

			if (inputConfirmed == inputStage) {
				inputStage++; // check if input made, and prepare for next input in next iteration
			}
		}
		System.out.println("Data read successfully.");
		in.close();
	}

	private void writeCodeFile(String line) {
		System.out.println("Writing codefile. " + line);
		try{
			bw.write(line);
			bw.newLine();
			bw.flush();
		}catch(IOException e) { //write AI customizable data and config AI
			System.out.println("ERROR: " + e);
			System.out.println("Terminating.");
			System.exit(0);
		}
	}

	public int getPanelX() {
		return panelX;

	}

	public int getPanelY() {
		return panelY;
	}

	public String getPanelColor() {
		return panelColor;
	}
}