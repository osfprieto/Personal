package misc;

import java.util.Scanner;

public class LeaguePicks {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int pos, friends, picks, arr[];
		LeaguePicks obj = new LeaguePicks();
		while(sc.hasNext()){
			pos = sc.nextInt();
			friends = sc.nextInt();
			picks = sc.nextInt();
			
			arr = obj.returnPicks(pos, friends, picks);
			
			print(arr);
			
		}
		
	}
	
	public static void print(int[] arr){
		System.out.print("{");
		boolean ini = false;
		for(int i=0;i<arr.length;i++)
			if(!ini){
				System.out.print(arr[i]);
				ini=true;
			}
			else
				System.out.print(", "+arr[i]);
		System.out.println("}");
	}
	
	public int[] returnPicks(int pos, int friends, int picks){
		int contPicks, i=1, sentido=0;//cero a la derecha, 1 a la izquierda
		Lista list = new Lista();
		for(contPicks=1;contPicks<=picks;contPicks++){
			if(i==pos)
				list.add(contPicks);
			if(sentido==0){
				if(i==friends)
					sentido=1;
				else
					i++;
			}
			else if(sentido==1){
				if(i==1)
					sentido=0;
				else
					i--;
			}
		}
		
		int arr[] = new int[list.length];
		Nodo temp = list.head;
		i=0;
		while(temp.next!=null){
			arr[i++] = temp.valor;
			temp = temp.next;
		}
		arr[i] = temp.valor;
		
		return arr;
	}
	public class Nodo{
		public int valor;
		public Nodo next;
		public Nodo(int valor, Nodo next){
			this.valor = valor;
			this.next = next;
		}
	}
	public class Lista{
		public Nodo head;
		public Nodo tail;
		public int length;
		public Lista(){
			head = null;
			tail = null;
			length=0;
		}
		public void add(int valor){
			Nodo nodo = new Nodo(valor, null);
			if(isEmpty()){
				head = nodo;
				tail = nodo;
				length=1;
			}
			else{
				tail.next = nodo;
				tail = nodo;
				length++;
			}
		}
		public boolean isEmpty(){
			return head==null;
		}
	}
}
