package varios;

import java.util.Scanner;

public class Candies {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		int n, money, i;
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		Nodo tempCount, tempCost;
		ListaAsc count;
		ListaDesc cost;
		while(n!=0){
			money = 0;
			
			count = new ListaAsc();
			cost = new ListaDesc();
			
			for(i=0;i<n;i++)
				count.add(sc.nextInt());
			for(i=0;i<n;i++)
				cost.add(sc.nextInt());
			
			for(i=0, tempCount = count.head, tempCost = cost.head;i<n;i++, tempCount = tempCount.next, tempCost = tempCost.next){
				money+=tempCount.value*tempCost.value;
				//System.out.println(tempCount.value+", "+tempCost.value);
			}
			
			System.out.println(money);
			n = sc.nextInt();
		}
	}
	
	public static class Nodo{
		public int value;
		public Nodo next;
		public Nodo(int value, Nodo next){
			this.value = value;
			this.next = next;
		}
	}
	public static class ListaAsc{
		public Nodo head;
		public ListaAsc(){
			head = null;
		}
		public void add(int value){
			Nodo nodo = new Nodo(value, null);
			if(head == null)
				head = nodo;
			else if(nodo.value<head.value){
				nodo.next = head;
				head = nodo;
			}
			else{
				Nodo temp = head;
				while(temp.next!=null && temp.next.value<nodo.value)
					temp = temp.next;
				nodo.next = temp.next;
				temp.next = nodo;
			}
		}
	}
	
	public static class ListaDesc{
		public Nodo head;
		public ListaDesc(){
			head = null;
		}
		public void add(int value){
			Nodo nodo = new Nodo(value, null);
			if(head == null)
				head = nodo;
			else if(nodo.value>head.value){
				nodo.next = head;
				head = nodo;
			}
			else{
				Nodo temp = head;
				while(temp.next!=null && temp.next.value>nodo.value)
					temp = temp.next;
				nodo.next = temp.next;
				temp.next = nodo;
			}
		}
	}
	
}
