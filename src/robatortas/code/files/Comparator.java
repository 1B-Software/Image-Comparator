package robatortas.code.files;

import java.util.Scanner;

public class Comparator {

	private Scanner readInput = new Scanner(System.in);
	private String dir;
	
	private String correct;
	
	public Comparator() {
		console();
	}
	
	public void console() {
		System.out.println("WELCOME TO IMAGE COMPARATOR\n\n" + 
				"This program is for comparing images and deleting the doppleganger images, only leaving one image\n"
				+ "This program is specifically used for some stuff I am working on, it is not intended for public use.\n\n"
				+ "If you want to use this program, suit yourself, this code is open source so if you want to use it you can! ;)\n\n"
				+ "-Made by Robatortas");
		
		System.out.println("Input the directory where you want to compare your images (I can wait...): ");
		this.dir = readInput.nextLine().toLowerCase();
		System.out.println("So your directory is " + dir + ", is that correct?");
		this.correct = readInput.nextLine().toLowerCase();
		
		try {
			switch(correct.charAt(0)) {
			case 'y': 
				System.out.println("Lets continue then!\n");
				System.out.println("Starting comparator...");
				compares();
				break;
			case 'n': System.out.println("Aborting..."); System.exit(0);
			break;
			}
		} catch(IndexOutOfBoundsException e) {
			System.err.println(e.getMessage());
			System.exit(0);
		}
	}
	
	public void compares() {
		String dots = "comparing...";
		for(int i = 0; i < dots.length(); i++) {
			try {
				Thread.sleep(500);
				System.out.print(dots.charAt(i));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
	}
}