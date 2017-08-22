# Pre-work - *Daily Task Scheduler*

Daily Task Scheduler is an android app that allows building a todo list and basic todo items management functionality including adding new items, editing and deleting an existing item.

Submitted by: Brian Yavuz Sagir

Time spent: 5-7 hours spent in total

## User Stories

The following required functionality is completed:

* [ ] User can successfully add and remove items from the task list
* [ ] User can tap a task item in the list and bring up an edit screen for the task item and then have any changes to the text reflected in the task list.
* [ ] User can persist todo items and retrieve them properly on app restart

The following **optional** features are implemented:

* [ ] Persist the todo items into SQLite instead of a text file
* [ ] Improve style of the todo items in the list using a custom adapter
* [ ] Add support for completion due times for todo items
* [ ] Add support for selecting the priority of each todo item (and display in listview item)
* [ ] Tweak the style improving the UI / UX, play with colors, images or backgrounds

The following additional features are implemented:

* [ ] Adding icons that shows the status of task on the task screen

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='http://imgur.com/cH7azcZ' title='Daily Task Scheduler'/>

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Project Analysis

As part of your pre-work submission, please reflect on the app and answer the following questions below:

Question 1: "What are your reactions to the Android app development platform so far? Compare and contrast Android's approach
to layouts and user interfaces in past platforms you've used."

Answer: I have been working on Android for 2+ years. I used Android Studio (Android SDK) to build native Android App.
Android application user interfaces are defined using layouts. There are a number of different types of layout types that can
be used to organize controls on a screen, or portion of a screen. Layouts can be defined using XML resources,
or programmatically at run-time in Java. Android's now more focusing on uI/UIX by establishing material design concept.
I have used AbsoluteLayout which is less flexible than other types of layouts because
absolute positioning is not useful in world of various screen resolutions and aspect ratios.

Question 2: "Take a moment to reflect on the `ArrayAdapter` used in your pre-work. How would you describe an adapter in this context and what is its function in Android?
Why do you think the adapter is important?
Explain the purpose of the `convertView` in the `getView` method of the `ArrayAdapter`."

Answer: Well adapters in this context (generally in Android) are basically a bridge between the UI components and the data source that
fill data into the UI Component. Adapter is an interface whose implementations provide data and control the display of that data.  
I think the adapter (also adapter design in Software Engineering) is important that helps control data efficiently.

The purpose of convertView in the getView method of the ArrayAdapter explains here (https://www.edureka.co/blog/what-are-adapters-in-android/)
perfectly and I copied below.

Adapters call the getView() method which returns a view for each item within the adapter view. 
The layout format and the corresponding data for an item within the adapter view is set in the getView() method. 
Now, it will be a performance nightmare if getView() returns a new View every time it is called. Creating a new view is very expensive 
in Android as you will need to loop through the view hierarchy (using the find ViewbyID () method) and
then inflate the view to finally display it on the screen.It also puts a lot of pressure on the garbage collector.
That is because when the user is scrolling through the list, if a new view is created; the old view (since it is not recycled)
is not referenced and becomes a candidate to be picked up by the garbage collector. 
So what Android does is that it recycles the views and reuses the view that goes out of focus.

## Notes

Describe any challenges encountered while building the app.

## License

    Copyright [yyyy] [Brian Yavuz Sagir]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
