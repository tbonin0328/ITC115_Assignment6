/**
 * @author thomasbonin
 * ITC 115, Assignment 6, Bug Simulation
 * Submitted 2/28/14
 */
public class Ant extends Organism
{
	
	/**
	 * @param world: brings in the world object
	 * @param x: the x coordinate (from the row/column structure) of the organism
	 * @param y: the y coordinate (from the row/column structure) of the organism
	 * This is the constructor for the Ant.
	 * This constructor sets the breedIncrement for the Ant to 8.
	 */
	public Ant(World world, int x, int y)
	{
		super(world, x, y);
		
	}
	
	/* (non-Javadoc)
	 * @see Organism#toString()
	 * This method returns a string representation of the ant
	 */
	public String toString()
	{
		return "ant";
	}
	
	public void list(int a, int b)
	{  
        System.out.println("Place: " + Integer.toString(a) + ", " + Integer.toString(b) +
        		"; Org: " + world.getAt(this.x, this.y) + 
        		"; PIG?:" + world.pointInGrid(a,b) +  
        		"; newX: " + newX +
        		"; newY: " + newY +
         		"; time alive: " + this.timeAlive +
         		"; breedCounter: " + breedCounter +
        		"; breedtime: " + breedIncrement
        		//"; notEat: " + this.eat() +
        		//"; adjPoints: " + listString(checkAdjacentPoints(this.x, this.y)) +
        		//"; adjBugs: " + listString(getAdjacentBugs(this.x, this.y))
        		//"; getAnt?: " + (getAnt(getAdjacentBugs(this.x, this.y)))
        		);
	}

	/* (non-Javadoc)
	 * @see Organism#makeChild(int, int)
	 * This method makes a new Ant.
	 */
	@Override
	public void makeChild(int childX, int childY) 
	{
		System.out.println("new " + world.getAt(this.x, this.y) + " at " + Integer.toString(x+1) + ", " + Integer.toString(y));
		Organism ant = new Ant (world, childX, childY);
		world.setAt(childX, childY, ant);
		ant.x = childX;
		ant.y = childY;
	}
}

