
public class Mail{
	public Mail(String post, String loc, String dest, Float dist)
	{
		postOffice = post;
		location = loc;
		destination = dest;
		distance = dist;
	}
	String postOffice;
	String location;
	String destination;
	Float distance;
	
	public String getPost()
	{
		return this.postOffice;
	}
	
	public void setPost(String post)
	{
		this.postOffice = post;
	}
	
	public String getLocation()
	{
		return this.location;
	}
	
	public void setLocation (String loc)
	{
		this.location = loc;
	}
	
	public String getDestination()
	{
		return this.destination;
	}
	
	public void setDestination(String dest)
	{
		this.destination = dest;
	}
	
	public Float getDistance()
	{
		return this.distance;
	}
	
	public void setDistance(Float dist)
	{
		this.distance = dist;
	}
}
