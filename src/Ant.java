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
	
	/* (non-Javadoc)
	 * @see Organism#makeChild(int, int)
	 * This method makes a new Ant.
	 */
	@Override
	public void makeChild(int childX, int childY) 
	{
		Organism ant = new Ant (world, childX, childY);
		world.setAt(childX, childY, ant);
		ant.x = childX;
		ant.y = childY;
	}
}

