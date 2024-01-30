package application;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Filters {
	/**
	 * 
	 * @param image
	 * @param percentage
	 * @return
	 * @throws IOException
	 */
	
	public static BufferedImage makeBlur(BufferedImage image,int percentage) throws IOException {
	
		BufferedImage result = null;
				

		ImageMatrix imgmatrix = new ImageMatrix(image);

		
	
		int blur =(int) (percentage*0.2);

		if(blur%2==0) {blur -= 1;}
		//System.out.println(blur);
		
		ImageMatrix newmatrix = new ImageMatrix(imgmatrix.getWidth(),imgmatrix.getHeight());
		for(int x = (int)blur / 2;x < imgmatrix.getImg().length -(int)blur / 2;x++) {
			for(int y = (int)blur / 2;y < imgmatrix.getImg()[x].length -(int)blur / 2;y++) {
				double sumred = 0;
				double sumgreen = 0;
				double sumblue = 0;
				for(int a = x-((int)blur / 2);a < x +1+ ((int)blur / 2);a++) {
					for(int b = y -((int)blur / 2);b < y+1+((int)blur / 2);b++) {
						sumred += imgmatrix.getRed(a, b);
						sumgreen += imgmatrix.getGreen(a, b);
						sumblue += imgmatrix.getBlue(a, b);
					}
				}
				int averagered = (int)(sumred/(blur*blur));
				int averagegreen = (int)(sumgreen/(blur*blur));
				int aaverageblue = (int)(sumblue/(blur*blur));
				int rgb = ImageMatrix.convertRGB(averagered, averagegreen, aaverageblue);
				
				
				newmatrix.setRGB(x, y, rgb);		
			}
		}
		
		result = newmatrix.getBufferedImage();
		
		
		return result;
	}
	
	/**
	 * 
	 * @param image
	 * @param percentage
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage makeSharp(BufferedImage image,int percentage) throws IOException{ 
		
		BufferedImage result = null;
				
		ImageMatrix orgmtrx = new ImageMatrix(image);
		ImageMatrix bluredmtrx = new ImageMatrix(makeBlur(image,percentage));
	
		ImageMatrix detailedmtrx = new ImageMatrix(orgmtrx.getImg().length,orgmtrx.getImg()[0].length);
		ImageMatrix sharpenmtrx = new ImageMatrix(orgmtrx.getImg().length,orgmtrx.getImg()[0].length);
		for(int x = 0;x < orgmtrx.getImg().length;x++) {
			for(int y = 0;y < orgmtrx.getImg()[x].length;y++) {
				int red = orgmtrx.getRed(x, y) - bluredmtrx.getRed(x, y);
				int green = orgmtrx.getGreen(x, y) - bluredmtrx.getGreen(x, y);
				int blue = orgmtrx.getBlue(x, y) - bluredmtrx.getBlue(x, y);
				int rgb = ImageMatrix.convertRGB(red, green, blue);
				detailedmtrx.setRGB(x,y,rgb);
			}
		}
		
		
		
		for(int x = 0;x < orgmtrx.getImg().length;x++) {
			for(int y = 0;y < orgmtrx.getImg()[x].length;y++) {
				int red = orgmtrx.getRed(x, y) + detailedmtrx.getRed(x, y);
				int green = orgmtrx.getGreen(x, y) + detailedmtrx.getGreen(x, y);
				int blue = orgmtrx.getBlue(x, y) + detailedmtrx.getBlue(x, y);
				int rgb = ImageMatrix.convertRGB(red, green, blue);
				if(bluredmtrx.getRGB(x, y) == -16777216) {
					sharpenmtrx.setRGB(x,y,-16777216);
				}
				else {
					sharpenmtrx.setRGB(x,y,rgb);
				}

			}
		}
		
		
		if(percentage < 5) {
			result = orgmtrx.getBufferedImage();
		}
		else {
			result = sharpenmtrx.getBufferedImage();
		}
		
		
		
		return result;
	}
	
	/**
	 * 
	 * @param image
	 * @param percentage
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage makeGrayscale(BufferedImage image,double percentage) throws IOException {
		
		BufferedImage result = null;
				
			ImageMatrix orgmtrx = new ImageMatrix(image);
			ImageMatrix grayedmtrx = new ImageMatrix(orgmtrx.getImg().length,orgmtrx.getImg()[0].length);
			
			for(int x = 0;x < orgmtrx.getImg().length;x++) {
				for(int y = 0;y < orgmtrx.getImg()[x].length;y++) {
					
					int red = (int) (orgmtrx.getRed(x, y)*0.2989);
					int green = (int) (orgmtrx.getGreen(x, y)*0.5870);
					int blue = (int) (orgmtrx.getBlue(x, y)*0.1140);
					int grayscale = red+green+blue;
					int grayRGB = (grayscale << 16) + (grayscale << 8) + grayscale;
					grayedmtrx.setRGB(x,y,grayRGB);
				}
			}
			if(percentage < 10) {
				result = orgmtrx.getBufferedImage();
			}
			else {
				result = grayedmtrx.getBufferedImage();
			}
			
		
		
	
		
		return result;
	}
	/**
	 * 
	 * @param image
	 * @param percentage
	 * @return
	 * @throws IOException
	 */
	
	public static BufferedImage makeEdgeDetection(BufferedImage image,int percentage)  throws IOException{ 
		
		BufferedImage result = null;
		BufferedImage orgimg = image;	
		
		ImageMatrix inputImage = new ImageMatrix(makeBlur(image,15));
		ImageMatrix outputImage = new ImageMatrix(inputImage.getWidth(),inputImage.getHeight());
		
     
		int degree = (int) (percentage*0.1);
		 int[][] sobelX = {{-degree, 0, degree}, {-2*degree, 0, 2*degree}, {-degree, 0, degree}};
	        int[][] sobelY = {{-degree, -2*degree, -degree}, {0, 0, 0}, {degree, 2*degree, degree}};

     
        for (int y = 1; y < inputImage.getHeight() - 1; y++) {
            for (int x = 1; x < inputImage.getWidth() - 1; x++) {
              
                int gx = 0, gy = 0;
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        int pixel = inputImage.getRGB(x + i, y + j);
                        int gray = (int) (0.299 * ((pixel >> 16) & 0xFF) + 0.587 * ((pixel >> 8) & 0xFF) + 0.114 * (pixel & 0xFF));
                        gx += sobelX[i + 1][j + 1] * gray;
                        gy += sobelY[i + 1][j + 1] * gray;
                    }
                }

               
                int magnitude = (int) Math.sqrt(gx * gx + gy * gy);
                magnitude = Math.min(255, Math.max(0, magnitude)); 
                int pixelValue = magnitude << 16 | magnitude << 8 | magnitude;
                outputImage.setRGB(x, y, pixelValue);
            }
        }

        if(percentage < 10) {
        	result = orgimg ;
        }
        else {
        	result = outputImage.getBufferedImage();
        }
		
		
		return result;
	}
	/**
	 * 
	 * @param image
	 * @param percentage
	 * @return
	 * @throws IOException
	 */

	public static BufferedImage changeConntrast(BufferedImage image,double percentage)  throws IOException{ 
		
		BufferedImage result = null;
				
		ImageMatrix orgmtrx = new ImageMatrix(image);
		ImageMatrix newmtrx = new ImageMatrix(orgmtrx.getWidth(),orgmtrx.getHeight());
		
		double p = percentage*0.015 + 0.2;
		
		for(int x = 0;x < orgmtrx.getImg().length;x++) {
			for(int y = 0;y < orgmtrx.getImg()[x].length;y++) {
				double gray = (orgmtrx.getRed(x, y)*0.2989 + orgmtrx.getGreen(x, y)*0.5870 + orgmtrx.getBlue(x, y)*0.1140);
				int red =  (int) ((orgmtrx.getRed(x, y)-gray)*p+gray);
				int green = (int) ((orgmtrx.getGreen(x, y)-gray)*p+gray);
				int blue =  (int) (int) ((orgmtrx.getBlue(x, y)-gray)*p+gray);
				int rgb = ImageMatrix.convertRGB(red, green, blue);
				newmtrx.setRGB(x,y,rgb);
			}
		}
   
		
		result = newmtrx.getBufferedImage();
		
		return result;
	}
	
	/**
	 * 
	 * @param image
	 * @param percentage
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage changeBrightness(BufferedImage image,double percentage)  throws IOException{ 
		
		BufferedImage result = null;
				
		
		ImageMatrix orgmtrx = new ImageMatrix(image);
		ImageMatrix newmtrx = new ImageMatrix(orgmtrx.getWidth(),orgmtrx.getHeight());
		
		double p = percentage*0.021 + 0.15;
		
		for(int x = 0;x < orgmtrx.getImg().length;x++) {
			for(int y = 0;y < orgmtrx.getImg()[x].length;y++) {
				int red = Math.min(255, (int) (orgmtrx.getRed(x, y)*p));
				int green = Math.min(255, (int) (orgmtrx.getGreen(x, y)*p));
				int blue = Math.min(255, (int) (orgmtrx.getBlue(x, y)*p));
				int rgb = ImageMatrix.convertRGB(red, green, blue);
				newmtrx.setRGB(x,y,rgb);
			}
		}
   
		
		result = newmtrx.getBufferedImage();
		
		
		return result;
	}

} 
