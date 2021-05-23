package product;

public interface Product 
{
	public int getId();
	public String getName();
	public double getPurchasePrice();
	public double getSellPrice();
	public int getQuantity();
	public String getDescription();
	
	public void setName(String name);
	public void setSellPrice(double price);
	public void setPurchasePrice(double price);
	public void buyQuantity(int amount);
	public void sellQuantity(int amount);
	public void setQuantity(int amount);
	public void setDescription(String desc);
	public boolean equals(Object o);
}
