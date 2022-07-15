package robatortas.code.files;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Comparator {

	private Scanner readInput = new Scanner(System.in);
	private String dir;
	
	private String correct;

	// The image that will be compared with all the other images.
	private BufferedImage image;
	// All the images the image is comparing itself to.
	private BufferedImage comparedImage;
	
	public Comparator() {
		console();
	}
	
	public void console() {
		System.out.println("\nWELCOME TO IMAGE COMPARATOR by Robatortas\n\n" + 
				"This program is for comparing images and deleting the doppleganger images, only leaving one image\n"
				+ "This is specifically used for some stuff I am working on, it is not intended for public use.\n\n"
				+ "If you want to use this program, suit yourself, this code is open source so if you want to use it you can! ;)\n\n");
		
		System.out.println("Input the directory where you want to compare your images (I can wait...): ");
		this.dir = readInput.nextLine().toLowerCase();
		System.out.println("So your directory is " + dir + ", is that correct?");
		this.correct = readInput.nextLine().toLowerCase();
		
		try {
			switch(correct.charAt(0)) {
			case 'y': 
				System.out.println("Lets continue then!\n");
				System.out.println("Starting comparator... (Let me handle this now!)\n");
				compares();
				break;
			case 'n': System.out.println("ABORTING"); System.exit(0);
			break;
			}
		} catch(IndexOutOfBoundsException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(0);
		}
		
		System.out.println("\nComparation is done!\n\nYour comparated images are processed and in the same folder location.\n\n"
				+ "Thank you for using Image Comparator and have a wonderful day!");
	}
	
	public void compares() {
		String dots = "comparing...";
		for(int i = 0; i < dots.length(); i++) {
			try {
				Thread.sleep(100);
				System.out.print(dots.charAt(i));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.print("\n\n");
		
		try {
			int fileSize = new File(dir).list().length;
			System.out.println("Num of files: " + fileSize + "\n***************");
			
			int[] pixels = null;
			int[] comparedPixels = null;
			
			String[] filesInDir = new File(dir).list();
			
			int originalFile = 0;
			
//			for(int k = 0; k < fileSize; k++)
			while(originalFile < fileSize) {
			for(int i = 0; i < fileSize; i++) {
				System.out.println("COMPARING ====> " + filesInDir[i]);
				// Without the getClass stuff, it gets files from the whole drive, not just from the resources.
				image = ImageIO.read(new File(dir + "\\" + filesInDir[originalFile]));
				int w = image.getWidth();
				int h = image.getHeight();
				pixels = new int[w*h];
				image.getRGB(0, 0, w, h, pixels, 0, w);
				
				comparedImage = ImageIO.read(new File(dir + "\\" + filesInDir[i]));
				int ww = comparedImage.getWidth();
				int hh = comparedImage.getHeight();
				comparedPixels = new int[ww*hh];
				comparedImage.getRGB(0, 0, ww, hh, comparedPixels, 0, ww);
				
				int RGB = 0;
				int comparedRGB = 0;
				
				// I am separating pixels just in case we might use it for the future!
				// And I know I could just assign image.getRGB() to a variable, but it's better this way for future use.
				
				for(int j = 0; j < pixels.length; j++) {
					int a = (pixels[j] & 0xff000000) >> 24;
					int r = (pixels[j] & 0xff0000) >> 16;
					int g = (pixels[j] & 0xff00) >> 8;
					int b = (pixels[j] & 0xff);
					
					RGB = a << 24 | r << 16 | g << 8 | b;
				}
				
				for(int j = 0; j < comparedPixels.length; j++) {
					int a = (comparedPixels[j] & 0xff000000) >> 24;
					int r = (comparedPixels[j] & 0xff0000) >> 16;
					int g = (comparedPixels[j] & 0xff00) >> 8;
					int b = (comparedPixels[j] & 0xff);
					
					comparedRGB = a << 24 | r << 16 | g << 8 | b;
				}
				
				if(RGB == comparedRGB && w*h == ww*hh && i != originalFile) {
					System.err.println("Match");
					new File(dir + "\\" + filesInDir[i]).delete();
				} else {
					System.out.println("Pass");
				}
				
				if(i == fileSize-1 && originalFile <= fileSize) {
					originalFile++;
					System.out.println("*************");
				}
				
				Thread.sleep(2);
			}
			}
		} catch (IOException e) {
			
		} catch (InterruptedException e) {
			System.out.println("\n");
			System.err.println(e.getMessage());
			System.out.println("ABORTING");
			System.exit(0);
		}
	}
}