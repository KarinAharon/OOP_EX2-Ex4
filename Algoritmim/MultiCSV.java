package Algoritmim;
import GIS.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

public class MultiCSV {

	private create_Layer c;
	GIS_project myProject;
	GIS_project myProject_temp;
	
	public MultiCSV() {
		
		myProject = new GisProject();
		myProject_temp = new GisProject();
		
	}

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

	public File String2file(String dirname) {
		return new File(dirname);
	}

	public boolean isCsv(File file) {
		return file.getName().endsWith(".csv");
	}


	public void project2kml(GIS_project project)  {

		try {
			for(GIS_layer layer : project) {
				c.layer2kml(layer);
			}
		}
		catch(Exception e) {};

	}}


