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
                e -> {
                    LOGGER.info("Save button pressed");
                    int saveOption = JOptionPane.showConfirmDialog(this,
                            "Do you want to save these record changes?");
                    if (saveOption == JOptionPane.YES_OPTION) {
                        LOGGER.info("Save - modified record");
                        Record updatedRecord = buildRecord();
                        cardView.dataAccessor.update(currentRecord.getReferenceNumber(), updatedRecord);
                    } else {
                        LOGGER.info("Save Cancelled");
                    }
                });
        enableModifyFields();
    }

    @Override
    public String getName() {
        return name;
    }
}
