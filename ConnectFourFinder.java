import java.util.*;
import java.io.*;
class Temp{
	public static void main (String [] args) throws IOException{
		BufferedReader in = new BufferedReader (new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter (new BufferedWriter (new OutputStreamWriter (System.out)));
		StringBuilder string = new StringBuilder ();
		
		StringTokenizer st = new StringTokenizer (in.readLine());
		int rows = Integer.parseInt(st.nextToken());
		int cols = Integer.parseInt(st.nextToken());
		
		char [][] grid = new char [rows][cols];
		
		for (int r = 0; r < rows; r++){
			String input = in.readLine();
			for (int c = 0; c < cols; c++){
				grid[r][c] = input.charAt(c);
			}
		}
		
		/*
		for (int r = 0; r < rows; r++){
			for (int c = 0; c < cols; c++){
				string.append(grid[r][c] + " ");
			}
			string.append("\n");
		}
		out.printf("%s", string.toString());
		out.close(); */
		
		if (winner(grid, '&', rows, cols)){
			string.append("Connect Four\n");
		} else{
			string.append("Sorry\n");
		}
		out.printf("%s", string.toString());
		out.close();
	}
	
	static boolean winner (char [][] grid, char playerSymbol, int rows, int cols){
		
		int [] dx = {1, -1, 0, 0, 1, -1, -1, 1};
		int [] dy = {0, 0, 1, -1, 1, 1, -1, -1};
		boolean found = false;
		loop:
		for (int r = rows - 1; r >= 0; r--){
			for (int c = cols - 1; c >= 0; c--){
				if (grid[r][c] == playerSymbol){
					Point start = new Point(-1, 1, r, c);
					Queue<Point> queue  = new LinkedList<Point>();
					queue.add(start);
					//int matches = 0;
					
					while (!queue.isEmpty()){
						Point current = queue.remove();
						
						//System.out.printf("Row: %d, Col:%d, Direction:%d, Run: %d%n", current.row, current.col, current.direction, current.run);
						
						if (current.run == 4){
							found = true;
							//System.out.printf("Row: %d, Col:%d, Direction:%d%n", current.row, current.col, current.direction);
							break loop;
						} 
						
						if (current.direction == -1){
							for (int i = 0; i < 8; i++){
								int newx = current.row + dx[i];
								int newy = current.col + dy[i];
								
								if (newx >= 0 && newx < rows && newy < cols && newy >= 0 && grid[newx][newy] == playerSymbol){
									queue.add(new Point (findDirection(dx[i], dy[i]), current.run + 1, newx, newy));
								}
							}
						} else{
							for (int i = 0; i < 8; i++){
								int newx = current.row + dx[i];
								int newy = current.col + dy[i];
								int d = findDirection(dx[i], dy[i]);
								
								if (newx >= 0 && newx < rows && newy < cols && newy >= 0 && grid[newx][newy] == playerSymbol && d == current.direction){
									queue.add(new Point(d, current.run + 1, newx, newy));
								}
							}
						}
					}
				} 
			}
		}
		return found;
	}
	
	static int findDirection (int x, int y){
		if (x == 1 && y == 0){
			return 0;
		} else if (x == -1 && y == 0){
			return 180;
		}else if (x == 0 && y == 1){
			return 90;
		} else if (x == 0 && y == -1){
			return 270;
		} else if (x == 1 && y == 1){
			return 45;
		} else if (x == -1 && y == 1){
			return 135;
		} else if (x == -1 && y == -1){
			return 225;
		} else {
			return 315;
		}
	}
	
	static class Point{
		public int direction, run, row, col;
		
		Point(int direction, int run, int row, int col){
			this.direction = direction;
			this.run = run;
			this.row = row;
			this.col = col;
		}
	}
}
