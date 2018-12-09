package model;

import model.contracts.ReceiptFormatter;

public class ReceiptFactory {
  public ReceiptFormatter createReceipt(String type) {
    return (type.equals("simple")) ? new SimpleReceiptFormatter() : new ComprehensiveReceiptFormatter();
  }
}
