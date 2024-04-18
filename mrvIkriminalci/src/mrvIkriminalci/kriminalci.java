package mrvIkriminalci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class kriminalci {
    public static class SLLNode<E> {
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
    }

    public static class CBHT<K extends Comparable<K>, E> {

        // An object of class CBHT is a closed-bucket hash table, containing
        // entries of class MapEntry.
        private SLLNode<MapEntry<K, E>>[] buckets;

        @SuppressWarnings("unchecked")
        public CBHT(int m) {
            // Construct an empty CBHT with m buckets.
            buckets = (SLLNode<MapEntry<K, E>>[]) new SLLNode[m];
        }

        private int hash(K key) {
            // Translate key to an index of the array buckets.
            return Math.abs(key.hashCode()) % buckets.length;
        }

        public SLLNode<MapEntry<K, E>> search(K targetKey) {
            // Find which if any node of this CBHT contains an entry whose key is
            // equal
            // to targetKey. Return a link to that node (or null if there is none).
            int b = hash(targetKey);
            for (SLLNode<MapEntry<K, E>> curr = buckets[b]; curr != null; curr = curr.succ) {
                if (targetKey.equals(((MapEntry<K, E>) curr.element).key))
                    return curr;
            }
            return null;
        }

        public void insert(K key, E val) {        // Insert the entry <key, val> into this CBHT.
            MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
            int b = hash(key);
            for (SLLNode<MapEntry<K, E>> curr = buckets[b]; curr != null; curr = curr.succ) {
                if (key.equals(((MapEntry<K, E>) curr.element).key)) {
                    // Make newEntry replace the existing entry ...
                    curr.element = newEntry;
                    return;
                }
            }
            // Insert newEntry at the front of the 1WLL in bucket b ...
            buckets[b] = new SLLNode<MapEntry<K, E>>(newEntry, buckets[b]);
        }

        public void delete(K key) {
            // Delete the entry (if any) whose key is equal to key from this CBHT.
            int b = hash(key);
            for (SLLNode<MapEntry<K, E>> pred = null, curr = buckets[b]; curr != null; pred = curr, curr = curr.succ) {
                if (key.equals(((MapEntry<K, E>) curr.element).key)) {
                    if (pred == null)
                        buckets[b] = curr.succ;
                    else
                        pred.succ = curr.succ;
                    return;
                }
            }
        }

        public String toString() {
            String temp = "";
            for (int i = 0; i < buckets.length; i++) {
                temp += i + ":";
                for (SLLNode<MapEntry<K, E>> curr = buckets[i]; curr != null; curr = curr.succ) {
                    temp += curr.element.toString() + " ";
                }
                temp += "\n";
            }
            return temp;
        }

    }

    static class MapEntry<K extends Comparable<K>, E> implements Comparable<K> {

        // Each MapEntry object is a pair consisting of a key (a Comparable
        // object) and a value (an arbitrary object).
        K key;
        E value;

        public MapEntry(K key, E val) {
            this.key = key;
            this.value = val;
        }

        public int compareTo(K that) {
            // Compare this map entry to that map entry.
            @SuppressWarnings("unchecked")
            MapEntry<K, E> other = (MapEntry<K, E>) that;
            return this.key.compareTo(other.key);
        }

        public String toString() {
            return "<" + key + "," + value + ">";
        }
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(br.readLine());
        CBHT<String,String> tabela1 = new CBHT<>(2*N+1);
        CBHT<String,String> tabela2 = new CBHT<>(2*N+1);

        String ime=null;
        String s1=null;
        String s2=null;
        
      
          for(int i=0; i<N; i++) {
              ime=br.readLine();
              s1=br.readLine();  
              s2=br.readLine(); 
              tabela1.insert(s1,ime);
              tabela2.insert(s2,ime);
          }
        
        String sekvenca1=br.readLine();
        String sekvenca2=br.readLine();
        
        SLLNode<MapEntry<String, String>> curr1=tabela1.search(sekvenca1);
        SLLNode<MapEntry<String, String>> curr2=tabela2.search(sekvenca1);

        if(curr1!=null) {
            SLLNode<MapEntry<String, String>> curr11=tabela2.search(sekvenca2);
            if(curr11!=null) {
            	System.out.println(curr11.element.value);

            }
            else {
            	System.out.println("Ne moze da se pronajde liceto");
            }
        }
        if(curr2!=null) {
        	SLLNode<MapEntry<String, String>> curr22=tabela1.search(sekvenca2);
            if(curr22!=null) {
            	System.out.println(curr22.element.value);

            }
            else {
            	System.out.println("Ne moze da se pronajde liceto");
            }
        }
     
}}


