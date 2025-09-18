import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class GA_Simulation {

  private int numIndividuals;
  private int numWinners;
  private int numRounds;
  private int c_0;
  private int c_max;
  private float m;
  private int numLetters;
  ArrayList<Individual> curr_population;

  // Use the instructions to identify the class variables, constructors, and methods you need
  public static Random rng;

  public GA_Simulation(int numIndividuals, int numWinners, int numRounds, int c_0, int c_max, float m, int numLetters){
    this.numIndividuals = numIndividuals;
    this.numWinners = numWinners;
    this.numRounds = numRounds;
    this.c_0 = c_0;
    this.c_max = c_max;
    this.m = m;
    this.numLetters = numLetters;
  }

  /*
   * To do this, you should write a method called `init` that initializes a population of the desired size, calling the `Individual` setup constructor ***n*** times to create a population of ***n*** Individuals.
Remember to pass `rng` to the constructor.
This will be your founding generation of creatures.
   */

   public void init(int numIndividuals, Random rng, int c_0, int numLetters){

    this.curr_population = new ArrayList<Individual>(numIndividuals);

    for(int i = 0; i < numIndividuals; i++){
      this.curr_population.add(new Individual(c_0, numLetters, rng));
    }

   }



  /** Provided method that prints out summary statistics for a given
   * generation, based on the information provided
   * @param roundNumber: Which round of evolution are we on, from 0 to n
   * @param bestFitness: Fitness of top-ranked (most fit) individual
   * @param kthFitness: Fitness of kth-ranked individual
   * @param leastFitness: Fitness of lowest-ranked (least fit) individual
   * @param best: Individual with highest fitness
   * @return: Nothing, prints statistics to standard out
   */
  private void printGenInfo(int roundNumber, int bestFitness, int kthFitness, int leastFitness, Individual best) {
    System.out.println("Round " + roundNumber + ":");
    System.out.println("Best fitness: " + bestFitness);
    // System.out.println("k-th (" + k + ") fitness: " + kthFitness);
    System.out.println("k-th fitness: " + kthFitness);
    System.out.println("Least fit: " + leastFitness);
    System.out.println("Best chromosome: " + best);
    System.out.println(); // blank line to match the example format
  }

  /** Provided method that sorts population by fitness score, best first
   * @param pop: ArrayList of Individuals in the current generation
   * @return: Nothing. ArrayList is sorted in place
   */
  public void rankPopulation(ArrayList<Individual> pop) {
    // sort population by fitness
    Comparator<Individual> ranker = new Comparator<>() {
      // this order will sort higher scores at the front
      public int compare(Individual c1, Individual c2) {
        return (int)Math.signum(c2.getFitness()-c1.getFitness());
      }
    };
    pop.sort(ranker);
  }

  /**
   * Method to evolve the current population in the genetic simulation
   * @param rng the Random object used to randomize the genes
   */
  public void evolve(Random rng){
    // sort out the winners (assumes that rankPopulation has already been run)

    ArrayList<Individual> winners = new ArrayList<Individual>(this.numWinners);
    for(int i = 0; i < this.numWinners; i++){
      winners.add(this.curr_population.get(i));
    }

    this.curr_population = new ArrayList<Individual>(this.numIndividuals); // clear out old population

    // randomly select 2 individuals numIndividuals times to make the next population
    for(int i = 0; i < this.numIndividuals; i++){
      Individual parent1 = winners.get(rng.nextInt(this.numWinners));
      Individual parent2 = winners.get(rng.nextInt(this.numWinners));
      this.curr_population.add(new Individual(parent1, parent2, this.c_max, this.m, this.numLetters, rng));

    }
  }

  /**
   * method to run the genetic simulation and output the results
   */
  public void run(){
    this.init(this.numIndividuals, rng, this.c_0, this.numLetters); // initialize the population
    this.rankPopulation(this.curr_population); // rank the population
    this.printGenInfo(1, this.curr_population.get(0).getFitness(), this.curr_population.get(numWinners-1).getFitness(), 
    this.curr_population.get(this.curr_population.size()-1).getFitness(), this.curr_population.get(0));

    for (int i = 2; i <= this.numRounds; i++){
      this.evolve(rng); // evolve the current population
      this.rankPopulation(this.curr_population); // rank the population
      this.printGenInfo(i, this.curr_population.get(0).getFitness(), this.curr_population.get(numWinners-1).getFitness(), 
      this.curr_population.get(this.curr_population.size()-1).getFitness(), this.curr_population.get(0));

    }

  }

  public static void main(String[] args) {
    // This first block of code establishes a random seed, which will make
    // it easier to test your code. The output should remain consistent if the
    // seed is the same. To run with a specific seed, you can run from the
    // command line like:
    //                    java GA_Simulation 24601

    long seed = System.currentTimeMillis(); // default
    if (args.length > 0) {
      try {
        seed = Long.parseLong(args[0]);
      } catch (NumberFormatException e) {
        System.err.println("Seed wasn't passed so using current time.");
      }
    }
    rng = new Random(seed);
    
    // Write your main below:
    GA_Simulation test = new GA_Simulation(10, 3, 10, 8, 20, 0.01f, 5);
    test.run();

  }

}
