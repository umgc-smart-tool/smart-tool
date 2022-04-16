package edu.umgc.smart.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import edu.umgc.smart.model.Record;
import edu.umgc.smart.model.RecordType;


/*
 * To do:
 * 		get()
 * 		update()
 * 
 * Conflicts exist in:
 * 		createRecord() - This is due to the Record constructor. 
 * 			I can adjust this however you guys think is best - either in the Record class or here.
 * 
 * 		getRecordsByMainSearch() - Clashes with file types of Date and RecordType.
 * 								   I suspect these will rectify themselves once it's added on Git,
 * 								   but let me know if I need to make changes here.
 */

public class CsvDataAccessor implements DataAccessor {
	private List<Record> records = new ArrayList<>();
	
	public void loadFile() {
		String line = "";  
		String delim = ",";  
		try {   
			BufferedReader br = new BufferedReader(new FileReader("Records.csv"));  
			while ((line = br.readLine()) != null) {  
				String[] employee = line.split(delim);  
				// Calls createRecord function and adds the new Record to the records ArrayList
				records.add(createRecord(employee));
			}  //end while
			br.close();
		} //end try
		catch (IOException e) { 
			e.printStackTrace();  
		} //end catch
	} //end loadFile()

	public Record createRecord(String[] params) {
		Record rec = new Record(params[0], params[1], params[2], params[3], params[4], params[5], params[6], params[7], params[8]);
		return rec;
	} //end createRecord()


	@Override
	public Record get(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Record> getAll() {
		return records;
	}

	@Override
	public void save(Record record) {
		// Upon file close, saves the new Record info into the CSV file (File f)
		File f = new File("Records.csv");
		try {
			FileWriter fWriter = new FileWriter(f);
			for(Record i : records) {
				fWriter.append(String.join(",", i.toString()));
				fWriter.append("\n");
			}
			fWriter.flush();
			fWriter.close();
		}
		catch (IOException e) { 
			e.printStackTrace();  
		} //end catch
	}

	@Override
	public void update(int id, Record record) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Record t) {
		// TODO Auto-generated method stub
		for(Record i : records)
			if(i == t)
				records.remove(records.indexOf(t));
	}

	public Record[] getRecordsByMainSearch(String searchTerm) {
		Record[] arr = new Record[records.size()];
		int x = 0;
		for(Record i : records) {
			if(i.getAuthorFirstName() == searchTerm || 
				i.getAuthorLastName() == searchTerm ||
				i.getCategory() == searchTerm ||
				i.getDate() == searchTerm ||
				i.getDocumentType() == searchTerm ||
				i.getLocation() == searchTerm ||
				i.getReferenceNumber() == searchTerm ||
				i.getSummary() == searchTerm ||
				i.getTitle() == searchTerm)
				arr[x] = i;
			x++;
		}
		return arr;
	}

	public Record[] getRecordsByReferenceNum(String referenceNumber) {
		Record[] arr = new Record[records.size()];
		int x = 0;
		for(Record i : records) {
			if(i.getReferenceNumber() == referenceNumber)
				arr[x] = i;
			x++;
		}
		return arr;
	}

	public Record[] getRecordsByTitle(String title) {
		Record[] arr = new Record[records.size()];
		int x = 0;
		for(Record i : records) {
			if(i.getTitle() == title)
				arr[x] = i;
			x++;
		}
		return arr;
	}

	public Record[] getRecordsByRecordType(RecordType recordType) {
		Record[] arr = new Record[records.size()];
		int x = 0;
		for(Record i : records) {
			if(i.getDocumentType() == recordType)
				arr[x] = i;
			x++;
		}
		return arr;
	}

	public Record[] getRecordsByAuthor(String author) {
		Record[] arr = new Record[records.size()];
		int x = 0;
		for(Record i : records) {
			if(i.getAuthorFirstName() == author || i.getAuthorLastName() == author)
				arr[x] = i;
			x++;
		}
		return arr;
	}

	public Record[] getRecordsByDate(Date date) {
		Record[] arr = new Record[records.size()];
		int x = 0;
		for(Record i : records) {
			if(i.getDate() == date)
				arr[x] = i;
			x++;
		}
		return arr;
	}

	public Record[] getRecordsByCategory(String category) {
		Record[] arr = new Record[records.size()];
		int x = 0;
		for(Record i : records) {
			if(i.getCategory() == category)
				arr[x] = i;
			x++;
		}
		return arr;
	}

	public Record[] getRecordsBySummary(String summary) {
		Record[] arr = new Record[records.size()];
		int x = 0;
		for(Record i : records) {
			if(i.getSummary() == summary)
				arr[x] = i;
			x++;
		}
		return arr;
	}

}
