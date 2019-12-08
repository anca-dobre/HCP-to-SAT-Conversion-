import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.BufferedReader;

public class Main {

	public static boolean ok = true;
	public static int numberOfNodes;

	public static void main(String[] args) throws IOException {		
		int[][] matrix = readMatrixFrom("graph.in");

		PrintStream fileStream = null ;
		fileStream = new PrintStream ("bexpr.out");
		System.setOut(fileStream);
		if (ok == false) {
			printError();
			return;
		}

		printCombinations(matrix);
		System.out.printf("&");
		printOrientedGraph(matrix);
		printDistance(matrix);

		printRootNeighbours(matrix);
		printVerify(matrix);
	}

	public static int[][] readMatrixFrom(String filename) throws IOException {
		FileReader fileReader = new FileReader(filename);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line = null;

		numberOfNodes = Integer.parseInt(bufferedReader.readLine());
		
		int[][] matrix = new int[numberOfNodes + 1][numberOfNodes + 1];

		for(int i=0; i<numberOfNodes+1; i++)
			for(int j=0; j<numberOfNodes+1; j++)
				matrix[i][j]= 0;		

		while ((line = bufferedReader.readLine()) != null 
				&& Integer.parseInt(line.split(" ")[0]) != -1) {
			int firstNode = Integer.parseInt(line.split(" ")[0]);
			int secondNode = Integer.parseInt(line.split(" ")[1]);

			matrix[firstNode][secondNode] = 1;
			matrix[secondNode][firstNode] = 1;

			matrix[firstNode][0]++;
			matrix[0][firstNode]++;

			matrix[secondNode][0]++;
			matrix[0][secondNode]++;
		}

		for (int i = 1; i <= numberOfNodes; i++) {
			if (matrix[0][i] < 2) {
				Main.ok = false;
				break;
			}
		}

		bufferedReader.close();
		return matrix;
	}

	public static void printError() {
		System.out.printf("x1-1 & ~x1-1");
	}

	public static void printRootNeighbours(int[][] matrix) {
		for (int i = 1; i <= numberOfNodes; i++) {
			if (matrix[1][i] == 1) {
				System.out.printf("(a1-%d|~x1-%d)&(~a1-%d|x1-%d)&",i,i,i,i);
			} else {
				System.out.printf("~a1-%d&", i);
			}
		}
	}

	public static void printOrientedGraph(int[][] matrix) {
		for (int i = 1; i <= numberOfNodes; i++) {
			for (int j = i+1; j <= numberOfNodes; j++) {
				if (matrix[i][j] == 1) {
					System.out.printf("((x%d-%d|~x%d-%d)&(~x%s-%d|x%d-%d))",i,j,j,i, i, j, j, i);
					if(i != numberOfNodes)
						System.out.printf("&");
				}
			}

		}
	}

	public static void printCombinations(int[][] matrix) {
		for (int i = 1; i <= numberOfNodes; i++) {
			int posibilities = factorial(matrix[i][0])/factorial(matrix[i][0]-2)/2;
			int displays = 0;
			System.out.printf("(");
			for (int j = 1; j <= numberOfNodes; j++) {
				if (matrix[i][j] == 1 ) {
					String firstNode = String.format("x%d-%d",i,j);

					for (int k = j + 1; k <= numberOfNodes; k++) {
						if (matrix[i][k] == 1) {
							System.out.printf("(");
							String secondNode = String.format("x%d-%d",i,k);
							System.out.print(firstNode + "&" + secondNode);

							for (int l = 1; l <= numberOfNodes; l++) {
								if (l != j && l != k && matrix[i][l] == 1) {
									String lastNode = String.format("&~x%d-%d",i,l);
									System.out.printf(lastNode);
								}
							}
							System.out.printf(")");
							displays++;
							if(displays<posibilities)
								System.out.printf("|");
						}
					}
				}
			}
			System.out.printf(")");
			if(i<numberOfNodes)
				System.out.printf("&");
		}
	}

	public static void printDistance(int[][] matrix) {
		for (int i = 2; i <= numberOfNodes; i++) {
			System.out.print("(");
			for (int j = 1; j <= numberOfNodes / 2 + 1; j++) {
				if(j==numberOfNodes/ 2+1)
					System.out.print(String.format("a%d-%d", j,i));
				else
				System.out.print(String.format("a%d-%d|", j,i));
			}

			System.out.print(")&");
			System.out.print("");
		}
	}

	public static void printVerify(int[][] matrix) {
		for(int i = 2; i <= (numberOfNodes/2 +1); i++) {
			for(int j = 2; j <= numberOfNodes; j++) {
				System.out.printf("(");
				System.out.printf("(a%d-%d|~((", i, j);
				
				help(matrix, j, i);
				
				System.out.printf("))&");
				
				System.out.printf("(");
				System.out.printf("~a%d-%d|((", i, j);
				
				help(matrix, j, i);

				System.out.printf(")))");

				
				if(j== numberOfNodes && i == (numberOfNodes/2 +1) )
				System.out.print("");
				else
					System.out.print("&");


			}
		}
	}

	static void help(int[][] matrix, int j, int i) {
		int printed = 0;

		for(int r = 2; r <= numberOfNodes; r++) {
			int posibilities=matrix[0][j];
			if(matrix[1][j]==1) posibilities --;
			if(matrix[j][r] == 1 ) {
				printed++;
				if(printed!=posibilities)
					System.out.printf("(a%d-%d&x%d-%d)|", i-1, r, r, j);
				else
					System.out.printf("(a%d-%d&x%d-%d))&~(", i-1, r, r, j);

			}
		}
		
		for(int p =1; p< i; p++) {
			if(p< i-1)
			System.out.printf("a%d-%d|", p, j);
			else 
				System.out.printf("a%d-%d)", p, j);

		}

	}
	
	static int factorial(int n){    
		if (n == 0)    
			return 1;    
		else    
			return(n * factorial(n-1));    
	}    


}