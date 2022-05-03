// Name: Andrew Xu
// Date: 4/29/22
// Purpose: Killer Sodoku Grid Game

import javax.swing.*;
import java.applet.*;
import java.awt.event.*;
import java.awt.*;

public class XuKillerSodoku extends Applet implements ActionListener {
	int[][] board = {
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	};

	int[][] answers = {
			{ 8, 2, 9, 3, 7, 4, 5, 6, 1 },
			{ 3, 6, 7, 1, 8, 5, 4, 2, 9 },
			{ 5, 1, 4, 9, 6, 2, 3, 7, 8 },
			{ 2, 7, 6, 5, 3, 1, 9, 8, 4 },
			{ 1, 3, 8, 6, 4, 9, 2, 5, 7 },
			{ 9, 4, 5, 8, 2, 7, 6, 1, 3 },
			{ 7, 5, 1, 4, 9, 6, 8, 3, 2 },
			{ 6, 9, 3, 2, 1, 8, 7, 4, 5 },
			{ 4, 8, 2, 7, 5, 3, 1, 9, 6 },
	};
	int[][][] boxes = {
			{ { 0, 0 }, { 0, 1 }, { 1, 0 } }, // 13
			{ { 1, 1 }, { 0, 2 }, { 1, 2 }, { 0, 3 }, { 1, 3 }, { 1, 4 } }, // 34
			{ { 0, 4 }, { 0, 5 }, { 1, 5 } }, // 16
			{ { 0, 6 }, { 0, 7 } }, // 11
			{ { 0, 8 }, { 1, 8 } }, // 10
			{ { 1, 6 }, { 2, 6 } }, // 7
			{ { 1, 7 }, { 2, 7 } }, // 9
			{ { 2, 0 }, { 3, 0 } }, // 7
			{ { 2, 1 }, { 2, 2 }, { 2, 3 } }, // 14
			{ { 2, 4 }, { 2, 5 } }, // 8
			{ { 2, 8 }, { 3, 8 } }, // 12
			{ { 4, 0 }, { 4, 1 }, { 3, 1 } }, // 11
			{ { 3, 2 }, { 4, 2 } }, // 14
			{ { 5, 1 }, { 5, 2 } }, // 9
			{ { 3, 3 }, { 3, 4 }, { 3, 5 } }, // 9
			{ { 4, 3 }, { 5, 3 } }, // 14
			{ { 4, 4 }, { 5, 4 } }, // 6
			{ { 3, 6 }, { 3, 7 } }, // 17
			{ { 4, 5 }, { 4, 6 }, { 4, 7 }, { 4, 8 } }, // 23
			{ { 5, 0 }, { 6, 0 }, { 7, 0 }, { 8, 0 } }, // 26
			{ { 5, 5 }, { 6, 5 }, { 6, 6 } }, // 21
			{ { 5, 6 }, { 5, 7 }, { 5, 8 }, { 6, 8 } }, // 12
			{ { 6, 1 }, { 7, 1 } }, // 14
			{ { 8, 1 } }, // 8
			{ { 6, 2 }, { 7, 2 }, { 7, 3 } }, // 6
			{ { 8, 2 }, { 8, 3 } }, // 9
			{ { 6, 3 } }, // 4
			{ { 6, 4 }, { 7, 4 }, { 7, 5 } }, // 18
			{ { 8, 4 }, { 8, 5 }, { 8, 6 } }, // 9
			{ { 7, 6 } }, // 7
			{ { 6, 7 }, { 7, 7 }, { 7, 8 }, { 8, 8 } }, // 18
			{ { 8, 7 } }, // 9
	};

	JLabel level_label, lives_label;
	JButton notes_button;
	int row = 9;
	int col = 9;
	JButton pics[] = new JButton[row * col];
	boolean notes = false;
	int selected = -1;
	int level = 1;
	int lives = 3;

