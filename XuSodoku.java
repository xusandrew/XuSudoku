// Name: Andrew Xu
// Date: 4/29/22
// Purpose: Sodoku Grid Game

import javax.swing.*;
import java.applet.*;
import java.awt.event.*;
import java.awt.*;

public class XuSodoku extends Applet implements ActionListener {
	Panel p_card;
	Panel card1, card2, card3, card4, card5; // the two screens
	CardLayout cdLayout = new CardLayout();

	JLabel level_label, lives_label;
	int row = 9;
	int col = 9;
	JButton pics[] = new JButton[row * col];
	int selected = -1;
	int level = 0;
	int lives = 3;

	int[][] board = {
			{ 0, 3, 1, 6, 0, 7, 0, 8, 0 },
			{ 6, 0, 0, 8, 0, 0, 2, 5, 7 },
			{ 8, 2, 0, 0, 9, 4, 6, 0, 3 },
			{ 4, 0, 0, 0, 0, 0, 8, 3, 2 },
			{ 0, 1, 0, 0, 6, 9, 0, 0, 0 },
			{ 7, 0, 3, 2, 4, 0, 0, 0, 6 },
			{ 9, 0, 2, 4, 0, 1, 0, 7, 8 },
			{ 0, 8, 5, 0, 0, 0, 0, 0, 9 },
			{ 3, 0, 4, 0, 8, 0, 0, 6, 1 },
	};

	int[][][] puzzles = {
			{
					{ 0, 3, 1, 6, 0, 7, 0, 8, 0 },
					{ 6, 0, 0, 8, 0, 0, 2, 5, 7 },
					{ 8, 2, 0, 0, 9, 4, 6, 0, 3 },
					{ 4, 0, 0, 0, 0, 0, 8, 3, 2 },
					{ 0, 1, 0, 0, 6, 9, 0, 0, 0 },
					{ 7, 0, 3, 2, 4, 0, 0, 0, 6 },
					{ 9, 0, 2, 4, 0, 1, 0, 7, 8 },
					{ 0, 8, 5, 0, 0, 0, 0, 0, 9 },
					{ 3, 0, 4, 0, 8, 0, 0, 6, 1 },
			},
			{
					{ 0, 4, 0, 7, 2, 0, 1, 0, 0 },
					{ 0, 3, 0, 9, 0, 6, 0, 2, 0 },
					{ 6, 0, 2, 0, 3, 0, 8, 9, 0 },
					{ 0, 0, 0, 2, 0, 9, 0, 3, 0 },
					{ 0, 0, 3, 5, 0, 0, 0, 0, 0 },
					{ 9, 2, 8, 0, 7, 0, 6, 0, 1 },
					{ 0, 0, 0, 0, 0, 0, 3, 0, 2 },
					{ 0, 9, 0, 8, 0, 0, 4, 0, 0 },
					{ 0, 0, 4, 0, 5, 0, 0, 0, 0 },
			}
	};

	int[][][] answers = {
			{
					{ 5, 3, 1, 6, 2, 7, 9, 8, 4 },
					{ 6, 4, 9, 8, 1, 3, 2, 5, 7 },
					{ 8, 2, 7, 5, 9, 4, 6, 1, 3 },
					{ 4, 9, 6, 1, 7, 5, 8, 3, 2 },
					{ 2, 1, 8, 3, 6, 9, 7, 4, 5 },
					{ 7, 5, 3, 2, 4, 8, 1, 9, 6 },
					{ 9, 6, 2, 4, 5, 1, 3, 7, 8 },
					{ 1, 8, 5, 7, 3, 6, 4, 2, 9 },
					{ 3, 7, 4, 9, 8, 2, 5, 6, 1 }
			},
			{
					{ 5, 4, 9, 7, 2, 8, 1, 6, 3 },
					{ 8, 3, 1, 9, 4, 6, 5, 2, 7 },
					{ 6, 7, 2, 1, 3, 5, 8, 9, 4 },
					{ 4, 1, 5, 2, 6, 9, 7, 3, 8 },
					{ 7, 6, 3, 5, 8, 1, 2, 4, 9 },
					{ 9, 2, 8, 4, 7, 3, 6, 5, 1 },
					{ 1, 5, 7, 6, 9, 4, 3, 8, 2 },
					{ 3, 9, 6, 8, 1, 2, 4, 7, 5 },
					{ 2, 8, 4, 3, 5, 7, 9, 1, 6 },
			}

	};

	public void init() {
		// Initialize all screens
		p_card = new Panel();
		p_card.setLayout(cdLayout);
		screen1();
		screen2();
		screen3();
		resize(350, 500);
		setLayout(new BorderLayout());
		add("Center", p_card);
	}

	public void screen1() {
		card1 = new Panel();
		// card1.setBackground(new Color(71, 137, 102));
		// Add background img
		JButton bkg = new JButton(createImageIcon("background.png"));
		bkg.setPreferredSize(new Dimension(350, 500));
		bkg.setActionCommand("s2");
		bkg.addActionListener(this);
		card1.add(bkg);
		p_card.add("1", card1);
	}

