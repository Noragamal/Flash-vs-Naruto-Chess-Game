package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

import exceptions.GameActionException;
import exceptions.OccupiedCellException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;
import model.pieces.heroes.ActivatablePowerHero;
import model.pieces.heroes.Armored;
import model.pieces.heroes.Medic;
import model.pieces.heroes.Ranged;
import model.pieces.heroes.Speedster;
import model.pieces.heroes.Super;
import model.pieces.heroes.Tech;
import model.pieces.sidekicks.SideKick;

public class GameView2 extends JFrame implements ActionListener, MouseListener {

	private Game game;
	private Player player1;
	private Player player2;
	private JProgressBar payload1;
	private JProgressBar payload2;
	private JPanel pnlGame;
	private JPanel loadp1;
	private JPanel loadp2;
	private JPanel mov;
	private int posi;
	private int posj;
	private JButton poweruse;
	private JButton move;
	private JLabel Background;
	// private JLabel CurrentPlayer;
	private JLabel owner1payload;
	private JLabel owner2payload;
	private JLabel DeadCharacter1;
	private JLabel Deadcharacter2;
	private ArrayList<JButton> btnsCells;
	private JButton[][] board;
	private JLabel picture;
	private JLabel picture2;
	private JLabel CurrentPlayer;
	private JComboBox<Piece> DeadCharactersList2;
	private JComboBox<Piece> DeadCharactersList1;
	private JPopupMenu menu1, menu2, menu3, menu4;
	private JMenuItem m1, m2, M1, M2, M3, M4, m3;
	private JButton Up;
	private JButton Down;
	private JButton Left;
	private JButton Right;
	private JButton UpLeft;
	private JButton DownRight;
	private JButton DownLeft;
	private JButton UpRight;
	private JTextArea nowP;
	private Direction d;
	private int a = 200;
	private int b = 200;

	public GameView2() {
		btnsCells = new ArrayList<JButton>();
		this.setTitle("The Flash vs Naruto");
		String fn = JOptionPane.showInputDialog("Enter Player 1");
		String sn = JOptionPane.showInputDialog("Enter Player 2");
		player1 = new Player(fn);
		player2 = new Player(sn);
		game = new Game(player1, player2);

		// this.getContentPane().add(picture);
		// setLayout(null);
		// Background = new JLabel (new ImageIcon(getClass().getResource("The Flash VS
		// Naruto.jpg")));
		// Background.setBackground(Color.getHSBColor(255, 128, 128));
		// Background.setBounds(10, 10, 1366, 768);
		// this.getContentPane().add(Background);

		this.setSize(1366, 768);
		this.setVisible(true);
		this.getContentPane().setBackground(Color.getHSBColor(40, 83, 216));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);

		pnlGame = new JPanel(new GridLayout(7, 6));
		pnlGame.setBounds(270, 40, 800, 550);
		this.getContentPane().add(pnlGame);

		// Background = new JLabel (new ImageIcon(getClass().getResource("The Flash VS
		// Naruto3.jpg")));
		// Background.setBounds(10,10,1366,768);
		// pnlGame.add(Background);

		CurrentPlayer = new JLabel("It's " + game.getCurrentPlayer().getName() + "Turn");
		CurrentPlayer.setBounds(600, 5, 250, 50);
		CurrentPlayer.setForeground(Color.BLACK);
		this.getContentPane().add(CurrentPlayer);

		// endTurn = new JButton("End Turn");
		// endTurn.setBounds(900, 640, 100, 30);
		// endTurn.setForeground(Color.getHSBColor(1, 1, 1000));
		// endTurn.setBackground(Color.getHSBColor(1, 100, 1000));
		// endTurn.addMouseListener(this);
		// this.getContentPane().add(endTurn);

		// newgame = new JButton("New Game");
		// newgame.setBounds(300, 640, 100, 30);
		// newgame.setBackground(Color.getHSBColor(1, 100, 1000));
		// newgame.setForeground(Color.getHSBColor(1, 1, 1000));
		// newgame.addMouseListener(this);
		// this.getContentPane().add(newgame);