	public void init() {
		resize(350, 500);
		setBackground(new Color(205, 224, 238));

		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		this.setLayout(layout);
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.ipady = 5;

		JLabel title = new JLabel("Killer Sodoku");
		title.setFont(new Font("Arial", Font.BOLD, 35));

		gbc.gridx = 0;
		gbc.gridy = 0;
		add(title, gbc);

		JPanel p1 = new JPanel(new BorderLayout());
		p1.setBackground(new Color(205, 224, 238));
		p1.setPreferredSize(new Dimension(225, 25));

		level_label = new JLabel("Level: " + level);
		level_label.setPreferredSize(new Dimension(50, 25));

		lives_label = new JLabel("Lives: " + lives + "/3");
		lives_label.setPreferredSize(new Dimension(53, 25));

		p1.add(level_label, BorderLayout.WEST);
		p1.add(lives_label, BorderLayout.EAST);

		p1.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(p1, gbc);

		JPanel grid = new JPanel(new GridLayout(9, 9));
		grid.setPreferredSize(new Dimension(270, 270));
		grid.setBackground(new Color(205, 224, 238));
		for (int m = 0; m < pics.length; m++) {
			pics[m] = new JButton(" ");
			pics[m].setPreferredSize(new Dimension(30, 30));

			int edgeMargin = 4;
			int[] margins = getMargins(m, edgeMargin);
			pics[m].setMargin(new Insets(margins[0], margins[1], margins[2], margins[3]));

			pics[m].setBackground(Color.white);
			pics[m].setActionCommand("" + m);
			pics[m].addActionListener(this);
			grid.add(pics[m]);
		}
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(grid, gbc);

		JPanel p2 = new JPanel(new GridLayout(1, 4));
		p2.setBackground(new Color(205, 224, 238));

		JButton erase = new JButton("Erase");
		erase.setPreferredSize(new Dimension(50, 50));
		erase.setMargin(new Insets(0, 0, 0, 0));
		erase.addActionListener(this);
		erase.setActionCommand("erase");

		notes_button = new JButton("Notes");
		notes_button.setPreferredSize(new Dimension(50, 50));
		notes_button.setMargin(new Insets(0, 0, 0, 0));
		notes_button.addActionListener(this);
		notes_button.setActionCommand("notes");

		JButton hint = new JButton("Hint");
		hint.setPreferredSize(new Dimension(50, 50));
		hint.setMargin(new Insets(0, 0, 0, 0));
		hint.addActionListener(this);
		hint.setActionCommand("previous");

		JButton reset = new JButton("Reset");
		reset.setPreferredSize(new Dimension(50, 50));
		reset.setMargin(new Insets(0, 0, 0, 0));
		reset.addActionListener(this);
		reset.setActionCommand("reset");

		p2.add(erase);
		p2.add(notes_button);
		p2.add(hint);
		p2.add(reset);

		gbc.gridx = 0;
		gbc.gridy = 3;
		add(p2, gbc);

		JPanel p3 = new JPanel(new GridLayout(1, 9));
		p3.setBackground(new Color(205, 224, 238));
		p3.setPreferredSize(new Dimension(270, 30));

		JButton[] number_buttons = new JButton[9];
		for (int i = 0; i < 9; i++) {
			number_buttons[i] = new JButton("" + (i + 1));
			number_buttons[i].setPreferredSize(new Dimension(30, 30));
			number_buttons[i].setMargin(new Insets(0, 0, 0, 0));
			number_buttons[i].setBackground(Color.white);
			number_buttons[i].setActionCommand("button" + (i + 1));
			number_buttons[i].addActionListener(this);
			p3.add(number_buttons[i]);
		}
		gbc.gridx = 0;
		gbc.gridy = 4;
		add(p3, gbc);

		JPanel p4 = new JPanel(new BorderLayout());
		p4.setBackground(new Color(205, 224, 238));
		p4.setPreferredSize(new Dimension(300, 50));

		JButton instructions = new JButton("Instructions");
		instructions.setPreferredSize(new Dimension(100, 50));
		instructions.setMargin(new Insets(0, 0, 0, 0));
		instructions.addActionListener(this);
		instructions.setActionCommand("instructions");

		JButton skip = new JButton("Reveal/Skip");
		skip.setPreferredSize(new Dimension(100, 50));
		skip.setMargin(new Insets(0, 0, 0, 0));
		skip.addActionListener(this);
		skip.setActionCommand("skip");

		p4.add(instructions, BorderLayout.WEST);
		p4.add(skip, BorderLayout.EAST);

		gbc.gridx = 0;
		gbc.gridy = 5;
		add(p4, gbc);

	}

