package swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class MenuTable extends JFrame implements ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	JMenu m = new JMenu("�ο�����");
	JMenuItem insert = new JMenuItem("���");
	JMenuItem update = new JMenuItem("����");
	JMenuItem delete = new JMenuItem("����");
	JMenuItem quit = new JMenuItem("����");
	JMenuBar mb = new JMenuBar();




	String[] name = { "ȸ����ȣ", "�̸�", "����", "�ּ�","�Կ���","���","��Ư��","�Ҵ�","�ѱ��ȣ"};
	DefaultTableModel dt = new DefaultTableModel(name, 0);
	JTable jt = new JTable(dt);
	JScrollPane jsp = new JScrollPane(jt);
	JPanel p = new JPanel();

	String[] comboName = { " ALL ", " amynum ", " name ", " clas " };



	JComboBox combo = new JComboBox(comboName);
	JTextField jtf = new JTextField(20);
	JButton search = new JButton("�˻�");
	
	ArmyDAO dao = new ArmyDAO();


	public MenuTable() {
		super("�� ���� ��ȸ �ý���");



		jt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jt.addMouseListener(this);
		jt.setRowHeight(24);// �����
		jt.setFont(new Font("���� ���", Font.BOLD, 16));// ��Ʈ



		m.add(insert);
		m.add(update);
		m.add(delete);
		m.add(quit);
		m.setForeground(Color.blue);

		m.setFont(new Font("���� ���", Font.BOLD, 16));
		insert.setFont(new Font("���� ���", Font.BOLD,16));
		update.setFont(new Font("���� ���", Font.BOLD,16));
		delete.setFont(new Font("���� ���", Font.BOLD,16));
		quit.setFont(new Font("���� ���", Font.BOLD,16));



		mb.add(m);
		



		setJMenuBar(mb);



		mb.setFont(new Font("���� ���", Font.BOLD, 16));



		p.setBackground(Color.LIGHT_GRAY);
		p.add(combo);
		p.add(jtf);
		p.add(search);

		add(jsp, "Center");
		add(p, "South");

		setSize(800, 400);
		setVisible(true);
		setLocationRelativeTo(null);


		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



		insert.addActionListener(this);
		update.addActionListener(this);
		delete.addActionListener(this);
		quit.addActionListener(this);
		search.addActionListener(this);
		



		dao.userSelectAll(dt);



		if (dt.getRowCount() > 0)
			jt.setRowSelectionInterval(0, 0);
	}

	public void mouseClicked(MouseEvent me) {




		new ArmyJDailogGUI(this, "����");
	}

	public static void main(String[] args) {

		new Intropage();

	}

//����,����,����,�˻����

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == insert) {// ���� �޴�
			new ArmyJDailogGUI(this, "���");
		} else if (e.getSource() == update) {// ���� �޴�
			new ArmyJDailogGUI(this, "����");
		} else if (e.getSource() == delete) {// ���� �޴�
			int row = jt.getSelectedRow();
			Object obj = jt.getValueAt(row, 0);
			if (dao.userDelete(obj.toString()) > 0) {
				ArmyJDailogGUI.messageBox(this, "���ڵ� �����Ǿ����ϴ�.");



				dao.userSelectAll(dt);
				if (dt.getRowCount() > 0)
					jt.setRowSelectionInterval(0, 0);
			} else {
				ArmyJDailogGUI.messageBox(this, "�����Ͱ� �������� �ʾҽ��ϴ�.");
			}
		} else if (e.getSource() == quit) {// ���� �޴�
			System.exit(0);
		} else if (e.getSource() == search) {// �˻� ��ư
			String fieldName = combo.getSelectedItem().toString();
			if (fieldName.trim().equals("ALL")) {// ��ü�˻�
				dao.userSelectAll(dt);
				if (dt.getRowCount() > 0)
					jt.setRowSelectionInterval(0, 0);
			} else {
				if (jtf.getText().trim().equals("")) {
					ArmyJDailogGUI.messageBox(this, "�˻��ܾ �Է����ּ���!");
					jtf.requestFocus();
				} else {// �˻�� �Է��������
					dao.getUserSearch(dt, fieldName, jtf.getText());
					if (dt.getRowCount() > 0)
						jt.setRowSelectionInterval(0, 0);
				}
			}
		}
	}
	@Override

	public void mousePressed(MouseEvent e) {



	}

	@Override

	public void mouseReleased(MouseEvent e) {

// TODO Auto-generated method stub

	}

	@Override

	public void mouseEntered(MouseEvent e) {

// TODO Auto-generated method stub

	}

	@Override

	public void mouseExited(MouseEvent e) {

// TODO Auto-generated method stub

	}

}
