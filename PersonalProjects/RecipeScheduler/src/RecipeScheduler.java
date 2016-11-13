import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Scanner;

import javafx.application.Application;

public class RecipeScheduler extends Application
{
	static String workingDirectory;
	static BinarySearchTree<String, String> recipeTree = new BinarySearchTree<String, String>();

	public static void main(String[] args)
	{
		/*File file = new File("C:/Users/Brendan/Documents/school/2016-2017/LinearDataStructures/HamletTestBooks/hamlet.txt");
		BinarySearchTree<String, String> recipeTree = new BinarySearchTree<String, String>();
		try
		{
			Scanner scanner = new Scanner(file);
			while(scanner.hasNext())
			{
				String input = scanner.next();
				recipeTree.add(input, input);
			}
			for(String output : recipeTree.getSortedList())
			{
				System.out.println(output);
			}
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/ //tests
		
		
		
		
		
		workingDirectory = System.getProperty("user.dir");
		
		File tempTest = new File(workingDirectory);
		if(!tempTest.exists())
			if(!tempTest.mkdirs())
				System.out.println("Creation of working directory failed");
		
		File recipeDirectory = new File(workingDirectory + "/recipes");
		if(recipeDirectory.exists())
		{
			for(File recipe : recipeDirectory.listFiles())
			{
				if(recipe.isDirectory() && !recipe.isFile())
				{
					String recipeName = recipe.getName();
					Scanner recipeParser = new Scanner(recipeName);
					recipeTree.add(recipeName, BinarySearchTree.stringToList(recipeName));//add itself to the tree
					
				}
			}
		}
		else
		{
			if(!recipeDirectory.mkdirs())
				System.out.println("Creation of recipes directory failed");
		}

			
		Application.launch(args);
		System.out.println("Exiting . . .");
	}
	@Override
	public void start(Stage primaryStage) throws MalformedURLException, NotImplementedException
	{
				TabPane tabPane = new TabPane();
					ScheduleTab scheduleTab = new ScheduleTab(workingDirectory,recipeTree);
					RecipeTab recipeTab = new RecipeTab(workingDirectory,recipeTree);
				tabPane.getTabs().add(scheduleTab);
				tabPane.getTabs().add(recipeTab);
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			Scene scene = new Scene(tabPane,1/2.0*screenSize.getWidth(),1/2.0*screenSize.getHeight());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
