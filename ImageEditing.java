import java.awt.*;
import java.awt.image.*;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class ImageEditing {
	private JFrame startFrame;
	private JLabel welcomeText;
	public ImageIcon icon;
    public JButton contrastButton;
    public JButton highlightButton;
    public JButton rotateButton;
    public JButton zoomButton;
    public JButton resetButton;
    public JButton blurButton;
    public JFrame f;
    public JPanel p;



    public ImageEditing() {
		startFrame = new JFrame("Image Example");
		startFrame.setSize(700, 800);
		startFrame.setLayout(new FlowLayout());


	welcomeText = new JLabel("Welcome to the Image Editor", JLabel.CENTER);
       


        imageButton = new JButton("image");
        imageButton.setActionCommand("IMAGE");
        imageButton.addActionListener(new ButtonClickListener());
        startframe.add(imageButton);



        


