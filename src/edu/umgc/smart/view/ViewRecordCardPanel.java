package edu.umgc.smart.view;

import java.util.logging.Logger;

import edu.umgc.smart.model.Record;

public class ViewRecordCardPanel extends RecordCardPanel {

  private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
  private String name = "View Record";


  public ViewRecordCardPanel(CardView cardView, Record currentRecord) {
    super(cardView, currentRecord, "view");
    setModifyButtonAction(e -> {
      cardView.setPanel(new ModifyRecordCardPanel(cardView, currentRecord));
    });
    disableAllFields();
  }

  @Override
  public String getName() {
    return this.name;
  }

}
