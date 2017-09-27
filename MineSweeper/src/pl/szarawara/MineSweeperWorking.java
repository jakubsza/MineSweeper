package pl.szarawara;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import pl.szarawara.helper.Field;
import pl.szarawara.helper.FieldHelper;

public class MineSweeperWorking implements MineSweeper {

	private String mineField;
	private int endLine;
	private List<FieldHelper> fieldHelperMain;

	@Override
	public void setMineField(String mineField) throws IllegalArgumentException {

		endingConditions(mineField);

		endLineConditions(mineField);

		mainConditions(mineField);

		setFieldNeightbours(mineField);

		this.mineField = mineField;

	}

	@Override
	public String getHintField() throws IllegalStateException {
		String hintField = "";
		int index = 0;

		if (mineField == null || mineField.isEmpty())
			throw new IllegalStateException();
		
		for (int i = 0; i < mineField.length(); i++) {

			if (mineField.charAt(i) == 'n')
				hintField += "n";
			else if (mineField.charAt(i) == '\\')
				hintField += "\\";
			else if (mineField.charAt(i) == '*')
				hintField += "*";
			else {
				hintField += fieldHelperMain.get(index).getNeightboursMinesCount();
				index++;
			}

		}

		return hintField;
	}

	private void endingConditions(String mineField) {
		endLine = 0;
		for (int i = 0; i < mineField.length(); i++) {
			if (mineField.charAt(i) == '.' || mineField.charAt(i) == '*')
				endLine++;
			else if (mineField.charAt(i) == '\\' && (i + 1) < mineField.length() && mineField.charAt(i + 1) == 'n')
				break;
			else
				throw new IllegalArgumentException();
		}
	}

	private void endLineConditions(String mineField) {
		if (mineField.charAt(mineField.length() - 1) == 'n')
			throw new IllegalArgumentException();
	}

	private void mainConditions(String mineField) {

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
				throw new IllegalArgumentException();
			}

		}
	}

	private void setFieldNeightbours(String mineField) {
		List<FieldHelper> fieldHelperMain = new ArrayList<FieldHelper>();
		
		HashSet<Integer> fields = new HashSet<Integer>();
		for (int i = 0; i < mineField.length(); i++)
			if (mineField.charAt(i) != 'n' && mineField.charAt(i) != '\\' && mineField.charAt(i) != '*'){
				List<Field> fieldGroup = new ArrayList<Field>();
			fields=checkingFields(mineField,i);
			FieldHelper fieldhelper = new FieldHelper();
			for (int j : fields){
				
				fieldGroup.add(setNeightbours(j, mineField));
				fieldhelper.setNeightbours(fieldGroup);
			}
				fieldHelperMain.add(fieldhelper);
			}
		this.fieldHelperMain=fieldHelperMain;
	}

	private Field setNeightbours(int position, String mineField) {
		Field field = new Field();
		if (mineField.charAt(position) == '*') {
			field.setMine(true);
		} else {
			field.setMine(false);
		}
		return field;
	}
	
	private HashSet<Integer> checkingFields(String mineField, int i){
		HashSet<Integer> fields = new HashSet<Integer>();
				if (i + endLine + 2 < mineField.length())
					fields.add(i + endLine + 2);
				if (i + endLine + 1 < mineField.length())
					fields.add(i + endLine + 1);
				if (i + endLine + 3 < mineField.length())
					fields.add(i + endLine + 3);
				if (i - endLine - 3 >= 0)
					fields.add(i - endLine - 3);
				if (i - endLine - 2 >= 0)
					fields.add(i - endLine - 2);
				if (i - endLine - 1 >= 0)
					fields.add(i - endLine - 1);
				if (i + 1 < mineField.length())
					fields.add(i + 1);
				if (i - 1 >= 0)
					fields.add(i - 1);
		return fields;
	}
}
