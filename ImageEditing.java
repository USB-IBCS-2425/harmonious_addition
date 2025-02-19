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
    public JLabel lab;




    public ImageEditing() {
		startFrame = new JFrame("Image Example");
		startFrame.setSize(700, 800);
		startFrame.setLayout(new FlowLayout());


	welcomeText = new JLabel("Welcome to the Image Editor", JLabel.CENTER);
       


        imageButton = new JButton("image");
        imageButton.setActionCommand("IMAGE");
        imageButton.addActionListener(new ButtonClickListener());
        startFrame.add(imageButton);


        resetButton = new JButton("reset");
        resetButton.setActionCommand("RESET");
        resetButton.addActionListener(new ButtonClickListener());
        startFrame.add(resetButton);


        highlightgButton = new JButton("highlight green");
        highlightgButton.setActionCommand("HIGHLIGHTGREEN");
        highlightgButton.addActionListener(new ButtonClickListener());
        startFrame.add(highlightgButton);



        contrastButton = new JButton("contrast");
        contrastButton.setActionCommand("CONTRAST");
        contrastButton.addActionListener(new ButtonClickListener());
        startFrame.add(contrastButton);



        zoomButton = new JButton("zoom");
        zoomButton.setActionCommand("Zoom");
        zoomButton.addActionListener(new ButtonClickListener());
        startFrame.add(zoomButton);



        rotateButton = new JButton("rotate");
        rotateButton.setActionCommand("ROTATE");
        rotateButton.addActionListener(new ButtonClickListener());
        startFrame.add(rotateButton);




        blurButton = new JButton("blur");
        blurButton.setActionCommand("BLUR");
        blurButton.addActionListener(new ButtonClickListener());
        startFrame.add(blurButton);




         icon = new ImageIcon("filter image.jpg");
        try {
        	im = ImageIO.read(new File("filter image.jpg"));

        	 }

        catch(IOException e) {
        	System.out.println("Error reading image: " + e.getMessage());
        }

        f = new JFrame();
		p = new JPanel();
		lab = new JLabel(icon);


		public static void main(String[] args) {
		ImageEditing mWin = new ImageEditing();
	    }


	    private class ButtonClickListener implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();



                 if(command.equals("IMAGE"))  {
				f.add(p);
				p.add(image);
				f.pack();
				f.setVisible(true);


				int width = im.getWidth();
         		int height = im.getHeight();
   
         		for (int i = 0; i < width; i++) {
         			for (int j = 0; j < height; j++) {


         		if(command.equals("RESET"))  {

				int width = im.getWidth();
         		int height = im.getHeight();

         		for (int i = 0; i < width; i++) {
         			for (int j = 0; j < height; j++) {




         		if(command.equals("HIGHLIGHTGREEN"))  {

				int width = im.getWidth();
         		int height = im.getHeight();

         		for (int i = 0; i < width; i++) {
         			for (int j = 0; j < height; j++) {





         		if(command.equals("CONTRAST"))  {

				int width = im.getWidth();
         		int height = im.getHeight();

         		for (int i = 0; i < width; i++) {
         			for (int j = 0; j < height; j++) {





         		if(command.equals("ZOOM"))  {

				int width = im.getWidth();
         		int height = im.getHeight();

         		for (int i = 0; i < width; i++) {
         			for (int j = 0; j < height; j++) {





         		if(command.equals("ROTATE"))  {

				int width = im.getWidth();
         		int height = im.getHeight();

         		for (int i = 0; i < width; i++) {
         			for (int j = 0; j < height; j++) {





         		if(command.equals("BLUR"))  {

				int width = im.getWidth();
         		int height = im.getHeight();

         		for (int i = 0; i < width; i++) {
         			for (int j = 0; j < height; j++) {






























