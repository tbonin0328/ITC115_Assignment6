public class DoodleBug extends Organism
{
	public DoodleBug(World world, int x, int y)
	{
		super(world, x, y);
		timeAlive = 0;
		breedIncrement = 0;
	}
	
	//string representation of doodlebug
	public String toString()
	{
		return "doodlebug";
	}

	@Override
	public void move() 
	{
		move();
	}
	
	public void simulate()
	{
		//don't simulate twice in a round
		if(simulated) return;
		simulated = true;
		
		int dir = rgen.nextInt(0,3);
		int newX = x, newY = y;
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
		
        if (!eat())
        {
	        if(world.pointInGrid(newX, newY) && world.getAt(newX, newY) == null)
	    	{
	    		move(newX,newY);
	    	}
        }
        else
        {
        	world.setAt(newX, newY, null);
        }

    	//now move, breed, ....
        timeCounter++;
        timeAlive++;
        breedIncrement++;
        list(x, y);
	}
	
	public boolean eat()
	{
		if (world.getAt(x-1, y) == null || world.getAt(x-1, y) == this) return false;
		if (world.getAt(x+1, y) == null || world.getAt(x+1, y) == this) return false; 
		if (world.getAt(x, y-1) == null || world.getAt(x, y-1) == this) return false;
		if (world.getAt(x, y+1) == null || world.getAt(x, y+1) == this) return false;
		return true;
	}

	
}
