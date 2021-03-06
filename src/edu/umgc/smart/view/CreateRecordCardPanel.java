package edu.umgc.smart.view;

import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import edu.umgc.smart.model.Record;

/**
 * Create Record Card Panel
 *
 * This class is used for creating a brand new record, which must have a unique
 * reference number in order to be created. Any other fields will be set to
 * default values if not valid.  To ensure the user is aware of the data stored
 * in the saved record, the View Record Card Panel for the new record is displayed.
 */
class CreateRecordCardPanel extends RecordCardPanel {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    CreateRecordCardPanel(CardView cardView) {
        super(cardView);
        enableReturnButton(false);
        setDateField();
        setSaveButton(
                new JButton("Save"),
                e -> saveRecord(cardView));
    }

    private void saveRecord(CardView cardView) {
        LOGGER.info("Save button pressed");
        int saveOption = JOptionPane.showConfirmDialog(null,
                "Do you want to save this record?");
        if (saveOption == JOptionPane.YES_OPTION) {
            saveNewRecord(cardView);
        } else {
            LOGGER.info("Save cancelled.");
        }
    }

    private void saveNewRecord(CardView cardView) {
        LOGGER.info("Save - new record");
        Record newRecord = buildRecord();
        if (null != newRecord) {
            saveNewRecordIfHasUniqueReferenceNumber(cardView, newRecord);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Unable to create new Record.",
                    "Invalid Record",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveNewRecordIfHasUniqueReferenceNumber(CardView cardView, Record newRecord) {
        if (null == cardView.dataAccessor.get(newRecord.getReferenceNumber())) {
            cardView.dataAccessor.add(newRecord);
            cardView.setPanel(new ViewRecordCardPanel(cardView, newRecord));
        } else {
            String message = "Could not create record.\nA record with the provided reference number already exists.";
            JOptionPane.showMessageDialog(this,
                    message,
                    "Operation Failed",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    public String getName() {
        return "Create Record";
    }
}
