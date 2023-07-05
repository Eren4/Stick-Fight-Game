package main_package;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.util.Random;

public class FightScreen extends JPanel {
	
	private Random random = new Random();
	
    private int player1Health, player2Health;
    private int player1Super, player2Super;
    
    private JPanel buttonPanel = new JPanel(new GridLayout(4, 1));
	
	private JButton punch, kick, special, transform;
	
	private ImageIcon gunther, pollux, guntherPowerup;
	private JLabel guntherLabel, polluxLabel, gunterPowerupLabel;
	
	private String topString = ""; //this will take the place of the below strings
	
	private String fight = "FIGHT!!!";
	
	private String guntherName = "Günther";
	private String polluxName = "Pollux";
	 
	private String guntherPunch = guntherName + " PUNCHES!";
	private String guntherKick = guntherName + " KICKS!";
	private String guntherSpecial = "SWORD ATTACK!!!";
	private String guntherTransfrom = guntherName + " OPENED RAGE MODE!!!";
	private String guntherDodge = guntherName + " DODGED!";
	private String guntherWin = guntherName + " WINS!!!";
	
	private String polluxPunch = polluxName + " PUNCHES!";
	private String polluxKick = polluxName + " KICKS!";
	private String polluxSpecial = "ENERGY BLAST!!!";
	private String polluxDodge = polluxName + " DODGED!";
	private String polluxWin = polluxName + " WINS!!!";
	
	private String available = "";
	private String transformAvailable = "";
	
	private int missChance;
	private int polluxAttackChance;
	
	private boolean transformMode = false; //default
	
