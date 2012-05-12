import ui.DrawPanel;
import ui.Window;
import hashlife.*;
import util.RLE;

public class Main {
	
	final static BooleanCell f = BooleanCell.False;
	final static BooleanCell t = BooleanCell.True;
	
	static public void main(String[] args) {
		Window w = new Window();
		w.setVisible(true);
		
		testHashlife();
		testDrawer(w.getDrawPanel());
		//testRLE();
	}
	
	static void testRLE() {
		
		boolean[][] t = RLE.read("media/glider.rle");
		
		System.out.println(MacroCell.niceStringFromTab(t));
		
	}
	
	static void testHashlife() {
		MacroCell a = get(f, t, f, f);
		MacroCell b = get(f, f, t, f);
		MacroCell c = get(t, t, f, f);
		MacroCell d = get(t, f, f, f);
		MacroCell planeur = get(a,b,c,d);
		
		MacroCell r = planeur.evolve(1);
		
		System.out.println(a.off);
		System.out.println(planeur.simplify().niceString());
		System.out.println(r.niceString());
		System.out.println(r.evolve(4).niceString());
	}
	
	static void testDrawer(DrawPanel drawer){
		MacroCell a = get(f, t, f, f);
		MacroCell b = get(f, f, t, f);
		MacroCell c = get(t, t, f, f);
		MacroCell d = get(t, f, f, f);
		MacroCell planeur = get(a,b,c,d);
		
		while(true){
			drawer.setCell(planeur);
			drawer.reDraw();
			planeur = planeur.evolve(4);
			System.out.println("Draw!!");
			for(int i=0; i<1000000000; i++){
				
			}
		}
	}
	
	static MacroCell empty(int n) {
		return MacroCell.empty(n);
	}
	
	static MacroCell get(MacroCell m0, MacroCell m1, MacroCell m2, MacroCell m3) {
		return MacroCell.get(m0, m1, m2, m3);
	}

}
