package edu.umgc.smart.view;

import javax.swing.JButton;

import edu.umgc.smart.model.Record;

/**
 * View Record Card Panel
 *
 * Provides the user with a detailed un-editable view of a single record.
 * Buttons are provided for enabling modification or deletion of the record.
 */
class ViewRecordCardPanel extends RecordCardPanel {

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
