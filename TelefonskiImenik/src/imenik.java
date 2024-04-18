import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class imenik {
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
        CBHT<String,String> table = new CBHT<>(200);
        String line = br.readLine().toLowerCase(); // cela linija
        while (!line.equals("#"))
        {
            //String line = br.readLine(); // cela linija
            String izraz[] = line.toLowerCase().split(","); // zborovite + brojot
            String zboroviVoIzraz[]=izraz[0].split(" "); // fat + halimi
            String broj = izraz[1];
            for(int i=0;i<zboroviVoIzraz.length;i++)
                table.insert(zboroviVoIzraz[i],broj);
            table.insert(izraz[0],broj);
            line=br.readLine().toLowerCase();
        }

        int broj = Integer.parseInt(br.readLine());
        for(int i=0;i<broj;i++)
        {
            String linija = br.readLine().toLowerCase();
            SLLNode<MapEntry<String,String>> curr = table.search(linija);
            if(curr!=null)
                System.out.println(curr.element.value);
            else
                System.out.println("Not found");
        }
    }}

