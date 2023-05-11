package br.com.via;

public class Product {
	private String name;
	private String size;
	private String colour;
	private int quantity;
	
	public Product(String name, String size, String colour, int quantity) {
		super();
		this.name = name;
		this.size = size;
		this.colour = colour;
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
