public class Ant extends Organism
{
	
	public Ant(World world, int x, int y)
	{
		super(world, x, y);
		breedIncrement = 3;
		
	}
	
	//returns a string representation of the ant
	public String toString()
	{
		return "ant";
	}
	
	public void list(int a, int b)
	{  
        System.out.println("Place: " + Integer.toString(a) + ", " + Integer.toString(b) +
        		"; Org: " + world.getAt(this.x, this.y) + 
        		"; PIG?:" + world.pointInGrid(a,b) +  
        		"; newX: " + newX +
        		"; newY: " + newY +
         		"; time alive: " + this.timeAlive +
        		"; breedtime: " + breedIncrement
        		);
	}

	@Override
	public Organism makeChild(int childX, int childY) 
	{
		return new Ant(world, childY, childY);
	}

	@Override
	public Organism makeChild() {
		// TODO Auto-generated method stub
		return null;
	}
}

