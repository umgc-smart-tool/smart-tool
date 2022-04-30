package edu.umgc.smart.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
	Random randNum = new Random(); // Random number generator for more diverse test Record set

	private List<Record> records = new ArrayList<>();

	public CsvDataAccessor() {
		int numberOfRecords = 10;
		for (int i = 0; i < numberOfRecords; i++) {
			records.add(new Record.Builder("R-22-" + i)
					.title("Title " + randNum.nextInt(20))
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
		for (Record r : records) {
			if (r.getReferenceNumber().equals(referenceNumber)) {
				return r;
			}
		}
		return null;
	}

	@Override
	public Record[] getAll() {
		return records.toArray(new Record[0]);
	}

	@Override
	public void add(Record r) {
		if (null == get(r.getReferenceNumber())) {
			records.add(r);
		}
	}

	@Override
	public void save() {
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
		Record target = get(referenceNumber);
		if (null != target) {
			delete(target);
		}
		add(r);
	}

	@Override
	public void delete(Record r) {
		Record[] recordsCopy = records.toArray(new Record[0]);
		for (int i = 0; i < recordsCopy.length; i++)
			if (recordsCopy[i] == r)
				records.remove(r);
	}

	public Record[] getRecordsByMainSearch(String searchTerm) {
		List<Record> arr = new ArrayList<>();
		for (Record i : records) {
			if (i.getAuthorFirstName().contains(searchTerm) ||
					i.getAuthorLastName().contains(searchTerm) ||
					i.getCategory().contains(searchTerm) ||
					i.getDate().contains(searchTerm) ||
					i.getDocumentType().toString().contains(searchTerm) ||
					i.getLocation().contains(searchTerm) ||
					i.getReferenceNumber().contains(searchTerm) ||
					i.getSummary().contains(searchTerm) ||
					i.getTitle().contains(searchTerm))
				arr.add(i);
		}
		return arr.toArray(new Record[0]);
	}

	public Record[] getRecordsByReferenceNum(String referenceNumber) {
		List<Record> arr = new ArrayList<>();
		for (Record i : records) {
			if (i.getReferenceNumber().contains(referenceNumber)) {
				arr.add(i);
			}
		}
		return arr.toArray(new Record[0]);
	}

	public Record[] getRecordsByTitle(String title) {
		List<Record> arr = new ArrayList<>();
		for (Record i : records) {
			if (i.getTitle().contains(title)) {
				arr.add(i);
			}
		}
		return arr.toArray(new Record[0]);
	}

	public Record[] getRecordsByRecordType(RecordType recordType) {
		List<Record> arr = new ArrayList<>();
		for (Record i : records) {
			if (i.getDocumentType().equals(recordType)) {
				arr.add(i);
			}
		}
		return arr.toArray(new Record[0]);
	}

	public Record[] getRecordsByAuthorFirstName(String author) {
		List<Record> arr = new ArrayList<>();
		for (Record i : records) {
			if (i.getAuthorFirstName().contains(author)) {
				arr.add(i);
			}
		}
		return arr.toArray(new Record[0]);
	}

	public Record[] getRecordsByAuthorLastName(String author) {
		List<Record> arr = new ArrayList<>();
		for (Record i : records) {
			if (i.getAuthorLastName().contains(author)) {
				arr.add(i);
			}
		}
		return arr.toArray(new Record[0]);
	}

	public Record[] getRecordsByDate(String date) {
		List<Record> arr = new ArrayList<>();
		for (Record i : records) {
			if (i.getDate().contains(date)) {
				arr.add(i);
			}
		}
		return arr.toArray(new Record[0]);
	}

	public Record[] getRecordsByCategory(String category) {
		List<Record> arr = new ArrayList<>();
		for (Record i : records) {
			if (i.getCategory().contains(category)) {
				arr.add(i);
			}
		}
		return arr.toArray(new Record[0]);
	}

	public Record[] getRecordsBySummary(String summary) {
		List<Record> arr = new ArrayList<>();
		for (Record i : records) {
			if (i.getSummary().contains(summary)) {
				arr.add(i);
			}
		}
		return arr.toArray(new Record[0]);
	}

}
