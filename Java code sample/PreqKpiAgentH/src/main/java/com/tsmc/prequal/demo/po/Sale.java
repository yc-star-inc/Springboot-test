package com.tsmc.prequal.demo.po;

public class Sale {

	private int id; 
	private String item; 
	private int quantity; 
	private float amount;
	
	public Sale() { }
	
	public Sale(String _item, int _qty, float _amount) {
		this.item = _item; 
		this.quantity = _qty; 
		this.amount = _amount; 
	}
	@Override
	public String toString() {
		return "SalePO [id=" + id + ", item=" + item + ", quantity=" + quantity + ", amount=" + amount + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	} 
	
	
}
