package edu.umgc.smart.view;

import java.util.logging.Logger;

import edu.umgc.smart.model.Record;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class ModifyRecordCardPanel extends RecordCardPanel {
    private String name = "Modify Record";
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    ModifyRecordCardPanel(CardView cardView, Record currentRecord) {
        super(cardView, currentRecord);
        enableReturnButton(true);
        setSaveButton(
                new JButton("Save Changes"),
                e -> saveModifications(cardView, currentRecord));
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
                    "Reference Number is not valid",
                    "Invalid Record",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
