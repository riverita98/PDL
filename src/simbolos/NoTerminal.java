package simbolos;

import java.util.ArrayList;

public class NoTerminal {
	
		ArrayList<String> first;
		ArrayList<String> follow;
		
		public NoTerminal() {
			this.first = new ArrayList<String>();
			this.follow = new ArrayList<String>();
		}
		
		public void addToFirst(ArrayList<String> u) {
			for (String s : u) {
				first.add(s);
			}
		}
		
		public void addToFollow(ArrayList<String> u) {
			for (String s : u) {
				follow.add(s);
			}
		}
		
		public boolean contenidoFirst(String s) {
			return first.contains((String) s);
		}
		
		public boolean contenidoFollow(String s) {
			return follow.contains((String) s);
		}	
		
}
