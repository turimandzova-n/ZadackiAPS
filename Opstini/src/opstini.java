import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class opstini {
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
        CBHT<String,String> tabela = new CBHT<>(2*N+1);
        
        String s=null;
        String []pom=null;
        String grad=null;
        String prvsaat=null;
        String vtorsaat=null;
        float temperatura=0;
        String barangrad=null;


        for(int i=0; i<N; i++) {
        	s=br.readLine();  
        	pom=s.split(" ");
        	
        	grad=pom[0];
        	prvsaat=pom[1];
        	vtorsaat=pom[2];
        	temperatura=Float.parseFloat(pom[3]);
        	
        	SLLNode<MapEntry<String, String>> curr=tabela.search(grad);
        	if(curr!=null) {
        		String starivrednosti=curr.element.value;
        		String []pomosen=null;
        		pomosen=starivrednosti.split(" ");
        		String pocetnovreme=pomosen[0];
        		String krajnovreme=pomosen[1];
        		float temp=Float.parseFloat(pomosen[2]);
        		
        		if(temp>temperatura) {
        			tabela.insert(grad, pocetnovreme+" "+krajnovreme+" "+temp);
        		}
        		else {
        			tabela.insert(grad, prvsaat+" "+vtorsaat+" "+temperatura);

        		}
        	}
        	else {
    			tabela.insert(grad, prvsaat+" "+vtorsaat+" "+temperatura);
        	}
        	
        }
      
        barangrad=br.readLine();
    	SLLNode<MapEntry<String, String>> curr=tabela.search(barangrad);
    	if(curr!=null) {
    		String vrednostite=curr.element.value;
    		String []p=null;
    		p=vrednostite.split(" ");
    		String saatbr1=p[0];
    		String saatbr2=p[1];
    		float vremeto=Float.parseFloat(p[2]);
    		String izlez=barangrad+" "+saatbr1+" "+saatbr2+" "+vremeto;
    		System.out.println(izlez);
    	}
    	else {
    		System.out.println("Ne postoi takov grad!");

    	}
      
}}

