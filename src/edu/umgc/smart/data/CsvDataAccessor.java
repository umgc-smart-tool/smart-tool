package edu.umgc.smart.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.umgc.smart.model.Record;
import edu.umgc.smart.model.RecordType;

public class CsvDataAccessor implements DataAccessor {
	private static final long serialVersionUID = 1564806799199445630L;

	private static final String RECORDS_CSV = "Records.csv";

	private List<Record> records = new ArrayList<>();

	public CsvDataAccessor() {
		loadFile();
	}

	private void loadFile() {
		try (BufferedReader br = new BufferedReader(new FileReader(RECORDS_CSV))) {
			parseRecordsFromFile(br);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void parseRecordsFromFile(BufferedReader br) throws IOException {
		String line;
		String delim = ",";
		while ((line = br.readLine()) != null) {
			if (line.equals(Record.getHeaders())) {
				continue;
			}
			String[] recordData = line.split(delim);
			records.add(createRecord(recordData));
		}
	}

	private Record createRecord(String[] params) {
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
	}

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
		File f = new File(RECORDS_CSV);
		try (FileWriter fWriter = new FileWriter(f)) {
			writeRecordsToCsv(fWriter);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeRecordsToCsv(FileWriter fWriter) throws IOException {
		fWriter.write(Record.getHeaders());
		for (Record i : records) {
			fWriter.append("\n");
			fWriter.append(String.join(",", i.toString()));
		}
		fWriter.flush();
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

	@Override
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

	@Override
	public Record[] getRecordsByReferenceNum(String referenceNumber) {
		List<Record> arr = new ArrayList<>();
		for (Record i : records) {
			if (i.getReferenceNumber().contains(referenceNumber)) {
				arr.add(i);
			}
		}
		return arr.toArray(new Record[0]);
	}

	@Override
	public Record[] getRecordsByTitle(String title) {
		List<Record> arr = new ArrayList<>();
		for (Record i : records) {
			if (i.getTitle().contains(title)) {
				arr.add(i);
			}
		}
		return arr.toArray(new Record[0]);
	}

	@Override
	public Record[] getRecordsByRecordType(RecordType recordType) {
		List<Record> arr = new ArrayList<>();
		for (Record i : records) {
			if (i.getDocumentType().equals(recordType)) {
				arr.add(i);
			}
		}
		return arr.toArray(new Record[0]);
	}

	@Override
	public Record[] getRecordsByAuthorFirstName(String author) {
		List<Record> arr = new ArrayList<>();
		for (Record i : records) {
			if (i.getAuthorFirstName().contains(author)) {
				arr.add(i);
			}
		}
		return arr.toArray(new Record[0]);
	}

	@Override
	public Record[] getRecordsByAuthorLastName(String author) {
		List<Record> arr = new ArrayList<>();
		for (Record i : records) {
			if (i.getAuthorLastName().contains(author)) {
				arr.add(i);
			}
		}
		return arr.toArray(new Record[0]);
	}

	@Override
	public Record[] getRecordsByDate(String date) {
		List<Record> arr = new ArrayList<>();
		for (Record i : records) {
			if (i.getDate().contains(date)) {
				arr.add(i);
			}
		}
		return arr.toArray(new Record[0]);
	}

	@Override
	public Record[] getRecordsByCategory(String category) {
		List<Record> arr = new ArrayList<>();
		for (Record i : records) {
			if (i.getCategory().contains(category)) {
				arr.add(i);
			}
		}
		return arr.toArray(new Record[0]);
	}

	@Override
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
