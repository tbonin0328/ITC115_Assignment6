/**
 * @author thomasbonin
 * ITC 115, Assignment 6, Bug Simulation
 * Submitted 2/28/14
 */
public class DoodleBug extends Organism
{	
	private int timeSinceEat = 0;
	
	/**
	 * @param world: brings in the world object
	 * @param x: the x coordinate (from the row/column structure) of the organism
	 * @param y: the y coordinate (from the row/column structure) of the organism
	 * This is the constructor for the DoodleBug.
	 * This constructor sets the breedIncrement for the DoodleBug to 8.
	 */
	public DoodleBug(World world, int x, int y)
	{
		super(world, x, y);
		breedIncrement = 8;
	}
	
	/* (non-Javadoc)
	 * @see Organism#toString()
	 * This method returns the string of the organism defined by this class (DoodleBug)
	 */
	public String toString()
	{
		return "doodlebug";
	}
	
	/* (non-Javadoc)
	 * @see Organism#simulate()
	 * This method varies the simulate method in the Organism class by first checking to 
	 * see if it's time for the DoodleBug to starve or eat and then proceeding to either 
	 * move and breed the bug (if not time to starve or eat) or going ahead and running the
	 * eat and starve methods.
	 */
	public void simulate()
	{
		if(simulated) return;
		simulated = true;
		
		if (breedCounter == breedIncrement)
		{
			breed();
			breedCounter = 0;
		}
		if (timeSinceEat != 3 && !eat())
		{
			move();
			breedCounter++;
		}
		eat();
		starve();
	}
	
	/**
	 * This method checks to see if timeSinceEat is 3; if it is,
	 * it sets the DoodleBug Object to 0.
	 */
	private void starve() 
	{
		if (timeSinceEat == 3)
		{
			world.setAt(x, y, null);
		}
	}

	/**
	 * @return: returns true if there's an ant to be replaced; return false if not.
	 * This method uses an if/else statement to check each adjacent space to see 
	 * if there's an ant there. If there is an ant, the replace ant method runs.
	 */
	public boolean eat()
	{
		if (checkAnt(x+1,y))
		{
			replaceAnt(x+1,y);
			return true;
		}
		else if (checkAnt(x-1,y))
		{
			replaceAnt(x-1, y);
			return true;
		}
		else if (checkAnt(x, y+1))
		{
			replaceAnt(x, y+1);
			return true;
		}
		else if (checkAnt(x, y-1))
		{
			replaceAnt(x, y-1);
			return true;
		}
		else
		{
			timeSinceEat++;
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see Organism#makeChild(int, int)
	 * This method makes a new DoodleBug.
	 */
	@Override
	public void makeChild(int childX, int childY) 
	{
		Organism bug = new DoodleBug(world, childX, childY);
		world.setAt(childX, childY, bug);
		bug.x = childX;
		bug.y = childY;
	}
	
	/**
	 * @param x: x coordinate of the space being checked for an ant
	 * @param y: y coordinate of the space being checked for an ant
	 * @return: returns true if there's an ant in the space; returns false if not.
	 * This method checks a particular space to see it contains an ant.
	 */
	public boolean checkAnt(int x, int y)
	{
		Organism org = world.getAt(x, y);
		if (world.pointInGrid(x, y) && org != null && org.toString() == "ant") return true;
		return false;
	}

	/**
	 * @param antX: the x coordinate of the ant to be replaced
	 * @param antY: the y coordinate of the ant to be replaced
	 * This method 1) takes in the above coordinates; 2) sets the space object to null;
	 * 3) places the DoodleBug in that space; 4) sets the space where the DoodleBug is 
	 * locate to null; 5) sets the x and y coordinates for the DoodleBug to the coordinates
	 * of the now deceased ant; and 6) resets TimeSinceEat to zero
	 */
	public void replaceAnt(int antX, int antY)
	{
		world.setAt(antX, antY, null);
		world.setAt(antX, antY, this);
		world.setAt(x, y, null);
		this.x = antX;
		this.y = antY;
		timeSinceEat = 0;
	}

}
