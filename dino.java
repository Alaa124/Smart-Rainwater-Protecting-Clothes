#define RAIN_SENSOR A0
#define DHTPIN 2
#define DHTTYPE DHT11
#define MOTOR_IN1 5
#define MOTOR_IN2 6

#include "DHT.h"
DHT dht(DHTPIN, DHTTYPE);

void setup() {
  pinMode(RAIN_SENSOR, INPUT);
  pinMode(MOTOR_IN1, OUTPUT);
  pinMode(MOTOR_IN2, OUTPUT);
  Serial.begin(9600);
  dht.begin();
}

void loop() {
  int rainValue = analogRead(RAIN_SENSOR);
  float temp = dht.readTemperature();

  Serial.print("Rain Value: "); Serial.println(rainValue);
  Serial.print("Temp: "); Serial.println(temp);

  if (rainValue < 400) { // Rain detected
    digitalWrite(MOTOR_IN1, HIGH);
    digitalWrite(MOTOR_IN2, LOW);
    Serial.println("Rain detected — Cover Activated");
  } else {
    digitalWrite(MOTOR_IN1, LOW);
    digitalWrite(MOTOR_IN2, HIGH);
    Serial.println("No Rain — Cover Retracted");
  }
  delay(2000);
}