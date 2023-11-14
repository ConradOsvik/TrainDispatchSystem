package edu.ntnu.stud.models;

import org.junit.jupiter.api.BeforeEach;

public class TestTrainRegister {
  TrainRegister trainRegister;

  @BeforeEach
  public void setup(){
    trainRegister = new TrainRegister();
    trainRegister.addTrain(new TrainDeparture("12:00", "F1", 100, "Oslo", 1, "00:10"));
  }
}
