package aws.dynamodb.service;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;

import aws.dynamodb.connection.DynamoDBConnection;
import aws.dynamodb.model.DBRecord;

public class Service {

	private static Table table;
	private static Item item;
	private static Item dbItem;
	private static String response;

	public static String addRecord(DBRecord record) {
		table = DynamoDBConnection.createConnection().getTable("Records");
		item = new Item().withPrimaryKey("id", record.getId()).withString("fname", record.getFname())
				.withString("lname", record.getLname()).withString("gender", record.getGender())
				.withString("age", record.getAge());
		try {
			System.out.println(
					"Attempting to add record to table: " + table.getTableName() + " with id: " + record.getId());
			if (table.getItem("id", record.getId()) != null)
				throw new Exception("Record Already Exists");
			if (200 == table.putItem(item).getPutItemResult().getSdkHttpMetadata().getHttpStatusCode()) {
				response = "200";
				System.out.println(
						"Successfully added record to table: " + table.getTableName() + " with id: " + record.getId());
			} else {
				response = "Unable to add record";
				System.out.println(
						"Unable to add record to table: " + table.getTableName() + " with id: " + record.getId());
			}
		} catch (AmazonDynamoDBException e) {
			System.err.println("Error code: " + e.getErrorCode() + " and error message: " + e.getErrorMessage());
			response = e.getErrorCode();
		} catch (Exception e) {
			System.err.println("Error message: " + e.getMessage());
			response = e.getMessage();
		}
		return response;
	}

	public static String updateRecord(DBRecord record) {
		table = DynamoDBConnection.createConnection().getTable("Records");
		try {
			System.out.println(
					"Attempting to update record to table: " + table.getTableName() + " with id: " + record.getId());
			dbItem = table.getItem("id", record.getId());
			if (dbItem == null)
				throw new Exception("Record Doesn't Exist");
			item = new Item().withPrimaryKey("id", record.getId());
			if (null == record.getFname()) {
				item.withString("fname", dbItem.getString("fname"));
			} else {
				item.withString("fname", record.getFname());
			}
			if (null == record.getLname()) {
				item.withString("lname", dbItem.getString("lname"));
			} else {
				item.withString("lname", record.getLname());
			}
			if (null == record.getGender()) {
				item.withString("gender", dbItem.getString("gender"));
			} else {
				item.withString("gender", record.getGender());
			}
			if (null == record.getAge()) {
				item.withString("age", dbItem.getString("age"));
			} else {
				item.withString("age", record.getAge());
			}
			if (200 == table.putItem(item).getPutItemResult().getSdkHttpMetadata().getHttpStatusCode()) {
				response = "200";
				System.out.println(
						"Successfully updated record in table: " + table.getTableName() + " with id: " + record.getId());
			} else {
				response = "Unable to update record";
			}
		} catch (AmazonDynamoDBException e) {
			System.err.println("Error code: " + e.getErrorCode() + " and error message: " + e.getErrorMessage());
			response = e.getErrorCode();
		} catch (Exception e) {
			System.err.println("Error message: " + e.getMessage());
			response = e.getMessage();
		}
		return response;
	}

	public static DBRecord getRecord(int id) {
		table = DynamoDBConnection.createConnection().getTable("Records");
		DBRecord response = new DBRecord();
		try {
			System.out.println(
					"Attempting to read record from table: " + table.getTableName() + " with id: " + id);
			dbItem = table.getItem("id", id);
			if (dbItem == null)
				throw new Exception("Record Doesn't Exist");
			response.setId(dbItem.getInt("id"));
			response.setFname(dbItem.getString("fname"));
			response.setLname(dbItem.getString("lname"));
			response.setGender(dbItem.getString("gender"));
			response.setAge(dbItem.getString("age"));
			response.setResponse("200");
		} catch (AmazonDynamoDBException e) {
			System.err.println("Error code: " + e.getErrorCode() + " and error message: " + e.getErrorMessage());
			response.setResponse(e.getErrorCode());
		} catch (Exception e) {
			System.err.println("Error message: " + e.getMessage());
			response.setResponse(e.getMessage());
		}
		return response;
	}

	public static String deleteRecord(int id) {
		table = DynamoDBConnection.createConnection().getTable("Records");
		DeleteItemSpec deleteItemSpec = new DeleteItemSpec().withPrimaryKey("id", id);
		try {
			if (table.getItem("id", id) == null)
				throw new Exception("Record Doesn't Exist");
			System.out.println("Attempting to delete record from table: " + table.getTableName() + " with id: " + id);
			response = String.valueOf(table.deleteItem(deleteItemSpec).getDeleteItemResult().getSdkHttpMetadata().getHttpStatusCode());
            System.out.println("Deleted record with id: " + id + " successfully");
        } catch (AmazonDynamoDBException e) {
			System.err.println("Error code: " + e.getErrorCode() + " and error message: " + e.getErrorMessage());
			response = e.getErrorCode();
		} catch (Exception e) {
			System.err.println("Error message: " + e.getMessage());
			response = e.getMessage();
		}
		return response;
	}
}
