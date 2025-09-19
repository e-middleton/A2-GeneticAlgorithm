# A2 Genetic Algorithms

Your readme should include the following information. Each student needs to submit all of this information themselves, even when pair programming. 

Group Members: NA

Any peers and/or TAs/Tutors you collaborated with: NA

Would you like to give "kudos" to anyone who was particularly supportive or helpful?

Cite any references used: Only class notes and javadoc

If you used AI, please describe how you used it and what the experience taught you: na

## Questions

Please briefly describe what you observed about the "winners" produced by your genetic algorithm. Did changing the parameter values have any effect on what you observed?

The winners had much longer chromosomes than the others, and they were usually two or three letters repeating like DADADADAD or ABCABCABCBA. It
remained relatively few letters, even when the number of possible gene states was increased to about 20, the winners had 3-4 letters in
their chromosomes.
If the number of rounds increased, the number of different genes in the chromosome decreased to 2.
The winners also usually had odd numbers of chromosomes. If I set the c_max to 20, the winners had 19 genes. This didn't change
if I tried to force a genetic bottleneck (numWinners=1), the winner still had a long, odd numbered chromosome.

## Reflection

Please provide a reflection on your experience with this assignment-- what was interesting? what was hard? what do you feel like you learned?

It was neat, it felt really satisfying to be able to quickly write up a little game/simulation and have a pretty good idea of what's going on in 
the memory while it runs.
I'm not sure if anything was particularly difficult in this assignment (though maybe that means I wildly misunderstood something),
but I did have to go back and review Random objects in java.
I feel like I have more practice now with ArrayLists which is nice.
I also started using the command line to run/test java code this assignment, which was new for me.