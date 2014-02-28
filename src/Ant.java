public class Ant extends Organism
{
	
	public Ant(World world, int x, int y)
	{
		super(world, x, y);
		
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
         		"; breedCounter: " + breedCounter +
        		"; breedtime: " + breedIncrement
        		//"; notEat: " + this.eat() +
        		//"; adjPoints: " + listString(checkAdjacentPoints(this.x, this.y)) +
        		//"; adjBugs: " + listString(getAdjacentBugs(this.x, this.y))
        		//"; getAnt?: " + (getAnt(getAdjacentBugs(this.x, this.y)))
        		);
	}

	@Override
	public void makeChild(int childX, int childY) 
	{
		System.out.println("new " + world.getAt(this.x, this.y) + " at " + Integer.toString(x+1) + ", " + Integer.toString(y));
		Organism ant = new Ant (world, childX, childY);
		world.setAt(childX, childY, ant);
		ant.x = childX;
		ant.y = childY;
	}
}

