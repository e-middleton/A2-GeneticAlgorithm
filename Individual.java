import java.util.ArrayList;
import java.util.Random;

public class Individual {

    /**
     * Chromosome stores the individual's genetic data as an ArrayList of characters
     * Each character represents a gene
     */
    ArrayList<Character> chromosome;
    public int size;
    public int fitness;

    /**
     * Inital constructor to generate initial population members
     * @param c_0 The initial chromosome size
     * @param num_letters The number of letters available to choose from
     */
    public Individual(int c_0, int num_letters, Random rng) {
        this.chromosome = new ArrayList<Character>(c_0); // allocate memory with initial size
        this.size = c_0;

        for (int i = 0; i < c_0; i++){
            this.chromosome.add(this.randomLetter(num_letters, rng)); // fill in chromosome with random letters
        }

    }

    /**
     * Second constructor to create parents and offspring chromosomes
     * @param parent1 The first parent chromosome
     * @param parent2 The second parent chromosome
     * @param c_max The maximum chromosome size
     * @param m The chances per round of mutation in each gene
     * @param num_letters the number of letters available to choose from
     * @param rng the random number generator being used for the current run
     */
    public Individual(Individual parent1, Individual parent2, int c_max, float m, int num_letters, Random rng) {
        int prefix = rng.nextInt(parent1.size) + 1; // will be minimum of one, and up to the size of the parent 
        int suffix = rng.nextInt(parent2.size) + 1;

        int prefix_ind = prefix - 1; // change num char to the index
        int suffix_ind = parent2.size - suffix; // counting from the end of parent2's chromosome

        this.chromosome = new ArrayList<Character>(prefix+suffix);
        this.size = prefix+suffix;

        //TODO are there built in methods for this?

        for (int i = 0; i <= prefix_ind; i ++){ 
            this.chromosome.add(parent1.chromosome.get(i));
        }

        for (int i = suffix_ind; i < parent2.size; i++) {
            this.chromosome.add(parent2.chromosome.get(i));
        }

        
       while(this.size > c_max){
        this.chromosome.remove(this.size-1);
        this.size -= 1;
       }

       // mutations //

       for (int i = 0; i < this.size; i++) {
        Boolean mutation = this.doesMutate(m, rng);

        if(mutation){
            this.chromosome.remove(i);
            this.chromosome.add(i, this.randomLetter(num_letters, rng));
        }
       }

    }

    /**
     * Method to calculate the fitness score of an individual based upon the genes in their chromosome.
     * @return the fitness score of an individual.
     */
    public int getFitness(){
        this.fitness = 0;

        // part 1: palindromic //
        for(int i = 0; i < this.size/2; i++) {
            if(this.chromosome.get(i).equals(this.chromosome.get(this.size-i-1))){
                this.fitness += 1;
            } else {
                this.fitness -= 1;
            }
        }
        if(this.size/2 != this.size/2.f) { // if there are an odd number of chromosomes, increase by one for middle
            this.fitness += 1;
        }

        // part 2: side by side //
        for(int i = 0; i < this.size-1; i++){
            if(this.chromosome.get(i).equals(this.chromosome.get(i+1))){
                this.fitness -= 1;
            }
        }
        return this.fitness;
    }

    /**
     * Provided method to choose a letter at random, in the range from A to the number of states indicated
     * @param num_letters The number of letters available to choose from (number of possible states)
     * @param rng The random number generator being used for the current run
     * @return The letter as a Character
     */
    private Character randomLetter(int num_letters, Random rng) {
        return Character.valueOf((char)(65 + rng.nextInt(num_letters)));
    }

    /**
     * Provided method to determine whether a given gene will mutate based on the parameter ***m***
     * @param m the mutation rate
     * @param rng The random number generator being used for the current run
     * @return true if a number randomly chosen between 0 and 1 is less than ***m***, else false
     */
    private Boolean doesMutate(float m, Random rng) {
        float randomNum = rng.nextInt(100) / 100f;
        return randomNum < m;
    }

    /**
     * Expresses the individual's chromosome as a String, for display purposes
     * @return The chromosome as a String
     */
    public String toString() {
        StringBuilder builder = new StringBuilder(chromosome.size());
        for(Character ch: chromosome) {
            builder.append(ch);
        }
        return builder.toString();
    }



    public static void main(String[] args) {
        // This code will set a random seed when you're testing Individual (i.e., running it without GA_Simulation)
        Random rng = new Random(System.currentTimeMillis());

        // You can pass rng, as defined above, to your constructors.
        Individual a = new Individual(8, 5, rng);
        Individual b = new Individual(8, 5, rng);

        Individual ab = new Individual(a, b, 10, 0.21f, 8, rng);
        System.out.println(a);
        System.out.println(b);
        System.out.println(ab);

        System.out.println(a.getFitness());
        System.out.println(b.getFitness());
        System.out.println(ab.getFitness());
    }

}