		// attack = new JButton("Attack");
		// attack.setForeground(Color.getHSBColor(1, 1, 1000));
		// attack.setBackground(Color.getHSBColor(1, 100, 1000));
		// attack.setBounds(700, 640, 100, 30);
		// attack.addMouseListener(this);
		// this.getContentPane().add(attack);
		//
		// String[] Messages3 = { "Up", "Down", "Left", "Right", "UpLeft", "UpRight",
		// "DownLeft", "DownRight", };
		// DirectionMoveList = new JComboBox(Messages3);
		// DirectionMoveList.setForeground(Color.getHSBColor(1, 1, 1000));
		// DirectionMoveList.setBackground(Color.getHSBColor(1, 100, 1000));
		// DirectionMoveList.setBounds(500, 640, 100, 30);
		// DirectionMoveList.addMouseListener(this);
		// this.getContentPane().add(DirectionMoveList);

		loadp1 = new JPanel();
		loadp1.setBackground(Color.getHSBColor(255, 128, 128));
		loadp1.setBounds(10, 10, 240, 720);
		this.getContentPane().add(loadp1);
		loadp1.setLayout(null);

		loadp2 = new JPanel();
		loadp2.setBackground(Color.getHSBColor(255, 128, 128));
		loadp2.setBounds(1100, 10, 240, 720);
		this.getContentPane().add(loadp2);
		loadp2.setLayout(null);

		// picture = new JLabel (new ImageIcon(getClass().getResource("The
		// flgbash.jpg")));
		// picture.setBounds(10,120,210,400);
		// loadp1.add(picture);

		payload1 = new JProgressBar();
		payload1.setName("player 1's payload");
		payload1.setValue(game.getPlayer1().getPayloadPos());
		payload1.setBounds(10, 120, 210, 400);
		payload1.setBackground(Color.getHSBColor(267, 96, 78));
		payload1.setForeground(Color.BLACK);
		loadp1.add(payload1);

		payload2 = new JProgressBar();
		payload2.setName("player 2's payload");
		payload2.setValue(game.getPlayer2().getPayloadPos());
		payload2.setBounds(20, 120, 210, 400);
		payload2.setBackground(Color.getHSBColor(267, 96, 78));
		payload2.setForeground(Color.BLACK);
		loadp2.add(payload2);

		picture = new JLabel(new ImageIcon(getClass().getResource("The flash.jpg")));
		picture.setBounds(5, 5, 210, 400);
		payload1.add(picture);

		picture2 = new JLabel(new ImageIcon(getClass().getResource("naruto.png")));
		picture2.setBounds(5, 5, 210, 400);
		payload2.add(picture2);

		owner1payload = new JLabel("Player1's Payload");
		owner1payload.setBounds(10, 50, 220, 45);
		owner1payload.setForeground(Color.BLACK);
		loadp1.add(owner1payload);

