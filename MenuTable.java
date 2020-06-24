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
	JMenu m = new JMenu("인원관리");
	JMenuItem insert = new JMenuItem("등록");
	JMenuItem update = new JMenuItem("수정");
	JMenuItem delete = new JMenuItem("삭제");
	JMenuItem quit = new JMenuItem("종료");
	JMenuBar mb = new JMenuBar();




	String[] name = { "회원번호", "이름", "나이", "주소","입영일","계급","주특기","소대","총기번호"};
	DefaultTableModel dt = new DefaultTableModel(name, 0);
	JTable jt = new JTable(dt);
	JScrollPane jsp = new JScrollPane(jt);
	JPanel p = new JPanel();

	String[] comboName = { " ALL ", " amynum ", " name ", " clas " };



	JComboBox combo = new JComboBox(comboName);
	JTextField jtf = new JTextField(20);
	JButton search = new JButton("검색");
	
	ArmyDAO dao = new ArmyDAO();


	public MenuTable() {
		super("군 정보 조회 시스템");



		jt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jt.addMouseListener(this);
		jt.setRowHeight(24);// 행높이
		jt.setFont(new Font("맑은 고딕", Font.BOLD, 16));// 폰트



		m.add(insert);
		m.add(update);
		m.add(delete);
		m.add(quit);
		m.setForeground(Color.blue);

		m.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		insert.setFont(new Font("맑은 고딕", Font.BOLD,16));
		update.setFont(new Font("맑은 고딕", Font.BOLD,16));
		delete.setFont(new Font("맑은 고딕", Font.BOLD,16));
		quit.setFont(new Font("맑은 고딕", Font.BOLD,16));



		mb.add(m);
		



		setJMenuBar(mb);



		mb.setFont(new Font("맑은 고딕", Font.BOLD, 16));



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




		new ArmyJDailogGUI(this, "수정");
	}

	public static void main(String[] args) {

		new Intropage();

	}

//가입,수정,삭제,검색기능

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == insert) {// 가입 메뉴
			new ArmyJDailogGUI(this, "등록");
		} else if (e.getSource() == update) {// 수정 메뉴
			new ArmyJDailogGUI(this, "수정");
		} else if (e.getSource() == delete) {// 삭제 메뉴
			int row = jt.getSelectedRow();
			Object obj = jt.getValueAt(row, 0);
			if (dao.userDelete(obj.toString()) > 0) {
				ArmyJDailogGUI.messageBox(this, "레코드 삭제되었습니다.");



				dao.userSelectAll(dt);
				if (dt.getRowCount() > 0)
					jt.setRowSelectionInterval(0, 0);
			} else {
				ArmyJDailogGUI.messageBox(this, "데이터가 삭제되지 않았습니다.");
			}
		} else if (e.getSource() == quit) {// 종료 메뉴
			System.exit(0);
		} else if (e.getSource() == search) {// 검색 버튼
			String fieldName = combo.getSelectedItem().toString();
			if (fieldName.trim().equals("ALL")) {// 전체검색
				dao.userSelectAll(dt);
				if (dt.getRowCount() > 0)
					jt.setRowSelectionInterval(0, 0);
			} else {
				if (jtf.getText().trim().equals("")) {
					ArmyJDailogGUI.messageBox(this, "검색단어를 입력해주세요!");
					jtf.requestFocus();
				} else {// 검색어를 입력했을경우
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
