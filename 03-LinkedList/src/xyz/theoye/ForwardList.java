package xyz.theoye;

public class ForwardList<E> extends AbstractList<E> implements List<E> {

	/*链表存储大小*/
	protected int size = 0;
	
	/*第一个元素头指针*/
	Node<E>first;
	
	private static class Node<E>{
		
		Node<E> next; 
		E element ; 
		
		protected  Node(E element, Node<E>next ) {
			this.element = element;
			this.next = next;
		}
	}



	@Override
	public void add(E element) {
		Node<E> newNode = new Node<E>(element, null);
		
		if(size == 0 ) {
			this.first = newNode;
		}else {
			Node<E>lastNode=  findNode(size - 1);
			lastNode.next = newNode;
		}
		size++;
	}




	@Override
	public E get(int index) {
		Node<E>node  = findNode(index);
		return node.element;
	}




	@Override
	public E set(int index, E element) {
		Node<E>node  = findNode(index);
		E old = node.element;
		node.element = element;
		
		return old;
	}




	@Override
	public void add(int index, E element) {
		
		Node<E>node  = findNode(index);
		Node<E>newNode = new Node<E>(element, node.next); 
		node.next = newNode;
		size++;
	}




	@Override
	public E remove(int index) {
		Node<E>node =  findNode(index);
		E oldE = node.element;
		
		if(index ==0 ) {
			node = first;
			first = first.next;
			node.next = null;
		}
		else {
			Node<E> preNode = findNode(index - 1);
			preNode.next = node.next;
			node.next = null;
			node = null;
		}

		size--;
		return oldE;
	}




	@Override
	public int indexOf(E element) {
		Node<E> node = first; 
		
		/*如果输入空就找空*/
		if(element == null) {   //
			for(int i = 0;  i < size; i++)
			{
				node = node.next;
				if(element == null) {
					return i;
				}
			}
		}
		/*如果输入非空就找改元素*/
		else {
			for(int i = 0;  i < size; i++)
			{
				node = node.next;
				if(element.equals(node.element)) {
					return i;
				}
			}
		}
		
		/*找不到返回ELEMENT_NOT_FOUND*/
		return ELEMENT_NOT_FOUND;
	}




	@Override
	public void clear() {
		
		size = 0 ;
		
		/*第一个没了, 其他自动没了*/
		first = null;  
	}
	
	private Node<E> findNode(int index) {
		rangeCheck(index);
		
		Node<E>node = first; 
		for(int i = 0 ; i<index;i++) {
			node = node.next;
		}
		return node;
	}
	
	
	private void rangeCheck(int index) {
		if(index<0 || index>=size) {
			throw new IndexOutOfBoundsException();
		}
	}
	
	
	
	
	/**
	 * toString方法
	 */
	@Override
	public String toString() {
		
		
		StringBuilder string = new StringBuilder("size="+size+",");
		string.append("[");
		
		Node<E>node = first;
		
		for(int i = 0 ; i< size; i++)
		{
			if(i!=0) {
				string.append(",").append(node.element.toString());
			}
			else {
				string.append(node.element.toString());
			}
			
			
			node = node.next;
		}
		string.append("]");	
		
		return string.toString();
	}




	
}
