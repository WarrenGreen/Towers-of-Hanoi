import java.util.LinkedList;

public class Towers {
	private int disks;
	private LinkedList<Integer>[] pegs;

	public static void main(String args[]) {
		Towers game = new Towers(5);
		game.printTowers();
		game.solveTowers();
	}

	public Towers(int disks) {
		this.disks = disks;
		pegs = (LinkedList<Integer>[]) new LinkedList[3];

		for (int x = 0; x < 3; x++)
			pegs[x] = new LinkedList<Integer>();

		for (int x = disks; x > 0; x--)
			pegs[0].add(x);
	}

	public void solveTowers() {
		while(pegs[1].size()<5){
			if(pegs[0].size()>0)
				move(0,0,1);
			if(pegs[2].size()>0)
				move(0,2,1);
		}
	}

	public void move(int disk, int start, int end) {
		boolean faultFree = true;

		if (pegs[start].size() - 1 > disk) {
			move(disk + 1, start, 3 - (start + end));
			faultFree = false;
		}
		
		if (pegs[end].size() > 0 && pegs[end].getLast() < pegs[start].getLast()) {
			int y=0;
			for(int x=0;x<pegs[end].size();x++)
				if(pegs[end].get(x)<pegs[start].get(disk))
					y=x;
			move(y, end, 3 - (start + end));
			faultFree = false;
		}
		
		if(!faultFree)
			move(disk, start, end);
		else{
			pegs[end].add(pegs[start].get(disk));
			pegs[start].remove(disk);

			printTowers();
		}

	}

	public void printTowers() {

		for (int x = disks - 1; x >= 0; x--) {
			for (int y = 0; y < 3; y++)
				if (x < pegs[y].size())
					System.out.print("\t  " + pegs[y].get(x) + "  ");
				else
					System.out.print("\t     ");
			System.out.println();
		}

		System.out.println("\t_____\t_____\t_____");
		System.out.println("\t  1  \t  2  \t  3  ");
	}
}