	public int[] getMargins(int m, int edgeMargin) {
		int top = 0;
		int left = 0;
		int bottom = 0;
		int right = 0;

		if ((m / row) % 3 == 0)
			top = edgeMargin;

		if ((m % row) % 3 == 0)
			left = edgeMargin;

		if (((m / row) + 1) % 3 == 0)
			bottom = edgeMargin;

		if (((m % row) + 1) % 3 == 0)
			right = edgeMargin;

		int[] ans = { top, left, bottom, right };
		return ans;
	}

	public void draw_boxes() {
		// int[][] shades_of_blue = {
		// { 84, 200,223 },
		// { 159, 211, 222},
		// { , , },
		// { , , },
		// { , , },
		// { , , },
		// { , , },
		// { , , },
		// { , , },
		// { , , },
		// { , , },
		// { , , },
		// { , , },
		// { , , },
		// { , , },
		// };
	};

	public void click_num(char num) {
		int n = Character.getNumericValue(num);

		if (selected != -1) {
			int x = selected / row;
			int y = selected % row;

			if (notes) {

			} else { // Notes are off

				if (check_place(n, x, y)) {
					board[x][y] = n;
				} else {
					lives -= 1;
				}
			}
		}

		redraw();
	}

	public boolean check_place(int n, int x, int y) {
		if (n == answers[x][y]) {
			return true;
		}
		return false;

	}

	// public void copyOver(int a[][], int b[][]) {
	// for (int i = 0; i < row; i++) {
	// for (int j = 0; j < col; j++) {
	// a[i][j] = b[i][j];
	// }
	// }
	// }

	// public void next() {

	// redraw();
	// }

	// public void reset() {

	// redraw();
	// }

	// public boolean win() {

	// }

	public void redraw() {
		int m = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (board[i][j] == 0) {
					pics[m].setText("");
				} else {
					pics[m].setText("" + board[i][j]);
				}
				pics[m].setBackground(Color.white);
				m++;
			}
		}
		if (selected != -1)
			pics[selected].setBackground(new Color(238, 130, 239));

		level_label.setText("Level: " + level);
		lives_label.setText("Lives: " + lives + "/3");

	}

	public void click(int pos) {
		if (selected == pos) { // If a place is selected
			selected = -1;
		} else {
			selected = pos;
		}
		redraw();
	}

	public void actionPerformed(ActionEvent e) {
		String action_command = e.getActionCommand();

		if (action_command.equals("reset")) {
			// reset();
		} else if (action_command.equals("next")) {
			// next();

		} else if (action_command.charAt(0) == 'b') {
			click_num(action_command.charAt(6));
		} else {
			// grid pieces
			int pos = Integer.parseInt(action_command);
			click(pos);
		}

		// if (win()) {
		// // switch array to have new value
		// for (int i = 0; i < row; i++) {
		// for (int j = 0; j < col; j++) {
		// light[i][j] = 1;
		// }
		// }
		// // update screen to show changes
		// redraw();
		// }
	}

	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = XuKillerSodoku.class.getResource(path);
		if (imgURL != null)
			return new ImageIcon(imgURL);
		else
			return null;
	}
}
