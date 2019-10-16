package Matze;
import java.util.ArrayList;

public class Order {
	
	private int orderNumber, orderPrice;
	private Frame orderFrame;
	private ArrayList<Package> orderPackages;
	
	/* Konstruktor */
	public Order(int orderNumber, int orderPrice, Frame orderFrame, ArrayList<Package> orderPackages) {
		super();
		this.orderNumber = orderNumber;
		this.orderPrice = orderPrice;
		this.orderFrame = orderFrame;
		this.orderPackages = orderPackages;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public int getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}

	public Frame getOrderFrame() {
		return orderFrame;
	}

	public void setOrderFrame(Frame orderFrame) {
		this.orderFrame = orderFrame;
	}

	public ArrayList<Package> getOrderPackages() {
		return orderPackages;
	}

	public void setOrderPackages(ArrayList<Package> orderPackages) {
		this.orderPackages = orderPackages;
	}
	
	

}
