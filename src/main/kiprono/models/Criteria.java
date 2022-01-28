package main.kiprono.models;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Criteria {
	private int fileCount;
	private int folderCOunt;
	private String extension = null;
	private Path folderPath = null;
	
	
	// constructor
	public Criteria(String extension, Path folderPath) {
		this.extension = extension;
		this.folderPath = folderPath;
		this.fileCount = 0;
		this.folderCOunt = 0;
	}
	
	// default constructor
	public Criteria() {
		this.extension = null;
		this.folderPath = null;
		this.fileCount = 0;
		this.folderCOunt = 0;
	}
	
	public Path getFolderPath() {
		return folderPath;
	}
	
	public void setFolderPath(Path folderPath) {
		this.folderPath = folderPath;
	}
	
	public int getCount() {
		return fileCount;
	}
	
	public void setCount(int fileCount) {
		this.fileCount = fileCount;
	}
	
	public String getExtension() {
		return extension;
	}
	
	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	public void addCount() {
		this.fileCount += 1;
	}	

	public void addFolderCount() {
		this.folderCOunt += 1;
	}

	public int getFolderCount() {
		return folderCOunt;
	}

	public void setFolderCount(int folderCOunt) {
		this.folderCOunt = folderCOunt;
	}
	
	/*
	 * @return Count: number of times the file appeared in folder
	 * 
	 * */
	public int searchFile() {
		
		// check if it is null, return zero
		if(this.extension == null) {
			System.out.println("Error! provide extension");
			return 0;
		} else if (!Files.isDirectory(this.folderPath)) {
            throw new IllegalArgumentException("Path must be a directory!");
        } else {
			// crawl the folder looking for files with given extension
			// check if the folder is present in disk
			try {
				this.fileCount = findFile();
			} catch (IOException ex) {
				ex.printStackTrace();
				System.out.println();
			}
		}
		return this.fileCount;
	}

	/* @ return the number of sub-folders in a folder
	 * 
	 * 
	 * */
	
	public int searchFolder() {
		
		// check if it is null, return zero
		if(this.extension == null) {
			System.out.println("Error! provide extension");
			return 0;
		} else if (!Files.isDirectory(this.folderPath)) {
			throw new IllegalArgumentException("Path must be a directory!");
		} else {
			// crawl the folder looking for files with given extension
			// check if the folder is present in disk
			try {
				this.folderCOunt = countFolders();
			} catch (IOException ex) {
				ex.printStackTrace();
				System.out.println();
			}
		}
		return this.folderCOunt;
	}
	/*
	 * Helper function to crawl the folders and sub-folders
	 * credit: mkyong.com
	 * 
	 * */
	
	private int findFile() throws IOException{
		List<String> result;
		
		try(Stream<Path> walk = Files.walk(this.folderPath)){
			result = walk.filter(p -> !Files.isDirectory(p)).map(p -> p.toString().toLowerCase()).filter(f -> f.endsWith(this.extension)).collect(Collectors.toList());
		}
		
		this.fileCount = result.size();
		return result.size();
	}

	/*
	 * Helper function to crawl and count sub-folders
	 * credit: mkyong.com
	 * 
	 * */

	private int countFolders() throws IOException{
		List<String> result;
		
		try(Stream<Path> walk = Files.walk(this.folderPath)){
			result = walk.filter(p -> Files.isDirectory(p)).map(p -> p.toString().toLowerCase()).collect(Collectors.toList());
		}
		
		this.folderCOunt = result.size();
		return result.size();
	}

	
}
