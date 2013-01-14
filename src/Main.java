
import java.awt.event.*;
import java.util.ArrayList;

//import org.apache.http.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import javax.swing.*;

public class Main extends JFrame implements ActionListener,MouseListener {

	/**
	 * @param args
	 */
	//�������
	JLabel jlb=null;
	JPanel jp_down=null;
	JTextArea jta =null;
	
	JTextField jtf=null;
	
	JButton jb=null;
	JPanel jp=null;
	
	String temp="";

	JScrollPane jpe=null;
	String result=null;
	ArrayList<SHU>dblist=null;
	
   // static TudouBookInfo book=null;
    
	public static void main(String[] args){
		// TODO Auto-generated method stub
             Main m=new Main();
	}
	
	
	Main(){
		
		jlb=new  JLabel(new ImageIcon("Image/douban.png"));
		//jlb.setBounds(0, 0, 200, 100);
		jp_down=new JPanel(); //������ʾ����
		jta=new JTextArea("",20,7);
		jta.setEditable(false);//ֻ����ʾ�������Ա༭��
		jpe=new JScrollPane(jta);//���û��֣�������ʾ������Ϣ
		jtf=new JTextField("���������ߡ�ISBN");//����������Ĭ��ֵ
		
		
		jtf.setBounds(150, 20, 240, 35);
		jtf.addMouseListener(this);//����ԭ��jtf.addActionListener(this)
		jb=new JButton("����");//����������ť
		jb.setBounds(395,20,80,35);
		jb.addActionListener(this);
	
		jp=new JPanel();//Ĭ����layout�����֡�
		
		jp.setLayout(null);

	
		jp.add(jtf);
		jp.add(jb);
		
		
		this.add(jlb,"North");
		
		this.add(jp,"Center");
		this.add(jpe,"South");
		
		this.setLocation(300, 200);
		this.setSize(600,500);
		this.setVisible(true);
		this.setResizable(false);
		this.setTitle("�������");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		 if(e.getSource()==jb){
			//���ö���api�����������Ϣ��
			//����java����json��ʽ�������ʾ��JTextArea��
			 if(jtf.getText().toString().equals("���������ߡ�ISBN")||jtf.getText().toString().equals(""))
				 return ;
			 jta.setText("");
			  temp=jtf.getText().trim().toString();//��ȥ�ո����ַ���  2023013
			  System.out.println(temp);
			  String url="https://api.douban.com/v2/book/search?q="+temp+"&start=0&count=20";
			 try{
				 DefaultHttpClient client = new DefaultHttpClient();
                 HttpGet get = new HttpGet(url);   
                 HttpResponse response = client.execute(get);
                 result=EntityUtils.toString(response.getEntity()); 
                 System.out.println(result);
			 }catch(Exception re){	  
				 re.printStackTrace();
				 
			 } 
			 
			 //����jsonstr��json2java������
			 Json dj=new Json();
			// String t="["+result+"]";
			 dj.Json2java(result);
			 dblist=new ArrayList<SHU>();
			 dblist=dj.getBklist();
			 System.out.println(dblist.size());
			 if(dblist.size()==0)
			 {
				 jta.append("���ҵ���û�У���");
			 }
			 else
			 {
			 for(int i=0;i<dblist.size();i++)
			 {
			  SHU book=dblist.get(i); 
			 jta.append(i+1+"��");
			 jta.append("������֣�"+book.getTitle()+"\r\n");
			 jta.append("���Isbn10��"+book.getIsbn10()+"\r\n");
			// jta.append("���Isbn13��"+book.getIsbn13()+"\r\n");
			 jta.append("���ҳ����"+book.getPages()+"\r\n");
			 jta.append("������ߣ�"+book.getAuthor()+"\r\n");
			 jta.append("װ����"+book.getBinding()+"\r\n");
			 jta.append("��ļ۸�"+book.getPrice()+"\r\n");
			 jta.append("����ʱ�䣺"+book.getPubdate()+"\r\n");
			 jta.append("�����磺"+book.getPublisher()+"\r\n");
			 jta.append("��Ľ��ܣ�"+"\r\n");
			 jta.append(book.getSummary()+"\r\n");
			 
			 }	
			 }
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
               
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	
       	  
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if(jtf.getText().toString().equals("���������ߡ�ISBN"))
		 jtf.setText("");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(jtf.getText().toString().equals(""))
		 jtf.setText("���������ߡ�ISBN");
	}

}
