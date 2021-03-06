package com.codebattles.models;

public class InputOutput {
  private String inputValue;

  private String outputValue;
  
  public InputOutput() {
    
  }
  
  public InputOutput(String input, String output) {
    this.inputValue = input;
    this.outputValue = output;
  }

  public String getInputValue() {
    return inputValue;
  }

  public void setInputValue(String inputValue) {
    this.inputValue = inputValue;
  }

  public String getOutputValue() {
    return outputValue;
  }

  public void setOutputValue(String outputValue) {
    this.outputValue = outputValue;
  }
}
