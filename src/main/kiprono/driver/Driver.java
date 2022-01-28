package main.kiprono.driver;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

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
		
		//split the path by \ then access last element representing  the folder
		
		myCriteria.setFolderName(path.toString().split(Pattern.quote("\\"))[path.toString().split(Pattern.quote("\\")).length - 1]);
		
		//this.folderName = String.valueOf(folderPath.toString()).split("\\")[String.valueOf(folderPath.toString()).split("\\").length -1];
		
		System.out.println("There are: " + countFiles(myCriteria) + " " + extension + " files discovered in " + myCriteria.getFolderName());
		System.out.println("There are: " + countFolders(myCriteria) + " folders discovered in " + myCriteria.getFolderName());
		
		
	}
	
	public static int countFiles(Criteria criteria) {
		return criteria.searchFile();
	}
	
	public static int countFolders(Criteria criteria) {
		return criteria.searchFolder();
	}

}
