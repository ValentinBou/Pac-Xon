import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		JFrame obj = new JFrame();
		GamePlay gameplay =new GamePlay();
		obj.setBounds(100, 100, 785, 588);
		obj.setTitle("Breakout Monster");
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(gameplay);
	}
}
