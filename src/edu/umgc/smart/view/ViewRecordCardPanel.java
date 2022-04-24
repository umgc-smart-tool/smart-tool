package edu.umgc.smart.view;

import java.util.logging.Logger;

public class ViewRecordCardPanel extends RecordCardPanel {

  private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
  private String name = "View Record";

  public ViewRecordCardPanel(CardView cardView) {
    super(cardView);
    setSaveButtonAction(e -> LOGGER.info("Save - View"));
    disableAllFields();
  }

  @Override
  public String getName() {
    return this.name;
  }

}
