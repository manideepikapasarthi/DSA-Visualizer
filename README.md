
# **📊 DSA Visualizer – Arrays, Stack, Queue, Circular Queue & Sorting**

*A Java Swing–based Data Structures Learning Tool*

---

## *Overview*

The DSA Visualizer is a Java desktop application that visually demonstrates the internal working of fundamental Data Structures and Sorting Algorithms.

This project helps students understand how structures like Array, Stack, Queue, and Circular Queue operate by showing each step visually using labeled boxes and smooth animations. Sorting algorithms are displayed step-by-step, making abstract concepts easy to grasp.

The application is menu-driven, interactive, and designed specifically for academic learning and conceptual clarity.

---

## *Core Features*

### **1.Data Structures Visualized**

* Array – user-defined size and elements
* Stack – Push & Pop operations with Top pointer
* Queue – Enqueue & Dequeue with Front & Rear pointers
* Circular Queue – array-based implementation with wrap-around logic

---

### **2.Sorting Algorithms**

* Bubble Sort – stepwise comparison and swapping
* Selection Sort – minimum element selection and swapping

---

### **3.Visualization Highlights**

* Elements displayed as labeled rectangular boxes
* Horizontal layout for arrays
* Vertical layout for stack, queue, and circular queue
* Dynamic pointer labels (Top, Front, Rear)
* Real-time status messages describing current actions
* Adjustable animation speed with stop control

---

## **Technical Stack & Concepts Used**

|Category                   |	Details                                              |
|-----------------------    |------------------------------------------------------|
|**Language**               |Java (JDK 11+)                                        |
|**GUI**	                  |Swing & AWT                                           |
|**OOP Concepts**	          |Abstraction, Encapsulation, Inheritance, Polymorphism |
|**Design**	                |Interfaces, ADTs, Modular Packages                    |
|**Multithreading**	        |Background animation threads                          |   
|**Algorithms**	            |Bubble Sort, Selection Sort                           | 
|**Data Structures**	      |Array, Stack, Queue, Circular Queue                   |

---

## **5.Project Structure**

```
src/
  com/dsa/adts/
    ArrayADT.java
    StackADT.java
    QueueADT.java
    CircularQueueADT.java
    *.Impl.java

  com/dsa/algorithms/
    Algorithm.java
    BubbleSortAlgorithm.java
    SelectionSortAlgorithm.java

  com/dsa/core/
    Visualizer.java
    ArrayVisualizer.java

  com/dsa/ui/
    ControlPanel.java
    AnimationPanel.java
    MainFrame.java

```

The project follows a clean separation of concerns, making it easy to extend with new data structures or algorithms.

---

## **Design & OOP Highlights**

* ADT-based design ensures abstraction and loose coupling
* Polymorphism used for algorithm execution via a common Algorithm interface
* Single Visualizer Component renders all structures using a mode-based approach
* Threaded animations keep the UI responsive during sorting

---

## **Learning Outcomes**

This project demonstrates proficiency in:

* Core Data Structures
* Sorting Algorithms
* Java Swing GUI programming
* Object-Oriented Design principles
* Algorithm visualization and animation logic
* Ideal for academic submission, DSA courses, viva discussions, and GitHub portfolio display.

---

## **Future Enhancements**

* Additional sorting algorithms (Insertion, Merge, Quick Sort)
* More data structures (Linked List, Tree, Heap)
* Step-by-step execution mode
* Pseudocode highlighting alongside visualization

---

## **Credits**

Developed as part of the Object Oriented Programming Curriculum.

---

## **Author**

**Manideepika Pasarthi** - [manideepikapasarthi@gmail.com] 
Github: [https://github.com/manideepikapasarthi]

**Deepika Penta**

--- 
