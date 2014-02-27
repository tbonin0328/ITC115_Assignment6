import acm.util.RandomGenerator;

public abstract class Organism
{
	protected World world;
	protected int x;
	protected int y;
	protected int breedIncrement = 0;
	protected int timeAlive;
	protected int newX;
	protected int newY;
	protected boolean simulated;
	protected Object[] buglist;
	protected Object[] pointlist;
	
	protected static final int LEFT = 0;
	protected static final int RIGHT = 1;
	protected static final int ABOVE = 2;
	protected static final int BELOW = 3;
	
	protected int[][] bugsNear = new int[][]{{x-1,y},{x+1,y},{x,y-1},{x,y+1}};
	
	
	RandomGenerator rgen = RandomGenerator.getInstance();
	
	public Organism(World world, int x, int y)
	{
		this.world = world;
		this.x = x;
		this.y = y;
	}
	
	//returns the string representation of the organism
	public abstract String toString();
	
	//simulate the bug, move the bug, increase the timeAlive incrementer, increase the breed incrementer
	public void simulate()
	{
		//don't simulate twice in a round
		if(simulated) return;
		simulated = true;
		
		move();
		breed();
		timeAlive++;
		list(x,y);
	}

	//move the organism by: 
	//1) verifying that the row/col does not contain null; 
	//2) using a random number generator to get a random direction between 1 and 4; 
	//3) recording the x and y value of the location as newX and newY;
	//4) putting the random direction into a switch statement that increases
	// the newX or newY, depending on the number;
	//5) using an if/else statement to check if the new point is in the grid
	//and is null, and, if it is,
	//6) setting the organism at the new x,y, and
	//7) setting the current x,y position to null
	//8) if the if statement is false, using else to keep the organism where it 
	//is [WILL THIS CAUSE ANOTHER BUG NOT TO GO THERE?]
	
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
			
		    if(world.pointInGrid(newX, newY) && world.getAt(newX, newY) == null)
		    	{
		    	world.setAt(newX, newY, this);   
		    	world.setAt(this.x, this.y, null); 
		    	this.x = newX;
		    	this.y = newY;
		    	}
			}
	}
	
	//get a list of various outputs on the console so I know if things are working or not
	public void list(int a, int b)
	{  
        System.out.println("Place: " + Integer.toString(a) + ", " + Integer.toString(b) +
        		"; Org: " + world.getAt(this.x, this.y) + 
        		"; PIG?:" + world.pointInGrid(a,b) +  
        		"; newX: " + newX +
        		"; newY: " + newY +
         		"; time alive: " + this.timeAlive +
        		"; breedtime: " + breedIncrement +
        		//"; notEat: " + this.eat() +
        		"; adjPoints: " + listString(checkAdjacentPoints(this.x, this.y)) +
        		"; adjBugs: " + listString(getAdjacentBugs(this.x, this.y))
        		//"; getAnt?: " + (getAnt(getAdjacentBugs(this.x, this.y)))
        		);
	}
	
	//1) get the breed modulus for the organism in question
	//2) if organism timestep % getModulus equals 0, 
	//create an additional organism of same type in 
	//empty space that is free.
	
	//indicate that the organism hasn't simulated this round
	public void resetSimulation()
	{
		simulated = false;
	}
	
	public abstract Organism makeChild();

	
	//if the space in question is occupied (not null), return false.
	//if the space in question is not on the grid, return false.
	//otherwise, return true and proceed
	public boolean checkSpace (int x, int y)
	{
		if (world.getAt(x, y) != null) return false;
		if (world.pointInGrid(x, y) != true) return false;
		return true;
	}
	
	public void breed()
	{
		if (!breedTime()) return;
		
		if (checkSpace(this.x+1, this.y))
		{
			world.setAt(this.x+1, this.y, this);
		}
		else if (checkSpace(this.x-1, this.y))
		{
			world.setAt(this.x-1, this.y, this);
		}
		else if (checkSpace(this.x, this.y+1))
		{
			world.setAt(this.x, this.y+1, this);
		}
		else if (checkSpace(this.x, this.y-1))
		{
			world.setAt(this.x, this.y-1, this);
		}
	}
	
	public boolean breedTime()
	{
		return timeAlive % breedIncrement == 0;
	}

	public Object[] checkAdjacentPoints(int x, int y)
	{
		Object[] pointlist = new Object[4];
			pointlist[0] = world.pointInGrid(x-1, y);
			pointlist[1] = world.pointInGrid(x+1, y);
			pointlist[2] = world.pointInGrid(x, y+1);
			pointlist[3] = world.pointInGrid(x, y-1);
			return pointlist;
	}
	
	public Object[] getAdjacentBugs(int x, int y)
	{
		buglist = new Object[4];
			buglist[0] = world.getAt(x-1, y);
			buglist[1] = world.getAt(x+1, y);
			buglist[2] = world.getAt(x, y+1);
			buglist[3] = world.getAt(x, y-1);
		return buglist;
	}

	public String listString (Object[][] items)
	{
		String listofwhatever = "";
		
		for (int i = 0; i < items.length; i++)
		{
			listofwhatever += items[i][i] + ", ";
		}
		return listofwhatever;
	}
	
	public String listString (Object[] items)
	{
		String listofwhatever = "";
		
		for (int i = 0; i < items.length; i++)
		{
			listofwhatever += items[i] + ", ";
		}
		return listofwhatever;
	}
	
	
	public Object get(Object[] items, int index)
	{
		//if they try to access an index beyond where data is
		return items[index];
	}
	
	//if it's an ant, get timestep % 3
	//if it's a doodlebug, get timestep % 8

	public Organism makeChild(int childX, int childY) {
		// TODO Auto-generated method stub
		return null;
	}
}


