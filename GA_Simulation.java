import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

/**
 * Class for creating and running a genetic algorithms simulation.
 */
public class GA_Simulation {
  
  private int numIndividuals;
  private int numWinners;
  private int numRounds;
  private int c_0;
  private int c_max;
  private float m;
  private int geneStates;
  public ArrayList<Individual> population;

  // Use the instructions to identify the class variables, constructors, and methods you need
  public static Random rng;

  /**
   * Constructor for the GA_Simulation class objects.
   * @param numIndividuals the number of individuals in the population.
   * @param numWinners the number of winners, or individuals allowed to reproduce, in each generation.
   * @param numRounds the number of rounds for which the simulation is run.
   * @param c_0 the initial chromosome size of the individuals.
   * @param c_max the maximum chromosome size for the individuals.
   * @param m the mutation rate of each gene per round.
   * @param geneStates the number of possible states for a given gene
   */
  public GA_Simulation(int numIndividuals, int numWinners, int numRounds, int c_0, int c_max, float m, int geneStates){
    this.numIndividuals = numIndividuals;
    this.numWinners = numWinners;
    this.numRounds = numRounds;
    this.c_0 = c_0;
    this.c_max = c_max;
    this.m = m;
    this.geneStates = geneStates;
  }


  /**
   * Method for initializing a population of size numIndividuals, and 
   * it calls the constructor for individuals that does NOT use sexual reproduction. 
   * This method gives the starting population for the GA_Simulation.
   * Method was made public because of the autograder.
   */
  public void init(){
    this.population = new ArrayList<Individual>(this.numIndividuals);
    
    for(int i = 0; i < numIndividuals; i++){
      this.population.add(new Individual(this.c_0, this.geneStates, rng));
    }
  }


  /** Provided method that prints out summary statistics for a given
   * generation, based on the information provided
   * @param roundNumber Which round of evolution are we on, from 0 to n
   * @param bestFitness Fitness of top-ranked (most fit) individual
   * @param kthFitness Fitness of kth-ranked individual
   * @param leastFitness Fitness of lowest-ranked (least fit) individual
   * @param best Individual with highest fitness
   */
  public void printGenInfo(int roundNumber, int bestFitness, int kthFitness, int leastFitness, Individual best) {
    System.out.println("Round " + roundNumber + ":");
    System.out.println("Best fitness: " + bestFitness);
    // System.out.println("k-th (" + k + ") fitness: " + kthFitness);
    System.out.println("k-th fitness: " + kthFitness);
    System.out.println("Least fit: " + leastFitness);
    System.out.println("Best chromosome: " + best);
    System.out.println(); // blank line to match the example format
  }

  /** Provided method that sorts population by fitness score, best first
   * @param pop ArrayList of Individuals in the current generation
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
   * and mutation rates of genes.
   */
  public void evolve(){
    // sort out the winners (assumes that rankPopulation has already been run)
    ArrayList<Individual> winners = new ArrayList<Individual>(this.numWinners);
    for(int i = 0; i < this.numWinners; i++){
      winners.add(this.population.get(i));
    }

    this.population = new ArrayList<Individual>(this.numIndividuals); // clear out old population

    // randomly select 2 individuals numIndividuals times to make the next population
    for(int i = 0; i < this.numIndividuals; i++){
      Individual parent1 = winners.get(rng.nextInt(this.numWinners));
      Individual parent2 = winners.get(rng.nextInt(this.numWinners));
      this.population.add(new Individual(parent1, parent2, this.c_max, this.m, this.geneStates, rng));
    }
  }

  /**
   * Method to output information about a given generation,
   * including the fitness of the fittest individual, the 'k'th individual,
   * and the least fit, as well as the "winning" chromosome.
   * @param roundNumber the current generation number
   */
  public void describeGeneration(int roundNumber){
    int fittestScore = this.population.get(0).getFitness(); // called after sorting
    int lastWinnerScore = this.population.get(numWinners-1).getFitness();
    int worstScore = this.population.get(this.population.size() - 1).getFitness();
    Individual fittestIndividual = this.population.get(0);

    printGenInfo(roundNumber, fittestScore, lastWinnerScore, worstScore, fittestIndividual);
  }

  /**
   * method to run the genetic simulation and output the results
   */
  public void run(){
    init(); // initialize the population
    rankPopulation(this.population); // rank the population
    printGenInfo(1, this.population.get(0).getFitness(), this.population.get(numWinners-1).getFitness(), 
    this.population.get(this.population.size()-1).getFitness(), this.population.get(0));

    for (int i = 2; i <= this.numRounds; i++){
      evolve(); // evolve the current population
      rankPopulation(this.population); // rank the population
      describeGeneration(i);
    }
  }

  /**
   * Main method, used for creating, running, and debugging the GA_Simulation.
   * @param args command line arguments.
   */
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
    GA_Simulation test = new GA_Simulation(100, 10, 20, 5, 20, 0.03f, 5);
    test.run();
  }
}
