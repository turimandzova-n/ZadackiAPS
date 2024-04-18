/*
Ispitna zadaca od Juni 2021

Dokolku nekoj broj se pojavuva vo listata paren broj pati istiot se brise.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
class DLLNode<E> {
    protected E element;
    protected DLLNode<E> pred, succ;

    public DLLNode(E element, DLLNode<E> pred, DLLNode<E> succ) {
        this.element = element;
        this.pred = pred;
        this.succ = succ;
    }

    public String toString() {
        return element.toString();
    }
}

class DLL<E> {
    private DLLNode<E> first, last;

    public DLL() {
        this.first = null;
        this.last = null;
    }

    public void deleteList() {
        first = null;
        last = null;
    }

    public DLLNode<E> getFirst() {
        return first;
    }

    public DLLNode<E> getLast() {
        return last;
    }

    public void insertFirst(E o) {
        DLLNode<E> nov = new DLLNode<E>(o, null, first);
        if (first == null)
            first = last = nov;
        else {
            first.pred = nov;
            first = nov;
        }
    }

    public void insertLast(E o) {
        DLLNode<E> nov = new DLLNode<E>(o, last, null);
        if (first == null)
            first = last = nov;
        else {
            last.succ = nov;
            last = nov;
        }
    }

    public void insertAfter(E o, DLLNode<E> after) {
        if (last == after) {
            insertLast(o);
            return;
        }
        DLLNode<E> nov = new DLLNode<E>(o, after, after.succ);
        after.succ.pred = nov;
        after.succ = nov;
    }

    public void insertBefore(E o, DLLNode<E> before) {
        if (before == first) {
            insertFirst(o);
            return;
        }
        DLLNode<E> nov = new DLLNode<E>(o, before.pred, before);
        before.pred.succ = nov;
        before.pred = nov;
    }

    public DLLNode<E> find(E o) {
        if (first != null) {
            DLLNode<E> tmp = first;
            while (tmp != null && tmp.element != o) {
                tmp = tmp.succ;
                if (tmp == null)
                    System.out.println("Elementot ne postoi vo listata");
                else
                    return tmp;
            }
        } else
            System.out.println("Listata e prazna");
        return null;

    }

    public DLLNode<E> findLast(E o) {
        if (first != null) {
            DLLNode<E> tmp = last;
            while (tmp != null && tmp.element != o)
                tmp = tmp.succ;
            if (tmp == null)
                System.out.println("Elementot ne postoi vo listata");
            else
                return tmp;
        } else
            System.out.println("Listata e prazna");
        return null;
    }

    public E deleteFirst() {
        if (first != null) {
            DLLNode<E> node = first;
            first = first.succ;
            if (first == null)
                last = null;
            else
                first.pred = null;
            return node.element;
        } else

            return null;

    }

    public E deleteLast() {
        if (first != null) {
            DLLNode<E> node = last;
            last = last.pred;
            if (last == null)
                first = null;
            else
                last.succ = null;
            return node.element;
        } else
            return null;
    }

    public E delete(DLLNode<E> node) {
        if (node == first) {
            // return deleteFirst(); ->moze i vaka
            deleteFirst();
            return node.element;
        }
        if (node == last) {
            // return deleteLast(); ->moze i vaka
            deleteLast();
            return node.element;
        }
        node.succ.pred = node.pred;
        node.pred.succ = node.succ;
        return node.element;
    }

    public String toString() {
        String ret = new String();
        ret = "";
        if (first != null) {
            DLLNode<E> tmp = first;

            while (tmp != null) {
                ret += tmp + "<->";
                tmp = tmp.succ;
            }

        } else
            ret = "Prazna lista!!";
        return ret;
    }

    public String toStringR() {
        String ret = new String();
        ret = "";
        if (first != null) {
            DLLNode<E> tmp = last;
            while (tmp != null) {
                ret += tmp + "<->";
                tmp = tmp.pred;
            }
        } else
            ret = "Prazna lista!!!";

        return ret;
    }
}

public class algoritmi {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        DLL<Integer> lista=new DLL<Integer>();
        int N=Integer.parseInt(br.readLine());
        String []pom;
        pom=br.readLine().split(" ");
        for(int i=0;i<N;i++){
            lista.insertLast(Integer.parseInt(pom[i]));
        }
        PodeliPoParnost(lista);
    }
    private static void PodeliPoParnost(DLL<Integer> lista){
        DLLNode<Integer> tmp=lista.getFirst();
        DLLNode<Integer> tmp1=null;
        DLLNode<Integer> nov= null;
        int brojac=0;
        while(tmp!=null && tmp.succ!=null) {
            if (tmp.element == tmp.succ.element) {
                nov = tmp;
                while (nov != null && tmp.succ != null && nov.element == tmp.succ.element) {
                    brojac++;
                    nov = nov.succ;
                }
                int brojac1 = 1;
                if (brojac % 2 == 0) {
                    tmp1 = tmp;
                    while (brojac1 != brojac) {
                        brojac1++;
                        lista.delete(tmp1);
                        tmp1 = tmp1.succ;
                    }
                } else {
                    tmp = tmp.succ;
                }
            }
            brojac = 0;
            tmp = tmp.succ;
        }
        System.out.println(lista);
    }
}