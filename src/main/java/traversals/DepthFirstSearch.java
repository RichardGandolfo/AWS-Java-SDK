package traversals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import util.Coordinate;

public class DepthFirstSearch {
	private static List<List<Coordinate>> paths = new ArrayList<>();
	private static int minSize = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		int[][] test = {
				{ 0, -1, 0, 0, 0, 0, 0 },
				{ 0,  -1, 0, -1, 0, 0, 0 },
				{ 0, -1, 0, 0, -1, -1, 0 },
				{ 0, -1, 0, 0, 0, 0, 0 },
				{ 0, -1, 0, -1, 0, 0, 0 },
				{ 0, -1, 0, 0, 0, 0, 0 },
				{ 0,  -1, 0, -1, 0, 0, 0 },
				{ 0, -1, 0, 0, 0, -1, 0 },
				{ 0, 0, 0, 0, 0, 0, 0 },
				{ 0, -1, 0, -1, 0, 0, 0 }
		};
		
		getPath(test, new Coordinate(0,0), new Coordinate(6,0));
		Collections.sort(paths, (a,b)->b.size()-a.size());
		
		int i=1;
		for(List<Coordinate> l: paths) {
			System.out.println(i+")." + l + "\n");
			i++;
		}
		
	}
	
	public static void getPath(int[][] arr, Coordinate start, Coordinate end) {
		dfs( arr, start, end, new ArrayList<Coordinate>() );
	}
	
	private static void dfs(int[][] arr, Coordinate current, Coordinate end, List<Coordinate> result) {
		int row = current.getX();
		int column = current.getY();
		
		if(row < 0 || row >= arr.length || column < 0 || column >= arr[row].length || arr[row][column] == -1)
			return;
		
		result.add(current);
		
		if(current.equals(end)) {
			paths.add(result);
			return;
		}
		
		arr[row][column] = -1;
		dfs(arr, new Coordinate(row+1, column), end, new ArrayList<>(result));
		dfs(arr, new Coordinate(row-1, column), end, new ArrayList<>(result));
		dfs(arr, new Coordinate(row, column+1), end, new ArrayList<>(result));
		dfs(arr, new Coordinate(row, column-1), end, new ArrayList<>(result));
		arr[row][column] = 0;
	}
}