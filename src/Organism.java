import acm.util.RandomGenerator;

/**
 * @author thomasbonin
 * ITC 115, Assignment 6, Bug Simulation
 * Submitted 2/28/14
 */
public abstract class Organism
{
	protected World world;
	protected int x;
	protected int y;
	protected int breedIncrement = 3;
	protected int breedCounter = 0;
	protected int timeAlive = 0;
	protected int newX;
	protected int newY;
	protected boolean simulated;
	
	protected static final int LEFT = 0;
	protected static final int RIGHT = 1;
	protected static final int ABOVE = 2;
	protected static final int BELOW = 3;
	
	RandomGenerator rgen = RandomGenerator.getInstance();
	
	/**
	 * @param world: brings in the world object
	 * @param x: the x coordinate (from the row/column structure) of the organism
	 * @param y: the y coordinate (from the row/column structure) of the organism
	 * This is the constructor for the Organism.
	 */
	public Organism(World world, int x, int y)
	{
		this.world = world;
		this.x = x;
		this.y = y;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * This method returns the string representation of the organism and 
	 * can be modified at the subclass level.
	 */
	public abstract String toString();
	
	/**
	 * This method simulates the bug. If it's time to breed, the organism breeds and the
	 * breeding counter is reset. If not time to breed, the organism moves.
	 * Either way, the time alive counter is increased. 
	 */
	public void simulate()
	{
		//don't simulate twice in a round
		if(simulated) return;
		simulated = true;
		
		if (breedCounter == breedIncrement)
			{
				breed();
				breedCounter = 0;
			}
		move();
		breedCounter++;
		timeAlive++;
	}
	
	/**
	 * 	This method moves the organism by: 
	 * 1) verifying that the row/col does not contain null; 
	 * 2) using a random number generator to get a random direction between 0 and 3; 
	 * 3) recording the x and y value of the location as newX and newY;
	 * 4) putting the random direction into a switch statement that increases
	 * the newX or newY, depending on the number;
	 * 5) using an if/else statement to check if the new point is in the grid
	 * and is null, and, if it is,
	 * 6) setting the organism at the new x,y, and
	 * 7) setting the current x,y position to null
	 */
	public void move()
	{	
		if (world.getAt(this.x, this.y) != null)
			{
			
			int dir = rgen.nextInt(0,3);
			newX = x; 
			newY = y;
	        
			switch(dir)
	        {
	        case LEFT: newX--;
	        break;
	        case RIGHT: newX++;
	        break;
	        case ABOVE: newY--;
	        break;
	        case BELOW: newY++;
	        break;
	        }
			
		    if(checkSpace(newX, newY))
		    	{
		    	world.setAt(newX, newY, this);
		    	world.setAt(this.x, this.y, null);
		    	this.x = newX;
		    	this.y = newY;
		    	}
			}
	}
	
	/**
	 * This method indicates that the organism hasn't simulated this round.
	 */
	public void resetSimulation()
	{
		simulated = false;
	}
	
	/**
	 * @param x: grid x coordinate of where the new child will be placed.
	 * @param y: grid y coordinate of where the new child will be placed.
	 * This method is the abstract core method for the makeChild methods in 
	 * the subclasses.
	 */
	public abstract void makeChild(int x, int y);

	/**
	 * @param x: the x coordinate of the space being checked.
	 * @param y: the y coordinate of the space being checked.
	 * @return: returns true if the space is not null and is in the grid.
	 * This method checks to see whether a space can be moved into or not.
	 */
	public boolean checkSpace (int x, int y)
	{
		if (world.getAt(x, y) != null) return false;
		if (!world.pointInGrid(x, y)) return false;
		return true;
	}
	
	/**
	 * This method uses an if/else statement to check each space around the organism
	 * in question. If the space is free, then the organism places a new organism there
	 * and exits.
	 */
	public void breed()
	{
		if (checkSpace(x+1, y))
		{
			makeChild(x+1, y); 
		}
		else if (checkSpace(x-1, y))
		{
			makeChild(x-1,y);
		}
		else if (checkSpace(x, y+1))
		{
			makeChild(x, y+1);
		}
		else if (checkSpace(x, y-1))
		{
			makeChild(x, y-1);
		}
	}
}


