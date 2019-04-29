package course2Week4Assignments;

/**
 * Create a gray scale version of an image by setting all color components of each pixel to the same value.
 * 
 * @author Diane Wajslic
 * @version 31/12/2018 
 */

import edu.duke.*;
import java.io.*;

public class BatchGrayScaleConverter {
	// Create a gray scale version of an image by setting all color components of each pixel to the same value.
	public ImageResource makeGray(ImageResource inImage) {
		// create a blank image of the same size
		ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
		for(Pixel pixel : outImage.pixels()) {
			//look at the corresponding pixel in inImage
			Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
			//compute inPixel's red + inPixel's blue + inPixel's green
			//divide that sum by 3 (call it average)
			int average = (inPixel.getRed() + inPixel.getGreen() + inPixel.getBlue()) / 3;
			//set pixel's red to average
			pixel.setRed(average);
			//set pixel's green to average
			pixel.setGreen(average);
			//set pixel's blue to average
			pixel.setBlue(average);
		}
		return outImage;
	}
	
	// Select image files from images folder and convert to grayScale
	// Save file in same directory as copy-filename
	public void selectAndConvertToGray() {
		DirectoryResource dr = new DirectoryResource();
		for(File f : dr.selectedFiles()) {
			ImageResource inImage = new ImageResource(f);			
			String fName = inImage.getFileName();
			String grayFileName = "copy-" + fName;
			ImageResource gray = makeGray(inImage);
			gray.setFileName(f.getParent() + "\\" + grayFileName);
			gray.draw();
			gray.save();
		}
	}
	
	public void testGray() {
		ImageResource ir = new ImageResource();
		ImageResource gray = makeGray(ir);
		gray.draw();
	}

	public static void main(String[] args) {
		BatchGrayScaleConverter bg = new BatchGrayScaleConverter();
		bg.selectAndConvertToGray();

	}

}
