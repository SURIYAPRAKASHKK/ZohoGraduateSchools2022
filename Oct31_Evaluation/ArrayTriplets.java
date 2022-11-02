package EvaluationOct31;

import java.util.ArrayList;
import java.util.Collections;


public class ArrayTriplets {

	public static void main(String[] args) {
		int[] nums = {5,3,-5,2,1,-1,-2};

		ArrayList<ArrayList<Integer>> l = new ArrayList<>(); 
		for(int i = 0; i < nums.length; i++) {
			
			for(int j = 0; j < nums.length; j++) {
				
				for(int k = 0; k < nums.length; k++) {
					ArrayList<Integer> temp = new ArrayList<>();
					if(i != j && i != k && j != k) {
						if(nums[i] + nums[j] + nums[k] == 0) {
							temp.add(nums[i]);
							temp.add(nums[j]);
							temp.add(nums[k]);
							Collections.sort(temp);
							if(!l.contains(temp))
								l.add(temp);
						}
					}
				}
			}
		}
		System.out.println(l);

	}

}


