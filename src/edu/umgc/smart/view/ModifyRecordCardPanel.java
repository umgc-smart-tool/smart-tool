package edu.umgc.smart.view;

import java.util.logging.Logger;

import edu.umgc.smart.model.Record;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class ModifyRecordCardPanel extends RecordCardPanel{
    private String name = "Modify Record";
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    ModifyRecordCardPanel(CardView cardView, Record currentRecord) {
        super(cardView, currentRecord);
        enableReturnButton(true);

        // TODO: Implement modifying and saving changes to a record. -- cardView.update("RecordNumber", new Record())
        setSaveButton(
            new JButton("Save Changes"),
            e -> {
                LOGGER.info("Save - modified record");

                JOptionPane.showMessageDialog(null, "Saving changes to modified records\n" +
                        "is not yet implemented.");
            }
        );
        enableModifyFields();
    }

    @Override
    public String getName() {
        return name;
    }
}
