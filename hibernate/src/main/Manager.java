package main;


import org.hibernate.cfg.Configuration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
public class Manager 
{

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Configuration conf=new Configuration().configure();
		SessionFactory sessionFactory=conf.buildSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		Good good=new Good();
		good.setFan_num(Integer.valueOf(5));
		good.setGoods_type(Integer.valueOf(0));
		good.setKinds(Integer.valueOf(2));
		good.setPic_url("pic_url_test");
		good.setPrice(Float.valueOf(23));
		good.setQuan_num(Integer.valueOf(2));
		good.setTaobao_price(Float.valueOf(100));
		good.setTitle("title_test");
		good.setUrl("url_test");
		
		session.save(good);
		transaction.commit();
		session.close();
		sessionFactory.close();

	
		
		
		
	}

}
