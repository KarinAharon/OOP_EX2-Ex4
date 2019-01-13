package Algoritmim;
import GIS.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

/**
 * This class scan a folder in recursive way and find csv files. 
 * it also sending those files to csv2layer function,
 * after that it add the layers to any project and make it a KML file.
 * 
 * @author Mor Danino and Karin Aharon
 *
 */
public class MultiCSV {

	/**
	 * The variables of the class
	 */
	private create_Layer c;
	GIS_project myProject;
	GIS_project myProject_temp;
	
	/**
	 * The constructor of the class
	 */
	public MultiCSV() {
		
		myProject = new GisProject();
		myProject_temp = new GisProject();
		
	}

	/**
	 * This function scan a folder in recursive way and find csv files.
	 * it also sending those files to csv2layer function.
     * after that it add the layers to any project.
	 * @param folderPath the path of the folder
	 * @return project
	 * @throws Exception
	 */
	public GIS_project scanFolder(String folderPath) throws Exception  {

		File folder = new File(folderPath);

		
			if(folder.isDirectory()) {
				File[] files = folder.listFiles();
				for (int i = 0; i< files.length; i++) {
					if (files[i].isDirectory()) {
						myProject_temp = scanFolder(files[i].getAbsolutePath());
						for(GIS_layer layer : myProject_temp) {
							myProject.add(layer);
						}
					}
					if (isCsv(files[i])==true) {
						GIS_layer myLayer = c.csv2layer(files[i].getName());
						myProject.add(myLayer);
					}

				}
			}
		return myProject;
	}

	/**
	 * This function change the string of the path's folder to File.
	 * @param dirname the path of the folder
	 * @return
	 */
	public File String2file(String dirname) {
		return new File(dirname);
	}

	/**
	 * This function checks if the file is a csv file.
	 * @param file
	 * @return
	 */
	public boolean isCsv(File file) {
		return file.getName().endsWith(".csv");
	}


	/**
	 * This function received a project and send its layers to the
	 * function layer2kml which make any layer to a kml file.
	 * @param project any project
	 */
	public void project2kml(GIS_project project)  {

		try {
			for(GIS_layer layer : project) {
				c.layer2kml(layer);
			}
		}
		catch(Exception e) {};

	}}


