import javax.swing.JOptionPane;

public class MonthLegal
{
	
	public static void main(String[] args)
	{
		String M = JOptionPane.showInputDialog("Input Number Date of Month");
		double m = Double.parseDouble (M);
		if (m < 1)
		JOptionPane.showMessageDialog(null, "This is not a real month, you are too low");
		else if (m == 1)
		JOptionPane.showMessageDialog(null, "It is January");
		else if (m == 2)
		JOptionPane.showMessageDialog(null, "It is February");
		else if (m == 3)
		JOptionPane.showMessageDialog(null, "It is March");
		else if (m == 4)
		JOptionPane.showMessageDialog(null, "It is April");
		else if (m == 5)
		JOptionPane.showMessageDialog(null, "It is May");
		else if (m <= 6)
		JOptionPane.showMessageDialog(null, "It is June");
		else if (m <= 7)
		JOptionPane.showMessageDialog(null, "It is July");
		else if (m <= 8)
		JOptionPane.showMessageDialog(null, "It is August");
		else if (m <= 9)
		JOptionPane.showMessageDialog(null, "It is September");
		else if (m <= 10)
		JOptionPane.showMessageDialog(null, "It is October");
		else if (m <= 11)
		JOptionPane.showMessageDialog(null, "It is November");
		else if (m <= 12)
		JOptionPane.showMessageDialog(null, "It is December");
		else 
		JOptionPane.showMessageDialog(null, "This is not a real month, you are too high");
		System.exit(0);
		}
}