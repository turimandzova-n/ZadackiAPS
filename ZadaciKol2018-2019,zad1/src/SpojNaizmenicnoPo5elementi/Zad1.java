package SpojNaizmenicnoPo5elementi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class SLL<E> {
   SLLNode<E> first;

   public SLL() {
       // Construct an empty SLL
       this.first = null;
   }

   public void deleteList() {
       first = null;
   }

   public int length() {
       int ret;
       if (first != null) {
           SLLNode<E> tmp = first;
           ret = 1;
           while (tmp.succ != null) {
               tmp = tmp.succ;
               ret++;
           }
           return ret;
       } else
           return 0;

   }

   @Override
   public String toString() {
       String ret = new String();
       if (first != null) {
           SLLNode<E> tmp = first;
           ret += tmp + "->";
           while (tmp.succ != null) {
               tmp = tmp.succ;
               ret += tmp + "->";
           }
       } else
           ret = "Prazna lista!!!";
       return ret;
   }

   public void insertFirst(E o) {
       SLLNode<E> ins = new SLLNode<E>(o, first);
       first = ins;
   }

   public void insertAfter(E o, SLLNode<E> node) {
       if (node != null) {
           SLLNode<E> ins = new SLLNode<E>(o, node.succ);
           node.succ = ins;
       } else {
           System.out.println("Dadenot jazol e null");
       }
   }

   public void insertBefore(E o, SLLNode<E> before) {

       if (first != null) {
           SLLNode<E> tmp = first;
           if (first == before) {
               this.insertFirst(o);
               return;
           }
           // ako first!=before
           while (tmp.succ != before)
               tmp = tmp.succ;
           if (tmp.succ == before) {
               SLLNode<E> ins = new SLLNode<E>(o, before);
               tmp.succ = ins;
           } else {
               System.out.println("Elementot ne postoi vo listata");
           }
       } else {
           System.out.println("Listata e prazna");
       }
   }

   public void insertLast(E o) {
       if (first != null) {
           SLLNode<E> tmp = first;
           while (tmp.succ != null)
               tmp = tmp.succ;
           SLLNode<E> ins = new SLLNode<E>(o, null);
           tmp.succ = ins;
       } else {
           insertFirst(o);
       }
   }

   public E deleteFirst() {
       if (first != null) {
           SLLNode<E> tmp = first;
           first = first.succ;
           return tmp.element;
       } else {
           System.out.println("Listata e prazna");
           return null;
       }
   }

   public E delete(SLLNode<E> node) {
       if (first != null) {
           SLLNode<E> tmp = first;
           if (first == node) {
               return this.deleteFirst();
           }
           while (tmp.succ != node && tmp.succ.succ != null)
               tmp = tmp.succ;
           if (tmp.succ == node) {
               tmp.succ = tmp.succ.succ;
               return node.element;
           } else {
               System.out.println("Elementot ne postoi vo listata");
               return null;
           }
       } else {
           System.out.println("Listata e prazna");
           return null;
       }

   }

   public SLLNode<E> getFirst() {
       return first;
   }

   public SLLNode<E> find(E o) {
       if (first != null) {
           SLLNode<E> tmp = first;
           while (tmp.element != o && tmp.succ != null)
               tmp = tmp.succ;
           if (tmp.element == o) {
               return tmp;
           } else {
               System.out.println("Elementot ne postoi vo listata");
           }
       } else {
           System.out.println("Listata e prazna");
       }
       return first;
   }



   public void mirror() {
       if (first != null) {
           // m=nextsucc, p=tmp,q=next
           SLLNode<E> tmp = first;
           SLLNode<E> newsucc = null;
           SLLNode<E> next;

           while (tmp != null) {
               next = tmp.succ;
               tmp.succ = newsucc;
               newsucc = tmp;
               tmp = next;
           }
           first = newsucc;
       }

   }

   public void merge(SLL<E> in) {
       if (first != null) {
           SLLNode<E> tmp = first;
           while (tmp.succ != null)
               tmp = tmp.succ;
           tmp.succ = in.getFirst();
       } else {
           first = in.getFirst();
       }
   }

   public SLLNode<Integer> getLast() {
       // TODO Auto-generated method stub
       return null;
   }
}

class SLLNode<E> {
   protected E element;
   protected SLLNode<E> succ;

   public SLLNode(E elem, SLLNode<E> succ) {
       this.element = elem;
       this.succ = succ;
   }

   @Override
   public String toString() {
       return element.toString();
   }

   public void insertbefore(int y, SLLNode<Integer> tmp) {
       // TODO Auto-generated method stub

   }
}

public class Zad1{
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		SLL<Integer> lista1 = new SLL<Integer>();

		String []pom = br.readLine().split(" ");
		
		for(int i=0; i<pom.length; i++)
		{
			lista1.insertLast(Integer.parseInt(pom[i]));
		}
		int n=Integer.parseInt(br.readLine());
        int m=Integer.parseInt(br.readLine());
		zadaca(lista1,n,m);
		
		
	}
	
	public static void zadaca(SLL<Integer> lista1, int n, int m)
	{
		SLLNode<Integer> tmp =lista1.getFirst();
		int br=1;
		int sum=0;

		while(tmp!=null)
		{
			if(br>=n && br<=m)
			{
				sum+=sum+tmp.element;
			}
			br++;
			tmp=tmp.succ;
			
		}
		tmp=lista1.getFirst();
		while(tmp!=null)
		{
			lista1.insertBefore(sum, tmp);
			tmp=tmp.succ;
		}
			
		System.out.println(lista1);
		
}
}