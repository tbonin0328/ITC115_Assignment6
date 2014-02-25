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
		move(x,y);
		//list(x,y);
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
}
