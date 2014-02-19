import com.sun.tools.javac.code.Attribute.Array;

public abstract class Organism
{
	protected World world;
	protected int x;
	protected int y;
	protected boolean simulated;
	
	public Organism(World world, int x, int y)
	{
		this.world = world;
		this.x = x;
		this.y = y;
		simulated = true;
	}
	
	//returns the string representation of the organism
	public abstract String toString();
	
	public void simulate()
	{
		//don't simulate twice in a round
		if(simulated) return;
		simulated = true;
		
		//now move, breed, ....
		
		if(getType() == "ant" && 
				detectAdjacentSpace() == true && 
				getTimestep() % 3 != 0) 
				moveIt();
		
		if(getType() == "ant" && 
				detectAdjacentSpace() == true && 
				getTimestep() % 3 == 0) 
				breedIt();
		
		if(getType() == "doodlebug" && 
				detectAdjacentSpace() == true && 
				adjacentOrgType() != "ant" && 
				getTimestep() % 8 != 0 &&
				getAntsEaten() > 0)
				moveIt();	

		if(getType() == "doodlebug" && 
				getTimestep() % 8 != 0 &&
				getTimestep() % 3 == 0 &&
				getAntsEaten() == 0)
				starveIt();	
		
		if(getType() == "doodlebug" && 
				detectAdjacentSpace() == true && 
				adjacentOrgType() == "ant" && 
				getTimestep() % 8 != 0 &&
				getTimestep() % 3 != 0)
				eatIt();	
		
		if(getType() == "doodlebug" && 
				detectAdjacentSpace() == true && 
				adjacentOrgType() == "ant" && 
				getTimestep() % 8 != 0 &&
				getTimestep() % 3 != 0)
				breedIt();	
	}

	//indicate that the organism hasn't simulated this round
	public void resetSimulation()
	{
		simulated = false;
	}
	
	public abstract boolean detectAdjacentSpace();
	
	public abstract String adjacentOrgType();
	
	public abstract String getType();
	
	public abstract int getTimestep();
	
	public abstract void moveIt();
	
	public abstract void breedIt();
	
	public abstract void eatIt();
	
	public abstract int getAntsEaten();
	
	public abstract void starveIt();

}
