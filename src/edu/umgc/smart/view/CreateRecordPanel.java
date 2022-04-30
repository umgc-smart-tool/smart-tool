package edu.umgc.smart.view;

import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import edu.umgc.smart.model.Record;

public class CreateRecordPanel extends RecordCardPanel {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    CreateRecordPanel(CardView cardView) {
        super(cardView);
        enableReturnButton(false);
        setSaveButton(
                new JButton("Save Button Pressed"),
                e -> saveRecord(cardView));
    }

    private void saveRecord(CardView cardView) {
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
            cardView.dataAccessor.add(newRecord);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Reference Number is not valid",
                    "Invalid Record",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public String getName() {
        return "Create Record";
    }
}
