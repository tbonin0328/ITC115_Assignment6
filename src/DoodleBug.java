public class DoodleBug extends Organism
{
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
		
        if (!eat())
        {
        	move();
        }
        // make it so that if there's an ant to eat the ant is removed and the DoodleBug moves into that space
        // get the coordinates of the ant space
        // create a switch statement somewhere that cycles through each space to see if it's an ant
        while (true)
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
		}
	        	
	        

    	//now move, breed, ....
        timeAlive++;
        breedIncrement++;
        list(x, y);
	}
	
	public boolean eat()
	{
		/*if (world.getAt(x-1, y).toString().equals("ant"))
			{ world.setAt(x-1,  y, null);
			  world.setAt(x-1,  y, this);
			  antsEaten++;
			  return true;
			}
		else if (world.getAt(x+1, y).toString().equals("ant"))
			{ world.setAt(x+1,  y, null);
			  world.setAt(x+1,  y, this);
			  antsEaten++;
			  return true;
			}
		else if (world.getAt(x, y-1).toString().equals("ant"))
			{ world.setAt(x, y-1, null);
			  world.setAt(x, y-1, this);
			  antsEaten++;
			  return true;
			}
		else if (world.getAt(x, y+1).toString().equals("ant"))
			{ world.setAt(x, y+1, null);
			  world.setAt(x, y+1, this);
			  antsEaten++;
			  return true;
			}
    	else
	    	{
	    		return false;
	    	}
    		*/
		
		if (!findAnt(getAdjacentBugs(this.x, this.y))) 
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
        		"; adjacentBugs: " + getAdjacentBugs(this.x, this.y)
        		);
	}
	
	public Object[] getAdjacentBugs(int x, int y)
	{
		Object[] buglist = new Object[4];
		
		//String listofbugs = "";
		
		buglist[0] = world.getAt(x-1, y);
		buglist[1] = world.getAt(x+1, y);
		buglist[2] = world.getAt(x, y+1);
		buglist[3] = world.getAt(x, y-1);
		return buglist;
		
		/*for (int i = 0; i < buglist.length; i++)
		{
			listofbugs += buglist[i] + ", ";
		}
		return listofbugs;
		*/
	}
	
	public boolean remove(int index)
	{
		world.setAt(this.x, this.y, null);
		return simulated;
	}
	
	public boolean  findAnt(Object[] items) //method overloading
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
		int antCount = 0;
		//look for the item in the list and remove it if it exists
		for(int i = 0; i < items.length; i++)
		{
			while (antCount < 2)
			{
				if(items[i].equals("ant"))
				{
					return items[i];
				}
			}
		}
		return false;//what happens if I return false here? How do I track this?
	}
}
