# ⏱ Berkeley Algorithm Implementation in Java  

This repository contains an implementation of the **Berkeley Algorithm** using **Java**. The Berkeley Algorithm is a well-known clock synchronization algorithm used in distributed systems to synchronize clocks of multiple devices or nodes in a network.  

## 📖 Project Overview  

The goal of this project is to demonstrate how the **Berkeley Algorithm** works by simulating a master node that collects time data from client nodes, calculates an average clock time, and sends corrections to each client to synchronize them.  

This project was developed as part of my studies and practical exploration of distributed systems and clock synchronization techniques.  

---

## ⚙️ Features:  
- Simulation of a master node and multiple client nodes.  
- Each client sends its current time to the master.  
- The master calculates the average clock time, excluding outliers if necessary.  
- The master sends offset corrections back to each client.  
- Console output to show clock values before and after synchronization.  

---

## 🛠️ Technologies:  
- **Java 11+**  
- **Socket programming (TCP)** — For communication between master and client nodes.  
- **Threads** — To simulate parallel clients and concurrent interactions.  
- **SimpleDateFormat & Date APIs** — For date/time handling.  

---
