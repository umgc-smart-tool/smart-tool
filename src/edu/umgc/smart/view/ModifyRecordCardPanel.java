package edu.umgc.smart.view;

import java.util.logging.Logger;

import edu.umgc.smart.model.Record;

import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * Modify Record Card Panel
 *
 * This card panel provides the user with the means of modifying or deleting an
 * existing record. The reference number and date cannot be manually changed.
 * If any of the other fields use invalid data, they will be set to default
 * values.
 */
class ModifyRecordCardPanel extends RecordCardPanel {
    private String name = "Modify Record";
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    ModifyRecordCardPanel(CardView cardView, Record currentRecord) {
        this(cardView, currentRecord, false);
    }

    ModifyRecordCardPanel(CardView cardView, Record currentRecord, boolean isFromResults) {
        super(cardView, currentRecord);
        enableReturnButton(false);
        setDateField();
        setSaveButton(
                new JButton("Save Changes"),
                e -> {
                    saveModifications(cardView, currentRecord);
                    cardView.setPanel(new ViewRecordCardPanel(cardView,
                            cardView.dataAccessor.get(currentRecord.getReferenceNumber()), isFromResults));
                });
        enableModifyFields();
    }

    private void saveModifications(CardView cardView, Record currentRecord) {
        LOGGER.info("Save button pressed");
        int saveOption = JOptionPane.showConfirmDialog(this,
                "Do you want to save these record changes?");
        if (saveOption == JOptionPane.YES_OPTION) {
            attemptToSave(cardView, currentRecord);
        } else {
            LOGGER.info("Save Cancelled");
        }
    }

    private void attemptToSave(CardView cardView, Record currentRecord) {
        LOGGER.info("Save - modified record");
        Record updatedRecord = buildRecord();
        if (null != updatedRecord) {
            cardView.dataAccessor.update(currentRecord.getReferenceNumber(), updatedRecord);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Unable to modify Record.",
                    "Invalid Record",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