	public FightScreen() {
		
		setLayout(null);
		
		topString = fight;
		
		punch = new JButton("Punch");
		kick = new JButton("Kick");
		special = new JButton("Special");
		transform = new JButton("Transform");
		
		buttonPanel.add(punch);
		buttonPanel.add(kick);
		buttonPanel.add(special);
		buttonPanel.add(transform);
		
		buttonPanel.setBounds(335, 230, 200, 250);
		
		transform.setVisible(false);
		
		add(buttonPanel);
		
		gunther = new ImageIcon(getClass().getClassLoader().getResource("gunther.png"));
		gunther = resizeImageIcon(gunther, 300, 400);
		guntherLabel = new JLabel(gunther);
		
		pollux = new ImageIcon(getClass().getClassLoader().getResource("pollux.png"));
		pollux = resizeImageIcon(pollux, 400, 400);
		polluxLabel = new JLabel(pollux);
		
		guntherPowerup = new ImageIcon(getClass().getClassLoader().getResource("gunther-powerup.png"));
		guntherPowerup = resizeImageIcon(guntherPowerup, 300, 400);
		gunterPowerupLabel = new JLabel(guntherPowerup);
		
		
		player1Health = 200;
		player2Health = 250;
		
		player1Super = 0;
		player2Super = 0;
		
		special.setEnabled(false);
		transform.setEnabled(false);
		
		punch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				missChance = random.nextInt(10) + 1;
				if(transformMode == false) {
				    if(missChance < 4) {
					    topString = polluxDodge;
					    playSound("miss.wav");
					    checkSuper();
						polluxAttack(getDelay("miss.wav") / 1000);
					    repaint();
				    }
				    else {
					    topString = guntherPunch;
					    playSound("punch.wav");
					    player2Health -= 10;
					    player1Super += 10;
					    player2Super += 5;
					    checkSuper();
					    polluxAttack(getDelay("punch.wav") / 1000);
					
					    repaint();
				    }
			    }
				else { // transform mode on
					if(missChance < 4) {
					    topString = polluxDodge;
					    playSound("miss.wav");
					    
					    checkSuper();
					    polluxAttack(getDelay("miss.wav") / 1000);
					    repaint();
				    }
					else {
					    topString = guntherPunch;
					    playSound("punch.wav");
					    player2Health -= 20;
					    player1Super += 30;
					    player2Super += 10;
					    
					    checkSuper();
					    polluxAttack(getDelay("punch.wav") / 1000);
					
					    repaint();
					}
				}
			}
		});
		
		kick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				missChance = random.nextInt(10) + 1;
				if(transformMode == false) {
				    if(missChance < 6) {
					    topString = polluxDodge;
					    playSound("miss.wav");
					    
					    checkSuper();
					    polluxAttack(getDelay("miss.wav") / 1000);
					    repaint();
				    }
				    else {
					    topString = guntherKick;
					    playSound("kick.wav");
					    player2Health -= 20;
					    player1Super += 15;
					    player2Super += 5;
					
					    checkSuper();
					    polluxAttack(getDelay("kick.wav") / 1000);
					    repaint();
					
				    }
				}
				else { //ape mode on
					if(missChance < 4) {
					    topString = polluxDodge;
					    playSound("miss.wav");
					    
					    checkSuper();
					    polluxAttack(getDelay("miss.wav") / 1000);
					    repaint();
					
				    }
					else {
					    topString = guntherKick;
					    playSound("kick.wav");
					    player2Health -= 25;
					    player1Super += 30;
					    player2Super += 10;
					    
					    checkSuper();
					    polluxAttack(getDelay("kick.wav") / 1000);
					
					    repaint();
					}
				}
			}
		});
		
		special.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				missChance = random.nextInt(10) + 1;
				if(transformMode == false) {
				    if(missChance < 3) {
					    topString = polluxDodge;
					    player1Super -= 100;
					
					    playSound("miss.wav");
					    
					    checkSuper();
					    polluxAttack(getDelay("miss.wav") / 1000);
					    repaint();
					
				    }
				    else {
					    topString = guntherSpecial;
					    playSound("sword-slash.wav");
					    player2Health -= 50;
					    player1Super -= 100;
					    
					    checkSuper();
					    polluxAttack(getDelay("sword-slash.wav") / 1000);
					
					    repaint();
				    }
				}
			   else {
				   if(missChance < 3) {
					    topString = polluxDodge;
					    player1Super -= 100;
					
					    playSound("miss.wav");
					    
					    checkSuper();
					    polluxAttack(getDelay("miss.wav") / 1000);
					    repaint();
				   }
				   else {
					topString = guntherSpecial;
					player1Super -= 100;
					playSound("sword-slash.wav");
					player2Health -= 75;
					
					checkSuper();
					polluxAttack(getDelay("sword-slash.wav") / 1000);
					
					repaint();
				}
			   }
			}
		});
		
		transform.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				topString = guntherTransfrom;
				transformMode = true;
				
				guntherName = "Günther (Rage Mode)";
				guntherPunch = guntherName + " PUNCHES!";
				guntherKick = guntherName + " KICKS!";
				guntherWin = guntherName + " WINS!!!";
				guntherDodge = guntherName + " DODGED!";
				
				playSound("transform.wav");
				
				player1Super -= 150;
				player1Health += 150;
				
				transform.setVisible(false);
				
				checkSuper();
				
				polluxAttack(getDelay("transform.wav") / 1000);
				
				repaint();
			}
		});

	}
	
	private void checkHealth () {
		if(player2Health <= 0) {
			buttonPanel.setEnabled(false);
			buttonPanel.setVisible(false);
			topString = guntherWin;
		}
		
		if(player1Health <= 0) {
			buttonPanel.setEnabled(false);
			buttonPanel.setVisible(false);
			topString = polluxWin;
		}
	}
	
	private void checkSuper() {
		if(player1Super >= 100 && player1Super < 150) {
			special.setEnabled(true);
			available = "AVAILABLE";
		}
		else if(player1Super >= 150) {
			if(transformMode == false) {
			    player1Super = 150;
			    transform.setEnabled(true);
			    transform.setVisible(true);
			    transformAvailable = "TRANSFORMATION AVAILABLE!";
			}
		}
		
		else {
			special.setEnabled(false);
			transform.setEnabled(false);
			transformAvailable = "";
			available = "";
		}
	}
	
	private void polluxAttack(long delay) {
		
		if(player2Health <= 0)
			return;
		
	    try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		missChance = random.nextInt(10) + 1;
		polluxAttackChance = random.nextInt(10) + 1;
		if(player2Super >= 100) {
			if(missChance < 3) {
				topString = guntherDodge;
				player2Super -= 100;
				playSound("miss.wav");
				checkSuper();
			}
			else {
			    topString = polluxSpecial;
			    player1Health -= 50;
			    player2Super -= 100;
			    playSound("energy-blast.wav");
			    checkSuper();
			}
		}
		else {
			if(polluxAttackChance < 6) { // punch
				if(missChance < 4) { // miss
				    topString = guntherDodge;
					playSound("miss.wav");
					checkSuper();
				}
				else { // hit
					topString = polluxPunch;
				    player2Super += 10;
				    player1Health -= 10;
				    player1Super += 5;
				    playSound("punch.wav");
				    checkSuper();
				}
			}
			else { //kick
				if(missChance < 6) {
					topString = polluxKick;
				    player2Super += 15;
				    player1Health -= 20;
				    player1Super += 5;
				    playSound("kick.wav");
				    checkSuper();
				}
				else {
					topString = guntherDodge;
					playSound("miss.wav");
					checkSuper();
				}
			}
		}
		buttonPanel.setVisible(true);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		checkHealth();
		
		g.setColor(Color.white);
        g.fillRect(0, 0, 900, 800);
		
		//Images
		if(transformMode == false) 
		    gunther.paintIcon(this, g, 0, 150);
		else {
			guntherPowerup.paintIcon(this, g, 0, 150);
			transformAvailable = "";
		}
		
		pollux.paintIcon(this, g, 525, 150);
		
		//HP
		g.setColor(Color.black);
		g.setFont(new Font("Normal", Font.BOLD, 30));
		
		g.drawString("HP: " + player1Health, 25, 600);
		g.drawString("HP: " + player2Health, 590, 600);
		
		g.drawString(guntherName, 75, 100);
		g.drawString("Pollux", 650, 100);
		
		g.setColor(Color.blue);
		g.setFont(new Font("Normal", Font.BOLD, 30));
		g.drawString("Super: " + player1Super + " " + available, 25, 640);
		g.drawString("Super: " + player2Super, 590, 640);
		
		g.setColor(Color.red);
		g.setFont(new Font("Normal", Font.BOLD, 30));
		g.drawString(transformAvailable, 25, 680);
		
		g.setColor(Color.black);
		g.setFont(new Font("Normal", Font.BOLD, 30));
		g.drawString(topString, 100, 50);
		
	}
	
	static private ImageIcon resizeImageIcon(ImageIcon temp, int width, int height) {
		Image image = temp.getImage();
		Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
		temp = new ImageIcon(scaledImage);
		return temp;
	}
	
	private void playSound(String music) {
		try {
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(getClass().getClassLoader().getResource(music));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInput);
			clip.start();
			clip.loop(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private long getDelay(String sound) {
		long delay = 0;
		try {
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(getClass().getClassLoader().getResource(sound));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInput);
			delay = clip.getMicrosecondLength();
		} catch (Exception e) {
			e.printStackTrace();
		}
		    return delay;
	}

}
