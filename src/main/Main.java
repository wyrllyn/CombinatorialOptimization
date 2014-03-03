package main;

import utils.Parser;
import algo.Algo;
import algo.BranchAndBound;
import algo.Greedy;
import core.Universe;

public class Main {

	private static String baseFileName;
	private static String entFileName;
	
	private static Algo algo;
	private static String s_algo;
	private static Universe verse;
	private static int n = 1;
	
	public static void main (String [] args) throws Exception {
		try {
			dealWithArgs(args);
			
			verse = Parser.parse(baseFileName, entFileName);
			algo = getAlgo();
			algo.startAlgo(n);
		} catch (Exception e) {
			e.printStackTrace();
			printHelp();
		}
	}

	public static Algo getAlgo() throws Exception {
		if (s_algo.equals("greedy")) {
			return new Greedy(verse);
		} else if (s_algo.equals("bnb")) {
			return new BranchAndBound(verse);
		} else {
			throw new Exception("Unrecognised algorithm: " + s_algo);
		}
	}

	private static void dealWithArgs(String[] args) throws Exception {
		for (int i = 0; i < args.length; i++) {
			try {
				String arg = args[i];
				switch (arg) {
				case "-help":
					printHelp();
					break;
				case "-baseList":
					baseFileName = args[++i];
					break;
				case "-entList":
					entFileName = args[++i];
					break;
				case "-algo":
					s_algo = args[++i];
					break;
				case "-n":
					n = Integer.parseInt(args[++i]);
					break;
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				throw new Exception("Insufficient number of arguments");
			}
		}
	}

	public static void printHelp() {
		System.out.println("Usage:");
		System.out.println("\t-help prints this");
		System.out.println("\t-baseList specify base list file");
		System.out.println("\t-entList specify enterprise list file");
		System.out.println("\t-algo specify algorithm, greedy or bnb");
		System.out.println("\t-n (optional) number of executions for a greedy algorithm");
	}
	
	
}