	public void screen2() {
		card2 = new Panel();
		// card2.setBackground(new Color(71, 137, 102));
		// Add background img
		JButton bkg = new JButton(createImageIcon("instructions.png"));
		bkg.setPreferredSize(new Dimension(350, 500));
		bkg.setActionCommand("s3");
		bkg.addActionListener(this);
		card2.add(bkg);
		p_card.add("2", card2);
	}

	public void screen3() {
		card3 = new Panel();
		card3.setBackground(new Color(205, 224, 238));

		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		this.setLayout(layout);
		gbc.ipady = 100;
		gbc.fill = GridBagConstraints.VERTICAL;

		JLabel title = new JLabel("Sodoku");
		title.setFont(new Font("Arial", Font.BOLD, 35));

		JPanel p1 = new JPanel(new BorderLayout());
		p1.setBackground(new Color(205, 224, 238));
		p1.setPreferredSize(new Dimension(225, 25));

		level_label = new JLabel("Level: " + (level + 1));
		level_label.setPreferredSize(new Dimension(50, 25));

		lives_label = new JLabel("Lives: " + lives + "/3");
		lives_label.setPreferredSize(new Dimension(53, 25));

		p1.add(level_label, BorderLayout.WEST);
		p1.add(lives_label, BorderLayout.EAST);

		p1.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		JPanel grid = get_grid();

		JPanel p2 = new JPanel(new GridLayout(1, 4));
		p2.setBackground(new Color(205, 224, 238));

		JButton erase = new JButton(createImageIcon("erase.png"));
		erase.setPreferredSize(new Dimension(50, 50));
		erase.setBackground(Color.white);
		erase.setMargin(new Insets(0, 0, 0, 0));
		erase.addActionListener(this);
		erase.setActionCommand("erase");

		JButton hint = new JButton(createImageIcon("hint.png"));
		hint.setPreferredSize(new Dimension(50, 50));
		hint.setBackground(Color.white);
		hint.setMargin(new Insets(0, 0, 0, 0));
		hint.addActionListener(this);
		hint.setActionCommand("hint");

		JButton reset = new JButton(createImageIcon("reset.png"));
		reset.setPreferredSize(new Dimension(50, 50));
		reset.setBackground(Color.white);
		reset.setMargin(new Insets(0, 0, 0, 0));
		reset.addActionListener(this);
		reset.setActionCommand("reset");

		p2.add(erase);
		p2.add(hint);
		p2.add(reset);

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

		JPanel p4 = new JPanel(new BorderLayout());
		p4.setBackground(new Color(205, 224, 238));
		p4.setPreferredSize(new Dimension(300, 50));

		JButton instructions = new JButton(createImageIcon("instructions.png"));
		instructions.setPreferredSize(new Dimension(100, 50));
		instructions.setBackground(Color.white);
		instructions.setMargin(new Insets(0, 0, 0, 0));
		instructions.addActionListener(this);
		instructions.setActionCommand("instructions");

		JButton skip = new JButton(createImageIcon("next.png"));
		skip.setPreferredSize(new Dimension(100, 50));
		skip.setBackground(Color.white);
		skip.setMargin(new Insets(0, 0, 0, 0));
		skip.addActionListener(this);
		skip.setActionCommand("next");

		p4.add(instructions, BorderLayout.WEST);
		p4.add(skip, BorderLayout.EAST);

		gbc.gridx = 0;
		gbc.gridy = 0;
		card3.add(title, gbc);

		gbc.gridy = 1;
		card3.add(p1, gbc);

		gbc.gridy = 2;
		card3.add(grid, gbc);

		gbc.gridy = 3;
		card3.add(get_space(), gbc);

		gbc.gridy = 4;
		card3.add(p2, gbc);

		gbc.gridy = 5;
		card3.add(get_space(), gbc);

		gbc.gridy = 6;
		card3.add(p3, gbc);

		gbc.gridy = 7;
		card3.add(get_space(), gbc);

		gbc.gridy = 8;
		card3.add(p4, gbc);

		p_card.add("3", card3);
	}

