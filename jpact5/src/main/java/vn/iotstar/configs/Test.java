package vn.iotstar.configs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import vn.iotstar.entity.Category;

public class Test 
{
public static void main(String[] args) {
		
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		Category cate = new Category();
		cate.setCategoryname("A");
		cate.setImages("https://cellphones.com.vn/sforum/wp-content/uploads/2021/07/gg.jpg");
		cate.setStatus(1);
		try {
			trans.begin();
			enma.persist(cate);
			trans.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}
}
