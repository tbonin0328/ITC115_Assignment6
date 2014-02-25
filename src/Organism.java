import acm.util.RandomGenerator;


public abstract class Organism
{
	protected World world;
	protected int x;
	protected int y;
	protected int breedIncrement;
	protected int timeAlive;
	protected int newX;
	protected int newY;
	protected boolean simulated;
	
	protected static final int LEFT = 0;
	protected static final int RIGHT = 1;
	protected static final int ABOVE = 2;
	protected static final int BELOW = 3;
	
	
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
		
		move();
	}

	protected abstract void move();
	
	public void move(int x, int y)
	{
		if (world.getAt(x,y) == null) return;
		
		if (world.getAt(x, y) != null)
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
			
		    if(world.pointInGrid(newX, newY) && world.getAt(newX, newY) == null)
		    	{
		    	   world.setAt(this.x, this.y, null);
		    	   this.x = x;
		    	   this.y = y;
			       world.setAt(newX, newY, this);
			       this.timeAlive++;
		    	}

    	//now move, breed, ....
		    this.timeAlive++;
		    breedIncrement++;
		    //list(x, y);
			}
	}
	
	public void list(int a, int b)
	{  
        System.out.println("Place: " + Integer.toString(a) + ", " + Integer.toString(b) +
        		"; Org: " + world.getAt(this.x, this.y) + 
        		"; PIG?:" + world.pointInGrid(a,b) +  
        		//"; above: " + world.getAt(x-1, y) + 
        		//"; below: " + world.getAt(x+1, y) +
        		//"; right: " + world.getAt(x, y+1) + 
        		//"; left: " + world.getAt(x, y-1) +
        		"; newX: " + newX +
        		"; newY: " + newY +
        		//"; thisX: " + this.x +
        		//"; thisY: " + this.y +
         		"; time alive: " + this.timeAlive +
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