		owner2payload = new JLabel("Player2's Payload");
		owner2payload.setBounds(10, 50, 220, 45);
		owner2payload.setForeground(Color.BLACK);
		loadp2.add(owner2payload);
		// Icon n = new ImageIcon(getClass().getResource(".jpg"));
		// Icon n1 = new ImageIcon(getClass().getResource("Hanju_Armored.png"));
		board = new JButton[7][6];
		for (int i = 0; i < game.getBoardHeight(); i++) {
			for (int j = 0; j < game.getBoardWidth(); j++) {
				System.out.println("height= " + i);
				System.out.println("width= " + j);
				JButton bb = new JButton(); // created a button
				Piece t = game.getCellAt(i, j).getPiece();
				if (t != null) {
					// bb.setText(t.getName());
					if (t instanceof ActivatablePowerHero) {
						bb.setToolTipText("Name: " + t.getName() + "\nOwner: " + t.getOwner().getName() + '\n'
								+ "Type: " + game.getCellAt(i, j).getPiece().toString() + " Is Power Used: "
								+ ((ActivatablePowerHero) t).isPowerUsed());

						if (t instanceof Medic) {
							// bb.setToolTipText("Name: " + t.getName() + "\nOwner: " +
							// t.getOwner().getName() + '\n'
							// + "Type: " + game.getCellAt(i, j).getPiece().toString());
							if (t.getOwner() == game.getPlayer1())
								bb.setIcon(new ImageIcon(getClass().getResource("Sakura_medic.jpg")));
							else
								bb.setIcon(new ImageIcon(getClass().getResource("killerfrost.jpg")));
						}

						if (t instanceof Super) {
							// bb.setToolTipText("Name: " + t.getName() + "\nOwner: " +
							// t.getOwner().getName() + '\n'
							// + "Type: " + game.getCellAt(i, j).getPiece().toString());
							if (t.getOwner() == game.getPlayer1())
								bb.setIcon(new ImageIcon(getClass().getResource("Super_akamaru.png")));
							else
								bb.setIcon(new ImageIcon(getClass().getResource("supergirl.jpg")));
						}

						if (t instanceof Ranged) {
							// bb.setToolTipText("Name: " + t.getName() + "\nOwner: " +
							// t.getOwner().getName() + '\n'
							// + "Type: " + game.getCellAt(i, j).getPiece().toString());
							if (t.getOwner() == game.getPlayer1())
								bb.setIcon(new ImageIcon(getClass().getResource("shikamaru_ranged.jpg")));
							else
								bb.setIcon(new ImageIcon(getClass().getResource("PlasticMan.jpg")));
						}

						if (t instanceof Tech) {
							// bb.setToolTipText("Name: " + t.getName() + "\nOwner: " +
							// t.getOwner().getName() + '\n'
							// + "Type: " + game.getCellAt(i, j).getPiece().toString());
							if (t.getOwner() == game.getPlayer1())
								bb.setIcon(new ImageIcon(getClass().getResource("kakashi_tech.png")));
							else
								bb.setIcon(new ImageIcon(getClass().getResource("Firestorm.png")));
						}
					}

					else if (t instanceof SideKick) {
						bb.setToolTipText("Name: " + t.getName() + "\nOwner: " + t.getOwner().getName() + '\n'
								+ "Type: " + game.getCellAt(i, j).getPiece().toString());
						if (t.getOwner() == game.getPlayer1())
							bb.setIcon(new ImageIcon(getClass().getResource("White_Zetsu Sidekick.png")));
						else
							bb.setIcon(new ImageIcon(getClass().getResource("vibe.png")));
					}

					else if (t instanceof Armored) {

						bb.setToolTipText("Name: " + t.getName() + "\nOwner: " + t.getOwner().getName() + '\n'
								+ "Type: " + game.getCellAt(i, j).getPiece().toString() + " Is Armored UP: "
								+ ((Armored) t).isArmorUp());
						if (t.getOwner() == game.getPlayer1())
							bb.setIcon(new ImageIcon(getClass().getResource("Hanju_Armored.png")));
						else
							bb.setIcon(new ImageIcon(getClass().getResource("greenarrow.jpg")));

					} else if (t instanceof Speedster) {
						bb.setToolTipText("Name: " + t.getName() + "\nOwner: " + t.getOwner().getName() + '\n'
								+ "Type: " + game.getCellAt(i, j).getPiece().toString());
						if (t.getOwner() == game.getPlayer1())
							bb.setIcon(new ImageIcon(getClass().getResource("Naruto_Speedster1.png")));
						else
							bb.setIcon(new ImageIcon(getClass().getResource("flash.png")));

					}
					//
					// else
					// bb.setToolTipText("Name: " + t.getName() + "\nOwner: " +
					// t.getOwner().getName() + '\n'
					// + "Type: " + game.getCellAt(i, j).getPiece().toString());
				}

				else {
					bb = new JButton();
					bb.setOpaque(false);
					bb.setContentAreaFilled(false);
					bb.setPreferredSize(new Dimension(90, 90));

				}
				bb.addActionListener(this);// to get an O/P this because its the same class
				board[i][j] = bb;
				pnlGame.add(bb);
				btnsCells.add(bb);

			}
		}

