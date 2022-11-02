package EvaluationOct31;

import java.util.Arrays;

public class MyLinkedList {

	public Node first;
	public Node last;
	int size = 0;

	class Node {
		public int value;
		public Node next = null;

		public Node(int value) {
			this.value = value;
			this.next = null;
		}
	}

	public void add(int value) {
		Node newNode = new Node(value);
		if (last != null) {
			last.next = newNode;
		} 
		if(first == null)
			first = newNode;

		last = newNode;
		size++;
	}

	public int[] nextGreater() {
		Node head = first;
		int max = 0;
		int index = 0;
		int[] output = new int[size];
		while (head != null) {	
			//head = first;
			Node second = head.next;
			max = 0;

			while (second != null) {
				
				if (head.value < second.value) {
					max = second.value;
					break;
				}

				second = second.next;
			}
			output[index] = max;
			//System.out.print(max+" ");

			head = head.next;
			index++;
		}
		return output;
		//System.out.println(Arrays.toString(output));
	}

	public static void main(String[] args) {
		MyLinkedList myll = new MyLinkedList();
		myll.add(2);
		myll.add(7);
		myll.add(4);
		myll.add(3);
		myll.add(5);
		myll.add(6);

		System.out.println(Arrays.toString(myll.nextGreater()));
		
		
	}

}





