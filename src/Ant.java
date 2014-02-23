public class Ant extends Organism
{
	
	public Ant(World world, int x, int y)
	{
		super(world, x, y);
		timeAlive = 0;
		breedIncrement = 0;
		
	}
	
	//returns a string representation of the ant
	public String toString()
	{
		return "ant";
	}

	protected void move()
	{
		move();
	}
		
}
