package application;


import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.imageio.ImageIO;

/**
 * ImageSecretary is used for basic I/O operations in terms of images.
 * 
 * @author osman.yasal
 *
 */
public class ImageSecretary {

    public static final String IMAGE_LOCATION = "./images/";
	private ImageSecretary() {

	}

	/**
	 * Reads the image from your resources.
	 * 
	 * @param imName    name of the file
	 * @param extension of the file
	 * @return new ImageMatrix
	 * @throws IOException
	 */
	public static ImageMatrix readResourceImage(String path) throws IOException {
		return new ImageMatrix(ImageIO.read(new File(path)));
	}

	/**
	 * Writes the rendered image to your resources with the given name and extension
	 * 
	 * @param image     rendered image
	 * @param name      of the file
	 * @param extension of the file
	 * @return
	 */
	public static boolean writeFilteredImageToResources(ImageMatrix image, String name) {
		return writeImageToResources(image.getBufferedImage(),name);
	}

	public static List<String> getRawImageNames() {
		List<String> res = new ArrayList<>();
		File[] files = new File(IMAGE_LOCATION).listFiles();

		for (File file : files) {
			if (file.isFile() && !file.getName().contains("_")) {
				res.add(file.getName());
			}
		}
		return res;
	}

	/**
	 * Writes the rendered image to your resources with the given name as jpg
	 * 
	 * @param image
	 * @param name
	 * @return
	 */
	public static boolean writeImageToResources(RenderedImage image, String name) {
		boolean result = true;
		 String outputFilePath = "saved_photo/"+name;

        
         String fileExtension = outputFilePath.substring(outputFilePath.lastIndexOf(".") + 1);

        
         try {
				ImageIO.write(image, fileExtension, new File(outputFilePath));
			} catch (IOException e1) {
				
				 Date date1 = new Date();

			        SimpleDateFormat sdf1 = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

			        String formattedDate1 = sdf1.format(date1);

			        String filePath2 = "errors_and_infos/application_error.txt";
			        String text1 = String.format("[%s] [ERROR] %s",
			        		formattedDate1,e1);

			        try (FileWriter fileWriter = new FileWriter(filePath2, true)) {  
			        	  fileWriter.write(text1);
			       	 fileWriter.write(System.lineSeparator());
			          
			           
			        } catch (IOException eror2) {
			            eror2.printStackTrace();
			        }
				result = false;
			}

		return result;
	}

}
