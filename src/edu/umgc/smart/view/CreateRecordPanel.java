package edu.umgc.smart.view;

import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class CreateRecordPanel extends RecordCardPanel{
    private String name = "Create Record";
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    CreateRecordPanel(CardView cardView, String viewType) {
        super(cardView, viewType);
        // TODO: Implement saving a new record to the data set
        setSaveNewRecordButtonAction(e -> {
            int saveOption = JOptionPane.showConfirmDialog(null,
                    "Do you want to save this record?");
            if (saveOption == 0){ // If yes to saving record
                LOGGER.info("Save - new record");
                JOptionPane.showMessageDialog(null, "Creating new records through the GUI\n" +
                        "is not yet implemented.");
            } else { // Else answer is no to save, or cancelled
                LOGGER.info("Save cancelled.");
            }
        });
    }

    @Override
    public String getName() {
        return null;
    }
}
