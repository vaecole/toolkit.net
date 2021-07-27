# qima-interview

## Description
QIMA Interview skeleton for backend developpers

### Getting Started

This a SpringBoot project configured one with the full implementation and the tests
the others only with the tests with JUnit and assertJ

## Dependencies provided with the project:

* SpringBoot
* JUnit 5
* AssertJ
* MapStruct
* Lombock
* Mockito
* ObjectMapper

## Model

### Workflow:

```java
class Workflow {
  String name;
  List<WorkflowStep> steps;
}
```

### Workflow Step:

```java
class WorkflowStep {
  List<WorkflowAction> actions;
}
```

### Workflow Action:

```java
class WorkflowAction {
  String type;
}
```

### Workflow Checklist Action

```java
class WorkflowChecklistAction {
  TestChecklist checklist;
}
```

### Workflow Measurement Action

```java
class WorkflowChecklistAction {
  MeasureChecklist measureChecklist;
}
```

### Measure Checklist

```java
class MeasureChecklist {

  String name;
  MeasurementTolerance tolerance;
  List<Measure> measureList;
}
```

### Measure

```java
class Measure {

  double result;
  double tolerance;
}
```

### Checklist item

```java
class ChecklistItem {
  String type;
  boolean isCheckPoint;
}
```
### Number item

```java
class NumberItem {
  double result;
  double expected;
  NumberComparator numberComparator;
}
```
### Text item

```java
class TextItem {

  String text;
}
```

### Yes/No item

```java
class YesNoItem {

  boolean response;
  boolean expected;
}
```

# Interview test

The goal of the interviewer is to validate and compute the result of a workflow:
* for the Yes/No the result should be the same as the expected value
* for the text it should validate the regex rule (or do it by hand) "^[a-zA-Z ]*$"
* for the number should be smaller, equal or greater than an expected value

For each of those previous points the test failed only if it's a check point

For the measures the candidate should either compute a percentage or add to compute the tolerance
and see if the measure is out of tolerance

