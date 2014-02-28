import acm.util.RandomGenerator;

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
	protected Object[] buglist;
	protected Object[] pointlist;
	
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
	
	//simulate the bug, move the bug, increase the timeAlive incrementer, increase the breed incrementer
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
		else
			{
				move();
				breedCounter++;
			}
		timeAlive++;

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
			
		    if(checkSpace(newX, newY))
		    	{
		    	world.setAt(newX, newY, this);
		    	list(x,y);
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
         		"; breedCounter: " + breedCounter +
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
	
	public abstract void makeChild(int x, int y);

	
	//if the space in question is occupied (not null), return false.
	//if the space in question is not on the grid, return false.
	//otherwise, return true and proceed
	public boolean checkSpace (int x, int y)
	{
		if (world.getAt(x, y) != null) return false;
		if (!world.pointInGrid(x, y)) return false;
		return true;
	}
	
	public void breed()
	{
		//if ants timeAlive is 6 / 3 = 2, remainder == 0 then it will go through this is timeAlive = 3/1 = 3
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
}


