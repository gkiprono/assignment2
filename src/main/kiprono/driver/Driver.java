package main.kiprono.driver;

import java.nio.file.Path;
import java.nio.file.Paths;

import main.kiprono.models.Criteria;
import main.kiprono.utils.Keyboard;

public class Driver {
	
	public static void main(String[] args) {
		Criteria myCriteria = new Criteria();
		Keyboard keyboard = Keyboard.getKeyboard();
		Path path;
		String extension;
		
		System.out.println("***********************************");
		path = Paths.get(keyboard.readString("Enter the path: "));
		extension = keyboard.readString("What Extension: ");
		
		myCriteria.setExtension(extension);
		myCriteria.setFolderPath(path);
		
		System.out.println("There are: " + countFiles(myCriteria) + " " + extension + " files discovered." );
		System.out.println("There are: " + countFolders(myCriteria) + " folders discovered.");
		
		
	}
	
	public static int countFiles(Criteria criteria) {
		return criteria.searchFile();
	}
	
	public static int countFolders(Criteria criteria) {
		return criteria.searchFolder();
	}

}
