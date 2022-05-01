package edu.umgc.smart.view;

import javax.swing.JButton;

import edu.umgc.smart.model.Record;

public class ViewRecordCardPanel extends RecordCardPanel {

  public ViewRecordCardPanel(CardView cardView, Record currentRecord) {
    super(cardView, currentRecord);
    enableReturnButton(false);
    setSaveButton(new JButton("Modify"), e -> cardView.setPanel(new ModifyRecordCardPanel(cardView, currentRecord)));
    disableAllFields();
  }

  @Override
  public String getName() {
    return "View Record";
  }

}
