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

	JLabel titleAmynum = new JLabel("군번");
	JLabel titleName = new JLabel("이름");
	JLabel titleBirth = new JLabel("생년월일");
	JLabel titleAddr = new JLabel("주소");
	JLabel titleFdate = new JLabel("입영일");
	JLabel titleClas = new JLabel("계급");
	JLabel titleSpec = new JLabel("주특기");
	JLabel titlePosit = new JLabel("소대");
	JLabel titleGunnum = new JLabel("총기번호");

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
	JButton reset = new JButton("취소");
	JButton remove = new JButton("삭제");

	MenuTable me;
	
	JPanel idCkP = new JPanel(new BorderLayout());
	JButton idCkBtn = new JButton("군번체크");

	
	
	ArmyDAO dao = new ArmyDAO();

	public ArmyJDailogGUI(MenuTable me, String index) {
		super(me, "정보창");
		this.me = me;

		Font font = new Font("맑은 고딕", Font.BOLD, 20);
		Font font2 = new Font("맑은 고딕", Font.PLAIN, 18);
		
		
		
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
		
		

		if (index.equals("등록")) {
			confirm = new JButton(index);

		} else {
			confirm = new JButton("수정");



			int row = me.jt.getSelectedRow();// 선택된 행
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

//TextField 추가

		pc.add(idCkP);
		pc.add(name);
		pc.add(birth);
		pc.add(address);
		pc.add(fdate);
		pc.add(clas);
		pc.add(spec);
		pc.add(posit);
		pc.add(gunnum);
		
		ps.add(confirm);// 수정버튼
		ps.add(remove);// 삭제버튼
		ps.add(reset);// 취소버튼

		add(pw, "West");
		add(pc, "Center");
		add(ps, "South");

		setSize(500, 350);
		setVisible(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		confirm.addActionListener(this); // 등록/수정 이벤트등록
		remove.addActionListener(this); // 삭제
		reset.addActionListener(this); // 취소 이벤트등록
		idCkBtn.addActionListener(this);// 군번중복체크 이벤트 등록

		
		
	}

	

	@Override

	public void actionPerformed(ActionEvent e) {
		String btnLabel = e.getActionCommand();
		if (btnLabel.equals("등록")) {
			if (dao.userListInsert(this) > 0) {
				messageBox(this, name.getText() + "등록되었습니다.");
				dispose();// 창닫기



				dao.userSelectAll(me.dt);
				if (me.dt.getRowCount() > 0)
					me.jt.setRowSelectionInterval(0, 0);

			} else {
				messageBox(this, "등록되지 않았습니다.");
			}

		} 
		else if (btnLabel.equals("수정"))/*참*/ {
			if (dao.userUpdate(this) > 0) {
				messageBox(this, "수정완료되었습니다.");
				dispose();
				dao.userSelectAll(me.dt);
				if (me.dt.getRowCount() > 0)
					me.jt.setRowSelectionInterval(0, 0);
			} else {
				messageBox(this, "수정되지 않았습니다.");
			}
		} else if (btnLabel.equals("삭제")) {
			int row = me.jt.getSelectedRow();
			Object obj = me.jt.getValueAt(row, 0);
			if (dao.userDelete(obj.toString()) > 0) {
				messageBox(this, "삭제완료되었습니다.");
				dispose();
				dao.userSelectAll(me.dt);

				if (me.dt.getRowCount() > 0)
					me.jt.setRowSelectionInterval(0, 0);
			} else {
				messageBox(this, "수정되지 않았습니다.");
			}
		} else if (btnLabel.equals("취소")) {
			dispose();
		} else if (btnLabel.equals("군번체크")) {



			if (amynum.getText().trim().equals("")) {
				messageBox(this, "군번을 입력해주세요.");
				amynum.requestFocus();// 포커스이동
			} else {
				if (dao.getIdByCheck(amynum.getText())) {
					messageBox(this, amynum.getText() + "는 사용가능합니다.");

				} else { 
					messageBox(this, amynum.getText() + "는 중복입니다.");
					amynum.setText("");// text박스지우기
					amynum.requestFocus();// 커서놓기
				}
			}
		}
	}

	public static void messageBox(Object obj, String message) {
		JOptionPane.showMessageDialog((Component) obj, message);
	}
	
}
