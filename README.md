# A2 Genetic Algorithms

Your readme should include the following information. Each student needs to submit all of this information themselves, even when pair programming. 

Group Members: NA

Any peers and/or TAs/Tutors you collaborated with: I didn't collaborate with anybody for code development, but I talked to Tanya and Jade about why my code might have been failing the autograder for describeGeneration and run but we couldn't figure out what the problem was.

Would you like to give "kudos" to anyone who was particularly supportive or helpful?

Cite any references used: Only class notes and javadoc

If you used AI, please describe how you used it and what the experience taught you: na

## Questions

Please briefly describe what you observed about the "winners" produced by your genetic algorithm. Did changing the parameter values have any effect on what you observed?

The winners had much longer chromosomes than the others, and they were usually two or three letters repeating like DADADADAD or ABCABCABCBA. It remained relatively few letters, even when the number of possible gene states was increased to about 20, the winners had 3-4 letters in their chromosomes.
If the number of rounds increased, the number of different genes in the chromosome decreased to 2.
The winners also usually had odd numbers of chromosomes. If I set the c_max to 20, the winners had 19 genes. This didn't change
if I tried to force a genetic bottleneck (numWinners=1), the winner still had a long, odd numbered chromosome.

## Reflection

Please provide a reflection on your experience with this assignment-- what was interesting? what was hard? what do you feel like you learned?

It was neat, it felt really satisfying to be able to write up a little game/simulation and have a good idea of what's going on in the memory while it runs.
This assignment was helpful because I realized that I was passing around instance variables as parameters when I really didn't have 
to be doing that and I was making it more convoluted than I needed. It was a good reminder to take a moment and think about what information the instance methods have access to and what info they actually needed passed into them.
I also had to go back and review Random objects in java in the javadoc.
I feel like I have more practice now with ArrayLists which is nice, because a lot of the programming I do is in python.
I also started using the command line to run/test java code this assignment, which was new for me.
As a side note: I had a lot of my methods in GA_Simulation written as private, and I had to change them to public for the autograder.
I also ended up having a lot of problems with the autograder, and the output it gives is really difficult to debug with.