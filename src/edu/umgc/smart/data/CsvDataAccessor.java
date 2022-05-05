package edu.umgc.smart.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import edu.umgc.smart.model.Record;
import edu.umgc.smart.model.RecordType;

/**
 * A flat-file-based DataAccessor.
 *
 * This class loads a CSV file of records into a HashMap, if the file exists.
 * While the application is running, changes made to the data are saved to file,
 * such as add, update, and delete operations.  This ensures that all changes
 * are persistent.
 */
public class CsvDataAccessor implements DataAccessor {
	private static final long serialVersionUID = 1564806799199445630L;

  private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private static final String RECORDS_CSV = "Records.csv";

	private final Map<String, Record> records = new HashMap<>();

	public CsvDataAccessor() {
		loadFile();
	}

	private void loadFile() {
		try (BufferedReader br = new BufferedReader(new FileReader(RECORDS_CSV))) {
			parseRecordsFromFile(br);
		}
		catch (FileNotFoundException e) {
			LOGGER.warning("No Records.csv file currently exists. Proceeding without loading data.");
		}
		catch (IOException e) {
			LOGGER.warning("Failed to read CSV file, Records.csv. Proceeding without loading data.");
		}
	}

	private void parseRecordsFromFile(BufferedReader br) throws IOException {
		String line;
		while ((line = br.readLine()) != null) {
			addRecordToMap(line);
		}
	}

	private void addRecordToMap(String line) {
		if (!line.equals(Record.getHeaders())) {
			String[] recordData = line.split(",");
			records.put(recordData[0], createRecord(recordData));
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
		return records.get(referenceNumber);
	}

	@Override
	public Record[] getAll() {
		return records.values().toArray(new Record[0]);
	}

	@Override
	public void add(Record r) {
		records.putIfAbsent(r.getReferenceNumber(), r);
		save();
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
		for (Record i : records.values()) {
			fWriter.append("\n");
			fWriter.append(String.join(",", i.toString()));
		}
		fWriter.flush();
	}

	@Override
	public void update(String referenceNumber, Record r) {
		records.replace(referenceNumber, r);
		save();
	}

	@Override
	public void delete(Record r) {
		records.remove(r.getReferenceNumber());
		save();
	}

	@Override
	public Record[] getRecordsByMainSearch(String searchTerm) {
		List<Record> arr = new ArrayList<>();
		for (Record i : records.values()) {
			if (i.toString().contains(searchTerm))
				arr.add(i);
		}
		return arr.toArray(new Record[0]);
	}

	@Override
	public Record[] getRecordsByReferenceNum(String referenceNumber) {
		List<Record> arr = new ArrayList<>();
		for (Record i : records.values()) {
			if (i.getReferenceNumber().contains(referenceNumber)) {
				arr.add(i);
			}
		}
		return arr.toArray(new Record[0]);
	}

	@Override
	public Record[] getRecordsByTitle(String title) {
		List<Record> arr = new ArrayList<>();
		for (Record i : records.values()) {
			if (i.getTitle().contains(title)) {
				arr.add(i);
			}
		}
		return arr.toArray(new Record[0]);
	}

	@Override
	public Record[] getRecordsByRecordType(RecordType recordType) {
		List<Record> arr = new ArrayList<>();
		for (Record i : records.values()) {
			if (i.getDocumentType().equals(recordType)) {
				arr.add(i);
			}
		}
		return arr.toArray(new Record[0]);
	}

	@Override
	public Record[] getRecordsByAuthorFirstName(String author) {
		List<Record> arr = new ArrayList<>();
		for (Record i : records.values()) {
			if (i.getAuthorFirstName().contains(author)) {
				arr.add(i);
			}
		}
		return arr.toArray(new Record[0]);
	}

	@Override
	public Record[] getRecordsByAuthorLastName(String author) {
		List<Record> arr = new ArrayList<>();
		for (Record i : records.values()) {
			if (i.getAuthorLastName().contains(author)) {
				arr.add(i);
			}
		}
		return arr.toArray(new Record[0]);
	}

	@Override
	public Record[] getRecordsByDate(String date) {
		List<Record> arr = new ArrayList<>();
		for (Record i : records.values()) {
			if (i.getDate().contains(date)) {
				arr.add(i);
			}
		}
		return arr.toArray(new Record[0]);
	}

	@Override
	public Record[] getRecordsByCategory(String category) {
		List<Record> arr = new ArrayList<>();
		for (Record i : records.values()) {
			if (i.getCategory().contains(category)) {
				arr.add(i);
			}
		}
		return arr.toArray(new Record[0]);
	}

	@Override
	public Record[] getRecordsBySummary(String summary) {
		List<Record> arr = new ArrayList<>();
		for (Record i : records.values()) {
			if (i.getSummary().contains(summary)) {
				arr.add(i);
			}
		}
		return arr.toArray(new Record[0]);
	}

}
