The-Factory [JAVA]  🏭

A Factory / Production-line Worker & Component Code Test (for Yagro.com). April '19. 



```
--------------------------- Coding Challenge ---------------------------

There is a factory production line around a single a conveyor belt.

Components (of type A and B) come onto the start of the belt at random 
intervals; workers must take one component of each type from the belt 
as they come past, and combine them to make a finished product.

The belt is divided into fixed-size slots; each slot can hold only one 
component or one finished product.  There are a number of worker 
stations on either side of the belt, spaced to match the size of the 
slots on the belt, like this:



       v   v   v   v   v          workers
     ---------------------
  -> | A |   | B | A | P | ->     conveyor belt
     ---------------------
       ^   ^   ^   ^   ^          workers



In each unit of time, the belt moves forwards one position, and there 
is time for a worker on one side of each slot to EITHER take an item 
from the slot or replace an item onto the belt.  The worker opposite 
them can't touch the same belt slot while they do this.
(So you can't have one worker picking something from a slot while 
their counterpart puts something down in the same place).

Once a worker has collected one of both types of component, they can 
begin assembling the finished product.  This takes an amount of time, 
so they will only be ready to place the assembled product back on the 
belt on the fourth subsequent slot.  While they are assembling the 
product, they can't touch the conveyor belt.  Workers can only hold 
two items (component or product) at a time: one in each hand.

Create a simulation of this, with three pairs of workers.  At each 
time interval, the slot at the start of the conveyor belt should have 
an equal (1/3) chance of containing nothing, a component A or a 
component B.

Run the simulation for 100 steps, and compute how many finished 
products come off the production line, and how many components of each 
type go through the production line without being picked up by any 
workers.

A few pointers:
 - You should expect to spend no more than two or three hours on this
   challenge.
 - The code does not have to be 'production quality', but
   we will be looking for evidence that it's written to be
   somewhat flexible, and that a third party would be able to
   read and maintain it. 
 - Be sure to state (or comment) your assumptions.
 - During the interview, we may ask about the effect of changing 
certain
   aspects of the simulation. (E.g. the length of the conveyor belt.)

 - Flexibility in the solution is preferred, but we are also looking 
   for a sensible decision on where this adds too much complexity. 
   (Where would it be better to rewrite the code for a different 
   scenario, rather than spending much more than the allotted time 
   creating an overly complicated, but very flexible simulation engine?)
-----------------------------------------------------------------------------
```
