public class Ant extends Organism
{
	
	public Ant(World world, int x, int y)
	{
		super(world, x, y);
		
		int antSteps;
		int direction;
		int[] spacesNear;
		
		
	}
	
	//returns a string representation of the ant
	public String toString()
	{
		return "ant";
	}

	@Override
	public boolean detectAdjacentSpace() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String adjacentOrgType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTimestep() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void moveIt() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void breedIt() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eatIt() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getAntsEaten() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void starveIt() {
		// TODO Auto-generated method stub
		
	}
}