package traversals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import util.Coordinate;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;;

public class DepthFirstSearch {
	private static List<List<Coordinate>> paths = new ArrayList<>();

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
		//Collections.sort(paths, (a,b)->b.size()-a.size());
		
		int i=1;
		for(List<Coordinate> l: paths) {
			System.out.println(i++ +")." + l + "\n");
		}
		CreateTableRequest request = new CreateTableRequest()
	            .withAttributeDefinitions(new AttributeDefinition(
	                     "Name", ScalarAttributeType.S))
	            .withKeySchema(new KeySchemaElement("Name", KeyType.HASH))
	            .withProvisionedThroughput(new ProvisionedThroughput(
	                     new Long(10), new Long(10)))
	            .withTableName("Test Table");
		final AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder.defaultClient();
		/*try {
			ddb.createTable(request);
		}
		catch(AmazonServiceException ase) {
			ase.printStackTrace();
		}*/
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