		mov = new JPanel();
		mov.setBounds(270, 600, 800, 500);
		mov.setBackground(Color.getHSBColor(255, 128, 128));
		this.getContentPane().add(mov);// ,BorderLayout.SOUTH);

		poweruse = new JButton();
		poweruse.addActionListener(this);
		poweruse.setText("poweruse");
		poweruse.setBounds(300, 640, 100, 30);
		mov.add(poweruse); // ,BorderLayout.WEST)

		move = new JButton();
		move.addActionListener(this);
		move.setText("move");
		mov.add(move, BorderLayout.EAST);

		this.add(mov, BorderLayout.SOUTH);
		Up = new JButton("Up");
		Up.setName("Up");
		Up.addActionListener(this);
		Down = new JButton("Down");
		Down.setName("Down");
		Down.addActionListener(this);
		Left = new JButton("Left");
		Left.setName("Left");
		Left.addActionListener(this);
		Right = new JButton("Right");
		Right.setName("Right");
		Right.addActionListener(this);
		UpLeft = new JButton("UpLeft");
		UpLeft.setName("UpLeft");
		UpLeft.addActionListener(this);
		DownRight = new JButton("DownRight");
		DownRight.setName("DownRight");
		DownRight.addActionListener(this);
		DownLeft = new JButton("DownLeft");
		DownLeft.setName("DownLeft");
		DownLeft.addActionListener(this);
		UpRight = new JButton("UpRight");
		UpRight.setName("UpRight");
		UpRight.addActionListener(this);
		JButton Nothing = new JButton();
		// JButton action=new JButton("action");
		// movement.setEnabled(false);
		// action.setEnabled(false);
		Nothing.setOpaque(false);
		Nothing.setContentAreaFilled(false);
		Nothing.setBorderPainted(false);
		JPanel direction = new JPanel(new GridLayout(3, 3));

		JPanel action1 = new JPanel(new GridLayout(3, 3));
		// action.add(new JLabel("action"));
		action1.setBorder(BorderFactory.createLineBorder(Color.black));
		action1.add(UpLeft, BorderLayout.EAST);
		action1.add(Up, BorderLayout.EAST);
		action1.add(UpRight, BorderLayout.EAST);
		action1.add(Left, BorderLayout.EAST);
		action1.add(Nothing, BorderLayout.EAST);
		action1.add(Right, BorderLayout.WEST);
		action1.add(DownLeft, BorderLayout.EAST);
		action1.add(Down, BorderLayout.SOUTH);
		action1.add(DownRight, BorderLayout.EAST);
		action1.setVisible(true);
		mov.add(direction, BorderLayout.EAST);
		mov.add(action1, BorderLayout.WEST);

		DeadCharacter1 = new JLabel("Player1 DeadCharacters");
		DeadCharacter1.setBounds(20, 530, 150, 80);
		DeadCharacter1.setForeground(Color.BLACK);
		loadp1.add(DeadCharacter1);

		// String[] Messages1 = { "Green Arrow", "BatMan", "Flash", "Wonder Woman",
		// "SuperMan", "Green Lantern" };
		// DeadCharactersList1 = new JComboBox(Messages1);
		// DeadCharactersList1.setBounds(20, 600, 200, 30);
		// DeadCharactersList1.setBackground(Color.getHSBColor(267, 96, 78));
		// DeadCharactersList1.setForeground(Color.BLACK);
		// DeadCharactersList1.addMouseListener(this);
		// loadp1.add(DeadCharactersList1);

		Deadcharacter2 = new JLabel("Player2 DeadCharacters");
		Deadcharacter2.setBounds(20, 530, 150, 80);
		Deadcharacter2.setForeground(Color.BLACK);
		loadp2.add(Deadcharacter2);

		Piece[] list2 = new Piece[15];
		for (int i = 0; i < game.getPlayer2().getDeadCharacters().size(); i++) {
			list2[i] = game.getPlayer2().getDeadCharacters().get(i);

		}

