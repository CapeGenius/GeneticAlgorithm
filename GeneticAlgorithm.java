/* Rohan Bendapudi - 1/31/2023
Genetic Algorithm to solve the traveling salesman problem
 */

package geneticalgorithm;

import java.util.ArrayList;

/**
 *
 * @author rohan
 */
public class GeneticAlgorithm
{

  public static void main(String[] args)
  {
    // declares a new pool of genes and begins a genetic algorithm to find an optimal solution
    GenePool initialPool = new GenePool();
    ArrayList<GenePool.Gene> genePool = new ArrayList<>();
    
    //generates a pool of genes
    genePool = initialPool.generatePool(initialPool.state);
    
    //initializes population and continues until the convergence limit has been reached
    boolean convergenceReached = false;
    while (convergenceReached == false)
    {
      if (genePool.get(0).distance <= initialPool.convergenceLimit)
      {
        break;
      }
      
      
    }

    
    System.out.println(genePool);
  }

}



class GenePool
{
  
  class Gene // a class for each gene to store the gene itself and its distance
  {
    public Object[] gene = new Object[state.length];
    public int distance;
    
    //constructor to declare a gene
    public Gene(Object[] gene, int distance)
    {
      this.gene = gene;
      this.distance = distance;
    }
  }
  
  //convergence limit for the population
  public int convergenceLimit = 900; 

  // declaring a state to find possible solutions
  public int state[][] =
  {
    {0, 94, 76, 141, 91, 60, 120, 145, 91, 74, 90},
    {94, 0, 156, 231, 64, 93, 108, 68, 37, 150, 130},
    {76, 156, 0, 80, 167, 133, 124, 216, 137, 114, 154},
    {141, 231, 80, 0, 229, 185, 201, 286, 216, 139, 192},
    {91, 64, 167, 229, 0, 49, 163, 65, 96, 114, 76},
    {60, 93, 133, 185, 49, 0, 165, 115, 112, 65, 39},
    {120, 108, 124, 201, 163, 165, 0, 173, 71, 194, 203},
    {145, 68, 216, 286, 65, 115, 173, 0, 103, 179, 139},
    {91, 37, 137, 216, 96, 112, 71, 103, 0, 160, 151},
    {74, 150, 114, 139, 114, 65, 194, 179, 160, 0, 54},
    {90, 130,154, 192, 76, 39, 203, 139, 151, 54, 0}
  };

  //initializes a gene pool
  public GenePool()
  {
    this.state = this.state;
    this.convergenceLimit = 900;
  }
  
  // generate pool of genes
  public ArrayList<Gene> generatePool(int[][] state)
  {
    ArrayList<Gene> initialPool = new ArrayList<>();

    // generates a pool of genes 
    for (int i = 0; i < (state.length / 2); i++)
    {
      //generates local gene to be processed and randomly generated
      Object[] localGene = new Object[this.state.length-1];
      localGene = this.generateGene();
      
      //swaps the chromosomes in the geene
      this.swap(localGene);
      printGene(localGene);
      
      //creates and initializes a new gene 
      Gene objGene = new Gene(localGene, geneWeight(localGene));
      initialPool.add(objGene);
    }
    
    return initialPool;
  }
  
  //distance function to figure out the distance to cross all genes  
  public int geneWeight(Object[] gene)
  {
    int travellingDistance = 0;
    System.out.println(gene.length);
    
    for (int i = 0; i < gene.length-1; i++)
    {
      System.out.println(i);
      if (i == 0) // if this is the first index of the gene --> distance from starting point to that point
      {
        travellingDistance += this.state[i][(Integer) gene[i]];
      }
      else if (i == gene.length-1) // if this is the last index of the gene --> distance from that point to starting point
      {
        travellingDistance += this.state[i][(Integer) gene[0]];
      }
      else //finds the distance between each point
      {
        travellingDistance += this.state[(Integer) gene[i]][(Integer) gene[i-1]];
      }
      
    }
    System.out.println("");
    
    System.out.println("The travelling distance is " + travellingDistance);
    return travellingDistance; 
  }

  //generates the genes 
  public Object[] generateGene()
  {
    Object[] gene = new Object[this.state.length];
    
    //generates a new value for each gene
    for (int i = 0; i < gene.length; i++)
    {
      gene[i] = i;
    }

    return gene;
  }
  
  // swap numbers randomly
  public void swap(Object[] gene)
  {
    
    for (int i = 0; i < gene.length; i++)
    {
      //generates a random number and swaps the index value at that randon number and swaps values
      int randomNumber = randomNumber(state.length);
      int temp = (Integer) gene[i];
      gene[i] = (Integer) gene[randomNumber];
      gene[randomNumber] = temp;
    } 
  }
  // prints the gene 
  public void printGene(Object[] gene)
  {
    System.out.println("The gene is: ");
    
    for (int i = 0; i < gene.length; i++)
    {
      System.out.print((Integer) gene[i] + " ");
    }
    System.out.println("");
  }
  
  //stochastic random sampling to choose random tours
  public ArrayList<Gene> stochasticUniversalSampling(ArrayList<Gene> genePool)
  {
    
    int randomIndex = randomNumber(genePool.size());
    
    Gene firstTour = genePool.get(randomIndex);
    
    
    return genePool;
  }
  
  // generates a random number using Math.random()
  public int randomNumber(int value)
  {
    int randomNumber = (int) (Math.random() * value);
    return randomNumber;
  }

}