	public JPanel get_grid() {
		JPanel grid = new JPanel(new GridBagLayout());
		grid.setBackground(new Color(25, 140, 207));

		GridBagConstraints gbc_grid = new GridBagConstraints();
		grid.setPreferredSize(new Dimension(235, 250));
		gbc_grid.fill = GridBagConstraints.BOTH;
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				gbc_grid.gridx = j;
				gbc_grid.gridy = i;

				if (i == 3 || i == 7) {
					JButton space_label1 = new JButton("");
					space_label1.setMargin(new Insets(0, 0, 0, 0));
					space_label1.setBorderPainted(false);
					space_label1.setBackground(new Color(25, 140, 207));

					if (i == 2 || i == 5)
						space_label1.setPreferredSize(new Dimension(5, 5));
					else
						space_label1.setPreferredSize(new Dimension(25, 5));

					grid.add(space_label1, gbc_grid);

				} else {
					if (j == 3 || j == 7) {
						JButton space_label2 = new JButton("");
						space_label2.setMargin(new Insets(0, 0, 0, 0));
						space_label2.setPreferredSize(new Dimension(5, 25));
						space_label2.setBorderPainted(false);
						space_label2.setBackground(new Color(25, 140, 207));
						grid.add(space_label2, gbc_grid);

					} else {
						int r_shift = (i + 1) / 4;
						int c_shift = (j + 1) / 4;

						int r = i - r_shift;
						int c = j - c_shift;

						int m = r * 9 + c;

						int a = m / 9;
						int b = m % 9;

						if (board[a][b] == 0)
							pics[m] = new JButton("   ");
						else {
							pics[m] = new JButton(" " + board[a][b] + " ");
						}

						pics[m].setSize(new Dimension(25, 25));
						pics[m].setMargin(new Insets(0, 0, 0, 0));
						pics[m].setBackground(Color.white);
						pics[m].setFont(new Font("Arial", Font.BOLD, 16));
						pics[m].setActionCommand("" + m);
						pics[m].addActionListener(this);
						grid.add(pics[m], gbc_grid);
					}
				}
			}
		}

		return grid;

	}

	public JButton get_space() {
		JButton space = new JButton("");
		space.setPreferredSize(new Dimension(1, 40));
		space.setBackground(new Color(205, 224, 238));
		space.setBorderPainted(false);

		return space;
	}

	public void click_num(char num) {
		int n = Character.getNumericValue(num);

		if (selected != -1) {
			int x = selected / row;
			int y = selected % row;

			board[x][y] = n;

			if (is_incorrect(n, x, y)) {
				lives -= 1;
			}
		}

		redraw();
	}

	public boolean check_place(int n, int x, int y) {
		if (n == answers[level][x][y]) {
			return true;
		}
		return false;
	}

	public int[][] get_level() {
		int[][] ans = new int[9][9];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				ans[i][j] = puzzles[level][i][j];
			}
		}
		return ans;
	}

	public void next_level() {
		level += 1;
		board = get_level();
		redraw();
	}

	public void erase(String num) {
		if (selected != -1) {
			int x = selected / row;
			int y = selected % row;

			board[x][y] = 0;
			redraw();
		}

	}

	public void hint() {
		int x = (int) (Math.random() * 9);
		int y = (int) (Math.random() * 9);

		while (board[x][y] != 0) {
			x = (int) (Math.random() * 9);
			y = (int) (Math.random() * 9);
		}

		int num = answers[level][x][y];

		board[x][y] = num;
		redraw();
	}

	public boolean is_incorrect(int value, int x, int y) {
		if (value == 0)
			return false;
		if (value == answers[level][x][y]) {
			return false;
		}
		return true;
	}

	public void reset() {
		board = get_level();

		selected = -1;
		lives = 3;
		redraw();
	}

	public boolean win() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (board[i][j] == 0)
					return false;
				if (is_incorrect(board[i][j], i, j))
					return false;
			}
		}
		return true;
	}

	public void game_won() {
		selected = -1;
		JOptionPane.showMessageDialog(null, "Completed Sodoku!","Completed",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public boolean lost() {
		if (lives <= 0)
			return true;
		return false;
	}

	public void game_lost() {
		selected = -1;
		JOptionPane.showMessageDialog(null, "Ran out of lives!","Not Completed",
				JOptionPane.INFORMATION_MESSAGE);
		reset();
	}

	public void redraw() {
		int m = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (board[i][j] == 0) {
					pics[m].setText("");
				} else {
					pics[m].setText("" + board[i][j]);
				}

				if (is_incorrect(board[i][j], i, j)) {
					pics[m].setBackground(new Color(255, 0, 0));
				} else if ((int) (selected / row) == i && (int) (selected % row) == j) {
					pics[selected].setBackground(new Color(238, 130, 239));
				} else
					pics[m].setBackground(Color.white);
				m++;
			}
		}

		level_label.setText("Level: " + (level + 1));
		lives_label.setText("Lives: " + lives + "/3");

	}

	public void click(int pos) {
		int x = pos / 9;
		int y = pos % 9;

		if (selected == pos || board[x][y] != 0) { // If a place is selected
			selected = -1;
		} else {
			selected = pos;
		}
		redraw();
	}

	public void actionPerformed(ActionEvent e) {
		String action_command = e.getActionCommand();

		if (e.getActionCommand().equals("s1"))
			cdLayout.show(p_card, "1");
		else if (e.getActionCommand().equals("s2"))
			cdLayout.show(p_card, "2");
		else if (e.getActionCommand().equals("s3"))
			cdLayout.show(p_card, "3");
		else if (action_command.equals("reset"))
			reset();
		else if (action_command.equals("next"))
			next_level();
		else if (action_command.equals("erase"))
			erase(action_command);
		else if (action_command.equals("hint"))
			hint();
		else if (action_command.charAt(0) == 'b')
			click_num(action_command.charAt(6));
		else {
			// grid pieces
			int pos = Integer.parseInt(action_command);
			click(pos);
		}

		if (win()) 
			game_won();
		else if (lost())
			game_lost();

	}

	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = XuSodoku.class.getResource(path);
		if (imgURL != null)
			return new ImageIcon(imgURL);
		else
			return null;
	}
}
