package pl.szarawara;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		MineSweeperWorking mineSweeperWorking = new MineSweeperWorking();

		String mineField = sc.nextLine();

		mineSweeperWorking.setMineField(mineField);

		System.out.println(mineSweeperWorking.getHintField());

	}

}
