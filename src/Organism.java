import acm.util.RandomGenerator;


public abstract class Organism
{
	protected World world;
	protected int x;
	protected int y;
	protected int breedIncrement;
	protected int timeAlive;
	protected boolean simulated;
	
	protected static final int LEFT = 0;
	protected static final int RIGHT = 1;
	protected static final int ABOVE = 2;
	protected static final int BELOW = 3;
	
	protected static int timeCounter = 0;
	
	
	RandomGenerator rgen = RandomGenerator.getInstance();
	
	public Organism(World world, int x, int y)
	{
		this.world = world;
		this.x = x;
		this.y = y;
	}
	
	//returns the string representation of the organism
	public abstract String toString();
	
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
		
	    if(world.pointInGrid(newX, newY) && world.getAt(newX, newY) == null)
	    	{
	    		move(newX,newY);
	    	}

    	//now move, breed, ....
        timeCounter++;
        timeAlive++;
        breedIncrement++;
        list(x, y);
	}

	protected abstract void move();
	
	public void move(int x, int y)
	{
		Organism bug = world.getAt(x, y);
		if(bug == null)
			{
				world.setAt(this.x, this.y, null);
				this.x = x;
				this.y = y;
				world.setAt(this.x, this.y, this);
			}
	}
	
	public void list(int a, int b)
	{  
        System.out.println("Place: " + Integer.toString(a) + ", " + Integer.toString(b) +
        		"; Org: " + world.getAt(this.x, this.y) + 
        		"; PIG?:" + world.pointInGrid(a,b) +  
        		"; above: " + world.getAt(x-1, y) + 
        		"; below: " + world.getAt(x+1, y) +
        		"; right: " + world.getAt(x, y+1) + 
        		"; left: " + world.getAt(x, y-1) +
        		"; time alive: " + timeAlive +
        		"; breedtime: " + breedIncrement
        		);
	}
	
	private void breed()
	{
		return;
	}
	
	//indicate that the organism hasn't simulated this round
	public void resetSimulation()
	{
		simulated = false;
	}
	
	protected void makeChild(int x, int y)
	{
		return;
	}
	


}

