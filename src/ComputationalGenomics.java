import java.util.*;
import java.io.*;

public class ComputationalGenomics {

	private static Scanner input;

	public static void main(String[] args) {

		input = new Scanner(System.in);
		menu();


		/*
		String dnaSeq = "ACGTGCATCATACA";
		System.out.println(Polymerase.getComplement(dnaSeq));

		ArrayList<Integer> list = NaiveExactMatching.find("AC", dnaSeq);
		for(Integer i: list) {
			System.out.println(i);
		}
		

		String dnaSeq2 = "GCTAGCTC";	
		//System.out.println(BoyerMoore.badCharacter("TCAA", dnaSeq2));
		System.out.println(BoyerMoore.badCharacter("tooth", "iamthetoothfairy"));
		System.out.println(BoyerMoore.goodSuffix("ACTA", dnaSeq2));
		System.out.println(BoyerMoore.goodSuffix("AGTAG", dnaSeq2));
		*/

	}

	public static void menu(){		

		int choice = 0;
		do {
			System.out.println("Welcome to the Computational Genomics Program: please select a task or type '-1' to quit:");
			System.out.println("1. Polymerase: This will return the complement DNA strand.");
			System.out.println("2. Exact Matching: This will find all the locations where String p exists in String t.");
			System.out.println("3. Boyer-Moore: (select to see secondary menu)");
			System.out.println("4. K-mer Indexes: (select to see secondary menu)");
			System.out.println("5. Approximate Matching (select to see secondary menu)");
			choice = input.nextInt();

			if (choice == 1) {
				//ACGTGCATCATACA
				System.out.println("Please enter a DNA sequence that you would like to find the complement strand of: ");
				String dnaSeq = input.next();
				System.out.println("The complement strand is: " + Polymerase.getComplement(dnaSeq));
			}
			else if (choice == 2) {
				//AC
				//ACGTGCATCATACA
				System.out.println("Please enter two strings (shorter string first - p then t): ");
				String p = input.next();
				String t = input.next();
				System.out.println("String p exists in String t at indices: ");
				HashSet<Integer> list = NaiveExactMatching.find(p, t);
				for(Integer i: list) {
					System.out.print(i + " ");
				}
				System.out.println();

			}
			else if (choice == 3) {
				int option = 0;
				do {
					System.out.println("Please select a task or type '-1' to quit:");
					System.out.println("1. This will return how much String p should shift in String t according to the bad character rule.");
					System.out.println("2. This will return how much String p should shift in String t according to the good suffix.");

					option = input.nextInt();
					
					if (option == 1) {
						//tooth
						//iamthetoothfairy
						System.out.println("Please enter two strings (shorter string first - p then t): ");
						String p = input.next();
						String t = input.next();
						
						System.out.println(BoyerMoore.badCharacter(p, t));

					}
					else if (option == 2) {
						//ACTA / ACTAG
						//GCTAGCTC
						System.out.println("Please enter two strings (shorter string first - p then t): ");
						String p = input.next();
						String t = input.next();
						
						System.out.println(BoyerMoore.goodSuffix(p, t));

					}
					
				}while (option != -1);	
				
			}
			else if (choice == 4) {
				
				int option = 0;
				do {
					System.out.println("Please select a task or type '-1' to quit:");
					System.out.println("1. This will return the location of String p in String t.");
					System.out.println("2. This will return the location of String p in String t using kmers for every nth offset.");

					option = input.nextInt();
					
					if (option == 1) {
						//GCGTGC / GCGTGA
						//CGTGCGTGCTT
						//5
						System.out.println("Please enter two strings (shorter string first - p then t): ");
						String p = input.next();
						String t = input.next();
						System.out.println("Please enter the length of the k-mer: ");
						int k = input.nextInt();
						
						System.out.println(Kmer.kmer(p, t, k));

					}
					else if (option == 2) {
						//GCGTGCTT
						//CGTGCGTGCTT
						//5
						//2
						System.out.println("Please enter two strings (shorter string first - p then t): ");
						String p = input.next();
						String t = input.next();
						System.out.println("Please enter the length of the k-mer: ");
						int k = input.nextInt();
						System.out.println("Please enter a value for n: ");
						int n = input.nextInt();
						
						System.out.println(Kmer.kmer2(p, t, k, n));

					}
					
				}while (option != -1);	
				
			}
			else if (choice == 5) {
				
				int option = 0;
				do 
				{
					System.out.println("Please select a task or type '-1' to quit:");
					System.out.println("1. This will return the index where String p could possibly be in String t within n edits.");
					System.out.println("2. This will return the edit distance between String x and String y using recursion.");
					System.out.println("3. This will return the edit distance between String x and String y using dynamic programming.");
		
					option = input.nextInt();

					if (option == 1) {
						//AACTTG
						//CACTTAATTTG
						//2 / 1
						System.out.println("Please enter two strings (shorter string first - p then t): ");
						String p = input.next();
						String t = input.next();
						System.out.println("Please enter the maximum number of edits allowed (n) ");
						int n = input.nextInt();
					
						System.out.println("The possible indicese are: ");
						HashSet<Integer> result = ApproximateMatching.approx(p, t, n);
						for(Integer i: result) {
							System.out.print(i + " ");
						}
						System.out.println();
					}
					else if (option == 2) {
						//shake spea
						//Shakespear
						String s = input.nextLine();
						
						System.out.println("Please enter two strings: ");
						String x = input.nextLine();
						String y = input.nextLine();
						
						Date begin = new Date();
						System.out.println(ApproximateMatching.recur(x, y));
						Date end = new Date();
						long execTimeInMillies = end.getTime()-begin.getTime();
						System.out.println("Calcuate recur time: " + execTimeInMillies + " milliseconds");
					}
					else if (option == 3) {
						//shake spea
						//Shakespear
						String s = input.nextLine();

						System.out.println("Please enter two strings: ");
						String x = input.nextLine();
						String y = input.nextLine();
						
						Date begin = new Date();
						System.out.println(ApproximateMatching.dynamic(x, y));
						Date end = new Date();
						long execTimeInMillies = end.getTime()-begin.getTime();
						System.out.println("Calcuate recur time: " + execTimeInMillies + " milliseconds");
					}
					

				}while (option != -1);				
				
			}

		}while (choice != -1);
		System.out.println("Thank You for using my program.");


	}

}




/*


int choice = 0;
		do 
		{
			System.out.println("Welcome to the Computational Genomics Program: please select a task or type '-1' to quit:");
			System.out.println("1. This will return the complement DNA strand.");
			System.out.println("2. ");
			System.out.println("3. ");
			System.out.println("4. ");
			System.out.println("5. ");
			choice = input.nextInt();

			if (choice == 1) {
			}
			else if (choice == 2) {
			}
			else if (choice == 3) {
			}
			else if (choice == 4) {
			}
			else if (choice == 5) {
			}

		}while (choice != -1);
		System.out.println("Thank You for using my program.");



 */




