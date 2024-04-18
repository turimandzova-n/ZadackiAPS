import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class prodavnica {
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
        CBHT<String,String> tabela = new CBHT<>(200);
        String s=br.readLine();  
        String []pom=null;
        String proizvod=null;
        int kolicina=0;
        int cena=0;
        
        while(!s.equals("trgoveckraj")) {
        	pom=s.split(" ");
        	proizvod=pom[0];
        	kolicina=Integer.parseInt(pom[3]);
        	cena=Integer.parseInt(pom[2]);
            
        	SLLNode<MapEntry<String,String>> curr = tabela.search(proizvod);
        	if(curr!=null) {
        		String stara=curr.element.value;
        		String[] staro1=stara.split(" ");
        		int starakolicina=Integer.parseInt(staro1[0]);
        		int novakolicina=starakolicina+kolicina;
            	tabela.insert(proizvod, novakolicina+" "+cena);
        	}
        	else{
        		tabela.insert(proizvod, kolicina+" "+cena);
        	}
        	s=br.readLine();

        }
       // System.out.println(tabela);
        int broj = Integer.parseInt(br.readLine());
        String baranProizvod=null;
        String []pom1=null;
        int newkol=0;
        int newcena=0;
        int smetka=0;
        int starakolicina=0;

        for(int i=0;i<broj;i++)
        {
            String linija = br.readLine();
            pom1=linija.split(" ");
            baranProizvod=pom1[0];
            newkol=Integer.parseInt(pom1[1]);
            
           
            SLLNode<MapEntry<String,String>> curr = tabela.search(baranProizvod);
            String spoeno=curr.element.value;
            String []pom2=spoeno.split(" ");
            starakolicina=Integer.parseInt(pom2[0]);
            newcena=Integer.parseInt(pom2[1]);
            int preostanato;
            
            if(curr!=null) {
                System.out.println("Kupeno: "+newkol+" parcinja"+baranProizvod+" za kupna cena od "+newcena*newkol);
                preostanato=starakolicina-newkol;
                System.out.println("Preostanuvaat uste "+ preostanato +"parcinja");
                smetka+=newcena*newkol;
                tabela.insert(baranProizvod, preostanato+" "+newcena);
        }
            else {
                System.out.println("Ne postoi!");

            }
       
    }
        System.out.println("Smetakata iznesuva" +smetka);

}}

