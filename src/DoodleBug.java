public class DoodleBug extends Organism
{
	private Object[] buglist;
	
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
			move(x,y);	
	}
	
	public void simulate()
	{
		//don't simulate twice in a round
		
        //if (!eat())
        //{
        	super.simulate();
        
        if (!eat())
        {
        	super.move(x,y);
        }
        
        //}
        // make it so that if there's an ant to eat the ant is removed and the DoodleBug moves into that space
        // get the coordinates of the ant space
        // create a switch statement somewhere that cycles through each space to see if it's an ant
        /*while (true)
        {
	        if (world.getAt(x,y) == null) break;
	        else
	        {
	        if (world.getAt(x-1, y) != null && world.pointInGrid(x-1,  y) && world.getAt(x-1, y).equals("ant"))
				{ world.setAt(x-1,  y, null);
				  world.setAt(x-1,  y, this);
				  System.out.println(world.getAt(x-1, y) + "eaten at " + (x - 1) + ", " + y);
				  break;
				}
			if (world.getAt(x-1, y) != null && world.pointInGrid(x-1,  y) && world.getAt(x+1, y).equals("ant"))
				{ world.setAt(x+1,  y, null);
				  world.setAt(x+1,  y, this);
				  System.out.println(world.getAt(x+1, y) + "eaten at " + (x + 1) + ", " + y);
				  break;
				}
			if (world.getAt(x-1, y) != null && world.getAt(x, y-1).equals("ant"))
				{ world.setAt(x, y-1, null);
				  world.setAt(x, y-1, this);
				  System.out.println(world.getAt(x, y-1) + "eaten at " + x + ", " + (y - 1));
				  break;
				}
			if (world.getAt(x-1, y) != null && world.getAt(x, y+1).equals("ant"))
				{ world.setAt(x, y+1, null);
				  world.setAt(x, y+1, this);
				  System.out.println(world.getAt(x, y+1) + "eaten at " + x + ", " + (y + 1));
				  break;
				}
	        }
		    return;
		}*/
	        	
	        

    	//now move, breed, ....
        timeAlive++;
        breedIncrement++;
        list(x, y);
	}
	
	public boolean eat()
	{
		//if it is not this (doodlebug) and not null, eat is 
		if (getAnt(getAdjacentBugs(this.x, this.y)) != this && getAnt(getAdjacentBugs(this.x, this.y)) != null)
		{
			return false;
		}
		return true;
		
		/*if (world.getAt(x-1, y) == null || world.getAt(x-1, y) == this) return false;
		if (world.getAt(x+1, y) == null || world.getAt(x+1, y) == this) return false; 
		if (world.getAt(x, y-1) == null || world.getAt(x, y-1) == this) return false;
		if (world.getAt(x, y+1) == null || world.getAt(x, y+1) == this) return false;
		return true;
		*/
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
        		"; adjacentBugs: " + bugString(getAdjacentBugs(this.x, this.y)) +
        		"; getAnt?: " + (getAnt(getAdjacentBugs(this.x, this.y)))
        		);
	}
	
	public Object[] getAdjacentBugs(int x, int y)
	{
		buglist = new Object[4];
		if (world.pointInGrid(x-1, y))
		{
			buglist[0] = world.getAt(x-1, y);
		}
		else
		{
			buglist[0] = null;
		}
		if (world.pointInGrid(x+1, y))
		{
			buglist[1] = world.getAt(x+1, y);
		}
		else
		{
			buglist[1] = null;
		}
		if (world.pointInGrid(x, y+1))
		{
			buglist[2] = world.getAt(x, y+1);
		}
		else
		{
			buglist[2] = null;
		}
		if (world.pointInGrid(x, y-1))
		{
			buglist[3] = world.getAt(x, y-1);
		}
		else
		{
			buglist[3] = null;
		}
		return buglist;
	}
	
	public boolean remove(int index)
	{
		world.setAt(this.x, this.y, null);
		return simulated;
	}
	
	public boolean findAnt(Object[] items) //method overloading
	{
		//if I get the word ant from a string, how do I match that string with the object/place it's connected with?
		int antCount = 0;
		//look for the item in the list and remove it if it exists
		for(int i = 0; i < items.length; i++)
		{
			while (antCount < 2)
			{
				if(items[i].equals("ant"))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public Object getAnt(Object[] items) //method overloading
	{
		//if I get the word ant from a string, how do I match that string with the object/place it's connected with?
		//int antCount = 0;
		Object item = null;
		//look for the item in the list and remove it if it exists
		for(int i = 0; i < items.length; i++)
			{	
					if(items[i].toString().equals("ant"))
					{
						item = items[i];
						break;
					}
					else
					{
						item = null;
					}
			}
		return item;//what happens if I return false here? How do I track this?
	}
	
	//I'm going to make a buglist string next. Why? Because I think it will make it so I can really see if there's a bug 
	//surrounding the space. 
	
	public String bugString (Object[] items)
	{
		String listofbugs = "";
		
		for (int i = 0; i < items.length; i++)
		{
			listofbugs += items[i] + ", ";
		}
		return listofbugs;
	}

}
