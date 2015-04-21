# GreenHub - Smart gardener
## Introduction

The system consists of an Android application in the smartphone of the user where he/she can manage the plants that are in the garden. Then, some tasks are created, according to the parameters collected by some sensors connected to a microcontroller in the garden, in order to guide the user throughout the process of sowing, watering and harvesting plants. In order to know what plant to plant the user will be able to search information about different plants.

The microcontroller that we chose is an Arduino UNO with different sensors for measuring temperature, humidity and lightness. Data gathered in the garden is stored in the cloud and, then, retrieved by the phone to analyse it. Moreover, we created a plant database in the cloud so that the phone memory is not full of plants, so we synchronize the device with the cloud every time the user launches the app or when an action is performed on the plants.

GreenHub consists of three parts:

* **Smartphone application**: we developed an Android application which acts as the main part of the project. In the application the user can manage his/her plants and keep up with the tasks.
* **Meteorological station**: the user must install a microcontroller and three sensors in order to gather data about the ambient conditions. The user will not interact with this device.
* **External server**: as mentioned before, we developed an external server to store all the plants. We synchronize the device with the cloud every time the user launches the app or when an action is performed on the plants. In this way, the same user can access his/her account on different devices. Moreover, we also store in this server the ambient conditions measured by the microcontroller.

## System description

GreenHub is a gardening assistant for amateur gardeners who want to engage sowing, flourishing and harvesting their own plants.

In our case, we decided to use the Android framework because Android development was one the courses we were attending. Besides, Android has a bigger share of the smartphone market so our app could be more used. On the other hand, we chose Arduino UNO board because it has a similar microcontroller to the one we are learning in the corresponding course and because it allowed us to use a WiFi connection. In addition, Arduino has a very big community and lot of libraries to support our project.

## Hardware and software requirements

### Arduino UNO board

The Arduino UNO board is a microcontroller board based on the ATmega328. We decided to use this board because it provided us with the necessary features for the system. It allows us to connect the sensors so that we collect the data from the ambient, as well as using a WiFi shield in order to send that data to an external server and then, retrieve it with the smartphone.

### Sensors

GreenHub is composed by three sensors which measure the ambient conditions in real time. We use one sensor alone for measuring lightness (TEMT6000) and a combined one for measuring humidity and temperature (DHT11). The latter one already had an Arduino library available which we implemented in our code. The lightness sensor, however, we have had to connect it as an analog input, so that we read the lightness parameter and convert it to digital with the ADC integrated in the Arduino board.

### External database

We decided Microsoft Azure for the external database where we store the list of plants, the input from the sensors and the weather conditions for each plant. Microsoft Azure is a cloud computing platform and infrastructure, created by Microsoft, for building, deploying and managing applications and services through a global network of datacenters.

We decided to use this external server in the cloud in order to allow the user synchronize his/her garden across different devices. Hence, the database must have two different plant tables: one with all the plants available for the user to add to his/her garden and another one with the added plants. On the other hand, the application must download the ambient conditions every hour to check if the user should perform a task in order to keep his/her plants.

Moreover, we decided to store the user credentials in a separate MySQL database. This database is saved in an external PHP server in one of our laptops. We chose to use this approach apart from Azure so that we also worked with a home-made server.
