package mazeProject;


import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class Maze {

	private static int [][] MazeArray;
	private static int Rows;
	private static int Cols;
	private static Point end = new Point();
	private static Point start = new Point();

	public static void ReadFileMakeMaze() {

		Scanner in = new Scanner(System.in);
		System.out.print("Select File: "); // Choose file
		String fileName = in.nextLine();
		fileName = fileName.trim();

		String Buffer = "";
		String[] Buffer2;
		String[] MazeBuffer;
		int Counter = 0;

		try {

			// Read input file
			BufferedReader ReadFileContents = new BufferedReader(new FileReader(fileName+".txt"));
			Buffer = ReadFileContents.readLine();
			MazeBuffer = Buffer.split(" ");

			// Creating MazeArray according to rows and columns from input file.
			Rows = Integer.parseInt(MazeBuffer[1]);
			Cols = Integer.parseInt(MazeBuffer[0]);
			MazeArray = new int[Rows][Cols];

			// Retrieving start locations and adding them to an X and Y coordinate.
			String[] StartPoints = ReadFileContents.readLine().split(" ");
			start.x = Integer.parseInt(StartPoints[0]);
			start.y = Integer.parseInt(StartPoints[1]);

			// Retrieving end locations and adding them to an X and Y coordinate.
			String[] EndPoints = ReadFileContents.readLine().split(" ");
			end.x = Integer.parseInt(EndPoints[0]);
			end.y = Integer.parseInt(EndPoints[1]);

			while(ReadFileContents.ready()) {
				Buffer = ReadFileContents.readLine();
				Buffer2 = Buffer.split(" ");

				for(int i = 0; i < Buffer2.length; i++) {
					MazeArray[Counter][i] = Integer.parseInt(Buffer2[i]); // Adding file Maze to MazeArray.
				}
				Counter ++;
				}
			}

			catch(Exception e){
				System.out.println(e); // No files found.
			}

		System.out.println(SolveMaze(start.x, start.y));
	}


	public static boolean SolveMaze(int x,int y) {

		Print(); // Printing the maze

		if(ReachedEnd(x,y)) {
			MazeArray[x][y] = 5; // 5 represents the end
			System.out.println(Arrays.deepToString(MazeArray));
			return true;

		}else {

			MazeArray[x][y] = 8; // Marking the path with 8's
			start.x = x;
			start.y = y;

			// Checking all directions
			if(MazeArray[x][y - 1] == 0 ) { 
				System.out.println("Left");
				SolveMaze(x, y - 1);
			}
			
			if(MazeArray[x + 1][y] == 0) {
				System.out.println("Down");
				SolveMaze(x + 1, y);
			}
			
			if(MazeArray[x - 1][y] == 0 ) {
				System.out.println("Up");
				SolveMaze(x - 1, y);
			}
			
			if(MazeArray[x][y + 1] == 0 ) {
				System.out.println("Right");
				SolveMaze(x, y + 1);
			}
		}
		return false;
	}

	
	public static boolean ReachedEnd(int x, int y) {

		if(x == end.x && y == end.y) { // Check if the end has been reached.
			return true;
		}else {
			return false;
		}
	}

	public static void Print() {
		System.out.println(Arrays.deepToString(MazeArray));
	}

	public static void main(String[] args) {
		ReadFileMakeMaze();
	}
}
