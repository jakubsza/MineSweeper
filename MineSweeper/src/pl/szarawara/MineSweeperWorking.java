package pl.szarawara;

public class MineSweeperWorking implements MineSweeper {

	private String mineField;
	int endLine;

	@Override
	public void setMineField(String mineField) throws IllegalArgumentException {
		endLine = 0;
		
		for (int i = 0; i < mineField.length(); i++) {
			if (mineField.charAt(i) == '.' || mineField.charAt(i) == '*')
				endLine++;
			else if (mineField.charAt(i) == '\\' && (i + 1) < mineField.length() && mineField.charAt(i + 1) == 'n')
				break;
			else
				throw new IllegalArgumentException();
		}

		if (mineField.charAt(mineField.length() - 1) == 'n')
			throw new IllegalArgumentException();

		for (int i = endLine + 2; i < mineField.length(); i++) {
			if (mineField.charAt(i) == '\\' && (i + 2) % (endLine + 2) != 0)
				throw new IllegalArgumentException();
			else if (mineField.charAt(i) == 'n' && (i + 1) % (endLine + 2) != 0)
				throw new IllegalArgumentException();
			else if (mineField.charAt(i) != 'n' && (i + 1) % (endLine + 2) == 0)
				throw new IllegalArgumentException();
			else if (mineField.charAt(i) != '\\' && (i + 2) % (endLine + 2) == 0)
				throw new IllegalArgumentException();
			else if (mineField.charAt(i) != '*' && mineField.charAt(i) != '.' && mineField.charAt(i) != '\\'
					&& mineField.charAt(i) != 'n') {
				System.out.println(i + " tutaj?");
				throw new IllegalArgumentException();
			}

		}

		this.mineField = mineField;

	}

	@Override
	public String getHintField() throws IllegalStateException {
		String hintField = "";

		if (mineField == null || mineField.isEmpty())
			throw new IllegalStateException();

		for (int i = 0; i < mineField.length(); i++) {
			int adjacentNumber = 0;

			if (mineField.charAt(i) == 'n')
				hintField += "n";
			else if (mineField.charAt(i) == '\\')
				hintField += "\\";
			else if (mineField.charAt(i) == '*')
				hintField += "*";
			else {
				if (i + endLine + 2 < mineField.length() && mineField.charAt(i + endLine + 2) == '*')
					adjacentNumber++;
				if (i + endLine + 1 < mineField.length() && mineField.charAt(i + endLine + 1) == '*')
					adjacentNumber++;
				if (i + endLine + 3 < mineField.length() && mineField.charAt(i + endLine + 3) == '*')
					adjacentNumber++;
				if (i - endLine - 3 >= 0 && mineField.charAt(i - endLine - 3) == '*')
					adjacentNumber++;
				if (i - endLine - 2 >= 0 && mineField.charAt(i - endLine - 2) == '*')
					adjacentNumber++;
				if (i - endLine - 1 >= 0 && mineField.charAt(i - endLine - 1) == '*')
					adjacentNumber++;
				if (i + 1 < mineField.length() && mineField.charAt(i + 1) == '*')
					adjacentNumber++;
				if (i - 1 >= 0 && mineField.charAt(i - 1) == '*')
					adjacentNumber++;
				hintField += adjacentNumber;
			}

		}

		return hintField;
	}

}
