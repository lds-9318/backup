package swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ArmyJDailogGUI extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;



	JPanel pw = new JPanel(new GridLayout(9, 1));
	JPanel pc = new JPanel(new GridLayout(9, 1));
	JPanel ps = new JPanel();

	JLabel titleAmynum = new JLabel("����");
	JLabel titleName = new JLabel("�̸�");
	JLabel titleBirth = new JLabel("�������");
	JLabel titleAddr = new JLabel("�ּ�");
	JLabel titleFdate = new JLabel("�Կ���");
	JLabel titleClas = new JLabel("���");
	JLabel titleSpec = new JLabel("��Ư��");
	JLabel titlePosit = new JLabel("�Ҵ�");
	JLabel titleGunnum = new JLabel("�ѱ��ȣ");

	JTextField amynum = new JTextField();
	JTextField name = new JTextField();
	JTextField birth = new JTextField();
	JTextField address = new JTextField();
	JTextField fdate = new JTextField();
	JTextField clas = new JTextField();
	JTextField spec = new JTextField();
	JTextField posit = new JTextField();
	JTextField gunnum = new JTextField();

	JButton confirm;
	JButton reset = new JButton("���");
	JButton remove = new JButton("����");

	MenuTable me;
	
	JPanel idCkP = new JPanel(new BorderLayout());
	JButton idCkBtn = new JButton("����üũ");

	
	
	ArmyDAO dao = new ArmyDAO();

	public ArmyJDailogGUI(MenuTable me, String index) {
		super(me, "����â");
		this.me = me;

		Font font = new Font("���� ���", Font.BOLD, 20);
		Font font2 = new Font("���� ���", Font.PLAIN, 18);
		
		
		
		titleAmynum.setFont(font);
		titleAmynum.setForeground(Color.BLACK);
		titleName.setFont(font);
		titleName.setForeground(Color.BLACK);
		titleBirth.setFont(font);
		titleBirth.setForeground(Color.BLACK);
		titleAddr.setFont(font);
		titleAddr.setForeground(Color.BLACK);
		titleFdate.setFont(font);
		titleFdate.setForeground(Color.BLACK);
		titleClas.setFont(font);
		titleClas.setForeground(Color.BLACK);
		titleSpec.setFont(font);
		titleSpec.setForeground(Color.BLACK);
		titlePosit.setFont(font);
		titlePosit.setForeground(Color.BLACK);
		titleGunnum.setFont(font);
		titleGunnum.setForeground(Color.BLACK);

		amynum.setFont(font2);
		amynum.setBackground(Color.LIGHT_GRAY);

		name.setFont(font2);
		name.setBackground(Color.LIGHT_GRAY);

		birth.setFont(font2);
		birth.setBackground(Color.LIGHT_GRAY);

		address.setFont(font2);
		address.setBackground(Color.LIGHT_GRAY);
		
		fdate.setFont(font2);
		fdate.setBackground(Color.LIGHT_GRAY);
		
		clas.setFont(font2);
		clas.setBackground(Color.LIGHT_GRAY);
		
		spec.setFont(font2);
		spec.setBackground(Color.LIGHT_GRAY);
		
		posit.setFont(font2);
		posit.setBackground(Color.LIGHT_GRAY);
		
		gunnum.setFont(font2);
		gunnum.setBackground(Color.LIGHT_GRAY);
		
		

		if (index.equals("���")) {
			confirm = new JButton(index);

		} else {
			confirm = new JButton("����");



			int row = me.jt.getSelectedRow();// ���õ� ��
			amynum.setText(me.jt.getValueAt(row, 0).toString());
			name.setText(me.jt.getValueAt(row, 1).toString());
			birth.setText(me.jt.getValueAt(row, 2).toString());
			address.setText(me.jt.getValueAt(row, 3).toString());
			fdate.setText(me.jt.getValueAt(row, 4).toString());
			clas.setText(me.jt.getValueAt(row, 5).toString());
			spec.setText(me.jt.getValueAt(row, 6).toString());
			posit.setText(me.jt.getValueAt(row, 7).toString());
			gunnum.setText(me.jt.getValueAt(row, 8).toString());
			


			amynum.setEditable(false);


			idCkBtn.setEnabled(false);
		}
		confirm.setFont(font2);
		remove.setFont(font2);
		reset.setFont(font2);

		pw.add(titleAmynum);
		pw.add(titleName);
		pw.add(titleBirth);
		pw.add(titleAddr);
		pw.add(titleFdate);
		pw.add(titleClas);
		pw.add(titleSpec);
		pw.add(titlePosit);
		pw.add(titleGunnum);
		

		idCkP.add(amynum, "Center");
		idCkP.add(idCkBtn, "East");

//TextField �߰�

		pc.add(idCkP);
		pc.add(name);
		pc.add(birth);
		pc.add(address);
		pc.add(fdate);
		pc.add(clas);
		pc.add(spec);
		pc.add(posit);
		pc.add(gunnum);
		
		ps.add(confirm);// ������ư
		ps.add(remove);// ������ư
		ps.add(reset);// ��ҹ�ư

		add(pw, "West");
		add(pc, "Center");
		add(ps, "South");

		setSize(500, 350);
		setVisible(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		confirm.addActionListener(this); // ���/���� �̺�Ʈ���
		remove.addActionListener(this); // ����
		reset.addActionListener(this); // ��� �̺�Ʈ���
		idCkBtn.addActionListener(this);// �����ߺ�üũ �̺�Ʈ ���

		
		
	}

	

	@Override

	public void actionPerformed(ActionEvent e) {
		String btnLabel = e.getActionCommand();
		if (btnLabel.equals("���")) {
			if (dao.userListInsert(this) > 0) {
				messageBox(this, name.getText() + "��ϵǾ����ϴ�.");
				dispose();// â�ݱ�



				dao.userSelectAll(me.dt);
				if (me.dt.getRowCount() > 0)
					me.jt.setRowSelectionInterval(0, 0);

			} else {
				messageBox(this, "��ϵ��� �ʾҽ��ϴ�.");
			}

		} 
		else if (btnLabel.equals("����"))/*��*/ {
			if (dao.userUpdate(this) > 0) {
				messageBox(this, "�����Ϸ�Ǿ����ϴ�.");
				dispose();
				dao.userSelectAll(me.dt);
				if (me.dt.getRowCount() > 0)
					me.jt.setRowSelectionInterval(0, 0);
			} else {
				messageBox(this, "�������� �ʾҽ��ϴ�.");
			}
		} else if (btnLabel.equals("����")) {
			int row = me.jt.getSelectedRow();
			Object obj = me.jt.getValueAt(row, 0);
			if (dao.userDelete(obj.toString()) > 0) {
				messageBox(this, "�����Ϸ�Ǿ����ϴ�.");
				dispose();
				dao.userSelectAll(me.dt);

				if (me.dt.getRowCount() > 0)
					me.jt.setRowSelectionInterval(0, 0);
			} else {
				messageBox(this, "�������� �ʾҽ��ϴ�.");
			}
		} else if (btnLabel.equals("���")) {
			dispose();
		} else if (btnLabel.equals("����üũ")) {



			if (amynum.getText().trim().equals("")) {
				messageBox(this, "������ �Է����ּ���.");
				amynum.requestFocus();// ��Ŀ���̵�
			} else {
				if (dao.getIdByCheck(amynum.getText())) {
					messageBox(this, amynum.getText() + "�� ��밡���մϴ�.");

				} else { 
					messageBox(this, amynum.getText() + "�� �ߺ��Դϴ�.");
					amynum.setText("");// text�ڽ������
					amynum.requestFocus();// Ŀ������
				}
			}
		}
	}

	public static void messageBox(Object obj, String message) {
		JOptionPane.showMessageDialog((Component) obj, message);
	}
	
}