		DeadCharactersList2 = new JComboBox<Piece>(list2);
		DeadCharactersList2.setName("dead2");
		DeadCharactersList2.setBounds(20, 600, 200, 30);
		DeadCharactersList2.setBackground(Color.getHSBColor(267, 96, 78));
		DeadCharactersList2.setForeground(Color.BLACK);
		DeadCharactersList2.addActionListener(this);
		loadp2.add(DeadCharactersList2);

		Piece[] list1 = new Piece[15];
		for (int i = 0; i < game.getPlayer1().getDeadCharacters().size(); i++) {
			list1[i] = game.getPlayer1().getDeadCharacters().get(i);
		}

		DeadCharactersList1 = new JComboBox<Piece>(list1);
		DeadCharactersList1.setName("dead");
		DeadCharactersList1.setBounds(20, 600, 200, 30);
		DeadCharactersList1.setBackground(Color.getHSBColor(267, 96, 78));
		DeadCharactersList1.setForeground(Color.BLACK);
		DeadCharactersList1.addActionListener(this);
		loadp1.add(DeadCharactersList1);
		// String[] Messages2 = { "Hawk Eye", "Vision", "Quick Silver", "Captain
		// America", "IronMan", "Hulk" };
		// DeadCharactersList2 = new JComboBox(Messages2);
		// DeadCharactersList2.setBounds(20, 600, 200, 30);
		// DeadCharactersList2.setBackground(Color.getHSBColor(267, 96, 78));
		// DeadCharactersList2.setForeground(Color.BLACK);
		// DeadCharactersList2.addMouseListener(this);
		// loadp2.add(DeadCharactersList2);

	}

	public void actionPerformed(ActionEvent e) {
		// JButton awldosa= (JButton) e.getSource();
		for (int i = 0; i < game.getBoardHeight(); i++) {
			for (int j = 0; j < game.getBoardWidth(); j++) {
				if (e.getSource() == board[i][j] && game.getCellAt(i, j).getPiece() != null
						&& game.getCellAt(i, j).getPiece().getOwner() == game.getCurrentPlayer()) {
					posi = i;
					posj = j;
				}

			}
		}
		if (e.getSource() == Up) {
			System.out.println("here");
			d = Direction.UP;
		}
		if (e.getSource() == Down) {
			d = Direction.DOWN;
		}
		if (e.getSource() == Left) {
			d = Direction.LEFT;
		}
		if (e.getSource() == Right) {
			d = Direction.RIGHT;
		}
		if (e.getSource() == UpRight) {
			d = Direction.UPRIGHT;
		}
		if (e.getSource() == UpLeft) {
			d = Direction.UPLEFT;
		}
		if (e.getSource() == DownRight) {
			d = Direction.DOWNRIGHT;
		}
		if (e.getSource() == DownLeft) {
			d = Direction.DOWNLEFT;
		}
		if (e.getSource() == move) {

			System.out.println(d);
			try {
				(game.getCellAt(posi, posj).getPiece()).move(d);
			} catch (UnallowedMovementException e1) {
				JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			} catch (OccupiedCellException e1) {
				System.out.println("occcell");
				JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			} catch (WrongTurnException e1) {
				System.out.println("wrongturn");
				JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}

			// catch (GameActionException e1) {
			// JOptionPane.showMessageDialog(null,
			// e1.toString(),
			// "Error",
			// JOptionPane.ERROR_MESSAGE);
			// //e1.printStackTrace();
			// }
			// catch(WrongTurnException e1){
			// JOptionPane.showMessageDialog(null,
			// e1.toString(),
			// "Error",
			// JOptionPane.ERROR_MESSAGE);
			// e1.printStackTrace();
			// }
			update(game, pnlGame);
		} else if (e.getSource() == poweruse) {

			if (game.getCellAt(posi, posj).getPiece() instanceof Super) {
				try {
					((Super) game.getCellAt(posi, posj).getPiece()).usePower(d, null, null);
				} catch (GameActionException e1) {
					System.out.println("super");
					JOptionPane.showMessageDialog(null, e1.toString(), "error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				update(game, pnlGame);
			} else if (game.getCellAt(posi, posj).getPiece() instanceof Ranged) {
				try {
					((Ranged) game.getCellAt(posi, posj).getPiece()).usePower(d, null, null);
				} catch (GameActionException e1) {
					System.out.println("ranged");
					JOptionPane.showMessageDialog(null, e1.toString());
					e1.printStackTrace();
				}
				update(game, pnlGame);
			} else if (game.getCellAt(posi, posj).getPiece() instanceof Medic) {
				if (game.getCurrentPlayer() == game.getPlayer1()) {

					try {
						Piece target = (Piece) DeadCharactersList1.getSelectedItem();
						// System.out.println("woho");
						((Medic) game.getCellAt(posi, posj).getPiece()).usePower(d, target, null);
					} catch (GameActionException e1) {
						System.out.println("medic");
						JOptionPane.showMessageDialog(null, e1.toString());
						e1.printStackTrace();
					}

					update(game, pnlGame);
				} else if (game.getCurrentPlayer() == game.getPlayer2()) {

					try {
						Piece target = (Piece) DeadCharactersList2.getSelectedItem();
						// System.out.println("woho");
						((Medic) game.getCellAt(posi, posj).getPiece()).usePower(d, target, null);
					} catch (GameActionException e1) {

						JOptionPane.showMessageDialog(null, e1.toString());
						e1.printStackTrace();
					}
					update(game, pnlGame);

				}
			} else if (game.getCellAt(posi, posj).getPiece() instanceof Tech) {
				if (a != 200 && b != 200) {
					System.out.println("hack");
					try {
						((ActivatablePowerHero) game.getCellAt(posi, posj).getPiece()).usePower(null,
								game.getCellAt(a, b).getPiece(), null);
					} catch (GameActionException e1) {

						JOptionPane.showMessageDialog(null, e1.toString(), "error", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
					// count=0;
					// update1(game,pnlGame);
				}
			}
		}
	}
	// }

	// }

	// else if(game.getCurrentPlayer()==game.getPlayer2()){
	// if(e.getSource()==DeadCharactersList2){
	// Piece target= (Piece) DeadCharactersList2.getSelectedItem();
	// try {
	// ((Medic) game.getCellAt(posi, posj).getPiece()).usePower(d,target,null);
	// } catch (InvalidPowerUseException | WrongTurnException e1) {
	// e1.printStackTrace();
	// }
	// }
	// update(game,pnlGame);
	// }

	public void update(Game game, JPanel pnlGame) {
		pnlGame.removeAll();
		loadp1.removeAll();
		loadp2.removeAll();
		CurrentPlayer.removeAll();
		CurrentPlayer = new JLabel("It's " + game.getCurrentPlayer().getName() + "Turn");
		CurrentPlayer.setBounds(600, 5, 250, 50);
		CurrentPlayer.setForeground(Color.BLACK);
		this.getContentPane().add(CurrentPlayer);
		// nowP.removeAll();
		// nowP = new JTextArea("It's " + game.getCurrentPlayer().getName() + " Turn");
		// nowP.setEditable(false);
		// Icon pink = new ImageIcon(getClass().getResource("r2.jpg"));
		// Icon blue = new ImageIcon(getClass().getResource("b3.jpg"));
		board = new JButton[7][6];
		for (int i = 0; i < game.getBoardHeight(); i++) {
			for (int j = 0; j < game.getBoardWidth(); j++) {
				JButton bb = new JButton(); // created a button
				Piece t = game.getCellAt(i, j).getPiece();
				if (t != null) {
					// bb.setText(t.getName());
					if (t instanceof ActivatablePowerHero) {
						bb.setToolTipText("Name: " + t.getName() + "\nOwner: " + t.getOwner().getName() + '\n'
								+ "Type: " + game.getCellAt(i, j).getPiece().toString() + " Is Power Used: "
								+ ((ActivatablePowerHero) t).isPowerUsed());

						if (t instanceof Medic) {
							// bb.setToolTipText("Name: " + t.getName() + "\nOwner: " +
							// t.getOwner().getName() + '\n'
							// + "Type: " + game.getCellAt(i, j).getPiece().toString());
							if (t.getOwner() == game.getPlayer1())
								bb.setIcon(new ImageIcon(getClass().getResource("Sakura_medic.jpg")));
							else
								bb.setIcon(new ImageIcon(getClass().getResource("killerfrost.jpg")));
						}

						if (t instanceof Super) {
							// bb.setToolTipText("Name: " + t.getName() + "\nOwner: " +
							// t.getOwner().getName() + '\n'
							// + "Type: " + game.getCellAt(i, j).getPiece().toString());
							if (t.getOwner() == game.getPlayer1())
								bb.setIcon(new ImageIcon(getClass().getResource("Super_akamaru.png")));
							else
								bb.setIcon(new ImageIcon(getClass().getResource("supergirl.jpg")));
						}

						if (t instanceof Ranged) {
							// bb.setToolTipText("Name: " + t.getName() + "\nOwner: " +
							// t.getOwner().getName() + '\n'
							// + "Type: " + game.getCellAt(i, j).getPiece().toString());
							if (t.getOwner() == game.getPlayer1())
								bb.setIcon(new ImageIcon(getClass().getResource("shikamaru_ranged.jpg")));
							else
								bb.setIcon(new ImageIcon(getClass().getResource("PlasticMan.jpg")));
						}

						if (t instanceof Tech) {
							// bb.setToolTipText("Name: " + t.getName() + "\nOwner: " +
							// t.getOwner().getName() + '\n'
							// + "Type: " + game.getCellAt(i, j).getPiece().toString());
							if (t.getOwner() == game.getPlayer1())
								bb.setIcon(new ImageIcon(getClass().getResource("kakashi_tech.png")));
							else
								bb.setIcon(new ImageIcon(getClass().getResource("Firestorm.png")));
						}
					}

					else if (t instanceof SideKick) {
						bb.setToolTipText("Name: " + t.getName() + "\nOwner: " + t.getOwner().getName() + '\n'
								+ "Type: " + game.getCellAt(i, j).getPiece().toString());
						if (t.getOwner() == game.getPlayer1())
							bb.setIcon(new ImageIcon(getClass().getResource("White_Zetsu Sidekick.png")));
						else
							bb.setIcon(new ImageIcon(getClass().getResource("vibe.png")));
					}

					else if (t instanceof Armored) {

						bb.setToolTipText("Name: " + t.getName() + "\nOwner: " + t.getOwner().getName() + '\n'
								+ "Type: " + game.getCellAt(i, j).getPiece().toString() + " Is Armored UP: "
								+ ((Armored) t).isArmorUp());
						if (t.getOwner() == game.getPlayer1())
							bb.setIcon(new ImageIcon(getClass().getResource("Hanju_Armored.png")));
						else
							bb.setIcon(new ImageIcon(getClass().getResource("greenarrow.jpg")));

					} else if (t instanceof Speedster) {
						bb.setToolTipText("Name: " + t.getName() + "\nOwner: " + t.getOwner().getName() + '\n'
								+ "Type: " + game.getCellAt(i, j).getPiece().toString());
						if (t.getOwner() == game.getPlayer1())
							bb.setIcon(new ImageIcon(getClass().getResource("Naruto_Speedster1.png")));
						else
							bb.setIcon(new ImageIcon(getClass().getResource("flash.png")));

					}
					//
					// else
					// bb.setToolTipText("Name: " + t.getName() + "\nOwner: " +
					// t.getOwner().getName() + '\n'
					// + "Type: " + game.getCellAt(i, j).getPiece().toString());
				}

				else {
					bb = new JButton();
					bb.setOpaque(false);
					bb.setContentAreaFilled(false);
					// bb.setRolloverIcon(n1);
					bb.setPreferredSize(new Dimension(90, 90));

				}
				bb.addActionListener(this);// to get an O/P this because its the same class
				pnlGame.add(bb);
				btnsCells.add(bb);
				board[i][j] = bb;
			}
		}

		payload1 = new JProgressBar();
		payload1.setName("player 1's payload");
		payload1.setValue(game.getPlayer1().getPayloadPos());
		payload1.setBounds(10, 120, 210, 400);
		payload1.setBackground(Color.getHSBColor(267, 96, 78));
		payload1.setForeground(Color.BLACK);
		loadp1.add(payload1);

		payload2 = new JProgressBar();
		payload2.setName("player 2's payload");
		payload2.setValue(game.getPlayer2().getPayloadPos());
		payload2.setBounds(20, 120, 210, 400);
		payload2.setBackground(Color.getHSBColor(267, 96, 78));
		payload2.setForeground(Color.BLACK);
		loadp2.add(payload2);

		picture = new JLabel(new ImageIcon(getClass().getResource("The flash.jpg")));
		picture.setBounds(5, 5, 210, 400);
		payload1.add(picture);

		picture2 = new JLabel(new ImageIcon(getClass().getResource("naruto.png")));
		picture2.setBounds(5, 5, 210, 400);
		payload2.add(picture2);

		owner1payload = new JLabel("Player1's Payload");
		owner1payload.setBounds(10, 50, 220, 45);
		owner1payload.setForeground(Color.BLACK);
		loadp1.add(owner1payload);

		owner2payload = new JLabel("Player2's Payload");
		owner2payload.setBounds(10, 50, 220, 45);
		owner2payload.setForeground(Color.BLACK);
		loadp2.add(owner2payload);

		loadp1 = new JPanel(new GridLayout(3, 1));
		this.add(loadp1, BorderLayout.WEST);
		payload1 = new JProgressBar(JProgressBar.VERTICAL);
		payload1.setName("player'1 payload");
		payload1.setValue(game.getPlayer1().getPayloadPos());
		payload1.setBackground(Color.BLUE);
		payload1.setForeground(Color.BLACK);
		loadp1.add(payload1);
		JTextArea p1name = new JTextArea(game.getPlayer1().getName() + "payload");
		p1name.setEditable(false);
		loadp1.add(p1name);

		loadp2 = new JPanel(new GridLayout(3, 1));
		payload2 = new JProgressBar(JProgressBar.VERTICAL);
		this.add(loadp2, BorderLayout.EAST);
		payload2.setName("player'2 payload");
		payload2.setValue(game.getPlayer2().getPayloadPos());
		payload2.setBackground(Color.BLUE);
		payload2.setForeground(Color.BLACK);
		loadp2.add(payload2);
		JTextArea p2name = new JTextArea(
				game.getPlayer2().getName() + " payload= " + game.getPlayer2().getPayloadPos());
		p2name.setEditable(false);
		loadp2.add(p2name);

		Piece[] list2 = new Piece[15];
		for (int i = 0; i < game.getPlayer2().getDeadCharacters().size(); i++) {
			list2[i] = game.getPlayer2().getDeadCharacters().get(i);
		}

		DeadCharactersList2 = new JComboBox<Piece>(list2);
		DeadCharactersList2.setBounds(20, 600, 200, 30);
		DeadCharactersList2.setBackground(Color.getHSBColor(267, 96, 78));
		DeadCharactersList2.setForeground(Color.BLACK);
		DeadCharactersList2.addActionListener(this);
		loadp2.add(DeadCharactersList2);

		Piece[] list1 = new Piece[15];
		for (int i = 0; i < game.getPlayer1().getDeadCharacters().size(); i++) {
			list1[i] = game.getPlayer1().getDeadCharacters().get(i);
		}
		DeadCharactersList1 = new JComboBox<Piece>(list1);
		DeadCharactersList1.setBounds(20, 600, 200, 30);
		DeadCharactersList1.setBackground(Color.getHSBColor(267, 96, 78));
		DeadCharactersList1.setForeground(Color.BLACK);
		DeadCharactersList1.addActionListener(this);
		loadp1.add(DeadCharactersList1);

		if (game.checkWinner()) {
			JOptionPane.showMessageDialog(null, game.getCurrentPlayer().getName() + " Won");
			System.exit(0);
		}
	}

	// balabizo.setText(eLAction.getName());

	public static void main(String[] args) {
		new GameView2();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
