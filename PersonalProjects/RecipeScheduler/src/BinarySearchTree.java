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
		scanner.close();
		return returnList;
	}
	public static <R extends Comparable<R>> List<R> removeDuplicates(List<R> dirtyList)
	{
		BinarySearchTree<R,R> sorter = new BinarySearchTree<R,R>();
		for(R parse : dirtyList)
		{
			sorter.add(parse, parse);
		}
		dirtyList.clear();
		dirtyList = sorter.getSortedList();		
		List<R> cleanList = new LinkedList<R>();
		R checkForDuplicate = null;
		for(R item : dirtyList)
		{
			if(!item.equals(checkForDuplicate))cleanList.add(item);
			checkForDuplicate = item;
		}
		return cleanList;
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
	public boolean isEmpty()
	{
		return root == null;
	}
	public List<T> getPartial(K partialKey)//returns any lists of objects that have a key that includes, but is not limited to the key that is passed in
	{
		return null;//TODO
	}
	public List<T> getSortedList()
	{
		if(root != null)return getSortedListRecursive(root);
		else return new LinkedList<T>();
	}
	private List<T> getSortedListRecursive(Node<T,K> root)
	{
		List<T> returnList = new LinkedList<T>();
		if(root.getLeftChild() != null) returnList.addAll(getSortedListRecursive(root.getLeftChild()));
		returnList.addAll(root.getContent());
		if(root.getRightChild() != null) returnList.addAll(getSortedListRecursive(root.getRightChild()));
		return returnList;
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
	private class Node<TYPE ,KEY>
	{
		private KEY key;
		private List<TYPE> content = new LinkedList<TYPE>();
		private Node<TYPE,KEY> rightChild;
		private Node<TYPE,KEY> leftChild;
		//private Node<TYPE,KEY> parent; not necessary as far as i can tell
		
		Node(Node<TYPE,KEY> parent, TYPE content, KEY key)
		{
			//this.parent = parent; refer to commented property "parent"
			this.content.add(content);
			this.key = key;
		}
		KEY getKey()
		{
			return key;
		}
		List<TYPE> getContent()
		{
			return content;
		}
		void addContent(TYPE moreContent)
		{
			content.add(moreContent);
		}
		/*Node<TYPE, KEY> getParent()
		{
			return parent;
		}*/ //not used yet
		Node<TYPE, KEY> getRightChild()
		{
			return rightChild;
		}
		Node<TYPE, KEY> getLeftChild()
		{
			return leftChild;
		}
		/*void setParent(Node<TYPE,KEY> setParent)
		{
			this.parent = setParent;
		}*/ //refer to commented property "parent"
		void setRightChild(Node<TYPE,KEY> setRightChild)
		{
			this.rightChild = setRightChild;
		}
		void setLeftChild(Node<TYPE,KEY> setLeftChild)
		{
			this.leftChild = setLeftChild;
		}
		public String toString()
		{
			return content.toString();
		}
	}
}





























