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
		
	}

	//indicate that the organism hasn't simulated this round
	public void resetSimulation()
	{
		simulated = false;
	}
}
