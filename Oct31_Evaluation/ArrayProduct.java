package EvaluationOct31;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayProduct {

	static Scanner sc = new Scanner(System.in);

	public static int[] answer(int[] arr) {
		int[] answer = new int[arr.length];

		for(int i = 0; i < arr.length; i++) {
			int mul = 1;
			for(int j = 0; j < arr.length; j++) {
				if(i != j) {
					mul *= arr[j];
					answer[i] = mul;
				}
			}
		}

		return answer;
	}

	public static void main(String[] args) {
		//int[] input1 = {1,2,3,4};

		System.out.print("Enter your Array Size : ");
		int n = sc.nextInt();
		int[] input = new int[n];
		System.out.println("Enter "+n +" Numbers: ");
		for(int i = 0; i < n; i++) {
			input[i] = sc.nextInt();
		}

		System.out.println("Output is: "+Arrays.toString(answer(input)));

	}

}
