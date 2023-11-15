package edu.ntnu.stud.models;

import org.junit.jupiter.api.BeforeEach;

/**
 * Test class for the TrainRegister entity class. The class methods follows the
 * methodName_stateUnderTest_expectedBehavior naming convention.
 */
public class TestTrainRegister {

  TrainRegister trainRegister;

  @BeforeEach
  public void setup() {
    trainRegister = new TrainRegister();
    trainRegister.addTrain(
        new TrainDeparture(
            "12:00",
            "F1",
            100,
            "Oslo",
            1,
            "00:10"
        )
    );
  }
}
