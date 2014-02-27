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
		if(simulated) return;
		simulated = true;
		
		if (!eat())
		{
			move();
			breed();	
			starve();
		}
        
    	timeSinceEat++;
	}
	
	private void starve() 
	{
		if (timeSinceEat <= 3)
		{
			world.setAt(this.x, this.y, null);
		}
	}

	public boolean eat()
	{
		return simulated;
	}
	
	public void list(int a, int b)
	{  
        System.out.println("Place: " + Integer.toString(a) + ", " + Integer.toString(b) +
        		"; Org: " + world.getAt(this.x, this.y) + 
        		"; PIG?:" + world.pointInGrid(a,b) +  
        		"; newX: " + newX +
        		"; newY: " + newY +
         		"; time alive: " + this.timeAlive +
        		"; breedtime: " + breedIncrement +
        		"; notEat: " + this.eat() +
        		"; adjPoints: " + listString(checkAdjacentPoints(this.x, this.y)) +
        		"; adjBugs: " + listString(getAdjacentBugs(this.x, this.y))
        		//"; getAnt?: " + (getAnt(getAdjacentBugs(this.x, this.y)))
        		);
	}
	
	public boolean remove(int index)
	{
		world.setAt(this.x, this.y, null);
		return simulated;
	}

	@Override
	public DoodleBug makeChild(int childX, int childY) 
	{
		return new DoodleBug(world, childY, childY);
	}

	@Override
	public Organism makeChild() {
		// TODO Auto-generated method stub
		return null;
	}
	


}
