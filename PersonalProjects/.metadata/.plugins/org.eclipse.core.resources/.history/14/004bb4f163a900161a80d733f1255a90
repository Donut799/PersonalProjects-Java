import java.util.Scanner;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class IngredientVBox extends VBox
{
	public IngredientVBox(String ingredients)//semicolon separated list of ingredients and amounts
	{
		Scanner ingredientParser = new Scanner(ingredients);
		ingredientParser.useDelimiter(";");
		while(ingredientParser.hasNext())
		{
			this.addNew(ingredientParser.next(),ingredientParser.next());
		}
	}
	public void addNew(String ingredient, String amount)
	{
		IngredientHBox ingredientHBox = new IngredientHBox(ingredient, amount);
		ingredientHBox.prefWidthProperty().bind(this.widthProperty());
		this.getChildren().add(ingredientHBox);
		//System.out.println(this.getChildren().remove(ingredientHBox));
	}
	public void addNew()
	{
		addNew("","");
	}
	public String toString()
	{
		String returnString = "";
		for(Node ingredientHBox : this.getChildren())
		{
			returnString += ingredientHBox.toString();
		}
		return returnString;
	}
	private void deleteIngredient_Click(HBox deleteIngredientHBox)
	{
		System.out.println(deleteIngredientHBox);
		System.out.println(this.getChildren().remove(deleteIngredientHBox));
	}
	
	private class IngredientHBox extends HBox
	{
		TextField ingredientTextField;
		TextField amountTextField;
		myButton deleteButton;
		
		public IngredientHBox(String ingredient, String amount)
		{
			ingredientTextField = new TextField(ingredient);
			ingredientTextField.prefWidthProperty().bind(this.widthProperty());
			this.getChildren().add(ingredientTextField);
			amountTextField = new TextField(amount);
			amountTextField.setMinWidth(200);
			this.getChildren().add(amountTextField);
			deleteButton = new myButton(this);
			deleteButton.setOnMouseClicked(e -> deleteIngredient_Click(deleteButton.getHBox()));
			this.getChildren().add(deleteButton);
		}
		public String toString()
		{
			return ingredientTextField.getText() + ";" + amountTextField.getText() + ";";
		}
		
	}
	private class myButton extends Button
	{
		HBox parent;
		public myButton(HBox parent)
		{
			this.parent = parent;
			this.setText("X");
			this.setStyle("-fx-background-radius: 0; -fx-text-fill: Red");
		}
		public HBox getHBox()
		{
			return parent;
		}
	}
}
