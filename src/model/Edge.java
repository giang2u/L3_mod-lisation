package model;
public class Edge
{
   int from;
   private int to;
   int cost;
   public Edge(int x, int y, int cost)
	 {
		this.from = x;
		this.setTo(y);
		this.cost = cost;
	 }
   
   
   public int depart(){
	   return from;
   }
   
   public int arrive(){
	   return getTo();
   }
   
   public int cout(){
	   return cost;
   }


public int getTo() {
	return to;
}


public void setTo(int to) {
	this.to = to;
}
   
}
