import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.Scanner;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

public class RecipeTab extends Tab
{
	VBox referenceTo = null;
	private String workingDirectory;
	
	public RecipeTab(String higherWorkingDirectory) throws MalformedURLException
	{
		workingDirectory = higherWorkingDirectory + "\\Recipes";
		
		
		System.out.println("new working directory is " + workingDirectory);
		File testDirectory = new File(workingDirectory);
		if(!testDirectory.exists()) 
			if(!testDirectory.mkdirs())System.out.println("Failed to make directory " + workingDirectory);
		setStandardView();
		
		this.setText("Recipes");
		this.setClosable(false);
	}
	private void setEditRecipeView(String recipeName)
	{
		String recipe = null;
		String time = null;
		String ingredients = null;
		if(new File(workingDirectory + "/" + recipeName).exists())
		{
			File recipeFile = new File(workingDirectory + "/" + recipeName + "/recipe.txt");
			if(recipeFile.exists())
			{
				try
				{
					recipe = new Scanner(recipeFile).useDelimiter("\\Z").next();
				} catch (FileNotFoundException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			File dataFile = new File(workingDirectory + "/" + recipeName + "/data.txt");
			if(dataFile.exists())
			{
				try
				{
					Scanner scanner = new Scanner(dataFile);
					if(scanner.hasNextLine())
					time = scanner.nextLine();
					if(scanner.hasNextLine())
					ingredients = scanner.nextLine();
					
				} catch (FileNotFoundException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
		
			BorderPane editRecipeBorderPane = new BorderPane();
				ScrollPane editRecipeScrollPane = new ScrollPane();
					VBox editRecipeVBox = new VBox();
						HBox editRecipeTopOptions = new HBox();
							Label nameLabel = new Label("Name:");
							nameLabel.setPadding(new Insets(0,10,0,20));
							TextField nameTextBox = null;
							if(recipeName == null)
							{
								nameTextBox = new TextField();
								nameTextBox.setPadding(new Insets(0,100,5,0));
							}
							Label timeLabel = new Label("Time: ");
							timeLabel.setPadding(new Insets(0,0,0,10));
							TextField timeTextBox = new TextField();
							timeTextBox.setText(time);
							timeTextBox.setPadding(new Insets(0,100,5,0));
						editRecipeTopOptions.getChildren().add(nameLabel);
						if(nameTextBox != null)editRecipeTopOptions.getChildren().add(nameTextBox);
						else 
						{
							Label recipeLabel = new Label(recipeName);
							recipeLabel.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
							editRecipeTopOptions.getChildren().add(recipeLabel);
						}
						editRecipeTopOptions.getChildren().addAll(timeLabel,timeTextBox);
						Label recipeTextFieldLabel = new Label("Recipe:");
						recipeTextFieldLabel.setPadding(new Insets(0,0,0,20));
						HBox recipeTextAreaOrganizer = new HBox();
						recipeTextAreaOrganizer.setPadding(new Insets(0,0,0,20));
							TextArea recipeTextArea = new TextArea();
							recipeTextArea.prefWidthProperty().bind(editRecipeScrollPane.widthProperty());
							recipeTextArea.setPrefHeight(500);
						recipeTextAreaOrganizer.getChildren().add(recipeTextArea);
						HBox ingredientOptionHBox = new HBox();
							Label ingredientLabel = new Label("Ingredients");
							ingredientLabel.setPadding(new Insets(5,10,0,0));
							Button addRecipeButton = new Button("add");
							addRecipeButton.setStyle("-fx-background-radius: 0");
						ingredientOptionHBox.setPadding(new Insets(5,0,2,20));
						ingredientOptionHBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0,0,2,0))));
						ingredientOptionHBox.getChildren().addAll(ingredientLabel,addRecipeButton);
						VBox ingredientVBox = new VBox();
							if(ingredients != null)
							{
								Scanner ingredientParser = new Scanner(ingredients);
								ingredientParser.useDelimiter(";");
								while(ingredientParser.hasNext())
								{
										HBox ingredientHBox = new HBox();
											BorderPane ingredientBorderPane = new BorderPane();
										//		Label
									//ingredientVBox.;
								}
							}
							
					editRecipeVBox.getChildren().addAll(editRecipeTopOptions,recipeTextFieldLabel,recipeTextAreaOrganizer,ingredientOptionHBox);
				editRecipeScrollPane.setPadding(new Insets(8,5,5,5));
				//editRecipeScrollPane.fitToHeightProperty();
				editRecipeScrollPane.setContent(editRecipeVBox);
				Pane editRecipeOptionsPane = new Pane();
				editRecipeOptionsPane.setPadding(new Insets(0,0,5,0));
					Button editRecipeSaveButton = new Button("Save");
					editRecipeSaveButton.setStyle("-fx-background-radius: 0");
					editRecipeSaveButton.setLayoutX(5);
					editRecipeSaveButton.setLayoutY(5);
					editRecipeSaveButton.setOnMouseClicked(e -> saveRecipeButton_Click(e));
					Button editRecipeCancelButton = new Button("Cancel");
					editRecipeCancelButton.setStyle("-fx-background-radius: 0");
					editRecipeCancelButton.setLayoutX(50);
					editRecipeCancelButton.setLayoutY(5);
				editRecipeOptionsPane.getChildren().add(editRecipeSaveButton);
				editRecipeOptionsPane.getChildren().add(editRecipeCancelButton);
			editRecipeBorderPane.setCenter(editRecipeScrollPane);
			editRecipeBorderPane.setBottom(editRecipeOptionsPane);
		this.setContent(editRecipeBorderPane);
	}
	private void saveRecipeButton_Click(MouseEvent e)
	{
		try
		{
			setStandardView();
		}
		catch(MalformedURLException exception)
		{
			
		}
	}
	public void setStandardView() throws MalformedURLException
	{
			BorderPane recipeLayoutPane = new BorderPane();
				Pane addRecipePane = new Pane();
				addRecipePane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2,0,0,0))));
					Button addRecipe = new Button("Add Recipe");
					addRecipe.setLayoutX(3);
					addRecipe.setStyle("-fx-background-radius: 0");
					addRecipe.setLayoutY(5);
					addRecipe.setOnMouseClicked(e -> addRecipeButton_Click(e));
					Button refreshRecipeList = new Button("Refresh");
					refreshRecipeList.setStyle("-fx-background-radius: 0");
					refreshRecipeList.setLayoutX(85);
					refreshRecipeList.setLayoutY(5); 
					refreshRecipeList.setOnMouseClicked(e -> refreshButton_Clicked(e, referenceTo));
				ScrollPane recipeScrollPane = new ScrollPane();
					VBox recipeList = new VBox();
					referenceTo = recipeList;
					recipeList.prefWidthProperty().bind(recipeScrollPane.widthProperty());
					recipeList.setPadding(new Insets(0,15,0,0));
					refreshRecipeList(recipeList);
				recipeScrollPane.setContent(recipeList);
				addRecipePane.getChildren().addAll(addRecipe,refreshRecipeList);
			recipeLayoutPane.setTop(addRecipePane);
			recipeLayoutPane.setCenter(recipeScrollPane);
		this.setContent(recipeLayoutPane);
	}
	private void addRecipeButton_Click(MouseEvent e)
	{
		setEditRecipeView(null);
	}
	private void refreshButton_Clicked(MouseEvent e, VBox f) 
	{
		try
		{
			refreshRecipeList(f);
		} catch (MalformedURLException e1)
		{
			
		}
	}
	private void refreshRecipeList(VBox recipeListRefresh) throws MalformedURLException
	{
		recipeListRefresh.getChildren().clear();
		File recipeDirectory = new File(workingDirectory);
		for (File sub : recipeDirectory.listFiles())
        {
			if(sub.isDirectory())
			{
		            HBox recipeBox = new HBox();
		        		String recipeName = sub.getName();
		            	Label recipeLabel = new Label(recipeName);
		            	recipeLabel.setFont(Font.font("Arial", FontPosture.REGULAR, 20));
		            		HBox recipeOptions = new HBox();
		            		System.out.println(workingDirectory + "\\edit.jpg");
		            			File editButton = new File(workingDirectory + "\\edit.jpg");
		            			System.out.println("the file exists: " + editButton.exists());
		            			Image editButtonJPG = new Image(editButton.toURI().toURL().toExternalForm());
		            			ImageView editButtonImageView = new ImageView(editButtonJPG);
		            			editButtonImageView.setFitWidth(20);
		            	        editButtonImageView.setPreserveRatio(true);
		            			Button editRecipeBt = new Button("",editButtonImageView);
		            			editRecipeBt.setStyle("-fx-background-radius: 0");
		            			editRecipeBt.setPadding(new Insets(2,2,2,2));
		            			editRecipeBt.setOnMousePressed(e -> editButtonMouseDown(e));
		            			editRecipeBt.setOnMouseReleased(e -> editButtonMouseReleased(e));
		            			Button deleteRecipeBt = new Button("X");
		            			deleteRecipeBt.setStyle("-fx-background-radius: 0;-fx-text-fill: Red");
		            		recipeOptions.setAlignment(Pos.CENTER_RIGHT);
		            		recipeOptions.getChildren().add(editRecipeBt);
		            		recipeOptions.getChildren().add(deleteRecipeBt);
		            recipeBox.getChildren().add(recipeLabel);
		            recipeBox.getChildren().add(recipeOptions);
		            HBox.setHgrow(recipeOptions, Priority.ALWAYS);
		            recipeBox.setPadding(new Insets(1,1,1,1));
		            recipeBox.prefWidthProperty().bind(recipeListRefresh.widthProperty());
		            recipeBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2,2,2,2))));
		        recipeListRefresh.getChildren().add(recipeBox);
			}
        }
	}
	private void editButtonMouseDown(MouseEvent e)
	{
		File editButtonFile = new File(workingDirectory + "\\InverseEdit.jpg");
		Image editButtonJPG;
		try
		{
			editButtonJPG = new Image(editButtonFile.toURI().toURL().toExternalForm());
			ImageView editButtonImageView = new ImageView(editButtonJPG);
			editButtonImageView.setFitWidth(20);
	        editButtonImageView.setPreserveRatio(true);
			((Button)e.getSource()).setGraphic(editButtonImageView);
		} catch (MalformedURLException e1)
		{
			//do nothing
		}
	}
	private void editButtonMouseReleased(MouseEvent e)
	{
		File editButton = new File(workingDirectory + "\\Edit.jpg");
		Image editButtonJPG;
		try
		{
			editButtonJPG = new Image(editButton.toURI().toURL().toExternalForm());
			ImageView editButtonImageView = new ImageView(editButtonJPG);
			//editButtonImageView.setViewport(new Rectangle2D(626,626,626,626));
			editButtonImageView.setFitWidth(20);
	        editButtonImageView.setPreserveRatio(true);
			((Button)e.getSource()).setGraphic(editButtonImageView);
		} catch (MalformedURLException e1)
		{
			//do nothing
		}
	}
	
}
