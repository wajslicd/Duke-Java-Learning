package course2Week4Assignments;

/**
 * Create a photographic negative of an image by setting all color components of each pixel to its opposite value.
 * 
 * @author Diane Wajslic
 * @version 31/12/2018 
 */

import edu.duke.*;
import java.io.*;

public class BatchInversions {
	
	public ImageResource makeInversion(ImageResource inImage) {
		ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
		for(Pixel p : outImage.pixels()) {
			Pixel inPixel = inImage.getPixel(p.getX(), p.getY());
			p.setRed(255 - inPixel.getRed()); 
			p.setGreen(255 - inPixel.getGreen());
			p.setBlue(255 - inPixel.getBlue());
		}
		return outImage;
	}
	
	// Select image files from images folder and invert to negative
	// Save file in same directory as inverted-filename
	public void selectAndConvert() {
		DirectoryResource dr = new DirectoryResource();
		for(File f : dr.selectedFiles()) {
			ImageResource inImage = new ImageResource(f);
			String fName = inImage.getFileName();
			String invertedFileName = "inverted-" + fName;
			ImageResource invert = makeInversion(inImage);
			invert.setFileName(f.getParent() + "\\" + invertedFileName);
			invert.draw();
			invert.save();
		}
	}
	
	public void testMakeInversion() {
		ImageResource ir = new ImageResource();
		ImageResource negative = makeInversion(ir);
		negative.draw();
	}

	public static void main(String[] args) {
		BatchInversions bi = new BatchInversions();
		//bi.testMakeInversion();
		bi.selectAndConvert();
	}

}
