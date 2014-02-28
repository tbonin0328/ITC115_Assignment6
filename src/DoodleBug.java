public class DoodleBug extends Organism
{
	private Object[] buglist;
	
	private int timeSinceEat = 0;
	
	public DoodleBug(World world, int x, int y)
	{
		super(world, x, y);
		breedIncrement = 8;
	}
	
	//string representation of doodlebug
	public String toString()
	{
		return "doodlebug";
	}
	
	public void simulate()
	{
		if (!eat())
		{
			super.simulate();
		}
		eat();
		starve();
	}
	
	private void starve() 
	{
		if (timeSinceEat == 3)
		{
			world.setAt(x, y, null);
			System.out.println("starved " + Integer.toString(x) + ", " + Integer.toString(y));
		}
		else
		{
			timeSinceEat++;
		}
	}

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
			return false;
	}
	
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
        		"; timeSinceEat: " + timeSinceEat
        		//"; adjPoints: " + listString(checkAdjacentPoints(this.x, this.y)) +
        		//"; adjBugs: " + listString(getAdjacentBugs(this.x, this.y))
        		//"; getAnt?: " + (getAnt(getAdjacentBugs(this.x, this.y)))
        		);
	}

	@Override
	public void makeChild(int childX, int childY) 
	{
		System.out.println("new " + world.getAt(this.x, this.y) + " at " + Integer.toString(x+1) + ", " + Integer.toString(y));
		Organism bug = new DoodleBug(world, childX, childY);
		world.setAt(childX, childY, bug);
		bug.x = childX;
		bug.y = childY;
	}
	
	public boolean checkAnt(int x, int y)
	{
		Organism org = world.getAt(x, y);
		if (world.pointInGrid(x, y) && org != null && org.toString() == "ant") return true;
		return false;
	}

	public void replaceAnt(int antX, int antY)
	{
		System.out.println("ant eaten at " + Integer.toString(antX) + ", " + Integer.toString(antY));
		world.setAt(antX, antY, null);
		world.setAt(antX, antY, this);
		world.setAt(x, y, null);
		this.x = antX;
		this.y = antY;
		timeSinceEat = 0;
	}
}
