import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class BinarySearchTree<T ,K extends Comparable<K>>
{
	Node<T,K> root = null;
	public BinarySearchTree()
	{
		
	}
	public void add(T addObject, List<K> keyList)
	{
		for(K key : keyList)
		{
			this.add(addObject, key);
		}
	}
	public static List<String> stringToList(String convert)//not useful in this class, just to know where it is
	{
		//takes in a String and returns a list of the 
		//String and all substrings that are delimited by 
		//whitespace.
		List<String> returnList = new ArrayList<String>();
		Scanner scanner = new Scanner(convert);
		returnList.add(convert);
		while(scanner.hasNext())
		{
			returnList.add(scanner.next());
		}
		return returnList;
	}
	public void add(T addObject, K key)
	{
		if(root == null)
		{
			root = new Node<T,K>(null, addObject, key);//parent is null since it is the root of the tree
			return;
		}//implied else
		Node<T,K> traverseNode = root;
		while(true)
		{
			int compare = key.compareTo(traverseNode.getKey());
			if(compare == 0)
			{
				traverseNode.addContent(addObject);
				return;
			}
			else if(compare < 0)
			{
				if(traverseNode.getLeftChild() == null)
				{
					traverseNode.setLeftChild(new Node<T,K>(traverseNode, addObject, key));
					return;
				}
				//implied else
				traverseNode = traverseNode.getLeftChild();
			}
			else if(compare > 0)
			{
				if(traverseNode.getRightChild() == null)
				{
					traverseNode.setRightChild(new Node<T,K>(traverseNode, addObject, key));
					return;
				}
				//implied else
				traverseNode = traverseNode.getRightChild();
			}
		}
	}
	public List<T> getExact(K key)//returns the list of objects that have a key that exactly matches the key that is passes in
	{
		//will return null if nothing is found
		if(root == null)
		{
			return null;
		}//implied else
		Node<T,K> traverseNode = root;
		while(true)
		{
			int compare = key.compareTo(traverseNode.getKey());
			if(compare == 0)
			{
				return traverseNode.getContent();
			}
			else if(compare < 0)
			{
				if(traverseNode.getLeftChild() == null)
				{
					return null;
				}
				//implied else
				traverseNode = traverseNode.getLeftChild();
			}
			else if(compare > 0)
			{
				if(traverseNode.getRightChild() == null)
				{
					return null;
				}
				//implied else
				traverseNode = traverseNode.getRightChild();
			}
		}
	}
	public List<T> getPartial(K partialKey)//returns any lists of objects that have a key that includes, but is not limited to the key that is passed in
	{
		return null;//TODO
	}
	public String toString()
	{
		return recursiveToString(root);
	}
	private String recursiveToString(Node<T,K> currentNode)
	{
		String rightToString = "";
		String leftToString = "";
		if(currentNode.getLeftChild() != null)leftToString = recursiveToString(currentNode.getLeftChild());
		if(currentNode.getRightChild() != null)rightToString = recursiveToString(currentNode.getRightChild());
		return leftToString + currentNode.toString() + "\n" + rightToString;
	}
	private class Node<T ,K>
	{
		private K key;
		private List<T> content = new LinkedList<T>();
		private Node<T,K> rightChild;
		private Node<T,K> leftChild;
		private Node<T,K> parent;
		
		Node(Node<T,K> parent, T content, K key)
		{
			this.parent = parent;
			this.content.add(content);
			this.key = key;
		}
		K getKey()
		{
			return key;
		}
		List<T> getContent()
		{
			return content;
		}
		void addContent(T moreContent)
		{
			content.add(moreContent);
		}
		Node<T, K> getParent()
		{
			return parent;
		}
		Node<T, K> getRightChild()
		{
			return rightChild;
		}
		Node<T, K> getLeftChild()
		{
			return leftChild;
		}
		void setParent(Node<T,K> setParent)
		{
			this.parent = setParent;
		}
		void setRightChild(Node<T,K> setRightChild)
		{
			this.rightChild = setRightChild;
		}
		void setLeftChild(Node<T,K> setLeftChild)
		{
			this.leftChild = setLeftChild;
		}
		public String toString()
		{
			return content.toString();
		}
	}
}





























