package edu.umgc.smart.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
	private static final long serialVersionUID = 1564806799199445630L;

	private List<Record> records = new ArrayList<>();

	public CsvDataAccessor() {
		int numberOfRecords = 10;
		for (int i = 0; i < numberOfRecords; i++) {
			records.add(new Record.Builder("R-22-" + i)
					.title("Title " + i)
					.type("MEMO")
					.lastName("Longo")
					.firstName("Josh")
					.date("2022-04-16")
					.category("Finance")
					.summary("Summary " + i)
					.location("Location")
					.build());
		}
	}

	public void loadFile() {
		String line = "";
		String delim = ",";
		try (BufferedReader br = new BufferedReader(new FileReader("Records.csv"))) {
			while ((line = br.readLine()) != null) {
				String[] employee = line.split(delim);
				// Calls createRecord function and adds the new Record to the records ArrayList
				records.add(createRecord(employee));
			} // end while
		} // end try
		catch (IOException e) {
			e.printStackTrace();
		} // end catch
	} // end loadFile()

	public Record createRecord(String[] params) {
		return new Record.Builder(params[0])
				.title(params[1])
				.type(params[2])
				.lastName(params[3])
				.firstName(params[4])
				.date(params[5])
				.category(params[6])
				.summary(params[7])
				.location(params[8])
				.build();
	} // end createRecord()

	@Override
	public Record get(String referenceNumber) {
		return null;
	}

	@Override
	public Record[] getAll() {
		return records.toArray(new Record[0]);
	}

	@Override
	public void save(Record r) {
		// Upon file close, saves the new Record info into the CSV file (File f)
		File f = new File("Records.csv");
		try (FileWriter fWriter = new FileWriter(f)) {
			for (Record i : records) {
				fWriter.append(String.join(",", i.toString()));
				fWriter.append("\n");
			}
			fWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} // end catch
	}

	@Override
	public void update(String referenceNumber, Record r) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void delete(Record t) {
		for (Record i : records)
			if (i == t)
				records.remove(records.indexOf(t));
	}

	public Record[] getRecordsByMainSearch(String searchTerm) {
		List<Record> arr = new ArrayList<>();
		for (Record i : records) {
			if (i.getAuthorFirstName().equals(searchTerm) ||
					i.getAuthorLastName().equals(searchTerm) ||
					i.getCategory().equals(searchTerm) ||
					i.getDate().toString().equals(searchTerm) ||
					i.getDocumentType().toString().equals(searchTerm) ||
					i.getLocation().equals(searchTerm) ||
					i.getReferenceNumber().equals(searchTerm) ||
					i.getSummary().equals(searchTerm) ||
					i.getTitle().equals(searchTerm))
				arr.add(i);
		}
		return arr.toArray(new Record[0]);
	}

	public Record[] getRecordsByReferenceNum(String referenceNumber) {
		Record[] arr = new Record[records.size()];
		int x = 0;
		for (Record i : records) {
			if (i.getReferenceNumber().equals(referenceNumber)) {
				arr[x] = i;
			}
			x++;
		}
		return arr;
	}

	public Record[] getRecordsByTitle(String title) {
		Record[] arr = new Record[records.size()];
		int x = 0;
		for (Record i : records) {
			if (i.getTitle().equals(title)) {
				arr[x] = i;
			}
			x++;
		}
		return arr;
	}

	public Record[] getRecordsByRecordType(RecordType recordType) {
		Record[] arr = new Record[records.size()];
		int x = 0;
		for (Record i : records) {
			if (i.getDocumentType() == recordType)
				arr[x] = i;
			x++;
		}
		return arr;
	}

	public Record[] getRecordsByAuthor(String author) {
		Record[] arr = new Record[records.size()];
		int x = 0;
		for (Record i : records) {
			if (i.getAuthorFirstName().equals(author) || i.getAuthorLastName().equals(author)) {
				arr[x] = i;
			}
			x++;
		}
		return arr;
	}

	public Record[] getRecordsByDate(Date date) {
		Record[] arr = new Record[records.size()];
		int x = 0;
		for (Record i : records) {
			if (i.getDate() == date)
				arr[x] = i;
			x++;
		}
		return arr;
	}

	public Record[] getRecordsByCategory(String category) {
		Record[] arr = new Record[records.size()];
		int x = 0;
		for (Record i : records) {
			if (i.getCategory().equals(category)) {
				arr[x] = i;
			}
			x++;
		}
		return arr;
	}

	public Record[] getRecordsBySummary(String summary) {
		Record[] arr = new Record[records.size()];
		int x = 0;
		for (Record i : records) {
			if (i.getSummary().equals(summary)) {
				arr[x] = i;
			}
			x++;
		}
		return arr;
	}

}
