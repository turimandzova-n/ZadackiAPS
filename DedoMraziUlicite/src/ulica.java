import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

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
}

class OBHT<K extends Comparable<K>, E> {

	// An object of class OBHT is an open-bucket hash table, containing entries
	// of class MapEntry.
	MapEntry<K, E>[] buckets;

	// buckets[b] is null if bucket b has never been occupied.
	// buckets[b] is former if bucket b is formerly-occupied
	// by an entry that has since been deleted (and not yet replaced).

	static final int NONE = -1; // ... distinct from any bucket index.

	private static final MapEntry former = new MapEntry(null, null);
	// This guarantees that, for any genuine entry e,
	// e.key.equals(former.key) returns false.

	private int occupancy = 0;
	// ... number of occupied or formerly-occupied buckets in this OBHT.

	@SuppressWarnings("unchecked")
	public OBHT(int m) {
		// Construct an empty OBHT with m buckets.
		buckets = (MapEntry<K, E>[]) new MapEntry[m];
	}

	private int hash(K key) {
		// Translate key to an index of the array buckets.
		return Math.abs(key.hashCode()) % buckets.length;
	}

	public int search(K targetKey) {
		// Find which if any bucket of this OBHT is occupied by an entry whose key
		// is equal to targetKey. Return the index of that bucket.
		int b = hash(targetKey);
		int n_search = 0;
		for (;;) {
			MapEntry<K, E> oldEntry = buckets[b];
			if (oldEntry == null)
				return NONE;
			else if (targetKey.equals(oldEntry.key))
				return b;
			else {
				b = (b + 1) % buckets.length;
				n_search++;
				if (n_search == buckets.length)
					return NONE;

			}
		}
	}

	public void insert(K key, E val) {
		// Insert the entry <key, val> into this OBHT.
		MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
		int b = hash(key);
		int n_search = 0;
		for (;;) {
			MapEntry<K, E> oldEntry = buckets[b];
			if (oldEntry == null) {
				if (++occupancy == buckets.length) {
					System.out.println("Hash tabelata e polna!!!");
				}
				buckets[b] = newEntry;
				return;
			} else if (oldEntry == former || key.equals(oldEntry.key)) {
				buckets[b] = newEntry;
				return;
			} else {
				b = (b + 1) % buckets.length;
				n_search++;
				if (n_search == buckets.length)
					return;

			}
		}
	}

	@SuppressWarnings("unchecked")
	public void delete(K key) {
		// Delete the entry (if any) whose key is equal to key from this OBHT.
		int b = hash(key);
		int n_search = 0;
		for (;;) {
			MapEntry<K, E> oldEntry = buckets[b];

			if (oldEntry == null)
				return;
			else if (key.equals(oldEntry.key)) {
				buckets[b] = former;// (MapEntry<K,E>)former;
				return;
			} else {
				b = (b + 1) % buckets.length;
				n_search++;
				if (n_search == buckets.length)
					return;

			}
		}
	}

	public String toString() {
		String temp = "";
		for (int i = 0; i < buckets.length; i++) {
			temp += i + ":";
			if (buckets[i] == null)
				temp += "\n";
			else if (buckets[i] == former)
				temp += "former\n";
			else
				temp += buckets[i] + "\n";
		}
		return temp;
	}

	public OBHT<K, E> clone() {
		OBHT<K, E> copy = new OBHT<K, E>(buckets.length);
		for (int i = 0; i < buckets.length; i++) {
			MapEntry<K, E> e = buckets[i];
			if (e != null && e != former)
				copy.buckets[i] = new MapEntry<K, E>(e.key, e.value);
			else
				copy.buckets[i] = e;
		}
		return copy;
	}

	public char[] getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object get(String angliski) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean containsKey(String angliski) {
		// TODO Auto-generated method stub
		return false;
	}

	
}


class MapEntry<K extends Comparable<K>, E> implements Comparable<K> {

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

public class ulica {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		OBHT<String, String> tabela1 = new OBHT<String, String>(2 * N + 1);
		String s = null;
		String ime;
		String ulica;
		for(int i=0; i<N; i++) {
			s=br.readLine();
			ime=s;
			s=br.readLine();
			ulica=s;
			tabela1.insert(ime, ulica);
		}
		int M = Integer.parseInt(br.readLine());
		OBHT<String, String> tabela2 = new OBHT<String, String>(2 * M + 1);
		String s1 = null;
		String []pom = null;
		String staroime;
		String novoime;
		String imeto;
		for(int i=0; i<M; i++) {
			s1=br.readLine();
			pom=s1.split(" ");
			staroime=pom[0];
			novoime=pom[1];
			tabela2.insert(staroime, novoime);
		}

		imeto=br.readLine();

		String []ul=null;
		String tocnotoime=null;
		String novaulica=null;
		String krajno=null;
		
		int h=tabela1.search(imeto);
		
		if(h==-1) {
			System.out.println("Ne postoi takvo dete");
		}
		else {
			ul = tabela1.buckets[h].value.split(" ");
			tocnotoime=ul[0];
			String broj = ul[1];
			String grad=ul[2];
			String drzava=ul[3];

			int h2=tabela2.search(tocnotoime);
			if(h2==-1) {
				System.out.println("Ne postoi takva ulica");
			}
			else {
				novaulica=tabela2.buckets[h2].value;
			}
		    krajno=novaulica+" "+broj+" "+grad+" "+drzava;
		}
		
		System.out.println(krajno);
		

	}}

